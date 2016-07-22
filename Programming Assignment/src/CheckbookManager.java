/**
 * Garry Dominique
 * CheckbookManager
 * 	Manages check objects in a virtual checkbook
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JTable;


public class CheckbookManager {

	//Instance Variables
	private ArrayList<Check> checkbook;
	private boolean isListFull;
	private Object [] columnData = { "Name", "Amount"};
	private	Object [][] rowData = new Object[50][50];
	private JTable table;
	private int counter;
	private Double balance;
	private FileManager fManager;
	private String status;
	
	/**
	 * Constructor
	 */
	public CheckbookManager(){
		fManager = new FileManager();
		checkbook = new ArrayList<Check>();	
		isListFull = false;
		counter++;
	}
	
	/**
	 * Method: Create
	 * 	Creates a check for the check book.
	 * @param name
	 * 		The name of the person using the check
	 * @param amount
	 * 		The amount of a check.
	 */
	public void create(String name, double amount, String type){
		checkbook.add(new Check(name,amount,type));
		int index;
		if(isListFull == false){
			rowData[0][0] = checkbook.get(0).getName();
			rowData[0][1] = checkbook.get(0).getAmount();
			isListFull = true;
		}
		else{
			for(int i = 0; i <= counter; i++){
				if(i == counter){
					index = counter-1;
					rowData[index][0] = checkbook.get(index).getName();
					if(checkbook.get(index).getType().equals("Deposit"))
						rowData[index][1] = checkbook.get(index).getAmount();
					else
						rowData[index][1] = "-" + checkbook.get(index).getAmount();
					break;
				}
			}//forloop
			counter++;
		}
	}//create()
	
	/**
	 * Method: initialCreate
	 * 	Creates intial checkbook
	 */
	public void initialCreate(){
		String type;
		for(int i =0; i < checkbook.size(); i++){
			rowData[i][0] = checkbook.get(i).getName();
			System.out.println(checkbook.get(i).getType());
			type = checkbook.get(i).getType();
			
			if(type.equals("Withdrawal")){
				rowData[i][1] = "-" + checkbook.get(i).getAmount();
				counter++;
			}
			else{
				rowData[i][1] = checkbook.get(i).getAmount();
				counter++;
			}
		}
		isListFull = true;
	}
	/**
	 * Method: Remove
	 * 	Removes a check from the list.
	 * @param name
	 * 	Name of the check
	 */
	public void remove(String name){
		if(checkbook.size() != 0){
			for(int i = 0; i < checkbook.size(); i++){
				if(checkbook.get(i).getName() == name){
					checkbook.remove(i);
					break;
				}
			}//forloop	
		}
	}//remove()
	
	/**
	 * Method: getTable()
	 * 	Creates and send a JTable
	 * @return
	 */
	public JTable getTable(){
		table = new JTable(rowData,columnData);
		return table;
		
	}//getTable()
	
	public Double calculateBalance(){
		balance = (double) 0;
		String type;
		for(int i =0; i < checkbook.size(); i++){
			type = checkbook.get(i).getType();
			if((checkbook.get(i).getType()).equals("Deposit"))
				balance += checkbook.get(i).getAmount();
			else
				balance -= checkbook.get(i).getAmount();
		}
		return balance;
	}
	
	public void loadCheckBook(){
		fManager.loadFile();
		checkbook = fManager.getCheckbook();
	}
	
	public void saveCheckbook() throws FileNotFoundException{
		fManager.createFile(checkbook);
	}
}//CheckbookManager
