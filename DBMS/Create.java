package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class Create extends Validate {
	boolean Executed;
	boolean DBexist;
	boolean tableexist;
	boolean db;
	boolean table;

	public boolean GetExecuted() {
		System.out.println(Executed);	

		return Executed;
	}

	public boolean GetDBexist() {
		return DBexist;
	}

	public boolean Gettableexist() {
		return tableexist;
	}

	public boolean Getdb() {
		return db;
	}

	public boolean Gettable() {
		return table;
	}

	public int Create(Boolean IsDBFound, String CurrentUsedDB, String GetRestSentence, Queries query,
			XmlValidation Detect) {
		this.Query = query;
		int UpdateCount = 0;
		this.Detect = Detect;
		DBexist = false;
		DBfound = IsDBFound;
		GetRest = GetRestSentence;
		CurrentlyUsedDB = CurrentUsedDB;
		Executed = false;
		tableexist = false;
		db = false;
		table = false;
		if (GetRest != null) {

			String j = GetFirstWord(GetRest);
			String Rest = GetRest;
			if (j.equalsIgnoreCase("database")) {
				db = true;
				UpdateCount = CreateDataBase(Rest);
			} 
			if (j.equalsIgnoreCase("table")) {
				table = true;
			} 
			if (GetRest != null && j.equalsIgnoreCase("table") && GetRest.contains("(")
					&& GetRest.contains(")")) {
				UpdateCount = CreateTable(Rest);
			} else {
				System.out.println("Invalid Command.complete the sql");
			}
		} else {
			System.out.println("Invalid Command.complete the sql");

		}
		return UpdateCount;
	}

	private int CreateDataBase(String Rest) {
		db = true;
		int UpdateCount = 0;
		if (!CheckName(Rest) || !check_validname(Rest) || !space(Rest) || Rest.charAt(0) == '_') {
			System.out.println("Invalid Name.");
		}
		if (Detect.DetectDataBase(Rest.toLowerCase()))
			DBexist = true;

		if (GetFirstWord(GetRest).equals(Rest) && !Detect.DetectDataBase(Rest.toLowerCase())) {

			Query.createDatabase(Rest.toLowerCase());
			Executed = true;

		} else {
			System.out.println("Invalid Command.Check database name");
		}
		return UpdateCount;
	}

	private int CreateTable(String Rest) {
		table = true;
		int UpdateCount = 0;
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
						if (Detect.DetectTable(CurrentlyUsedDB, TableName))
							tableexist = true;

						if (reform(fields2) && !Detect.DetectTable(CurrentlyUsedDB, TableName)) {
							Query.creatTable(CurrentlyUsedDB, TableName, fields3);
							Executed = true;
							// System.out.println("Table is Created");
						}

					} else {
						System.out.println("Invalid command.check Syntax");
					}
				} else {
					System.out.println("Invalid Command.complete the sql");
				}
			} else {
				System.out.println("Invalid Command.complete the sql");
			}
		} else
			System.out.println("Select a Database first.");

		return UpdateCount;
	}

}
