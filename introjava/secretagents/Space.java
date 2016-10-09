/* Michael Neas
 */
public class Space 
{
	private String _name;
	private String _description;
	private Portal _portal; //private reference to the Portal class
	private String _spaceImage;
	//full, simple constructor with parameters for all the member variables
	public Space(String name, String description, Portal portal, String spaceImage)
	{
		_name = name;
		_description = description;
		_portal = portal;
		_spaceImage = spaceImage;
	}
	public String getSpaceImage()
	{
		return _spaceImage;
	}
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
		//String a = this._name + ":" + " " + this._description;
		if (_portal != null) //!= not equal to
		{
			return (this._name + ":" + " " + this._description + " With a " + getPortal().toStringLong() + ".");
		}
		else
		{
			return (this._name + ":" + " " + this._description + " with no connected portal"); //otherwise return the name and description
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