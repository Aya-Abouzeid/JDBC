package eg.edu.alexu.csd.oop.DBMS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DtdFile  {
	/* protected  DocumentBuilderFactory documentBuilderFactory;
	 protected DocumentBuilder documentBuilder;
	 protected Document document;
	 protected BufferedWriter fileWriter;
	 protected int indexOfTable = 0;*/
	
	private String path;
	private xml objXml;
	public  DtdFile(String path) {
		System.out.println("XMLDTD: " + path);
		this.path= path;
		objXml= new xml(path);
	}
//	public void CreateDtDFile(String databaseName,String tableName, String[] dtd,String[]type){
//   	 File dtdFile = new File(path + File.separator + databaseName + File.separator + tableName + ".dtd");
//   	 File tables = new File(path + File.separator + databaseName + File.separator + tableName + ".xml");
//   	objXml.documentBuilderFactory = DocumentBuilderFactory.newInstance();
//		try {
//			objXml.documentBuilder = objXml.documentBuilderFactory.newDocumentBuilder();
//			objXml.document = objXml.documentBuilder.parse(tables);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//   		 Element root =objXml.document.getDocumentElement();
//    		 NodeList roots = root.getElementsByTagName(tableName);
//    		 NodeList columns = (roots.item(0)).getChildNodes();     		 
//       	 try {objXml.fileWriter = new BufferedWriter(new FileWriter(dtdFile));
//       	objXml.fileWriter.write("<!ELEMENT "+tableName+" "+"("+tableName +","+ tableName +"*)"+">\n");
//       	objXml.fileWriter.write("<!ATTLIST "+tableName+" "+" numberOfRows"+ " CDATA "+"#REQUIRED"+">\n");
//       	objXml.fileWriter.write("<!ELEMENT "+roots.item(0).getNodeName()+" (");
//   			//ForLoopNodes(columns);
//       	objXml.fileWriter.write(columns.item(columns.getLength()-2).getNodeName()+")>\n");
//   			for(int y = 0;y < (type.length);y++){
//      				if(y ==(type.length - 1)){objXml.fileWriter.write(type[y]+")>\n");}
//      				else{objXml.fileWriter.write(type[y]+",");}}
//   			for(int g = 0 ;g < type.length; g++){
//   				objXml.fileWriter.write("<!ELEMENT "+type[g]+" (#PCDATA)>\n");}
//   			objXml.fileWriter.write("<!ELEMENT "+tableName+" "+"(");
//      			for(int y = 0;y < (dtd.length);y++){
//      				if(y==(dtd.length-1)){
//      					objXml.fileWriter.write(dtd[y]);
//      				}else{
//      					objXml.fileWriter.write(dtd[y]+",");
//       				
//      				}
//   			}
//      			objXml.fileWriter.write(")>\n");
// 	
//   			for(int g = 0 ;g < dtd.length; g++){
//   				objXml.fileWriter.write("<!ELEMENT "+dtd[g]+" (#PCDATA)>\n");
//   			}	
//   			objXml. fileWriter.close();
//       			} catch (IOException e) {System.out.println("Invalid Input");}
//       	 }
	protected void ForLoopNodes(NodeList elements){
		for(int i = 0; i<(elements.getLength()-2); i++){
			if(!elements.item(i).getNodeName().equals("#text")){
				try {
					objXml.fileWriter.write(elements.item(i).getNodeName()+",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}

	protected void ForLoopValues(NodeList elements){
		for(int g = 0 ;g < elements.getLength(); g++){
			if(!elements.item(g).getNodeName().equals("#text")){
				try {
					objXml.fileWriter.write("<!ELEMENT "+elements.item(g).getNodeName()+" (#PCDATA)>\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
	public boolean validateWithDTDUsingDOM(String databaseName, String tableName )
		    throws ParserConfigurationException, IOException{
		    try {
		      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		      factory.setValidating(true);
		      factory.setNamespaceAware(true);
		      DocumentBuilder builder = factory.newDocumentBuilder();
		      builder.setErrorHandler(new ErrorHandler() {
		            public void warning(SAXParseException e) throws SAXException {
		              System.out.println("WARNING : " + e.getMessage());}
		            public void error(SAXParseException e) throws SAXException {}
		            public void fatalError(SAXParseException e) throws SAXException {
		              System.out.println("FATAL : " + e.getMessage());}});
		      builder.parse(new InputSource(path + File.separator + databaseName+File.separator+tableName+".xml"));
		      return true;}
		    catch (ParserConfigurationException pce) {
		      throw pce;}
		    catch (IOException io) {
		      throw io;}
		    catch (SAXException se){
		      return false;}}
		

}
