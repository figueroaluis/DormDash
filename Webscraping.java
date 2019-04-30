import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Webscraping {

	//establish connections to the database
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/DormDash_db";
	static final String USER = "root";
	static final String PASSWORD = "Carol13Uc";


	public static void insertMenu() {
		Document doc;
		Elements foods = new Elements();
		
		Connection conn = null;
		PreparedStatement ps = null;
	
		
		
		try {
			doc = Jsoup.connect("https://www.oxy.edu/student-life/campus-dining/marketplace").get();

			//Jsoup connect gets HTML code from website, but parse gets words

			//doc = Jsoup.parse("https://www.oxy.edu/student-life/campus-dining/marketplace");

			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
			String formattedDate = (dateFormat.format(date));

			String title = doc.title();
			//System.out.println("Title: " + title);

			String pageDate = null;
			String hours = null;
			String breakfast = null;
			String lunch = null;
			String dinner = null;

			Elements lines = doc.select("p");
			for(Element line : lines) {
				if(line.text().contains("April 25, 2019")) {		//add formattedDate
					//					System.out.println("Foods: " + line.text());
					pageDate = line.text();

					//					System.out.println(line.nextElementSibling().text());
					hours = line.nextElementSibling().text();

					//					System.out.println(line.nextElementSibling().nextElementSibling().text());
					breakfast = line.nextElementSibling().nextElementSibling().text();

					//					System.out.println(line.nextElementSibling().nextElementSibling().nextElementSibling().text());
					lunch = line.nextElementSibling().nextElementSibling().nextElementSibling().text();

					//					System.out.println(line.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text());
					dinner = line.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text();
				}
			}

			//BREAKFAST

			String[] b1 = null;
			String[] b2 = null;
			String[] b3 = null;
			String[] b4 = null;

			if(breakfast.contains("9:15am ")) {
				b1 = breakfast.split("9:15am ");
			}
			//			System.out.println(b1[0]);
			//			System.out.println(b1[1]);

			if(breakfast.contains("Waffle Station")) {
				b2 = b1[1].split("Waffle Station");
			}
			//			System.out.println(b2[0]);
			//			System.out.println(b2[1]);

			String bGrill = b2[0];

			if(breakfast.contains("Hot &")) {
				b3 = b2[1].split("Hot &");
			}
			//			System.out.println(b3[0]);
			//			System.out.println(b3[1]);

			String bWaffle = "Waffle Station";
			String bCereal = "Hot & Cold Cereal";
			String bSalad = "Salad Bar";
			String bPastries = "Breakfast Pastries";

			if(bGrill.contains(" - ")) {
				b4 = bGrill.split(" - ");
			}

			bGrill = b4[2];

			//			System.out.println(bGrill);
			//			System.out.println(bWaffle);
			//			System.out.println(bCereal);
			//			System.out.println(bSalad);
			//			System.out.println(bPastries);
			
			ArrayList<String> breakfasts = new ArrayList<>();
			breakfasts.add(bGrill);
			breakfasts.add(bWaffle);
			breakfasts.add(bCereal);
			breakfasts.add(bSalad);
			breakfasts.add(bPastries);


			//LUNCH
			String[] l1 = null;
			String[] l2 = null;
			String[] l3 = null;
			String[] l4 = null;
			String[] l5 = null;
			String[] l6 = null;
			String lHomestyle = null;
			String lSaute = null;
			String lChefsCorner = null;
			String lGrill = null;
			String lSpecialtySalad = null;
			String lHomestyleVeg = null;
			String lGrillVeg = null;
			String lSpecialtySaladOther = null;
			String lSoupVeg = null;

			if(lunch.contains("1:30pm ")) {
				l1 = lunch.split("1:30pm ");
			}
			//			System.out.println(l1[1]);

			if(lunch.contains("Sauté Station")) {
				l2 = l1[1].split("Sauté Station");
				lHomestyle = l2[0];
			}
			//			System.out.println(l2[0]);
			//			System.out.println(l2[1]);


			if(lunch.contains("Chef's Corner")) {
				l3 = l2[1].split("Chef's Corner");
				lSaute = "Sauté Station" + l3[0];
			}
			//			System.out.println(l3[0]);
			//			System.out.println(l3[1]);



			if(lunch.contains("Grill Station")) {
				l4 = l3[1].split("Grill Station");
				lChefsCorner = "Chef's Corner" + l4[0];
			}
			//			System.out.println(l4[0]);
			//			System.out.println(l4[1]);



			if(lunch.contains("Specialty Salad")) {
				l5 = l4[1].split("Specialty Salad");
				lGrill = "Grill Station" + l5[0];
			}
			//			System.out.println(l5[0]);
			//			System.out.println(l5[1]);



			if(lunch.contains("Salad Bar ")) {
				l6 = l5[1].split("Salad Bar ");
				lSpecialtySalad = "Specialty Salad" + l6[0];
			}


			String lSaladBar = "Salad Bar";
			String lSoup = l6[1];

			if(lHomestyle.contains(" - ")) {
				l1 = lHomestyle.split(" - ");
				lHomestyle = l1[2];
			}

			if(lSaute.contains(" - ")) {
				l1 = lSaute.split(" - ");
				lSaute = l1[2];
			}

			if(lChefsCorner.contains(" - ")) {
				l1 = lChefsCorner.split(" - ");
				lChefsCorner = l1[2];
			}

			if(lGrill.contains(" - ")) {
				l1 = lGrill.split(" - ");
				lGrill = l1[2];
			}

			if(lSpecialtySalad.contains(" - ")) {
				l1 = lSpecialtySalad.split(" - ");
				lSpecialtySalad = l1[1];
			}
			
			if(lSoup.contains(" - ")) {
				l1 = lSoup.split(" - ");
				lSoup = l1[1];
			}
			
			if(lHomestyle.contains(" or ")) {
				l1 = lHomestyle.split(" or ");
				lHomestyle = l1[0];
				lHomestyleVeg = l1[1];
			}
			
			if(lGrill.contains(" or ")) {
				l1 = lGrill.split(" or ");
				lGrill = l1[0];
				lGrillVeg = l1[1];
			}
			
			if(lSpecialtySalad.contains(" or ")) {
				l1 = lSpecialtySalad.split(" or ");
				lSpecialtySalad = l1[0];
				lSpecialtySaladOther = l1[1];
			}
			
			if(lSoup.contains(" or ")) {
				l1 = lSoup.split(" or ");
				lSoup = l1[0];
				lSoupVeg = l1[1];
			}
			
			
//			System.out.println(lHomestyle);
//			System.out.println(lSaute);
//			System.out.println(lChefsCorner);
//			System.out.println(lGrill);
//			System.out.println(lSpecialtySalad);
//			System.out.println(lSaladBar);
////		System.out.println(lSoup);
//			System.out.println(lHomestyleVeg);
//			System.out.println(lGrillVeg);
//			System.out.println(lSpecialtySaladOther);
//			System.out.println(lSoupVeg);
			
			ArrayList<String> lunches = new ArrayList<>();
			lunches.add(lHomestyle);
			lunches.add(lSaute);
			lunches.add(lChefsCorner);
			lunches.add(lGrill);
			lunches.add(lSpecialtySalad);
			lunches.add(lSaladBar);
			lunches.add(lSoup);
			
			if(lHomestyleVeg != null) {
				lunches.add(lHomestyleVeg);
			}
			if(lGrillVeg != null) {
				lunches.add(lGrillVeg);	
			}
			if(lSpecialtySaladOther != null) {
				lunches.add(lSpecialtySaladOther);
			}
			if(lSoupVeg != null) {
				lunches.add(lSoupVeg);
			}
			
			
			//DINNER
			String[] d1 = null;
			String[] d2 = null;
			String[] d3 = null;
			String[] d4 = null;
			String[] d5 = null;
			String[] d6 = null;
			String dHomestyle = null;
			String dSaute = null;
			String dChefsCorner = null;
			String dGrill = null; 
			String dSpecialtySalad = null;
			String dSoup = null;
			String dHomestyleVeg = null;
			String dGrillVeg = null;
			String dSpecialtySaladOther = null;
			String dSoupVeg = null;
					
			
			if(dinner.contains("7:30pm ")) {
				d1 = dinner.split("7:30pm ");
			}
//			System.out.println(d1[1]);
			
			if(dinner.contains("Sauté Station")) {
				d2 = d1[1].split("Sauté Station");
				dHomestyle = d2[0];
			}
//			System.out.println(d2[0]);
//			System.out.println(d2[1]);
			
			if(dinner.contains("Chef's Corner")) {
				d3 = d2[1].split("Chef's Corner");
				dSaute = d3[0];
			}
//			System.out.println(d3[0]);
//			System.out.println(d3[1]);
			
			if(dinner.contains("Grill Station")) {
				d4 = d3[1].split("Grill Station");
				dChefsCorner = d4[0];
			}
//			System.out.println(d4[0]);
//			System.out.println(d4[1]);
			
			if(dinner.contains("Specialty Salad")) {
				d5 = d4[1].split("Specialty Salad");
				dGrill = d5[0];
			}
//			System.out.println(d5[0]);
//			System.out.println(d5[1]);
		
			if(dinner.contains("Salad Bar ")) {
				d6 = d5[1].split("Salad Bar ");
				dSpecialtySalad = d6[0];
			}
			
			String dSaladBar = "Salad Bar";
			dSoup = d6[1];
			
			
			if(dHomestyle.contains(" - ")) {
				d1 = dHomestyle.split(" - ");
				dHomestyle = d1[2];
			}
			
			if(dSaute.contains(" - ")) {
				d1 = dSaute.split(" - ");
				dSaute = d1[2];
			}
			
			if(dChefsCorner.contains(" - ")) {
				d1 = dChefsCorner.split(" - ");
				dChefsCorner = d1[2];
			}
			
			if(dGrill.contains(" - ")) {
				d1 = dGrill.split(" - ");
				dGrill = d1[2];
			}
			
			if(dSpecialtySalad.contains(" - ")) {
				d1 = dSpecialtySalad.split(" - ");
				dSpecialtySalad = d1[1];
			}
			
			if(dSoup.contains(" - ")) {
				d1 = dSoup.split(" - ");
				dSoup = d1[1];
			}
			
			if(dHomestyle.contains(" or ")) {
				d1 = dHomestyle.split(" or ");
				dHomestyle = d1[0];
				dHomestyleVeg = d1[1];
			}
			
			if(dGrill.contains(" or ")) {
				d1 = dGrill.split(" or ");
				dGrill = d1[0];
				dGrillVeg = d1[1];
			}
			
			if(dSpecialtySalad.contains(" or ")) {
				d1 = dSpecialtySalad.split(" or ");
				dSpecialtySalad = d1[0];
				dSpecialtySaladOther = d1[1];
			}
			
			if(dSoup.contains(" or ")) {
				d1 = dSoup.split(" or ");
				dSoup = d1[0];
				dSoupVeg = d1[1];
			}
			
			
//			System.out.println(dHomestyle);
//			System.out.println(dSaute);
//			System.out.println(dChefsCorner);
//			System.out.println(dGrill);
//			System.out.println(dSpecialtySalad);
//			System.out.println(dSaladBar);
//			System.out.println(dSoup);
//			System.out.println(dHomestyleVeg);
//			System.out.println(dGrillVeg);
//			System.out.println(dSpecialtySaladOther);
//			System.out.println(dSoupVeg);
			
			ArrayList<String> dinners = new ArrayList<>();
			dinners.add(dHomestyle);
			dinners.add(dSaute);
			dinners.add(dChefsCorner);
			dinners.add(dGrill);
			dinners.add(dSpecialtySalad);
			dinners.add(dSaladBar);
			dinners.add(dSoup);
			
			if(dHomestyleVeg != null) {
				dinners.add(dHomestyleVeg);
			}
			if(dGrillVeg != null) {
				dinners.add(dGrillVeg);	
			}
			if(dSpecialtySaladOther != null) {
				dinners.add(dSpecialtySaladOther);
			}
			if(dSoupVeg != null) {
				dinners.add(dSoupVeg);
			}
			
			
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			String insertFood = "INSERT INTO Menu (date, foodItem, mealType) values(?, ?, ?)";
			ps = conn.prepareStatement(insertFood);

			for(String item : breakfasts) {
				ps.setString(1, formattedDate);
				ps.setString(2, item);
				ps.setString(3, "breakfast");
				ps.executeUpdate();
			}
			
			for(String item : lunches) {
				ps.setString(1, formattedDate);
				ps.setString(2, item);
				ps.setString(3, "lunch");
				ps.executeUpdate();
			}
			
			for(String item : dinners) {
				ps.setString(1, formattedDate);
				ps.setString(2, item);
				ps.setString(3, "dinner");
				ps.executeUpdate();
			}
			
			ps.close();
			conn.close();
			
		

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		insertMenu();
	}
}
