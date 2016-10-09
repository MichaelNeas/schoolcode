

package view;

import java.awt.Color;
import gui.Button;
import javax.swing.JPanel;
import controller.Controller;

public class ControlButtons extends JPanel
{
	private static final long serialVersionUID = 1L;

	public ControlButtons(Controller control)
	{
		setLayout(null);
		Button play = new Button(control, "Play");
		play.setSize(60, 30);
		play.setLocation(0, 0);
		play.setBackground(Color.decode("#009900"));
		add(play);
		Button stop = new Button(control, "Stop");
		stop.setSize(60, 30);
		stop.setLocation(70, 0);
		stop.setBackground(Color.decode("#D63030"));
		add(stop);
		this.setSize(130, 30);

	}

}
