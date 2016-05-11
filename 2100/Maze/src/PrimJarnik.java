/*Maze
 *CSE2100 Project 6, Fall 2014
 *Michael Neas
 *12/07/2014
 *Instructor: Chun-Hsi (Vincent) Huang
 */

public class PrimJarnik {
	private Vertex[] _vertexArray;
	private int[] _arrayOfDistances;
	private Edge[] _previousMinimumEdges;
	private Entry<Integer,Vertex>[] _entry; 

	public PrimJarnik(){}

	public AdjacencyListGraph sortThisGraph(AdjacencyListGraph graph) {
		HeapAdaptablePQ<Integer, Vertex> heapPQ = new HeapAdaptablePQ<Integer, Vertex>(); //heap priority queue for smallest edge values
		int totalVerts = graph.setOfVertices().size(); //make array of the size of the total nodes to iterate through
		_vertexArray = new Vertex[totalVerts]; //all vertices
		_arrayOfDistances = new int[totalVerts]; //stores the distances
		_previousMinimumEdges = new Edge[totalVerts]; //edge connections should be the same as amount of vertices
		_entry = new Entry[totalVerts]; //allows heapPQ to be used to extract minimums

		for(int h = 0; h < totalVerts; h++) {//prime all vertices to get ready to use prim's
			if(h == 0) //if no nodes have been visited yet then the total distance is zero
				_arrayOfDistances[h] = 0; //setting up the root
			else //otherwise the vertices
				_arrayOfDistances[h] = Integer.MAX_VALUE; //setting the unvisited vertices to max value to represent infinity
			_previousMinimumEdges[h] = null; //all parent edges are null
			_vertexArray[h] = graph.setOfVertices().get(h); //fill the vertex array with all the cells
			_entry[h] = heapPQ.insert(_arrayOfDistances[h], _vertexArray[h]);
			//adding the key and value to the heap priority queue;
		}
		while(!heapPQ.isEmpty()) {//while there is still weight value pairs in heap go through
			Vertex tempV = heapPQ.removeMin().getValue(); //initialize temporary vertex pulled from the heap
			for(int i= 0; i < graph.incidentEdges(tempV).size(); i++) {//for loop to go through all the incident edges of the vertex
				Edge tempEdge = graph.incidentEdges(tempV).get(i); //initialize temporary edge to test from all incident edges
				Vertex opposingVertex = graph.opposite(tempV,tempEdge); //get the opposite vertex
				int weightOfEdge = tempEdge.getWeight(); //store the weight of the given edge being looked at
				if ( weightOfEdge < _arrayOfDistances[opposingVertex.getIndex()]) {//if it is the minimum weight
					_arrayOfDistances[opposingVertex.getIndex()] = weightOfEdge; //set the array of distance weight equal to weight of edge 
					_previousMinimumEdges[opposingVertex.getIndex()] = tempEdge; //set the edge where this smallest weight is equal to the smallest edge object
					heapPQ.replaceKey(_entry[opposingVertex.getIndex()], weightOfEdge); //change the key of vertex in priority queue to the weight here
				}
			}
		}
		AdjacencyListGraph solvedGraph = new AdjacencyListGraph(); 
		for(int j = 0; j < _vertexArray.length; j++) {//iterate through this vertex array and insert all vertices into array(should be same as put in)
			solvedGraph.insertVertex(_vertexArray[j]);
			_vertexArray[j].removeIncidentEdges(); //need to remove all the previous edges
		}
		int newIndexIterator = 0; //assigning index
		for(int k = 0; k < _previousMinimumEdges.length; k++) //now to insert the minimum edges
			if(_previousMinimumEdges[k] != null) {//if that array is not empty
				_previousMinimumEdges[k].setIndex(newIndexIterator); //set the index of all the edges to the iteration count
				solvedGraph.insertEdge(_previousMinimumEdges[k].getVertex1(), _previousMinimumEdges[k].getVertex2(), 
						_previousMinimumEdges[k].getWeight(), newIndexIterator);//insert to set of edges
				newIndexIterator++; //increment
			}
		return solvedGraph; //give back the graph
	}

	public Edge[] getEdges(){ 
		return _previousMinimumEdges; 
	} 

	public Vertex[] getVertices(){ 
		return _vertexArray; 
	}
}
