import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class SellOrder extends Order {

    SellOrder(Trader trader) {
        super(trader);
        this.size  = new Random().nextInt(5) + 1;
        this.color = Color.PINK;
        
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }

    @Override
    void step() {
    }
    
}
