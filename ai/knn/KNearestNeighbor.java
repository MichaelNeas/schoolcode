import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class KNearestNeighbor {

	public static void main(String [] args) throws IOException {
		//Import data
		File testfile = new File("/KNearestNeighbor/TestData.txt");
		File trainfile = new File("TrainingData.txt");
		//Data stored in these arrays
		int[][] testdata = readFile(testfile);
		int[][] traindata = readFile(trainfile);
		
		//Get the length of the data sets
		int dataLenTest = getLen(testdata);
		System.out.println("Length of test data: " + dataLenTest);
		int dataLenTrain = getLen(traindata);
		System.out.println("Length of training data: " + dataLenTrain);
		
		//Print first 5 entries of each
		System.out.println("5 Test Data entries: ");
		printData(testdata, 5);
		System.out.println("5 Training Data entries: ");
		printData(traindata, 5);
	}

	//Return 2D array with x values in first column and y values in second based on dataset given as arg. 
	public static int[][] getXY(int[][] dataset) {
		int[][] xyData = new int[getLen(dataset)][2];
		for (int i=0;i<getLen(dataset);i++) {
			xyData[i][1]=getY(dataset[i][0],dataset[i][2],dataset[i][4],dataset[i][6],dataset[i][8]);
			xyData[i][0]=getX(xyData[i][1],dataset[i][1],dataset[i][3],dataset[i][5],dataset[i][7],dataset[i][9]);
		}
		return xyData;
	}
	
	/*
	 * The three methods that I created below are used in the getXY method
	 * above. They probably won't be needed for anything else.
	*/
	
	//Gets Y value to tell if it is a flush or not
	private static int getY(int suit1, int suit2, int suit3, int suit4, int suit5) {
		if (suit1==suit2 & suit2==suit3 & suit3==suit4 & suit4==suit5)
			return 2;
		else return 1;
	}

	//Gets X value depending on Y and how many of each cards there are.
	private static int getX(int yVal, int rank1, int rank2, int rank3, int rank4, int rank5) {
		int handRanks[] = {rank1,rank2,rank3,rank4,rank5};
		Arrays.sort(handRanks);
		int[] ranks=new int[12];
		ranks[rank1]++;
		ranks[rank2]++;
		ranks[rank3]++;
		ranks[rank4]++;
		ranks[rank5]++;
		int A=handRanks[4];
		int B=handRanks[3];
		if(yVal==2) //Flush. Therefore x will be the sum of the ranks.
			return rank1+rank2+rank3+rank4+rank5;
		else if(checkForStraight(handRanks))	//Straight. We will use 6.
			return 6;
		else if (A==4)		//Four of a kind. We will use 4.
			return A;
		else if (A==3&B==2)	//Full house. We will use 5.
			return 5;
		else if (A==3)		//Three of a kind. We will use 3.
			return A;
		else if (A==2&B==2)	//Two pairs. We will use 2.
			return A;
		else if (A==2)		//One pair. We will use 1.
			return 1;
		else				//Nothing.
			return 0;
	}
	
	//Check to see if hand is a straight given a sorted array.
	public static boolean checkForStraight(int[] ranks) {
		for(int i=0;i<ranks.length-1;i++) {
			if (ranks[i]+1!=ranks[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * All methods below this point are simply helpers to import and
	 * organize data succinctly. Not strictly relevant to kNN algorithm. 
	 */
	
	//Read data from the given data sets. Uncomment print lines for debug/display.
	public static int[][] readFile(File file) throws IOException {
		//Initialize
		BufferedReader br = new BufferedReader(new FileReader(file));
		int count = 0;
		int[][] column = new int[1000010][11];
		
		//Begin reading
		try {
			//Read first line
			String line = br.readLine();
			//Loop until empty line
			while(line != null) {
				//Initial variables
				int commacount = 0;
				int numstart = 0;
				int numend = 0;
				char curchar = ' ';
				String curnumstr = "";
				int curnum = 0;

				//Loop through a given line, dependent on length
				for(int i = 0 ; i < line.length() ; i++) {
					curchar = line.charAt(i);
					//If comma, store preceding char in array
					if(curchar == ',') {
						curnumstr =  line.substring(numstart, numend);
						curnum = Integer.parseInt(curnumstr);
						column[count][commacount] = curnum;
						//Update
						commacount++;
						numstart = numend + 1;
						numend = numstart;

						//System.out.print(curnum + ",");
					}
					//Else increment up
					else {
						numend++;
					}
					
					//Handle final int
					if(commacount == 10) {
						curnumstr =  line.substring(numstart, line.length());
						curnum = Integer.parseInt(curnumstr);
						column[count][commacount++] = curnum;
						//System.out.print(curnum);
					}

				}
				//System.out.println(" ");
				count++;
				line = br.readLine();
			}
		}
		//Close file, return array
		finally { br.close(); }
		return column;
	}

	//Display first n entries of a data set. 
	public static void printData(int[][] cols, int n) {
		for(int i = 0 ; i < n ; i++) {
			System.out.print((i + 1) + ": [");
			for(int j = 0 ; j < 10 ; j++) {
				System.out.print(cols[i][j] + ",");
			}
			System.out.print(cols[i][10]);
			System.out.println("]");
		}
	}

	//Get the length of a data set.
	public static int getLen(int[][] colx) {
		int len = 0;
		while(colx[len][0] != 0) 
			len++;	
		return len;
	}
}
