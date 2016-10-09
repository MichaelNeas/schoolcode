/* Michael Neas
 */
public class Main 
{
	public static void main(String[] args)
	{
		Robot r1 = new EnglishRobot("Eve");
		r1.sayHello();
		Robot r2 = new FrenchRobot("Mandroid");
		r2.sayHello();
		Robot r3 = new GermanRobot("Hector");
		r3.sayHello();
	}
}
