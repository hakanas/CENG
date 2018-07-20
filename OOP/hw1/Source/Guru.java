import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Guru extends ConManDecorator {
    public Image image;
    
    Guru(ConMan c) {
        super(c);
        try {
            this.image              = ImageIO.read(new File("..\\Docs\\crown.png"));
        } catch (IOException ex) {
            
        }
        this.image                  = this.image.getScaledInstance(75, 37, Image.SCALE_DEFAULT);
        decoratedConMan             = c;
        decoratedConMan.setDimension(115);
        decoratedConMan.image       = decoratedConMan.image.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, (int) decoratedConMan.getPositionX() + 20,
                            (int) decoratedConMan.getPositionY() - 30, null);
        
        g2d.setColor(Color.ORANGE);
        g2d.fillRect((int) decoratedConMan.getPositionX() + 22,
                            (int) decoratedConMan.getPositionY() - 40, 20, 20);
        
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect((int) decoratedConMan.getPositionX() + 52, 
                            (int) decoratedConMan.getPositionY() - 40, 20, 20);
        
        g2d.setColor(Color.BLUE);
        g2d.fillRect((int) decoratedConMan.getPositionX() + 82 , 
                            (int) decoratedConMan.getPositionY() - 40, 20, 20);
        
        this.decoratedConMan.draw(g2d);
    }
    
    @Override
    public void step(Common common) throws IOException {
        decoratedConMan.step(common);
    }
}
