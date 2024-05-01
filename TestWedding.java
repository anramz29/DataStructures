package org.example.wedding;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TestWedding extends JFrame {
    // initializing J Text filed for all Data inputs
    private JTextField firstNameBrideTF, lastNameBrideTF, firstNameGroomTF, lastNameGroomTF,
            dateTF, locationTF;

    // Fields for the input Button
    private JButton submitButton;
    // Field for the Display Area
    private JTextArea displayArea;


    public TestWedding(){
        setupGUI(); // call the main part that setups the gui
        setTitle("Test Wedding Application"); // set the title
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500); // set size
        setLocationRelativeTo(null); // Center window
        setResizable(false); // Make the window un resizeable
        setVisible(true); // make visible
    }

    public void setupGUI(){
        JPanel panel = new JPanel(); // create new panel to store components

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // use a box layout

        // add bride first name label and text field
        panel.add(new JLabel("Bride's First Name:"));
        firstNameBrideTF = new JTextField(20);
        panel.add(firstNameBrideTF);

        // add bride last name label and text field
        panel.add(new JLabel("Bride's Last Name:"));
        lastNameBrideTF = new JTextField(20);
        panel.add(lastNameBrideTF);

        // add groom first name label and text field
        panel.add(new JLabel("Groom's First Name:"));
        firstNameGroomTF = new JTextField(20);
        panel.add(firstNameGroomTF);

        // add bride last name name label and text field
        panel.add(new JLabel("Groom's Last Name:"));
        lastNameGroomTF = new JTextField(20);
        panel.add(lastNameGroomTF);

        // add the Wedding Data text field
        panel.add(new JLabel("Wedding Date (yyyy-mm-dd): "));
        dateTF = new JTextField(20);
        panel.add(dateTF);

        // add the location component
        panel.add(new JLabel("Location :"));
        locationTF = new JTextField(20);
        panel.add(locationTF);

        // create a submit button
        submitButton = new JButton("Create Wedding!");
        panel.add(submitButton);
        // call upon the action listener and use another method for processing
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAction();
            }
        });

        // initialize the response/display area
        displayArea = new JTextArea(5, 20);
        displayArea.setEditable(false);
        panel.add(displayArea);

        // Add the panel to the frame's content pane
        getContentPane().add(panel);

    }
    private void submitAction(){
        try {
            // Check if any of the input fields are empty
            if (firstNameBrideTF.getText().trim().isEmpty() ||
                    lastNameBrideTF.getText().trim().isEmpty() ||
                    firstNameGroomTF.getText().trim().isEmpty() ||
                    lastNameGroomTF.getText().trim().isEmpty() ||
                    dateTF.getText().trim().isEmpty() ||
                    locationTF.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled out.");
            }
            // create person, objects for the bride and groom
            Person bride = new Person(firstNameBrideTF.getText(), lastNameBrideTF.getText());
            Person groom = new Person(firstNameGroomTF.getText(), lastNameGroomTF.getText());
            // using the person objects create a couple object
            Couple couple = new Couple(bride, groom);

            // Date parsing with validation
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateTF.getText(), formatter); // Validate date format

            String location = locationTF.getText(); // get the location

            // Create the weeding object using previous constructor
            Wedding wedding = new Wedding(couple, date, location);

            // Display area of the wedding Details
            displayArea.setText("Wedding Details:\n");
            displayArea.append("Bride: " + bride.getFirstName() + " " + bride.getLastName() + "\n");
            displayArea.append("Groom: " + groom.getFirstName() + " " + groom.getLastName() + "\n");
            displayArea.append("Date: " + wedding.getWeddingDate() + "\n");
            displayArea.append("Location: " + wedding.getLocation());

        } catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(this, "Incorrect date Format use yyyy-mm-dd", "Date Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            // Show an error message dialog if any fields are empty
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Show an error message dialog if an unexpected exception occurs
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args){
        new TestWedding();
    }
}
