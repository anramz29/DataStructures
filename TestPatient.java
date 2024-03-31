import javax.swing.*;

public class TestPatient{
    TestPatient(){

        Patient patient1 = new Patient();
        // Prompt for patient data
        int idNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter ID number:"));

        // parse the age of the person
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter age:"));

        // Assuming a separate method to prompt for BloodData (see below for implementation)
        BloodData bd = promptForBloodData();

        // Patient with user data
        Patient patient2 = new Patient(idNumber, age, bd);

        // Patient with user data but default BloodData
        Patient patient3 = new Patient(idNumber, age, new BloodData());
    }
    private static BloodData promptForBloodData() {
        return new BloodData();
    }
}
