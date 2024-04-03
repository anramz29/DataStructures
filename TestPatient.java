import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestPatient extends JFrame{
    // initialize all components
    private JTextField idTextField, ageTextField, bloodTypeTextField, rhFactorTextField;
    private JLabel infoLabel, warningLabel;
    private Patient defaultPatient, userPatient, customBloodPatient;


    TestPatient(){
        PatientGUI();
        setSize(300, 500);
    }
    private void PatientGUI(){
        // very similar to create gui method in TestBloodData
        setTitle("Patient Information");
        // again using GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);

        // add the id label
        add(new JLabel("ID:"), gbc);
        idTextField = new JTextField();
        add(idTextField, gbc);

        // age label
        add(new JLabel("Age:"), gbc);
        ageTextField = new JTextField();
        add(ageTextField, gbc);

        // blood type label
        add(new JLabel("Blood Type (O, A, B, AB):"), gbc);
        bloodTypeTextField = new JTextField();
        add(bloodTypeTextField, gbc);

        // rh factor label
        add(new JLabel("Rh Factor (Positive, Negative):"), gbc);
        rhFactorTextField = new JTextField();
        add(rhFactorTextField, gbc);

        // submit button
        JButton submitButton = new JButton("Submit");
        add(submitButton, gbc);

        infoLabel = new JLabel(" ");
        // allows for both horizontal and vertical expansion
        gbc.fill = GridBagConstraints.BOTH;
        // allocating more space
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(infoLabel, gbc);

        // same as the info label
        warningLabel = new JLabel();
        warningLabel.setForeground(Color.red);
        add(warningLabel, gbc);

        // create an addAction Listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterData();
            }
        });

    }
    private void enterData() {
        warningLabel.setText(""); // clear warnings for diff iterations

        try {
            // parse all text field boxes
            int id = Integer.parseInt(idTextField.getText().trim());
            int age = Integer.parseInt(ageTextField.getText().trim());
            BloodData.BloodType bloodType = BloodData.BloodType.valueOf(bloodTypeTextField.getText().toUpperCase().trim());
            BloodData.RhFactor rhFactor = rhFactorTextField.getText().trim().equalsIgnoreCase("POSITIVE") ? BloodData.RhFactor.Positive : BloodData.RhFactor.Negative;

            // instantiate the three constructors that we are told to use,
            defaultPatient = new Patient(); // default constructor
            userPatient = new Patient(id, age, new BloodData(bloodType, rhFactor)); // overloaded constructor
            customBloodPatient = new Patient(id, age, new BloodData()); // overloaded constructor with defualt blood data


            // Creativity decided to use my experience with web scraping, and hence knowing html to write this
            // I decided to use format specifiers %d = digit, %s = string, <br>= line breaks, the get methods go into the format and are in order
            // I use get methods to obtain the actual values
            // documentation used: https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
            infoLabel.setText(String.format("<html>Patients Created:<br>Default Patient - ID: %d, Age: %d, " +
                            "Blood: %s%s<br>User Patient - ID: %d, Age: %d, Blood: %s%s<br>Custom " +
                            "Blood Patient - ID: %d, Age: %d, Blood: %s%s</html>",
                    defaultPatient.getIdNumber(), defaultPatient.getAge(), defaultPatient.getBloodData().getBloodType(), defaultPatient.getBloodData().getRhFactor(),
                    userPatient.getIdNumber(), userPatient.getAge(), userPatient.getBloodData().getBloodType(), userPatient.getBloodData().getRhFactor(),
                    customBloodPatient.getIdNumber(), customBloodPatient.getAge(), customBloodPatient.getBloodData().getBloodType(), customBloodPatient.getBloodData().getRhFactor()));
        // catch if ID and age are not integers
        } catch (NumberFormatException ex) {
            warningLabel.setText("ID and Age must be Integers.");
        // catch all other issues
        } catch (IllegalArgumentException ex) {
            warningLabel.setText("Invalid Blood Type or RhFactor.");
        }

        // Ensure updates are shown
        warningLabel.revalidate();
        warningLabel.repaint();
        infoLabel.revalidate();
        infoLabel.repaint();
    }

}


