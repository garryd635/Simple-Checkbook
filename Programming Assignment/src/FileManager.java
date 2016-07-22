import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class FileManager {

	//Instance Variable
	private ArrayList<Check> checkbook;
	
	/**
	 * Method: Locates and load file into system
	 */
	public void loadFile(){
		checkbook = new ArrayList<Check>();
		Scanner fileInput;
		File inFile = new File("checkbook.txt");
		
		System.out.println("Opening and Reading File");
		
		try{
			fileInput = new Scanner(inFile);
			while(fileInput.hasNext()){
				checkbook.add(new Check(fileInput.next(),fileInput.nextDouble(),fileInput.next()));
			}
			fileInput.close();
		}//end try
		catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} // end catch
		System.out.println("Reading Complete");
	}//end loadFile()
	
	/**
	 * Method: Create a file containing checkbook details
	 * @param checkList
	 * @throws FileNotFoundException
	 */
	public void createFile(ArrayList<Check> checkList) throws FileNotFoundException{
		System.out.println("Creating checkbook file");
		PrintWriter out = new PrintWriter("checkbook.txt");
		for(int i =0; i < checkList.size(); i++)
			out.println(checkList.get(i).getName() + " " + checkList.get(i).getAmount() + " " + checkList.get(i).getType());
		out.close();
		System.out.println("File Created");
	}
	
	/**
	 * Method: getCheckbook
	 * @return
	 */
	public ArrayList<Check> getCheckbook(){
		return checkbook;
	}
}
