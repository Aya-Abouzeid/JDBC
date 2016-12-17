package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.crypto.NodeSetData;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class User {
	protected  DocumentBuilderFactory documentBuilderFactory;
	 protected DocumentBuilder documentBuilder;
	 protected Document document;
	
	public ArrayList<ArrayList<String>> readFile2(){
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> datause ;
		File tables = new File(System.getProperty("user.home")+File.separator+"connect.xml");
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(tables);
			Element root = document.getDocumentElement();
			NodeList users = root.getElementsByTagName("user");
			for(int i = 0 ; i < users.getLength();i++){
				 datause = new ArrayList<String>();
				NodeList u = ((Element) users.item(i)).getElementsByTagName("username");
				NodeList p = ((Element) users.item(i)).getElementsByTagName("password");
					datause.add(u.item(0).getTextContent());
	                datause.add(p.item(0).getTextContent());
                    data.add(datause);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public boolean checkPassAndUser(String username ,String password){
		 ArrayList<ArrayList<String>> configurationFile = readFile2();
		 for(int i = 0 ; i < configurationFile.size();i++){
			 for(int y = 0;y < configurationFile.get(i).size();y++){
				 if(configurationFile.get(i).get(0).equals(username)&&configurationFile.get(i).get(1).equals(password)){
					 return true;
				 }
			 }
		 }
		 return false;
	}
}
