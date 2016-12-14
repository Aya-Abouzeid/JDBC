package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class EngineSelect {
	private Titles titlesObject = new Titles();
	private Condition  test = new Condition();
	private int counter=0;
	private compare comparing = new compare();
	private String[]selectTypes;
	public ArrayList<ArrayList<String>> selectAllColumns(ArrayList<ArrayList<String>> tableData, String[]ArrayOfTypes){
     selectTypes= new String[ArrayOfTypes.length];
     for (int i = 0; i < ArrayOfTypes.length; i++) {
		selectTypes[i]=ArrayOfTypes[i];
	}
		return tableData;	
	}
	public String[][] selectColumnsWithCondition(ArrayList<ArrayList<String>> tableData,String[]condition,
			String[]columntitles,String[] ArrayOfTypes, String[]headers){
		ArrayList<Integer> itemsCounter= new ArrayList<Integer>();
		//ArrayList<Integer> index= new ArrayList<Integer>();
		ArrayList<String> newRow;
		boolean out = false;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < columntitles.length; i++) {
			out= test.inspectColum(headers, columntitles[i]);
			if (!out) {
				return null;
			}
			itemsCounter.add(test.getlocation());
		}
		out= test.inspectColumCondition(headers, condition[0],ArrayOfTypes);
		if (!out) {
			counter=0;
			return null;
		}
		String columType= test.getType();
		int  testing = test.getlocation();
		out= test.inspectType(columType, condition[2]);
		if (!out) {
			counter=0;
			return null;
		}
		newRow= new ArrayList<>();
		for (int i = 0; i <itemsCounter.size(); i++) {
			newRow.add(tableData.get(0).get(itemsCounter.get(i)));
		}
		output.add(newRow);
		for (int i = 1; i < tableData.size(); i++) {
			newRow= new ArrayList<>();
			if (columType.equalsIgnoreCase("int")) {
				int check= comparing.compareInteger(tableData.get(i).get(testing), condition[2]);
				adding(condition, tableData, i, check,itemsCounter,newRow,output);
				
			}else if (columType.equalsIgnoreCase("varchar") ){
				int check= comparing.compareString(tableData.get(i).get(testing), condition[2]);
		        adding(condition, tableData, i, check,itemsCounter,newRow,output);
		        
			}else if (columType.equalsIgnoreCase("float") ){
				int check= comparing.compareFloat(tableData.get(i).get(testing), condition[2]);
				adding(condition, tableData, i, check,itemsCounter,newRow,output);
				
			}else if (columType.equalsIgnoreCase("date") ){
				int check= comparing.compareDate(tableData.get(i).get(testing), condition[2]);
				adding(condition, tableData, i, check,itemsCounter,newRow,output);                                                                              
			}
		}
		selectTypes= new String[itemsCounter.size()];
		for (int i = 0; i < itemsCounter.size(); i++) {
			selectTypes[i]=ArrayOfTypes[itemsCounter.get(i)];
		}
		String[][]outputTable = new String[(output.size())][output.get(0).size()];
		for (int i = 0; i < output.size(); i++) {
			for (int j = 0; j < output.get(0).size(); j++) {
				outputTable[i][j] = output.get(i).get(j);
			}
		}
		return outputTable;
	}
	public String[][]selectColumns(ArrayList<ArrayList<String>> tableData,String[]columntitles,String[] ArrayOfTypes, String[]headers){
		ArrayList<Integer> itemsCounterUpdate= new ArrayList<Integer>();
		ArrayList<String> newRow;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < columntitles.length; i++) {
			boolean out = false;
			out= test.inspectColum(headers, columntitles[i]);
			if (!out) {
				return null;
			}
			itemsCounterUpdate.add(test.getlocation());
		}
		for (int i = 0; i < tableData.size(); i++) {
			newRow= new ArrayList<String>();
			for (int j = 0; j <itemsCounterUpdate.size(); j++) {
				newRow.add(tableData.get(i).get(itemsCounterUpdate.get(j)));
			}
			if(!newRow.isEmpty() ){
				output.add(newRow);
			}
		}
		selectTypes= new String[itemsCounterUpdate.size()];
		for (int i = 0; i < itemsCounterUpdate.size(); i++) {
			selectTypes[i]=ArrayOfTypes[itemsCounterUpdate.get(i)];
		}
		String[][]outputTable = new String[(output.size())][output.get(0).size()];
		for (int i = 0; i < output.size(); i++) {
			for (int j = 0; j < output.get(0).size(); j++) {
				outputTable[i][j] = output.get(i).get(j);
			}
		}
		return outputTable;
	}
	
	public String[][]selectAllWithCondition(ArrayList<ArrayList<String>> tableData,String[]condition,String[] ArrayOfTypes, String[]headers){
		return selectColumnsWithCondition(tableData, condition, headers, ArrayOfTypes, headers);
	}
	private void adding(String[] condition,ArrayList<ArrayList<String>> tableData,int i,int check
			,ArrayList<Integer>itemsCounter,ArrayList<String>newRow,ArrayList<ArrayList<String>> output ) {
		if (condition[1].equals("=")) {
			if (check == 0) {
				for (int j = 0; j < itemsCounter.size(); j++) {
					newRow.add(tableData.get(i).get(itemsCounter.get(j)));
					//tableData.get(i).set(itemsCounterUpdate.get(j), update.get(j));
				}
				output.add(newRow);
				counter++;
			}
		}else if (condition[1].equals(">")){
			if (check >0) {
				for (int j = 0; j < itemsCounter.size(); j++) {
					newRow.add(tableData.get(i).get(itemsCounter.get(j)));
					//tableData.get(i).set(itemsCounterUpdate.get(j), update.get(j));
				}
				output.add(newRow);
				counter++;
			}
		}else if (condition[1].equals("<")){
			if (check < 0) {
				for (int j = 0; j < itemsCounter.size(); j++) {
					newRow.add(tableData.get(i).get(itemsCounter.get(j)));
					//tableData.get(i).set(itemsCounterUpdate.get(j), update.get(j));
				}
				output.add(newRow);
				counter++;
			}
		}
	}
	public String[] getType() {
		return selectTypes;
	}

}
