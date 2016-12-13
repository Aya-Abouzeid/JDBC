package eg.edu.alexu.csd.oop.DBMS;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Titles extends xml {
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
}
