/* Michael Neas
 */
public class Robot 
{
	private String _name;
	private int _serialNumber;
	private Battery _powerSource;
	private Gripper _gripper;
	
	public Robot()
	{
		_name = "";
		_serialNumber = -1;
		_powerSource = new Battery();
		_gripper = new Gripper();
		_powerSource.Recharge();
	}
	public Robot(String name, int serialNumber, int batteryMinutes)
	{
		_name = name;
		_serialNumber = serialNumber;
		_powerSource = new Battery(batteryMinutes);
	}
	public int getMinutes() {
		return this._powerSource.getMinutes();
	}
}
