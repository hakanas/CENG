public class BuyOrderFactory extends OrderFactory {
    
    BuyOrderFactory() { }

    @Override
    public Order createOrder(Trader t) {
        return new BuyOrder(t);
    }
}
