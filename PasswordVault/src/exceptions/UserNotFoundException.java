/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package exceptions;
/**
 * This is a program to throw SiteNotFoundException
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Error message
	 */
	public UserNotFoundException() {
        super("Error: User Not Found");
    }

}
