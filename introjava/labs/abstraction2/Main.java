
public class Main 
{
	public static void main(String[] args)
	{
		Robot r1 = new Robot();
		Robot r2 = new Robot("Hector", 2, 20);
		System.out.println(r1.getMinutes());
		System.out.println(r2.getMinutes());
		
	}
}
