public class Pizza {
    private String[] toppings;
    private double price;

    public Pizza(String[] toppings, int numToppings) {
        this.toppings = toppings;
        this.price = 14 + 2 * numToppings;
    }

    @Override
    public String toString() {
        return "Pizza with toppings: " + String.join(", ", toppings) + "\nPrice: $" + price;
    }

    public double getPrice() {
        return price;
    }
}
