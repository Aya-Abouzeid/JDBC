package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import wagu.Block;
//import wagu.Board;
//import wagu.Table;

public class Select extends Validate {
	boolean distinct=false;
	ArrayList<String[]> table = new ArrayList<>();
	int MaxValueLength = -1;
	boolean Executed;
     public String []Type;
	public String[][] Select(Boolean IsDBFound, String CurrentUsedDB, String GetRestSentence ,Queries query,XmlValidation Detect) {
		this.Query = query;
		this.Detect = Detect;
		String[][] x = null;
        differ=false;
		DBfound = IsDBFound;
		GetRest = GetRestSentence;
		CurrentlyUsedDB = CurrentUsedDB;
		Executed = false;
			selected_fields1 = new ArrayList<String>();
			String Rest = GetRest;
			if (Rest != null) {
				if (Rest.charAt(0) == '*') {
					decide = true;
					Rest = TrimCommand(Rest.substring(1));
					x=select_withASt(Rest);
					decide = false;
				} 
				else if(GetFirstWord(Rest).equalsIgnoreCase("distinct")){
					x=selectDistinct(GetRest);
				}
				
				
				else {
					x= select_withoutASt(Rest);
				}
			} else
				System.out.println("Invalid Command.");

			return x;
	}

	private String[][] selectDistinct(String getRest) {
		String x[][] = null;
		distinct=true;
		x = select_withoutASt(getRest);
		distinct=false;
		return x;
	}

	private String[][] select_withASt(String Rest) {
		String[][] x = null;
		boolean s1 = check_from_state(Rest);
		boolean s2 = true;
		boolean withwhere = false;
		if (Rest.toLowerCase().contains("where")) {
			withwhere = true;
			s2 = check_where_state(Rest);
		}if (s1 == false || s2 == false) {
			System.out.println("Invalid Command.");
		} else {
			if (withwhere) {
				 x = Query.selectAllWithCondition(CurrentlyUsedDB, current_table1, condition);
				 Type=Query.getType();
				 Executed = true;
				if(x != null){
					x = Print2D(x);	}
				else{
					System.out.println("Invalid Condition.");
				}	} else {
				x = Query.selectAllColumns(CurrentlyUsedDB, current_table1);
				 Type=Query.getType();

				 Executed = true;

				if(x !=null)
				x=	Print2D(x);		
					else
						System.out.println("Invalid Condition.");	}
		}	
		return x;
		}
public boolean GetExecuted(){
	return Executed;
}

	private String[][] select_withoutASt(String Rest) {
		String[][] x = null;
		if (!Rest.toLowerCase().contains("from")) {
			System.out.println("Invalid Command.");
		} else {

			boolean s1 = check_from_state(Rest);
			boolean s2 = true;
			boolean withwhere = false;
			if (Rest.toLowerCase().contains("where")) {
				withwhere = true;
				s2 = check_where_state(Rest);
			}
			if (s1 == false || s2 == false) {
				System.out.println("Invalid Command.");
			} else if (s1 == true && s2 == true) {
				check_select();
				if (withwhere) {
					x = Query.selectColumnsWithCondition(CurrentlyUsedDB, current_table1, selected_fields,condition);
					 Type=Query.getType();
					 System.out.println("jjj"+Type);
					 Executed = true;


					if(x != null){
					x=Print2D(x);
					}
					else
						System.out.println("Invalid Condition.");


				} else {
					if(distinct){
						x =Query.distinct(CurrentlyUsedDB, current_table1, selected_fields);
						Type=Query.getType();
						 Executed = true;

					}
					else{
					 x = Query.selectColumns(CurrentlyUsedDB, current_table1, selected_fields);
					 Type=Query.getType();
					 Executed = true;

					}
				if(x != null)
					x= Print2D(x);
				else
					System.out.println("Invalid Condition.");

				}
			}}
	return x;	
	}

	

		private String[][] Print2D(String[][] x){
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
//					headersList = header;
//					Board board = new Board(75);
//									String tableString = board.setInitialBlock(new Table(board, 75, headersList, rowsList).tableToBlocks()).build()
//							.getPreview();
//					System.out.println(tableString);
					}
					else{
						 System.out.println("No Results found");
					x= null;	 
					}
			
				return x;
		}
	

}
