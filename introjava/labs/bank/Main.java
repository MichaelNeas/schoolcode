
public class Main {
	public static void main(String[] args)
	{
		BankAccount ba = new BankAccount();
		ba.changePassword();
		//ba.showBalance();
		//if(ba.checkPassword()){}
		//System.out.println("Hey, you. The password is " + ba._password); --- shows pw when public
		ba.set_balance(ba.get_balance() + 100);
		ba.showBalance();
		
	}
}
