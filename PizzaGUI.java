import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PizzaGUI extends JFrame {
    private JCheckBox deliveryCheckbox;
    private JTextField deliveryAddressField;
    private JLabel priceLabel;
    private JButton orderButton;
    private JPanel toppingsPanel;

    public PizzaGUI(){
        // Set up GUI Properties
        setTitle("Pizza Order");
        setSize(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize GUI components
        initializeComponents();

        // Add GUI components to the frame
        addComponentsToGUI();

        setVisible(true); // Make the GUI visible
    }

    // Method to initialize GUI components
    private void initializeComponents(){
        // Array of Toppings
        String[] availableToppings = {
                "Pepperoni", "Mushrooms", "Sausage", "Onions", "Green Peppers",
                "Black Olives", "Pineapple", "Spinach", "Bacon", "Extra Cheese"
        };

        // Toppings Panel Setup
        toppingsPanel = new JPanel();
        toppingsPanel.setLayout(new GridLayout(3, 7));

        for (String topping: availableToppings) {
            JCheckBox checkBox = new JCheckBox(topping);
            toppingsPanel.add(checkBox); // Adding checkboxes to toppingsPanel
        }
        // Adding toppingsPanel to the frame
        add(toppingsPanel, BorderLayout.NORTH);

        // Delivery checkbox
        deliveryCheckbox = new JCheckBox("Delivery?");
        deliveryAddressField = new JTextField(10);
        deliveryAddressField.setEnabled(false); // Initially disabled

        // Price label and Order button
        priceLabel = new JLabel("Price: ");
        orderButton = new JButton("Place Order");
    }

    // Method to add GUI components to the frame
    private void addComponentsToGUI() {
        // Delivery panel
        JPanel deliveryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deliveryPanel.add(deliveryCheckbox);
        deliveryPanel.add(new JLabel("Delivery Address:"));
        deliveryPanel.add(deliveryAddressField);
        add(deliveryPanel, BorderLayout.CENTER);

        // Price and Order Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(priceLabel);
        bottomPanel.add(orderButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action listener for delivery checkbox
        deliveryCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryAddressField.setEnabled(deliveryCheckbox.isSelected());
            }
        });

        // Action listener for order button
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });
    }

    // Method to handle placing an order
    private void placeOrder() {
        // 1. Get selected toppings
        ArrayList<String> selectedToppings = new ArrayList<>();
        for (Component component : toppingsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    selectedToppings.add(checkBox.getText());
                }
            }
        }

        // 2. Check delivery status
        boolean isDelivery = deliveryCheckbox.isSelected();

        // 3. Get delivery address
        String deliveryAddress = isDelivery ? deliveryAddressField.getText() : "";

        // 4. Create Pizza object
        Pizza pizza;
        if (isDelivery) {
            pizza = new deliveryPizza(selectedToppings.toArray(new String[0]), deliveryAddress, selectedToppings.size());
        } else {
            pizza = new Pizza(selectedToppings.toArray(new String[0]), selectedToppings.size());
        }

        // 5. Display order details
        JOptionPane.showMessageDialog(this, "Your Order:\n" + pizza.toString());
    }

    // Main method to start the GUI
    public static void main(String args[]) {
        PizzaGUI pizzaGUI = new PizzaGUI();
    }
}
