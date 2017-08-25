package storeLocalData;
import java.util.*;

/**
 * 
 * @author Jake
 * @author Xavier
 *
 */

public class User {
	//variables
	private String username;
	private String password;
	private String ID;
	private String email; //Email should be controlled by regex to verify that it is an actual email.
				//Eventually there should  a way to verify that the email is real.
	//private int ID;

	HashMap<String, Lexicon> lexiconList;
	
	/**
	 * This is a constructor for the user
	 * @param username
	 */
	public User(String username, String password, String email, String ID) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.ID = ID;
		lexiconList = new HashMap<String, Lexicon>();
	}


	/**
	 * This methods uses regex to verify if the email provided is
	 * is a valid a format.
	 * @return
	 */
	public static boolean verifyEmail(String email) {
		//To Do
		return true;
	}
	//getter
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public String geID() {
		return this.ID;
	}
	
	public Lexicon getLexicon(String language) {
		return lexiconList.get(language);
	}
	
	/**
	 * Add lexicon
	 * @param language
	 */
	public void addLexicon(String language) {
		Lexicon lexicon = new Lexicon(language);
		lexiconList.put(language, lexicon);	
	}
	
	/**
	 * Print values of lexicon from user.
	 * Eventually one should be able to catch error if wrong string given.
	 * @param language
	 */
	public void printLexiconWords(String language) {
		lexiconList.get(language).displayWords();
	}
	
	
}