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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
 
public class JsonTable implements ITable{
	 protected String[] ArrayOfTypes;
	 public String[]Type;
	 protected String[] headers;
	 private Titles titles= new Titles();
	 private xml xmlObject; 
	 private String path;
	 private EngineDelete  deleteObject = new EngineDelete();
	 private EngineInsert  insetObject = new EngineInsert();
	 private EngineSelect  SelectObject =new EngineSelect();
	 private EngineUpdate  UpdatObject = new EngineUpdate();
	 private EngineAlter   alterObject = new EngineAlter();
	 private EngineDistinct distinctObject = new EngineDistinct();
	 private ArrayList<ArrayList<String>>  working ;
	 ArrayList<ArrayList<String>> tableData;	
	 BufferedWriter file ;
	 InputStream input;
	 BufferedReader read;
	public JsonTable(String path){
		 this.path = path; 
		 xmlObject = new xml(path); 
	 }
	public String[] getType(){
		  return this.Type;
	  }
	
	@Override
    public void creatTable(String databaseName, String tableName, String[] properties) {
		System.out.println("jsonpath"+path);
		File jsonFile  = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		try {
			
				
			
			if (jsonFile.createNewFile()){System.out.println("File is created!");
			}else System.out.println("File already exists.");
		    ArrayOfTypes = new String[properties.length];
		    write( jsonFile ,tableName);
		    for(int i = 0; i < properties.length; i++){
		    	String[] str = properties[i].split(" ");
		    	file.write("\t\t"+"{\""+str[0]+"\""+":"+"\""+str[1]+"\"}"+",");
		    	ArrayOfTypes[i] = str[0];
		    	file.newLine();}
		    file.write("\t\t};");
			file.newLine();
			file.write("\t}");
			file.newLine();
			file.write("}");
			file.close();
		 }catch (IOException e) {e.printStackTrace();}
	}
 
