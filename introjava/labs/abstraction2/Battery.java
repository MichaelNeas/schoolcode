
public class Battery {
	private int _minutes;
	public void Recharge()
	{
		setMinutes(30);
	}
	public void Recharge(int numberMinutes)
	{
		setMinutes(numberMinutes);
	}
	public Battery()
	{
		this.Recharge();
	}
	public Battery(int batteryMinutes)
	{
		setMinutes(batteryMinutes);
	}
	public int getMinutes() {
		return _minutes;
	}
	public void setMinutes(int _minutes) {
		this._minutes = _minutes;
	}
}
