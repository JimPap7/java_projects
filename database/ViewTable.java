package mypkgs;


import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.DoubleStream;


public class ViewTable {

    String resp;
    Scanner s2 = new Scanner(System.in);

	public void viewTable() {

		if(Table.noTables()) {
			System.out.println("You need to first create a table. Exiting.");
			return;
		}

		Table myTable = Table.searchTable();
		if(myTable == null) {
			return;
		}
       boolean endcheck = true;

		while(endcheck) {

			getMenu();
			resp = s2.nextLine();

			if(resp.equals("1")){
			  printTable(myTable, myTable.createCopy1());
			} else if (resp.equals("2")){
				viewSortBy(myTable);
			} else if (resp.equals("3")){
				customViewTable(myTable);
			} else if (resp.equals("4")){
				 viewStats(myTable);
		    } else {
			   return;
		    }
	    }
   }

      public void customView(ArrayList<String> colList, ArrayList<Integer> srcSet, Table myTable) {
		       for(String w : colList) {
					   System.out.print(w + "\t");
			   }
			   System.out.println();
			   for(int i : srcSet) {
				  for(String w : colList) {
					 System.out.print(myTable.getValue(w, i) + "\t");
				  }
				  System.out.println();
			   }
	  }


	  public void customViewTable(Table myTable) {
		  if(myTable.getRowNo() == 0) {
			  System.out.println("Please first add some rows to access this functionality");
			  return;
		  }
		  ArrayList<String> colList = new ArrayList<String>();
		  ArrayList<Integer> srcSet = new ArrayList<Integer>();
		  boolean a = true;
		  String colName="", styp="";
		  int maxI=0, s=0;

		  maxI = myTable.getRowNo();
		  for(int i = 0; i < maxI; i++) {
			  srcSet.add(i);
		  }
		  System.out.println("Select which columns you would like to view. Press enter to exit");
		   while(a && s < myTable.getMappings()) {
			   s++;
			   resp = s2.nextLine();
			   if(resp.equals("")) {
				   a = false;
			    } else if(myTable.containsKey(resp)) {
					colList.add(resp);
			   } else {
				   System.out.println("The column name does not exist");
			   }
		   }
		   if(colList.isEmpty()) {
			   System.out.println("No columns to view. Exiting");
			   return;
		   }
           System.out.println("would you like to enter a condition?[Y/any other key])");
		   resp = s2.nextLine();
		    if(!resp.equalsIgnoreCase("y")) {
			    customView(colList, srcSet, myTable);
			    System.out.println("Press any key to exit");
				resp = s2.nextLine();
			    return;
			}
			Predicate<Integer> d;
			String colComp="" , conVal="";
			boolean b = true;
			List<Integer> resSet = new ArrayList<Integer>();
            while(b) {
		     System.out.println("Select condition column");
		     a = true;
		      while(a) {
			   resp = s2.nextLine();
			    if(myTable.containsKey(resp)) {
				   a = false;
				   colName = resp;
			   } else {
				   System.out.println("The column name does not exist");
			  }
		   }
		   String coltyp;
		   if(colName.equals(myTable.getPk()) && myTable.getPKInc()) {
			   coltyp = "Í";
		   } else {
           coltyp = myTable.getType(colName).substring(0, 1);
		   }
            if(coltyp.equals("S")) {
			   System.out.println("Enter a contition. Choose one of the symbols(= or #)");
		    } else {
			   System.out.println("Enter a contition. Choose one of the symbols(<,=,>,#)");
		    }
		   a = true;
		    while(a) {
			   resp = s2.nextLine();
			   if((resp.length() == 1) && (("<=>#".contains(resp)) && (!coltyp.equals("S"))) ||
			     (("=#".contains(resp)) && (coltyp.equals("S")))) {
				   a = false;
				   colComp = resp;
				} else {
			   		System.out.println("Invalid condition. Please try again");
			    }
		    }
            Add2Table.getTypes(coltyp, colName);
			conVal = s2.nextLine();
			 if(createShortcut(coltyp,colName, conVal, myTable, resSet, colComp)) {
			    d = Predicates.getCorrectPredicate(colComp, coltyp, myTable.getList(colName),conVal);
			    resSet = srcSet.parallelStream().filter(d).collect(Collectors.toList());
			 }
			if(resSet.isEmpty()) {
				System.out.println("No data found matching the condition");
				return;
			}
			srcSet.retainAll(resSet);
			myTable.viewTable(myTable.createCopy1(), colList, srcSet);
			resSet.clear();
			if(srcSet.size() == 1) {
				System.out.println("Press any key to exit");
				resp = s2.nextLine();
				return;
			}
			System.out.println("Would you like to add more conditions?[Y/N] ");
			resp = s2.nextLine();
			if (!resp.equalsIgnoreCase("y")) {
				b = false;
			}
     }

	}

	public boolean createShortcut(String coltyp, String colName, String conVal, Table myTable, List<Integer> resSet, String colComp) {
       if(coltyp.equalsIgnoreCase("N") && !conVal.matches("-?[0-9]*(.|,)?[0-9]+?$")) {
		   return false;
		} else if(coltyp.equalsIgnoreCase("C") && !conVal.replaceAll("\\s+","").matches("[a-zA-Z]+$")) {
			return false;
		}
		if(myTable.getType(colName).contains("2") || colName.equals(myTable.getPk())) {
			int i = myTable.findUnique(colName, conVal);
			if(colComp.equals("=")) {
			 if(i > 0) {
				 resSet.add(i);
				 return false;
			 } else {
				 return false;
		     }
	        }
		} else if(myTable.getType(colName).contains("2") || colName.equals(myTable.getRowNo())  && conVal.equals("")) {
			return false;
		}
		  return true;
	}




