
package view;

import javax.swing.JPanel;

public class BeatNumbers extends JPanel
{
	private BeatNumberSquare[] _bnsInstance;
	private static final long serialVersionUID = 1L;

	public BeatNumbers(int numBeats)
	{
		setLayout(null);
		int x = 0;
		_bnsInstance = new BeatNumberSquare[numBeats];
		for(int i = 0; i < numBeats; i++)
		{
			_bnsInstance[i] = new BeatNumberSquare(i+1);
			_bnsInstance[i].setLocation(x, 0);	
			x = x + NoteSquare.SIZE + Tracks.GAP_SIZE;
			add(_bnsInstance[i]);
		}
		setSize(numBeats*NoteSquare.SIZE + numBeats*Tracks.GAP_SIZE,30);
	}

	public void setBeatNumber(int beatNumb)
	{
		if(_bnsInstance[beatNumb].getState() == false)
		{
			clear();
			_bnsInstance[beatNumb].setState(true);
		}
	}

	public void clear()
	{
		for (BeatNumberSquare beatNumb : _bnsInstance)
			beatNumb.setState(false);
	}

	public String toString()
	{
		return _bnsInstance.toString();
	}
}
