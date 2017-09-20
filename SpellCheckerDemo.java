package edu.wit.dcsn.comp2000.bagadt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

	/**
	 * Used to execute the spell checker
	 * @author Erich Hauntsman
	 */
public class SpellCheckerDemo {
	/**
	 * Executes the methods from the spell checker class
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[]args)  {
		/* 
		 * pass in file name dictionary using scanner
		 * spell check file and put them in bag of misspelled words
		 * display the misspelled words
		 * 
		 */	
		System.out.println(System.getProperty("spell checker"));
        File dictionary = new File("american-english-JL.txt");
        try {
            Scanner sc = new Scanner(dictionary);
           
            while (sc.hasNextLine()){
            	 String string = sc.nextLine();
    			System.out.println(string);
    		}
        } catch (Exception e) {
        System.out.println(e);
        }
        
        System.out.println("The misspelled words are: ");			
		while (word = misspelledWords.remove(!null)){			
			System.out.println(word);
			
		}//end while
		
        
	}//end main
}
