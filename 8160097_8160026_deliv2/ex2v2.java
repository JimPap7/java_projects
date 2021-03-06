import java.util.Scanner;

public class ex2v2 {
	public static void main(String[] args) {

	createObjects(); // load Objects to test program's functionality




	int s;
	Customer Custx;
	Scanner Sc = new Scanner(System.in);
	System.out.println("DMST Supermarket");
	System.out.println("Log in to Continue");
	Custx=Customer.LogCus();
    CustomerBonus Custbonus=(CustomerBonus)Custx;
	if (Custx instanceof CustomerBonus){
	 s=1;


     } else {
	  s=0;
    }

int d = 1;

while (d>=1 && d<=5) {

	printMenu(s);

System.out.print("Make a choice: ");
d = Sc.nextInt();

String tst = Integer.toString(s) + Integer.toString(d);
switch (tst) {
	case "01" : case "11" : //Change password
		Custx.changePassword();
		break;
    case "02" : case "13" : //Print product list

         Product.getProducts(s);
         break;
     case "12" ://print bonus

     	Custbonus.getBonusPoints();
     	break;

     case "03" : case "14" : //Buy Product
		if(s==0){
			Receipt.BuyItems(null);
		} else {
         Receipt.BuyItems(Custbonus);
		}
     	break;
     case  "15" : //Print transactions
        Receipt.getPreviousTransactions();
     	break;
}// End switch user options

}
}

private static void createObjects() {
    Product Prod1 = new Product("Book of electron", 45.80,7);
    Product Prod2 = new Product("Motor part no:78", 5.20,82);
    Product Prod3 = new Product("Spring", 9.35,28);
    Customer Cust = new Customer( "DowJ", 1234, "DowJones");
    Customer Cust1 = new Customer( "abcd", 1234, "manolis");
    CustomerBonus Cust3 = new CustomerBonus(15,"bbb",12,"Bonus Customer Jim");

}
private static void printMenu(int s) {
	System.out.println("*Menu*");
	System.out.println("1.Change Password");
	switch (s) {
		case 1:
			System.out.println("2.print bonus points");
			System.out.println("3.print available products");
			System.out.println("4.Buy Products");
			System.out.println("5.print previous transactions");
			break;
		case 0:
			System.out.println("2.Print available Products");
			System.out.println("3.Buy Products");
			break;
		default:
			break;
} //end case customer type menu
}
}


