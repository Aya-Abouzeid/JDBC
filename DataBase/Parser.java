package eg.edu.alexu.csd.oop.DataBase;
 
import java.util.ArrayList;
 
public class Parser {
	Queries Query = new Queries();
    private String Sql = new String();
    protected String FirstWord;
	protected String GetRest = new String();
    protected String CurrentlyUsedDB = new String();
    protected boolean DBfound = false;
    Insert InsertObject = new Insert();
    Create CreateObject = new Create();
    Delete DeleteObject = new Delete();
    Drop DropObject = new Drop();
    Insert insertObject = new Insert();
    Select SelectObject = new Select();
    Update UpdateObject = new Update();
    
    protected void ChangeToLowerCase(String command) {
        if (!command.equals("")) {
            Sql = command.toLowerCase();
            Sql = TrimCommand(Sql);
            Sql = Trim_end(Sql);
            Sql = RemoveSC(command);
            Sql = Trim_end(Sql);
            FirstWord = GetFirstWord(Sql);
                  ChooseQuery(FirstWord);
        } else
            System.out.println("Enter SQL Statement");
    }
 
    private String RemoveSC(String command) {
        String Edited = command;
        if (command.charAt(command.length() - 1) == ';') {
            Edited = command.substring(0, command.length() - 1);
 
        }
        return Edited;
    }
 
    private String TrimCommand(String command) {
        if (command == null) {
            return null;
        }
        int i = 0;
        String Editedsql = command;
        while (i != command.length() && command.charAt(i) == ' ') {
 
            if (i + 1 != command.length()) {
                i++;
                Editedsql = command.substring(i);
 
            } else
                break;
        }
        return (Editedsql);
 
    }
   
    private String Trim_end(String command) {
        if (command == null) {
 
            System.out.println("invalid command");
 
            return null;
        }
 
        int i = command.length() - 1;
        String Editedsql = command;
        while (i != -1 && command.charAt(i) == ' ') {
 
            Editedsql = command.substring(0, i);
 
            i--;
        }
        return (Editedsql);
 
    }
  
    private String GetFirstWord(String sql) {
        if (sql == null) {
            return null;
        }
        String GetFirstWord = new String();
        int i = 0;
        while (i != sql.length() && sql.charAt(i) != ' ' && sql.charAt(i) != '*') {
            GetFirstWord = GetFirstWord + sql.charAt(i);
            i++;
        }
 
        if (i == sql.length()) {
            GetRest = null;
        } else {
 
            GetRest = TrimCommand(sql.substring(i));
            GetRest = Trim_end(GetRest);
        }
        return GetFirstWord;
    }
 
    private void ChooseQuery(String FirstWord) {
        switch (FirstWord) {   case "create":
            CreateObject.Create(DBfound, CurrentlyUsedDB, GetRest); break;
        case "drop":{
            DropObject.Drop(DBfound, CurrentlyUsedDB, GetRest);
            DBfound = DropObject.NewDpFound();  }     break;
        case "insert":
            InsertObject.Insert(DBfound, CurrentlyUsedDB, GetRest); break;
        case "delete":
            DeleteObject.Delete(DBfound, CurrentlyUsedDB, GetRest);  break;
        case "use":
            Use();  break;
        case "update":
            UpdateObject.Update(DBfound, CurrentlyUsedDB, GetRest); break;
        case "select":
            SelectObject.Select(DBfound, CurrentlyUsedDB, GetRest);  break;
        case "ls":
            List();     break;
        default:
            System.out.println("Invalid Command.");
        }    }
 
    
    private void List(){
    	String Name = GetRest;
    	 
       if(GetRest.equals("tables")){
        	 if(DBfound)
        	Query.ListTables(CurrentlyUsedDB);
        	 else
        	     System.out.println("Select Valid DataBase.");

        }
        else {
            System.out.println("Invalid Command.");
        }
    }
    private void Use() {
        String Name = GetRest;
 
        if (Query.DetectDataBase(Name)) {
 
            CurrentlyUsedDB = Name;
            DBfound = true;
            System.out.println("database found");
        } else {
            System.out.println("Invalid DataBase Name.");
            DBfound = false;
        }
 
    }
   
 
    
}
