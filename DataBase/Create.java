package eg.edu.alexu.csd.oop.DataBase;

import java.util.ArrayList;

public class Create extends Validate {
	 protected void Create(Boolean IsDBFound , String CurrentUsedDB,String GetRestSentence ) {
		 DBfound = IsDBFound;
		 GetRest = GetRestSentence;
		 CurrentlyUsedDB = CurrentUsedDB;
		 
	        if (GetRest != null) {
	 
	            String j = GetFirstWord(GetRest);
	            String Rest = GetRest;
	            if (j.equals("database")) {
	                CreateDataBase(Rest);
	            } else if (GetRest != null && j.equals("table") && GetRest.contains("(") && GetRest.contains(")")) {
	                CreateTable(Rest);
	            } else {
	                System.out.println("Invalid Command.");
	            }
	        } else {
	            System.out.println("Invalid Command.");
	 
	        }
	    }
	 private void CreateDataBase(String Rest) {
	        if (!CheckName(Rest) || !check_validname(Rest) || !space(Rest)) {
	            System.out.println("Invalid Name.");
	 
	        } else if (GetFirstWord(GetRest).equals(Rest)) {
	            Query.createDatabase(Rest);
	        } else {
	            System.out.println("Invalid Command.");
	        }
	    }
	 private void CreateTable(String Rest) {
	        if (DBfound) {
	            fields1 = new ArrayList<String>();
	            GetRest = Rest.substring(Rest.indexOf('('));
	            String TableName = getf_rest(Rest);
	 
	            if (TableName != null && !TableName.equals("")) {
	                Rest = TrimCommand(GetRest);
	                String l = new String();
	                if (space(TableName) && CheckName(TableName) && check_validname(TableName)) {
	 
	                    for (int i = 1; i < Rest.length(); i++) {
	                        if (Rest.charAt(i) == ',' || Rest.charAt(i) == ')') {
	                            String iterator1 = new String();
	 
	                            iterator1 = TrimCommand(l);
	                            iterator1 = Trim_end(iterator1);
	                            fields1.add(iterator1);
	                            l = new String();
	                        } else {
	                            l = l + Rest.charAt(i);
	                        }
	                    }
	                    fields2 = new String[fields1.size()][2];
	                    for (int i = 0; i < fields2.length; i++) {
	                        fields2[i][0] = fields1.get(i);
	                    }
	                    fields3 = new String[fields1.size()];
	                    if (!GetRest.equals("()") && GetRest.indexOf(")") == GetRest.length() - 1) {
	 
	                        if (reform(fields2)) {
	                            Query.creatTable(CurrentlyUsedDB, TableName, fields3);
	                			System.out.println("Table is Created");

	                        }
	 
	                    } else {
	                        System.out.println("Invalid command.");
	                    }
	                } else {
	                    System.out.println("Invalid Command.");
	                }
	            } else {
	                System.out.println("Invalid Command.");
	            }
	        } else
	            System.out.println("Select a Database first.");
	 
	    }
	 
	   
}
