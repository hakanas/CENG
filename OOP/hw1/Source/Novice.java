import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

public class Novice extends ConManDecorator {
    
    Novice(ConMan c) {
        super(c);
        decoratedConMan = c;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.ORANGE);
        g2d.fillRect((int) decoratedConMan.getPositionX() + 10, 
                        (int) decoratedConMan.getPositionY() - 40, 20, 20);
        
        this.decoratedConMan.draw(g2d);
    }
    
    @Override
    public void step(Common common) throws IOException {
        if (decoratedConMan.status != null && decoratedConMan.status.equals("Master")) {
            this.decoratedConMan = new Master(this.decoratedConMan);
        }
        decoratedConMan.step(common);
    }
}
