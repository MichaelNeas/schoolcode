/* Michael Neas
 */
public class Main 
{
	public static void main(String[] args) //need method to call on the other class
	{
		Space dream = new Space();
		dream.setName("Dream state");
		dream.setDescription("Poofy clouds everywhere!");
		
		Space alley = new Space();
		alley.setName("an alleyway");
		alley.setDescription("It's dark and scary!");
		
		Space museum = new Space();
		museum.setName("The Hermitage");
		museum.setDescription("The worlds largest museum.");
		
		Space secretRoom = new Space();
		secretRoom.setName("The Diamond Room");
		secretRoom.setDescription("Incredibly high security, but you're a ninja so it should be okay. There's diamonds EVERYWHERE!");
		
		Space jail = new Space();
		jail.setName("Vladamir Central Prison");
		jail.setDescription("Cells everywhere, you goofed up.");
		
		
		
		Portal visual = new Portal();
		visual.setName("portal");
		visual.setDirection("in a direction unknown");
		visual.setDestination(alley);
		dream.setPortal(visual);
		
		Portal door = new Portal();
		door.setName("door");
		door.setDirection("in");
		door.setDestination(museum);
		alley.setPortal(door);
		
		Portal stairs = new Portal();
		stairs.setName("stairs");
		stairs.setDirection("down");
		stairs.setDestination(secretRoom);
		museum.setPortal(stairs);
		
		Portal steal = new Portal();
		steal.setName("choice");
		steal.setDirection("directly");
		steal.setDestination(jail);
		secretRoom.setPortal(steal);
		
		Portal imagine = new Portal();
		imagine.setName("magic carpet");
		imagine.setDirection("back");
		imagine.setDestination(dream);
		jail.setPortal(imagine);
		
		
		
		Agent me = new Agent();
		me.setName("Mike");
		me.setLocation(dream);
		
		CommandInterpreter.run(me);
		
		
		
		
		
		
//		Assignment tests
//		Space classroom = new Space();
//		classroom.setName("classroom");
//		classroom.setDescription("a large lecture hall");
//		Space sidewalk = new Space();
//		sidewalk.setName("sidewalk");
//		sidewalk.setDescription("a plain concrete sidewalk with weeds growing through the cracks");
//
//		Portal door = new Portal();
//		door.setName("door");
//		door.setDirection("outside");
//		door.setDestination(sidewalk);
//		classroom.setPortal(door);
//
//		Agent student = new Agent();
//		student.setName("Harry Potter");
//		student.setLocation(classroom); //needs directions
//		System.out.println(student.toStringLong());
//
//		student.usePortal();
//		System.out.println(student.toStringLong());
//		
//		CommandInterpreter.run(student);
		
//		Personal Testing
//		Portal bridge = new Portal();
//		bridge.setName("baldwin bridge");
//		bridge.setDestination("Old Saybrook");
//		bridge.setDirection("over the water");
//		System.out.print(bridge.toStringLong());
//		System.out.println(bridge.toString());

	}
}
