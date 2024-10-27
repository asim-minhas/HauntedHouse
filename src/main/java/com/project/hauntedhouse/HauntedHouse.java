package com.project.hauntedhouse;

import javax.swing.*;
import java.awt.*;

public class HauntedHouse extends JPanel {
    private CardLayout cardLayout; // Layout manager for switching between start screen and game rooms
    private JPanel mainPanel; // Main panel holding all screens
    private JPanel roomsPanel; // Panel holding all game rooms
    private JPanel startScreenPanel; // Panel for the start screen
    private Player player; // Player object managing player state
    private JTextArea messageLog; // Text area for logging messages

    // Constructor for HauntedHouse class
    public HauntedHouse() {
        cardLayout = new CardLayout(); // Initialize card layout
        mainPanel = new JPanel(cardLayout); // Initialize main panel with card layout
        player = new Player(); // Initialize player

        initializeStartScreen(); // Initialize the start screen panel
        initializeGameRooms(); // Initialize the game rooms panel

        // Set up the message log text area with a scroll pane
        messageLog = new JTextArea(10, 50); // 10 rows, 50 columns
        messageLog.setEditable(false); // Make the text area non-editable
        JScrollPane scrollPane = new JScrollPane(messageLog);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Set the layout for the HauntedHouse panel
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.SOUTH); // Add the scroll pane to the bottom of the panel
        add(mainPanel, BorderLayout.CENTER); // Add the main panel to the center of the panel

        showStartScreen(); // Show the start screen initially
    }

    // Initialize the start screen panel
    private void initializeStartScreen() {
        startScreenPanel = new JPanel(new BorderLayout()); // Set layout for the start screen panel

        JLabel startScreenImageLabel = new JLabel(new ImageIcon("src/main/resources/haunted-house.jpg")); // Load start screen image

        // Create start game button and add action listener to show game
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> showGame());

        startScreenPanel.add(startScreenImageLabel, BorderLayout.CENTER); // Add image to center of start screen
        startScreenPanel.add(startGameButton, BorderLayout.SOUTH); // Add button to bottom of start screen

        mainPanel.add(startScreenPanel, "StartScreen"); // Add start screen panel to main panel
    }

    // Initialize the game rooms panel
    private void initializeGameRooms() {
        roomsPanel = new JPanel(new CardLayout()); // Set layout for game rooms panel

        // Add all game rooms to the rooms panel
        roomsPanel.add(new Hall(this), "Hall");
        roomsPanel.add(new BedRoom(this), "Bedroom");
        roomsPanel.add(new DiningHall(this, player), "DiningHall");
        roomsPanel.add(new Upstairs(this, player), "Upstairs");
        roomsPanel.add(new StoreRoom(this, player), "Store");
        roomsPanel.add(new Attic(this, player), "Attic");

        mainPanel.add(roomsPanel, "Game"); // Add game rooms panel to main panel
    }

    // Method to show a message in the message log
    public void showMessage(String message) {
        messageLog.append(message + "\n"); // Append message to log
        messageLog.setCaretPosition(messageLog.getDocument().getLength()); // Scroll to bottom
    }

    // Method to show the game
    public void showGame() {
        resetGame(); // Reset game state
        cardLayout.show(mainPanel, "Game"); // Switch to game panel
        goToRoom("Hall"); // Start in the hall
    }

    // Method to show the start screen
    public void showStartScreen() {
        cardLayout.show(mainPanel, "StartScreen"); // Switch to start screen panel
        showMessage("Welcome to the Educational Adventure Game!"); // Show welcome message
    }

    // Method to navigate to a specific room
    public void goToRoom(String roomName) {
        CardLayout roomsCardLayout = (CardLayout) roomsPanel.getLayout();
        roomsCardLayout.show(roomsPanel, roomName); // Show specified room panel

        // Provide descriptions for each room when entered for the first time
        switch (roomName) {
            case "Bedroom":
                if (!player.hasEnteredRoom(1)) {
                    showMessage("You entered into eerie bedroom.\nA dimly lit chamber with tattered curtains,\n an old four-poster bed, and a sense of foreboding lurking in the shadows.\n");
                    player.setEnteredRoom(1, true);
                }
                break;
            case "DiningHall":
                if (!player.hasEnteredRoom(2)) {
                    showMessage("You step into the eerie dining hall.\nA vast room with a long, dusty table set for a macabre feast,\n flickering candlelight casting eerie shadows on the walls.\n");
                    player.setEnteredRoom(2, true);
                }
                break;
            case "StoreRoom":
                if (!player.hasEnteredRoom(3)) {
                    showMessage("StoreRoom:\n A cramped space filled with dusty shelves and mysterious crates,\n where the air feels heavy with the weight of forgotten secrets.\n");
                    player.setEnteredRoom(3, true);
                }
                break;
            case "Hall":
                if (!player.hasEnteredRoom(4)) {
                    showMessage("Hall:\n A grand entrance with a creaky staircase leading up,\n surrounded by eerie portraits lining the walls.\n");
                    player.setEnteredRoom(4, true);
                }
                break;
            case "Upstairs":
                if (!player.hasEnteredRoom(5)) {
                    showMessage("Spooky Upstairs:\n A narrow corridor with peeling wallpaper and flickering sconces,\n leading to mysterious doors veiled in shadow, whispering secrets of forgotten tales and unseen terrors.\n");
                    player.setEnteredRoom(5, true);
                }
                break;
            case "Attic":
                if (!player.hasEnteredRoom(0)) {
                    showMessage("Attic:\n A cobweb-covered attic with slanted ceilings and scattered boxes,\n the air thick with the scent of decay and the whispers of restless spirits and mysterious chests.\n");
                    player.setEnteredRoom(0, true);
                }
                break;
            // Add similar checks for other rooms if necessary
        }
    }

    // Getter method for player
    public Player getPlayer() {
        return player;
    }

    // Method to reset the game
    public void resetGame() {
        player.reset(); // Reset player state
        roomsPanel.removeAll(); // Remove all room panels
        initializeGameRooms(); // Re-initialize game rooms
        revalidate(); // Revalidate the layout
        repaint(); // Repaint the panel
    }
}
