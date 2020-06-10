/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package exceptions;
/**
 * This is a program to throw UserLockedOutException
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class UserLockedOutException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Error message
	 * 
	 * @param username The username that was locked out
	 */
	public UserLockedOutException(String username) {
        super("Error: Locked User - " + username);
    }

}
