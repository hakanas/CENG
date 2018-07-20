import java.awt.Graphics2D;
import java.io.IOException;

public class BasicConMan extends ConMan {
    
    public BasicConMan(String name, int moneyStolen, String image) throws IOException {
        super(name, moneyStolen, image);
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }

    @Override
    void step() { }
}
