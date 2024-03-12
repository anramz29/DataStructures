import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ItemOrder> items;

    // create the backbone of the function creating a shopping cart when an item is added
    public ShoppingCart(){
        items = new ArrayList<>();
    }

    // used in the action listener for adding items
    public void addItemOrder(ItemOrder order){
        items.add(order);
    }

    // using in the action listener for removing items
    public void removeItemOrder(ItemOrder order){
        items.remove(order);
    }

    // this is also used in the update total price method
    public double calculateTotalPrice(){
        // initialize the total price as zero
        double total = 0.0;
        // use a for loop and the += functionality
        for (ItemOrder order : items) {
            total += order.calculatePrice();
        }
        return total;
    }
}
