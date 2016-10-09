/*Maze
 *Michael Neas
 */

import java.util.Scanner;

public class MazeConstruct {
	public Scanner mazeVars = new Scanner(System.in);
	private int _nSize; //number of cells per row/column
	private int _rowStart;
	private int _colStart;
	private int _rowFinish;
	private int _colFinish;
	private int _startingVertex;
	private int _finishingVertex;
	private AdjacencyListGraph _graphDS = new AdjacencyListGraph();

	public void getStartingInfo() {//grab all starting input data
		System.out.println("Please input: n rowStart colStart rowFinish colFinish -- separated by spaces.");
		_nSize = mazeVars.nextInt();
		_rowStart = mazeVars.nextInt();
		_colStart = mazeVars.nextInt();
		_rowFinish = mazeVars.nextInt();
		_colFinish = mazeVars.nextInt();
		//System.out.println(_nSize + " " + _rowStart + " " + _colStart + " " + _rowFinish + " " + _colFinish);
	}

	public void initialGraph(int sizeOfMaze) {//puts all the connections in place
		int vertexIndexCount = 0;
		for(int x = 0; x<sizeOfMaze; x++) //double for loop iterating through coordinates
			for(int y = 0; y<sizeOfMaze;y++){
				_graphDS.insertVertex(new Vertex(vertexIndexCount, x, y));
				vertexIndexCount++; //insert the vertices with respective locations
			}
		//if input is 4 iterate 0->3
		int edgeIndexCount = 0;
		for(int a = 0; a<sizeOfMaze-1; a++) {//still n^2 time  but this inserts the edges
			for(int b = a*sizeOfMaze; b<((a+1)*sizeOfMaze)-1; b++)  {//connect edges horizontal in lists WRT graph size
				_graphDS.insertEdge(_graphDS.setOfVertices().get(b), _graphDS.setOfVertices().get(b+1), 
						mazeVars.nextInt(), edgeIndexCount);
				edgeIndexCount++;
			}
			for(int c = a*sizeOfMaze; c<((a+1)*sizeOfMaze); c++) {//this connects the vertically connected vertices
				_graphDS.insertEdge(_graphDS.setOfVertices().get(c), _graphDS.setOfVertices().get(c+sizeOfMaze), 
						mazeVars.nextInt(), edgeIndexCount);
				edgeIndexCount++;
			}
		}
		for(int d = (sizeOfMaze*(sizeOfMaze-1)); d<((sizeOfMaze*sizeOfMaze)-1); d++) {//get that last row since they just need to reconnect to already connected vertices
			_graphDS.insertEdge(_graphDS.setOfVertices().get(d), _graphDS.setOfVertices().get(d+1),
					mazeVars.nextInt(), edgeIndexCount);
			edgeIndexCount++;
		}
	}

	public void drawPreRemoval(int size) {//makes the first maze with only openings cut out
		AdjacencyListGraph noEdges = new AdjacencyListGraph(); //just a graph with nSize^2 vertices and no connected edges
		int vertexIndex = 0;
		for(int x = 0;x < size; x++)
			for(int y = 0; y < size; y++){
				noEdges.insertVertex(new Vertex(vertexIndex, x, y));
				vertexIndex++;
			}
		drawMaze(noEdges);
	}

