package langEnglishPackage;
import storeLocalData.Instance;

public class EnglishInstance extends Instance {
	
	EnglishAttribute attribute;
	
	public EnglishInstance(String orthography, String partOfSpeech, String definition, String sentence) {
		super(orthography, partOfSpeech, definition, sentence);
		
		//Add Attribute
		if(super.partOfSpeech.equals("noun")) {
			attribute = new NounEnglishAttribute();
		}
		else if(super.partOfSpeech.equals("verb")) {
			attribute = new VerbEnglishAttribute();
		}
		else if(super.partOfSpeech.equals("adjective")) {
			attribute = new AdjectiveEnglishAttribute();
		}
		
	}
	
	/*
	public void addAttribute() {
		if(super.partOfSpeech.equals("noun")) {
			attribute = new NounEnglishAttribute();
		}
		else if(super.partOfSpeech.equals("verb")) {
			attribute = new VerbEnglishAttribute();
		}
		else if(super.partOfSpeech.equals("adjective")) {
			attribute = new AdjectiveEnglishAttribute();
		}
	}
	*/
	
}//End of class
