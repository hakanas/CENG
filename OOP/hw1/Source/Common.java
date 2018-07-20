import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import java.util.concurrent.ThreadLocalRandom;

public class Common {
    public int          windowWidth;
    public int          windowHeight;
    public PricePlot    plot;
    public Ticker       ticker;
    public List<Trader> traders;
    public List<ConMan> conmen;
    public List<Order>  orders;
    public int          plotHeight;

    public Common(int windowWidth, int windowHeight, int plotHeight, PricePlot plot, Ticker ticker, List<Trader> traders, List<ConMan> conmen, List<Order> orders) {
        this.windowWidth    = windowWidth;
        this.windowHeight   = windowHeight;
        this.plotHeight     = plotHeight;
        this.plot           = plot;
        this.ticker         = ticker;
        this.traders        = traders;
        this.conmen         = conmen;
        this.orders         = orders;
    }

    void stepAllEntities(JFrame window) throws IOException {
        
        this.plot.step();
        this.ticker.step();

        this.traders.forEach((trader) -> {
            trader.step(this);
        });
        
        for (int i = 0; i < this.conmen.size(); i++) {
            
            if (this.conmen.get(i).status != null && this.conmen.get(i).status.equals("Novice")) {
                this.conmen.set(i, new Novice(this.conmen.get(i)));
            }
            
            this.conmen.get(i).step(this);
        }

        this.orders.forEach((order) -> {
            order.step(this);
        });
        
        window.repaint();
        
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    int generateRandomInt(int i, int j) {
        int randomNum = ThreadLocalRandom.current().nextInt(i, j + 1);
        return randomNum;
    }
}
