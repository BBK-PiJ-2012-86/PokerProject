package poker.singleton;

public class ComputerIDs {
	private static ComputerIDs instance = null; 
	private int counter = 1; 
	
	protected ComputerIDs(){
		
	}
	
	public int getCounter(){
		counter++;
		return counter - 1;
	}
	
	public static ComputerIDs getInstance(){
		if(instance == null){
			instance = new ComputerIDs();
		}
		return instance;
	}
}
