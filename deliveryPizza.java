public class deliveryPizza extends Pizza {
    private double deliveryFee;
    private String deliveryAddress;

    public deliveryPizza(String[] toppings, String deliveryAddress, int numToppings) {
        super(toppings, numToppings);
        this.deliveryAddress = deliveryAddress;
        this.deliveryFee = calculateDeliveryFee();
    }
    private double calculateDeliveryFee() {
        if (super.getPrice() > 18) {
            return 3;
        } else {
            return 5;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nDelivery Fee: $" + deliveryFee + "\nDelivery Address: " + deliveryAddress;
    }
}
