package eg.edu.alexu.csd.oop.DBMS;

import java.sql.Date;
import java.sql.SQLException;

import org.w3c.dom.Element;

public class Condition extends Titles {

	protected boolean checkConditionBoolean(Element root,String[] condition ) {
		String[]pass={ condition[0]};
        String[]types= columType(root,pass);
        String[]proberties={condition[2]};
        if (checkValueBoolean(types, proberties, 0)){
        	System.out.println(" invald type for condition");
        	try {
				throw new SQLException();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//return false;
        	}
        return true;
	}
	protected boolean checkValueBoolean(String[]types,String[] proberties,int y){
		if(types[y].equalsIgnoreCase("int") ){
			if(!isNumeric(proberties[y])){
				System.out.println("invalid value for int");
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	 protected boolean isNumeric(String str)  
	 {  
	   try  
	   {  
	     int d = Integer.parseInt(str); 
	   }  
	   catch(NumberFormatException nfe)  
	   {  
		   try {
				throw new SQLException();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }  
	   return true;  
	 }
	 
	 public boolean  isFloat( String str) {
		 try {
			 float d = Float.parseFloat(str); 
		} catch (Exception e) {
			// TODO: handle exception
			try {
				throw new SQLException();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return true ;
	}     
	 public boolean checkDate(String date){
		 Validate b=new Validate();
			if(date==null){
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			date=b.TrimCommand(date);
			date=b.Trim_end(date);
			if(date==null){
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(date.contains(" ")){
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!date.contains("-")){
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			String year=date.substring(0,date.indexOf("-"));
			date=date.substring(date.indexOf("-"));
	        if(date.length()<=1||date.charAt(1)=='-'){
	        	try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			date=date.substring(1);
			if(!date.contains("-")){
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			String month=date.substring(0,date.indexOf("-"));
			date=date.substring(date.indexOf("-"));
			if(date.length()<=1){
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			String day=date.substring(1);
			try{
			Integer.parseInt(year);
			Integer.parseInt(month);
			Integer.parseInt(day);
			
			}
			catch(Exception e){
				try {
					throw new SQLException();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			if(year.length()!=4||Integer.parseInt(month)<=0||Integer.parseInt(month)>12||Integer.parseInt(day)<=0||Integer.parseInt(day)>31){
				try {
					throw new SQLException();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
		}
	 public boolean inspectColum(String[]headers, String colum) {
			for (int j = 0; j < headers.length; j++) {
				if (colum.equalsIgnoreCase(headers[j])){
					location=j;
					return true;
					}
			}
			try {
				throw new SQLException();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	 private int location=0;
	 private String type="";
	 public boolean inspectColumCondition(String[]headers, String colum,String[]ArrayOfTypes) {
			
			for (int j = 0; j < headers.length; j++) {
				if (colum.equalsIgnoreCase(headers[j])){
					location=j;
					type= ArrayOfTypes[j];
					return true;
					
					}
			}
			try {
				throw new SQLException();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	 public String getType() {
		return type;
	}
	 public int getlocation() {
		 return location;
		
	}
		public boolean  inspectType(String type , String value) {
			if (type.equalsIgnoreCase("int")) {
			  return isNumeric(value);
			
			}else if(type.equalsIgnoreCase("float")) {
				return isFloat(value);
				
			}else if(type.equalsIgnoreCase("date")) {
				return checkDate(value);
				
				
			}
			return true;
		}
}
