package eg.edu.alexu.csd.oop.DataBase;

import java.util.ArrayList;

public class Update extends Validate {
	protected void Update(Boolean IsDBFound , String CurrentUsedDB,String GetRestSentence) {
		 DBfound = IsDBFound;
		 GetRest = GetRestSentence;
		 CurrentlyUsedDB = CurrentUsedDB;
    	if(DBfound){
        updated_fields1 = new ArrayList<String>();
        String Rest = GetRest;
        if (Rest != null) {
            if (!Rest.contains("set")) {  System.out.println("Invalid Command.");
            } else { String tablename = GetFirstWord(Rest);
                if (GetRest != null) {
                    if (!GetFirstWord(GetRest).equals("set") || !CheckName(tablename) || !space(tablename)|| !check_validname(tablename)) {
                        System.out.println("Invalid Command.");
                    } else {Rest = GetRest;
                        if (Rest!=null&&Rest.contains("where") && !check_where_state(Rest)) {
                            System.out.println("Invalid Command.");
                        } else if (Rest!=null&&!Rest.contains("where")) {
                        	update_withoutwhere(Rest,tablename);                        	                     
                        } else {
                            if (before_where == null) {  System.out.println("Invalid Command.");
                            } else {
                            	update_withwhere(tablename);  	                
                            }}}} 
          else {  System.out.println("Invalid command.");
                }}}    
         else {   System.out.println("Invalid command.");
        } 
        }
    	else{
    		System.out.println("select valid database first");
    	}
    }
	private void update_withoutwhere(String Rest,String tablename){
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
	     reform2(updated_fields2);
	     Query.updateWhitoutWhere(CurrentlyUsedDB, tablename, updateStatment);	 
	 }
	 private void update_withwhere(String tablename){
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
	     reform2(updated_fields2);
	     // Query.updateWhitWhere(CurrentlyUsedDB,
	     // tablename, updateStatment,condition);
	 }
	    
}
