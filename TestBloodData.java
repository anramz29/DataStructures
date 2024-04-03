import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class TestBloodData extends JFrame {

    // Initializing Gui Components
    private final BloodData bloodData;
    private JTextField bloodTypeBox;
    private JTextField rhFactorBox;
    private JButton submitButton;
    private JButton continueButton;
    private JLabel responseLabel, warningLabel;

    TestBloodData() {
        bloodData = new BloodData();
        createGUI();
    }

    public void createGUI() {
        // trying new layout GridBag
        setLayout(new GridBagLayout());
        // create the grid bag constraints
        GridBagConstraints gbc = new GridBagConstraints();
        // give each component it's separate row
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // fill the content horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        // create margins
        gbc.insets = new Insets(3, 3, 3, 3);
        // set the size
        setSize(250, 300);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // use the .values() method to obtain an array of all blood types
        add(new JLabel("Select blood type in " + Arrays.toString(BloodData.BloodType.values())), gbc);
        // create and add first box
        bloodTypeBox = new JTextField(10);
        add(bloodTypeBox, gbc);

        // create and add a rh factor box
        add(new JLabel("Select Rh type (Positive or Negative)"), gbc);
        rhFactorBox = new JTextField(10);
        add(rhFactorBox, gbc);

        // add the submit button
        submitButton = new JButton("Submit");
        add(submitButton, gbc);

        // create a reponse label that at first will be empty
        responseLabel = new JLabel(" ");
        // align the response label to the anchor, and add the label
        gbc.anchor = GridBagConstraints.CENTER;
        add(responseLabel, gbc);

        // add the continue Button
        continueButton = new JButton("Continue");
        // make sure it's initially hidden
        continueButton.setVisible(false);
        // anchor it to the southern part of the frame
        gbc.anchor = GridBagConstraints.SOUTH;
        add(continueButton, gbc);

        // add action listener instances
        submitButton.addActionListener(e -> onSubmit());
        continueButton.addActionListener(e -> onContinue());

        setVisible(true);

        // lastly create a red warning label for errors
        warningLabel = new JLabel();
        warningLabel.setForeground(Color.red);
    }
    // the on Continue button calls the test Patient frame
    private void onContinue(){
        JFrame testPatient = new TestPatient();
        testPatient.setVisible(true);
    }
    // the On submit button displays the data and adds the continue button
    private void onSubmit() {
        // within the method make sure we can update the warning label every iteration
        warningLabel.setText("");
        try {
            // use the value of, methods to assign the text to the blood data
            BloodData.BloodType chosenBloodType = BloodData.BloodType.valueOf(bloodTypeBox.getText().toUpperCase());
            // same
            String rhInput = rhFactorBox.getText().trim().toUpperCase();
            // if else statement, if it's positive, assign the rhfactor to positive and vice versa
            BloodData.RhFactor chosenRhFactor = "POSITIVE".equals(rhInput) ? BloodData.RhFactor.Positive : BloodData.RhFactor.Negative;

            // create an instance of the overloaded BloodData constructor
            BloodData newBloodData = new BloodData(chosenBloodType, chosenRhFactor);

            // Update the responseLabel instead of creating a new panel, call both constructors
            // documentation used: https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
            responseLabel.setText("<html>Default Blood Type: " + bloodData.getBloodType() +
                    "<br>Default Rh Factor: " + bloodData.getRhFactor() +
                    "<br>Selected Blood Type: " + newBloodData.getBloodType() +
                    "<br>Selected Rh Factor: " + newBloodData.getRhFactor() + "</html>");

            continueButton.setVisible(true);
        // catch block although not really used as if there is not input we just use default constructor
        } catch (IllegalArgumentException ex) {
            warningLabel.setText("Error Please Try again");
        }
    }
    public static void main(String[] args) {
        new TestBloodData();
    }
}
