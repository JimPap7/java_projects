package mypkgs;

import java.util.function.Predicate;
import java.util.*;

public class Predicates {

	public static Predicate<Integer> isEquals(LinkedList<String> l1, String colVal) {
		System.out.println("Here");
		return  (t) -> l1.get(t).equals(colVal);
	}

	public static Predicate<Integer> isGreater(LinkedList<String> l1, String colVal) {
			return  (t) -> l1.get(t).compareTo(colVal) > 0;
	}

	public static Predicate<Integer> isEqualsN(LinkedList<String> l1, String colVal) {
		        Double colVal1 = Double.parseDouble(colVal);
		        ArrayList<Double> List = new ArrayList<Double>();
				  for(String s : l1) {
				    List.add(Double.parseDouble(s));
				  }
				return  (t) -> List.get(t) == colVal1;
	}

	public static Predicate<Integer> isGreaterN(LinkedList<String> l1, String colVal) {
			        Double colVal1 = Double.parseDouble(colVal);
			        ArrayList<Double> List = new ArrayList<Double>();
			        for(String s : l1) {
						List.add(Double.parseDouble(s));
						}
					return  (t) -> List.get(t) > colVal1;
	}

	public static Predicate<Integer> getCorrectPredicate(String condition, String Type, LinkedList<String> l1, String colVal) {
		if(Type.equals("N")) {
			if(condition.equals("=")) {
				return isEqualsN(l1, colVal);
            } else if(condition.equals("#")) {
				   return isEqualsN(l1, colVal).negate();
		    } else if(condition.equals(">")) {
				   return isGreaterN(l1, colVal);
		    } else {
				   return isGreaterN(l1, colVal).negate().and(isEqualsN(l1, colVal).negate());
		    }
	     } else {
			 if(condition.equals("=")) {
			 	 return isEquals(l1, colVal);
			  } else if(condition.equals("#")) {
			 	  return isEquals(l1, colVal).negate();
			  } else if(condition.equals(">")) {
			 	  return isGreater(l1, colVal);
			  } else  {
				 return isGreater(l1, colVal).negate().and(isEquals(l1, colVal).negate());
		     }
	   }
   }
}