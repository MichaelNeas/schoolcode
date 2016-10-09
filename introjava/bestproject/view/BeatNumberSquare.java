

package view;

import gui.Box;

public class BeatNumberSquare extends Box
{
	private int _beatNumb;
	private boolean _state;
	private static final long serialVersionUID = 1L;

	BeatNumberSquare(int beatNumb)
	{
		super();
		_beatNumb = beatNumb;
		_state = false;
		super.setText(Integer.toString(beatNumb));
		super.setForeground(Colors.BEATNUMBER_OFF_FG);
		super.setBackground(Colors.BEATNUMBER_OFF_BG);
		super.setSize(NoteSquare.SIZE,NoteSquare.SIZE);
		super.invalidate();
	}
	public void setState(boolean state)
	{
		this._state = state;
		if(state == true)
		{
			super.setForeground(Colors.BEATNUMBER_ON_FG);
			super.setBackground(Colors.BEATNUMBER_ON_BG);
		}
		else
		{
			super.setForeground(Colors.BEATNUMBER_OFF_FG);
			super.setBackground(Colors.BEATNUMBER_OFF_BG);
		}
	}
	public boolean getState()
	{
		return _state;
	}

	public int getBeatNumb()
	{
		return _beatNumb;
	}
}
