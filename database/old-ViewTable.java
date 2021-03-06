package mypkgs;


import java.util.*;
import java.io.*;
import java.util.Scanner;


public class ViewTable {

    String resp;
    Scanner s2 = new Scanner(System.in);

	public void viewTable() {

		if(Table.noTables()) {
			System.out.println("You need to first create a table. Exiting.");
			return;
			}


		Table myTable = new Table(); //creating Table type object

		myTable = myTable.searchTable();

		if(myTable == null) {
			return;
		} else if(myTable.checkTable()) {
			System.out.println("Please add some columns to the table. Exiting.");
			return;
		}
       boolean endcheck = true;

		while(endcheck) {

			getMenu();
			resp = s2.nextLine();

			if(resp.equals("1")){
			  myTable.viewTable();
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
					   System.out.println(w + "\t");
			   }
			     for(String w : colList) {
				   for(int i : srcSet) {
					 System.out.println(myTable.getValue(w, i) + "\t");
				   }
			    }
	  }


	  public void customViewTable(Table myTable) {
		  if(myTable.getRowNo() == 0) {
			  System.out.println("Please first add some rows to access this functionality");
			  return;
		  }
		  ArrayList<String> colList = new ArrayList<String>();
		  ArrayList<Integer> resSet = new ArrayList<Integer>();
		  ArrayList<Integer> srcSet = new ArrayList<Integer>();
		  boolean a = true , b = true;
		  String colName="", colComp="" , conVal="", styp="";
		  double x=0,z=0;
		  int maxI=0,maxJ=0, s=0;

		  maxI = myTable.getRowNo();
		  for(int i = 0; i < maxI; i++) {
			  srcSet.add(i);
		  }

		  System.out.println("Select which columns you would like to view. Press enter to exit");
		   while(a && s <= myTable.getRowNo()) {
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
			   }
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

           String coltyp = myTable.getType(colName);
           if(coltyp.equals("S")) {
			   System.out.println("Enter a contition. Choose one of the symbols(= or #)");
		   } else {
			   System.out.println("Enter a contition. Choose one of the symbols(<,=,>,#)");
		   }

		   a = true;
		   while(a) {
			   resp = s2.nextLine();
			   if((resp.length() == 1) && (("<=>#".contains(resp)) && (!coltyp.equals("S"))) ||
			                              ((  "=#".contains(resp)) && ( coltyp.equals("S")))  ) {
				   a = false;
				   colComp = resp;
				} else {
			   		System.out.println("Invalid condition");
			    }
		   }

		   switch (coltyp) {
		   		case "S" : styp = "String"; break;
		   		case "N" : styp = "Numeric"; break;
		   		case "C" : styp = "Character"; break;
			}
			System.out.println("Enter a valid " + styp + " value for " + colName);
			conVal = s2.nextLine();
			for(int i : srcSet) {

				String colVal = myTable.getValue(colName, i);
				if ((coltyp.equals("S")) || (coltyp.equals("C"))) {
					if ((colComp.equals("=")) && (colVal.equals(conVal))) {
						resSet.add(i);
					} else if ((colComp.equals("<")) && (colVal.compareTo(conVal) < 0)) {
						resSet.add(i);
					} else if ((colComp.equals(">")) && (colVal.compareTo(conVal) > 0)) {
						resSet.add(i);
					} else if ((colComp.equals("#")) && !(colVal.equals(conVal))) {
						resSet.add(i);
					}
				} else {
					if(colVal.matches("[-+]?\\d+")) {
						x = Integer.parseInt(colVal);
					}
					if(colVal.matches("[-+]?[0-9]*\\.?[0-9]*")) {
						x = Double.parseDouble(colVal);
					}
					if(conVal.matches("[-+]?\\d+")) {
						z = Integer.parseInt(conVal);
					}
					if(conVal.matches("[-+]?[0-9]*\\.?[0-9]*")) {
						z = Double.parseDouble(conVal);
					}
					if ((colComp.equals("=")) && (x == z)) {
						resSet.add(i);
					} else if ((colComp.equals("<")) && (x < z)) {
						resSet.add(i);
					} else if ((colComp.equals(">")) && (x > z)) {
						resSet.add(i);
					} else if ((colComp.equals("#")) && (x != z)) {
						resSet.add(i);
					}

				}

			}
			if(resSet.isEmpty()) {
				System.out.println("No data found matching the condition");
				return;
			}
			customView(colList, srcSet, myTable);
			srcSet.retainAll(resSet);
			resSet.clear();
			System.out.println("Would you like to add more conditions?[Y/N] ");
			resp = s2.nextLine();
			if (!resp.equalsIgnoreCase("y")) {
				b = false;
			}
     }

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
		 System.out.println("Input the name of a numeric or a character Column to sort the table by");
	     colby = checkColumn(myTable);
	     System.out.println("Would you like the sorting to be done in (Á)scending or (D)escending order?");
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

	    customViewTable(ss, myTable.getRowNo());
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
      while(s) {
       System.out.println("Press 1 to view max, 2 to view min, 3 to view sum, 4 to view average, any other key to exit");
       resp = s2.nextLine();
         if(resp.equals("1")) {
	      System.out.println("Select a numeric column to view the max value");
	      resp = checkColumn(myTable);
		  max = Collections.max(myTable.getList(resp),Comparators.getAttribute1Comparator());
		  System.out.println("The max value of column " + resp + " is " + max);
		   //int x = Collections.binarySearch(myTable.getList(resp), "10");
		   //System.out.println(x);
	        }else if(resp.equals("2")) {
		       System.out.println("Select a numeric column to view the min value");
		       resp = checkColumn(myTable);
		       min = Collections.min(myTable.getList(resp),Comparators.getAttribute1Comparator());
		       System.out.println("The max value of column " + resp + " is " + min);
            }else if(resp.equals("3")) {
		       System.out.println("Select a numeric column to view the sum");
		       resp = checkColumn(myTable);
		       System.out.println("The sum of column " + resp + " is " + getSum(myTable.getList(resp)));
              }else if(resp.equals("4")) {
		         System.out.println("Select a numeric column to view the average");
		         resp = checkColumn(myTable);
		   	     double avg = (getSum(myTable.getList(resp)) / myTable.getRowNo());
		         System.out.println("The average of column " + resp + " is " + avg);
		       } else {
			      s = false;
		  }
     }

   }
       public String checkColumn(Table myTable) {
		   boolean a = true;
		   while(a) {
			   resp = s2.nextLine();
			   if(!myTable.containsKey(resp)) {
			   System.out.println("Column " + resp + " doesnt exist");
			    } else if(!myTable.getType(resp).equals("N")) {
				    System.out.println("Column " + resp + " is not numeric");
		          } else {
					  a = false;
			    }
	      }
	      return resp;
	   }


