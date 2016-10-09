import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
	public static String go(String name){  // new method "go"
		String string = "";  // creats a variable and stores the empty string in it
		try {  // try and catch any errors that come up
			FileReader fr = new FileReader(name);    // create a new file reader that will read the filename stored in "name" variable
			BufferedReader br = new BufferedReader(fr);  // create a new buffer for FileReader fr
			while(true){  // until the end of time
				String line;  // create a null string named line
				line = br.readLine();  // assign the read string to the line variable
				if(line==null){  // if line is null, or there aren't anymore lines to be read
					break;  // end this loop
				}
				string += line +"\n";  // append the string in line to the string variable
			}
			br.close();  // close the reader
		}
		
		catch (IOException e) {  // try and catch any IOException errors from the enclosed try lines and resolve them

			System.out.println("Error reading from file");  // print the statement 
		}
		return string;  // return string variable and all its contents to the world above


	}
}
