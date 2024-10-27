package com.project.hauntedhouse;

/**
 * The Player class represents the player character in the game.
 * It keeps track of the player's current room, inventory, and progress.
 */
public class Player {
    private String currentRoom; // The current room of the player
    private boolean hasKey; // Flag indicating whether the player has a key
    private boolean hasNote; // Flag indicating whether the player has a note
    private boolean hasFork; // Flag indicating whether the player has a fork
    private boolean hasReadRiddle; // Flag indicating whether the player has read the riddle
    private boolean[] hasEnteredRoom; // Array to track whether player has entered each room

    /**
     * Constructor to initialize a new player.
     * It resets the player's attributes and initializes the array for tracking entered rooms.
     */
    public Player() {
        reset(); // Reset player attributes
        this.hasEnteredRoom = new boolean[6]; // Initialize array for 6 rooms (including hall)
    }

    /**
     * Resets the player's attributes to their default values.
     */
    public void reset() {
        this.currentRoom = "Hall"; // Start in the hall
        this.hasKey = false; // Player doesn't have a key initially
        this.hasNote = false; // Player doesn't have a note initially
        this.hasEnteredRoom = new boolean[6]; // Initialize array for 6 rooms (including hall)
    }

    /**
     * Get the current room of the player.
     * @return The current room of the player.
     */
    public String getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Set the current room of the player.
     * @param currentRoom The current room to set.
     */
    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Check if the player has a key.
     * @return true if the player has a key, false otherwise.
     */
    public boolean hasKey() {
        return hasKey;
    }

    /**
     * Set whether the player has a key.
     * @param hasKey true if the player has a key, false otherwise.
     */
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    /**
     * Check if the player has a note.
     * @return true if the player has a note, false otherwise.
     */
    public boolean hasNote() {
        return hasNote;
    }

    /**
     * Set whether the player has a fork.
     * @param hasFork true if the player has a fork, false otherwise.
     */
    public void setHasFork(boolean hasFork) {
        this.hasFork = hasFork;
    }

    /**
     * Check if the player has a fork.
     * @return true if the player has a fork, false otherwise.
     */
    public boolean hasFork() {
        return hasFork;
    }

    /**
     * Check if the player has read the riddle.
     * @return true if the player has read the riddle, false otherwise.
     */
    public boolean hasReadRiddle() {
        return hasReadRiddle;
    }

    /**
     * Set whether the player has read the riddle.
     * @param hasReadRiddle true if the player has read the riddle, false otherwise.
     */
    public void setHasReadRiddle(boolean hasReadRiddle) {
        this.hasReadRiddle = hasReadRiddle;
    }

    /**
     * Add an item to the player's inventory.
     * @param item The item to add.
     */
    public void addItem(String item) {
        if (item.equals("key")) {
            this.hasKey = true;
        } else if (item.equals("note")) {
            this.hasNote = true;
        } else if (item.equals("fork")) {
            this.hasFork = true;
        }
    }

    /**
     * Check if the player has entered a specific room.
     * @param roomIndex The index of the room to check.
     * @return true if the player has entered the room, false otherwise.
     */
    public boolean hasEnteredRoom(int roomIndex) {
        return hasEnteredRoom[roomIndex];
    }

    /**
     * Set whether the player has entered a specific room.
     * @param roomIndex The index of the room.
     * @param value true if the player has entered the room, false otherwise.
     */
    public void setEnteredRoom(int roomIndex, boolean value) {
        hasEnteredRoom[roomIndex] = value;
    }
}
