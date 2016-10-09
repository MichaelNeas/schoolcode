/* Michael Neas
 */

public class Main 
{
	public static void main(String[] args) //need method to call on the other class
	{	
		File configFile = new File("data", "config.ini");
		ConfigLoader cfig = new ConfigLoader(configFile);
		ImageWindow storeImageWindow = new ImageWindow(); //new instance of the imported image window
		TextConsole bringToFront = TextConsole.create();
		Agent me = cfig.buildAll();
		CommandInterpreter.run(me, storeImageWindow, bringToFront);//inspired largely by Jeff
	}
}
