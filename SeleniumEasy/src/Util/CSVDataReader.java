package src.Util;

//import com.opencsv.*;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;



public class CSVDataReader {
	
	public static String firstname = null;
	public static String lastname = null;
	public static String phone = null;
	public static String zip = null;
	public static String postalcode = null;
	public static String email = null;
	public static String address = null;
	public static String city = null;
	public static String state = null;
	public static String country = null;
	public static String username = null;
	public static String password = null;	
	public static String confirmpwd = null;
		

	public static void GetUserData(String path) throws IOException {
		
		CSVReader r = new CSVReader(new FileReader(path));
		String[] csvCell;
		
		while((csvCell = r.readNext()) != null) {		
				firstname = csvCell[0];
				lastname = csvCell[1];
				phone = csvCell[2];
				email = csvCell[3];	
				address = csvCell[4];
				city = csvCell[5];
				state = csvCell[6];
				postalcode = csvCell[7];
				country = csvCell[8];
				username = csvCell[9];
				password = csvCell[10];
				confirmpwd = csvCell[11];
			}
			
		}

}
