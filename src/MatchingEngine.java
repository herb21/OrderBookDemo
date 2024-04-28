class MatchingEngine {
    LimitOrderBook lob;

    public MatchingEngine(LimitOrderBook lob) {
        this.lob = lob;
    }

    public void processOrder(Order order) {
        if (order.getSide().equals("BUY")) {
            lob.executeTrade(order);
            if (order.quantity > 0) {
                lob.addOrder(order);
            }
        } else {
            lob.executeTrade(order);
            if (order.quantity > 0) {
                lob.addOrder(order);
            }
        }
    }
}
