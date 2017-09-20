package edu.wit.dcsn.comp2000.bagadt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SpellChecker
{	
	BagInterface<String> dictionary;
	
	File fileOne = new File("The-lancashire-cotton-famine.txt");
	File fileTwo = new File("wit-attendance-policy.txt");
	
	SpellChecker() throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("american-english-JL.txt"));
		dictionary = new ResizableArrayBag<String>();
		
		while (sc.hasNextLine()) {
		  dictionary.add(sc.nextLine());
		}
		sc.close();
    }
	
	public BagInterface<String> spellCheckFile(File f) throws FileNotFoundException
	{
		BagInterface<String> misspelledWords = new ResizableArrayBag<String>();
		String word;
		Scanner sc = new Scanner(f);
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

}







