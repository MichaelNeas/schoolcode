

package view;

import java.awt.Color;
import javax.swing.JPanel;
import controller.Controller;

public class SoundBank extends JPanel
{
	private SoundNameBox[] _numTracksStore;

	private static final long serialVersionUID = 1L;

	public SoundBank(Controller control, int numTracks)
	{
		setLayout(null);
		int y = 0;
		_numTracksStore = new SoundNameBox[numTracks];
		for(int i = 0; i < numTracks; i++)
		{  
			_numTracksStore[i] = new SoundNameBox(control, i);
			_numTracksStore[i].setLocation(0, y);	
			y = y + Tracks.GAP_SIZE + SoundNameBox.HEIGHT;
			_numTracksStore[i].setBackground(Color.decode("#FFA500"));
			add(_numTracksStore[i]);
		}
		this.setSize(SoundNameBox.WIDTH,SoundNameBox.HEIGHT*numTracks + Tracks.GAP_SIZE*numTracks);
	}

	public void setSoundName(int trackNumb, String soundName)
	{
		_numTracksStore[trackNumb].setText(soundName);
	}

	public String toString()
	{
		return _numTracksStore.toString();
	}
}
