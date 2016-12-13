package eg.edu.alexu.csd.oop.DBMS;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlValidation {
	protected boolean DetectDataBase(String name) {
		File file = new File(System.getProperty("user.home") + File.separator + name);
		if (file.exists()){
			return true;
			}
		return false;
	}
	protected boolean DetectTable(String databaseName, String tableName) {
		File tableFile = new File(
				System.getProperty("user.home") + File.separator + databaseName + File.separator + tableName + ".txt");
		if (tableFile.exists()){
			return true;
			}
		return false;
	}

	

}
