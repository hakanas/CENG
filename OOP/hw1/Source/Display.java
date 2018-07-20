import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Display extends JPanel {
    public Common common;

    Display(Common common) {
        this.common = common;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(common.windowWidth, common.windowHeight);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        BufferedImage logo = null;
        String logoImage = "..\\Docs\\Hitcoin.gif";
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        setBackground(Color.WHITE);
        
        try {
            logo = ImageIO.read(new File(logoImage));
        } catch (IOException ex) {
            
        }
        
        Image newImage = logo.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        g2d.drawImage(newImage, 0, 0, null);
        
        for (Trader trader : common.traders) {
            trader.draw(g2d);
        }
        
        try {
            common.plot.draw(g2d);
        } catch (NullPointerException e) { 
        
            }
        
        try {
            try {
                for (Order order : common.orders) {
                    order.draw(g2d);
                }
            } catch (NullPointerException e) {
                
            }
        } catch (ConcurrentModificationException e) {
            
        }
        
        for (ConMan conman : common.conmen) {
            conman.draw(g2d);
        }

        try {
            common.ticker.draw(g2d);
        } catch (NullPointerException e) {
            
        }
    }
}
