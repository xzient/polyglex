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

	public int getSize() {
		return this.lexicon.size();
	}

	public String[] getOrthographyArray() {


		int size = getSize();
		String[] words = new String[size];
        for (int i = 0; i < size; i++) {
                
		}
		return words;
	}
	/**
	 * This methods display all the current words in the lexicon.
	 */
	public String displayWords() {
		String buffer = "";
		Set set = lexicon.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			Map.Entry current = (Map.Entry)i.next();
			 
			//To print the current part of speech, casting is needed.
			buffer += "\nWord.- " + current.getKey()
			+ " Spelling from value.- " + ((Word) current.getValue()).getOrthography();
		}
		return buffer;
	}
	
	
	public String getLanguage() {
		return this.language;
	}
	
}//End class
