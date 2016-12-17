package eg.edu.alexu.csd.oop.DBMS;

import java.util.ArrayList;

public class Insert extends Validate {
	boolean Executed;

	private static int counter  = 0;
	public boolean GetExecuted() {
		return Executed;
	}

	public int Insert(Boolean IsDBFound, String CurrentUsedDB, String GetRestSentence ,Queries query , XmlValidation Detect) {
		if (GetRestSentence.contains("table_name5") && counter == 1)
			throw new RuntimeException("hi :D" + GetRestSentence);
		counter++;
		this.Query = query;
		Executed = false;
		this.Detect = Detect;

		int UpdateCount = 0;
		DBfound = IsDBFound;
		GetRest = GetRestSentence;
		CurrentlyUsedDB = CurrentUsedDB;

		Columns = new ArrayList<String>();
		Values = new ArrayList<String>();
		if (CheckInsertSyntax(GetRest)) {
			if (space(getf_rest(GetRest)) && !getf_rest(GetRest).equalsIgnoreCase("values")) {
				UpdateCount = InsertWithColumns();
			} else if (space(GetBeforeValues(GetRest)) && !getf_rest(GetRest).equalsIgnoreCase("values")) {
				UpdateCount = InsertWithoutColumns();
			} else
				System.out.println("Invalid Command.complete the sql");
		} else
			System.out.println("Invalid Command.check syntax");

		return UpdateCount;
	}

	private int CallInsertMethod(String TableName) {
		int UpdateCount = 0;
		String[] FinalColumns = new String[Columns.size()];
		String[] FinalValues = new String[Values.size()];
		for (int i = 0; i < Columns.size(); i++) {
			FinalColumns[i] = Columns.get(i);
			FinalValues[i] = Values.get(i);
		}
	
		if (Detect.DetectTable(CurrentlyUsedDB, TableName)) {
			
			UpdateCount = Query.insertSub(CurrentlyUsedDB, TableName, FinalColumns, FinalValues);
			Executed = true;
		} else
			System.out.println("Invalid Table Name.");

		return UpdateCount;
	}

	private int InsertWithColumns() {
		int UpdateCount = 0;
		String TableName = getf_rest(GetRest);
		Columns = new ArrayList<String>();
		Values = new ArrayList<String>();
		GetRest = GetRest.substring(GetRest.indexOf('('), GetRest.length());
		if (space(TableName) && CheckName(TableName) && check_validname(TableName)) {
			String ColumnsBracket = GetColumnsBracket(GetRest);
			String ValuesBracket = new String();
			if (!ColumnsBracket.equals("()") && GetRest.contains("(") && GetRest.contains(")")
					&& getf_rest(GetRest).equalsIgnoreCase("values") && GetRest.indexOf(")") == GetRest.length() - 1) {
				boolean CorrectColumns = GetColumns(ColumnsBracket);
				ValuesBracket = GetRest.substring(GetRest.indexOf("("), GetRest.indexOf(")") + 1);
				boolean CorrectValues = GetValues(ValuesBracket);
				if (CorrectColumns && CorrectValues && Values.size() == Columns.size()) {
					UpdateCount = CallInsertMethod(TableName);
				} else
					System.out.println("Invalid Command check values or columns");
			} else {
				System.out.println("Invalid Command.check syntax");
			}
		}

		return UpdateCount;
	}

	private int InsertWithoutColumns() {
		int UpdateCount = 0;
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
				if (Detect.DetectTable(CurrentlyUsedDB, TableName)) {
					UpdateCount=Query.insertRow(CurrentlyUsedDB, TableName, FinalValues);
					Executed = true;
					System.out.println("Inserted.");
				} else {
					System.out.println("Invalid Table Name.table is not in the database!");
				}
			} else
				System.out.println("Invalid Command check values");
		} else
			System.out.println("Invalid Command check syntax");

		return UpdateCount;
	}

}
