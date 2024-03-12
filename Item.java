
public class Item {
    private double price;
    private String name;

    // These are the set methods
    public Item(double price, String name) {
        this.name = name;
        this.price = price;
    }

    // These are the get methods
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
