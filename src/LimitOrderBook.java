import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class LimitOrderBook {
    List<Order> buyOrders;
    List<Order> sellOrders;

    public LimitOrderBook() {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (order.getSide().equals("BUY")) {
            buyOrders.add(order);
            Collections.sort(buyOrders, Comparator.comparingDouble(o -> -o.getPrice())); // Sort descending by price
        } else {
            sellOrders.add(order);
            Collections.sort(sellOrders, Comparator.comparingDouble(o -> o.getPrice())); // Sort ascending by price
        }
    }

    public void executeTrade(Order order) {
        List<Order> matchingOrders;
        List<Order> oppositeSideOrders;
        if (order.getSide().equals("BUY")) {
            matchingOrders = sellOrders;
            oppositeSideOrders = buyOrders;
        } else {
            matchingOrders = buyOrders;
            oppositeSideOrders = sellOrders;
        }

        for (Order matchingOrder : matchingOrders) {
            if (matchingOrder.getPrice() <= order.getPrice() && matchingOrder.getQuantity() > 0) {
                int tradeQuantity = Math.min(matchingOrder.getQuantity(), order.getQuantity());
                matchingOrder.quantity -= tradeQuantity;
                order.quantity -= tradeQuantity;
                System.out.println("Trade executed: " + tradeQuantity + " at price " + matchingOrder.getPrice());
                if (matchingOrder.quantity == 0) {
                    matchingOrders.remove(matchingOrder);
                }
                if (order.quantity == 0) {
                    break;
                }
            }
        }
    }
}
