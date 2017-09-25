package edu.wit.dcsn.comp2000.bagapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class spell checks a file by comparing its contents with
 * a dictionary.
 * @author Scott Austin, Jack MacVicar
 */
public class SpellChecker
{	
	// utility constants
	private static final String SUCCESS_DESC = "SUCCESS";
	private static final String FAIL_DESC = "FAIL";
	private static final String TEST_DICTIONARY_FILE = "american-english-JL.txt";
	private static final String TEST_FILE_1 = "testFile1.txt";
	private static final String TEST_FILE_2 = "testFile2.txt";
	
	// class constants
	private static final int DICTIONARY_LENGTH = 100000;
	
	// instance variables
	private BagInterface<String> dictionary;
	
	/**
	 * Create a SpellChecker class with a dictionary supplied by a file.
	 * 
	 * @param dictionaryFile  file that contains dictionary words separated by whitespace
	 * @throws FileNotFoundException
	 */
	SpellChecker(File dictionaryFile) throws FileNotFoundException
	{
		dictionary = new ResizableArrayBag<String>(DICTIONARY_LENGTH);
	
		Scanner sc = new Scanner(dictionaryFile);
		while (sc.hasNextLine())
		{
		  dictionary.add(sc.nextLine().toLowerCase());
		} // end while
		sc.close();
		
    } // end constructor
	
	/**
	 * Reads a file and checks the contents for misspelled words and returns a bag 
	 * containing one instance of each misspelled word.
	 * 
	 * @param file  The file to spell check.
	 * @return  A BagInterface<String> that contains one instance of each of the 
	 *          misspelled words found in the file.
	 * @throws FileNotFoundException
	 */
	public BagInterface<String> spellCheckFile(File file) throws FileNotFoundException
	{
		BagInterface<String> misspelledWords = new ResizableArrayBag<String>();
		String word;
		Scanner sc = new Scanner(file);
		sc.useDelimiter("[\\s+.,;-]+");  // do not include punctuation in tokens
		
		while (sc.hasNext())
		{
			// discard numbers from the spell check process
			if (sc.hasNextDouble())
			{
				sc.nextDouble();
			} // end if
			else
			{
				word = sc.next();
				if (!dictionary.contains(word.toLowerCase()))
				{
					// we only want one instance of each misspelled word
					if (!misspelledWords.contains(word))
					{
						misspelledWords.add(word);
					} // end if
				} // end if
			} // end else
		} // end while
		sc.close();
		
		return misspelledWords;
	} // end spellCheckFile()
	
	/** Unit test driver.
	 * 
	 * @param args  -unused-
	 */
	public static void main(String[] args)
	{
		System.out.println("Testing SpellChecker:");
		
		testConstructor();
		testSpellCheckFile();
	}
	
	/** Unit tests for the constructor. */
	private static void testConstructor()
	{
		SpellChecker spellChecker;
		boolean testResult;
		
		System.out.println();
		System.out.println("Testing constructor:");
		
		// constructor test #1
		testResult = false;
		try {
			spellChecker = new SpellChecker(new File("does-not-exist.txt"));
		} catch (FileNotFoundException e) {
			testResult = true;
		} // end try-catch
		System.out.println("Dictionary FileNotFoundException Test:\t" + (testResult ? SUCCESS_DESC : FAIL_DESC));
		
		// constructor test #2
		testResult = true;
		try {
			spellChecker = new SpellChecker(new File(TEST_DICTIONARY_FILE));
		} catch (FileNotFoundException e) {
			testResult = false;
		} // end try-catch
		System.out.println("Dictionary file loaded test:\t\t" + (testResult ? SUCCESS_DESC : FAIL_DESC));
		
		// constructor test #3
		try {
			spellChecker = new SpellChecker(new File(TEST_DICTIONARY_FILE));
			testResult = !spellChecker.dictionary.isEmpty();
			System.out.println("Dictionary filled test:\t\t\t" + (testResult ? SUCCESS_DESC : FAIL_DESC));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to load dictionary in constructor test 3.");
		} // end try-catch
	} // end testConstructor()
	
	/** Unit tests for the spellCheckFile method. */
	private static void testSpellCheckFile()
	{
		SpellChecker spellChecker;
		BagInterface<String> misspelledWords;
		boolean testResult;
		
		System.out.println();
		System.out.println("Testing spellCheckFile():");
		
		try {
			spellChecker = new SpellChecker(new File(TEST_DICTIONARY_FILE));
			
			// spellCheckFile test #1
			misspelledWords = spellChecker.spellCheckFile(new File(TEST_FILE_1));
			testResult = misspelledWords.isEmpty();
			System.out.println("testFile1 spellCheckTest:\t\t" + (testResult ? SUCCESS_DESC : FAIL_DESC));
			
			// spellCheckFile test #2
			misspelledWords = spellChecker.spellCheckFile(new File(TEST_FILE_2));
			testResult = (misspelledWords.getCurrentSize() == 3);
			testResult = ( testResult && ( misspelledWords.remove().equals("fil") ) );
			testResult = ( testResult && ( misspelledWords.remove().equals("wods") ) );
			testResult = ( testResult && ( misspelledWords.remove().equals("mispelled") ) );
			System.out.println("testFile2 spellCheckTest:\t\t" + (testResult ? SUCCESS_DESC : FAIL_DESC));
			
		} catch (FileNotFoundException e) {
			System.out.println("Failed to load dictionary in spellCheckFile test.");
		} // end try-catch
	} // end testSpellCheckFile()
	
} // end SpellChecker
