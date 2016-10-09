

package view;

import gui.NumberSlider;
import controller.Controller;

public class View
{
	public  static final int TRACK_HEIGHT = 40; // this one must be public
	private BeatNumbers _beatNumbers;
	private SoundBank _soundBanky;
	private Header _header;
	private NumberSlider _numberSlider;
	private Window window;
	private Tracks tracks;
	private static final int LEFT_MARGIN  = 20;
	private static final int TOP_MARGIN   = 20;

	public View(Controller controller, int numTracks, int numBeats)
	{
		window = new Window(controller, "Rhymininator 2000");
		window.setSize(900, 500);

		_header = new Header(controller); 
		_header.setLocation(10,10);

		_soundBanky = new SoundBank(controller, 6);
		_soundBanky.setLocation(10, 50);

		ControlButtons cb = new ControlButtons(controller);
		cb.setLocation(10,290);

		tracks = new Tracks(controller, 6, 16);
		tracks.setLocation(180, 50);

		_beatNumbers = new BeatNumbers(16);
		_beatNumbers.setLocation(180, 290);

		_numberSlider = new NumberSlider(controller, "number", 10, 200, 100);
		_numberSlider.setLocation(180, 340);

		window.add(_soundBanky);
		window.add(_beatNumbers);
		window.add(_header);
		window.add(tracks);
		window.add(cb);
		window.add(_numberSlider);
		window.setVisible(true);
	}

	public void setNoteSquare(int track, int beat, boolean value)
	{
		tracks.setNoteSquare(track, beat, value);
	}

	public void setBeatNumber(int beatNum)
	{
		_beatNumbers.setBeatNumber(beatNum);
	}

	public void clearBeatNumbers()
	{
		_beatNumbers.clear();
	}

	public void setSoundName(int trackNum, String soundName)
	{
		_soundBanky.setSoundName(trackNum, soundName); 
	}

	public static int getLeftMargin() 
	{
		return LEFT_MARGIN;
	}

	public static int getTopMargin() 
	{
		return TOP_MARGIN;
	}
	public String getHeader()
	{
		System.out.println(_header.getFileName());
		return null;
	}
	public void setHeader(String name)
	{
		_header.setFileName(name);
	}

	public NumberSlider getNumberSlider()
	{
		System.out.println(_numberSlider.getValue());
		return _numberSlider;
	}

	public void update(){
		this.window.repaint();
	}

	public void setNumberSlider(int sliderValue) {
		_numberSlider.setValue(sliderValue);
	}

}
