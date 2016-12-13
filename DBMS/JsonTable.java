package eg.edu.alexu.csd.oop.DBMS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JsonTable implements ITable{
	 protected String[] ArrayOfTypes;
	 protected String[] headers;
	 private Titles titles= new Titles();
	  private DtdFile dtdObject;
		 private xml xmlObject; 

	 private String path;
	  public  JsonTable(String path) {
		this.path = path;
		this.dtdObject = new DtdFile(path);
		this.xmlObject = new xml(path);
	}
	 private EngineDelete  deleteObject = new EngineDelete();
	 private EngineInsert  insetObject = new EngineInsert();
	 private EngineSelect  SelectObject =new EngineSelect();
	 private EngineUpdate  UpdatObject = new EngineUpdate();
	 private EngineAlter   alterObject = new EngineAlter();
	 private EngineDistinct distinctObject = new EngineDistinct();
	 private ArrayList<ArrayList<String>>  working ;
	 ArrayList<ArrayList<String>> tableData;	

	@Override
	public void creatTable(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub
		System.out.println("jsontable"+path);

		File jsonFile  = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		try {
			if (jsonFile.createNewFile()){
		        System.out.println("File is created!");
		      }else
		        System.out.println("File already exists.");
			ArrayOfTypes = new String[properties.length];
			BufferedWriter file = new BufferedWriter(new FileWriter(jsonFile));
            file.write("{");
            file.newLine();
            file.write(" \""+tableName+"\""+":{");
            file.newLine();
			file.write("\t"+"\""+"numberOfRows"+"\""+":"+"\""+"0"+"\""+",");
            file.newLine();
			file.write("\t"+"\""+tableName+"\""+":{");
            file.newLine();
            for(int i = 0; i < properties.length; i++){
            	String[] str = properties[i].split(" ");
    			file.write("\t\t"+"{\""+str[0]+"\""+":"+"\""+str[1]+"\"}"+",");
    			ArrayOfTypes[i] = str[0];
    			file.newLine();
            }
			file.write("\t\t};");
			file.newLine();
			file.write("\t}");
			file.newLine();
			file.write("}");
	        file.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void dropTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		
	}
	public String [] headers (ArrayList<ArrayList<String>> table){
		for(int x = 0; x<table.get(0).size(); x++){
			headers[x] = table.get(0).get(x);
		}
		return headers;	
	}
	@Override
	public ArrayList<ArrayList<String>> readFile(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
		File tables = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		ArrayOfTypes = column(databaseName , tableName);
		try(BufferedReader file = new  BufferedReader(new FileReader(tables))) {
			String currentLine;
			ArrayList<String> lines = new ArrayList<String>();
			ArrayList<String> trim = new ArrayList<String>();
			ArrayList<ArrayList<String>> tablelines = new ArrayList<ArrayList<String>>();

			while ((currentLine = file.readLine()) != null) {
				currentLine=currentLine.replace("}","");
				currentLine=currentLine.replace(";","");
				currentLine=currentLine.replace("{","");
				currentLine=currentLine.replace(",","");
				currentLine=currentLine.replace("\""+tableName+"\""+":","");
				currentLine=currentLine.trim();
				lines.add(currentLine);
				}
			lines.remove(0);
            for(int i = 0; i < lines.size(); i++){
				if(lines.get(i).isEmpty()){}
				else {trim.add(lines.get(i));}
				}
            trim.remove(0); //to delete numberOfRows from array trim.
            
            for(int e = 0; e < trim.size(); e++){
				trim.set(e,trim.get(e).replace("\"", ""));
            }
            int y = 0;
            ArrayList<String> Row;
            for(int i = y; y < trim.size(); i++){
				 Row = new ArrayList<String>();
					for(int g = 0 ; g < ArrayOfTypes.length; g++ ){
						Row.add(trim.get(y));
			            y++;
					}
					tablelines.add(Row);
				}
			for(int j = 0;j < tablelines.size() ;j++){
				ArrayList<String> R = new ArrayList<String>();
				for(int h = 0;h < ArrayOfTypes.length ;h++){
					String[] str = tablelines.get(j).get(h).split(":");
					R.add(str[1]);
				}
				tableData.add(R);
			}
			headers = new String[tableData.get(0).size()];
			headers = headers(tableData);
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tableData;
	}
	
	public String []  column(String databaseName , String tableName){
		File tables = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		ArrayList<String> columnTitle = new ArrayList<String>();
		ArrayList<String> trim = new ArrayList<String>();
		ArrayList<String> t = new ArrayList<String>();
		InputStream input;
		try {
			input = new FileInputStream(tables);
			BufferedReader file = new  BufferedReader(new InputStreamReader(input, "UTF-8"));
			String currentLine;

			while ((currentLine = file.readLine()) != null) {
				currentLine=currentLine.replace("}","");
				currentLine=currentLine.replace(";","");
				currentLine=currentLine.replace("{","");
				currentLine=currentLine.replace(",","");
				currentLine=currentLine.replace("\""+tableName+"\""+":","");
				currentLine=currentLine.trim();
				columnTitle.add(currentLine);
				}
			columnTitle.remove(0);
			for(int i = 0; i < columnTitle.size(); i++){
				if(columnTitle.get(i).isEmpty()){}
				else {trim.add(columnTitle.get(i));}
				}
            trim.remove(0); //to delete numberOfRows from array trim.
            for(int i = 0; i < trim.size(); i++){
				if(trim.get(i).contains("int")||trim.get(i).contains("float")||trim.get(i).contains("date")||trim.get(i).contains("varchar"))
				{
					String[] typestable = trim.get(i).split(":");
					typestable[0] = typestable[0].replace("\"", "");
					t.add(typestable[0]);
				}
            }
            //ArrayOfTypes = new String[t.size()];
    		for(int i = 0;i < t.size();i++){
    			ArrayOfTypes[i]=t.get(i);
    		}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return ArrayOfTypes;
	}

	@Override
	public void writeFile(String databaseName, String tableName, ArrayList<ArrayList<String>> tableValues) {
		// TODO Auto-generated method stub
		File jsonFile  = new File(path+ File.separator + databaseName+File.separator+tableName+".json");
		ArrayOfTypes = column(databaseName , tableName);
		jsonFile.delete();
		try {
			ArrayList<String> firstRow = tableValues.get(0);
			jsonFile.createNewFile();
			BufferedWriter file = new BufferedWriter(new FileWriter(jsonFile));
            file.write("{");
            file.newLine();
            file.write(" \""+tableName+"\""+":{");
            file.newLine();
			file.write("\t"+"\""+"numberOfRows"+"\""+":"+" \""+Integer.toString(tableValues.size()-1)+"\""+",");
            file.newLine();
            file.write("\t"+"\""+tableName+"\""+":{");
            file.newLine();
            for(int j = 0; j < firstRow.size(); j++){
            	//System.out.println(ArrayOfTypes.length);
    			file.write("\t\t"+"{\""+ArrayOfTypes[j]+"\""+":"+"\""+firstRow.get(j)+"\""+"}"+",");
    			file.newLine();}
            file.write("\t\t};");
			file.newLine(); 
            for(int i = 1;i<tableValues.size();i++){
            	file.write("\t"+"\""+tableName+"\""+":{");
                file.newLine();
                for(int j = 0;j < tableValues.get(i).size() ;j++){
        			file.write("\t\t"+"{"+"\""+tableValues.get(0).get(j)+"\""+":"+"\""+tableValues.get(i).get(j)+"\""+"},");
        			file.newLine();}
                file.write("\t\t};");
    			file.newLine();}
            file.write("\t}");
			file.newLine();
			file.write("}");
	        file.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public int insertRow(String databaseName, String tableName, String[] properties) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData = insetObject.insertRow(working, properties, ArrayOfTypes,headers);
         writeFile(databaseName, tableName,tableData);
		return insetObject.getCounter();
	}

	@Override
	public int insertSub(String databaseName, String tableName, String[] columSend, String[] properties) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=insetObject.insertSub(working,columSend, properties, ArrayOfTypes,headers);
		/*for (int i = 0; i < tableData.size(); i++) {
			for (int j = 0; j < tableData.get(0).size(); j++) {
				System.out.println(tableData.get(i).get(j));
			}
		}*/
        writeFile(databaseName, tableName,tableData);
		
		return insetObject.getCounter();
	}
	@Override
	public int deleteTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData= deleteObject.deleteTable(tableData,headers);
		 writeFile(databaseName, tableName,tableData);
		return 0;
	}
	@Override
	public int deleteSubTable(String databaseName, String tableName, String[] condition) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData=  deleteObject.deleteSubTable(working,condition, ArrayOfTypes,headers);
		writeFile(databaseName, tableName,tableData);
		return deleteObject.getCounter();
	}
	@Override
	public int update(String databaseName, String tableName, String[] condition, String[] updateStatment) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData=  UpdatObject.update(working,condition,updateStatment, ArrayOfTypes,headers);
		writeFile(databaseName, tableName,tableData);
		return UpdatObject.getCounter();
	}
	@Override
	public int updateWhitoutWhere(String databaseName, String tableName, String[] updateStatment) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData=  UpdatObject.updateWhitoutWhere(working,updateStatment, ArrayOfTypes,headers);
		writeFile(databaseName, tableName,tableData);
		return UpdatObject.getCounter();
	}
	@Override
	public String[][] selectColumnsWithCondition(String databaseName, String tableName, String[] columntitles,
			String[] Condition) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = SelectObject.selectColumnsWithCondition(tableData,Condition,columntitles,ArrayOfTypes,headers);
		return outputTable;
	}
	@Override
	public String[][] selectColumns(String databaseName, String tableName, String[] columntitles) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = SelectObject.selectColumns(tableData,columntitles,ArrayOfTypes,headers);
		return outputTable;
	}
	@Override
	public String[][] selectAllColumns(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		writeFile(databaseName, tableName, SelectObject.selectAllColumns(working)); ///////////// return array
		String[][]outputTable = new String[(working.size())][working.get(0).size()];
		for (int i = 0; i < working.size(); i++) {
			for (int j = 0; j < working.get(0).size(); j++) {
				outputTable[i][j] = working.get(i).get(j);
			}
		}
		return outputTable;
	}
	@Override
	public String[][] selectAllWithCondition(String databaseName, String tableName, String[] Condition) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = SelectObject.selectAllWithCondition(tableData,Condition,ArrayOfTypes,headers);
		return outputTable;
	}
	@Override
	public int addAlter(String databaseName, String tableName, String type, String columName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=alterObject.addColum(working, ArrayOfTypes,headers,type,columName);
		headers = new String[alterObject.getHeaders().length];
		headers = alterObject.getHeaders();
		ArrayOfTypes = new String[alterObject.getArrayOfTypes().length];
		ArrayOfTypes = alterObject.getArrayOfTypes();
         writeFile(databaseName, tableName,tableData);	
		return alterObject.getCounter();
	}
	@Override
	public int deleteAlter(String databaseName, String tableName, String columName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=alterObject.deleteColum(working, ArrayOfTypes,headers,columName);
		headers=alterObject.getHeaders();
		ArrayOfTypes =alterObject.getArrayOfTypes();
         writeFile(databaseName, tableName,tableData);	
		return alterObject.getCounter();
	}
	@Override
	public String[][] distinct(String databaseName, String tableName, String[] columsName) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData = working;
		working= distinctObject.distinct(tableData, columsName,headers);
		writeFile(databaseName, tableName, tableData); ///////////// return array
		String[][]outputTable = new String[(working.size())][working.get(0).size()];
		for (int i = 0; i < working.size(); i++) {
			for (int j = 0; j < working.get(0).size(); j++) {
				outputTable[i][j] = working.get(i).get(j);
			}
		}
		return outputTable;
	}
}
