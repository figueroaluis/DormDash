import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Webscraping {

	public static void main(String[] args) {
		Document doc;
		Elements foods = new Elements();

		try {
			doc = Jsoup.connect("https://www.oxy.edu/student-life/campus-dining/marketplace").get();

			//Jsoup connect gets HTML code from website, but parse gets words

			//doc = Jsoup.parse("https://www.oxy.edu/student-life/campus-dining/marketplace");
			
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
			String formattedDate = (dateFormat.format(date));

			String title = doc.title();
			System.out.println("Title: " + title);

			Elements lines = doc.select("p");
			for(Element line : lines) {
				if(line.text().contains(formattedDate)) {		//add formattedDate
					System.out.println("Foods: " + line.text());
					System.out.println(line.nextElementSibling().text());
					System.out.println(line.nextElementSibling().nextElementSibling().text());
					System.out.println(line.nextElementSibling().nextElementSibling().nextElementSibling().text());
					System.out.println(line.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text());
				}
			}
			
			
			
			
			
			//System.out.println(foods);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}
}
