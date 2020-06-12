/*
 * JungBok Cho
 * Password vault program
 */
package encrypt;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This is a program to do JUnit test for the CaesarCipher class.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
class CaesarCipherTest {
	
	/**
	 * Test the first constructor by creating an non-static object
	 */
	@Test
	void testCaesarCipher() {
		// Creating an non-static object
		CaesarCipher cipher = new CaesarCipher();
		CaesarCipher cipher1 = cipher;
		CaesarCipher cipher2 = new CaesarCipher();
		
		assertEquals(cipher, cipher1);
		assertNotEquals(cipher, cipher2);
	}
	
	
	/**
	 * Test the first constructor by creating a static object,
	 * using the singleton pattern
	 */
	@Test
	void testGetInstance() {
		// Creating an static object
		CaesarCipher cipher = CaesarCipher.getInstance();
		CaesarCipher cipher1 = cipher;
		CaesarCipher cipher2 = CaesarCipher.getInstance();
		
		assertEquals(cipher, cipher1);
		assertEquals(cipher, cipher2);
	}
	
	
	/**
	 * Test the encrypt method
	 */
	@Test
	void testEncrypt() {
		// Create password to test and Encrypt the password
		String password = "absdf1534&^#";
		String encrptedPassword = CaesarCipher.getInstance().encrypt(password);
		
		assertNotEquals(password, encrptedPassword);
	}
	
	
	/**
	 * Test the exception in the encrypt method
	 */
	@Test
	void testEncryptException() {
		try {
			String password = "absdf\t1534&¤·¤©¤·^#";
			CaesarCipher.getInstance().encrypt(password);
			fail();
		} catch(IllegalArgumentException e) {}
	}
	
	
	/**
	 * Test the decrypt method
	 */
	@Test
	void testDecrypt() {
		// Create a long password to test and Encrypt the password
		String password = "absdf1534&^#";
		String encryptedPassword = CaesarCipher.getInstance().encrypt(password);
		
		// Create a short password to test and Encrypt the password
		String shortPassword = "ab";
		String encryptedShortPassword = CaesarCipher.getInstance().encrypt(shortPassword);
		
		// Test the decrypt method
		assertNotEquals(password, encryptedPassword);
		assertEquals(password, CaesarCipher.getInstance().decrypt(encryptedPassword));
		
		assertNotEquals(shortPassword, encryptedShortPassword);
		assertEquals(shortPassword, CaesarCipher.getInstance().decrypt(encryptedShortPassword));	
	}

}
