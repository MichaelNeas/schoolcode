

package view;

import java.awt.Color;
import gui.Button;
import gui.TextBox;
import javax.swing.JPanel;
import controller.Controller;


public class Header extends JPanel
{
	private TextBox txtbox;

	private static final long serialVersionUID = 1L;

	public Header(Controller control)
	{
		setLayout(null);
		txtbox = new TextBox("Unnamed", "Enter a new name:", "Change name");
		txtbox.setSize(200, 30);
		txtbox.setLocation(0, 0);
		txtbox.setBackground(Color.decode("#34CFBD"));
		add(txtbox);
		Button load = new Button(control, "Load");
		load.setSize(60, 30);
		load.setLocation(txtbox.getWidth() + 10, 0);
		load.setBackground(Color.decode("#34CFBD"));
		add(load);
		Button save = new Button(control, "Save");
		save.setSize(60, 30);
		save.setLocation(load.getX() + load.getWidth() + 10,0);
		save.setBackground(Color.decode("#34CFBD"));
		add(save);
		Button quit = new Button(control, "Quit");
		quit.setSize(60, 30);
		quit.setLocation(save.getX() + load.getWidth() + 40,0);
		quit.setBackground(Color.decode("#34CFBD"));
		add(quit);
		this.setSize(quit.getX() + quit.getWidth(), 30);
	}

	public String getFileName()
	{
		return txtbox.getText();
	}
	public void setFileName(String name)
	{
		txtbox.setText(name);
	}
}
