import javax.swing.*;
import java.awt.*;

public class TestBloodData extends JFrame {
    private BloodData bloodData;

    private TestPatient testPatient;

    public TestBloodData() {
        bloodData = new BloodData();
        this.setSize(400, 400);
        InitializeComponents();
    }
    public void InitializeComponents() {
        setTitle("Test Blood Data");

        // Main panel using BorderLayout to manage major sections of the layout
        JPanel main = new JPanel(new BorderLayout());

        // Panel for blood type and Rh factor inputs
        JPanel inputsPanel = new JPanel(new GridLayout(2, 2));
        inputsPanel.add(new JLabel("Select Blood Type:"));
        JComboBox<BloodData.BloodType> selectBloodType = new JComboBox<>(BloodData.BloodType.values());
        inputsPanel.add(selectBloodType);
        inputsPanel.add(new JLabel("Select Rh Factor:"));
        // Instead of bloodTypeComboBox and rhFactorComboBox, you might see

        JComboBox<BloodData.RhFactor> selectRhFactor = new JComboBox<>(BloodData.RhFactor.values());
        inputsPanel.add(selectRhFactor);

        // Submit button
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            try {
                BloodData.BloodType selectedBloodType = (BloodData.BloodType) selectBloodType.getSelectedItem();
                BloodData.RhFactor selectedRhFactor = (BloodData.RhFactor) selectRhFactor.getSelectedItem();
                bloodData.setBloodType(selectedBloodType);
                bloodData.setRhFactor(selectedRhFactor);
                JOptionPane.showMessageDialog(this, "Blood Data Updated:\n\n" + bloodData +"\n", "Message", JOptionPane.PLAIN_MESSAGE, null);

            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error Updating Blood "+ ex.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);

            }
        });
        // Adding the inputs panel to the main panel at the top (North)
        main.add(inputsPanel, BorderLayout.NORTH);

        // Adding the submit button to the main panel at the center
        // This will place the button below the inputs panel
        main.add(submit, BorderLayout.CENTER);

        // Setting the main panel as the content pane of the JFrame
        this.setContentPane(main);

        pack(); // Adjusts the frame size to fit the components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        new TestBloodData();
    }
}
