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
		String lowercaseddb = name.toLowerCase();

		File file = new File(path + File.separator + lowercaseddb);
		if (file.exists()){
			return true;
			}
		return false;
	}
	public boolean DetectTable(String databaseName, String tableName) {
		 File dbdirectory = new File (path + File.separator + databaseName);
		 String[] tablesNames=dbdirectory.list();
		 
		 for (int i = 0; i < tablesNames.length; i++) {
			if(tablesNames[i].substring(0, tablesNames[i].indexOf('.')).equalsIgnoreCase(tableName)){
				
				tableName = tablesNames[i].substring(0, tablesNames[i].indexOf('.'));
			}

		}

		File tableFile = new File(
				path + File.separator + databaseName + File.separator + tableName + ".xml");
		File tableFile2 = new File(
				path + File.separator + databaseName + File.separator + tableName + ".json");
		
		if (tableFile.exists() || tableFile2.exists()){
			return true;
			}

		return false;
	}

	
	
	
	

}
