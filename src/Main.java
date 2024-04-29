public class Main {
    public static void main(String[] args) {
        // Test cases
        Orderbook orderbook = new Orderbook();
        Order order1 = new Order(1, 10.0, 100, Side.BUY.toString());
        Order order2 = new Order(2, 9.5, 50, Side.SELL.toString());
        orderbook.addOrder(order1);
        orderbook.addOrder(order2);

        System.out.println("Orders at price 10.0, Buy side: " + orderbook.getOrdersAtPriceLevel(10.0, Side.BUY.toString()));

        orderbook.modifyOrder(1, 200);
        System.out.println("Modified order: " + order1.getQuantity());

        orderbook.deleteOrder(2);
        System.out.println("Orders at price 9.5, Sell side: " + orderbook.getOrdersAtPriceLevel(9.5, Side.SELL.toString()));


        LimitOrderBook lob = new LimitOrderBook();
        MatchingEngine matchingEngine = new MatchingEngine(lob);

        // Add some initial orders to the order book
        lob.addOrder(new Order(1, 50, 10, Side.SELL.toString()));
        lob.addOrder(new Order(2, 40, 12,Side.SELL.toString()));
        lob.addOrder(new Order(3, 30, 8, Side.SELL.toString()));

        // New order arrives
        Order newOrder = new Order(4, 55, 9,Side.SELL.toString());
        matchingEngine.processOrder(newOrder);

        // Print resulting order book
        System.out.println("Updated Order Book:");
        System.out.println("Buy Orders:");
        for (Order buyOrder : lob.buyOrders) {
            System.out.println("Order ID: " + buyOrder.getId() + ", Price: " + buyOrder.getPrice() + ", Quantity: " + buyOrder.quantity);
        }
        System.out.println("Sell Orders:");
        for (Order sellOrder : lob.sellOrders) {
            System.out.println("Order ID: " + sellOrder.getId() + ", Price: " + sellOrder.getPrice() + ", Quantity: " + sellOrder.quantity);
        }
    }
}
