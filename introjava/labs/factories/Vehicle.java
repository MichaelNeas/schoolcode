public abstract class Vehicle 
{
	private String _color;
	
	public Vehicle(String color)
	{
		_color = color;
	}

	@Override
	public String toString()
	{
		return _color + " vehicle";
	}
	
	public String getColor() {
		return _color;
	}

	public void setColor(String _color) {
		this._color = _color;
	}
}