	 public void viewSortBy(Table myTable) {

         if(myTable.getRowNo() == 0) {
		    System.out.println("Please first add some rows to access this functionality");
		    return;
		 }

		Comparators c = new Comparators();
        String colby;
        LinkedHashMap<String, LinkedList<String>>  ss = new  LinkedHashMap<String, LinkedList<String>>();
         ss = myTable.createCopy1();

		 System.out.println("Input the name of a numeric or a character Column to sort the table by. Press enter to exit.");
	     colby = checkColumn(myTable);
	     if(colby.equals("")) {return;}
	     System.out.println("Would you like the sorting to be done in (A)scending or (D)escending order?");
	     resp = s2.nextLine();
	       while(!resp.equalsIgnoreCase("a") && !resp.equalsIgnoreCase("d")) {
	        System.out.println("Invalid input. Please try again");
	        resp = s2.nextLine();
		   }
        if(myTable.getType(colby).equals("N")) {

		   if(!resp.equalsIgnoreCase("d")) {
		     Collections.sort(ss.get(colby), c.getAttribute1Comparator());
		    } else {
		        Collections.sort(ss.get(colby), Collections.reverseOrder(c.getAttribute1Comparator()));
		   }
		  } else {
			    if(!resp.equalsIgnoreCase("d")) {
				  Collections.sort(ss.get(colby), c.getAttribute2Comparator());
			    } else {
		          Collections.sort(ss.get(colby), Collections.reverseOrder(c.getAttribute2Comparator()));
	            }
		 }
          for(String key : myTable.getKeySet()) {

			if (!key.equals(colby)) {
			 Collections.sort(ss.get(key), c.getAttribute3Comparator());
	   		}
		  }
		printTable(myTable, ss);
   }

   public void printTable(Table myTable, LinkedHashMap<String, LinkedList<String>> a) {
	    int s = myTable.getRowNo();
	    List<Integer> l = IntStream.rangeClosed(0, s - 1).boxed().collect(Collectors.toList());
	    ArrayList<String> mainList = new ArrayList<String>();
	   	mainList.addAll(myTable.getKeySet());
	    myTable.viewTable(a,mainList, l);
   }

   public void customViewTable(LinkedHashMap<String, LinkedList<String>> a, int size) {

	   for( String key : a.keySet() ) {
	    	System.out.print(key + "\t");
	       }
	    	System.out.println();

	         for(int i = 0;i < size;i++){
	    	    for(String key2 : a.keySet()){
	    		System.out.print(a.get(key2).get(i) + "\t");
	    	 }
	    	   System.out.println();
	   }
   }

   public void viewStats(Table myTable) {
     if(myTable.getRowNo() == 0) {
	 	 System.out.println("Please first add some rows to access this functionality");
	 	  return;
	  }
     String max, min;
     boolean s = true;
     String exit = "or press enter to exit";
      while(s) {
       System.out.println("Press 1 to view max, 2 to view min, 3 to view sum, 4 to view average, any other key to exit");
       resp = s2.nextLine();
         if(resp.equals("1")) {
	      System.out.println("Select a numeric column to view the max value " + exit);
	      resp = checkColumn(myTable);
	       if(!resp.equals("")) {
		      max = Collections.max(myTable.getList(resp),Comparators.getAttribute1Comparator());
		      System.out.println("The max value of column " + resp + " is " + max);
	       }
	      }else if(resp.equals("2")) {
		       System.out.println("Select a numeric column to view the min value " + exit);
		       resp = checkColumn(myTable);
		        if(!resp.equals("")) {
		          min = Collections.min(myTable.getList(resp),Comparators.getAttribute1Comparator());
		          System.out.println("The max value of column " + resp + " is " + min);
		        }
          }else if(resp.equals("3")) {
		       System.out.println("Select a numeric column to view the sum " + exit);
		       resp = checkColumn(myTable);
		        if(!resp.equals("")) {
				  Double sum = getSum(myTable.getList(resp));
		          System.out.println("The sum of column " + resp + " is " + sum);
		        }
           }else if(resp.equals("4")) {
		         System.out.println("Select a numeric column to view the average " + exit);
		         resp = checkColumn(myTable);
		          if(!resp.equals("")) {
		   	        double avg =  getSum(myTable.getList(resp)) / myTable.getRowNo();
		            System.out.println("The average of column " + resp + " is " + avg);
			      }
		   } else {
			   s = false;
		  }
     }

   }
       public String checkColumn(Table myTable) {
		   boolean a = true;
		   while(a) {
			   resp = s2.nextLine();
			   if(resp.equals("")) {
				   a = false;
			   }else if(!myTable.containsKey(resp)) {
			       System.out.println("Column " + resp + " doesnt exist");
			   } else if(!myTable.getType(resp).contains("N") && !myTable.getType(resp).contains("C")) {
				   System.out.println("Column " + resp + " is not numeric or character");
		       } else {
				  a = false;
			    }
	      }
	      return resp;
	   }


       public double getSum(LinkedList<String> l) {
		 DoubleStream a = l.stream().mapToDouble(Double::valueOf);
		 double sum = a.sum();
		 return sum;
	   }


		public void getMenu() {

		   System.out.println("Make a choice from 1-4. Press any other key to exit to main menu");
		   System.out.println("1. View Whole table ");
		   System.out.println("2. View table sorted by a column(Must be character or numeric column)");
		   System.out.println("3. Custom View Table ");
		   System.out.println("4. View Stats of a column.(Must be numeric)");
		}

}