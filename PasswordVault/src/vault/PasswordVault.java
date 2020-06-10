/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package vault;
import java.util.HashMap;
import encrypt.CaesarCipher;
import encrypt.Encryptor;
import exceptions.DuplicateSiteException;
import exceptions.DuplicateUserException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidSiteException;
import exceptions.InvalidUsernameException;
import exceptions.PasswordMismatchException;
import exceptions.SiteNotFoundException;
import exceptions.UserLockedOutException;
import exceptions.UserNotFoundException;
/**
 * This is a program to create a password vault.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class PasswordVault implements Vault {
	
	/**
	 * The first Constructor of PasswordVault class
	 */
	public PasswordVault() {
		this(new CaesarCipher());  // Call the second constructor
	}
	
	/**
	 * The second Constructor of PasswordVault class
	 */
	public PasswordVault(Encryptor e) {
		// Initialize HashMap and Random objects
		vaultLogIn =  new HashMap<>();
		vaultUser = new HashMap<>();
		suspiciousUser = new HashMap<>();
	}
	
	public void addNewUser(String username, String password) throws
				  		   exceptions.InvalidUsernameException,
				 		   exceptions.InvalidPasswordException, 
				 		   exceptions.DuplicateUserException {
		// Check if the username is in a correct format
		if(CheckingValidation.getInstance().checkingUserName(username)) {
			throw new InvalidUsernameException();
		// Check if the password is in a correct format
		} else if(CheckingValidation.getInstance().checkingPassword(password)) {
			throw new InvalidPasswordException();
		// Add a new user
		} else { 
			// Check if the requested username already exists
			if(vaultLogIn.containsKey(username)) {
				throw new DuplicateUserException();
			} else {
				vaultLogIn.put(username, CaesarCipher.getInstance().
							   encrypt(password));
				vaultUser.put(username, null);
			}
		}
	}
	
	public String addNewSite(String username, String password, String sitename)
							 throws  exceptions.DuplicateSiteException, 
									 exceptions.UserNotFoundException,
									 exceptions.UserLockedOutException, 
									 exceptions.PasswordMismatchException, 
									 exceptions.InvalidSiteException {
		
		ManageWebsite root = vaultUser.get(username);  // To store the root
		String newPassword = "";  // To store a website password
		
		logIn(username, password); // Check three exceptions first
		
		// Check if the sitename is in a correct format
		if(CheckingValidation.getInstance().checkingWebsite(sitename)) {
			throw new InvalidSiteException();
		}
		
		// If the root is null, create an object of BST and put in the HashMap
		if(root == null) {
			newPassword = CheckingValidation.getInstance().generatePassword();
			ManageWebsite temp = new ManageWebsite();
			temp.insert(sitename, CaesarCipher.getInstance().
						encrypt(newPassword));
			vaultUser.put(username, temp);
		// Check if the root already has the website
		} else {
			// If exists, throw exception
			if(root.contains(sitename)) {
				throw new DuplicateSiteException();
			// If not, add new website
			} else {
				newPassword = CheckingValidation.getInstance().generatePassword();
				root.insert(sitename, CaesarCipher.getInstance().
							encrypt(newPassword));
			}
		}
		return newPassword;
	}
	
	public String updateSitePassword(String username, String password, 
									 String sitename) throws
									 exceptions.SiteNotFoundException, 
									 exceptions.UserNotFoundException,
									 exceptions.UserLockedOutException, 
									 exceptions.PasswordMismatchException {
		ManageWebsite root = vaultUser.get(username); // To store the root 
		String newPassword = "";  // To store a new website password
		
		logIn(username, password);  // Check three exceptions first
		
		// If the root is null, throw exception
		if(root == null) {
			throw new SiteNotFoundException();
		// Check if the user has the sitename
		} else {
			if(!root.contains(sitename)) {
				throw new SiteNotFoundException();
			// Update the new password
			} else {
				newPassword = CheckingValidation.getInstance().generatePassword();
				root.update(sitename, CaesarCipher.getInstance().
							encrypt(newPassword));
			}
		}
		return newPassword;
	}
	
	public String retrieveSitePassword(String username, String password, 
									   String sitename) throws
									   exceptions.SiteNotFoundException, 
									   exceptions.UserNotFoundException,
									   exceptions.UserLockedOutException, 
									   exceptions.PasswordMismatchException {
		ManageWebsite root = vaultUser.get(username);  // To store the root 
		String getPassword = "";  // To store a password
		
		logIn(username, password);  // Check three exceptions first
		
		// If the root is null, throw exception
		if(root == null) {
			throw new SiteNotFoundException();
		// Check if the user has the sitename
		} else {
			if(!root.contains(sitename)) {
				throw new SiteNotFoundException();
			} else {
				getPassword = root.get(sitename);
				return CaesarCipher.getInstance().decrypt(getPassword);
			}
		}
	}
	
	/**
	 * To unlock a user
	 * 
	 * @param username  Username to unlock
	 * @param password  Password to check
	 * @return Return unlocked if it got unlocked, otherwise return null
	 * @throws UserNotFoundException
	 * @throws PasswordMismatchException
	 */
	public String unlockUser(String username, String password) throws 
	   						 exceptions.UserNotFoundException,
	   						 exceptions.PasswordMismatchException {
		// Check if the username exists
		if(!vaultLogIn.containsKey(username)) {
			throw new UserNotFoundException();
		} else {
			// // Check if the user got locked out
			if(suspiciousUser.containsKey(username) 
				&& suspiciousUser.get(username) == 3) {
				// If password is correct, unlock the user
				if(CaesarCipher.getInstance().encrypt(password).equals
				   (vaultLogIn.get(username))) {
					suspiciousUser.remove(username);
					return "unlocked";
				} else {
					throw new PasswordMismatchException(username);
				}
			} else {
				return null;
			}
		}
	}
	
	/**
	 * Check three exception before processing other functions
	 * 
	 * @param username  Username to check
	 * @param password  Password to check
	 * @throws UserNotFoundException
	 * @throws UserLockedOutException
	 * @throws PasswordMismatchException
	 */
	private void logIn(String username, String password) throws 
	  				   exceptions.UserNotFoundException, 
	  				   exceptions.UserLockedOutException,
	  				   exceptions.PasswordMismatchException {
		// If the user was not found, throw exception
		if(!vaultLogIn.containsKey(username)) {
			throw new UserNotFoundException();
		}
		// If the user failed to log in 3 times, throw exception
		if(suspiciousUser.containsKey(username) && 
		   suspiciousUser.get(username) == 3) {
			throw new UserLockedOutException(username);
		} else {
			// If the password did not match throw exception
			if(!CaesarCipher.getInstance().encrypt(password).equals
				(vaultLogIn.get(username))) {
				if(!suspiciousUser.containsKey(username)) {
					suspiciousUser.put(username, 1);
				} else {
					suspiciousUser.put(username,suspiciousUser.get(username) + 1);
				}
				throw new PasswordMismatchException(username,suspiciousUser.
															 get(username));
			} else {
				// If the user enter a correct password, 
				// remove the username from the HashMap, suspicious 
				if(suspiciousUser.containsKey(username)) {
					suspiciousUser.remove(username);
				}
			}
		}
	}
	
	// To store usernames and passwords
	private HashMap<String, String> vaultLogIn;
	
	// To store usernames and roots of websites
	private HashMap<String, ManageWebsite> vaultUser;
	
	// To store suspicious users that typed a wrong password
	private HashMap<String,Integer> suspiciousUser;
	
}
