
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertData {

	//establish connections to the database
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/DormDash_db";
	static final String USER = "root";
	static final String PASSWORD = "Carol13Uc";


	//insertPlayers
	public static void registerUser() {

		Connection conn = null;
		PreparedStatement psUser = null;
		PreparedStatement psWorker = null;

		//open connection
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			String username = "";
			String password = "";

			//if(selected register as user) {

			String insertUsers = "INSERT INTO users (username, password) values(?, ?)";
			psUser = conn.prepareStatement(insertUsers);

			psUser.setString(1, username);
			psUser.setString(2, password);
			psUser.executeUpdate();

			//} else {

			String insertWorkers = "INSERT INTO workers (username, password) values(?, ?)";
			psWorker = conn.prepareStatement(insertWorkers);

			psWorker.setString(1, username);
			psWorker.setString(2, password);
			psWorker.executeUpdate();

			//}



			psUser.close();
			psWorker.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	public static void loginUser() {
		Connection conn = null;
		PreparedStatement psUser = null;
		PreparedStatement psWorker = null;

		//open connection
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			String username = "";
			String password = "";
			
			//if(login as user) {

			String loginUsers = "SELECT username, password FROM users WHERE username = " + username
					+ " AND password = " + password + ";";
			psUser = conn.prepareStatement(loginUsers);

			psUser.setString(1, username);
			psUser.setString(2, password);
			
			ResultSet rsUser  = psUser.executeQuery();
		
			//} else {

			String loginWorkers = "SELECT username, password FROM workers WHERE username = " + username
					+ " AND password = " + password + ";";
			psWorker = conn.prepareStatement(loginWorkers);

			psWorker.setString(1, username);
			psWorker.setString(2, password);
			
			ResultSet rsWorker  = psWorker.executeQuery();

			//}

			psUser.close();
			psWorker.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void insertMenu() {
		Connection conn = null;
		PreparedStatement ps = null;

		//open connection
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			String dayOfWeek = "";
			String food = "";

			String addFood = "INSERT INTO menu (dayOfWeek, foodItem) values(?, ?)";
			ps = conn.prepareStatement(addFood);

			ps.setString(1, dayOfWeek);
			ps.setString(2, food);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void addOrders() {
		Connection conn = null;
		PreparedStatement ps = null;

		//open connection
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			int userID = 0;
			int workerID = 0;
			int orderID = 0;
			String foodOrder = "";
			String orderPickupLocation = "";
			String pickupTime = "";
			

			String insertOrder = "INSERT INTO orders (userID, workerID, orderID, foodOrder, orderPickupLocation,"
					+ "pickupTime) values(?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(insertOrder);

			ps.setInt(1, userID);
			ps.setInt(2, workerID);
			ps.setInt(3, orderID);
			ps.setString(4, foodOrder);
			ps.setString(5, orderPickupLocation);
			ps.setString(6, pickupTime);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
