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
		for (int j = 0; j < headers.length; j++) {
			if (type.equalsIgnoreCase(headers[j])) {
				System.out.println("invalid colum name");
				titles = new String[headers.length];
				titlesType = new String[headers.length];
				for (int i = 0; i < headers.length; i++) {
					titles[i]=headers[i];
					titlesType[i]=ArrayOfTypes[i];}
				return tableData;
			}
		}
	    out = getOut(out, type);
		if (out==0) {
			titles = new String[headers.length];
			titlesType = new String[headers.length];
			for (int i = 0; i < headers.length; i++) {
				titles[i]=headers[i];
				titlesType[i]=ArrayOfTypes[i];}
			return tableData;
		}

		titles = new String[headers.length+1];
		titlesType = new String[headers.length+1];
		for (int i = 0; i < headers.length; i++) {
			titles[i]=headers[i];
			titlesType[i]=ArrayOfTypes[i];}
		titles[headers.length]=columName;
		titlesType[ArrayOfTypes.length]=type;
		tableData.get(0).add(columName);
		for (int i =1; i < tableData.size(); i++){tableData.get(i).add("null");}
		counter++;
		return tableData ;
	}
	public int getOut( int out , String type) {
		int out2= out;
		if (type.equalsIgnoreCase("int")) {
			out2++;
		}else if (type.equalsIgnoreCase("varchar")) {
			out2++;
		}else if (type.equalsIgnoreCase("float")) {
			out2++;
		}else if (type.equalsIgnoreCase("date")) {
			out2++;
		}else {
			out2++;
		}
		return out2;
	}
	public ArrayList<ArrayList<String>> deleteColum (ArrayList<ArrayList<String>> tableData,String[] ArrayOfTypes, String[]headers,
			String columName) {
		int out = 0, location = 0;
		counter =0;
		for (int i = 0; i < headers.length; i++) {
			if (columName.equalsIgnoreCase(headers[i])) {out++;location = i;}
		}
		if (out==0) {
			titles = new String[headers.length];
			titlesType = new String[headers.length];
			for (int i = 0; i < headers.length; i++) {
				titles[i]=headers[i];
				titlesType[i]=ArrayOfTypes[i];}
			return tableData;
		}
		for (int i = 0; i < tableData.size(); i++) {tableData.get(i).remove(location);}
		titles = new String[headers.length-1];
		titlesType = new String[headers.length-1];
		int add =0;
		for (int i = 0; i < headers.length; i++) {
			if (i!= location) {
				titles[add]=headers[i];
				titlesType[add]=ArrayOfTypes[i];
				add++;
			}
		}
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
