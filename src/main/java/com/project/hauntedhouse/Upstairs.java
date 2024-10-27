package com.project.hauntedhouse;

import javax.swing.*;
import java.awt.*;

public class Upstairs extends JPanel {
    private HauntedHouse mainPanel; // Reference to the main HauntedHouse panel
    private Player player; // Reference to the player

    // Constructor for the Upstairs class
    public Upstairs(HauntedHouse mainPanel, Player player) {
        this.mainPanel = mainPanel; // Initialize main panel reference
        this.player = player; // Initialize player reference
        setLayout(new BorderLayout()); // Set layout manager for the panel

        // Load the upstairs image
        JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/upstairs.jpg"));

        // Create navigation buttons
        JButton atticButton = new JButton("Go to Attic (West)"); // Button to go to the attic
        JButton storeButton = new JButton("Go to Store (Right)"); // Button to go to the storeroom
        JButton backButton = new JButton("Go back to Hall"); // Button to go back to the hall

        // Action listener for attic button
        atticButton.addActionListener(e -> mainPanel.goToRoom("Attic"));

        // Action listener for store button with logic to check if player has a fork
        storeButton.addActionListener(e -> {
            if (player.hasFork()) { // Check if player has a fork
                mainPanel.showMessage("You used the fork to pick the lock and enter the storeroom.");
                mainPanel.goToRoom("Store"); // Go to storeroom if player has a fork
            } else {
                mainPanel.showMessage("You need a fork to pick the lock and enter the storeroom.");
            }
        });

        // Action listener for back button
        backButton.addActionListener(e -> mainPanel.goToRoom("Hall"));

        // Create a panel for the navigation buttons
        JPanel navigationPanel = new JPanel(new GridLayout(1, 3));
        navigationPanel.add(atticButton); // Add attic button to panel
        navigationPanel.add(storeButton); // Add store button to panel
        navigationPanel.add(backButton); // Add back button to panel

        // Position the navigation panel to the south
        add(navigationPanel, BorderLayout.SOUTH);

        // Position the image to the center
        add(imageLabel, BorderLayout.CENTER);
    }
}
