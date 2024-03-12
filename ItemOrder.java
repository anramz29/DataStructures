public class ItemOrder {
    private Item item;
    private int quantity;

    // Constructor!! as seen in class
    public ItemOrder(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
    // calculates price for a single item, takes into discounts into account
    public double calculatePrice() {

        double totalPrice = item.getPrice() * quantity;

        // Apply 3 for 2 discount if eligible
        if (quantity >= 3) {
            // checks for the number of possible discounted prices
            int discountSets = quantity / 3;
            // calculates the discounted price
            double discountedPrice = item.getPrice() * 2 * discountSets;
            // gets the remainder
            int remainder = quantity % 3;
            // final calculation
            totalPrice = discountedPrice + remainder * item.getPrice();
        }
        return totalPrice;
    }
    // customized to return a string in the formatted manner for the shopping cart
    @Override
    public String toString() {
        return item.getName() + " - Qty: " + quantity + ", Price: $" + String.format("%.2f", item.getPrice());
    }
}
