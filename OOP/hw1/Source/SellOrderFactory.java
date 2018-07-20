public class SellOrderFactory extends OrderFactory {
    
    SellOrderFactory() {
    }
    
    @Override
    public Order createOrder(Trader t) {
        return new SellOrder(t);
    }
}
