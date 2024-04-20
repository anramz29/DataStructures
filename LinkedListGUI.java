import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LinkedListGUI extends JFrame{

    // all of my predefined swing components
    private JTextArea linkedListDisplay;
    private StudentLinkedList studentLinkedList;
    private JTextField nameField;
    private JButton removeAfterButton, copyButton;
    private JPanel inputPanel, subPanel;

    LinkedListGUI(){
        // use super to give the Application a header
        super("Linked List Application");
        setSize(500, 300);
        // instance of a linked list
        studentLinkedList = new StudentLinkedList();
        // call the below methods
        insertNodes();
        initializeComponents();

        // used a lot in the past pretty self explanatory
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void insertNodes(){
        // Using the Insert Nodes Method Given By the Professor
        Student chris = new Student("Chris", 19, "CS", 2025);
        Student john = new Student("John", 20, "DS", 2026);
        Student bill = new Student("Bill", 17, "Math", 2027);
        Student mona = new Student("Mona", 18, "Physics", 2034);
        Student tom = new Student("Tom", 18, "Physics", 2034);
        studentLinkedList.insertNode(chris);
        studentLinkedList.insertNode(john);
        studentLinkedList.insertNode(bill);
        studentLinkedList.insertNode(mona);
        studentLinkedList.insertNode(tom);

    }
    private void initializeComponents(){
        linkedListDisplay = new JTextArea(20, 30); // create a text area to display LL
        linkedListDisplay.setEditable(false);  // text area non-editable
        add(linkedListDisplay, BorderLayout.CENTER);  // Add scrollPane to JFrame
        updateDisplay();  // Initial link list being displayed

        // create panel for inputting components, Flow layout is used to make tighter components
        subPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0 ,0));

        // create a input pannel for all j buttons as well as the sub panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4,1));
        subPanel.add(new JLabel("Student Name :"));
        nameField = new JTextField(15); // Initialize the nameField
        subPanel.add(nameField); // Add the nameField to the subPanel


        removeAfterButton = new JButton("RemoveAfter() method"); // create the Remove After Button
        removeAfterButton.addActionListener(new removeAfterListener()); // attach listner
        subPanel.add(removeAfterButton); // add button

        inputPanel.add(subPanel);

        copyButton = new JButton("copy() Method"); // create copy button
        copyButton.addActionListener(new copyListListener()); // attach listener
        inputPanel.add(copyButton); // ect

        JButton maxButton = new JButton("max() Method"); // ect
        maxButton.addActionListener(new MaxListener());
        inputPanel.add(maxButton);

        // finally add the input panel to application
        add(inputPanel, BorderLayout.SOUTH);
    }
    private class removeAfterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // obtain text
            String name = nameField.getText();

            // use try catch block for no matching name or if it's the last node
            try {

                // use the search node method to obtain the node of the name
                StudentNode node = studentLinkedList.searchNode(name);

                if (node == null) {
                    throw new IllegalArgumentException("Student not found.");
                }

                if (node.getNext() == null) {
                    throw new IllegalArgumentException("Cannot remove after the last node.");
                }

                // implemnt remove after method
                studentLinkedList.removeAfter(node);
                updateDisplay(); // Update the display
                nameField.setText(""); // Clear text

            } catch (IllegalArgumentException ex) {
                // catch illegal argument and display message specified in the throw lines
                JOptionPane.showMessageDialog(LinkedListGUI.this, ex.getMessage());
            }
        }
    }
    private class copyListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (studentLinkedList.getHead() == null) {
                    throw new IllegalArgumentException("The linked list is empty. Cannot copy.");
                }
                // simple here, just calling the copy method
                StudentLinkedList copiedList = studentLinkedList.copy();

                // Build string model of the linked lost
                StringBuilder displayText = new StringBuilder();

                // obtain the linked list head
                StudentNode current = copiedList.getHead();
                // iterate till last node
                while (current != null) {
                    // display student information using the to String method in Student
                    displayText.append(current.getStudent().toString()).append("\n");
                    // iterate to next node
                    current = current.getNext();
                }


                // Display the copied list in a JOptionPane
                JOptionPane.showMessageDialog(LinkedListGUI.this, displayText.toString(), "Copied List", JOptionPane.INFORMATION_MESSAGE);
            }catch (IllegalArgumentException ex){
                // catch illegal argument if there is no linked list
                JOptionPane.showMessageDialog(LinkedListGUI.this, ex.getMessage());
            }
        }
    }
    private class MaxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // call the max function of the head of the first student node in the linked list
            int maxAge = studentLinkedList.max(studentLinkedList.getHead());

            JOptionPane.showMessageDialog(LinkedListGUI.this, "Maximum Age: " + maxAge);
        }
    }
    private void updateDisplay() {
        // clear previous info
        linkedListDisplay.setText("");

        // same structure, as the copy JDialog, iterate through nodes and use the toString method
        StudentNode current = studentLinkedList.getHead();
        while (current != null) {
            Student student = current.getStudent();
            linkedListDisplay.append(student.toString() + "\n");  // Use Student's toString method
            current = current.getNext();
        }
    }
    public static void main(String[] args) {
        new LinkedListGUI();  // Call the GUI
    }
}
