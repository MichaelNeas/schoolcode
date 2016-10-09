

package model;

import message.Sequencer;

public class Model
{

	public int _numTracks;
	public int _numBeats;
	private SoundBank _soundBank;
	private Sequencer _sequencer;
	private Chord[] _chords;
	private Clock _clock;

	/**
	 * The model sets up the clock.
	 * Most of the work of running the application happens in the clock.
	 * @param numTracks
	 * @param numBeats
	 */
	public Model(int numTracks, int numBeats)
	{
		_numTracks = numTracks;
		_numBeats = numBeats;
		_sequencer = new Sequencer(numTracks);
		Sound[] arrayOfSounds = new Sound[numBeats];
		_soundBank = new SoundBank(arrayOfSounds);		
		_chords = new Chord[numBeats];
		for(int i = 0; i < _chords.length; i++)
		{
			Chord c = new Chord(_soundBank, numTracks);
			this._chords[i] = c;
			_sequencer.subscribe(_chords[i]);
		}
		_clock = new Clock();
		_clock.subscribe(_sequencer);
	}

	public int getBeatNumber()
	{
		return _sequencer.getStepNumber();
	}

	public Clock getClock()
	{
		return _clock;
	}

	public void startPlaying()
	{
		_clock.start();
	}

	public void stopPlaying()
	{
		_clock.stop();
		_sequencer.reset();
	}

	public void setNote(int trackNum, int beatNum, boolean value) 
	{
		_chords[beatNum - 1].setNote(trackNum, value);
	}

	public void setSoundName(int trackNum, String soundName)
	{
		_soundBank.setSound(trackNum, new Sound(soundName));
	}

	public void getTracks()
	{
		for (int i = 0; i<_numTracks; i++)
			if(_soundBank.getSounds()[i] == null)
				System.out.println("No Sound");
			else
			{
				Sound tryBank = _soundBank.getSounds()[i];
				String tryBankString = tryBank.toString();
				if (tryBankString.indexOf(".") > 0)
					tryBankString = tryBankString.substring(0, tryBankString.lastIndexOf("."));
				System.out.println(tryBankString);
			}
	}

	public void getAllNotes()
	{
		for (int numTracks = 0; numTracks < _numTracks; numTracks++)
		{
			for (int numBeats = 0; numBeats < _numBeats; numBeats++)
			{
				boolean noteValue = getChords()[numBeats].getNotes()[numTracks];
				System.out.print( (noteValue ? 1 : 0) + " ");
			}
			System.out.println();
		}
	}

	public Chord[] getChords()
	{
		return _chords;
	}
}
