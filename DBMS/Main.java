package eg.edu.alexu.csd.oop.DBMS;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.sql.Date;
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to DBMS");
//		 Parser p = new Parser();
		 Scanner scan = new Scanner(System.in);
		Runtime rt = Runtime.getRuntime();
//			Runtime.getRuntime().exec("cmd /c start "+System.getProperty("user.home") + File.separator +"RUN2.bat");
String tryy ="023123";
		Float f = Float.parseFloat(tryy);
		System.out.println(f);

		String s = "1992-10-21"; //varchar
		
		//date
//		try{
//		Date x = Date.valueOf(s);
//		System.out.println(x);
//		}
//		catch(Exception e){
//			System.out.println("here");
//		}
//
//		
		while (true) {
			String command = scan.nextLine();
//			p.Parse(command);
	}
	}
}
