package MyServer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.security.MessageDigest;
import java.sql.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@RestController
public class UserController {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/DormDash?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASSWORD = "";
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	static Connection conn = null;
	static PreparedStatement ps = null;

	@RequestMapping(value = "/register", method = RequestMethod.POST) // <-- setup the endpoint URL at /hello with the HTTP POST method
	public ResponseEntity<String> register(@RequestBody String body, HttpServletRequest request) {
		String username = request.getParameter("username"); //Grabbing name and age parameters
		String password = request.getParameter("password");
		String selectTableSql = "SELECT password FROM users WHERE username = '" + username + "';";
		String insertTableSql = "INSERT INTO users(username, password) VALUES(?, ?)";

		/*Creating http headers object to place into response entity the server will return.
		This is what allows us to set the content-type to application/json or any other content-type
		we would want to return */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");

		//Initializing a MessageDigest object which will allow us to digest a String with SHA-256
		MessageDigest digest = null;
		String hashedKey = null;
		try {
			digest = MessageDigest.getInstance("SHA-256"); //digest algorithm set to SHA-256
			//Converts the password to SHA-256 bytes. Then the bytes are converted to hexadecimal with the helper method written below
			hashedKey = bytesToHex(digest.digest(password.getBytes("UTF-8")));
			System.out.println(hashedKey);


		}catch(Exception e) {

		}
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			ps = conn.prepareStatement(selectTableSql);
			System.out.println(selectTableSql);
			ResultSet rs = ps.executeQuery();

			//Checking if the hashmap contains the username trying to register and returns a BAD_REQUEST if username is taken
			if (!rs.next() ) {

				System.out.println("Insert Into the User Table");
				ps = conn.prepareStatement(insertTableSql);
				ps.setString(1, username);
				ps.setString(2, hashedKey);
				ps.executeUpdate();


			}
			else {
				return new ResponseEntity("{\"message\":\"username taken\"}", responseHeaders, HttpStatus.BAD_REQUEST);
			}
		}
		catch(ClassNotFoundException ce){
			ce.printStackTrace();
			System.out.println("Class Not found");
		}
		catch(SQLException se){
			System.out.println("SQL Error");
			se.printStackTrace();

		}

