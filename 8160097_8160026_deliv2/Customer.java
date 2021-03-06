import java.util.Scanner;
public class Customer {

 private String username;
 private int password;
 private String FullName;
 private static int CustomerCode = -1;
 protected static Customer[] Custom = new Customer[20];

  public Customer( String username, int password, String FullName) {

    this.username = username;
    this.password = password;
    this.FullName = FullName;

    CustomerCode++;
    Custom[CustomerCode] = this;

 }

public static Customer LogCus(){

	Boolean Login = false;
	Scanner Sc = new Scanner(System.in);
		while(!Login) {
			System.out.print("Username:");
			String Username = Sc.nextLine();

			System.out.print("Password:");
			int PassWord = Sc.nextInt();
			Sc.nextLine();


	 //Loop for all entries
			for(int i = 0;i<=CustomerCode;i++) {
		  		if (Username.equals(Custom[i].username) && PassWord == Custom[i].password) {
					System.out.println("Welcome " + Custom[i].FullName);
		   			Login = true;
		   			return Custom[i];
		   		}
			   }
		   		if(!Login) {
					System.out.println("Wrong combination. Try again");
			    }

	 //Test if a match was found


	}//End while
 return null;
}



  public void setUsername(String username) {

    this.username = username;
  }

  public String getUsername() {

   return this.username;
  }

  public void changePassword() {
  Scanner Sc = new Scanner(System.in);

  System.out.println("Change Password");
  System.out.print("Type in new Password:");
  int x = Sc.nextInt();
  System.out.print("Type new Password again:");
  int j = Sc.nextInt();
  if (x==j) {
	 setPassword(x);
	 System.out.println("Pasword has changed");
 } else {
	 System.out.println("Passwords Dont match");
}
}



  public void setPassword(int password) {

    this.password = password;
  }

  public int getPassword() {

   return this.password;
  }

  public void setFullname(String FullName) {

   this.FullName = FullName;
  }

  public String getFullname() {

   return  this.FullName;
   }

   public static int getCustomerCode() {

     return CustomerCode;
   }
   public void CustomerCode1() {
	 this.CustomerCode++;
}

}