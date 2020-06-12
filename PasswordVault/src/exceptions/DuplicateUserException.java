/*
 * JungBok Cho
 * Password vault system
 */
package exceptions;

/**
 * This is a program to throw DuplicateUserException
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class DuplicateUserException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Error message
	 */
	public DuplicateUserException() {
        super("Error: Duplicate User name");
    }
	
}
