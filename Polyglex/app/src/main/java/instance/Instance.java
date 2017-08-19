package instance;

/**
 * 
 * @author Jake
 * @author Xavier
 *
 */

public class Instance {
	private String orthography;
	protected String partOfSpeech;
	private String definition;
	private String sentence;
	
	public Instance(String orthography, String partOfSpeech, String definition, String sentence) {
		this.orthography = orthography;
		this.partOfSpeech = partOfSpeech;
		this.definition = definition;
		this.sentence = sentence;
	}

	public Instance(String partOfSpeech, String definition) {
		this.partOfSpeech = partOfSpeech;
		this.sentence = sentence;
	}
	
	public String getPartOfSpeech() {
		return this.partOfSpeech;
	}
	
	public String getDefinition() {
		return this.definition;
	}
	
	public String getSentence() {
		return this.sentence;
	}
	
	public void displayInstance() {
		System.out.println("Word: " + this.orthography + "\nPart of Speech: " + this.partOfSpeech +
				"\nDefinition: " + this.definition +
				"\nSentence: " + this.sentence);
	}
}
