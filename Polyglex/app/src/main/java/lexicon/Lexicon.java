package lexicon;

import java.util.*;

import word.*;

/**
 * 
 * @author Xavier Zientarski
 * @author Jacob Bremerman
 * 
 */
public class Lexicon {
	//Variables
	private String language;
	Hashtable<String, Word> lexicon;
	
	/**
	 * This constant static variable allows to verify all the possible languages.
	 */
	private static final String PossibleLanguages[] = {"English", "Spanish", "German"};
	
	
	/**
	 * Simple constructor with one argument.
	 * @param language
	 */
	public Lexicon(String language) {
		this.language = language;
		lexicon = new Hashtable<String, Word>();
	}
	
	/**
	 * This method will add a new word to the lexicon.
	 * @param orthography
	 */
	public void addWord(String orthography) {
		Word word = new Word(orthography, this.language);
		
		lexicon.put(orthography, word);
	}
	
	public Word getWord(String orthography) {
		return lexicon.get(orthography);
	}
	
	/**
	 * This methods display all the current words in the lexicon.
	 */
	public void displayWords() {
		//The iterator is used to display the values for now. We may use better methods eventually. 
		Set set = lexicon.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			Map.Entry current = (Map.Entry)i.next();
			 
			//To print the current part of speech, casting is needed.
			System.out.println("Word.- " + current.getKey()
			+ "\t\tSpelling from value.- " + ((Word) current.getValue()).getOrthography() );
		}
	}
	
	
	public String getLanguage() {
		return this.language;
	}
	
}//End class
