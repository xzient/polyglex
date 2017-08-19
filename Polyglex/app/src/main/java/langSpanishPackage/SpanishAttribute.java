package langSpanishPackage;

public interface SpanishAttribute {
	/**
	 * This display all the info about an attribute. Including if it's regular, or not.
	 * This may include general concepts about the word. Such as, a  noun being 
	 * femenine or masculine, etc.
	 */
	public void display();
	
	
	/**
	 * This function returns the inflections. This may include diminutives for nouns,
	 * conjugations for verbs, etc.
	 */
	public void getInflections();
	
	
	/**
	 * Does the instance of a word have regular attributes?
	 */
	public void isRegular();
}
