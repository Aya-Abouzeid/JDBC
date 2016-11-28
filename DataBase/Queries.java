
package eg.edu.alexu.csd.oop.DataBase;

import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Queries implements DataBaseInterface {
	String databasepath = "";
	DocumentBuilderFactory documentBuilderFactory;
	DocumentBuilder documentBuilder;
	Document document;
	int indexOfTable = 0;


	@Override
	public void createDatabase(String databaseName) {
		// TODO Auto-generated method stub
		File dataBaseDirectory = new File(System.getProperty("user.home") + File.separator + databaseName);
		databasepath = System.getProperty("user.home") + File.separator + databaseName;
		if (!dataBaseDirectory.exists()) {
			if (!dataBaseDirectory.mkdirs()) {
				dataBaseDirectory.mkdirs();
			}
		} else {
			try {
				FileUtils.cleanDirectory(dataBaseDirectory);
			} catch (IOException e) {
				System.out.println("Invalid Database.");
			}
		}
		System.out.println("Data Base Created successfully");
	}

	public void dropDatabase(String databaseName) {
		File file = new File(System.getProperty("user.home") + File.separator + databaseName);
		try {
			FileUtils.deleteDirectory(file);
			System.out.println("Data Base Dropped successfully");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void creatTable( String databaseName,String tableName , String[] properties) {
		// TODO Auto-generated method stub
		if(fileMinimizeBoolean(databaseName,tableName)){
		return ;} 
	    Element tableNameElement = document.createElement(tableName);
		Element rows = document.createElement(tableName);
		Attr numberOfrows = document.createAttribute("numberOfRows");
		numberOfrows.setValue("0");
		tableNameElement.setAttributeNode(numberOfrows);
		for (int i = 0; i < properties.length; i++) {
			String[] str = properties[i].split(" ");
			Node column = document.createElement(str[0]);
			column.appendChild(document.createTextNode(str[1]));
			rows.appendChild(column);
		}
		tableNameElement.appendChild(rows);
		document.appendChild(tableNameElement);
		document.normalize();
		transform(document,databaseName,tableName);
	}

	@Override
	public void deleteTable( String databaseName,String tableName ) {
		// TODO Auto-generated method stub
		File tables = new File(	System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
			 if (fileMinimizeBolean(tables,databaseName,tableName)){
				 return;
			 }
		//	NodeList tableList = document.getElementsByTagName(tableName);
			Element root = document.getDocumentElement();
			String [] columSend =columsCtreate(root); 
			dropTable(databaseName, tableName);
			creatTable(databaseName,tableName, columSend);
	}
	protected String[] columsCtreate (Element root ){
		NodeList children = root.getChildNodes();
		Node workNode = children.item(1);
        NodeList subChildNodes = workNode.getChildNodes();
        ArrayList<String> sendColunms = new ArrayList<String>();
        for( int i=0; i<subChildNodes.getLength()&&!(subChildNodes.item(i).getNodeName().equals(null));i++ ){
        	String item =subChildNodes.item(i).getNodeName()+" ";           	
        	item+=subChildNodes.item(i).getTextContent() ;
        	if( !(item.contains("#text"))){
        		sendColunms.add(item);
        		}	
        }
        String[] send= new String [ sendColunms.size()];
        for( int j =0 ; j<sendColunms.size();j++ ){
        	send[j]=sendColunms.get(j);
        	}
        return send;
	}
	
	protected String[] columTitles (Element root ){
		NodeList children = root.getChildNodes();
		Node workNode = children.item(1);
        NodeList subChildNodes = workNode.getChildNodes();
        ArrayList<String> sendColunms = new ArrayList<String>();
        for( int i=0; i<subChildNodes.getLength()&&!(subChildNodes.item(i).getNodeName().equals(null));i++ ){
        	String item =subChildNodes.item(i).getNodeName()+" ";           	
        	item+=subChildNodes.item(i).getTextContent() ;
        	if( !(item.contains("#text"))){
        		sendColunms.add(item);
        		}	
        }
        String[]titles =  new String [ sendColunms.size()];
        String[] send= new String [2];
        for( int j =0 ; j<sendColunms.size();j++ ){
        	send=sendColunms.get(j).split(" ");
        	titles[j]=send[1];
        	}
        return titles;
	}
	protected String[] columType(Element root ,String[] columnsValuesSplited){
		NodeList children = root.getChildNodes();
		Node workNode = children.item(1);
        NodeList subChildNodes = workNode.getChildNodes();
        ArrayList<String> sendColunms = new ArrayList<String>();
        for( int i=0; i<subChildNodes.getLength()&&!(subChildNodes.item(i).getNodeName().equals(null));i++ ){
        	String item =subChildNodes.item(i).getNodeName();
        	for( int count =0;count< columnsValuesSplited.length;count++){
        		boolean check =columnsValuesSplited[count].equals(subChildNodes.item(i).getTextContent());
        	if(!(item.contains("#text")) && (check)){
        		sendColunms.add(item);
        		}	
        	}
        }
        String[]titles =  new String [ sendColunms.size()];
        for( int j =0 ; j<sendColunms.size();j++ ){
        	titles[j]=sendColunms.get(j);
         }
        return titles;
	}
	
	protected void transform(Document document ,String databaseName ,String TableName) {
		try {
			Transformer serializer= SAXTransformerFactory.newInstance().newTransformer();
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            //serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            //serializer.setOutputProperty("{http://xml.customer.org/xslt}indent-amount", "2");
			/*Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");*/
			
			/*transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");*/
			/* done
			 * Transformer transformer = TransformerFactory.newInstance().newTransformer();
			 
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");*/
			/*TransformerFactory tranFactory = TransformerFactory.newInstance();
			Transformer aTransformer = tranFactory.newTransformer();*/
			
			/*aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			aTransformer.setOutputProperty("{http://x...content-available-to-author-only...e.org/xslt}indent-amount",
					"4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			aTransformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");*/
			
			/*Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
			Transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			Transformer.setOutputProperty(
	                    "{http://xml.apache.org/xslt}indent-amount", "3");*/
			DOMSource source = new DOMSource(document);
			try {
				FileWriter XmlFileWriter = new FileWriter(
						System.getProperty("user.home") + File.separator + databaseName+File.separator+TableName+".txt");
				StreamResult result = new StreamResult(XmlFileWriter);
				serializer.transform(source, result);
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

	public void dropTable(String databaseName, String TableName) {
		File table = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+TableName+".txt");
		if (DetectDataBase(databaseName)&& table.exists()) {
			System.out.println("Table is Dropped");
			table.delete();
		}else{
			System.out.println("Invalid Command.");
		}
	}

	public void insertRow( String databaseName ,String TableName ,String[] proberties) {
		File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+TableName+".txt");
		 if (fileMinimizeBolean(tables,databaseName,TableName)){
			 return;}
		Element root = document.getDocumentElement();
		String[] columnsValuesSplited= columTitles(root);
		String idUsed = root.getAttribute("numberOfRows");
		Element newRow = document.createElement(TableName);
		//Node values;
		String[] types = columType(root,columnsValuesSplited);
		if(columnsValuesSplited.length!= proberties.length ){
			System.out.println("Invalid Insertion");
			return ;}
       loopInsertRow(columnsValuesSplited, types, proberties, newRow);
		NamedNodeMap attr = root.getAttributes();
		Node nodeAttr = attr.getNamedItem("numberOfRows");
		nodeAttr.setTextContent(Integer.toString(Integer.parseInt(idUsed) + 1));
		root.appendChild(newRow);
		transform(document,databaseName,TableName);
	}
protected void loopInsertRow(String[]columnsValuesSplited,String[]types,String[]proberties,Element newRow) {
	Node values;
	for (int y = 0; y < columnsValuesSplited.length; y++) {
		if(checkValueBoolean(types, proberties, y)){
			return ;
		} 
		values = document.createElement(columnsValuesSplited[y]);
		values.appendChild(document.createTextNode(proberties[y]));
		newRow.appendChild(values);
	}
}
	protected boolean checkValueBoolean(String[]types,String[] proberties,int y){
		if(types[y].equals("int") ){
			if(!isNumeric(proberties[y])){
				System.out.println("Invalid Value For Int");
				return true;
			}
		}
		return false;
	}
	
	protected void loopInsert(String databaseName,String tableName, String[]columnsValuesSplited,String[]columSend,Document document, Element newRow,String[]properties,String[]types ){
		boolean write =true;
		Node values;
		for(int i=0;i<columnsValuesSplited.length;i++){
			for(int j=0;j<columSend.length;j++){
				if(columnsValuesSplited[i].equals(columSend[j])){
				    write=false;
				    if(checkValueBoolean(types, properties, j)){
				    	deleterow(databaseName, tableName);
				    	
				    	return ; }
					values = document.createElement(columnsValuesSplited[i]);
					values.appendChild(document.createTextNode(properties[j]));
					newRow.appendChild(values);
					break; }
			}
			if(write){
				values = document.createElement(columnsValuesSplited[i]);
				values.appendChild(document.createTextNode("null"));
				newRow.appendChild(values);}
			write=true;
		}
	}
	@Override
	public void insertSub( String databaseName,String tableName , String[] columSend, String[] properties) {
		// TODO Auto-generated method stub
		File tables = new File(	System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
		 if (fileMinimizeBolean(tables,databaseName,tableName)){return;}
			for (int i = 0; i < columSend.length; i++) {
		        if(!DetectColumn(databaseName, tableName, columSend[i])){
		        	System.out.println("Invalid Column To Insertion"); 
		        	return ;}
		}
			Element root = document.getDocumentElement();		
			String[] columnsValuesSplited = columTitles(root);
			String[] types = columType(root,columSend);
			String idUsed = root.getAttribute("numberOfRows");
			Element newRow = document.createElement(tableName);  
            loopInsert(databaseName, tableName,columnsValuesSplited, columSend, document, newRow, properties,types);
			NamedNodeMap attr = root.getAttributes();
			Node nodeAttr = attr.getNamedItem("numberOfRows");
			nodeAttr.setTextContent(Integer.toString(Integer.parseInt(idUsed) + 1));
			root.appendChild(newRow);
			transform(document,databaseName,tableName);
	}
	 
	 protected  void updateLoop (String [] updateStatment,NodeList subChildNodes,Element root  ){
		 String[] sendForType= new String[updateStatment.length/3];
		 String[] sendForcheck= new String[updateStatment.length/3];
		 int looping =2, counter =0;
		 for (int i = 0; i < sendForType.length; i++) {
			sendForType[i]=updateStatment[counter];
			sendForcheck[i]=updateStatment[looping];
			counter=counter+3;
			looping=looping+3;
		}
		 counter=0;
		 String[] types = columType(root,sendForType);
			for (int count = 0; count < updateStatment.length; count = count + 3) {
				for (int loop = 0; loop < subChildNodes.getLength(); loop++) {
					if (subChildNodes.item(loop).getNodeName().equals(updateStatment[count])) {
						if (checkValueBoolean(types, sendForcheck, counter)){
	                       return;}
						subChildNodes.item(loop).setTextContent(updateStatment[count + 2]);
						counter++;}
				}
			}
	 }
	 protected boolean isNumeric(String str)  
	 {  
	   try  
	   {  
	     int d = Integer.parseInt(str); 
	   }  
	   catch(NumberFormatException nfe)  
	   {  
	     return false;  
	   }  
	   return true;  
	 }
	@Override
	public void update(String databaseName,String tableName , String[] condition, String[] updateStatment) {
   	 File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
   	 if (fileMinimizeBolean(tables,databaseName,tableName)){ return;}
       if(!DetectColumn(databaseName, tableName, condition[0])){
       	System.out.println("Invalid Column  In Condition");
       	return ;}
	   	for (int i = 0; i < updateStatment.length; i=i+3) {
	        if(!DetectColumn(databaseName, tableName, updateStatment[i])){
	        	System.out.println("Invalid Update Statment");
	        	return ;}  }
        try {
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getElementsByTagName(tableName);
        checkCondition(root, condition);
        for(int index = 1; index < childNodes.getLength(); index++) {
       	 Node workNode = childNodes.item(index);
            NodeList subChildNodes = workNode.getChildNodes();
            for( int i =0 ; i<subChildNodes.getLength();i++ ){
           conditionExecution(subChildNodes, condition, updateStatment, i,root);} }
        transform(document, databaseName, tableName);
       } catch (Exception e) {
       	System.out.println("Invalid Command.");
		} 
	}
	protected void checkCondition(Element root,String[] condition ) {
		String[]pass={ condition[0]};
        String[]types= columType(root,pass);
        String[]proberties={condition[2]};
        if (checkValueBoolean(types, proberties, 0)){
        	System.out.println(" Invald Type For Condition");
        return;}
	}
	@Override
	public void deleteSubTable(String databaseName, String tableName, String[] condition) {
	   	 File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
	   	 if (fileMinimizeBolean(tables,databaseName,tableName)){ return;}
	        boolean out=false;
	        if(!DetectColumn(databaseName, tableName, condition[0])){
	        	System.out.println("Invalid Column In Condition");
	        	return ;}
	         try {
	         Element root = document.getDocumentElement();
	         NodeList childNodes = root.getElementsByTagName(tableName);
	         ArrayList<Integer> itemsCounterDeleted = new ArrayList<Integer>();
	         loopDelete(childNodes, condition, itemsCounterDeleted, out);
	         checkCondition(root, condition);
	         int delete=0;
	         for(int i=0;i<itemsCounterDeleted.size();i++){
	        	 root.removeChild(childNodes.item(itemsCounterDeleted.get(i)-delete));
	        	delete++;}
	         transform(document, databaseName, tableName);
	        } catch (Exception e) {
	        	System.out.println("Invalid Command");
	 		} 
	}
	public void deleterow(String databaseName, String tableName) {
	   	 File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
	   	 if (fileMinimizeBolean(tables,databaseName,tableName)){
			 return;
		 }
	       // boolean out=false;
	         try {
	         Element root = document.getDocumentElement();
	         NodeList childNodes = root.getElementsByTagName(tableName);
	         //ArrayList<Integer> itemsCounterDeleted = new ArrayList<Integer>();
	         root.removeChild(childNodes.item(childNodes.getLength()));
	         transform(document, databaseName, tableName);
	        } catch (Exception e) {
	        	System.out.println("Invalid Command");
	 		} 
	}

	protected void loopDelete( NodeList childNodes,String[]condition,ArrayList<Integer> itemsCounterDeleted,boolean out){
        for(int index = 1; index < childNodes.getLength(); index++) {
       	 Node workNode = childNodes.item(index);
            NodeList subChildNodes = workNode.getChildNodes();
            for( int i =0 ; i<subChildNodes.getLength();i++ ){
           	 out= conditionDelete(subChildNodes, condition, i,out);
           	 if(out){
           		 itemsCounterDeleted.add(index);
           	     out=false;
           		 i=subChildNodes.getLength();
           	 }
             }   
        }
	}
	@Override
	public void updateWhitoutWhere( String databaseName,String tableName , String[] updateStatment) {
	   	 File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
	   	 if (fileMinimizeBolean(tables,databaseName,tableName)){return;}
	    	for (int i = 0; i < updateStatment.length; i=i+3) {
		        if(!DetectColumn(databaseName, tableName, updateStatment[i])){
		        	System.out.println("Invalid Update Statment");
		        	return ;}
			}
	        try {
	            Element root = document.getDocumentElement();
	            NodeList childNodes = root.getElementsByTagName(tableName);
	            for(int index = 1; index < childNodes.getLength(); index++) {
	           	 Node workNode = childNodes.item(index);
	                NodeList subChildNodes = workNode.getChildNodes();
	                for( int i =0 ; i<subChildNodes.getLength();i++ ){
	                	updateLoop(updateStatment, subChildNodes,root);}   
	            }
	            transform(document, databaseName, tableName);
	           } catch (Exception e) {
	           	System.out.println("Invalid Command");
	    		} 
	}
	protected void conditionExecution(NodeList subChildNodes, String[] condition, String[] updateStatment,int i,Element root){
		if (subChildNodes.item(i).getNodeName().equals(condition[0])) {
			if (condition[1].equals("=")) {
				 Element work =(Element)subChildNodes.item(i);
				if (work.getTextContent().equals(condition[2])) {
				updateLoop(updateStatment, subChildNodes,root);}
			} else if (condition[1].equals("<")) {
				 Element work =(Element)subChildNodes.item(i);
				if (Integer.parseInt(work.getTextContent()) < Integer.parseInt(condition[2])) {
					updateLoop(updateStatment, subChildNodes,root);}
			} else if (condition[1].equals(">")) {
				 Element work =(Element)subChildNodes.item(i);
				if (Integer.parseInt(work.getTextContent()) > Integer.parseInt(condition[2])) {
					updateLoop(updateStatment, subChildNodes,root);}
			}
		}
	}
	protected boolean conditionDelete(NodeList subChildNodes, String[] condition, int i,boolean out){
		if (subChildNodes.item(i).getNodeName().equals(condition[0])) {
			if (condition[1].equals("=")) {
				 Element work =(Element)subChildNodes.item(i);
				if (work.getTextContent().equals(condition[2])) {
					out = true;
					}
			} else if (condition[1].equals("<")) {
				 Element work =(Element)subChildNodes.item(i);
				if (Integer.parseInt(work.getTextContent()) < Integer.parseInt(condition[2])) {
					out = true;
					}
			} else if (condition[1].equals(">")) {
				 Element work =(Element)subChildNodes.item(i);
				if (Integer.parseInt(work.getTextContent()) > Integer.parseInt(condition[2])) {
					out = true;
					}
			}
		}
    return out;
	}

	

	public boolean fileMinimizeBoolean( String databaseName,String tableName) {
		boolean testData =false; 
		testData=DetectDataBase(databaseName);
		if(!testData){
		 System.out.println("Invalid Database");
		 return true;
		}
//		if(DetectTable(databaseName, tableName)){
//			return true ;}
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
		 boolean testData=DetectDataBase(databaseName);
			if(!testData){
			 System.out.println("Invalid Database.");
			 return true;}
			boolean testTable= DetectTable(databaseName, tableName);
	        if(!testTable){
	        	System.out.println("Invalid Table Name.");
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

	protected boolean DetectColumn(String databaseName, String tableName , String Column) {
		File tableFile = new File(
				System.getProperty("user.home") + File.separator + databaseName + File.separator + tableName + ".txt");
		 if (fileMinimizeBolean(tableFile,databaseName,tableName)){
			 return false;
		 }
		Element root = document.getDocumentElement();
		String[] columntitles=columTitles(root);
		for(int i = 0 ; i < columntitles.length;i++){
			if (columntitles[i].equals(Column))
				return true;
		}
		return false;
	}
	
	
	 private boolean  grater( NodeList childValues,int count ,String []Condition,ArrayList<String>outputTableList,String[]columntitles) {
         try {
           
       
            Node change= childValues.item(count);
        if (childValues.item(count).getNodeName().equals(Condition[0])){                       
            if (Integer.parseInt(change.getTextContent()) > Integer.parseInt(Condition[2])) {
                for (int loop = 0; loop < columntitles.length; loop++) {
                    for (int k = 0; k < childValues.getLength(); k++) {
                        if (childValues.item(k).getNodeName().equals(columntitles[loop])) {
                            outputTableList.add(childValues.item(k).getTextContent()); 
                        }
                    }                                                                                              
                }
            }              
        }
        return true;
         } catch (Exception e) {
                // TODO: handle exception
             System.out.println("Error In Condition");
             return false;
            }
 
    }
	public String[][] selectColumnsWithCondition(String databaseName, String tableName,String[] columntitles, String[] Condition) {
        File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
        boolean out = false ,check=false;
         if (fileMinimizeBolean(tables,databaseName,tableName)){ return null;}
         try {
        Element root = document.getDocumentElement();
        NodeList children = root.getElementsByTagName(tableName);          
        ArrayList<String> outputTableList = new ArrayList<String>();
        check=loopPrint(databaseName, tableName, Condition, columntitles, outputTableList);
        if (!check){return null;}
        checkCondition(root, Condition);
        out=forSelect(children, Condition, columntitles, outputTableList);
        String[][] outputTable = new String[(outputTableList.size()/columntitles.length)][columntitles.length];
        return outputTable=loopToAdd(outputTableList, columntitles);
       
        } catch (Exception e) {
            System.out.println("Invalid In Selection.");
            return null;
        }
    }
	private boolean forSelect( NodeList children, String [] Condition,String [] columntitles, ArrayList<String> outputTableList) {
        boolean out = false;
        try {
           for (int i = 1; i < children.getLength(); i++) {
               Node child = children.item(i);
               NodeList childValues = child.getChildNodes();  
               for (int count = 0; count <childValues.getLength(); count++) {
                   if(Condition[1].equals("=")){
                    out=equal(childValues, count, Condition, outputTableList, columntitles);
                   }else if(Condition[1].equals(">")){                
                       out=grater(childValues, count, Condition, outputTableList, columntitles);
                   }else if(Condition[1].equals("<")){
                       out=smaller(childValues, count, Condition, outputTableList, columntitles);
                   }                  
               }  
           }
           if (!out) {
               return false;
              
           }
           return true;
        } catch (Exception e) {
               // TODO: handle exception
            return false;
           }
   }

    protected boolean smaller(NodeList childValues,int count ,String []Condition,ArrayList<String>outputTableList,String[]columntitles) {
        try {
          
      
           Node change= childValues.item(count);
       if (childValues.item(count).getNodeName().equals(Condition[0])){                       
           if (Integer.parseInt(change.getTextContent()) > Integer.parseInt(Condition[2])) {
               for (int loop = 0; loop < columntitles.length; loop++) {
                   for (int k = 0; k < childValues.getLength(); k++) {
                       if (childValues.item(k).getNodeName().equals(columntitles[loop])) {
                           outputTableList.add(childValues.item(k).getTextContent()); 
                       }
                   }                                                                                              
               }
           }  
          
       }
       return true;
        } catch (Exception e) {
               // TODO: handle exception
            System.out.println("Error in Condition In Selection.");
            return false;
           }
  
      
   }
	protected String[] [] loopToAdd(ArrayList<String> outputTableList,String[]columntitles) {
        int counter=0;
        String[][] outputTable = new String[(outputTableList.size()/columntitles.length)][columntitles.length];
        for (int i = 0; i <(outputTableList.size()/columntitles.length) ; i++) {
            for (int j = 0; j <columntitles.length; j++) {
                if( counter<outputTableList.size()){
            outputTable[i][j]=outputTableList.get(counter);                
            counter++;}
            }
         }
        return  outputTable;
    }
	private String[][] conditionsEqual(String[] Condition,NodeList colum,String[] columntitles,int lengthroots,String roows){
		String[][] Outputcolumns = new String[lengthroots][columntitles.length];
		if (roows.equals(Condition[2])) {
			for (int g = 0; g < lengthroots; g++) {
				Node colums2 = colum.item(g);
				if(!(colums2.getNodeName().equals("#text"))){
					for (int h = 0; h < columntitles.length; h++) {
						if (colums2.getNodeName().equals(columntitles[h])) {
							String roows2 = colums2.getTextContent();
							Outputcolumns[g][h] = roows2;}}}}}
		return Outputcolumns;
		}
	private String[][] conditionsGreater(String[] Condition,NodeList colum,String[] columntitles,int lengthroots,String roows){
		String[][] Outputcolumns = new String[lengthroots][columntitles.length];
		if (roows.compareTo(Condition[2]) > 0) {
			for (int g = 0; g < lengthroots; g++) {
				Node colums2 = colum.item(g);
				for (int h = 0; h < columntitles.length; h++) {
					if (colums2.getNodeName().equals(columntitles[h])) {
						String roows2 = colums2.getTextContent();
						Outputcolumns[g][h] = roows2;}}}}
		return Outputcolumns;
	}
	private String[][] conditionsLess(String[] Condition,NodeList colum,String[] columntitles,int lengthroots,String roows){
		String[][] Outputcolumns = new String[lengthroots][columntitles.length];
		if (roows.compareTo(Condition[2]) < 0) {
			for (int g = 0; g < lengthroots; g++) {
				Node colums2 = colum.item(g);
				for (int h = 0; h < columntitles.length; h++) {
					if (colums2.getNodeName().equals(columntitles[h])) {
						String roows2 = colums2.getTextContent();
						Outputcolumns[g][h] = roows2;}}}}
		return Outputcolumns;
	}
	
	public boolean equal ( NodeList childValues,int count ,String []Condition,ArrayList<String>outputTableList,String[]columntitles) {
        try {
         Node change= childValues.item(count);
        if (childValues.item(count).getNodeName().equals(Condition[0])){                       
            if (change.getTextContent().equals(Condition[2])) {
                for (int loop = 0; loop < columntitles.length; loop++) {
                    for (int k = 0; k < childValues.getLength(); k++) {
                        if (childValues.item(k).getNodeName().equals(columntitles[loop])) {
                            outputTableList.add(childValues.item(k).getTextContent()); 
                           
                        }
                    }                                                                                              
                }
            }
               
        }
        return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error In Condition in Selection.");
            return false;
        }  
    }
	
	
	
	
	
	public String[][] selectColumns(String databaseName, String tableName,String[] columntitles) {
		File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
		if (!fileMinimizeBolean(tables,databaseName,tableName)){
			Element root = document.getDocumentElement();
		    String columnsValues = root.getAttribute("numberOfRows");
		    ArrayList<String> outputTableList = new ArrayList<String>();
		    int index = Integer.parseInt(columnsValues)+1;
		for (int i = 0; i < columntitles.length; i++) {
	        if(!DetectColumn(databaseName, tableName, columntitles[i])){
	        	System.out.println("Invalid Column To Select."); 
	        	return null;}
		}
		String[][] outputTable = new String[index][columntitles.length];
		for (int i = 0; i < columntitles.length; i++) {
			String title = columntitles[i];
			outputTableList.add(title);
			NodeList roots = root.getElementsByTagName(tableName);
			for (int y = 1; y < index; y++) {
				Node item = roots.item(y);
				NodeList colum = item.getChildNodes();
				for (int g = 0; g < colum.getLength(); g++) {
					Node colums = colum.item(g);
					if (colums.getNodeName().equals(title)) {
						Element rowsValues = (Element) colums;
						String roows = rowsValues.getTextContent();
						outputTableList.add(roows);}}}}
		int r = 0;
		int l = 0;
		while(r < outputTableList.size()){
			for( int j = 0 ; j < index ;j++ ){
	        	outputTable[j][l] = outputTableList.get(r);
	        	r++;
	         }
			l++;
         }
		return outputTable;
		}
		else 
			return null;
}
	public String[] selectAllColumns(String databaseName, String tableName) {
		File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
		 if (fileMinimizeBolean(tables,databaseName,tableName)){
			 return null;
		 }
		Element root = document.getDocumentElement();
		NodeList roots = root.getElementsByTagName(tableName);
		String tableattributes = " ";
		  ArrayList<String> outputTableList = new ArrayList<String>();
		for (int i = 0; i < roots.getLength(); i++) {
			Node item = roots.item(i);
			tableattributes = item.getTextContent();
			outputTableList.add(tableattributes);
			
			}
		String [] outputTable = new String[outputTableList.size()];
        for( int j =0 ; j<outputTableList.size();j++ ){
        	outputTable[j]=outputTableList.get(j);
         }
		return outputTable;
	}
	public String[] selectAllWithCondition(String databaseName, String tableName, String[] Condition) {
		File tables = new File(System.getProperty("user.home") + File.separator + databaseName+File.separator+tableName+".txt");
		if (fileMinimizeBolean(tables,databaseName,tableName)){
			 return null;
		 }
		Element root = document.getDocumentElement();
		NodeList roots = root.getElementsByTagName(tableName);
		if(!DetectColumn(databaseName, tableName, Condition[0])){
        	System.out.println("Invalid Column in Condition."); 
        	return null;}
		checkCondition(root, Condition);
		String[] l = columTitles(root);
		String titles="";
		for(int i = 0 ;i<l.length;i++){
			titles = titles+l[i]+" ";
		}
	     ArrayList<String> outputTableList = new ArrayList<String>();
	     outputTableList.add(titles);
		String title = Condition[0];
          selectAllLoop(roots, outputTableList, title, Condition);  
		String [] outputTable = new String[outputTableList.size()];
        for( int j =0 ; j < outputTableList.size();j++ ){
        	outputTable[j] = outputTableList.get(j);
         }
		return outputTable;
	}	
	
	private boolean loopPrint(String databaseName,String tableName,String[]Condition,String[]columntitles,ArrayList<String> outputTableList) {
        if(!DetectColumn(databaseName, tableName, Condition[0])){
            System.out.println("Invalid Column In Condition.");
            return false;}
        for (int i = 0; i < columntitles.length; i++) {
            if(!DetectColumn(databaseName, tableName, columntitles[i])){
                System.out.println("Invalid Column TO Select.");
                return false;}
        }
        for (int i = 0; i < columntitles.length; i++) {
            outputTableList.add(columntitles[i]);
            }
        return true;
    }
   
   
	protected void selectAllLoop(NodeList roots,ArrayList<String> outputTableList ,String title ,String[] Condition){
	    String tableattributes = "";
		for (int i = 0; i < roots.getLength(); i++) {
			Node item = roots.item(i);
			NodeList colum = item.getChildNodes();
			for (int y = 0; y < colum.getLength(); y++) {
				Node colums = colum.item(y);
				if (colums.getNodeName().equals(title)) {
					Element rowsValues = (Element) colums;
					String roows = rowsValues.getTextContent();
					outputTableList.add(roows);
					if (Condition[1].equals("=")) {
						if (roows.equals(Condition[2])) {
							tableattributes = item.getTextContent();
							outputTableList .add( tableattributes);}
					} else if(Condition[1].equals(">")){
						if (roows.compareTo(Condition[2]) > 0 ) {
							tableattributes = item.getTextContent();
							outputTableList .add( tableattributes);}
					} else if(Condition[1].equals("<")){
						if (roows.compareTo(Condition[2]) < 0 ) {
							tableattributes = item.getTextContent();
							outputTableList .add( tableattributes);}}}}}
	}
	

}