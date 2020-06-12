/*
 * JungBok Cho
 * Password vault system
 */
package exceptions;

/**
 * This is a program to throw InvalidPasswordException
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class InvalidPasswordException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Error message
	 */
	public InvalidPasswordException() {
        super("Error: Invalid Password" + "\n- Password must be between "
        	+ "6 and 15 characters long\n- Password must contain at "
        	+ "least one letter (can be upper or lowercase) and one "
        	+ "digit (0-9)\n- Password must contain at least one "
        	+ "special character from the set !@#$%^&");
    }
	
}
