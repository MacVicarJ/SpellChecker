import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class SpellChecker {
	
	File dictionary = new File("american-english-JL.txt");
	File fileOne = new File("The-lancashire-cotton-famine.txt");
	File fileTwo = new File("wit-attendance-policy.txt");
	private Scanner sc;
	private Scanner s2;
	
	SpellChecker() throws FileNotFoundException{
		
		
				sc = new Scanner(dictionary);
				ArrayList<String> words = new ArrayList<String>();
				while (sc.hasNextLine()) {
				  words.add(sc.nextLine());
				}

				String[] arr = words.toArray(new String[0]);
				
				
				Scanner scanner = null;
			    try {
			        scanner = new Scanner(fileTwo);
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();  
			    }
			    
			    
			    while (scanner.hasNextLine()) {
			            s2 = new Scanner(scanner.nextLine());
			            
			        while (s2.hasNext()) {
			            String s = s2.next();
			            
			            if (Arrays.asList(arr.toString().toLowerCase()).contains(s)){
			            	System.out.println(s);
			            }
			            
			      }
			    }
		      }
			}
	






