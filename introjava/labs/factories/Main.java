
public class Main 
{
	public static void main(String[] args)
	{
		VehicleFactory vf = new VehicleFactory();
		Vehicle v = vf.create("car");
		System.out.println("main method got a " + v);
		VehicleFactory redFactory = new VehicleFactory("red");
		Vehicle redBoat = redFactory.create("boat");
		System.out.println("main method got a " + redBoat);
		VehicleFactory blackFactory = new VehicleFactory("black");
		Vehicle bc = blackFactory.create("convertible");
		System.out.println("main method got a " + bc);
		Vehicle except = blackFactory.create("exception");
		System.out.println("main method got a " + except);
	}
}
