import java.util.Scanner;

public class UserInput {

	static Scanner userInput = new Scanner(System.in);
	
	static int readInt(String prompt) {
		boolean validInput = false;
		int input = 0;
		
		do {
			System.out.println(prompt);
			if (userInput.hasNextInt()) {
				input = userInput.nextInt();
				validInput = true;
			} else {
				System.out.printf("Invalid input: %s\n",userInput.next());
			}
			
		} while(!validInput);
		
		return input;
	}
	
	static char readChar(String prompt) {
		char input = ' ';
		System.out.println(prompt);
		while (userInput.hasNextLine()) {
			String response = userInput.nextLine();
			if (response.length() == 1) {
				input = response.charAt(0);
				break;
			} else {
				System.out.printf("Invalid input: %s\n",response);
				System.out.println(prompt);
			}
		}
		return input;
	}
	
	static String readPattern(String pattern, String prompt) {
		//this method is best for usernames or passwords
		boolean validInput = false;
		String input = "";
		
		do {
			System.out.println(prompt);
			if (userInput.hasNext(pattern)) {
				input = userInput.next();
				validInput = true;
			} else {
				System.out.printf("Invalid input: %s\n",userInput.next());
			}
			
		} while(!validInput);
		
		return input;
	}
	
	public static void main(String[] args) {
		System.out.println("You entered: "+readChar("Enter a letter:"));
	
		
	}

}
