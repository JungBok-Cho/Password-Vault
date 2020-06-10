/*
 * JungBok Cho
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package menu;
/**
 * This is an instruction class that implements an interface 
 * to write a menu-driven console app.
 * 
 * Reference: <a href="https://www.java-forums.org/new-java/26584-menu-
 * driven-console-help-please.html">A Simple Text-Based Menu System</a>
 * 
 * @author  JosAH
 * @version 1.0
 */
public class MenuItem implements Runnable {
    
    /**
     * Constructor of MenuItem
     *  
     * @param title  Title to display
     */
    protected MenuItem(String title) { this(title, null); }
 
    /**
     * 2nd Constructor of MenuItem
     * 
     * @param title  Title to display
     * @param exec   Runnable object 
     */
    public MenuItem(String title, Runnable exec) {
        this.title= title;
        this.exec= exec;
    }
    
    /**
     * Function to get a title
     * 
     * @return  Return a title
     */
    public String getTitle() { return title; }
     
    /**
     * Check if the function is executable
     * 
     * @return Return true if executable, otherwise return false
     */
    public boolean isExec() { return exec != null; }
     
    /**
     * Set executable
     * 
     * @param exec  Runnable object
     */
    protected void setExec(Runnable exec) { this.exec = exec; }
     
    /**
     * Run a program
     */
    public void run() {
        try {
            exec.run();
        }
        catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
    
    private String title;	// Title to display
    private Runnable exec;	// Runnable object 
    
}