package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.jdbc.Log4j;

public class EngineDelete {
	private Condition  test = new Condition();
	private  int counter=0;
	private compare comparing = new compare();
	private Log4j log = new Log4j();
	public ArrayList<ArrayList<String>> deleteTable(ArrayList<ArrayList<String>> tableData,String []headers){
		ArrayList<String>newRow= new ArrayList<>();
		counter= tableData.size()-1;

		for (int i = 0; i < headers.length; i++) {
			newRow.add(headers[i]);
		}
		tableData.add(newRow);
		log.LOG().info(" delete done");
		System.out.println(" delete done");
		return tableData;
	}
	public ArrayList<ArrayList<String>> deleteSubTable(ArrayList<ArrayList<String>> tableData,String[]condition,
			String [] ArrayOfTypes,String []headers){
		counter=0;
		boolean out = false;
		out= test.inspectColumCondition(headers, condition[0],ArrayOfTypes);
		if (!out) {
			counter=0;
			log.LOG().warning(" invalid condition");
			System.out.println(" invalid condition");
			return tableData;
		}
		String columType= test.getType();
		int  testing = test.getlocation();
		 ArrayList<Integer> itemsCounterDeleted = new ArrayList<Integer>();
		out= test.inspectType(columType, condition[2]);
		if (!out) {
			counter=0;
			log.LOG().warning(" invalid condition");
			System.out.println(" invalid condition");
			return tableData;
		}
	  looping(tableData, columType, condition, testing, itemsCounterDeleted);
		int delete=0;
        for(int i=0;i<itemsCounterDeleted.size();i++){
       	tableData.remove(itemsCounterDeleted.get(i)-delete);
       	delete++;}
        counter= itemsCounterDeleted.size();
        log.LOG().info(" delete done");
		System.out.println(" delete done");
		return tableData;
	}
	public void adding(String[] condition, ArrayList<Integer>itemsCounterDeleted,int i,int check) {
		if (condition[1].equals("=")) {

			if (check == 0) {
				itemsCounterDeleted.add(i);
			}
		}else if (condition[1].equals(">")){
			if (check >0) {
				itemsCounterDeleted.add(i);
			}
		}else if (condition[1].equals("<")){
			if (check < 0) {
				itemsCounterDeleted.add(i);
			}
		}
	}
	public int getCounter() {
		return counter;
	}
	public void looping(ArrayList<ArrayList<String>>tableData, String columType ,String[]condition,int testing ,ArrayList<Integer>itemsCounterDeleted) {
		for (int i = 1; i < tableData.size(); i++) {
			if (columType.equalsIgnoreCase("int")) {
				int check= comparing.compareInteger(tableData.get(i).get(testing), condition[2]);
				adding(condition, itemsCounterDeleted, i, check);
			}else if (columType.equalsIgnoreCase("varchar") ){
				int check= comparing.compareString(tableData.get(i).get(testing), condition[2]);
		        adding(condition, itemsCounterDeleted, i, check);
			}else if (columType.equalsIgnoreCase("float") ){
				int check= comparing.compareFloat(tableData.get(i).get(testing), condition[2]);
				adding(condition, itemsCounterDeleted, i, check);
			}else if (columType.equalsIgnoreCase("date") ){
				int check= comparing.compareDate(tableData.get(i).get(testing), condition[2]);
				adding(condition, itemsCounterDeleted, i, check);
			}
			
			
		}
	}
}

