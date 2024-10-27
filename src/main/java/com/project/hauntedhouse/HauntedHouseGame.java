/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.hauntedhouse;

import javax.swing.*;

public class HauntedHouseGame {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure Swing components are created and updated on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create the main JFrame for the game
                JFrame frame = new JFrame("Educational Adventure Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation to exit the application
                frame.setSize(800, 600); // Set the size of the frame
                frame.setLocationRelativeTo(null); // Center the frame on the screen

                // Create and add the main panel (HauntedHouse) to the frame
                HauntedHouse mainPanel = new HauntedHouse();
                frame.add(mainPanel);

                frame.setVisible(true); // Make the frame visible
            }
        });
    }
}
