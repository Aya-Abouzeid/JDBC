package eg.edu.alexu.csd.oop.DBMS;
 
import java.util.ArrayList;
 
public class Parser  {
	protected String JDBCdir;

	XmlValidation Detect = new XmlValidation();
	
	   private String Sql = new String();
    protected String FirstWord;
	protected String GetRest = new String();
    protected String CurrentlyUsedDB = new String();
    protected boolean DBfound = false;
   public String GetCurrentDB(){
	   return CurrentlyUsedDB;
   }
   public Boolean GetDBfound(){
	   return DBfound;
   }
   public String GetGetRest(){
	   return GetRest;
   }
    public String Parse(String command) {
        if (!command.equals("")) {

            Sql = TrimCommand(command);
            Sql = Trim_end(Sql);
            
            Sql = RemoveSC(Sql);
            Sql = Trim_end(Sql);

            FirstWord = GetFirstWord(Sql);
            return FirstWord;

//                  
        } else{
            System.out.println("Enter SQL Statement");
         return null;   
        }
        
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
        while (i != command.length() && Character.isWhitespace(command.charAt(i))) {
 
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
        while (i != -1 && Character.isWhitespace(command.charAt(i))) {
 
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
        while (i != sql.length() && !Character.isWhitespace(sql.charAt(i)) && sql.charAt(i) != '*') {
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
 
    
    public void SetDBFound(boolean setter){
    
    	DBfound = setter;
    }
  
    public void Use() {
        String Name = GetRest;
 
        if (Detect.DetectDataBase(Name)) {
        	
            CurrentlyUsedDB = Name;
            DBfound = true;
            System.out.println("database found");
        } else {
            System.out.println("Invalid DataBase Name.");
            DBfound = false;
        }
 
    }
   
 
    
}
