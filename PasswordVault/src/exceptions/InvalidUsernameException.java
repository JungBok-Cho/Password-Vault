/*
 * JungBok Cho
 * Password vault system
 */
package exceptions;

/**
 * This is a program to throw InvalidUsernameException
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class InvalidUsernameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Error message
	 */
	public InvalidUsernameException() {
        super("Error: Invalid Username" + "\n- Username"
        		+ " must be strings of at least 6 and at"
        		+ " most 12 lower-case letters");
    }
	
}
