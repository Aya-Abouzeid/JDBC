package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class EngineDistinct {
	 private int counter =0;
public ArrayList<ArrayList<String>>  distinct( ArrayList<ArrayList<String>> tableData, String[] columsName, String[]headers) {
	int out = 0;
	counter =0;
	ArrayList<Integer> location =new ArrayList<Integer>() ;
	ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	for (int j = 0; j < columsName.length; j++) {
		out=0;
		for (int i = 0; i < headers.length; i++) {
			if (columsName[j].equals(headers[i])) {
				out++;
				location.add(i);
			}
		}

		if (out==0) {
			return tableData;
		}
	}
	System.out.println("si"+tableData.size());
	for (int i = 0; i <tableData.size(); i++) {
			ArrayList<String>newRow =new ArrayList<String>();
			for (int k = 0; k < location.size(); k++) {
				newRow.add(tableData.get(i).get(location.get(k)));
				System.out.println("8888888"+tableData.get(i).get(location.get(k)));
			}
		output.add(newRow);
		
	}
	ArrayList<Integer> delete =new ArrayList<Integer>();
	int check=0;
	for (int i = 0; i < output.size(); i++) {
		ArrayList<String>newRow =new ArrayList<String>();
		newRow=output.get(i);
		for (int j = i+1; j <output.size(); j++) {
			check=0;
			for (int j2 = 0; j2 <output.get(0).size(); j2++) {
				System.out.println("size"+output.get(0).size());
				if (newRow.get(j2).equals(output.get(j).get(j2))) {
					System.out.println("now     "+newRow.get(j2));
					System.out.println("compare  "+output.get(i).get(j2));
					check++;
				}
			}
			if (check == output.get(0).size()) {
				delete.add(j);
				System.out.println("j"+j);
			}
			
			
		}
	}
	System.out.println("sgaswgs"+delete.size());
	ArrayList<ArrayList<String>> reslut = new ArrayList<ArrayList<String>>();
	int loop=0;
	for (int i = 0; i < output.size() && loop <delete.size(); i++) {
	
			if (i != delete.get(loop)) {
				reslut.add(output.get(i));
				loop++;
			}
		
	}
	/*for (int i = 0; i <delete.size(); i++) {
		output.remove(delete.get(i));
		System.out.println("rrrrrr"+delete.get(i));
		System.out.println("bdbnnklj"+output.size() );
	}*/
	return reslut;
}
}
