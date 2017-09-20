package edu.wit.dcsn.comp2000.bagadt;
import java.io.File;
import java.io.FileNotFoundException;

	/**
	 * Used to execute the spell checker
	 * @author Erich Hauntsman
	 */
public class SpellCheckerDemo
{
	private static SpellChecker spellChecker;
	
	/**
	 * Executes the methods from the spell checker class
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[]args) throws FileNotFoundException
	{
		/* 
		 * pass in file name dictionary using scanner
		 * spell check file and put them in bag of misspelled words
		 * display the misspelled words
		 * 
		 */	
		System.out.println("Starting the Spell Checker Demo...");
        spellChecker = new SpellChecker();
        
        checkFile(new File("wit-attendance-policy.txt"));
        checkFile(new File("the-lancashire-cotton-famine.txt"));
	}//end main
	
	private static void checkFile(File f) throws FileNotFoundException
	{
		System.out.println();
		System.out.println("Spell checking file: \"" + f.getName() + "\".");
		BagInterface<String> misspelledWords = spellChecker.spellCheckFile(f);
        
		System.out.printf("There are %d misspelled words.%n", misspelledWords.getCurrentSize());
		if (!misspelledWords.isEmpty())
		{
	        System.out.println("The misspelled words are: ");
	        String word;
			while ( (word = misspelledWords.remove()) != null)
			{			
				System.out.println(word);	
			}//end while
		} // end if
	} // end checkFile()
} // end SpellCheckerDemo
