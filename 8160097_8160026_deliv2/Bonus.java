public class Bonus {

private int Points;

public Bonus(int Points) {
this.Points = Points;
}

public int getPoints() {
 return this.Points;
}

public void setPoints(int Points) {
this.Points = Points;
}

public int calculatePoints(double totalCost) {

 return (int)totalCost / 3;
}
public void AddPoints(double totalCost) {

this.Points+=(int)totalCost / 3;
}

public String PrintPoints() {
	return String.format("You have " + getPoints() + "and you need " + (200 - getPoints()) + " To reach 200");

}

public void NewPurchase(double Total) {

System.out.println("Today you earn " + calculatePoints(Total) + " and reached 200 points! You get 20% discount.");
Points-=200;

}

}




