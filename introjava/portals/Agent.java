/* Michael Neas
 */
public class Agent 
{
	private Space _location;
	private String _name;
	public Space getLocation() 
	{
		return _location;
	}
	public void setLocation(Space location) 
	{
		this._location = location;
	}
	public String getName() 
	{
		return _name;
	}
	public void setName(String name) 
	{
		this._name = name;
	}
	public String toString()
	{
		return _name;
	}
	public String toStringLong()
	{
		return (_name + " is in " + _location);
	}
	public Boolean usePortal()
	{
		if(_location.getPortal() != null)
		{
			System.out.println(this._name + " is moving from " + this._location + " to " + _location.getPortal().getDestination());
			_location.getPortal().transport(this);
			return true;
		}
		else
		{
			return false;
		}
	}
}