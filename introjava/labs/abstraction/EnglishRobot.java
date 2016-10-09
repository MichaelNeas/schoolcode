/* Michael Neas
 */
public class EnglishRobot extends Robot
{

	public EnglishRobot(String name) 
	{
		super(name);
	}

	@Override
	public void sayHello() 
	{
	System.out.print("Ello, der! ");
	this.sayName();
	}

}
