package service;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ColorChooserDialog {
    
    public static String showColorChooserDialog() {
        // Set the default UI look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create an array of color names
        String[] colors = { "Blue", "Red", "Yellow", "Green" };

        // Show the option dialog box with radio buttons and get the selected color
        Object selectedObject = JOptionPane.showInputDialog(null, "Select a color", "Color Chooser",
                JOptionPane.PLAIN_MESSAGE, null, colors, colors[0]);

        // Check if the user selected a color or closed the JOptionPane
        String selectedColor;
        if (selectedObject != null) {
            selectedColor = (String) selectedObject;
        } else {
            selectedColor = "None";
        }

        // Return the selected color
        return selectedColor;
    }
}
