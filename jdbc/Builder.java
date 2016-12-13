package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.sql.Date;

public  class Builder {	
		//required 
	private String[]properties;
	String[]datatype ;
	String[]titles ;
	public Builder(String databaseName,String tableName ,String[]properties){
		for(int i = 0;i < properties.length; i++){
			this.properties[i] = properties[i];
		}
		File protoBufFile = new File (System.getProperty("user.home")+File.separator+databaseName+File.separator+tableName+".proto");
		for(int i = 0 ;i < properties.length;i++){
			String[] split = properties[i].split(" ");
			datatype[i] = split[1];
			titles[i] = split[0];
		}
	}
}

