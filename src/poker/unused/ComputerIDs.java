package poker.unused;

public class ComputerIDs {
	private static ComputerIDs INSTANCE = null; 
	private int counter = 1; 
	
	protected ComputerIDs(){
		
	}
	
	public int getCounter(){
		counter++;
		return counter - 1;
	}
	
	public static ComputerIDs getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ComputerIDs();
		}
		return INSTANCE;
	}
}
