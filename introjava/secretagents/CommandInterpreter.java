/* Michael Neas
 */
import java.io.File;
import jeff.imagewindow.ImageWindow;
import jeff.textconsole.TextConsole;

public class CommandInterpreter 
{
	private static void _showImage(ImageWindow imageWindow, Agent agent)
	{
		String imageName = agent.getLocation().getSpaceImage();
		File imageDir = new File("data", "images");
		File imageFile = new File(imageDir, imageName);
		imageWindow.loadImage(imageFile);
	}
	public static void run(Agent agent, ImageWindow imageWindow, TextConsole textConsole)
	{
		boolean sneaky = true;
		String input;
		textConsole.println(agent.getName() + " begins his quest in a " + agent.getLocation());
		_showImage(imageWindow, agent);
		do
		{
			textConsole.print("===>");
			input = textConsole.readLine();
			if (input.equals("quit"))
			{
				sneaky = false;
			}
			else if(input.equals("go"))
			{
				agent.usePortal(textConsole);
				_showImage(imageWindow, agent);
			}
			else if(input.equals("help"))
			{
				textConsole.println("The following commands are available: go, help, look, quit, and where");
			}
			else if(input.equals("look"))
			{
				textConsole.println(agent.toStringLong());
			}
			else if(input.equals("where"))
			{
				textConsole.println(agent.getLocation().toStringLong());
			}
			else
			{
				textConsole.println("Sorry, I don't understand the command: " + input);
			}
		}while (sneaky);
		System.exit(0);
	}
}
