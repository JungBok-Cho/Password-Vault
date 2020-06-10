/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package exceptions;
/**
 * This is a program to throw PasswordMismatchException
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class PasswordMismatchException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Error message for unlockUser function
	 * 
	 * @param username The username that failed to log in
	 */
	public PasswordMismatchException(String username) {
		super("Error: Password Mismatch - " + username + 
			  "\n       => If you do not remember your password,"
			  + "\n          Please contact with an administrator.");
    }

	/**
	 * Error message for logIn function
	 * 
	 * @param username	The username that failed to log in
	 * @param tries	    The number of tries
	 */
	public PasswordMismatchException(String username, int tries) {
		super(errorMessage(username, tries));
    }
	
	/**
	 * Helper method of logIn function
	 * 
	 * @param username  The username that failed to log in
	 * @param tries	    The number of tries
	 * @return Return error message
	 */
	private static String errorMessage(String username, int tries) {
		if(tries == 1) {
			return("Error: Password Mismatch - " + username + " & " 
					+ tries + "st time wrong");	
		} else if (tries == 2) {
			return("Error: Password Mismatch - " + username + " & " 
					+ tries + "nd time wrong");	
		}  else {
			return("Error: Password Mismatch - " + username + " & " 
					+ tries + "rd time wrong\n" + "       => Your "
					+ "account is locked out now.");	
		}
	}

}
