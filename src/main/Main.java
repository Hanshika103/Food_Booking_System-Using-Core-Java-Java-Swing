package main; 
// This is the main package → entry point of the application

import javax.swing.SwingUtilities; 
// Swing utility class → used to run GUI safely on Event Dispatch Thread (EDT)

import ui.LoginFrame; 
// Import LoginFrame → first screen (UI) shown to user

// Main class → starting point of Java application
public class Main {

    // Main method → JVM starts execution from here
    public static void main(String[] args) {

        // ==============================
        // IMPORTANT: Swing Thread Rule
        // ==============================
        // All Swing GUI code must run on Event Dispatch Thread (EDT)
        // to avoid UI freezing or thread issues

        SwingUtilities.invokeLater(() -> {

            // Create and show Login window
            new LoginFrame(); 
            // This opens the login GUI when application starts
        });
    }
}
