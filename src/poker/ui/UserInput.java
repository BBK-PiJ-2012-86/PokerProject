package poker.ui;

import java.util.Scanner;

public class UserInput {

		private Scanner scanner;
		
		public int getInteger(){
			scanner = new Scanner(System.in);
			int userInput = scanner.nextInt();
			scanner.close();
			return userInput;
		}
}
