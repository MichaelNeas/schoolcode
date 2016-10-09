
package view;

import javax.swing.JPanel;
import controller.Controller;

public class Tracks extends JPanel
{
	public static final int GAP_SIZE = 10; //size of the space separating the squares vertically and horizontally

	private static final long serialVersionUID = 1L;

	NoteSquare[][] soundGrid = null;

	public Tracks(Controller control, int numTracks, int numBeats) //dont need to store cause no methods
	{
		setLayout(null);
		soundGrid = new NoteSquare[numTracks][numBeats];
		int x = 0;
		int y = 0;
		for(int i = 0; i < soundGrid.length; i++)
		{
			x = 0;
			for (int j = 0; j < soundGrid[i].length; j++)
			{
				soundGrid[i][j] = new NoteSquare(control, i+1, j+1);
				soundGrid[i][j].setLocation(x, y);
				add(soundGrid[i][j]);
				x = x + NoteSquare.SIZE + GAP_SIZE;
			}
			y = y + NoteSquare.SIZE + GAP_SIZE;
		}
		this.setSize((NoteSquare.SIZE*numBeats + (GAP_SIZE*(numBeats - 1))),
				(NoteSquare.SIZE*numTracks) + (GAP_SIZE * (numTracks - 1)));
	}

	public void setNoteSquare(int track, int beat, boolean value)
	{
		soundGrid[track-1][beat-1].setValue(value ? 1 : 0);
	}
}
