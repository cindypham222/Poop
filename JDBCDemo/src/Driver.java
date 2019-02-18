import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.json.HTTP;
import org.json.JSONPointer;



public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//loadMovies();
			//testLoad();
			//loadCredits();
			//failingAgain();
			csvParser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//1. Get connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "C1ndyPh4m222");
			
			//2. Create a statement
			Statement myStmt = myConn.createStatement();
			//3. Execute query
			ResultSet myRe = myStmt.executeQuery("select * from employees");
			
			//4. Process results set
//			while (myRe.next()){
//				System.out.println(myRe.getString("last_name")+", " +
//			myRe.getString("first_name"));
//			}		
		
		}
		catch(Exception exc) {
			
		}
	}
	
	private static void csvParser() throws Exception {
		ProductionCompanyFactory pc = new ProductionCompanyFactory();
		GenresFactory g = new GenresFactory();
		ProductionCountryFactory prc = new ProductionCountryFactory();
		SpokenLanguageFactory sl = new SpokenLanguageFactory();
		KeywordsFactory k = new KeywordsFactory();
		File creditFile = new File("./Files/tmdb_5000_credits.csv");
		File moviesFile = new File("./Files/tmdb_5000_movies.csv");
		CSVParser movieParser = CSVParser.parseFile(moviesFile);
		List<String> cells;
		movieParser.nextLine();
		while (movieParser.hasNext()) {
		     cells = movieParser.nextLine();
		     //g.GenerateGenres(cells.get(1));
		     k.GenerateKeywords(cells.get(4));
		     System.out.println("Are there results " + k.GenerateKeywords(cells.get(4)).toString());
//		     pc.GenerateProductionCompany(cells.get(9));
//		     prc.GenerateProductionCountry(cells.get(10));
//		     sl.GenerateSpokenLanguage(cells.get(14));
		}
	}
	 
	
	private static void loadCredits() throws Exception {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "C1ndyPh4m222");
		String creditsStatement = "Insert into CREDIT values (?, ?, ?, ?)";
		String moviesStatement = "Insert into MOVIE values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertCredits = myConn.prepareStatement(creditsStatement);
		PreparedStatement insertMovies = myConn.prepareStatement(moviesStatement);
		File creditFile = new File("./Files/tmdb_5000_credits.csv");
		File moviesFile = new File("./Files/tmdb_5000_movies.csv");
		CSVParser creditParser = CSVParser.parseFile(creditFile);
		CSVParser movieParser = CSVParser.parseFile(moviesFile);
		List<String> cells;
		List<String> c2;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		boolean firstLine = true;
//		while (creditParser.hasNext()) {
//			if (firstLine) {
//				creditParser.nextLine();
//				firstLine = false;
//			}
//			else {
//		     cells = creditParser.nextLine();
//		     //System.out.println(cells);
//		     insertCredits.setInt(1, Integer.parseInt(cells.get(0)));
//		     insertCredits.setString(2, cells.get(1));
//		     insertCredits.setObject(3, cells.get(2));
//		     insertCredits.setObject(4, cells.get(3));
//		     insertCredits.execute(); //Maybe try and batch this
//			}
//		}
		firstLine = true;
		movieParser.nextLine();
		while (movieParser.hasNext()) {
//			if (firstLine) {
//				movieParser.nextLine();
//				firstLine = false;
//			}
//			else {
		     c2 = movieParser.nextLine();
		     if(c2.isEmpty()) {
		    	 System.out.println("Opps");
		     }
//		     System.out.println(c2);
//		     //budget
//		     insertMovies.setInt(1, Integer.parseInt(c2.get(0)));
//		     //genres
//		     insertMovies.setObject(2, c2.get(1));
//		     //homepage
//		     insertMovies.setString(3, c2.get(2));
//		     //id
//		     insertMovies.setInt(4, Integer.parseInt(c2.get(3)));
//		     //keywords
//		     insertMovies.setObject(5, c2.get(4));
//		     //original_language
//		     insertMovies.setString(6, c2.get(5));
//		     //original_title
//		     insertMovies.setString(7, c2.get(6));
//		     //overview
//		     insertMovies.setString(8, c2.get(7));
//		     //popularity
//		     insertMovies.setFloat(9, Float.parseFloat(c2.get(8)));
//		     //production_company
//		     insertMovies.setObject(10, c2.get(9));
		     ProductionCompanyFactory test = null;
		     test.GenerateProductionCompany(c2.get(9));
//		     //production_countries
//		     insertMovies.setObject(11, c2.get(10));
//		     //release_date
//		     Date date = Date.valueOf(c2.get(11));
// 		     insertMovies.setDate(12, date);
//		     //revenue
//		     insertMovies.setInt(13, '1');
//		     //runtime
//		     insertMovies.setInt(14, Integer.parseInt(c2.get(13)));
//		     //spoken_languages
//		     insertMovies.setObject(15, c2.get(14));
//		     //status
//		     insertMovies.setString(16, c2.get(15));
//		     //tagline
//		     insertMovies.setObject(17, c2.get(16));
//		     //title
//		     insertMovies.setString(18, c2.get(17));
//		     //vote_average
//		     insertMovies.setFloat(19, Float.parseFloat(c2.get(18)));
//		     //vote_count
//		     insertMovies.setInt(20, Integer.parseInt(c2.get(19)));
//		     insertMovies.execute();
		     

		     //What this code can look like now is 
		     /*
		      * Read The File in with CSV Parser
		      * 
		      * Create Appropriate Factories ie Movies Credits
		      * 
		      * While Parsing this, you will need to create more Factories for the JSON values that are on the csv file
		      * 
		      * What you will end with is a clean way to say what it is you have to do
		      * 
		      * Also consider wiping the database of all values before you insert anything
		      * 
		      */
		}
	}



}