	public void drawMaze(AdjacencyListGraph aGraph) {//draws all three mazes, if there is an edge connecting then there will be no wall
		int	vertexIndexCount = 0; //count to give to vertices
		System.out.print(" _"); //starting line that will never be cause (0,0) will be on the left for entry
		for(int e = 1; e < _nSize; e++){
			if((_rowStart == 0 && _colStart == _nSize-1) || (_colFinish == _nSize-1 && _rowFinish == 0)) //prints the top row
				System.out.print(" _");
			else if((_rowStart == 0 && _colStart == e) || (_colFinish == e && _rowFinish == 0)) //accounts for top row being opening at the start
				System.out.print("  "); //if the open space lines up then open the maze
			else
				System.out.print(" _"); 
		}
		for(int f = 0; f < _nSize; f++) {//draws 0 to n-1 rows and columns
			System.out.println(); 
			if((_colStart == 0 && _rowStart == f) || (_rowFinish == f && _colFinish == 0))//draws left side to graph
				System.out.print(" ");
			else
				System.out.print("|");
			for(int g = 0; g < _nSize; g++) {//provides a y for location
				if(f < _nSize-1){
					if(g < _nSize-1) {//only deal with the insides 
						if(!aGraph.areAdjacent(aGraph.setOfVertices().get(vertexIndexCount), //draws the floors for n-1 space
								aGraph.setOfVertices().get(vertexIndexCount+_nSize)))
							System.out.print("_");
						else if(aGraph.setOfVertices().get(vertexIndexCount).getAsterisk() == true) //puts asterisks when solved
							System.out.print("*");
						else
							System.out.print(" ");
						if(!aGraph.areAdjacent(aGraph.setOfVertices().get(vertexIndexCount), 
								aGraph.setOfVertices().get(vertexIndexCount + 1)))
							System.out.print("|");// Vertical edges
						else 
							System.out.print(" ");
						vertexIndexCount++;
					} else {//for the right most column check
						if(!aGraph.areAdjacent(aGraph.setOfVertices().get(vertexIndexCount),  //evaluate if not adjacent
								aGraph.setOfVertices().get(vertexIndexCount + _nSize)))
							System.out.print("_"); 
						else if(aGraph.setOfVertices().get(vertexIndexCount).getAsterisk() == true)
							System.out.print("*"); 
						else
							System.out.print(" ");
						if((_rowStart == f && _colStart == _nSize-1) || (_rowFinish == f && _colFinish == _nSize-1))
							System.out.print(" ");
						else
							System.out.print("|");
						vertexIndexCount++;
					}
				} else{ //for corners and middle bottom separately
					if(aGraph.setOfVertices().get(vertexIndexCount).getAsterisk() == true)
						System.out.print("*");
					else if(g == 0 || g == _nSize-1)
						System.out.print("_");
					else if((_rowStart == f && _colStart == g) || (_rowFinish == f && _colFinish == g))
						System.out.print(" ");
					else
						System.out.print("_");
					if(g < _nSize-1) {//bottom row comparison for adjacent up to nSize squared -1 
						if(!aGraph.areAdjacent(aGraph.setOfVertices().get(vertexIndexCount), 
								aGraph.setOfVertices().get(vertexIndexCount + 1)))
							System.out.print("|");
						else{
							System.out.print(" ");
						}
						vertexIndexCount++;	
					}else {//gets the very bottom row with walls
						if((_rowStart == f && _colStart == _nSize-1) || (_rowFinish == f && _colFinish == _nSize-1))
							System.out.print("|");
						else
							System.out.print("|");
						vertexIndexCount++;
					}
				}
			}
		}
	}

	public int findStartVertex(AdjacencyListGraph graphDS) {//used for the DFS since it compares by indexes
		for(int a = 0; a<graphDS.setOfVertices().size(); a++){
			if(graphDS.setOfVertices().get(a).getCellObj().get_xCoordinate() == _rowStart &&
					graphDS.setOfVertices().get(a).getCellObj().get_yCoordinate() == _colStart)
				_startingVertex = graphDS.setOfVertices().get(a).getIndex();
		}
		return _startingVertex;
	}

	public int findFinishVertex(AdjacencyListGraph graphDS){
		for(int z = 0; z<graphDS.setOfVertices().size(); z++){
			if(graphDS.setOfVertices().get(z).getCellObj().get_xCoordinate() == _rowFinish &&
					graphDS.setOfVertices().get(z).getCellObj().get_yCoordinate() == _colFinish)
				_finishingVertex = graphDS.setOfVertices().get(z).getIndex();
		}
		return _finishingVertex;
	}

	public int getRowStart(){
		return _rowStart;
	}

	public int getColStart(){
		return _colStart;
	}

	public int getRowFinish(){
		return _rowFinish;
	}

	public int getColFinish(){
		return _colFinish;
	}

	public int getSize(){
		return _nSize;
	}

	public AdjacencyListGraph getInternalGraph(){
		return _graphDS;
	}

	/** Test and working input for MazeConstruct.java, instructions say to make MazeSolve.java the main class
	 * so I had to move this main over there
	 **/
	//	public static void main(String[] args)
	//	{
	//		getStartingInfo();
	//		initialGraph(_nSize);
	//		drawPreRemoval(_nSize);
	//		System.out.println();
	//		AdjacencyListGraph _pathMaze = new PrimJarnik().sortThisGraph(_graphDS);
	//		drawMaze(_pathMaze);
	//		mazeVars.close();
	//	}
}
