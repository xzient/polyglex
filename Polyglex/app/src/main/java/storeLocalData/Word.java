package storeLocalData;

import java.util.LinkedList;

import langEnglishPackage.EnglishInstance;
import langSpanishPackage.SpanishInstance;

/**
 * @author Jake
 * @author Xavier
 */

public class Word {
	
	private String orthography;
	private String language;
	
	private LinkedList<Instance> instances;
	
	/**
	 * Constructor that takes the following parameters:
	 * @param orthography
	 *
	 */
	public Word(String orthography, String language) {
		this.orthography = orthography;
		this.language = language;
		this.instances = new LinkedList<Instance>();
	}
	
	//Adder
	/**
	 * This is a instance adder :D
	 */
	
	public void addInstance(String partOfSpeech, String definition, String sentence) {
		//Instance instance = new Instance(this.orthography, partOfSpeech, definition, sentence);
		//instances.add(instance);
		if (language == "English") {
			EnglishInstance englishinstance = new EnglishInstance(this.orthography, partOfSpeech, definition, sentence);
			instances.add(englishinstance);
		}
		else if (language == "Spanish") {
			SpanishInstance spanishinstance = new SpanishInstance(this.orthography, partOfSpeech, definition,sentence);
			instances.add(spanishinstance);
		}
	}
	
	//Getters
	/**
	 * Get orthography
	 * @return
	 */
	
	public String getOrthography() {
		return this.orthography;		
	}
	
	/**
	 * I have decided to return an object for now. This is quite generic.
	 * @param index
	 * @return
	 */
	public Object getInstance(int index) {
		if (language == "English") {
			return instances.get(index);
		}
		else if (language == "Spanish"){
			return instances.get(index);
		}
		else {
			return instances.get(index);
		}
	}
}
