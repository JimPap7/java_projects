public class CustomerBonus extends Customer {

  private Bonus bonus;

 public CustomerBonus(int a,String username, int password, String FullName) {
 super( username, password, FullName);
 bonus = new Bonus(a);


 }

 public void getBonusPoints() {
	System.out.println("You have " + bonus.getPoints() + " You need " + (200 - bonus.getPoints()) + " To reach 200 points");
}
public int getBonus(double totalcost) {
	bonus.AddPoints(totalcost);
    return bonus.getPoints();

}
public void Points(int x) {
	bonus.setPoints(x);
}
}