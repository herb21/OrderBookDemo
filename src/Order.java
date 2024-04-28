class Order {
    private int id;
    private double price;
    int quantity;
    private Side side;

    public Order(int id, double price, int quantity, String side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.side = Side.valueOf(side);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return String.valueOf(side);
    }

    public void setSide(Side side) {
        this.side = side;
    }
}