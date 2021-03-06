import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Receipt {
protected static Receipt[] receipts = new Receipt[20];
private static int ReceiptId =0;
 private Date date;
 private int[][] Items;
 private double totalcost;
 private CustomerBonus SpecialClient;



 public Receipt(CustomerBonus SP,int[][] Array) {
 this.date = new Date();
 this.SpecialClient = SP;
 this.Items = Array;
 this.totalcost = 0.00;
 receipts[ReceiptId] = this;
 ReceiptId++;
}
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
public static void getPreviousTransactions() {
	DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	int s;
	for(int i=0;i<ReceiptId;i++) {
		s=0;
		for(int j=0;j<receipts[i].Items.length;j++) {
			s+=receipts[i].Items[j][1];

	 }

		System.out.printf("Date of issue " + dateFormat1.format(receipts[i].date)  +
		                  " Toral Cost " + receipts[i].totalcost + " ,Bought Items  %d%n",s);
}
}

public void getMainBodyOfReceipt() {

	double sum=0;
	for(int i=0;i<Items.length;i++) {
		System.out.print("Product Name : " + Product.Prods[Items[i][0]].getProductName() +
		                 "   Product Price: " + Product.Prods[Items[i][0]].getPrice()
		        + "\n X " + Items[i][1] + "    " + Items[i][1]*Product.Prods[Items[i][0]].getPrice());

		sum+=Items[i][1]*Product.Prods[Items[i][0]].getPrice();

      }
      System.out.println("Cost : " + sum);

 }

public static void BuyItems(CustomerBonus CT){
	int[][] Items = new int[20][2];
	int ItemCnt = 0;
	int PrdCd = 0;
	int PrdQn = 0;
	int AvlQn = 0;


	Scanner Sc = new Scanner(System.in);


	while(PrdCd != -1){
		System.out.print("Enter product code, -1 to end : ");
		PrdCd = Sc.nextInt();
		if(Product.IsValidProd(PrdCd)){
			AvlQn = Product.IsAvailProd(PrdCd);
			if(AvlQn != 0) {
				System.out.print("Enter Quantity : ");
				PrdQn = Sc.nextInt();
				if(PrdQn > AvlQn){
					System.out.println("Max Available quantity : " + AvlQn);
				} else {

					Items[ItemCnt][0] = PrdCd;
					Items[ItemCnt][1] = PrdQn;
					Product.Prods[PrdCd].setAvail( AvlQn - PrdQn);
					ItemCnt++;
				}
			} else {
				String ProdNAME = Product.Prods[PrdCd].getProductName();
				System.out.println("Product " + ProdNAME + " not available...");
			}
		} else {
			if(PrdCd!=-1) {
			System.out.println("Invalid code.");
		}
		}
	}

	if(ItemCnt > -1) {
		int[][] AllItems = new int[ItemCnt][2];
		for(int i=0;i<ItemCnt;i++){
			AllItems[i][0] = Items[i][0];
			AllItems[i][1] = Items[i][1];
		}
		Receipt Rs = new Receipt(CT, AllItems);
		Rs.printReceipt();
	}
}

public void printReceipt() {

	System.out.println("Receipt Id " + this.ReceiptId + " \nDate of issue :  " + dateFormat.format(date));
    for(int i=0;i<Items.length;i++) {
	System.out.printf("Product Name :  %s Product Price: %.2f%n",
	                   Product.Prods[Items[i][0]].getProductName(),
	                   Product.Prods[Items[i][0]].getPrice());

    System.out.printf("      X %d     %.2f%n", Items[i][1], Items[i][1]*Product.Prods[Items[i][0]].getPrice());


    totalcost+=Items[i][1]*Product.Prods[Items[i][0]].getPrice();

	}
	System.out.printf("Cost : %.2f%n" ,totalcost);

	if (SpecialClient==null) {
         System.out.printf("Total Cost : %.2f%n" ,totalcost);
       } else {
		   int pn = SpecialClient.getBonus(totalcost);
		   if(pn>=200) {
		   SpecialClient.Points(pn -200);
		   System.out.println("Today you have earned " + (int)totalcost/3 + " points and you have reached 200 points \n You get 20% discount\n" +
		                      "You now have" + (pn - 200) + "points");
		   totalcost=0.8*totalcost;
		   System.out.printf("Total Cost : %.2f%n", totalcost);
		   } else {
		  System.out.printf("Total Cost : %.2f%n", totalcost);
	   }
	  }


}





 }
