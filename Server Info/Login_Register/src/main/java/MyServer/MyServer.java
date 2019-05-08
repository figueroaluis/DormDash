package MyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class MyServer {
    /*pretend database of users using a HashMap where key
    will be the username and the value will be the password or hashed password*/
	//public static HashMap<String, String> users = new HashMap<>();

	public static HashMap<String, String> OpenOrders = new HashMap<>(); //key is orderID, value is orderText
	public static HashMap<String, String> CustomerOrder = new HashMap<>(); //key is order_id, username is value
	public static HashMap<String, String> DeliveredBy = new HashMap<>(); //key is username of orderID,
																			// value is deliveryperson username
	public static HashMap<Integer, Float> OrderPrices = new HashMap<Integer, Float>(); //key is orderID, value is price of order

	public static void main(String[] args) {
		SpringApplication.run(MyServer.class, args);
	}
}