package eg.edu.alexu.csd.oop.DBMS;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlValidation {
	private String path;
	public  XmlValidation(String path ) {
		this.path = path;
	}
	public String getpath() {
		return path;
	}

	public boolean DetectDataBase(String name) {
		File file = new File(path + File.separator + name);
		if (file.exists()){
			return true;
			}
		return false;
	}
	
	public boolean DetectTable(String databaseName, String tableName) {
		File tableFile = new File(
				path + File.separator + databaseName + File.separator + tableName + ".json");
		File tableFile2 = new File(
				path + File.separator + databaseName + File.separator + tableName + ".xml");
		if (tableFile.exists() ||tableFile2.exists() ){
			return true;
			}

		return false;
	}

	
	
	
	

}
