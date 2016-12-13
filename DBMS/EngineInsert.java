package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class EngineInsert {
	private int counter =0;
	private Titles titlesObject = new Titles();
	private Condition  test = new Condition();
	public ArrayList<ArrayList<String>>  insertRow(ArrayList<ArrayList<String>> tableData, String[] properties,
			String[] ArrayOfTypes, String[]headers){
		counter=0;
		if(headers.length!= properties.length ){
			System.out.println(" invalid insertion");
			return tableData ;
			}
		boolean out = false;
		for (int i = 0; i < headers.length; i++) {
			out =test.inspectType(ArrayOfTypes[i], properties[i]);
			if(!out){return tableData;}
			
		}
		ArrayList<String> newRow = new ArrayList<String>();
		for (int i = 0; i < properties.length; i++) {
		newRow.add(properties[i]);	
		}
		if (!newRow.isEmpty()) {
			tableData.add(newRow);
		}
		
		counter=1;
		return tableData;
	}
	public ArrayList<ArrayList<String>> insertSub(ArrayList<ArrayList<String>> tableData, String[] columSend, String[] properties,
			String[] ArrayOfTypes, String[]headers){
		
		counter=0;
		boolean out= false;
		boolean write =true;
		if(columSend.length!= properties.length ){

			System.out.println(" invalid insertion");
			return tableData;
			}
		
		for (int i = 0; i < columSend.length; i++) {
			out= test.inspectColum(headers, columSend[i]);
			if (!out) {

				return tableData;
			}
		}
		ArrayList<String> newRow = new ArrayList<String>();
		for (int i = 0; i < headers.length; i++) {
			for (int j = 0; j < columSend.length; j++) {
				if (columSend[j].equalsIgnoreCase(headers[i])) {

					 write=false;
					out= test.inspectType(ArrayOfTypes[i], properties[j]);
					if(!out){return tableData;}
					newRow.add(properties[j]);

				}
			}
			if(write){
				newRow.add("null");
				}
			write=true;
		}
		if (!newRow.isEmpty()) {
			tableData.add(newRow);
		}

		 counter=1;

		return tableData;
	}

	public int getCounter(){

		return counter;
	}
	

}
