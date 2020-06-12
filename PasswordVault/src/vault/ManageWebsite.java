/*
 * JungBok Cho
 * Password vault system
 */
package vault;

/**
 * This is an ADT of Binary Search Tree to save a pair of website and password.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class ManageWebsite {
	
	/**
	 * Constructor of ManageWebsite class
	 */
	public ManageWebsite() {
		root = null;
	}
	
	
	/**
	 * Check if the BST contains the site name
	 * 
	 * @param sitename  The sitename to search
	 * @return	Return true if contains, otherwise return false
	 */
	public boolean contains(String sitename) {
		return contains(root, sitename);
	}
	

	/**
	 * Insert a pair of a sitename and a password into the BST
	 * 
	 * @param sitename  The sitename to add
	 * @param password  The password to add
	 */
	public void insert(String sitename, String password) {
		root = insert(root, sitename, password);
	} 
	
	
	/**
	 * Update a password of a website
	 * 
	 * @param sitename  Sitename to search
	 * @param password  The new password to add
	 */
	public void update(String sitename, String password) {
		root = update(root, sitename, password);
	}
	

	/**
	 * Return the password of the website
	 * 
	 * @param sitename  The sitename to search
	 * @return  Return the password of the website
	 */
	public String get(String sitename) {
		return get(root, sitename);
	}
	
	
	/**
	 * Helper method of insert function
	 * 
	 * @param root      The Root to use
	 * @param sitename  The sitename to add
	 * @param password  The sitename to add
	 * @return  Return the updated root
	 */
	private Node insert(Node root, String sitename, String password) {
		if(root == null) {
			return new Node(sitename, password);
		}
		if(root.website.compareTo(sitename) < 0) {
			root.left = insert(root.left, sitename, password);
		} else if(root.website.compareTo(sitename) > 0) {
			root.right = insert(root.right, sitename, password);
		}
		return root;
	}
	
	
	/**
	 * Helper method of contains function
	 * 
	 * @param root		Root to search
	 * @param sitename  The sitename to find
	 * @return  Return true if contains, otherwise return false
	 */
	private boolean contains(Node root, String sitename) {
		if(root == null) {
			return false;
		}
		if(root.website.compareTo(sitename) < 0) {
			return contains(root.left, sitename);
		} else if(root.website.compareTo(sitename) > 0) {
			return contains(root.right, sitename);
		} else {
			return true;
		}
	}
	
	
	/**
	 * Helper method of update function
	 * 
	 * @param root      Root to update
	 * @param sitename  The sitename to search
	 * @param password  The new password to add
	 * @return  Return the updated root
	 */
	private Node update(Node root, String sitename, String password) {
		if(root == null) {
			return null;
		}
		if(root.website.compareTo(sitename) < 0) {
			root.left = update(root.left, sitename, password);
		} else if(root.website.compareTo(sitename) > 0) {
			root.right = update(root.right, sitename, password);
		} else {
			root.password = password;
		}
		return root;
	}
	
	
	/**
	 * Helper method of get function
	 * 
	 * @param root      The root to search
	 * @param sitename  The sitename to search
	 * @return  Return the password of the website
	 */
	private String get(Node root, String sitename) {
		if(root == null) {
			return null;
		}
		if(root.website.compareTo(sitename) < 0) {
			return get(root.left, sitename);
		} else if(root.website.compareTo(sitename) > 0) {
			return get(root.right, sitename);
		} else {
			return root.password;
		}
	}
	
	
	/**
	 * Node class
	 */
	private class Node {
		private String website;	   // To store a website
		private String password;   // To store a password of the website
		private Node left;	   // Left Node
		private Node right;	   // Right Node
		
		/**
		 * Constructor of Node clas
		 * 
		 * @param website   The website to store
		 * @param password  The password to store
		 */
		public Node(String website, String password) {
			this.website = website;
			this.password = password;
			this.left = null;
			this.right = null;
		}
	}
	
	
	private Node root; // To store a root of a website
	
}
