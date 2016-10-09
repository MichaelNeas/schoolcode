
public class VehicleFactory 
{
	private String _color;

	public VehicleFactory()
	{
		_color = "white";
	}

	public VehicleFactory(String color)
	{
		_color = color;
	}

	public Vehicle create(String vehicleType)
	{
		Vehicle vc = null;
		if(vehicleType.equals("car"))
		{
			vc = new Car(_color);
		}
		else if(vehicleType.equals("boat"))
		{
			vc = new Boat(_color);
		}
		else if(vehicleType.equals("convertible"))
		{
			vc = new Convertible(_color);
		}
		else
		{
			//throw new RuntimeException("vehicle type not understood:" + vehicleType);
			//vc = null;
			vc = new Car("white");
		}
		System.out.println("VehicleFactory created a " + vc);
		return vc;  //works because vehicle is converted to a string then is called
	}
}
