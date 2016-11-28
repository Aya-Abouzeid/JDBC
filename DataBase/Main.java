package eg.edu.alexu.csd.oop.DataBase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Parser p = new Parser();
		 Scanner scan = new Scanner(System.in);
		Runtime rt = Runtime.getRuntime();
//		try {
//			Runtime.getRuntime().exec("cmd /c start "+System.getProperty("user.home") + File.separator +"RUN2.bat");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		while (true) {
			String command = scan.nextLine();
			p.ChangeToLowerCase(command);
	}
	}
}
