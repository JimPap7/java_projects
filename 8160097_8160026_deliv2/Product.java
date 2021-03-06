public class Product {

 private int PrdCode;
 private String ProductName;
 private double Price;
 private int Avail;
 private static int productCode = -1;
 protected static Product Prods[] = new Product[20];

  public Product(String ProductName, double Price, int Avail) {


   productCode++;

   this.PrdCode = productCode;
   this.ProductName = ProductName;
   this.Price = Price;
   this.Avail = Avail;

   Prods[productCode] = this;
   }

   public void setProductName(String ProductName) {

     this.ProductName = ProductName;
   }

   public static int getProduct(int s) {
	   for(int i=0;i<=productCode;i++) {
		if(Prods[i].PrdCode == s) {
		  return i;

        }
       }
       return -1;

  }


   public String getProductName() {

    return this.ProductName;
   }

   public void setPrice(int Price) {

    this.Price = Price;
   }

   public double getPrice() {

    return this.Price;
   }

   public void setAvail(int Avail) {

     this.Avail = Avail;
   }

   public int getAvail() {

    return this.Avail;
   }

   public static int  getProductCode() {
	   return productCode;
}

public static boolean IsValidProd(int TstPr){

	for(int i=0;i<=productCode;i++) {
		if(Prods[i].PrdCode == TstPr) {
			return true;
		}
	}
	return false;
}

public static int IsAvailProd(int TstPr){

	for(int i=0;i<=productCode;i++) {
		if(Prods[i].PrdCode == TstPr) {
			return Prods[i].Avail;
		}
	}
	return -1;
}


  public static void getProducts(int k) {
	  for(int i=0;i<=productCode;i++) {
		  if(k==0) {

	     System.out.println("Product [id= " + (i+1) + ",Name =" + Prods[i].getProductName() + ", Price =" + Prods[i].getPrice() + ",Available quantity =" + Prods[i].getAvail() + "]");
   } else {
	     if(Prods[i].getAvail()!=0) {
	     System.out.println("Product [id= " + (i+1) + ",Name =" + Prods[i].getProductName() + ", Price =" + Prods[i].getPrice() + ",Available quantity =" + Prods[i].getAvail()+ "]");
	 }
 }
}
 }

}