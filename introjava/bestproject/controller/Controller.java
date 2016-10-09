/*Rhy
 *Michael Neas
 */

package controller;

import gui.Button;
import gui.Dialog;
import message.ISubscriber;
import message.Message;
import model.Clock;
import model.Model;
import view.NoteSquare;
import view.View;
import java.util.Scanner;

public class Controller implements ISubscriber
{
	private Model _model;
	private Clock _clock;
	private View  _view;
	private static Scanner kbd;

	public Controller(Clock clock, Model model)
	{
		_model = model;
		_clock = clock;
		_clock.subscribe(this);
	}

	public void buttonPressed(Button button)
	{
		if(button.getText().equals("Save"))
			_buttonSave();
		else if(button.getText().equals("Load"))
			_buttonLoad();
		else if(button.getText().equals("Play"))
		{
			_model.startPlaying();
			System.out.println("Play the Rhythminator 2000!");
		}
		else if(button.getText().equals("Stop"))
		{
			_model.stopPlaying();
			_view.clearBeatNumbers();
			System.out.println("Stop the genius!");
		}
		else if(button.getText().equals("Quit"))
			_buttonQuit();
		else
			System.out.println("Controller.buttonPressed " + button + " pressed");
	}

	private void _buttonQuit()
	{
		if(Dialog.askYesNo("Exiting program", "Really quit?"))
			System.exit(0);
	}

	private void _buttonLoad()
	{
		System.out.println("Please copy and paste your saved text: ");
		kbd = new Scanner( System.in );
		// first line

		String name = kbd.nextLine();
		_view.setHeader(name);

		for(int i = 0; i < _model._numTracks; i++){
			String trackName = kbd.nextLine();
			if(trackName.equalsIgnoreCase("No Sound"))
			{}
			else
			{
				_model.setSoundName(i, trackName);
				_view.setSoundName(i, trackName);
			}
		}

		for(int track = 0 ; track < _model._numTracks; track++){
			String trackLine = kbd.nextLine();
			String[] beats = trackLine.split(" ");
			for(int beat = 0 ; beat < beats.length; beat++){
				boolean value = Integer.parseInt(beats[beat]) == 0 ? false : true;
				_model.setNote(track + 1, beat + 1, value);
				_view.setNoteSquare(track + 1, beat  + 1, value );
			}

			if(track == _model._numTracks-1)
			{
				String[] strings = trackLine.split(" ");
				trackLine = strings[0];
			}
		}

		String sliderValue = kbd.nextLine();
		_view.setNumberSlider(Integer.parseInt(sliderValue));
	}

	private void _buttonSave()
	{
		System.out.println("~~~~Copy And Paste Below in A Text File~~~~~");
		_view.getHeader();
		_model.getTracks();
		_model.getAllNotes();
		_view.getNumberSlider();

		//System.out.println("Controller._buttonSave called");
	}

	public void keyPressed(int keyCode)
	{
		//System.out.println("Controller.keyPressed " + keyCode);
	}

	public void keyReleased(int keyCode)
	{
		//System.out.println("Controller.keyReleased " + keyCode);
	}

	public void keyTyped(char keyChar)
	{
		//System.out.println("Controller.keyTyped '" + keyChar + "'");
	}

	public void noteSquareClicked(NoteSquare noteSquare)
	{
		boolean value;
		if(noteSquare.getValue() == 0)
			value = false;
		else
			value = true;

		_model.setNote(noteSquare.getTrackNumb(), noteSquare.getBeatNumb(), value);
		//System.out.println("Controller.noteSquareClicked " + noteSquare);
	}

	@Override
	public void notify(Message message)
	{
		_view.setBeatNumber(_model.getBeatNumber() - 1);
		//System.out.println("Count " + _model.getBeatNumber());
	}

	public void soundNameSelected(int trackNumber, String soundName)
	{
		_model.setSoundName(trackNumber, soundName); 
		_view.setSoundName(trackNumber, soundName); 
		System.out.println("You've chosen slot: " + trackNumber + " for the sound: " + soundName);
		//System.out.println("Controller.soundNameSelected for track " +  trackNumber + ": " + soundName);
	}

	public void setView(View view)
	{
		_view = view;
	}

	public void sliderChange(String name, int value)
	{
		_clock.stop();
		long delay = (long)((15.0/(double)value)*1000);
		_clock.setDelay(delay);
		_clock.start();
		//System.out.println("Controller.sliderChange " + name + " = " + value);
	}
}
