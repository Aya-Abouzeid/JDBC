package eg.edu.alexu.csd.oop.DataBase;

public class Delete extends Validate{

	 protected void Delete(Boolean IsDBFound , String CurrentUsedDB,String GetRestSentence ) {
		 DBfound = IsDBFound;
		 GetRest = GetRestSentence;
		 differ=false;
		 CurrentlyUsedDB = CurrentUsedDB;
	    	if(DBfound){
	        del = true;
	        String Rest = GetRest;
	        Rest = TrimCommand(Rest);
	        if (Rest==null||(GetRest.contains("where") && GetRest.contains("*"))) {
	            System.out.println("Invalid Command.");
	        } else {
	            Rest = TrimCommand(Rest.replace("*", " "));
	            boolean s1 = check_from_state(Rest);
	            boolean s2 = true;
	            if (Rest == null) {
	                System.out.println("Invalid Command.");
	            } else {
	            	boolean withwhere=false;
	                if (Rest.contains("where")) {
	                	withwhere=true;
	                    s2 = check_where_state(Rest);
	                }
	                if (s1 == false || s2 == false) {
	                    System.out.println("Invalid Command.");
	                }
	                else {
	                	if(withwhere){	                		Query.deleteSubTable(CurrentUsedDB, current_table1, condition);
	            			System.out.println("Done.");

	                	}
	                	else{
	                		Query.deleteTable(CurrentUsedDB, current_table1);
	            			System.out.println("Table is Deleted");
	                		}
	                }
	            }
	            del = false;
	        }}
	    	else{
	    		System.out.println("select valid database first");

	    	}
	    }
}
