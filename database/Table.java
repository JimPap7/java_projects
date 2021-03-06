package mypkgs;


import java.util.*;
import java.util.Scanner;
import java.util.regex.*;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Table implements  Serializable{

	private String name;
	static Scanner sc = new Scanner(System.in);
	private int rows = 0;

	private String Pkey;
	private boolean PKInc = false;
    private Map<String, String> ColTypes = new HashMap<String, String>();
    private static HashMap<String, Table> listoftables  = new HashMap<String, Table>();
	private Map<String, LinkedList<String> > table = new LinkedHashMap<String, LinkedList<String>>(); //TreeMap that contains the rows and the values of every table
    private LinkedList<String> list;

	public Table(String name){ //constructor
		this.name = name;
    }

    public void setTable() throws IOException {
	 listoftables.put(this.name, this);
     try {
	 FileOutputStream fos = new FileOutputStream(this.name + ".txt");
	 ObjectOutputStream oos = new ObjectOutputStream(fos);
	 oos.writeObject(this);
	 oos.close();
	 fos.close();
	 } catch (IOException e) {
	 	 e.printStackTrace();
     }
}

    public Table() {}

    public String getName() {
		return this.name;
    }

    public int getMappings() {
		return this.table.size();
		}

    public void setRows(int row) {
		this.rows = row;
	}
	public void setPKInc(boolean s) {
		this.PKInc = s;
	}

    public boolean getPKInc() {
		return this.PKInc;
	}

	public LinkedList<String> getCopy(String colname) {
		LinkedList<String> s = new LinkedList<String>(table.get(colname));
		return s;
		}


    public static boolean noTables() {
		return listoftables.isEmpty();
	}

    public void updateRowNo() {
		this.rows++;
	}
	public int getRowNo() {
		return this.rows;
	}

	public int findUnique(String col, String val) {
		return this.table.get(col).indexOf(val);
   }

    public void putValues(String rowname) {
       list = new LinkedList<String>(); //list that contains the values of a column
	   this.table.put(rowname, list);
	}

	 public void createTableCols() {

         boolean check1 = true; // checks if you want to stop adding columns
	     String colname;

	  	 colname = getColname();
		 check1 = !colname.trim().isEmpty();
	  		while(check1) {
	  		  setType(colname);
	  		  addConstraints(colname);
			  putValues(colname);
			  colname = getColname();
	  		  check1 = !colname.trim().isEmpty();
			}
	 }

	public void setType(String colname) {
		String coltype;
		System.out.println("Give column type (S)tring,(Í)umeric or (C)haracter");
		coltype = sc.nextLine();
		 while(coltype.length() != 1 || !"SNC".toLowerCase().contains(coltype.toLowerCase())) {
			System.out.println("Invalid input. Please input S, N or C");
			coltype = sc.nextLine();
	     }
	  	ColTypes.put(colname, coltype.toUpperCase());
    }

    public String getColname() {
		int s = 0;
		String colname;
		System.out.println("Please enter column name or press enter to exit");
		colname = sc.nextLine();
			while(table.containsKey(colname)) {
				s++;
			  System.out.println("column name already exists. Please try again");
			  if((s % 3) == 0) {
			  	 System.out.println("Reminder : You have already entered this/these column(s): " + table.keySet());
			   }
			  colname = sc.nextLine();
		    }
		    return colname;
    }

    public void addConstraints(String colname) {
		if(colname.equals(this.Pkey)) {
			return;
		}
		System.out.println("Would you like to add  constraint(s)?[Y/any other key]");
		String resp = sc.nextLine();
		if(!resp.equalsIgnoreCase("y")) {
			return;
		}
		int s = 0;
		 System.out.println("Choose constraints. Press enter to exit");
			 System.out.println("1. Not null");
			 System.out.println("2. No duplicates");
			 while(s < 2 && !resp.equals("")) {
				 resp = sc.nextLine();
				 if(!(resp.equals("1") || resp.equals("2")) && !resp.equals("")) {
					 System.out.println("Invalid input. Please try again");
				 } else {
					  s++;
					 String s1 = ColTypes.get(colname) + resp;
					 ColTypes.put(colname, s1);
				 }
			 }
   }



	public void fillEmptyCols() {

		for (String key : table.keySet()) {
			if( table.get(key).size() < rows) {
				for(int i = 0; i<rows; i++) {
					table.get(key).add("null");
				}
           }

		}
    }



		public static Table searchTable() {
   			boolean workwithtable = true; // Work with a table
   			Table tableobj = null; //Work table
   			String name;
   			System.out.println("Give the table's name to work with or E to exit :");
   			while(workwithtable){
   				name = sc.nextLine();	//read the table's name
   				if(name.toLowerCase().equals("e")) {
   					workwithtable = false;
   				} else {
   					tableobj = getTable(name);
   					if ( tableobj == null){
   						System.out.println("Table " + name + " does not exists");
   					} else {
   						workwithtable = false;
   					}
   				}
			}
			return tableobj;
		}

// Encapsulate Collections
	public String getPk() {
		return this.Pkey;
	}

	public void setTypePK(String PK) {
			ColTypes.put(PK, "N");
    }

	public void setPK(String key) {
		 this.Pkey = key;
    }

	public void add2Table(String key, String value) {
		 this.table.get(key).add(value);
    }

    public Set<String> getKeySet() {
         return Collections.unmodifiableSet(this.table.keySet());
    }

    public boolean keyContainsVal(String key, String val) {
		 return this.table.get(key).contains(val);
    }

   public String getType(String key) {
	    return this.ColTypes.get(key);
   }

   public boolean containsKey(String key) {
	    return this.table.containsKey(key);
   }

   public String getValue(String key, int i) {
        return this.table.get(key).get(i);
   }

   public LinkedList<String> getList(String key) {
	    LinkedList<String> l = new LinkedList<String>(table.get(key));
	    return l;

  }

   public static  Table getTable(String name) {
		if(listoftables.containsKey(name)) {
			return  listoftables.get(name);
	 } else {
		 try {
		 FileInputStream fis = new FileInputStream( name + ".txt");
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Table result = (Table)ois.readObject();
		 ois.close();
		 fis.close();
		 return result;
		 } catch (FileNotFoundException e) {
		 		return null;
		 } catch (IOException e) {
		 		return null;
		 } catch (ClassNotFoundException e) {
		 		return null;
		}
     }
 }

    public LinkedHashMap<String, LinkedList<String>> createCopy1() {
	 LinkedList<String> list12;
	 LinkedHashMap<String,LinkedList<String>> b = new LinkedHashMap<String, LinkedList<String>>();

	 for (String key : getKeySet()) {
		 list12 = new LinkedList<String>(table.get(key));
         b.put(key, list12);
      }
         return b;
	 }


   public void viewTable(LinkedHashMap<String, LinkedList<String>> a,ArrayList<String> colList, List<Integer> l) {

	       if(getKeySet() == null) {
			   System.out.println("No Data");
			   return;
		   }
		   String leftAlignFormat = "| %-"+ 15 +"s |";
		   String leftAlignFormat1 = " %-15s ";

           for(String key : colList){
			System.out.format("+-----------------+");
		   }
		   System.out.println();
			for(String key : colList) {
			System.out.format(leftAlignFormat, key);
		    }
		    System.out.println();
		    for(String key : colList) {
            System.out.format("+-----------------+");
		    }
 					System.out.println();

             for(int i : l){
 			     for(String key2 : colList){
 					 System.out.format(leftAlignFormat,  a.get(key2).get(i));
 				 }
 				 System.out.println();


			 }
			 for(String key2 : table.keySet()) {
				 System.out.format("+-----------------+");
			 }
            System.out.println();

   }

}