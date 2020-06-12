/*
 * JungBok Cho
 * Password vault system
 */
package vault;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * This is a program to generate a password for a website and
 * to check the validation of a username, password, and site name.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class CheckingValidation {
	
	/**
	 * Constructor of CheckingValidation class to use singleton pattern
	 * 
	 * @return  Return a static object of CheckingValidation class
	 */
	public static CheckingValidation getInstance() {
		return INSTANCE;
	}
	
	
	/**
	 * Check if the username is in a correct format
	 * 
	 * @param username  The username to check
	 * @return Return true if the username is valid, otherwise return false
	 */
	public boolean checkingUserName(String username) {
		return username.length() < 6 || username.length() > 12 
			|| !Pattern.matches("[a-z]+", username);
	}
	
	
	/**
	 * Check if the password is in a correct format
	 * 
	 * @param password  The password to check
	 * @return Return true if the password is valid, otherwise return false
	 */
	public boolean checkingPassword(String password) {
		return !(password.length() >= 6 && password.length() <= 15 &&
			(password.matches(".*[a-z].*") || 
			 password.matches(".*[A-Z].*")) &&
			 password.matches(".*[0-9].*") &&
			 password.matches(".*[!@#$%^&].*"));
	}
	
	
	/**
	 * Check if the site name is in a correct format
	 * 
	 * @param sitename  The site name to check
	 * @return Return true if the site name is valid, otherwise return false
	 */
	public boolean checkingWebsite(String sitename) {
		return sitename.length() < 6 || sitename.length() > 12 || 
		       !Pattern.matches("[a-z]+", sitename);
	}
	

	/**
	 * Generate a password for a website
	 * 
	 * @return Return generated password
	 */
	public String generatePassword() {
		StringBuilder returnValue;	// StringBuilder object
		
		// Generate a password until it meets the requirements
		do {
			returnValue = new StringBuilder(rand.nextInt(1 + 15 - 6) + 6);
			for (int i = 0; i < returnValue.capacity(); i++) {
			    returnValue.append(ALPHABET.charAt(rand.nextInt(ALPHABET.length())));
			}
		} while(!(returnValue.toString().length() >= 6 
			&& returnValue.toString().length() <= 15 &&
			(returnValue.toString().matches(".*[a-z].*") || 
			 returnValue.toString().matches(".*[A-Z].*")) &&
			 returnValue.toString().matches(".*[0-9].*") && 
			 returnValue.toString().matches(".*[!@#$%^&].*")));
		return returnValue.toString();
	}
	
	
	// CheckingValidation object for the singleton pattern
        private static final CheckingValidation INSTANCE = new CheckingValidation();	
	
	// Random object
	private Random rand  = new Random();
	
	// To generate a website password for user
	private static final String ALPHABET = "!@#$%^&0123456789ABCDEFGH"
						+ "IJKLMNOPQRSTUVWXYZabcde"
						+ "fghijklmnopqrstuvwxyz";
	
}
