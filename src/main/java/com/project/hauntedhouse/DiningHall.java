/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.hauntedhouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiningHall extends JPanel {
    private HauntedHouse mainPanel; // Reference to the main panel containing all rooms
    private Player player; // Reference to the player object
    private Timer timer; // Timer to track the time after picking up the fork
    private JPanel gameOverPanel; // Panel to show game over screen

    // Constructor for DiningHall
    public DiningHall(HauntedHouse mainPanel, Player player) {
        this.mainPanel = mainPanel; // Set the main panel reference
        this.player = player; // Set the player reference

        setLayout(new BorderLayout()); // Set layout for this panel

        // Load the dining hall image
        JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/dining-hall.jpg"));

        // Create navigation buttons
        JButton takeForkButton = new JButton("Take Fork");
        JButton backButton = new JButton("Back to Hall");

        // Add action listener to the take fork button
        takeForkButton.addActionListener(e -> {
            mainPanel.showMessage("You took a fork from the dining table. Elizabeth is coming for you. Run for your life."); // Show message when fork is taken
            player.addItem("fork"); // Add fork to player's inventory
            startTimer(); // Start the timer when the fork is picked up
        });

        // Add action listener to the back button
        backButton.addActionListener(e -> {
            stopTimer(); // Stop the timer if the player goes back to the Hall
            mainPanel.goToRoom("Hall"); // Navigate back to the hall
        });

        // Create a panel for interaction buttons and add buttons to it
        JPanel interactionPanel = new JPanel(new GridLayout(1, 2));
        interactionPanel.add(takeForkButton);
        interactionPanel.add(backButton);

        // Add interaction panel to the bottom and image to the center
        add(interactionPanel, BorderLayout.SOUTH);
        add(imageLabel, BorderLayout.CENTER);
    }

    // Method to start the timer
    private void startTimer() {
        timer = new Timer(3000, new ActionListener() { // 3 seconds timer
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameOverPanel(); // Show game over screen if timer elapses
            }
        });
        timer.setRepeats(false); // Timer should only fire once
        timer.start(); // Start the timer
    }

    // Method to stop the timer
    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // Stop the timer if it's running
        }
    }

    // Method to show the game over screen
    private void showGameOverPanel() {
        removeAll(); // Remove current components
        gameOverPanel = new JPanel(new BorderLayout()); // Create a new game over panel with border layout

        // Load the game over image
        JLabel gameOverLabel = new JLabel(new ImageIcon("src/main/resources/dining-hall-ghost.png"));

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
        gameOverPanel.add(backButton, BorderLayout.SOUTH); // Add back button to bottom

        add(gameOverPanel, BorderLayout.CENTER); // Add game over panel to the current panel
        mainPanel.showMessage("Elizabeth cut you to pieces for messing with her cutlery."); // Show game over message
        revalidate(); // Revalidate the panel
        repaint(); // Repaint the panel
    }
}
