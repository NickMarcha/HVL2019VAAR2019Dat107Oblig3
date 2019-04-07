package no.HVLDAT107.Oblig3;

import java.util.Scanner;

/**
 * Helper class that contains the input scanner and allows for a list of "commands"
 * to be easily printed and selected, repeats if command not found.
 */
public final class Commands {

	public static Scanner input;
	public static void showCommands(Command[] commands) {
			
			System.err.println("\nType command and press Enter:");
			System.out.println("-----------------------");
			for (Command c : commands) {
				System.out.println(c);
			}
			System.out.println("-----------------------\ncmd | Description");
			
			
			
			String i = input.next() + input.nextLine();
			boolean ranCommand = false;
			for (Command c : commands) {
				if(c.command.equalsIgnoreCase(i)) {
					c.RunCommand();
					ranCommand = true;
					break;
				}
			}
			
			if(!ranCommand) {
				System.err.println("Not valid Command");
				showCommands(commands);
			}
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	public static class Command{
		String command;
		public String getCommand() {
			return command;
		}
		
		Commandable function;
		
		String description;
		public String getDescription() {
			return description;
		}
		
		public void RunCommand() {
			function.command();
		}
		
		public Command(String command, String description, Commandable function ) {
			this.command = command;
			this.description = description;
			this.function = function;
		}
		public String toString() {
			return command + " -- " + description; 
		}
	}
	
	public interface Commandable{
		void command();
	}
}
