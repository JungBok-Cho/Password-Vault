/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package menu;
import java.util.Scanner;
import exceptions.DuplicateSiteException;
import exceptions.DuplicateUserException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidSiteException;
import exceptions.InvalidUsernameException;
import exceptions.PasswordMismatchException;
import exceptions.SiteNotFoundException;
import exceptions.UserLockedOutException;
import exceptions.UserNotFoundException;
import vault.PasswordVault;
/**
 * This is a program to create a menu-driven console app.
 * 
 * Reference: <a href="https://www.java-forums.org/new-java/26584-
 * menu-driven-console-help-please.html">A Simple Text-Based Menu System</a>
 * 
 * @author  Jungbok Cho
 * @version 1.0
 */
public class MenuDriver {
	
	/**
     * Call run function using topMenu
     * 
	 * @param args A string array containing the command line arguments.
	 */
    public static void main(String[] args) {
        topMenu.run();
    }
	
	/**
	 * Object to process the addNewUser function
	 */
    private static MenuItem item1 = new MenuItem("Add New User", new Runnable() {
        public void run() {
        	// Get a username
        	System.out.println();
        	System.out.print("Enter a username: ");
        	username = keyboard.nextLine();
        	
        	// Get a password
        	System.out.print("Enter a password: ");
        	password = keyboard.nextLine();
        	
        	// Call the addNewUser function
        	try {
				vault.addNewUser(username, password);
				System.out.println("Added user " + "'" + username + "'\n");
			} catch (InvalidUsernameException | InvalidPasswordException | 
					DuplicateUserException e) {
				System.err.println(e.getMessage() + "\n");
				System.err.flush();
		        try {
		            Thread.sleep(1);
		        } catch (InterruptedException k) {
		            return;
		        }
			}
        }
    });
    
	/**
	 * Object to process the addNewSite function
	 */
    private static MenuItem item2 = new MenuItem("Add New Site", new Runnable() {
        public void run() {
        	// Get a username
        	System.out.println();
        	System.out.print("Enter a username: ");
        	username = keyboard.nextLine();
        	
        	// Get a password
        	System.out.print("Enter a password: ");
        	password = keyboard.nextLine();
        	
        	// Get a website
        	System.out.print("Enter a website: ");
        	website = keyboard.nextLine();
        	
        	// Call the addNewSite function
        	try {
        		System.out.println("Add site: '" + website + "' for user '" + 
        				username + "' \n           => generated site password: " +
						vault.addNewSite(username, password, website));
			} catch (UserNotFoundException | UserLockedOutException | 
					 PasswordMismatchException | DuplicateSiteException | 
					 InvalidSiteException e) {
				System.err.println(e.getMessage() + "\n");
				System.err.flush();
		        try {
		            Thread.sleep(1);
		        } catch (InterruptedException k) {
		            return;
		        }
			}
        }
    });
     
	/**
	 * Object to process the updateSitePassword function
	 */
    private static MenuItem item3 = new MenuItem("Update Site Password", new Runnable() {
        public void run() {
        	// Get a username
        	System.out.println();
        	System.out.print("Enter a username: ");
        	username = keyboard.nextLine();
        	
        	// Get a password
        	System.out.print("Enter a password: ");
        	password = keyboard.nextLine();
        	
        	// Get a website
        	System.out.print("Enter a website: ");
        	website = keyboard.nextLine();
        	
        	// Call the updateSitePassword function
        	try {
        		System.out.println("Update site password for '" + website 
        				+ "' for user '" + username + "'\n           "
        				+ "=> updated password: " +
						vault.updateSitePassword(username, password, website));
			} catch (UserNotFoundException | UserLockedOutException | 
					 PasswordMismatchException | SiteNotFoundException e) {
				System.err.println(e.getMessage() + "\n");
				System.err.flush();
		        try {
		            Thread.sleep(1);
		        } catch (InterruptedException k) {
		            return;
		        }
			}
        }
    });
 
	/**
	 * Object to process the retrieveSitePassword function
	 */
    private static MenuItem item4 = new MenuItem("Retrieve Site Password", 
    											 new Runnable() {
        public void run() {
        	// Get a username
        	System.out.println();
        	System.out.print("Enter a username: ");
        	username = keyboard.nextLine();
        	
        	// Get a password
        	System.out.print("Enter a password: ");
        	password = keyboard.nextLine();
        	
        	// Get a website
        	System.out.print("Enter a website: ");
        	website = keyboard.nextLine();
        	
        	// Call the retrieveSitePassword function
        	try {
        		System.out.println("Retrieve site password for '" + website + 
        				"' for user '" + username + "'\n           => password: " +
    					vault.retrieveSitePassword(username, password, website));
			} catch (UserNotFoundException | UserLockedOutException | 
					 PasswordMismatchException | SiteNotFoundException e) {
				System.err.println(e.getMessage() + "\n");
				System.err.flush();
		        try {
		            Thread.sleep(1);
		        } catch (InterruptedException k) {
		            return;
		        }
			}
        }
    });
    
   	/**
   	 * Object to process the unlockUser function
   	 */
    private static MenuItem item5 = new MenuItem("Unlock Account", new Runnable() {
        public void run() {
        	// Get a username
        	System.out.println();
        	System.out.print("Enter a username: ");
        	username = keyboard.nextLine();
        	
        	// Get a password
        	System.out.print("Enter a password: ");
        	password = keyboard.nextLine();
        	
        	// Call the unlockUser function
			try {
				password = vault.unlockUser(username, password);
				if(password == null) {
	    			System.out.println("Your account is not locked.\n");
	    		} else if(password.equals("unlocked")) {
	    			System.out.println("Your account is unlocked now.\n");
	    		}
			} catch (UserNotFoundException | PasswordMismatchException e) {
				System.err.println(e.getMessage() + "\n");
				System.err.flush();
		        try {
		            Thread.sleep(1);
		        } catch (InterruptedException k) {
		            return;
		        }
			}
        }
    });
    
    // Object to store all the obejcts (functions) above
    private static Menu topMenu = new Menu("top menu", false, true, item1, 
    										item2, item3, item4, item5);
    
    // PasswordVault object
 	private static PasswordVault vault = new PasswordVault(); 
 	
 	// Scanner object
 	private static Scanner keyboard = new Scanner(System.in);
 	
 	private static String username = "";  // Username to store
 	private static String password = "";  // Password to store
 	private static String website = "";   // Website to store
 	
}
