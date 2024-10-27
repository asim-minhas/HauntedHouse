package com.project.hauntedhouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hall extends JPanel {
    private HauntedHouse mainPanel; // Reference to the main panel

    // Constructor for the Hall panel
    public Hall(HauntedHouse mainPanel) {
        this.mainPanel = mainPanel; // Set reference to the main panel
        setLayout(new BorderLayout()); // Set layout manager for the Hall panel

        // Load the image of the hall
        JLabel imageLabel = new JLabel(new ImageIcon("src/main/resources/Hall.jpg"));

        // Panel for navigation buttons
        JPanel navigationPanel = new JPanel(new GridLayout(1, 3)); // Create a panel with 1 row and 3 columns

        // Create buttons for navigating to other rooms
        JButton bedroomButton = new JButton("Go East to Bedroom");
        JButton diningHallButton = new JButton("Go West to Dining Hall");
        JButton upstairsButton = new JButton("Go North up the Stairs");

        // ActionListener for the bedroom button
        bedroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.goToRoom("Bedroom"); // Go to the Bedroom room
            }
        });

        // ActionListener for the dining hall button
        diningHallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.goToRoom("DiningHall"); // Go to the DiningHall room
            }
        });

        // ActionListener for the upstairs button
        upstairsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.goToRoom("Upstairs"); // Go to the Upstairs room (To be implemented)
            }
        });

        // Add buttons to the navigation panel
        navigationPanel.add(diningHallButton); // Add button to go West to Dining Hall
        navigationPanel.add(upstairsButton);  // Add button to go North up the Stairs
        navigationPanel.add(bedroomButton);   // Add button to go East to Bedroom

        // Add components to the Hall panel
        add(imageLabel, BorderLayout.CENTER); // Add the image of the hall to the center of the panel
        add(navigationPanel, BorderLayout.SOUTH); // Add navigation buttons to the bottom of the panel
    }
}
