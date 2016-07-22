/**
 * Garry Dominique
 * Check
 * Create Check objects.
 */
public class Check {
	
	//Instance Variables
	private String name;
	private double amount;
	private String type;
	
	/**
	 * Constructor
	 * @param name
	 * @param amount
	 */
	public Check(String name, double amount, String type){
		this.name = name;
		this.amount = amount;
		this.type = type;
	}//constructor
	
	/**
	 * Method: getName
	 * Retrieves check name.
	 * @return name
	 */
	public String getName(){
		return name;
	}//getName()
	
	/**
	 * Method: getAmount
	 * Retrieve check amount.
	 * @return name
	 */
	public double getAmount(){
		return amount;
	}//getAmount()
	
	/**
	 * Get the type of Check
	 * @return name
	 */
	public String getType(){
		return type;
	}//end getType
}//Check
