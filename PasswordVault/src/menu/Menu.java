/*
 * JungBok Cho
 * Password vault system
 */
package menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a program to implement MenuItem.
 * 
 * Reference: <a href="https://www.java-forums.org/new-java/26584-menu-driven
 * -console-help-please.html">A Simple Text-Based Menu System</a>
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class Menu extends MenuItem {
    
    List<MenuItem> items;  // List to store MenuItem
 
	
    /**
     * Menu Constructor
     * 
     * @param title  Title of the menu
     * @param items  Items to use
     */
    public Menu(String title, MenuItem ... items) { this(title, false, true, items); }
	
 
    /**
     * Second Menu Constructor
     *  
     * @param title    Title of the menu
     * @param addBack  Check if the menu wants the go back function
     * @param addQuit  Check if the menu wants the quit function
     * @param items    Items to use
     */
    public Menu(String title, boolean addBack, boolean addQuit, MenuItem ... items) {
        super(title);
        setExec(this);
 
        initialize(addBack, addQuit, items);  // Call initialize
    }
	
     
    /**
     * Initialize the menu
     * 
     * @param addBack  Check if the menu wants the go back function
     * @param addQuit  Check if the menu wants the quit function
     * @param items    Items to use
     */
    private void initialize(boolean addBack, boolean addQuit, MenuItem ... items) {
        this.items = new ArrayList<MenuItem>(Arrays.asList(items));
        if (addBack) this.items.add(back);
        if (addQuit) this.items.add(quit);
    }
	
     
    /**
     * Display the menu
     */
    private void display() {
        int option = 0;
        System.out.println(getTitle() + ":");
        for (MenuItem item : items) {
            System.out.println((option++) + ": " + item.getTitle());
        }
        System.out.print("select option: ");
        System.out.flush();
    }
	
     
    /**
     * Prompt the menu
     * 
     * @return  Return the item option
     * @throws IOException
     */
    private MenuItem prompt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        while (true) {
            display();
         
            String line = br.readLine();
            try {
                int option= Integer.parseInt(line);
                if (option >= 0 && option < items.size())
                    return items.get(option);
            } catch (NumberFormatException e) { }
         
            System.out.println("not a valid menu option: " + line + "\n");
        } 
    }
	
     
    /**
     * Run the menu
     */
    public void run() {
        try {
            for (MenuItem item = prompt(); item.isExec(); item = prompt())
                item.run();
        } catch (Throwable t) {
            t.printStackTrace(System.out);
        }
    }
    
	
    /**
     * MenuItem object to quit
     */
    private static final MenuItem quit = new MenuItem("quit", new Runnable() {
        public void run() {
            System.exit(0);
        }
    });
     
	
    /**
     * MenuItem object to go back
     */
    private static final MenuItem back = new MenuItem("back");
    
}
