// Michael Neas
import robotgrid.Grid;
import robotgrid.GridServer;
import robotgrid.Heading;
import robotgrid.Layer;
import robotgrid.Robot;
import robotgrid.TriggerCell;
import robotgrid.Utils;
import robotgrid.Wall;

public class Test 
{
	public static void main(String[] args)
	{
		GridServer server = new GridServer();
		Grid grid = server.createGrid("Test", 10, 10);
		Robot robot1 = new Robot("Robot 1", Heading.East);
		grid.placeObject(robot1, 0, 0);
		setup(grid);
		//pause a bit before things start to happen
		Utils.sleep(500);
		int moveVar = 0;
		while (moveVar < 8)
		{
			robot1.move();
			moveVar++;
		}
		robot1.turnRight();
		robot1.move();
		robot1.turnRight();
		moveVar = 0;
		while (moveVar < 3)
		{
			robot1.move();
			moveVar++;
		}
		robot1.turnLeft();
		moveVar = 0;
		while (moveVar < 7)
		{
			robot1.move();
			moveVar++;
		}
		robot1.turnLeft();
		robot1.turnLeft();
		moveVar = 0;
		while (moveVar < 6)
		{
			robot1.move();
			moveVar++;
		}
		robot1.turnLeft();
		moveVar = 0;
		while (moveVar < 4)
		{
			robot1.move();
			moveVar++;
		}
	}
	private static void setup(final Grid grid)
	{
		for(int n=0; n<10; n++)
		{
			Wall wall = new Wall();
			grid.placeObject(wall, n, 5);
		}
		Layer layer = grid.getLayer(null);
		TriggerCell tc1 = new TriggerCell(layer, 8, 1, "")
		{
			@Override
			public void activate()
			{
				grid.removeObject(5, 5);
			}
		};
		layer.setCell(8, 1, tc1);
		TriggerCell tc2 = new TriggerCell(layer, 5, 8, "Checkpoint");
		layer.setCell(5, 8, tc2);
		TriggerCell tc3 = new TriggerCell(layer, 1, 2, "Finished!");
		layer.setCell(1, 2, tc3);
	}
}
