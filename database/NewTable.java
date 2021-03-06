package mypkgs;

import java.util.*;
import java.io.*;
import java.util.Scanner;

public class NewTable{

Table tableobj = null; //creating Table type object
Scanner sc = new Scanner(System.in);



public void createTable(){

	boolean checkexistingtable = true; // checks if the table name is available

	 //creating Table type object
	String name;
	while(checkexistingtable){ //while checkeistingtable = true
		System.out.println("Give table name");
		name = sc.nextLine();	//read the table's name
		if (Table.getTable(name) != null){ //checks if table names exists already
	 		checkexistingtable = true; // make again the loop
	 		System.out.println("Table already exists");
	 	} else {
			tableobj = new Table(name);//the given name becomes a table name ,add the table to the list of tables
			tableobj.setTable();
			checkexistingtable = false; // end the loop
		}
	 }
	setPrimaryKey(tableobj);
	tableobj.createTableCols();
	}

	public void setPrimaryKey(Table myTable) {

	  System.out.println("Give column name for primary key");
	  String pkval = sc.nextLine();
	 	while(pkval.trim().isEmpty()) {
	 		System.out.println("Primary key cannot be an empty line. Please try again");
	 		pkval = sc.nextLine();
	 	}
	 	System.out.println("Would you like the primary key to be auto-incremental?[Y/any other key]");
	 	String pki = sc.nextLine();
			if(pki.equalsIgnoreCase("y")) {
			 		myTable.setPKInc(true);
	 	} else {
			myTable.setType(pkval);
		}
	   myTable.setPK(pkval);
       myTable.putValues(pkval);
    }



}
