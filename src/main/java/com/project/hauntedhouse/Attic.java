package com.project.hauntedhouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Attic extends JPanel {
    private HauntedHouse mainPanel; // Reference to the main panel
    private Player player; // Reference to the player object
    private JPanel gameOverPanel; // Panel for the game over screen
    private JPanel navigationPanel; // Panel for navigation buttons

    // Constructor for Attic class
    public Attic(HauntedHouse mainPanel, Player player) {
        this.mainPanel = mainPanel; // Set the main panel reference
        this.player = player; // Set the player reference

        setLayout(new BorderLayout()); // Set layout for the panel

        // Load the attic image
        JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/attic-resized.jpg"));
        add(imageLabel, BorderLayout.CENTER); // Add the image to the center of the panel

        // Check if the player has the note
        if (player.hasNote()) {
            // Show the riddle if the player has the note
            mainPanel.showMessage(
                "Three chests are placed before you:\n" +
                "One is red, one is green, and one is blue.\n" +
                "Only one chest contains the gold you seek.\n" +
                "The other two contain deadly dangers: one has a serpent and the other, poisonous gas.\n\n" +
                "There are three clues:\n" +
                "1. The gold is not in the red chest.\n" +
                "2. The serpent is not in the blue chest.\n" +
                "3. The green chest does not contain poisonous gas.\n\n" +
                "Which chest will you open?"
            );
            player.setHasReadRiddle(true); // Set flag indicating the player has read the riddle
        }

        // Create navigation buttons
        JButton readNoteButton = new JButton("Read Note");
        JButton backButton = new JButton("Back to Upstairs");

        // Add action listener to the read note button
        readNoteButton.addActionListener(e -> readRiddle());

        // Add action listener to the back button
        backButton.addActionListener(e -> mainPanel.goToRoom("Upstairs"));

        // Create navigation panel and set layout
        navigationPanel = new JPanel(new GridLayout(1, player.hasReadRiddle() ? 4 : 1));

        // Add components to the navigation panel
        navigationPanel.add(readNoteButton);
        navigationPanel.add(backButton);

        // Add navigation panel to the bottom of the main panel
        add(navigationPanel, BorderLayout.SOUTH);
    }

    // Method to handle opening the chest based on its color
    private void openChest(String chestColor) {
        if (!player.hasKey()) { // Check if the player has the key
            mainPanel.showMessage("You need keys to open the chests."); // Show message if key is not available
            return;
        }

        // Check the color of the chest and take appropriate action
        switch (chestColor) {
            case "Red":
                showGameOverScreen("src/main/resources/attic-serpent.jpg", "Game Over: The Great Serpent devoured you!");
                break;
            case "Green":
                showGameOverScreen("src/main/resources/attic-poison-chest.jpg", "Game Over: You were suffocated by poisonous gas!");
                break;
            case "Blue":
                showVictoryScreen();
                break;
        }
    }

    // Method to display the game over screen
    private void showGameOverScreen(String imagePath, String message) {
        removeAll(); // Remove all components from the panel
        gameOverPanel = new JPanel(new BorderLayout()); // Create a new panel for the game over screen
        JLabel gameOverLabel = new JLabel(new ImageIcon(imagePath)); // Create a label with the game over image
        JButton backButton = new JButton("Return to Main Screen"); // Create a button to return to the main screen

        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.resetGame(); // Reset the game when returning to the main screen
                mainPanel.showStartScreen(); // Show the start screen
            }
        });

        // Add components to the game over panel
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER); // Add the game over image to the center
        gameOverPanel.add(backButton, BorderLayout.SOUTH); // Add the back button to the bottom

        // Add the game over panel to the current panel
        add(gameOverPanel, BorderLayout.CENTER);

        // Show the game over message
        mainPanel.showMessage(message);

        // Revalidate and repaint the panel
        revalidate();
        repaint();
    }

    // Method to display the victory screen
    
private void showVictoryScreen() {
        removeAll(); // Remove all components from the panel
        JPanel victoryPanel = new JPanel(new BorderLayout()); // Create a panel for the victory screen
        JLabel victoryLabel = new JLabel(new ImageIcon("src/main/resources/attic-gold-chest.png")); // Create a label with the victory image

        // Add action listener to the back button
        JButton backButton = new JButton("Return to Main Screen"); // Create a button to return to the main screen

        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.resetGame(); // Reset the game when returning to the main screen
                mainPanel.showStartScreen(); // Show the start screen
            }
        });

        // Add components to the victory panel
        victoryPanel.add(victoryLabel, BorderLayout.CENTER); // Add the victory image to the center
        victoryPanel.add(backButton, BorderLayout.SOUTH); // Add the back button to the bottom

        // Add the victory panel to the current panel
        add(victoryPanel, BorderLayout.CENTER);

        // Show the victory message
        mainPanel.showMessage("Congratulations! You found the gold and won the game!");
        
        // Revalidate and repaint the panel
        revalidate();
        repaint();
    }

    // Method to handle reading the riddle
    public void readRiddle() {
        if (!player.hasNote()) { // Check if the player has the note
            mainPanel.showMessage("Look for a note to figure out which chest to open"); // Show message if note is not found
        } else {
            navigationPanel.removeAll(); // Remove all components from the navigation panel
            mainPanel.showMessage(
                "Three chests are placed before you:\n" +
                "One is red, one is green, and one is blue.\n" +
                "Only one chest contains the gold you seek.\n" +
                "The other two contain deadly dangers: one has a serpent and the other, poisonous gas.\n\n" +
                "There are three clues:\n" +
                "1. The gold is not in the red chest.\n" +
                "2. The serpent is not in the blue chest.\n" +
                "3. The green chest does not contain poisonous gas.\n\n" +
                "Which chest will you open?"
            ); // Show the riddle

            // Create buttons to open each chest and back button
            JButton openRedChestButton = new JButton("Open Red Chest");
            JButton openGreenChestButton = new JButton("Open Green Chest");
            JButton openBlueChestButton = new JButton("Open Blue Chest");
            JButton backButton = new JButton("Back to Upstairs");

            // Add action listeners to the chest buttons
            openRedChestButton.addActionListener(e -> openChest("Red"));
            openGreenChestButton.addActionListener(e -> openChest("Green"));
            openBlueChestButton.addActionListener(e -> openChest("Blue"));

            // Add action listener to the back button
            backButton.addActionListener(e -> mainPanel.goToRoom("Upstairs"));

            // Add buttons to the navigation panel
            navigationPanel.add(openRedChestButton);
            navigationPanel.add(openBlueChestButton);
            navigationPanel.add(openGreenChestButton);
            navigationPanel.add(backButton);

            // Repaint and revalidate the navigation panel
            navigationPanel.repaint();
            navigationPanel.revalidate();
        }
    }
}
