package langSpanishPackage;
import storeLocalData.Instance;

public class SpanishInstance extends Instance {
	
	SpanishAttribute attribute;
	
	public SpanishInstance(String orthography, String partOfSpeech, String definition, String sentence) {
		super(orthography, partOfSpeech, definition, sentence);
		
	
		//Add Attribute
		if(super.partOfSpeech.equals("noun")) {
			attribute = new NounSpanishAttribute();
		}
		else if(super.partOfSpeech.equals("verb")) {
			attribute = new VerbSpanishAttribute();
		}
		else if(super.partOfSpeech.equals("adjective")) {
			attribute = new AdjectiveSpanishAttribute();
		}
		
		
	}
	
	//getter
	public SpanishAttribute getAttribute() {
		return this.attribute;
	}
	
	
	
	/*
	public void addAttribute() {
		if(super.partOfSpeech.equals("noun")) {
			attribute = new NounSpanishAttribute();
		}
		else if(super.partOfSpeech.equals("verb")) {
			attribute = new VerbSpanishAttribute();
		}
		else if(super.partOfSpeech.equals("adjective")) {
			attribute = new AdjectiveSpanishAttribute();
		}
	}
	*/
	
}//End of class