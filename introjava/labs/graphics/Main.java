import javax.swing.SwingUtilities;

public class Main
{
	public static void main(String[] args)
	{
		// Swing graphics should be done in a concurrently-running
		// thread. This creates just such a thread.
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// The new thread starts running in this method.
				// All this method does is call the _setup method. 
				_setup();
			}
		});
	}
	private static void _setup()
	{
		GraphicsWindow gWindow = new GraphicsWindow();
		Square sq = new Square(20, 20, 100);
		Square sq1 = new Square(140, 140, 50);
		Square sq2 = new Square(100, 300, 15);
		gWindow.addShape(sq);
		gWindow.addShape(sq1);
		gWindow.addShape(sq2);
		
	}
}