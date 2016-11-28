package eg.edu.alexu.csd.oop.DataBase;

public class Drop extends Validate {
	protected Boolean DBfound2 = DBfound;
	 protected void Drop(Boolean IsDBFound , String CurrentUsedDB,String GetRestSentence) {
		 DBfound = IsDBFound;
		 GetRest = GetRestSentence;
		 CurrentlyUsedDB = CurrentUsedDB;
	        String j = GetFirstWord(GetRest);
	        String Rest = GetRest;
	        if (j.equals("database")) {
	            DropDataBase(Rest);
	        } else if (j.equals("table")) {
	            DropTable(Rest);
	        } else
	            System.out.println("Invalid Command.");
	    }
	 	protected Boolean NewDpFound(){
	 		return DBfound2;
	 	}
	    private void DropDataBase(String Rest) {
	        if (GetRest != null && GetFirstWord(GetRest).equals(Rest)) {
	            if (!Query.DetectDataBase(Rest)){
	            	DBfound2 = DBfound;
	                System.out.println("DataBase Not Found.");
	            }
	            else
	            { DBfound2 = false;
	            	Query.dropDatabase(Rest);
	            }
	        } else {
            	DBfound2 = DBfound;
	            System.out.println("Invalid Command.");
	        }
	 
	    }
	 
	    private void DropTable(String Rest) {
	 
	        if (GetRest != null && GetFirstWord(GetRest).equals(Rest)) {
	            if (!DBfound)
	                System.out.println("Select a Valid DataBase First");
	            else if (!Query.DetectTable(CurrentlyUsedDB, Rest)) {
	                System.out.println("Invalid Command. Table was not found.");
	            } else
	                Query.dropTable(CurrentlyUsedDB, Rest);
	        } else {
	            System.out.println("Invalid Command.");
	        }
	 
	    }

	  
}
