/* Michael Neas
 */
public class Space 
{
	private String _name;
	private String _description;
	private Portal _portal; //private reference to the Portal class

	public String getName() 
	{
		return _name;
	}
	public void setName(String name) 
	{
		this._name = name;
	}
	public String getDescription() 
	{
		return _description;
	}
	public void setDescription(String description) 
	{
		this._description = description;
	}
	public Portal getPortal() 
	{
		return _portal;
	}
	public void setPortal(Portal portal) 
	{
		this._portal = portal;
	}
	
	public String toString()
	{
		return _name;
	}
	public String toStringLong() //combo of name and its description
	{
		if (_portal != null) //!= not equal to
		{
			return (_name + ":" + " " + _description + " With a " + _portal.toStringLong() + ".");
		}
		else
		{
			return (_name + ":" + " " + _description); //otherwise return the name and description
		}
	}
}
/*Notes:
 * Getters/setters: Get/set object property values to allow private variables without direct alteration
 *        -aka: Accessors/Mutators
 * toString returns the same thing that the getName method returns. 
 * 		  -One is used when you want to know what the name of a Space is. 
 *        -The other is used when you(Java) wants to see what the string representation of a Space is
 */