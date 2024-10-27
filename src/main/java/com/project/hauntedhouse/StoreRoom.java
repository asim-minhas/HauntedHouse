package com.project.hauntedhouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreRoom extends JPanel {
    private HauntedHouse mainPanel; // Reference to the main HauntedHouse panel
    private Player player; // Reference to the player
    private JPanel gameOverPanel; // Panel to display game over screen

    // Constructor for the StoreRoom class
    public StoreRoom(HauntedHouse mainPanel, Player player) {
        this.mainPanel = mainPanel; // Initialize main panel reference
        this.player = player; // Initialize player reference

        setLayout(new BorderLayout()); // Set layout manager for the panel

        // Load the store room image
        JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/store-room.jpg"));

        // Create navigation buttons
        JButton solveRiddleButton = new JButton("Solve Grim Reaper's Riddle"); // Button to solve riddle
        JButton backButton = new JButton("Back to Upstairs"); // Button to go back to upstairs

        // Action listener for solve riddle button
        solveRiddleButton.addActionListener(e -> showRiddleDialog());
        // Action listener for back button
        backButton.addActionListener(e -> mainPanel.goToRoom("Upstairs"));

        // Create a panel for the navigation buttons
        JPanel interactionPanel = new JPanel(new GridLayout(1, 2));
        interactionPanel.add(solveRiddleButton); // Add solve riddle button to panel
        interactionPanel.add(backButton); // Add back button to panel

        add(interactionPanel, BorderLayout.SOUTH); // Add navigation panel to the south
        add(imageLabel, BorderLayout.CENTER); // Add image label to the center
    }

    // Method to show the riddle dialog
    private void showRiddleDialog() {
        String riddle = "What is 5 * (5+5)?"; // Define the riddle
        // Show input dialog to get the player's answer
        String answer = JOptionPane.showInputDialog(this, riddle, "Grim Reaper's Riddle", JOptionPane.QUESTION_MESSAGE);

        // Check if the answer is correct
        if (answer != null && answer.equalsIgnoreCase("50")) {
            player.setHasKey(true); // Player receives the key to the chests
            mainPanel.showMessage("Correct! The Grim Reaper gives you the keys to the chests.");
        } else {
            showGameOverPanel(); // Show game over screen if answer is incorrect
        }
    }

    // Method to show the game over screen
    private void showGameOverPanel() {
        removeAll(); // Remove current components
        gameOverPanel = new JPanel(new BorderLayout()); // Create a new game over panel

        // Load the game over image
        JLabel gameOverLabel = new JLabel(new ImageIcon("src/main/resources/store-room-ghost.jpg"));

        // Create button to go back to main screen
        JButton backButton = new JButton("Return to Main Screen");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.resetGame(); // Reset the game when returning to the main screen
                mainPanel.showStartScreen(); // Show the start screen
            }
        });

        // Add components to the game over panel
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER); // Add game over image to center
        gameOverPanel.add(backButton, BorderLayout.SOUTH); // Add back button to south

        add(gameOverPanel, BorderLayout.CENTER); // Add game over panel to the center
        mainPanel.showMessage("The Grim Reaper took your head for failing to solve his riddle.");
        revalidate(); // Revalidate the panel
        repaint(); // Repaint the panel
    }
}
