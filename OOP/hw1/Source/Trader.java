import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

class Trader extends Entity {
    public String   name;
    public String   nickname;
    public String   nameAbbr;
    public int      coins;
    public int      capital;
    public int      netWorth;
    public int      order;
    public int      time;
    public Image    image;
    
    public Trader(String name, String nickname, String nameAbbr, int coins, int capital, int net, int order, String image) throws IOException {
        super(new Position((double)(order * 200) + ((order + 1) * (35)) , (double)660));
        this.name       = name;
        this.nickname   = nickname;
        this.nameAbbr   = nameAbbr;
        this.coins      = coins;
        this.capital    = capital;
        this.netWorth   = net;
        this.order      = order;
        this.time       = new Random().nextInt(30) + 5;
        this.image      = ImageIO.read(new File(image));
        this.image      = this.image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
    }
    
    public Order createOrder() {
        int nextOrder = (int) (Math.random() * 2);
        OrderFactory order;
        
        if (nextOrder == 0) {
            order = new SellOrderFactory();
        }
        else {
            order = new BuyOrderFactory();
        }
        
        return order.createOrder(this);
    }

    @Override
    void draw(Graphics2D g2d) {
        g2d.setFont(new Font("default", Font.BOLD, 16));
        
        drawPhoto(g2d);
        drawName(g2d);
        drawNickname(g2d);
        drawCoins(g2d);
        drawCapital(g2d);
        drawNet(g2d);
    }

    @Override
    void step() {

    }
    
    void step(Common common) {
        if(time <= 0) {
            common.orders.add(this.createOrder());
            this.time = new Random().nextInt(50) + 3;
        }
        else
            this.time--;
    }
    
    public void drawPhoto(Graphics2D g2d) {
        g2d.setClip(new Ellipse2D.Double((order * 200) + ((order + 1) * (35)), 660, 200, 200));
        g2d.drawImage(image, (order * 200) + ((order + 1) * (35)) , 660, null);
        g2d.setClip(0, 0, 1200, 1000);
    }
    
    public void drawName(Graphics2D g2d) {
        int nameWidth = g2d.getFontMetrics().stringWidth(this.name);
        g2d.setPaint(Color.BLACK);
        g2d.drawString(this.name, (order * 200) + ((35) * (order + 1)) + makeCenter(nameWidth), 880);
    }
    
    public void drawNickname(Graphics2D g2d) {
        int nameWidth = g2d.getFontMetrics().stringWidth(this.nickname + " '" + " '");
        g2d.setPaint(Color.MAGENTA);
        g2d.drawString("' " + this.nickname + " '", (order * 200) + ((35) * (order + 1)) + makeCenter(nameWidth), 900);
    }
    
    public void drawCoins(Graphics2D g2d) {
        int nameWidth = g2d.getFontMetrics().stringWidth(this.coins +  " coins");
        g2d.setPaint(Color.BLUE);
        g2d.drawString(this.coins + " coins", (order * 200) + ((35) * (order + 1)) + makeCenter(nameWidth), 920);
    }
    
    public void drawCapital(Graphics2D g2d) {
        int nameWidth = g2d.getFontMetrics().stringWidth(this.capital +  " cash");
        g2d.setPaint(Color.GREEN);
        g2d.drawString(this.capital + " cash", (order * 200) + ((35) * (order + 1)) + makeCenter(nameWidth), 940);
    }
    
    public void drawNet(Graphics2D g2d) {
        int nameWidth = g2d.getFontMetrics().stringWidth(this.netWorth +  "Net: ");
        g2d.setPaint(Color.BLACK);
        g2d.drawString("Net: " + this.netWorth, (order * 200) + ((35) * (order + 1)) + makeCenter(nameWidth), 960);
    }
    
    public int makeCenter(int textWidth) {
        return (200 - textWidth) / 2;
    }
}
