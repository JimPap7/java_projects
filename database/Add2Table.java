package mypkgs;

import java.util.*;
import java.io.*;
import java.util.Scanner;



public class Add2Table {

	Scanner s2 = new Scanner(System.in);

	public  void addAtt2Table() {

        if(Table.noTables()) {
		  System.out.println("you need to first create a table. Exiting.");
		  return;
	    }
	    boolean check = true;
		Table myTable = Table.searchTable();

		if (myTable == null) {
			return;
		}


		String resp;
			while(check) {
				System.out.println("Add rows (r) or new Columns (c). Press any other key to exit");
				resp = s2.nextLine();
				if (resp.equalsIgnoreCase("c")) {
					myTable.createTableCols();
					myTable.fillEmptyCols();
				} else if (resp.equalsIgnoreCase("r")) {
					addRows(myTable);
				} else {
					check = false;
			    }
	         }
    }

	public void addRows(Table myTable) {

			String myval;
			String coltyp;
			String key;
			boolean morerow = true;

			while(morerow) {
		     Iterator<Map.Entry<String, LinkedList<String>>> itr1 = myTable.createCopy1().entrySet().iterator();
			   while(itr1.hasNext()) {
				 Map.Entry<String, LinkedList<String>> entry = itr1.next();
                      key = entry.getKey();
					if(key.equals(myTable.getPk()) && myTable.getPKInc()) {
							int i = myTable.getRowNo() + 1;
						    myTable.add2Table(key, Integer.toString(i));
					} else {
					  coltyp = myTable.getType(key).substring(0, 1);
					  getTypes(coltyp, key);
					    myval = s2.nextLine();
						while(!checkval(myval, coltyp, myval, key, myTable)) {
							myval = s2.nextLine();
					   }
					myTable.add2Table(key, myval);

			     }
		      }
		        myTable.updateRowNo();
				System.out.println("Add next row ?(Y/N)");
				myval = s2.nextLine();
				morerow = (myval.equalsIgnoreCase("Y"));
			}

	}

	public static void getTypes(String coltype, String key) {
		String styp = "";
		switch (coltype) {
		   case "S" : styp = "a String"; break;
		   case "N" : styp = "a Numeric"; break;
		   case "C" : styp = "a Character"; break;
		}
		System.out.println("Enter " + styp + " value for " + key);
   }

	public boolean checkval(String tval, String typeof, String myval, String key, Table myTable) {

			int s = 0;

			if (typeof.equalsIgnoreCase("n") && !tval.matches("^[0-9]*(.|,)?[0-9]+?$")) {
				System.out.println("Wrong type. Enter a numeric value ");
				s++;
			}
			if (typeof.equalsIgnoreCase("c") && !tval.replaceAll("\\s+","").matches("^[a-zA-Z]+$")) {
				System.out.println("Wrong type. Enter a character value ");
				s++;
			}
			if(myTable.getPk().equals(key)) {
				if(myTable.keyContainsVal(key, myval)) {
					s++;
			       System.out.println("Duplicate  values for Primary Key are not allowed. Enter a correct value.");
				}
				if(myval.equals("")) {
					System.out.println("Null  value for Primary Key is not allowed. Enter a correct value.");
					s++;
		        }
		   }
		   if(myTable.getType(key).contains("1") && myval.equals("")) {
			   System.out.println("Constraint violation: No null values allowed for column " + key);
			   s++;
		    }
		    if(myTable.getType(key).contains("2") && myTable.keyContainsVal(key, myval)) {
			   System.out.println("Constraint violation: No duplicate values allowed for column " + key);
			   s++;
		    }

			if(s != 0) {
			  return false;
		    } else {
			  return true;
		   }
     }


}
