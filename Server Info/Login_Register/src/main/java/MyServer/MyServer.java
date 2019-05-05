package MyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class MyServer {
    /*pretend database of users using a HashMap where key
    will be the username and the value will be the password or hashed password*/
	//public static HashMap<String, String> users = new HashMap<>();

	public static HashMap<String, String> CustomerOrder = new HashMap<>();
	public static HashMap<String, String> DeliveredBy = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(MyServer.class, args);
	}
}