package poker.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInput {

		//private Scanner scanner;
		
		public Integer getInteger(){
			BufferedReader bufferedReader = null; 	
			try {
					bufferedReader = new BufferedReader(new InputStreamReader(System.in));	
					String line = bufferedReader.readLine();
					return Integer.parseInt(line);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try{
						if(bufferedReader != null){
							bufferedReader.close();
						}
					}catch (IOException e){
							e.printStackTrace();
						}
				}
			return null;	
		}
}
