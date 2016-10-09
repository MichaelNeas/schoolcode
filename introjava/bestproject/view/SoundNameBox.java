

package view;

import java.awt.event.MouseEvent;
import controller.Controller;
import gui.Box;

public class SoundNameBox extends Box
{
	private Controller _control;
	private int _trackNumb;

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 160;
	public static final int HEIGHT = 30;

	public SoundNameBox(Controller control, int trackNumb)
	{
		super();
		_control = control;
		_trackNumb = trackNumb;
		this.setSize(WIDTH, HEIGHT);
	}

	@Override
	public void mousePressed(MouseEvent mev)
	{
		super.getText();
		SoundChooser.open(_control, _trackNumb, super.getText());
	}
}
