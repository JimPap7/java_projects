package mypkgs;
import java.util.*;
public  class Comparators {
		static int j;
        static ArrayList<Integer> sortingMethodReturns = new ArrayList<Integer>();

		static Comparator<String> getAttribute1Comparator() {
		        return new Comparator<String>() {
					@Override
				    public int compare(String lhs, String rhs) {


							    double x=0,z=0;
							     if(lhs.matches("\\d+")) {

								  x = Integer.parseInt(lhs);
								 }
								 if(lhs.matches("[-+]?[0-9]*\\.?[0-9]*")) {
									x = Double.parseDouble(lhs);
								 }
								 if(rhs.matches("\\d+")) {
										z = Integer.parseInt(rhs);
								  }
								 if(rhs.matches("[-+]?[0-9]*\\.?[0-9]*")) {
									z = Double.parseDouble(rhs);
								 }


							      int returning=0;
							        if (x  < z) {
									 returning = -1;
									} else if (x > z) {
									 returning = 1;
 										} else {
 										returning =  0;
										}


							          sortingMethodReturns.add(returning);

							                return returning;
		                                }

		        };
       }



    static Comparator<String> getAttribute2Comparator() {
			        return new Comparator<String>() {
						@Override
						public int compare(String lhs, String rhs) {
								int returning = lhs.compareTo(rhs);
								sortingMethodReturns.add(returning);
								return returning;
			             }

		            };
     }


       		static Comparator<String> getAttribute3Comparator() {
	   		        return new Comparator<String>() {
	   					@Override
	   				    public int compare(String lhs, String rhs) {
                          int returning = sortingMethodReturns.get(j);
                          j++;
                          return returning;
	   		            }

	   		        };
          }

 }

