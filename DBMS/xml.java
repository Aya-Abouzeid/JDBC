package eg.edu.alexu.csd.oop.DBMS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class xml {
	 protected  DocumentBuilderFactory documentBuilderFactory;
	 protected DocumentBuilder documentBuilder;
	 protected Document document;
	 protected BufferedWriter fileWriter;
	 protected int indexOfTable = 0;
	  private String path;
	  private XmlValidation obXmlValidation;

	  public  xml(String path) {
		this.path = path;
		this.obXmlValidation = new XmlValidation(path);

	}
	 public boolean fileMinimizeBoolean( String databaseName,String tableName) {
			boolean testData =false; 
			testData=obXmlValidation.DetectDataBase(databaseName);
			if(!testData){
			 System.out.println("invalid database");
			 return true;
			}
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			try {
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document = documentBuilder.newDocument();
			return false;
		}
		public boolean fileMinimizeBolean(File folderName, String databaseName, String tableName) {
			 boolean testData=obXmlValidation.DetectDataBase(databaseName);
				if(!testData){
				 System.out.println("invalid database");
				 return true;}
				boolean testTable= obXmlValidation.DetectTable(databaseName, tableName);
		        if(!testTable){
		        	System.out.println("invalid table name");
		        	return true; }
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			try {
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
				document = documentBuilder.parse(folderName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false ;
		}
		
		protected void transform(Document document ,String databaseName ,String TableName) {
			try {
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, TableName+".dtd");
				DOMSource source = new DOMSource(document);
				try {
					FileWriter XmlFileWriter = new FileWriter(
							path + File.separator + databaseName+File.separator+TableName+".xml");
					StreamResult result = new StreamResult(XmlFileWriter);
					transformer.transform(source, result);
					XmlFileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (TransformerConfigurationException e) {
				throw new RuntimeErrorException(null);
			} catch (TransformerException e) {
				throw new RuntimeErrorException(null);
			}
		}

		protected boolean DetectDataBase(String name) {
			File file = new File(path + File.separator + name);
			if (file.exists()){
				return true;
				}
			return false;
		}
}
