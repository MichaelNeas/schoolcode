

package view;

import java.awt.event.MouseEvent;
import controller.Controller;
import gui.Box;
import view.Colors;

public class NoteSquare extends Box 
{
	public static final int SIZE = 30; //constant 30x30 throughout the project
	private Controller _control;
	private int _trackNumb;
	private int _beatNumb;
	private int _value;
	private static final long serialVersionUID = 1L;

	public NoteSquare(Controller control, int trackNumb, int beatNumb)
	{
		super();//invokes Box
		_control = control;
		_trackNumb = trackNumb;
		_beatNumb = beatNumb;
		_value = 0;
		this.setSize(SIZE,SIZE);
		this.setBackground(Colors.NOTESQUARE_OFF);
	}
	public int getTrackNumb() 
	{
		return _trackNumb;
	}
	public int getBeatNumb()
	{
		return _beatNumb;
	}
	public int getValue()
	{
		return _value;
	}
	public void setValue(int value)
	{
		this._value = value;
		if(value == 0)
		{
			super.setBackground(Colors.NOTESQUARE_OFF);
		}
		else
		{
			super.setBackground(Colors.NOTESQUARE_ON);
		}
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent mev)
	{
		if(_value == 0)
		{
			setValue(1);
		}
		else
		{
			setValue(0);
		}
		_control.noteSquareClicked(this);
	}

	@Override
	public String toString()
	{
		return "NoteSquare(Track = " + getTrackNumb() + ", Beat =" + getBeatNumb() + ")";
	}
}
