/* Michael Neas
 */
import java.util.Scanner;
public class CommandInterpreter 
{
	public static void run(Agent agent)
	{
		boolean sneaky = true;
		Scanner kbd = new Scanner(System.in);
		String input;
		System.out.println(agent.toString() + " begins his quest in a " + agent.getLocation().toString());
		while (sneaky)
		{
			System.out.print("===>");
			input = kbd.next();
			if (input.equals("quit"))
			{
				sneaky = false;
			}
			else if(input.equals("go"))
			{
				agent.usePortal();
			}
			else if(input.equals("help"))
			{
				System.out.println("The following commands are available: go, help, look, quit, and where");
			}
			else if(input.equals("look"))
			{
				System.out.println(agent.toStringLong());
			}
			else if(input.equals("where"))
			{
				System.out.println(agent.getLocation().toStringLong());
			}
			else
			{
				System.out.println("Sorry, I don't understand the command: " + input);
			}
		}
	}
}
