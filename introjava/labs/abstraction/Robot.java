/* Michael Neas
 */
public abstract class Robot //polymorphic!
{
	private String _name;
	public Robot(String name)
	{
		setName(name);
	}
	public void sayName()
	{
		System.out.println("My name is " + this.getName());
	}
	public abstract void sayHello();
	
	public String getName() 
	{
		return _name;
	}
	public void setName(String _name) 
	{
		this._name = _name;
	}
	
}
