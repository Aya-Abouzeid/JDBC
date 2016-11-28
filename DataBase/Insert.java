package eg.edu.alexu.csd.oop.DataBase;

import java.util.ArrayList;

public class Insert extends Validate{
	 protected void Insert(Boolean IsDBFound , String CurrentUsedDB,String GetRestSentence) {
		 DBfound = IsDBFound;
		 GetRest = GetRestSentence;
		 CurrentlyUsedDB = CurrentUsedDB;
	        if (DBfound) {
	          
	            Columns = new ArrayList<String>();
	            Values = new ArrayList<String>();
	            if (CheckInsertSyntax(GetRest)) {
	                if (space(getf_rest(GetRest)) && !getf_rest(GetRest).equals("values")) {
	                    InsertWithColumns();
	                } else if (space(GetBeforeValues(GetRest)) && !getf_rest(GetRest).equals("values")) {
	                    InsertWithoutColumns();
	                } else
	                    System.out.println("Invalid Command.");
	            } else
	                System.out.println("Invalid Command.");
	        } else
	            System.out.println("Select Valid DataBase.");
	    }
	private void CallInsertMethod(String TableName){
	   
	    String[] FinalColumns = new String[Columns.size()];
	    String[] FinalValues = new String[Values.size()];
	    for (int i = 0; i < Columns.size(); i++) {
	        FinalColumns[i] = Columns.get(i);
	        FinalValues[i] = Values.get(i);
	    }
	    Query.insertSub(CurrentlyUsedDB, TableName, FinalColumns, FinalValues);
	}
	    private void InsertWithColumns() {
	        String TableName = getf_rest(GetRest);
	        Columns = new ArrayList<String>();
	        Values = new ArrayList<String>();
	        GetRest = GetRest.substring(GetRest.indexOf('('), GetRest.length());
	        if (space(TableName) && CheckName(TableName) && check_validname(TableName)) {
	            String ColumnsBracket = GetColumnsBracket(GetRest);
	            String ValuesBracket = new String();
	            if (!ColumnsBracket.equals("()") && GetRest.contains("(") && GetRest.contains(")")
	                    && getf_rest(GetRest).equals("values") && GetRest.indexOf(")") == GetRest.length() - 1) {
	                boolean CorrectColumns = GetColumns(ColumnsBracket);
	                ValuesBracket = GetRest.substring(GetRest.indexOf("("), GetRest.indexOf(")") + 1);
	                boolean CorrectValues = GetValues(ValuesBracket);
	                if (CorrectColumns && CorrectValues && Values.size() == Columns.size()) {
	                    CallInsertMethod(TableName);
	                } else
	                    System.out.println("Invalid Command");
	            } else {
	                System.out.println("Invalid Command.");
	            }} }
	 
	    private void InsertWithoutColumns() {
	        String TableName = new String();
	        Values = new ArrayList<String>();
	        TableName = GetBeforeValues(GetRest);
	        GetRest = GetRest.substring(GetRest.indexOf('('), GetRest.length());
	        if (!GetRest.equals("()") && GetRest.contains("(") && GetRest.contains(")") && space(TableName)
	                && CheckName(TableName) && check_validname(TableName) && GetRest.indexOf(')') == GetRest.length() - 1) {
	            boolean CorrectValues = GetValues(GetRest);
	            if (CorrectValues) {
	                String[] FinalValues = new String[Values.size()];
	                for (int i = 0; i < Values.size(); i++) {
	                    FinalValues[i] = Values.get(i);
	                }
	                Query.insertRow(CurrentlyUsedDB, TableName, FinalValues);
	            } else
	                System.out.println("Invalid Command");
	        } else
	            System.out.println("Invalid Command");
	    }
	 
}
