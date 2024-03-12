import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class ShoppingCartGUI extends JFrame {

    //Initialize the Shopping Cart
    private ShoppingCart cart;
    //Initialize the Default List Model, allows for dynamic updates within the GUI
    private DefaultListModel<ItemOrder> listModel;

    // Display's the cart's content to the user
    private JList<ItemOrder> itemList;

    // initialize the total price label
    private JLabel totalPriceLabel;

    // just like the JLabel here we initialize the box to put in the item name
    private JTextField itemNameField;

    // Here im using a new type of GUI object called a JSpinner, this like a mix between, a text field a Boxes selector
    private JSpinner itemQuantitySpinner;

    // Last JTextField to input the price
    private JTextField itemPriceField;

    public ShoppingCartGUI() {

        // invoking the constructor from the superclass, as we learned in class
        super("Shopping Cart");

        // call a new shopping cart instance
        cart = new ShoppingCart();

        // call a new list model, and itemList instance
        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);

        // call the initialize components method specified lower
        initializeComponents();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This was a new method called pack, and allow for the window to be sized neatly
        //https://stackoverflow.com/questions/22982295/what-does-pack-do
        pack();

        setVisible(true);
    }

    private void initializeComponents() {
        // Creating a new JPanel
        JPanel itemInputPanel = new JPanel();

        // Creates a border around the input panel
        itemInputPanel.setBorder(new TitledBorder("Add Item"));

        // add the Name JTextField
        itemNameField = new JTextField(10);


        // Initialize the JSpinner for quantity with a SpinnerNumberModel, in an actual website this would extend to the quantity of items in stock
        itemQuantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        // add the price box
        itemPriceField = new JTextField(5);

        // new cart button instance
        JButton addItemButton = new JButton("Add to Cart");

        // add an action listener method to the cart
        addItemButton.addActionListener(new AddItemListener());

        // add the name label for it's corresponding textbox
        itemInputPanel.add(new JLabel("Name:"));
        // add the textbox
        itemInputPanel.add(itemNameField);

        // add quantity label
        itemInputPanel.add(new JLabel("Quantity:"));
        // add quantity Spinner
        itemInputPanel.add(itemQuantitySpinner);

        // same as above
        itemInputPanel.add(new JLabel("Price:"));
        itemInputPanel.add(itemPriceField);

        // add the cart button
        itemInputPanel.add(addItemButton);

        // create a new instance of a cart Panel
        JPanel cartPanel = new JPanel();

        // Set a new border are for the cart display panel
        cartPanel.setBorder(new TitledBorder("Cart Contents"));

        // This is also new method only allows for items to be selected if you want to remove it
        //https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/ListSelectionModel.html#:~:text=ListSelectionModel.,be%20selected%20at%20a%20time.
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // create a new JScrollPane with the itemList
        JScrollPane listScrollPane = new JScrollPane(itemList);

        // create a bottom that removes items and attach the item listener method to it
        JButton removeItemButton = new JButton("Remove Item");

        // connect the remove Item Listener method to the remove Item Button
        removeItemButton.addActionListener(new RemoveItemListener());

        // add the scroll pane to the cart panel
        cartPanel.add(listScrollPane);

        // add the remove item to the panel
        cartPanel.add(removeItemButton);

        // create a new instance of the last JPanel for choosing the price
        JPanel totalPanel = new JPanel();

        // set the border to make the GUI look better
        totalPanel.setBorder(new TitledBorder("Total"));

        // create a new label to dynamically show the price
        totalPriceLabel = new JLabel("Total: $0.00");

        // lastly we will add the total panel
        totalPanel.add(totalPriceLabel);

        // Add panels to the main GUI layout and specify there location
        add(itemInputPanel, BorderLayout.NORTH);
        add(cartPanel, BorderLayout.CENTER);
        add(totalPanel, BorderLayout.SOUTH);
    }

    // this will connect to the calculateTotalPrice() method in the Shopping Cart Class
    private void updateTotalPrice() {
        totalPriceLabel.setText("Total: $" + String.format("%.2f", cart.calculateTotalPrice()));
    }

    // Inner classes for event listeners
    class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                String name = itemNameField.getText(); // Get the string for the item that's been inputted by the text

                // obtain the integer from the spinner and parse, ensure to cast expression to (int)
                int quantity = (int) itemQuantitySpinner.getValue();

                // exact same thing but this time using double and a JText box for the price of the item
                double price  = Double.parseDouble(itemPriceField.getText());

                // call the item class so that we can use are get and set methods for confidential information
                Item newItem = new Item(price, name);

                // Same as above but now for our item order class
                ItemOrder newOrder = new ItemOrder(newItem, quantity);
                // add different items to the cart
                cart.addItemOrder(newOrder);
                // this is where the list model comes in handy allowing us to dynamic display the list in the scroll pane
                listModel.addElement(newOrder);
                // lastly call the update total price method to dynamically adjust the total price label
                updateTotalPrice();

                // Very Important for Functionally Reset the input fields after adding an item
                itemNameField.setText("");
                itemQuantitySpinner.setValue(1);
                itemPriceField.setText("");

                // good use of the catch block that allows an error message to pop up
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ShoppingCartGUI.this, "Please enter valid numbers for price and quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    class RemoveItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = itemList.getSelectedIndex();
            // this is crucial, as it only allow the remove function to work if there are items in the cart, using -1 instead of 0 because list indices start at zero
            if (selectedIndex != -1) {
                // extremely similar to the add item just in reverse, no need for extensive comments
                ItemOrder orderToRemove = listModel.get(selectedIndex);
                cart.removeItemOrder(orderToRemove);
                listModel.removeElementAt(selectedIndex);
                updateTotalPrice();
            }
        }
    }

    // call the main function for usage
    public static void main(String[] args) {
        new ShoppingCartGUI();
    }
}
