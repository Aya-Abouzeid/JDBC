package eg.edu.alexu.csd.oop.DBMS;

public class Alter extends Validate{
	boolean Executed;
	String tablename=null;
	public boolean GetExecuted(){
		return Executed;
	}
	public int Alter(Boolean IsDBFound, String CurrentUsedDB, String GetRestSentence , Queries query) {
		this.Query = query;

		Executed = false;
		int UpdateCount = 0;
		DBfound = IsDBFound;
		GetRest = GetRestSentence;
		CurrentlyUsedDB = CurrentUsedDB;
		
			String Rest = GetRest;
			if (Rest != null) {
				if (GetFirstWord(Rest).equalsIgnoreCase("table")) {
					
					Rest = TrimCommand(GetRest);
					if(!(Rest==null)){
						
					if(Rest.toLowerCase().contains("add")){
						UpdateCount = alterAdd(Rest);
					}
					else if(Rest.toLowerCase().contains("drop")){
						UpdateCount = alterdrop(Rest);
					}
					else
					{
						System.out.println("Invalid Command.");		
					}
					}
					else{
					System.out.println("Invalid Command.");		
					}
					
				} else {
					System.out.println("Invalid Command.");	
				}
			} else{
				System.out.println("Invalid Command.");
		}
			
			
		return UpdateCount;
		
	}

	private int alterdrop(String rest) {
		int UpdateCount = 0;
		tablename=GetBeforeDrop(rest);
		if(tablename!=null && space(tablename)
                && CheckName(tablename) && check_validname(tablename)){
			if(GetFirstWord(GetRest).equalsIgnoreCase("drop")){
				if(GetRest==null){
			    System.out.println("Invalid Command.");	
				}
				else{
					UpdateCount = checkColumn("drop");
				}
			}
			else{
	   System.out.println("Invalid Command.");		
			}	
		}
		else
		{
		System.out.println("Invalid Command.");	
		}
		return UpdateCount;
	}

	private int checkColumn(String g) {
		int UpdateCount =  0;
     if(g.equalsIgnoreCase("add")){
   String columnName= GetFirstWord(GetRest);
   if(GetRest==null||!(GetRest.equalsIgnoreCase("int")||GetRest.equalsIgnoreCase("varchar")||
		   GetRest.equalsIgnoreCase("float")||GetRest.equalsIgnoreCase("date"))){
	   System.out.println("Invalid Command.");	  
   }
   else{
	   if(!check_validname(tablename)||!space(tablename) ||! CheckName(tablename)||
			   !check_validname(columnName)||!space(columnName) ||! CheckName(columnName) ){
			 System.out.println("Invalid Command.");		 
		 }
		 else
		 { 	String DataType = GetFirstWord(GetRest);
		 if(GetRest == null){
			 // UpdateCount =call Queries.alterdrop(tablename,columnName,GetRest);
			 Executed = true;
		 }
		 else
			 System.out.println("Invalid Command.");
		 }
   }
     } 
     else {
    	 if(GetFirstWord(GetRest).equalsIgnoreCase("column")&&GetRest!=null){
    		 if(!check_validname(tablename)||!space(tablename) ||
    				 ! CheckName(tablename)||!check_validname(GetRest)||!space(GetRest) ||! CheckName(GetRest) ){
    			 System.out.println("Invalid Command.");		 
    		 }
    		 else
    		 {
    			 	String ColumnName = GetFirstWord(GetRest);
    				 if(GetRest == null){

    			 //  UpdateCount = call Queries.alterdrop(tablename,ColumnName);
    				 Executed= true;
    				 }
    				 else
    					 System.out.println("Invalid Command.");
    		 }
    		 
    	 }
    	 else {
    	System.out.println("Invalid Command.");		 
    	 }
     }
		
		return UpdateCount;
	}

	private String GetBeforeAdd(String rest) {
		if(rest.toLowerCase().indexOf("add")==0){
			return null;
		}
       String g=rest.substring(0,rest.toLowerCase().indexOf("add"));
		g=TrimCommand(g);
		g=Trim_end(g);
		GetRest=rest.substring(rest.toLowerCase().indexOf("add"));
		if(g==null){
			return null;
		}
		return g;
	}

	private int alterAdd(String rest) {
		int UpdateCount = 0;
		tablename=GetBeforeAdd(rest);
        if(tablename!=null  && space(tablename)
                && CheckName(tablename) && check_validname(tablename)){
			if(GetFirstWord(GetRest).equalsIgnoreCase("add")){
				if(GetRest==null){
			    System.out.println("Invalid Command.");	
				}
				else{
					UpdateCount = checkColumn("add");
				}
			}
			else{
	   System.out.println("Invalid Command.");		
			}
		}
		else
		{
		System.out.println("Invalid Command.");	
		}
        return  UpdateCount;
	}

	private String GetBeforeDrop(String rest) {
		if(rest.toLowerCase().indexOf("drop")==0){
			return null;
		}
       String g=rest.substring(0,rest.toLowerCase().indexOf("drop"));
		g=TrimCommand(g);
		g=Trim_end(g);
		GetRest=rest.substring(rest.toLowerCase().indexOf("drop"));
		if(g==null){
			return null;
		}
		return g;
		
	}
}
