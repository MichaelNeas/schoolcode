

package model;

import message.ISubscriber;
import message.Message;

public class SoundPlayer implements ISubscriber
{

	private SoundBank _soundBank;
	private int _soundNumber;

	public SoundPlayer(SoundBank soundBank, int soundNumber)
	{
		_soundBank = soundBank;
		_soundNumber = soundNumber;
	}

	@Override
	public void notify(Message message)
	{
		_soundBank.play(_soundNumber);
	}

}
