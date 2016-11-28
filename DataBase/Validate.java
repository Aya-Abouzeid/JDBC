package eg.edu.alexu.csd.oop.DataBase;

import java.util.ArrayList;

public class Validate  {
	Queries Query = new Queries();
	protected String FirstWord;
	protected String GetRest = new String();
	protected boolean decide = false;
	protected boolean differ=false;
	protected boolean d = false;
	protected int space = 0;
	protected boolean del = false;
	protected ArrayList<String> Values = new ArrayList<String>();
	protected ArrayList<String> Columns = new ArrayList<String>();
	protected String[] updateStatment;
	protected String current_table1 = new String();
	protected String[] condition = new String[3];
	protected String before_from = new String();
	protected String before_where = new String();
	protected ArrayList<String> updated_fields1 = new ArrayList<String>();
	protected String[] selected_fields;
	protected ArrayList<String> selected_fields1 = new ArrayList<String>();
	protected String specialchar[] = { "+", "-", "*", "/", ")", "(", "&", "%", "$", "#", "@", "!", "~", ">", "<", "?",
			".", ",", ";", "{", "}", "[", "]" };
	protected String[][] fields2;
	protected String[] fields3;
	protected ArrayList<String> fields1 = new ArrayList<String>();
	protected String CurrentlyUsedDB = new String();
	protected boolean DBfound = false;

