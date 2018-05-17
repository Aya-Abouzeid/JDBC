package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EngineDistinct {
	 private int counter =0;
	 private String[]selectTypes;
public ArrayList<ArrayList<String>>  distinct( ArrayList<ArrayList<String>> tableData, String[] columsName, String[]headers,String[] ArrayOfTypes) {
	int out = 0;
	counter =0;
	ArrayList<Integer> location =new ArrayList<Integer>() ;
	ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	for (int j = 0; j < columsName.length; j++) {
		out=0;
		for (int i = 0; i < headers.length; i++) {
			if (columsName[j].equalsIgnoreCase(headers[i])) {
				out++;
				location.add(i);
			}
		}

		if (out==0) {
			return tableData;
		}
	}
	Set distinct =new HashSet<String>();
	for (int i = 0; i <tableData.size(); i++) {
			ArrayList<String>newRow =new ArrayList<String>();
			for (int k = 0; k < location.size(); k++) {
				newRow.add(tableData.get(i).get(location.get(k)));
			}
		distinct.add(newRow);
		
	}
	selectTypes= new String[location.size()];
	for (int i = 0; i < location.size(); i++) {
		selectTypes[i]=ArrayOfTypes[location.get(i)];
	}

	ArrayList<ArrayList<String>> reslut = new ArrayList<ArrayList<String>>(distinct);
	
	counter=reslut.size();
	return reslut;
}
public int getCounter() {
	return counter;
}
public String[] getType() {
	return selectTypes;
}
}
