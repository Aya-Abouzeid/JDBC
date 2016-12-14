package eg.edu.alexu.csd.oop.DBMS;

import java.sql.Date;
import java.lang.Float;
import java.lang.String;
import java.lang.Integer;
//int //string
public class compare {
  
   public int compareFloat(String one , String two){
	   Float first =   Float.parseFloat(one);
	   Float second = Float.parseFloat(two);
       return first.compareTo(second); 
      
   }
  
   public int compareDate(String one , String two){
	   Date first =   Date.valueOf(one);
	   Date second = Date.valueOf(two);
       return one.compareTo(two);
      
   }
   public int compareString(String one , String two){
      

       return one.compareTo(two);
          
   }
   public int compareInteger(String one , String two){
        Integer first = Integer.parseInt(one);
        Integer second = Integer.parseInt(two);
      
       return first.compareTo(second); 
      
   }
}