	protected String TrimCommand(String command) {
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

	protected String Trim_end(String command) {
		if (command == null) {

			System.out.println("Invalid Command.");

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

	protected boolean check_validname(String name) {
		if (name == null) {
			return false;
		}
		if (name.equals("database") || name.equals("from") || name.equals("select") || name.equals("into")
				|| name.equals("int") || name.equals("varchar") || name.equals("create") || name.equals("update")
				|| name.equals("insert") || name.equals("delete") || name.equals("drop") || name.equals("where")
				|| name.equals("table") || name.equals("values")) {
			return false;
		}
		return true;
	}

	protected String GetFirstWord(String sql) {
		space++;
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

	protected boolean reform(String[][] l) {
		boolean s = true;
		int i = 0;
		while (i < l.length) {
			ms_twowords(i);
			s = ms_secondword(i);
			i++;
			if (s == false) {
				System.out.println("Invalid Command.");
				break;
			}

		}
		return s;
	}

	protected void ms_twowords(int i) {

		fields2[i][0] = GetFirstWord(fields2[i][0]);
		fields2[i][1] = TrimCommand(GetRest);
		fields3[i] = fields2[i][1] + " " + fields2[i][0];
	}

	protected boolean ms_secondword(int i) {
		if (fields2[i][1] != null && (fields2[i][1].equals("int") || fields2[i][1].equals("varchar"))) {
			return true;

		} else {

			return false;
		}
	}

	protected boolean CheckName(String Name) {
		if (Name == null || Name.equals("")) {
			return false;
		}
		for (int i = 0; i < specialchar.length; i++) {
			if (Name.contains(specialchar[i])) {
				return false;
			}
		}
		if (Character.isDigit(Name.charAt(0))) {
			return false;
		}
		return true;
	}

	protected boolean space(String g) {
		if (g == null) {
			return false;
		}
		if (g.contains(" ")) {
			return false;
		} else {
			return true;
		}
	}

	protected String getf_rest(String g) {

		g = g.substring(0, g.indexOf('('));

		String TableName = TrimCommand(g);
		TableName = Trim_end(TableName);
		return TableName;
	}

	protected boolean CheckInsertSyntax(String Rest) {
		if (Rest == null || !GetFirstWord(Rest).equals("into")) {
			return false;
		} else if (!GetRest.contains("values")) {
			return false;
		} else if (!GetRest.contains("(")) {
			return false;
		} else if (!GetRest.contains(")")) {
			return false;
		} else if (!(GetRest.substring(GetRest.indexOf("values"), GetRest.length()).contains("("))) {
			return false;
		} else if (!(GetRest.substring(GetRest.indexOf("values"), GetRest.length()).contains(")"))) {
			return false;
		}
		return true;
	}

	protected String GetBeforeValues(String Rest) {
		if (Rest.contains("values")) {
			Rest = Rest.substring(0, Rest.indexOf("values"));

			String TableName = TrimCommand(Rest);
			TableName = Trim_end(TableName);

			return TableName;
		} else
			return null;

	}

	protected String GetColumnsBracket(String Rest) {
		String Columns = new String();
		Columns = Rest.substring(0, Rest.indexOf(")") + 1);
		GetRest = Rest.substring(Rest.indexOf(")") + 1, Rest.length());
		GetRest = TrimCommand(GetRest);

		return Columns;
	}

	protected boolean GetColumns(String ColumnsBracket) {
		String l = new String();
		for (int i = 1; i < ColumnsBracket.length(); i++) {
			if (ColumnsBracket.charAt(i) == ',' || ColumnsBracket.charAt(i) == ')') {
				String Input = TrimCommand(l);
				Input = Trim_end(Input);
				Columns.add(Input);
				if (Input == null || Input.equals("")) {
					return false;
				}
				l = new String();
			} else {
				l = l + ColumnsBracket.charAt(i);
			}
		}
		return true;
	}

	protected boolean GetValues(String bracket) {
		String l = new String();
		for (int i = 1; i < bracket.length(); i++) {
			if (bracket.charAt(i) == ',' || bracket.charAt(i) == ')') {
				String Input = TrimCommand(l);
				Input = Trim_end(Input);
				if (!check_quotes(Input)) {
					return false;
				}
				if (Input == null || Input.equals("")) {
					return false;
				}
				l = new String();
				Input = remove_quotes(Input);
				Values.add(Input);
			} else {
				l = l + bracket.charAt(i);
			}
		}
		return true;
	}

	 protected boolean reform2(String[][] updated_fields2) {
	        int i = 0;
	        int j=0;
	        while (i < updated_fields2.length) {
	            if (!updated_fields2[i][0].contains("=")) {
	                System.out.println("Invalid Command.");
	                break;
	            } else {
	                String h = updated_fields2[i][0];
	                String o = updated_fields2[i][0];
	                updated_fields2[i][1] =TrimCommand(o.substring(o.indexOf("=") + 1));
	                updated_fields2[i][0] = Trim_end(h.substring(0, h.indexOf("=")));
	                updateStatment[j] = updated_fields2[i][0];
	                updateStatment[j +1] = "=";
	                updateStatment[j +2] = updated_fields2[i][1];
	               
	                if(!check_quotes(updateStatment[j + 2])){
	                	 System.out.println("Invalid Command.");
	                     return false;
	                }
	                if (!space(updated_fields2[i][0]) || !CheckName(updated_fields2[i][0])|| !check_validname(updated_fields2[i][0])) {
	                    System.out.println("Invalid Command.");
	                    return false;
	                } 
	                updateStatment[j + 2]=remove_quotes(updateStatment[j + 2]);
	                if( updateStatment[i + 2].contains("\'")){
	                	 System.out.println("Invalid Command.");
	                	 return false;
	                }
	            }
	            i++;
	            j=j+3;
	            }
	        return true;
	        }
	 
	protected void check_select() {
		int i2 = 0;
		String l = new String();
		for (int i = 0; i <= before_from.length(); i++) {
			if (i == before_from.length() || before_from.charAt(i) == ',') {
				String iterator1 = new String();
				iterator1 = TrimCommand(l);
				iterator1 = Trim_end(iterator1);
				selected_fields1.add(iterator1);
				selected_fields = new String[selected_fields1.size()];
				for (int i1 = 0; i1 < selected_fields.length; i1++) {
					selected_fields[i1] = selected_fields1.get(i1);

				}
				if (!space(selected_fields[i2]) || !CheckName(selected_fields[i2])
						|| !check_validname(selected_fields[i2])) {
					System.out.println("Invalid Command.");
					break;
				}
				i2++;
				l = new String();
			} else {
				l = l + before_from.charAt(i);
			}
		}
	}

	protected boolean check_where_state(String g) {
		String g1 = g;
		if(differ==true){
		String g2=g;
		g=g2.substring(0, g2.indexOf("where"))+" "+g2.substring( g2.indexOf("where"));
		}
		String before = GetFirstWord(g);
		before_where = new String();
		if (before == null) {
			return false;
		}
		while (!before.equals("where")) {
			before_where = before_where + before;
			g = GetRest;
			before = GetRest;

			if (g == null) {
				return false;
			}
			before = GetFirstWord(before);
		}
		before_where = g1.substring(0, g1.indexOf("where"));
		before_where = Trim_end(before_where);
		return (check_equation(GetRest));
	}
	protected boolean check_equation(String getRest2) {
		condition = new String[3];
		if (getRest2 == null) {
			return false;
		}
		String k = TrimCommand(getRest2);
		k = TrimCommand(k);
		if (k.equals(null)) {
			return false;
		}

		String c = new String();
		if (!getRest2.contains("<") && !getRest2.contains(">") && !getRest2.contains("=")) {
			return false;
		} else {
			if (getRest2.contains("<")) {
				c = "<";
			} else if (getRest2.contains(">")) {
				c = ">";
			} else if (getRest2.contains("=")) {
				c = "=";
			} else {
				return false;
			}
			String g1 = k;
			String g2 = k;
			if (k.charAt(0) == c.charAt(0) || k.charAt(k.length() - 1) == c.charAt(0)) {
				return false;
			}
			condition[0] = Trim_end(g1.substring(0, g1.indexOf(c)));
			condition[1] = c;
			condition[2] = TrimCommand(g2.substring(g2.indexOf(c) + 1));
			if (!check_quotes(condition[2])) {
				return false;
			}
			condition[2] = remove_quotes(condition[2]);
			if (condition[2].contains("\'") || !space(condition[0]) || !CheckName(condition[0])
					|| !check_validname(condition[0])) {
				return false;
			}

		}
		return true;
	}

	protected boolean check_quotes(String h) {
		if (h == null || h.equals("")) {
			return false;
		}
		if (!(h.charAt(0) == '\'') || !(h.charAt(h.length() - 1) == '\'')) {
			try {
				d = true;
				Integer.parseInt(h);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		}
		d = false;
		return true;
	}

	protected String remove_quotes(String h) {
		if (d == false) {
			h = TrimCommand(h.substring(1, h.length() - 1));
			h = Trim_end(h);
		}
		return h;
	}

	protected boolean check_from_state(String g) {
		String n = g;
		space = 0;
		String before = GetFirstWord(g);
		before_from = new String();
		if (before == null) {
			return false;
		}
		if (before.equals("from") && decide == false && del == false) {
			return false;
		}
		if (!before.equals("from") && decide == true) {
			return false;
		}
		if (!before.equals("from") && del == true) {
			return false;
		}
		while (!before.equals("from")) {
			before_from = before_from + before;
			g = GetRest;
			before = GetRest;
			if (g == null || (!n.contains(",") && space > 1)) {
				return false;
			}
			before = GetFirstWord(before);
		}
		if (GetRest != null && GetRest.contains("where")) {
			String k = GetRest;
			String b = TrimCommand(k.substring(0, k.indexOf("where")));
			b = Trim_end(b);
			if (!space(b) || !CheckName(b) || !check_validname(b)) {
				return false;
			}
			current_table1 = b;
		} else {
			if (!space(GetRest) || !CheckName(GetRest) || !check_validname(GetRest)) {
				return false;
			}
			current_table1 = GetRest;
		}
		before_from = n.substring(0, n.indexOf("from"));
		before_from = Trim_end(before_from);
		return true;
	}

}
