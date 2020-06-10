/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package exceptions;
/**
 * This is a program to throw InvalidSiteException
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class InvalidSiteException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Error message
	 */
	public InvalidSiteException() {
        super("Error: Invalid Site" + "\nSite name must be "
        		+ "strings of at least 6 and at most 12 lower-case letters" );
    }

}
