package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class EngineAlter {
	private String [] titles;
	private String [] titlesType;
	private int counter = 0;
	public ArrayList<ArrayList<String>> addColum (ArrayList<ArrayList<String>> tableData,String[] ArrayOfTypes, String[]headers, String type,
			String columName) {
		int out = 0;
		counter =0;
		if (type.equalsIgnoreCase("int")) {
			out++;
		}else if (type.equalsIgnoreCase("varchar")) {
			out++;
		}else if (type.equalsIgnoreCase("float")) {
			out++;
		}else if (type.equalsIgnoreCase("date")) {
			out++;
		}else {
			out++;
		}
		if (out==0) {
			titles = new String[headers.length];
			titlesType = new String[headers.length];
			for (int i = 0; i < headers.length; i++) {
				titles[i]=headers[i];
				titlesType[i]=ArrayOfTypes[i];
			}
			return tableData;
		}
		
		/*for (int i = 0; i < headers.length; i++) {
			if (type.equals(ArrayOfTypes[i])) {
				out ++;
			}
		}
		if (out==0) {
			return tableData;
		}*/
		
		titles = new String[headers.length+1];
		titlesType = new String[headers.length+1];
		for (int i = 0; i < headers.length; i++) {
			titles[i]=headers[i];
			titlesType[i]=ArrayOfTypes[i];
		}
		titles[headers.length]=columName;
		titlesType[ArrayOfTypes.length]=type;
		tableData.get(0).add(columName);
		for (int i =1; i < tableData.size(); i++) {
			tableData.get(i).add("null");
		}
		counter++;
		return tableData ;
	}
	public ArrayList<ArrayList<String>> deleteColum (ArrayList<ArrayList<String>> tableData,String[] ArrayOfTypes, String[]headers,
			String columName) {
		int out = 0;
		counter =0;
		int location = 0;
		for (int i = 0; i < headers.length; i++) {
			if (columName.equalsIgnoreCase(headers[i])) {
				out++;
				location = i;
			}
		}
		if (out==0) {
			titles = new String[headers.length];
			titlesType = new String[headers.length];
			for (int i = 0; i < headers.length; i++) {
				titles[i]=headers[i];
				titlesType[i]=ArrayOfTypes[i];
			}
			return tableData;
		}
		
		for (int i = 0; i < tableData.size(); i++) {
			tableData.get(i).remove(location);
		}
		titles = new String[headers.length-1];
		titlesType = new String[headers.length-1];
		int add =0;
		for (int i = 0; i < headers.length; i++) {
			if (i!= location) {
				titles[add]=headers[i];
				titlesType[add]=ArrayOfTypes[i];
				System.out.println("yyyy"+titles[add]);
				System.out.println("lllll"+titlesType[add]);
				add++;
			}
			
		}
		System.out.println("whfuowh");
		counter++;
		return tableData ;
	}
	public int getCounter() {
		return counter;
	}
	public String[] getHeaders() {
		return titles;
	}
    public String[] getArrayOfTypes() {
		return titlesType ;
	}
}
