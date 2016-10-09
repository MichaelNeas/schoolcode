import java.util.Scanner;

public class Balloon 
{
	public static void main(String[] args)
	{
	Scanner kbd = new Scanner(System.in); //creating a new instance of scanner from the import
	double start;
	double end;
	double sampleInterval; //automatically set to 0
	System.out.print("Please enter the start time: ");
	start = kbd.nextDouble();
	System.out.print("Please enter the end time: ");
	end = kbd.nextDouble();
	System.out.print("Please enter the sample interval: ");
	sampleInterval = kbd.nextDouble();
	double storeAltitude; //store these variables outside to be updated inside for loop
	double storeVelocity;
	double i;
		for(i = start; i <= end; i += sampleInterval)
		{
			storeAltitude = Balloon.altitude(i);
			storeVelocity = Balloon.velocity(i) / 3600; //calling other methods inside Balloon class
			System.out.println(i + "\t" + storeAltitude + "\t" + storeVelocity); //"\t" is a tab
		}
	}
	public static double altitude(double t)
	{
		double alt = -.12*Math.pow(t, 4) + 12*Math.pow(t, 3)- 380*Math.pow(t, 2) + 4100*t + 220;
		return alt; //value returning
	}
	public static double velocity(double t)
	{
		double vel = -.48*Math.pow(t, 3) + 36*Math.pow(t, 2) - 760*t + 4100;
		return vel; //Math.pow allows for a variable, power
	}
	
}