		//Returns the response with a String, headers, and HTTP status
		return new ResponseEntity(hashedKey, responseHeaders, HttpStatus.OK);
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST) // <-- setup the endpoint URL at /hello with the HTTP POST method
	public ResponseEntity<String> login(HttpServletRequest request) {
		String username = request.getParameter("username"); //Grabbing name and age parameters from URL
		String password = request.getParameter("password");
		String selectTableSql = "SELECT password FROM users WHERE username = '" + username + "';";
		String storedHashedKey;

		/*Creating http headers object to place into response entity the server will return.
		This is what allows us to set the content-type to application/json or any other content-type
		we would want to return */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");

		MessageDigest digest = null;
		String hashedKey = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			//Hashing the input password so that we have something to compare with the stored hashed password
			hashedKey = bytesToHex(digest.digest(password.getBytes("UTF-8")));
		}catch(Exception e) {

		}
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			ps = conn.prepareStatement(selectTableSql);
			ResultSet rs = ps.executeQuery();
			//Check if the hashmap contains the username trying to login

			if (!rs.next()) {
				return new ResponseEntity("{\"message\":\"username not registered\"}", responseHeaders, HttpStatus.BAD_REQUEST);
			}
			else {
				try {
					storedHashedKey = rs.getString("password");
					//Compare the stored hashed key with the input hashedKey generated from the password parameter to validate the login

					if (storedHashedKey.equals(hashedKey)) {

						//We will sign our JWT with our ApiKey secret
						String jws = Jwts.builder().setHeaderParam("typ", "JWT").setSubject(sessionGen.randomAlphaNumeric(10)).signWith(key).compact();
						responseHeaders.set("Authorization", jws);
//						MyServer.users.put(username, jws);
						return new ResponseEntity("{\"message\":\"user logged in\"}", responseHeaders, HttpStatus.OK);
					}
				}
				catch(SQLException se) {}

			}
		}
		catch(ClassNotFoundException ce){
			ce.printStackTrace();
			System.out.println("Class Not found");
		}
		catch(SQLException se){
			System.out.println("SQL Error");
			se.printStackTrace();

		}
		return new ResponseEntity("{\"message\":\"username/password combination is incorrect\"}", responseHeaders, HttpStatus.BAD_REQUEST);

	}
	@RequestMapping(value = "/order", method = RequestMethod.POST) // <-- setup the endpoint URL at /order with the HTTP POST method
	public ResponseEntity<String> order(@RequestBody String body, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");
		System.out.println("made it to here");

		//section to verify authorization. If it fails jump to the catch clause

		String token = null;
        String order_id = null;


        try {
			final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(request.getHeader("Authorization")).getBody();

			token = request.getHeader("Authorization");
			responseHeaders.set("Authorization", token);

			System.out.println(claims.getSubject());
		} catch (final SignatureException e) {
			return new ResponseEntity("{\"message\":\"Invalid Session\"}", responseHeaders, HttpStatus.FORBIDDEN);
		}

		String username = request.getParameter("username");
		String foodOrder = request.getParameter("foodOrder");
		String orderPickupLocation = request.getParameter("orderPickupLocation");
		String orderDropoffLocation = request.getParameter("orderDropoffLocation");
		String selectUsername = "SELECT username FROM users WHERE username = '" + username + "';";
		String insertSql = "INSERT INTO orders(username,foodOrder, orderPickupLocation, orderDropoffLocation) " +
				"VALUES (?, ?, ?, ?)";
        String grabOrder = "Select orderID From orders Where username = '" + username +"';";

		//section for ordercheck and hashmap placement
		if (MyServer.CustomerOrder.containsKey(username)){
			return new ResponseEntity("{\"message\":\"Order already exists.\"}", responseHeaders, HttpStatus.BAD_REQUEST);

		}

		//section for SQL stuff that will save in case of server failuer
		try {

            Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			//get correct username
			ps = conn.prepareStatement(selectUsername);
			System.out.println(selectUsername);
			ResultSet rs = ps.executeQuery();

			//put on database
			ps = conn.prepareStatement(insertSql);
			ps.setString(1, username);
			ps.setString(2, foodOrder);
			ps.setString(3, orderPickupLocation);
			ps.setString(4, orderDropoffLocation);
			ps.executeUpdate();

            ps = conn.prepareStatement(grabOrder);
            ResultSet order_rs = ps.executeQuery();
            System.out.println(order_rs);

            while (order_rs.next()) {

                order_id = order_rs.getString("orderID");
            }



		} catch(Exception e) {
			System.out.println("Oops there was an error");
			e.printStackTrace();
			return new ResponseEntity("{\"message\":\"Something went wrong :(\"}", responseHeaders, HttpStatus.BAD_REQUEST);
		}
        MyServer.CustomerOrder.put(username, order_id);


		return new ResponseEntity("{\"message\":\"order placed\"}", responseHeaders, HttpStatus.OK);
	}
	//if the consumer has recieved the order
	@RequestMapping(value = "/recievedorder", method = RequestMethod.POST) // <-- setup the endpoint URL at /order with the HTTP POST method
	public ResponseEntity<String> recievedorder(@RequestBody String body, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");
		System.out.println("made it to here");

		//section to verify authorization. If it fails jump to the catch clause
		String token;
		try {
			final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(request.getHeader("Authorization")).getBody();

			token = request.getHeader("Authorization");
			responseHeaders.set("Authorization", token);

			System.out.println(claims.getSubject());
		} catch (final SignatureException e) {
			return new ResponseEntity("{\"message\":\"Invalid Session\"}", responseHeaders, HttpStatus.FORBIDDEN);
		}

		String username = request.getParameter("username");
        //begin different sections that affect the user and the delivery person

        //method 1: complete order for user
        //method 2: complete order for deliveryperson

        if (MyServer.CustomerOrder.containsKey(username) || MyServer.DeliveredBy.containsKey(username)){
            if (MyServer.CustomerOrder.get(username).equals(username)){

            }
            if (MyServer.DeliveredBy.get(username).equals(username)){

            }
        }

		//section for ordercheck and hashmap placement
		if (!MyServer.CustomerOrder.containsKey(username)){
			return new ResponseEntity("{\"message\":\"You don't have an order?\"}", responseHeaders, HttpStatus.BAD_REQUEST);
		}
	
		MyServer.CustomerOrder.remove(username);

		return new ResponseEntity("{\"message\":\"Error\"}", responseHeaders, HttpStatus.BAD_REQUEST);

	}
	@RequestMapping(value = "/cancelorder", method = RequestMethod.DELETE)
	public ResponseEntity<String> cancelorder(@RequestBody String body, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");
		String username = request.getParameter("username");
		String order = request.getParameter("foodOrder");
		String selectUsername = "SELECT username FROM users WHERE username = '" + username + "';";
		String insertSql = "DELETE FROM orders WHERE username = '" + username + "' AND foodOrder = '" + order + "';";
		String token;

		try {
			final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(request.getHeader("Authorization")).getBody();

			token = request.getHeader("Authorization");
			responseHeaders.set("Authorization", token);

			System.out.println(claims.getSubject());
		} catch (final SignatureException e) {
			return new ResponseEntity("{\"message\":\"Invalid Session\"}", responseHeaders, HttpStatus.FORBIDDEN);
		}

		//section for ordercheck and hashmap placement
		if (!MyServer.CustomerOrder.containsKey(username)){
			return new ResponseEntity("{\"message\":\"You don't have an order?\"}", responseHeaders, HttpStatus.BAD_REQUEST);
		}
        System.out.println( MyServer.CustomerOrder.keySet());
        MyServer.CustomerOrder.remove(username);



		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//get correct username
			ps = conn.prepareStatement(selectUsername);
			System.out.println(selectUsername);
			ResultSet rs = ps.executeQuery();
			//put on database
			ps = conn.prepareStatement(insertSql);
			ps.executeUpdate();
		} catch(ClassNotFoundException cne) {
			System.out.println("Class Not Found Exception");
		} catch (SQLException se) {
			System.out.println("SQL Exception");

		} catch(Exception e) {
			System.out.println("Oops there was an error");

		}
		return new ResponseEntity("{\"message\":\"order canceled\"}", responseHeaders, HttpStatus.OK);

	}

	@RequestMapping(value = "/acceptorder", method = RequestMethod.POST)
	public ResponseEntity<String> acceptorder(@RequestBody String body, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");

		String username = request.getParameter("username");
        String recipient = request.getParameter("recipient");
		String order = request.getParameter("foodOrder");
        String workstatus = request.getParameter("working");
		String selectUsername = "SELECT orderID FROM orders WHERE foodOrder = '" + order + "';";

		String token;
		try {
			final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(request.getHeader("Authorization")).getBody();

			token = request.getHeader("Authorization");
			responseHeaders.set("Authorization", token);

			System.out.println(claims.getSubject());
		} catch (final SignatureException e) {
			return new ResponseEntity("{\"message\":\"Invalid Session\"}", responseHeaders, HttpStatus.FORBIDDEN);
		}
		//check if working
		if (Integer.parseInt(workstatus) == 0) {
			return new ResponseEntity("{\"message\":\"You aren't working at the moment" +
					". Activate your work status.\"}", responseHeaders, HttpStatus.BAD_REQUEST);
		}

		//section for ordercheck and hashmap placement
		if (MyServer.DeliveredBy.containsKey(username)){
			return new ResponseEntity("{\"message\":\"You already have an active order.\"}", responseHeaders, HttpStatus.BAD_REQUEST);
		}

		MyServer.DeliveredBy.put(username, recipient);

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//get correct username
			ps = conn.prepareStatement(selectUsername);
			System.out.println(selectUsername);
			ResultSet rs = ps.executeQuery();
			System.out.println("here");
			int id = 0;
			if(rs.next()){
				id = rs.getInt("orderID");
			}
			System.out.println(id);
			String insertSql = "UPDATE users SET orderAccepted = " + id + " WHERE username = '" + username + "';";
			//put on database
			ps = conn.prepareStatement(insertSql);
			ps.executeUpdate();

		} catch(ClassNotFoundException cne) {
			System.out.println("Class Not Found Exception");
		} catch (SQLException se) {
			System.out.println("SQL Exception");

		} catch(Exception e) {
			System.out.println("Oops there was an error");

		}


		return new ResponseEntity("{\"message\":\"order accepted\"}", responseHeaders, HttpStatus.OK);



	}

	@RequestMapping(value = "/worktime", method = RequestMethod.POST) // <-- setup the endpoint URL at /hello with the HTTP POST method
	public ResponseEntity<String> worktime(HttpServletRequest request) {
		String workStatus = request.getParameter("working");
        String username = request.getParameter("username");


        HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");

		String updateSql = "UPDATE Users SET is_working = ?" + "WHERE username = '" + username + "';";


		String token;
		try {
			final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(request.getHeader("Authorization")).getBody();

			token = request.getHeader("Authorization");
			responseHeaders.set("Authorization", token);

			System.out.println(claims.getSubject());
		} catch (final SignatureException e) {
			return new ResponseEntity("{\"message\":\"Invalid Session\"}", responseHeaders, HttpStatus.FORBIDDEN);
		}
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			//put on database
			ps = conn.prepareStatement(updateSql);
			ps.setString(1, workStatus);

			ps.executeUpdate();

		} catch(Exception e) {
			System.out.println("Oops there was an error");
			e.printStackTrace();
			return new ResponseEntity("{\"message\":\"Something went wrong :(\"}", responseHeaders, HttpStatus.BAD_REQUEST);
		}
		if (Integer.parseInt(workStatus) == 0){
			return new ResponseEntity("{\"message\":\"You are not working anymore.\"}", responseHeaders, HttpStatus.OK);}
		return new ResponseEntity("{\"message\":\"You are working now.\"}", responseHeaders, HttpStatus.OK);




	}

	//Helper method to convert bytes into hexadecimal
	public static String bytesToHex(byte[] in) {
		StringBuilder builder = new StringBuilder();
		for(byte b: in) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
}
