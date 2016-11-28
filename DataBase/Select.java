package eg.edu.alexu.csd.oop.DataBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wagu.Block;
import wagu.Board;
import wagu.Table;

public class Select extends Validate {
	ArrayList<String[]> table = new ArrayList<>();
	int MaxValueLength = -1;

	protected void Select(Boolean IsDBFound, String CurrentUsedDB, String GetRestSentence) {
        differ=false;
		DBfound = IsDBFound;
		GetRest = GetRestSentence;
		CurrentlyUsedDB = CurrentUsedDB;
		if (DBfound) {
			selected_fields1 = new ArrayList<String>();
			String Rest = GetRest;
			if (Rest != null) {
				if (Rest.charAt(0) == '*') {
					decide = true;
					Rest = TrimCommand(Rest.substring(1));
					select_withASt(Rest);
					decide = false;
				} else {
					select_withoutASt(Rest);
				}
			} else
				System.out.println("Invalid Command.");
		} else
			System.out.println("Invalid command. Select a Database first.");
	}

	private void select_withASt(String Rest) {
		boolean s1 = check_from_state(Rest);
		boolean s2 = true;
		boolean withwhere = false;
		if (Rest.contains("where")) {
			withwhere = true;
			s2 = check_where_state(Rest);
		}
		if (s1 == false || s2 == false) {
			System.out.println("Invalid Command.");
		} else {
			if (withwhere) {
				String[][] x = Query.selectAllWithCondition(CurrentlyUsedDB, current_table1, condition);
				if(x != null){
					Print2D(x);

				}
				else{
					System.out.println("Invalid Condition.");

				}

			} else {
				String[][] x = Query.selectAllColumns(CurrentlyUsedDB, current_table1);
				if(x !=null)
					Print2D(x);
					
					else
						System.out.println("Invalid Condition.");


				
			}
		}

	}

	private void FillTableAllWithCondition(String[] x) {
		List<String> headersList = Arrays.asList();
		List<String> header = new ArrayList<>();
		List<List<String>> rows = new ArrayList<>();
		List<List<String>> rowsList = Arrays.asList();
		for (int i = 0; i < x.length; i++) {
			List<String> Sublist = new ArrayList<>();
			if (i == 0 ) {
				x[i] = TrimCommand(x[i]);
				x[i] = Trim_end(x[i]);
				String[] Titles = x[0].split(" ");
			for (int j = 0; j < Titles.length; j = j + 1) {

					header.add(Titles[j]);
				
			}
			} else if (i != 0 && x[i].contains(x[i-1])) {
				x[i] = TrimCommand(x[i]);
				x[i] = Trim_end(x[i]);

				String[] values = x[i].split("    ");
for (int j = 0; j < values.length; j ++) {
	Sublist.add(values[j]);
			}
rows.add(Sublist);
			}
		}
		rowsList = rows;
		if(rowsList.size() >=1 ){
		headersList = header;
		Board board = new Board(75);
		String tableString = board.setInitialBlock(new Table(board, 75, headersList, rowsList).tableToBlocks()).build()
				.getPreview();
		System.out.println(tableString);
		}
		else
			 System.out.println("No Results found");
	}


	private void select_withoutASt(String Rest) {
		if (!Rest.contains("from")) {
			System.out.println("Invalid Command.");
		} else {

			boolean s1 = check_from_state(Rest);
			boolean s2 = true;
			boolean withwhere = false;
			if (Rest.contains("where")) {
				withwhere = true;
				s2 = check_where_state(Rest);
			}
			if (s1 == false || s2 == false) {
				System.out.println("Invalid Command.");
			} else if (s1 == true && s2 == true) {
				check_select();
				if (withwhere) {
					String[][] x = Query.selectColumnsWithCondition(CurrentlyUsedDB, current_table1, selected_fields,
							condition);
					if(x != null){
					Print2D(x);
					}
					else
						System.out.println("Invalid Condition.");


				} else {
					String[][] x = Query.selectColumns(CurrentlyUsedDB, current_table1, selected_fields);
				if(x != null)
					Print2D(x);
				else
					System.out.println("Invalid Condition.");

				}
			}}}

	

		private void Print2D(String[][] x){
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
					else
						 System.out.println("No Results found");
			
		}
	

}