	private void write(File jsonFile ,String tableName){
		try {
			file = new BufferedWriter(new FileWriter(jsonFile));
			  file.write("{");
		        file.newLine();
		        file.write(" \""+tableName+"\""+":{");
		        file.newLine();
				file.write("\t"+"\""+"numberOfRows"+"\""+":"+"\""+"0"+"\""+",");
		        file.newLine();
				file.write("\t"+"\""+tableName+"\""+":{");
		        file.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	@Override
	public void dropTable(String databaseName, String tableName) throws SQLException {
		// TODO Auto-generated method stub
		File table = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		if (xmlObject.DetectDataBase(databaseName)&& table.exists()) {
			table.delete();
		}else{
			System.out.println("Invalid command.");
			throw new SQLException();
		}
	}
 
	private String [] headers (ArrayList<ArrayList<String>> table){
		for(int x = 0; x<table.get(0).size(); x++){
			headers[x] = table.get(0).get(x);
		}
		return headers;	
	}
 
	@Override
	public ArrayList<ArrayList<String>> readFile(String databaseName, String tableName) {
		ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
		File tables = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		//ArrayOfTypes = column(databaseName , tableName);
		ArrayList<String> trim = new ArrayList<String>();
		ArrayList<ArrayList<String>> tablelines = new ArrayList<ArrayList<String>>();
		try{trim = readline(tables,tableName);
            for(int e = 0; e < trim.size(); e++){
				trim.set(e,trim.get(e).replace("\"", ""));}
            int y = 0;
            for(int i = y; y < trim.size(); i++){
                ArrayList<String> Row = new ArrayList<String>();
					for(int g = 0 ; g < ArrayOfTypes.length; g++ ){
						Row.add(trim.get(y));
			            y++;}
					tablelines.add(Row);}
			for(int j = 0;j < tablelines.size() ;j++){
				ArrayList<String> R = new ArrayList<String>();
				for(int h = 0;h < ArrayOfTypes.length ;h++){
					String[] str = tablelines.get(j).get(h).split(":");
					R.add(str[1]);}
				tableData.add(R);}
			headers = new String[tableData.get(0).size()];
			headers = headers(tableData);
			read.close();
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		return tableData;
	}
	private ArrayList<String> readline(File tables , String tableName){
		ArrayList<String> columnTitle = new ArrayList<String>();
		ArrayList<String> trim = new ArrayList<String>();
		try {input = new FileInputStream(tables);
			read = new  BufferedReader(new InputStreamReader(input, "UTF-8"));
			String currentLine;
			while ((currentLine = read.readLine()) != null) {
				currentLine=currentLine.replace("}","");
				currentLine=currentLine.replace(";","");
				currentLine=currentLine.replace("{","");
				currentLine=currentLine.replace(",","");
				currentLine=currentLine.replace("\""+tableName+"\""+":","");
				currentLine=currentLine.trim();
				columnTitle.add(currentLine);}
			columnTitle.remove(0);
			  for(int i = 0; i < columnTitle.size(); i++){
					if(columnTitle.get(i).isEmpty()){}
					else {trim.add(columnTitle.get(i));}}
	            trim.remove(0); //to delete numberOfRows from array trim.
		} catch (IOException e) {e.printStackTrace();}
		return trim;
	}
 
	private void writebegin(String databaseName,File jsonFile ,String size ,String tableName ){
		jsonFile.delete();
		//ArrayOfTypes = column(databaseName , tableName);
		try{jsonFile.createNewFile();
			file = new BufferedWriter(new FileWriter(jsonFile));
            file.write("{");
            file.newLine();
            file.write(" \""+tableName+"\""+":{");
            file.newLine();
			file.write("\t"+"\""+"numberOfRows"+"\""+":"+" \""+size+"\""+",");
            file.newLine();
            file.write("\t"+"\""+tableName+"\""+":{");
            file.newLine();      
		}catch(IOException e){}
	}
 
	@Override
	public void writeFile(String databaseName, String tableName, ArrayList<ArrayList<String>> tableValues) {
		File jsonFile  = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		writebegin (databaseName,jsonFile,Integer.toString(tableValues.size()-1),tableName);
		try {ArrayList<String> firstRow = tableValues.get(0);
			for(int j = 0; j < firstRow.size(); j++){
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
		} catch (IOException e) {e.printStackTrace();}}
 
	@Override
	public int insertRow(String databaseName, String tableName, String[] properties) throws SQLException {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=insetObject.insertRow(working, properties, ArrayOfTypes,headers);
         writeFile(databaseName, tableName,tableData);
 
		return insetObject.getCounter();
	}
	@Override
	public int insertSub(String databaseName, String tableName, String[] columSend, String[] properties) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=insetObject.insertSub(working,columSend, properties, ArrayOfTypes,headers);
 
        writeFile(databaseName, tableName,tableData);
 
		return insetObject.getCounter();
	}
	@Override
	public int deleteTable(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData= new ArrayList<ArrayList<String>>();
		tableData= deleteObject.deleteTable(working,headers);
 
		 writeFile(databaseName, tableName,tableData);
		return deleteObject.getCounter();
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
		Type=SelectObject.getType();
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
		Type=SelectObject.getType();
		return outputTable;
	}
 
	@Override
	public String[][] selectAllColumns(String databaseName, String tableName) {
		// TODO Auto-generated method stub
		working= readFile(databaseName, tableName);
		writeFile(databaseName, tableName, SelectObject.selectAllColumns(working,ArrayOfTypes)); ///////////// return array
		Type=SelectObject.getType();
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
		Type=SelectObject.getType();
		return outputTable;
	}
 
	@Override
	public int addAlter(String databaseName, String tableName, String type, String columName) {
		// TODO Auto-generated method stub
		working = readFile(databaseName, tableName);
		tableData=alterObject.addColum(working, ArrayOfTypes,headers,type,columName);
		headers=alterObject.getHeaders();
		ArrayOfTypes =alterObject.getArrayOfTypes();
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
		working= distinctObject.distinct(tableData, columsName,headers,ArrayOfTypes);
		writeFile(databaseName, tableName, tableData); ///////////// return array
		Type=distinctObject.getType();
		String[][]outputTable = new String[(working.size())][working.get(0).size()];
		for (int i = 0; i < working.size(); i++) {
			for (int j = 0; j < working.get(0).size(); j++) {
				outputTable[i][j] = working.get(i).get(j);
			}
		}
		return outputTable;
	}
}