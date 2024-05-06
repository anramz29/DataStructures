import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class GameGUI {
    private JFrame frame; // Main frame of the game
    private JLabel questionLabel; // Label to display questions
    private Node root; // Root node of the game tree
    private Node current; // Current node being displayed
    private Node parent; // Parent node of the current node
    private boolean isYes; // Flag to track user's response (yes or no)
    private int questionCount; // Counter to keep track of the number of questions asked

    // Constructor to initialize the game
    public GameGUI() {
        setUpGUI();  // Set up the graphical user interface
        loadTree(); // Load the game tree from a file
    }

    // Method to set up the graphical user interface
    private void setUpGUI() {
        frame = new JFrame("20 Questions Game"); // Create main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation
        frame.setSize(400, 200); // Set frame size
        frame.setLayout(new BorderLayout()); // Set layout

        questionLabel = new JLabel("Think of an object...", SwingConstants.CENTER); // Create label for questions
        JButton yesButton = new JButton("Yes"); // Create Yes button
        JButton noButton = new JButton("No"); // Create No button
        JButton quitButton = new JButton("Quit"); // Create Quit button

        // Add action listeners to the buttons
        yesButton.addActionListener(e -> processAnswer(true));
        noButton.addActionListener(e -> processAnswer(false));
        quitButton.addActionListener(e -> quitGame());

        // Create panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        buttonPanel.add(quitButton);

        // Add components to the frame
        frame.add(questionLabel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true); // Make frame visible

        startGame(); // Start the game
    }

    // Method to start the game
    private void startGame() {
        if (root == null) {
            initializeDefaultTree(); // If tree is empty, initialize with default tree
        }
        current = root; // Set current node to the root of the loaded tree
        parent = null; // Reset parent node
        isYes = false; // Reset answer flag
        questionCount = 0; // Reset question count

        // Check if the root is an answer node
        if (root.yes == null && root.no == null) {
            // Prompt user with default starting question
            questionLabel.setText("Is it an " + root.data + "?");
        } else {
            // Display the first question
            questionLabel.setText(current.data);
        }
    }

    // Method to process user's answer to a question
    private void processAnswer(boolean answer) {
        if (current == null || (current.yes == null && current.no == null)) {
            if (answer) {
                JOptionPane.showMessageDialog(frame, "I guessed it in " + questionCount + " questions!");
                startGame(); // Restart the game
                return;
            } else {
                gatherNewQuestionInfo();
            }
        } else {
            questionCount++; // Increment question count
            parent = current; // Set parent node
            isYes = answer; // Set answer flag
            current = answer ? current.yes : current.no; // Move to next node based on user's answer
            questionLabel.setText("<html>Question #" + questionCount + ": " + current.data + "</html>"); // Display the question
        }
    }

    // Method to gather new question information from the user
    private void gatherNewQuestionInfo() {
        String correctAnswer = JOptionPane.showInputDialog(frame, "I give up. What was it?"); // Prompt user for the correct answer
        String newQuestion = JOptionPane.showInputDialog(frame, "Give me a question that distinguishes " + current.data + " from " + correctAnswer); // Prompt user for a new question
        boolean answerForYourObject = yesTo("Does your object satisfy the question: " + newQuestion + "?"); // Ask if the object satisfies the new question

        // Create new nodes for the new question and the correct answer
        Node newQuestionNode = new Node(newQuestion);
        newQuestionNode.yes = new Node(answerForYourObject ? correctAnswer : current.data);
        newQuestionNode.no = new Node(answerForYourObject ? current.data : correctAnswer);

        // Update the parent node with the new question node
        if (isYes && parent != null) {
            parent.yes = newQuestionNode;
            current = parent.yes; // Update current node to the newly added question
        } else if (parent != null) {
            parent.no = newQuestionNode;
            current = parent.no; // Update current node to the newly added question
        }
        saveTree(); // Save the updated tree to a file
        startGame(); // Continue the game
    }

    // Method to load the game tree from a file
    private void loadTree() {
        try {
            Scanner scanner = new Scanner(new File("treefile")); // Open the file for reading
            root = readTree(scanner); // Read the tree from the file
            scanner.close(); // Close the scanner
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load the tree, initializing with a default tree.");
            initializeDefaultTree(); // If loading fails, initialize with a default tree
        }
    }

    // Method to read the tree from a scanner
    private Node readTree(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.equals("A:")) {
            return new Node(scanner.nextLine().trim());
        } else { // line.equals("Q:")
            Node node = new Node(scanner.nextLine().trim());
            node.yes = readTree(scanner);
            node.no = readTree(scanner);
            return node;
        }
    }

    // Method to save the game tree to a file
    private void saveTree() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("treefile")); // Open the file for writing
            writeTree(writer, root); // Write the tree to the file
            writer.close(); // Close the writer
            JOptionPane.showMessageDialog(frame, "Game data saved.");
        } catch (IOException i) {
            JOptionPane.showMessageDialog(frame, "Failed to save game data.");
            i.printStackTrace();
        }
    }

    // Method to write the tree to a writer
    private void writeTree(PrintWriter writer, Node node) {
        if (node != null) {
            writer.println(node.yes == null && node.no == null ? "A:" : "Q:"); // Write node type (question or answer)
            writer.println(node.data); // Write node data
            writeTree(writer, node.yes); // Recursively write left subtree
            writeTree(writer, node.no); // Recursively write right subtree
        }
    }

    // Method to initialize the game with a default tree
    private void initializeDefaultTree() {
        try {
            Scanner scanner = new Scanner(new File("treefile")); // Change the file name accordingly
            root = readTree(scanner); // Read the default tree from the file
            scanner.close(); // Close the scanner
            JOptionPane.showMessageDialog(frame, "Default tree loaded successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load the default tree, initializing with a basic tree.");
            // If the default tree file is not found, initialize with a basic default tree
            root = new Node("Is it an animal?");
            root.yes = new Node("Cat");
            root.no = new Node("Carrot");
        }
        startGame(); // Start the game
    }

    // Method to ask a question to the user and return true if the answer is yes
    public boolean yesTo(String prompt) {
        int result = JOptionPane.showConfirmDialog(frame, prompt, "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    // Method to handle quitting the game
    private void quitGame() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit without saving?");
        if (confirm == JOptionPane.YES_OPTION) {
            frame.dispose(); // Close the frame
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
