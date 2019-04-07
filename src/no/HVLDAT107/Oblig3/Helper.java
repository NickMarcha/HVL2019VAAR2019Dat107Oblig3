package no.HVLDAT107.Oblig3;


import java.util.List;

public class Helper {

	public static <T> void PrintList(List<T> liste) {
		
		for(T el : liste) {
			System.out.println(el);
		}
	}

	public static String InputString(String Display) {
    	return InputString(Display, 1, 50);
	}
	
	public static String InputString(String Display,int min, int Length) {
		
		System.err.println(Display);
		String input = Commands.input.next() + Commands.input.nextLine();
    	while (input.length() >Length || input.length() < min) {
    		
    		System.err.println("Input is too long or too short");
    		System.err.println(Display);
    		input = Commands.input.next() + Commands.input.nextLine();
    	}
		return input;
	}
	
	public static int InputInt(String Display) {
		int returnValue = Integer.MIN_VALUE;
		while(returnValue == Integer.MIN_VALUE) {
			try {System.err.println(Display);
			
				 int temp = Integer.parseInt(Commands.input.next() + Commands.input.nextLine());
				 returnValue = temp;
			} catch (NumberFormatException e) {
				System.err.println("Input not a valid whole number");
			}
		}
		
		
    	return returnValue;
	}
	
	
	public static java.sql.Date InputDate(String Display) {
		
		java.sql.Date date = null;
		
		while(date == null) {
			System.err.println(Display);
			try {
				java.sql.Date temp = java.sql.Date.valueOf(Commands.input.next() + Commands.input.nextLine());
				date = temp;
			} catch(IllegalArgumentException ex) {
				System.err.println("Date is wrong format");
			}
		}
		return date;
	}
	
	public static boolean confirmation() {
		System.err.println("Are you sure?(y/n)");
    	String in = Commands.input.next() + Commands.input.nextLine();
    	
    	if(in.equalsIgnoreCase("y")) {
    		return true;
    	} else if (in.equalsIgnoreCase("n")) {
    		return false;
    	} else {
    		return confirmation();
    	}
	}
}
