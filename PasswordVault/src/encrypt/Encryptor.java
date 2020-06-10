/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package encrypt;
/**
 * This is an Interface class for the CaesarCipher class.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public interface Encryptor {
	
	/**
	 * Encrypts the passed in string
	 * 
	 * @param password The string to encrypt
	 * @return  The encrypted string
	 */
	String encrypt(String password);
	
	/**
	 * Decrypts the passed in string
	 * 
	 * @param password The string to decrypt
	 * @return  The (plaintext) decrypted string
	 */
	String decrypt(String password);
	
}