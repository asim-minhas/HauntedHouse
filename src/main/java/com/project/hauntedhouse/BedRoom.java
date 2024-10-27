package com.project.hauntedhouse;

import javax.swing.*;
import java.awt.*;

public class BedRoom extends JPanel {
    private HauntedHouse mainPanel; // Reference to the main panel containing all rooms
    private Player player; // Reference to the player object
    private boolean lightsOn; // Flag to track if the lights are on
    private boolean noteTaken; // Flag to track if the note has been taken
    private JPanel navigationPanel; // Panel to hold navigation buttons
    private JButton takeNoteButton; // Button to take the note

    // Constructor for BedRoom
    public BedRoom(HauntedHouse mainPanel) {
        this.mainPanel = mainPanel; // Set the main panel reference
        this.player = mainPanel.getPlayer(); // Get the player object from the main panel
        this.lightsOn = false; // Initialize lightsOn to false
        this.noteTaken = false; // Initialize noteTaken to false

        setLayout(new BorderLayout()); // Set layout for this panel

        // Load the bedroom image with lights off
        JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/bedroom-lights-off.jpg"));
        add(imageLabel, BorderLayout.CENTER); // Add the image to the center of the panel

        // Create navigation buttons
        JButton lightsButton = new JButton("Turn Lights On");
        JButton lookUnderButton = new JButton("Look Under the Bed");
        takeNoteButton = new JButton("Take the Note");
        takeNoteButton.setVisible(false); // Initially hide the take note button
        JButton backButton = new JButton("Back to Hall");

        // Add action listener to the lights button
        lightsButton.addActionListener(e -> {
            lightsOn = true; // Set lightsOn to true
            imageLabel.setIcon(new ImageIcon("src/main/resources/bedroom-lights-on.jpg")); // Change image to lights on
            mainPanel.showMessage("The lights are now on."); // Show message indicating lights are on
        });

        // Add action listener to the look under button
        lookUnderButton.addActionListener(e -> {
            if (lightsOn) { // Check if lights are on
                if (!noteTaken) { // Check if note has not been taken
                    mainPanel.showMessage("You found a note under the bed."); // Show message indicating note found
                    takeNoteButton.setVisible(true); // Make take note button visible
                    updateNavigationPanel(); // Update the navigation panel
                } else {
                    mainPanel.showMessage("There is nothing else under the bed."); // Show message indicating nothing else is there
                }
            } else { // If lights are off
                mainPanel.showMessage("Game Over. Amelia Ghost got you!"); // Show game over message
                imageLabel.setIcon(new ImageIcon("src/main/resources/bedroom-ghost.jpg")); // Change image to show ghost
                remove(navigationPanel); // Remove navigation panel

                // Create a game over panel with border layout
                JPanel gameOverPanel = new JPanel(new BorderLayout());
                JLabel gameOverLabel = new JLabel("Game Over. Amelia Ghost got you!", SwingConstants.CENTER); // Game over message
                JButton mainScreenButton = new JButton("Go to Main Screen"); // Button to go to main screen
                mainScreenButton.addActionListener(ev -> {
                    mainPanel.resetGame(); // Reset game
                    mainPanel.showStartScreen(); // Go to start screen
                });

                // Add components to the game over panel
                gameOverPanel.add(gameOverLabel, BorderLayout.CENTER); // Add game over message to center
                gameOverPanel.add(mainScreenButton, BorderLayout.SOUTH); // Add back button to bottom
                
                add(gameOverPanel, BorderLayout.SOUTH); // Add game over panel to the current panel
                revalidate(); // Revalidate the panel
                repaint(); // Repaint the panel
            }
        });

        // Add action listener to the take note button
        takeNoteButton.addActionListener(e -> {
            noteTaken = true; // Set noteTaken to true
            player.addItem("note"); // Add note to player's inventory
            mainPanel.showMessage("You have taken the note."); // Show message indicating note taken
            takeNoteButton.setVisible(false); // Hide take note button
            updateNavigationPanel(); // Update the navigation panel
        });

        // Add action listener to the back button
        backButton.addActionListener(e -> mainPanel.goToRoom("Hall")); // Navigate back to the hall

        // Create a panel for navigation buttons and add buttons to it
        navigationPanel = new JPanel(new FlowLayout());
        navigationPanel.add(lightsButton);
        navigationPanel.add(lookUnderButton);
        navigationPanel.add(takeNoteButton);
        navigationPanel.add(backButton);

        add(navigationPanel, BorderLayout.SOUTH); // Add navigation panel to the bottom
    }

    // Method to update the navigation panel
    private void updateNavigationPanel() {
        navigationPanel.revalidate(); // Revalidate the navigation panel
        navigationPanel.repaint(); // Repaint the navigation panel
    }
}
