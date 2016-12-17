package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class Update extends Validate {
	boolean Executed;
	public boolean GetExecuted(){
		return Executed;
	}
	public int Update(Boolean IsDBFound , String CurrentUsedDB,String GetRestSentence , Queries query,XmlValidation Detect) {
		if (GetRestSentence.contains("table_name5"))
			throw new RuntimeException("hi :D" + GetRestSentence);
		
		Executed = false;
		this.Query = query;
		this.Detect = Detect;

		int UpdateCount = 0;
		differ=true;
		 DBfound = IsDBFound;
		 GetRest = GetRestSentence;
		 CurrentlyUsedDB = CurrentUsedDB;
        updated_fields1 = new ArrayList<String>();
        String Rest = GetRest;
        if (Rest != null) {
            if (!Rest.toLowerCase().contains("set")) {  System.out.println("Invalid Command.");
            } else { String tablename = GetFirstWord(Rest);
                if (GetRest != null) {
                    if (!GetFirstWord(GetRest).equalsIgnoreCase("set") || !CheckName(tablename) 
                    		|| !space(tablename)|| !check_validname(tablename)) {
                        System.out.println("Invalid Command.");
                    } else {Rest = GetRest;
                        if (Rest!=null&&Rest.toLowerCase().contains("where") && !check_where_state(Rest)) {
                            System.out.println("1Invalid Command.");
                        } else if (Rest!=null&&!Rest.toLowerCase().contains("where")) {
                        	UpdateCount = update_withoutwhere(Rest,tablename);                        	                     
                        } else {
                            if (before_where == null) {  System.out.println("Invalid Command.");
                            } else {
                            	UpdateCount = update_withwhere(tablename);  	                
                            }}}} 
          else {  System.out.println("Invalid command.");
                }}}    
         else {   System.out.println("Invalid command.");
        } 
        
        return UpdateCount;
       
    }
	private int update_withoutwhere(String Rest,String tablename){
		
		int UpdateCount = 0;
		 Rest = TrimCommand(Rest);
	     Rest = Trim_end(Rest);
	     String l = new String();
	     for (int i = 0; i <= Rest.length(); i++) {
	         if (i == Rest.length() || Rest.charAt(i) == ',') {
	             String iterator1 = new String();
	             iterator1 = TrimCommand(l);
	             iterator1 = Trim_end(iterator1);
	             updated_fields1.add(iterator1);

	             l = new String();
	         } else {
	             l = l + Rest.charAt(i);
	         }
	     }
	     String[][] updated_fields2 = new String[updated_fields1.size()][2];
	     updateStatment = new String[3 * updated_fields1.size()];
	     for (int i = 0; i < updated_fields2.length; i++) {
	         updated_fields2[i][0] = updated_fields1.get(i);
	     }
	    if( reform2(updated_fields2) && Detect.DetectTable(CurrentlyUsedDB, tablename)){
	    	UpdateCount=  Query.updateWhitoutWhere(CurrentlyUsedDB, tablename, updateStatment);
	     System.out.println("updated");
	 	 Executed = true;

	     }	 
	    return UpdateCount;
	 }
	 private int update_withwhere(String tablename){
		 int UpdateCount = 0;
		 before_where = TrimCommand(before_where);
	     before_where = Trim_end(before_where);
	     String l = new String();
	     for (int i = 0; i <= before_where.length(); i++) {
	         if (i == before_where.length() || before_where.charAt(i) == ',') {
	             String iterator1 = new String();
	             iterator1 = TrimCommand(l);
	             iterator1 = Trim_end(iterator1);
	             updated_fields1.add(iterator1);
	             l = new String();
	         } else {   l = l + before_where.charAt(i);
	         }
	     }
	     String[][] updated_fields2 = new String[updated_fields1.size()][2];
	     updateStatment = new String[3 * updated_fields1.size()];
	     for (int i = 0; i < updated_fields2.length; i++) {
	         updated_fields2[i][0] = updated_fields1.get(i);
	     }
	    if( reform2(updated_fields2) && Detect.DetectTable(CurrentlyUsedDB, tablename)){
		     System.out.println("updated");

		     UpdateCount= Query.update(CurrentlyUsedDB, tablename, condition, updateStatment);
	     Executed = true;
	     }
	    return UpdateCount;
	 }
	    
}
