package MyServer;

import org.springframework.web.bind.annotation.*; 
import org.springframework.http.*;

import javax.servlet.http.*;
import javax.xml.transform.Result;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;


@RestController
public class UserController {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/DormDash?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASSWORD = "";
	static final String SAMPASSWORD = "Matosabe4";
    static Connection conn = null;
    static PreparedStatement ps = null;

	@RequestMapping(value = "/register", method = RequestMethod.POST) // <-- setup the endpoint URL at /hello with the HTTP POST method
	public ResponseEntity<String> register(@RequestBody String body, HttpServletRequest request) {
		String username = request.getParameter("username"); //Grabbing name and age parameters from URL
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
	@RequestMapping(value = "/login", method = RequestMethod.GET) // <-- setup the endpoint URL at /hello with the HTTP POST method
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
                //Retrieves the stored hashkey for the username logging in
                try {
                    storedHashedKey = rs.getString("password");
                    //Compare the stored hashed key with the input hashedKey generated from the password parameter to validate the login
                    if (storedHashedKey.equals(hashedKey)) {
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
	@RequestMapping(value = "/order", method = RequestMethod.POST) // <-- setup the endpoint URL at /hello with the HTTP POST method
	public ResponseEntity<String> order(@RequestBody String body, HttpServletRequest request) {
		//username varchar(40) not null,
//	orderID int not null,
//	foodOrder varchar(50),
//	orderPickupLocation varchar(40),
//	orderDropoffLocation varchar(40),
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");

<<<<<<< Updated upstream
=======
		String username = request.getParameter("username");
		String order = request.getParameter("foodOrder");
		String selectUsername = "SELECT username FROM users WHERE username = '" + username + "';";
		String insertSql = "INSERT INTO orders(username,foodOrder) VALUES (?,?)";
//		String orderPickupLocation = request.getParameter("orderPickupLocation");
//		String orderDropoffLocation = request.getParameter("orderDropoffLocation");

		try {

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, SAMPASSWORD);

			//get correct username
			ps = conn.prepareStatement(selectUsername);
			System.out.println(selectUsername);
			ResultSet rs = ps.executeQuery();

			//put on database

			ps = conn.prepareStatement(insertSql);
			ps.setString(1, username);
			ps.setString(2, order);
			ps.executeUpdate();



		} catch(Exception e) {
			System.out.println("Oops there was an error");
		}

		//String insertTableSql = "Insert Into Orders."

		return new ResponseEntity("{\"message\":\"order placed\"}", responseHeaders, HttpStatus.OK);

	}
    @RequestMapping(value = "/cancelorder", method = RequestMethod.GET)
	public ResponseEntity<String> cancelorder(@RequestBody String body, HttpServletRequest request) {

		//username varchar(40) not null,
//	orderID int not null,
//	foodOrder varchar(50),
//	orderPickupLocation varchar(40),
//	orderDropoffLocation varchar(40),
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");
>>>>>>> Stashed changes

		String order = request.getParameter("foodOrder");
<<<<<<< Updated upstream
		Random random = new Random();
		String OrderID = String.valueOf(random.ints(0,10000));
		String orderPickupLocation = request.getParameter("orderPickupLocation");
		String orderDropoffLocation = request.getParameter("orderDropoffLocation");
=======
		String selectUsername = "SELECT username FROM users WHERE username = '" + username + "';";
		String insertSql = "DELETE from orders WHERE username = "+ username;
//		String orderPickupLocation = request.getParameter("orderPickupLocation");
//		String orderDropoffLocation = request.getParameter("orderDropoffLocation");
>>>>>>> Stashed changes



		//String insertTableSql = "Insert Into Orders."

		return new ResponseEntity("{\"message\":\"user logged in\"}", responseHeaders, HttpStatus.OK);

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