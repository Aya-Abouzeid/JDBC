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

public class JSONFile {
	
private String[] type ;
private String[] columnTitles;

	public void createJSONfile(String databaseName , String tableName ,String[] properties ,String path){
		File jsonFile  = new File(path + File.separator + databaseName+File.separator+tableName+".json");
		try {
			if (jsonFile.createNewFile()){
		        System.out.println("File is created!");
		      }else
		        System.out.println("File already exists.");
			type = new String[properties.length];
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
    			type[i] = str[0];
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
	public String []  column(String databaseName , String tableName){
		File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".json");
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
    		type = new String[t.size()];
    		for(int i = 0;i < t.size();i++){
    			type[i]=t.get(i);
    		}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return type;
	}
	public String [] headers (ArrayList<ArrayList<String>> table){
		for(int x = 0; x<table.get(0).size(); x++){
			columnTitles[x] = table.get(0).get(x);
		}
		return columnTitles;
		
	}
	public ArrayList<ArrayList<String>> jsonReader(String databaseName , String tableName){
		ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
		File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".json");
		type = column(databaseName , tableName);
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
					for(int g = 0 ; g < type.length; g++ ){
						Row.add(trim.get(y));
			            y++;
					}
					tablelines.add(Row);
				}
			for(int j = 0;j < tablelines.size() ;j++){
				ArrayList<String> R = new ArrayList<String>();
				for(int h = 0;h < type.length ;h++){
					String[] str = tablelines.get(j).get(h).split(":");
					R.add(str[1]);
				}
				tableData.add(R);
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return tableData;
	}
	public void jsonWriter(String databaseName ,String tableName ,ArrayList<ArrayList<String>> tableValues){
		File jsonFile  = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".json");
		type = column(databaseName , tableName);
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
    			file.write("\t\t"+"{\""+type[j]+"\""+":"+"\""+firstRow.get(j)+"\""+"}"+",");
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
}
