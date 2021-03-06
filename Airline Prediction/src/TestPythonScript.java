import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import codes.Database;
import codes.StaticKeys;

public class TestPythonScript {
	static Connection con = Database.getDBConnection();
	/*public static void main(String[] args){
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		try {
			String travelDate="2017-12-28";
			System.out.println(travelDate);
			Date newDate,date = new Date();
			newDate = new SimpleDateFormat("yyyy-MM-dd").parse(travelDate);
			System.out.println(dateFormat.format(date));
			System.out.println(dateFormat.format(newDate));
			long diff=Math.abs((newDate.getTime()-date.getTime())/86400000);
			System.out.println(diff);
			String date1=dateFormat.format(newDate);
			//System.out.println(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //newDate=dateFormat.format(newDate);
		
	}*/
	
	/*public static void main(String[] args){
		String travelDate="2017-12-28";
		
		Date newDate;
		newDate = new SimpleDateFormat("yyyy-dd-MM").parse(travelDate);
		String date1=dateFormat.format(newDate);
		System.out.println(date1)
		//sendData("BOM", "DEL", "12/28/2017");
	}*/
	
	/*public static void main(String[] args) throws IOException{
		/*File folder = new File("src");
		File[] listOfFiles = folder.listFiles();
		System.out.println("File List "+ Arrays.deepToString(listOfFiles));
		ArrayList<String> records = new ArrayList<String>();
		BufferedReader reader=new BufferedReader(new FileReader("src/expedia.py"));
		String ln="";
		while((ln=reader.readLine())!= null){
			records.add(ln);
		}
		System.out.println("records "+ records);*/
		//sendData("BOM","DEL","01/28/2018");
	//}
public static void sendDataSet(String source, String Dest, String travelDate) {
	try {
		System.out.println(travelDate);
		
	DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
	Date newDate,date = new Date();
	newDate = new SimpleDateFormat("MM/dd/yyyy").parse(travelDate);
	System.out.println(dateFormat.format(date));
	System.out.println(dateFormat.format(newDate));
	long diff=Math.abs((newDate.getTime()-date.getTime())/86400000);
	String diff1=new Long(diff).toString();
	System.out.println(diff);
		//System.out.println("source :"+source+" Destination : "+Dest+" Date : "+travelDate);
		
		
			String sql="Delete from flightDataset";
			PreparedStatement ps=con.prepareStatement(sql);
			int res=ps.executeUpdate();
				String filePath="H:\\1232\\Airline Prediction\\src\\codes\\expediaFetchData.py";
			String[] cmd={
					"python",
					filePath,
					source,
					Dest,
					travelDate,
					diff1
					
			};
			Runtime rt= Runtime.getRuntime();
			Process pr=rt.exec(cmd);
			BufferedReader bre = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line="";
			while((line=bre.readLine())!= null){
				System.out.println(line);
			}
			
			while((line=bfr.readLine())!= null){
				System.out.println(line);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void sendData(String source, String Dest, String travelDate) {
		
		//System.out.println("source :"+source+" Destination : "+Dest+" Date : "+travelDate);
		
		try {
			String sql="Delete from flightDetails";
			PreparedStatement ps=con.prepareStatement(sql);
			int res=ps.executeUpdate();
				String filePath="H:\\1232\\Airline Prediction\\src\\codes\\expedia.py";
			String[] cmd={
					"python",
					filePath,
					source,
					Dest,
					travelDate
			};
			Runtime rt= Runtime.getRuntime();
			Process pr=rt.exec(cmd);
			BufferedReader bre = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line="";
			while((line=bre.readLine())!= null){
				System.out.println(line);
			}
			
			while((line=bfr.readLine())!= null){
				System.out.println(line);
				if(line.equals("Data Inserted")){
					StaticKeys.dataInsertFlag="flagSet";
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
