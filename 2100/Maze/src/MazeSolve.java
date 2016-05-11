/*Maze
 *CSE2100 Project 6, Fall 2014
 *Michael Neas
 *12/07/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

import java.util.ArrayList;
import java.util.Collection;

public class MazeSolve //main class
{
	private static MazeConstruct _constructMaze = new MazeConstruct();
	private static ArrayList<Integer> _asteriskPath = new ArrayList<Integer>();

	public static void main(String[] args)
	{
		_constructMaze.getStartingInfo();
		_constructMaze.initialGraph(_constructMaze.getSize());
		System.out.println();
		System.out.println("Initial maze with " + _constructMaze.getInternalGraph().setOfVertices().size() + " vertices and " 
				+ _constructMaze.getInternalGraph().setOfEdges().size() + " edges");
		_constructMaze.drawPreRemoval(_constructMaze.getSize());
		AdjacencyListGraph _pathMaze = new PrimJarnik().sortThisGraph(_constructMaze.getInternalGraph());
		System.out.println("\n" + "\n" + "Using prims algorithm for the minimum amount of edges to have the graph still connected,"
				+ " the maze now has " + _pathMaze.setOfVertices().size() + " vertices and " 
				+ _pathMaze.setOfEdges().size() + " edges");
		_constructMaze.drawMaze(_pathMaze);
		//System.out.println(_constructMaze.findStartVertex() + " " + _constructMaze.findFinishVertex());
		solveMaze(_pathMaze);
		//System.out.println(_asteriskPath); check vertices that make up the path
		assignAsterisk(_asteriskPath, _pathMaze);
		AdjacencyListGraph _completedMaze = _pathMaze;
		System.out.println("\n" + "\n" + "The maze has been solved!");
		_constructMaze.drawMaze(_completedMaze);
		_constructMaze.mazeVars.close();
		System.out.println("\n" + "\n" + "--[Michael Neas]--");
	}

	public static void assignAsterisk(ArrayList<Integer> aList, AdjacencyListGraph graph)
	{
		for(int q = 0;q < aList.size();q++)
			for(int r = 0; r < graph.numVertices(); r++) //goes through the list of truly assigned connections that form the path
			{
				int indexMatch = aList.get(q);
				if(graph.setOfVertices().get(r).getIndex() == indexMatch) //when it finds the match the boolean in vertex is set to true
					graph.setOfVertices().get(r).setAsteriskTrue();
			}
	}
	public static void solveMaze(AdjacencyListGraph graph)
	{
		int startingVertex = _constructMaze.findStartVertex(graph);
		DFS depthFirstSearch = new DFS(graph,startingVertex);
		int finishingVertex = _constructMaze.findFinishVertex(graph);
		//System.out.println(depthFirstSearch.pathTo(finishingVertex)); iteratable list
		_asteriskPath.addAll((Collection<? extends Integer>) depthFirstSearch.pathTo(finishingVertex)); //change iterable to collection
	}
}
