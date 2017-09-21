package edu.wit.dcsn.comp2000.bagadt;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Runs a demonstration of the SpellChecker
 * @author Erich Hauntsman
 */
public class SpellCheckerDemo
{
	private static final String DICTIONARY_FILENAME = "american-english-JL.txt";
	private static final String TEST_FILE_ONE = "wit-attendance-policy.txt";
	private static final String TEST_FILE_TWO = "the-lancashire-cotton-famine.txt";
	
	private static SpellChecker spellChecker;
	
	/**
	 * Runs a demonstration of the SpellChecker class
	 * @param args  -unused-
	 * @throws FileNotFoundException 
	 */
	public static void main(String[]args) throws FileNotFoundException
	{
		System.out.println("Starting the Spell Checker Demo...");
        spellChecker = new SpellChecker(new File(DICTIONARY_FILENAME));
        
        checkFile(new File(TEST_FILE_ONE));
        checkFile(new File(TEST_FILE_TWO));
        
	} //end main()
	
	/**
	 * Runs the spell checker on a specified file and prints to the console the
	 * number of misspelled words found and a list of all the words. 
	 * @param file  The file to run the spell checker on.
	 * @throws FileNotFoundException
	 */
	private static void checkFile(File file) throws FileNotFoundException
	{
		System.out.println();
		System.out.println("Spell checking file: \"" + file.getName() + "\".");
		
		BagInterface<String> misspelledWords = spellChecker.spellCheckFile(file);
        
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
