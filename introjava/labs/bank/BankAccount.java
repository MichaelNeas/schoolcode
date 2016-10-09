import java.util.Scanner;

/* Michael Neas
 */
public class BankAccount 
{
	private int _balance = 0; //public -> private
	private String _password = "secret";//need to protect under all circumstances (defensive)
	private Scanner kbd = null;
	public void showBalance()
	{
		System.out.println("This balance is " + get_balance());
	}
	public boolean checkPassword() //when called it asks user to type a password which may or may not match
	{
		//Scanner kbd = new Scanner(System.in);
		System.out.print("Enter password: ");
		String guess = kbd.nextLine();
		//kbd.close();
		if (guess.equals(_password))
		{
			System.out.println("Password correct");
			return true;
		}
		else
		{
			System.out.println("Password incorrect");
			return false;
		}
	}
	public int get_balance() 
	{
		return _balance; //returns to the user, other methods
	}
	public BankAccount(){
		kbd = new Scanner(System.in);

	}
	public void set_balance(int balance) //assignment of balance -> previous _balance
	{ 
		if(this.checkPassword()) //all password checking takes place in BankAccount
		{
		this._balance = balance; //this. means the instance of BankAccount that method was called on
		}
	} //ba does not exist in BankAccount
	public void changePassword()
	{
		if(this.checkPassword())
		{
			//Scanner kbd = new Scanner(System.in);
			System.out.print("Enter new password: ");
			_password = kbd.nextLine();
			//kbd.close();
		}
		else
		{
			return;
		}
	}
	
	public void finalize(){
		if(kbd != null){
			kbd.close();
		}
	}
	
}
