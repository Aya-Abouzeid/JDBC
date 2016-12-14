package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class EngineUpdate {
	private Condition  test = new Condition();
	private  int counter=0;
	private compare comparing = new compare();
	public ArrayList<ArrayList<String>> update(ArrayList<ArrayList<String>> tableData,String[] condition,
			String[]updateStatment,String[] ArrayOfTypes,String[]headers){
		counter=0;
		boolean out = false;
		 ArrayList<Integer> itemsCounterUpdate= new ArrayList<Integer>();
		 ArrayList<String> update= new ArrayList<String>();
		out= test.inspectColumCondition(headers, condition[0],ArrayOfTypes);
		if (!out) {
			counter=-1;
			return tableData;
		}
		if (tableData.size()== 1) {
			counter=0;
			return tableData;
		}
		String columType= test.getType();
		int  testing = test.getlocation();
		out= test.inspectType(columType, condition[2]);
		
		if (!out) {
			counter=-1;
			return tableData;
		}
		for (int i = 0; i < updateStatment.length; i=i+3) {
			out= test.inspectColumCondition(headers, updateStatment[i],ArrayOfTypes);
			if (!updateStatment[i+1].equals("=")) {
				counter=-1;
				return tableData;
			}
			if (!out) {
				counter=-1;
				return tableData;
			}
			out= test.inspectType(test.getType(), updateStatment[i+2]);
			if (!out) {
				counter=-1;
				return tableData;
			}
			itemsCounterUpdate.add(test.getlocation());
			update.add(updateStatment[i+2]);
		}
		out= test.inspectType(columType, condition[2]);
		if (!out) {
			counter=-1;
			return tableData;
		}
		for (int i = 1; i < tableData.size(); i++) {
			if (columType.equalsIgnoreCase("int")) {
				int check= comparing.compareInteger(tableData.get(i).get(testing), condition[2]);
				adding(condition, tableData, i, check,update,itemsCounterUpdate);
			}else if (columType.equalsIgnoreCase("varchar") ){
				int check= comparing.compareString(tableData.get(i).get(testing), condition[2]);
		        adding(condition, tableData, i, check,update,itemsCounterUpdate);
			}else if (columType.equalsIgnoreCase("float") ){
				int check= comparing.compareFloat(tableData.get(i).get(testing), condition[2]);
				adding(condition, tableData, i, check,update,itemsCounterUpdate);
			}else if (columType.equalsIgnoreCase("date") ){
		                                              ////                                             datttttttttttttttttttttttttttttttttttte
			}
		}
		
		return tableData;
	}
	private void adding(String[] condition,ArrayList<ArrayList<String>> tableData,int i,int check
			,ArrayList<String>update,ArrayList<Integer>itemsCounterUpdate ) {
		if (condition[1].equals("=")) {
			if (check == 0) {
				for (int j = 0; j < update.size(); j++) {
					tableData.get(i).set(itemsCounterUpdate.get(j), update.get(j));
				}
				counter++;
			}
		}else if (condition[1].equals(">")){
			if (check >0) {
				for (int j = 0; j < update.size(); j++) {
					tableData.get(i).set(itemsCounterUpdate.get(j), update.get(j));
				}
				counter++;
			}
		}else if (condition[1].equals("<")){
			if (check < 0) {
				for (int j = 0; j < update.size(); j++) {
					tableData.get(i).set(itemsCounterUpdate.get(j), update.get(j));
				}
				counter++;
			}
		}
	}
	public ArrayList<ArrayList<String>> updateWhitoutWhere (ArrayList<ArrayList<String>> tableData,
			String[]updateStatment,String[] ArrayOfTypes,String[]headers){
	  counter=0;
	  boolean out = false;
	  ArrayList<Integer> itemsCounterUpdate= new ArrayList<Integer>();
	  ArrayList<String> update= new ArrayList<String>();
	  if (tableData.size()== 1) {
			counter=0;
			return tableData;
		}
		for (int i = 0; i < updateStatment.length; i=i+3) {
			out= test.inspectColumCondition(headers, updateStatment[i],ArrayOfTypes);
			if (!updateStatment[i+1].equals("=")) {
				counter=-1;
				return tableData;
			}
			if (!out) {
				counter=-1;
				return tableData;
			}
			out= test.inspectType(test.getType(), updateStatment[i+2]);
			if (!out) {
				counter=-1;
				return tableData;
			}
			itemsCounterUpdate.add(test.getlocation());
			update.add(updateStatment[i+2]);
		}
		for (int i = 1; i < tableData.size(); i++) {
			for (int j = 0; j < update.size(); j++) {
				tableData.get(i).set(itemsCounterUpdate.get(j), update.get(j));
			}
			counter++;
		}
		
		return tableData;
		}
		public int getCounter() {
			return counter;
		}
}
