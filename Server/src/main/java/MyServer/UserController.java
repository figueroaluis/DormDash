package MyServer;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import javax.servlet.http.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {
    @RequestMapping(value = "/register", method = RequestMethod.POST) // <-- setup the endpoint URL at /hello with the HTTP POST method
    public ResponseEntity<String> register(@RequestBody String body, HttpServletRequest request) {
        String username = request.getParameter("username"); //Grabbing name and age parameters from URL
        String password = request.getParameter("password");

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

        }catch(Exception e) {

        }

        //Checking if the hashmap contains the username trying to register and returns a BAD_REQUEST if username is taken
        if (!MyServer.users.containsKey(username)) {
            MyServer.users.put(username, hashedKey); //Stores the username and hashed password into the HashMap
        }else {
            return new ResponseEntity("{\"message\":\"username taken\"}", responseHeaders, HttpStatus.BAD_REQUEST);
        }
        //Returns the response with a String, headers, and HTTP status
        return new ResponseEntity(hashedKey, responseHeaders, HttpStatus.OK);
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET) // <-- setup the endpoint URL at /hello with the HTTP POST method
    public ResponseEntity<String> login(HttpServletRequest request) {
        String username = request.getParameter("username"); //Grabbing name and age parameters from URL
        String password = request.getParameter("password");

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

        //Check if the hashmap contains the username trying to login
        if (!MyServer.users.containsKey(username)) {
            return new ResponseEntity("{\"message\":\"username not registered\"}", responseHeaders, HttpStatus.BAD_REQUEST);
        }else {
            //Retrieves the stored hashkey for the username logging in
            String storedHashedKey = MyServer.users.get(username);
            //Compare the stored hashed key with the input hashedKey generated from the password parameter to validate the login
            if (storedHashedKey.equals(hashedKey)) {
                return new ResponseEntity("{\"message\":\"user logged in\"}", responseHeaders, HttpStatus.OK);
            }else {
                return new ResponseEntity("{\"message\":\"username/password combination is incorrect\"}", responseHeaders, HttpStatus.BAD_REQUEST);
            }
        }
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