       public double getSum(LinkedList<String> l) {

		 double sum = 0;
		  for(String s : l) {

			sum += Double.parseDouble(s);
		  }
		  return sum;
	   }


		public void getMenu() {

		   System.out.println("Make a choice from 1-4. Press any other key to exit to main menu");
		   System.out.println("1. View Whole table ");
		   System.out.println("2. View table sorted by a column(Must be character or numeric column)");
		   System.out.println("3. Custom View Table ");
		   System.out.println("4. View Stats of a column.(Must be numeric)");
		}


	//public static class Comparators {
		//static int j;
        //static ArrayList<Integer> sortingMethodReturns = new ArrayList<Integer>();

		//static Comparator<String> getAttribute1Comparator() {
		       // return new Comparator<String>() {
				//	@Override
				  //  public int compare(String lhs, String rhs) {


							  //  double x=0,z=0;
							   //  if(lhs.matches("\\d+")) {

								 // x = Integer.parseInt(lhs);
								// }
								// if(lhs.matches("[-+]?[0-9]*\\.?[0-9]*")) {
								//	x = Double.parseDouble(lhs);
								// }
								// if(rhs.matches("\\d+")) {
								//		z = Integer.parseInt(rhs);
								 // }
								 //if(rhs.matches("[-+]?[0-9]*\\.?[0-9]*")) {
								//	z = Double.parseDouble(rhs);
								 //}


							     // int returning=0;
							      //  if (x  < z) {
									// returning = -1;
									//} else if (x > z) {
									// returning = 1;
 									//	} else {
 									//	returning =  0;
									//	}


							         // sortingMethodReturns.add(returning);

							         //       return returning;
		                              //  }

		       // };
      // }



   // static Comparator<String> getAttribute2Comparator() {
			        //return new Comparator<String>() {
					//	@Override
						//public int compare(String lhs, String rhs) {
							//	int returning = lhs.compareTo(rhs);
							//	sortingMethodReturns.add(returning);
							//	return returning;
			           //  }

		           // };
   //  }


       		//static Comparator<String> getAttribute3Comparator() {
	   		    //    return new Comparator<String>() {
	   					//@Override
	   				  //  public int compare(String lhs, String rhs) {
                       //   int returning = sortingMethodReturns.get(j);
                       //   j++;
                       //   return returning;
	   		           // }

	   		       // };
         // }

 //}

}