package eg.edu.alexu.csd.oop.DBMS;

import java.sql.SQLException;

public class Drop extends Validate {
	boolean Executed ;
	public boolean GetExecuted(){
		return Executed;
	}

	protected Boolean DBfound2 = DBfound;
	 public int Drop(Boolean IsDBFound , String CurrentUsedDB,String GetRestSentence , Queries query ,  XmlValidation Detect) throws SQLException {
			this.Query = query;
this.Detect = Detect;
		 Executed = false;
		 int UpdateCount = 0;
		 DBfound = IsDBFound;
		 GetRest = GetRestSentence;
		 CurrentlyUsedDB = CurrentUsedDB;
	        String j = GetFirstWord(GetRest);
	        String Rest = GetRest;
	        if (j.equalsIgnoreCase("database")) {
	        	UpdateCount =   DropDataBase(Rest);
	        } else if (j.equalsIgnoreCase("table")) {
	        	UpdateCount =  DropTable(Rest);
	        } else
	            System.out.println("Invalid Command.");
	 
	        return UpdateCount;
	 }
	 	public Boolean NewDpFound(){
	 		return DBfound2;
	 	}
	    private int DropDataBase(String Rest) {
	    	int UpdateCount = 0;
	        if (GetRest != null && GetFirstWord(GetRest).equals(Rest)) {
	            if (!Detect.DetectDataBase(Rest.toLowerCase())){
	            	DBfound2 = DBfound;
	                System.out.println("DataBase Not Found.");
	            }
	            else
	            { DBfound2 = false;
	            	Query.dropDatabase(Rest.toLowerCase());
	            	Executed = true;
	            }
	        } else {
            	DBfound2 = DBfound;
	            System.out.println("Invalid Command.");
	        }
	 return UpdateCount ;
	    }
	 
	    private int DropTable(String Rest) throws SQLException {
	 int UpdateCount = 0;
	        if (GetRest != null && GetFirstWord(GetRest).equals(Rest)) {
	            if (!DBfound)
	                System.out.println("Select a Valid DataBase First");
	            else if (!Detect.DetectTable(CurrentlyUsedDB, Rest)) {
	                System.out.println("Invalid Command. Table was not found.");
	            } else
	            {
	                Query.dropTable(CurrentlyUsedDB, Rest);
	                Executed = true;
	    			System.out.println("Table is Dropped");
	            }
	        } else {
	            System.out.println("Invalid Command.");
	        }
	 return UpdateCount;
	    }

	  
}
