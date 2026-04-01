

package main;

import javax.swing.SwingUtilities;
import ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Open Login GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new LoginFrame(); // Launch login frame
        });
    }
}

