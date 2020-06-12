/*
 * JungBok Cho
 * Password vault system
 */
package encrypt;
import java.util.Random;

/**
 * This is a program to encrypt and decrypt a password.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class CaesarCipher implements Encryptor {

	
	/**
	 * Constructor of CaesarCipher class
	 */
	public CaesarCipher() {
		shift = getShift();
	}
	
	
	/**
	 * Constructor of CaesarCipher class to use singleton pattern
	 * 
	 * @return  Return a static object of CaesarCipher class
	 */
	public static CaesarCipher getInstance() {
		return INSTANCE;
	}
	
	
	@Override
	public String encrypt(String password) {
		return encryptDecrypt(password, true);
	}
	

	@Override
	public String decrypt(String password) {
		return encryptDecrypt(password, false);
	}
	
	
	/**
	 * To shift words in the ASCII table
	 * 
	 * @return  Return the number to shift in the ASCII table
	 */
	private static int getShift() {
		Random r = new Random();  // Create a Random object 
		int low = 1;
		int high = OFFSET_MAX - OFFSET_MIN;
		return r.nextInt(high - low) + low;
	}
	
	
	/**
	 * To encrypt or decrypt a password
	 * 
	 * @param password  Password to encrypt or decrypt
	 * @param encrypt   To check if user wants to encrypt or decrypt
	 * @return	Return an encrypted or decrypted password
	 * @throws IllegalArgumentException
	 */
	private String encryptDecrypt(String password, boolean encrypt) throws IllegalArgumentException {
		// StringBuilder object to create encrypted or decrypted password
		StringBuilder sb = new StringBuilder();	
		
		for (char c : password.toCharArray()) {
			int indx = c, cpos;
			
			if (!isPositionInRange(indx)) {
				throw new IllegalArgumentException("String to be encrypted "
								    + "has unrecognized "
								    + "character " + c);
			}
			
			// Encrypt the password
			if (encrypt) {
				cpos = indx + shift;
				if (cpos > OFFSET_MAX)
					cpos = OFFSET_MIN + (cpos - OFFSET_MAX);
				
			// Decrypt the password
			} else {
				cpos = indx - shift;
				if (cpos < OFFSET_MIN)
					cpos = OFFSET_MAX - (OFFSET_MIN - cpos);	
			}
			
			sb.append((char)cpos);
		}
		return sb.toString();		
	}	
	
	
	/**
	 * Check if the character is in the range between 32 and 126 
	 * in the ASCII table
	 * 
	 * @param indx	The number in the ASCII table
	 * @return	Return the number in the ASCII table
	 */
	private boolean isPositionInRange(int indx) {
		return indx >= OFFSET_MIN && indx <= OFFSET_MAX;
	}
	
	
	// To store the number to shift in the ASCII table
	private static int shift;	
	
	// The starting character in the ASCII table
        private static final int OFFSET_MIN = 32;	
    
        // The ending character in the ASCII table
        private static final int OFFSET_MAX = 126;	
    
        // CaesarCipher object for the singleton pattern
        private static final CaesarCipher INSTANCE = new CaesarCipher();	
   
}
