import java.awt.Graphics2D;

public abstract class ConManDecorator extends ConMan {
    public ConMan decoratedConMan;
    
    public ConManDecorator(ConMan c) {
        super(c);
        this.decoratedConMan = c;
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        
    }

    @Override
    void step() {
    }
}
