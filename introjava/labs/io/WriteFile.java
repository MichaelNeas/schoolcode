import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class WriteFile {  //new class
	public static void go(String name, String stringToWrite){  // new method which has the parameters name and stringToWrite, both strings

		FileWriter fw;  // create an uninitialized FileWriter variable named fw
		try {     // try and catch any errors that come up
			fw = new FileWriter(name);   // initialize the fw variable tell it to write in the file with the name stored in the name variable
			BufferedWriter bw = new BufferedWriter(fw);   // create a new buffer for FileReader fr named bw
			PrintWriter pw = new PrintWriter(bw);     // create a new PrintWriter named pw for BufferedWriter bw
			pw.print(stringToWrite);  // print the string in stringToWrite into the pw PrintWriter which transfers tot he buffer and eventuall back to be written in the file 
			pw.close();  // close PrintWriter pw
		}
		catch (IOException e) {   // try and catch any IOException errors from the enclosed try lines and resolve them
			System.out.println("Unable to write file");    // print the statement 
		}

	}
}
