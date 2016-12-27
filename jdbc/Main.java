package eg.edu.alexu.csd.oop.jdbc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.io.InputStreamReader;

import eg.edu.alexu.csd.oop.jdbc.Driver;
import eg.edu.alexu.csd.oop.jdbc.Statement;
import eg.edu.alexu.csd.oop.DBMS.Validate;
import eg.edu.alexu.csd.oop.jdbc.Connection;
import eg.edu.alexu.csd.oop.jdbc.User;
import wagu.Block;
import wagu.Board;
import wagu.Table;
import java.sql.SQLException;
public class Main {
    
	public static void main(String[] args) throws SQLException {
		 Validate validate= new Validate();
		 User user = new User();
		 boolean check= false;
		 String path=System.getProperty("user.home");
			Scanner scan=new Scanner(System.in);
		 while (!check) {
				System.out.println("enter the userName");
				String userName =scan.nextLine();
				System.out.println("enter the password");
				String password =scan.nextLine();
				  check =user.checkPassAndUser(userName, password);
				 if (!check) {
					System.out.println(" invalid userName or password ");
				} 
		}
   
		System.out.println("Enter the url");
		String url=scan.nextLine();
		String [][]table;
		Properties info = new Properties();
		info.put("path",path);
		Driver driver =new Driver();
		Connection connect=driver.connect(url,info);		
		Statement statement= connect.createStatement();
		
		while(true){
			try{
				System.out.println("enter the sql statement");
				String sql=scan.nextLine();
				boolean print=statement.execute(sql);
				
				if(print){
					ResultSet resultset=statement.getResultSet();
					table=resultset.resultSet;
					
					print(table);
				}
				else{
					String number= validate.GetFirstWord(sql);
					if (number.equalsIgnoreCase("use") ||number.equalsIgnoreCase("create") || number.equalsIgnoreCase("drop")||number.equalsIgnoreCase("alter")) {
						
					}else {
						System.out.println(statement.getUpdateCount()+"rows affected");
					}
					
				}
			}
			catch(SQLException e){
				System.out.println("Execution Error.");
			}
	}
	}

	private static void print(String[][] x){
		List<String> headersList = Arrays.asList();
		List<String> header = new ArrayList<>();
		List<List<String>> rows = new ArrayList<>();
		List<List<String>> rowsList = Arrays.asList();
	
		for (int i = 0 ; i < x[0].length ; i++){
			header.add(x[0][i]);
		}

		for(int i =1 ; i<x.length;i++){
			List<String> Sublist = new ArrayList<>();
			for(int j =0 ; j<x[0].length ; j++){
				Sublist.add(x[i][j]);
			}
			rows.add(Sublist);
		}
			rowsList = rows;
		
			if(rowsList.size() >=1 ){
				headersList = header;
				Board board = new Board(75);
								String tableString = board.setInitialBlock(new Table(board, 75, headersList, rowsList).tableToBlocks()).build()
						.getPreview();
				System.out.println(tableString);
				}
				else{
					 System.out.println("No Results found");
				x= null;	 
				}
		
		
	}
	
}
