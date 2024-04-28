import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Orderbook {
    private Map<Double, Map<String, List<Order>>> ordersByPrice;

    public Orderbook() {
        ordersByPrice = new HashMap<>();
    }

    public void addOrder(Order order) {
        ordersByPrice.putIfAbsent(order.getPrice(), new HashMap<>());
        Map<String, List<Order>> ordersBySide = ordersByPrice.get(order.getPrice());
        ordersBySide.putIfAbsent(order.getSide(), new LinkedList<>());
        ordersBySide.get(order.getSide()).add(order);
    }

    public void deleteOrder(int orderId) {
        for (Map<String, List<Order>> ordersBySide : ordersByPrice.values()) {
            for (List<Order> orders : ordersBySide.values()) {
                orders.removeIf(order -> order.getId() == orderId);
            }
        }
    }

    public void modifyOrder(int orderId, int newQuantity) {
        for (Map<String, List<Order>> ordersBySide : ordersByPrice.values()) {
            for (List<Order> orders : ordersBySide.values()) {
                for (Order order : orders) {
                    if (order.getId() == orderId) {
                        order.setQuantity(newQuantity);
                        // To maintain FIFO, you may need to reorder the list
                        return;
                    }
                }
            }
        }
    }

    public List<Order> getOrdersAtPriceLevel(double price, String side) {
        if (ordersByPrice.containsKey(price)) {
            Map<String, List<Order>> ordersBySide = ordersByPrice.get(price);
            if (ordersBySide.containsKey(side)) {
                return ordersBySide.get(side);
            }
        }
        return new LinkedList<>(); // Return empty list if no orders found
    }
}
