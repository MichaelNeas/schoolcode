import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
public class GraphicsWindow extends JFrame
{
	public DrawingPanel useDrawingPanel;
	private static final long serialVersionUID = 1L;

	public GraphicsWindow()
	{
		this.setSize(600, 400);
		useDrawingPanel = new DrawingPanel();
		this.setContentPane(useDrawingPanel);
		this.setVisible(true); //this needs to be after it is set so then it is visible after initial drawing panel
	}

	public void addShape(Shape shape) 
	{
		useDrawingPanel.addShape(shape);
	}
}