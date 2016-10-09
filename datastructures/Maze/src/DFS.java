/*Maze
 *Michael Neas
 */

import java.util.Stack;

public class DFS {
	private boolean[] _visited; //true/false to determine if vertex has been seen
	private int[] _edgeTo; //previous vertices that are responsible for the discovery of this vertex
	private int _from; //where the first vertex is

	public DFS(){} //general constructor

	public DFS(AdjacencyListGraph graph, int aVertex) {
		this._from = aVertex; //the starting vertex becomes the first one entered
		_edgeTo = new int[graph.setOfVertices().size()]; //all the edges 
		_visited = new boolean[graph.setOfVertices().size()]; //know when all vertices have been visited
		dfsAlg(graph, _from); 
	}

	// depth first search from v
	private void dfsAlg(AdjacencyListGraph graph, int b) {
		_visited[b] = true; //once it sees a vertex it sets its boolean to true
		for (int w = 0; w< graph.setOfVertices().get(b).getAllEdges().size(); w++) {//loop through all edges of a vertex
			if (!_visited[graph.oppositeIndex(graph.setOfVertices().get(b), w)]) {//if the index of the vertex on the opposite side of the edge hasn't been seen yet
				_edgeTo[graph.oppositeIndex(graph.setOfVertices().get(b), w)] = b; //set the edge to that vertex as b
				dfsAlg(graph, graph.oppositeIndex(graph.setOfVertices().get(b), w)); //recursively go through its edges
			}
		}
	}

	public Iterable<Integer> pathTo(int vertexIndex) {
		if (!hasPathToVertex(vertexIndex))  //if there is no connection then its no good
			return null;
		Stack<Integer> pathStack = new Stack<Integer>(); //use stack implementation to get the path there
		for (int v = vertexIndex; v != _from; v = _edgeTo[v])
			pathStack.push(v); //push everything that doesn't really matter and at the end push traversal
		pathStack.push(_from);
		return pathStack;
	}

	public boolean hasPathToVertex(int desiredVertex) {
		return _visited[desiredVertex]; //same thing as getVisited(int) but name difference for clarity in code
	}

	public boolean getVisited(int v) {
		return _visited[v];
	}

	public int getFrom() {
		return _from;
	}
}

