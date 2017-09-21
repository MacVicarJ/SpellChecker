package edu.wit.dcsn.comp2000.bagadt;
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
	private static final int DICTIONARY_LENGTH = 100000;
	private BagInterface<String> dictionary;
	
	/**
	 * Create a SpellChecker class with a dictionary supplied by a file
	 * @param dictionaryFile  file that contains dictionary words separated by whitespace
	 * @throws FileNotFoundException
	 */
	SpellChecker(File dictionaryFile) throws FileNotFoundException
	{
		dictionary = new ResizableArrayBag<String>(DICTIONARY_LENGTH);
	
		Scanner sc = new Scanner(dictionaryFile);
		while (sc.hasNextLine())
		{
		  dictionary.add(sc.nextLine());
		} // end while
		sc.close();
		
    } // end constructor
	
	/**
	 * Reads a file and checks the contents for misspelled words and returns a bag 
	 * containing one instance of each misspelled word.
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
				word = sc.next().toLowerCase();
				if (!dictionary.contains(word))
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
	
} // end SpellChecker
