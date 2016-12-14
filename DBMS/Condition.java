package eg.edu.alexu.csd.oop.DBMS;

import java.sql.Date;

import org.w3c.dom.Element;

public class Condition extends Titles {

	protected boolean checkConditionBoolean(Element root,String[] condition ) {
		String[]pass={ condition[0]};
        String[]types= columType(root,pass);
        String[]proberties={condition[2]};
        if (checkValueBoolean(types, proberties, 0)){
        	System.out.println(" invald type for condition");
        	return false;}
        return true;
	}
	protected boolean checkValueBoolean(String[]types,String[] proberties,int y){
		if(types[y].equalsIgnoreCase("int") ){
			if(!isNumeric(proberties[y])){
				System.out.println("invalid value for int");
				return true;
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
	     return false;  
	   }  
	   return true;  
	 }
	 
	 public boolean  isFloat( String str) {
		 try {
			 float d = Float.parseFloat(str); 
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true ;
	}     
	 public boolean checkDate(String date){
		 Validate b=new Validate();
			if(date==null){
				return false;
			}
			
			date=b.TrimCommand(date);
			date=b.Trim_end(date);
			if(date==null){
				return false;
			}
			if(date.contains(" ")){
				return false;
			}
			if(!date.contains("-")){
				return false;
			};
			String year=date.substring(0,date.indexOf("-"));
			date=date.substring(date.indexOf("-"));
	        if(date.length()<=1||date.charAt(1)=='-'){
	        	return false;
	        }
			date=date.substring(1);
			if(!date.contains("-")){
				return false;
			};
			String month=date.substring(0,date.indexOf("-"));
			date=date.substring(date.indexOf("-"));
			if(date.length()<=1){
	        	return false;
	        }
			String day=date.substring(1);
			try{
			Integer.parseInt(year);
			Integer.parseInt(month);
			Integer.parseInt(day);
			
			}
			catch(Exception e){
				return false;
			}
			if(year.length()!=4||Integer.parseInt(month)<=0||Integer.parseInt(month)>12||Integer.parseInt(day)<=0||Integer.parseInt(day)>31){
				return false;
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
