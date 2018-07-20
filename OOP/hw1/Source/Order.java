import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import static java.lang.Math.sqrt;
import java.util.Random;

abstract class Order extends Entity {
    public int      size;
    public int      speed;
    public int      attachedTo;
    public Color    color;
    public Position targetPoint;
    public Trader   trader;
    
    public Order (Trader trader) {
        this.attachedTo  = -1; // for BlackHole state 
        this.trader      = trader;
        this.speed       = new Random().nextInt(8) + 3;
        this.targetPoint = new Position(new Random().nextInt(998) + 201, 200);
        this.position    = new Position((double) (trader.position.getX() + 100), 
                                    (double) (trader.position.getY()));

    }
    
    @Override
    public void draw (Graphics2D g2d) {
        g2d.setFont(new Font("default", Font.BOLD, 12));
        
        drawAbbr(g2d);
        drawCircle(g2d);
        drawOrderSize(g2d);
    }

    void step (Common common) {
        double yVel = targetPoint.y - position.y;
        double xVel = targetPoint.x - position.x;

        double mag = sqrt(xVel * xVel + yVel * yVel);

        xVel = xVel * speed / mag;

        yVel = yVel * speed / mag;
        
        position.x += xVel;
        position.y += yVel;
    }
    
    public void drawAbbr (Graphics2D g2d) {
        g2d.setPaint(this.color);
        g2d.drawString(trader.nameAbbr, position.getX(), position.getY() - 5);
    }
    
    public void drawCircle (Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval(position.getX(), position.getY(), 15, 15);
        g2d.fillOval(position.getX(), position.getY(), 15, 15);
    }
    
    public void drawOrderSize (Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(1));
        g2d.setPaint(Color.BLACK);
        g2d.drawString(String.valueOf(this.size), position.getX() + 5, position.getY() + 12);
    }
}
