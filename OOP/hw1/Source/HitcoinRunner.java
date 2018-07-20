import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class HitcoinRunner {

    public static JFrame window;
    public static Display display;
    public static Common common;
    
    public static void main(String[] args) throws IOException {
        Dimension screen = new Dimension(1200, 1000);
        
        String trader1 = "..\\Docs\\BillGates.gif";
        String trader2 = "..\\Docs\\WarrenBuffet.gif";
        String trader3 = "..\\Docs\\SelimTemizer.gif";
        String trader4 = "..\\Docs\\NickLeeson.gif";
        String trader5 = "..\\Docs\\GeorgeSoros.gif";
        
        String conMan1 = "..\\Docs\\BankerYalcin.gif";
        String conMan2 = "..\\Docs\\SulunOsman.gif";
        String conMan3 = "..\\Docs\\TosunMehmet.gif";
        String conMan4 = "..\\Docs\\SelcukParsadan.gif";
        
        PricePlot plot = null;
        Ticker ticker  = new Ticker();
        
        List<Trader> traders = new ArrayList<Trader>() {{
            try {
                add(new Trader("Bill Gates", "Hell Gates", "BL", 471, 50358, 50829, 0, trader1));
                add(new Trader("Warren Buffet", "Warum Buffet", "WB", 488, 46960, 47488, 1, trader2));
                add(new Trader("Selim Temizer", "Trade King", "ST", 497, 46130, 46627, 2, trader3));
                add(new Trader("Nick Leeson", "Rogue Trader", "RT", 484, 48635, 49119, 3, trader4));
                add(new Trader("George Soros", "Doom Bringer", "GS", 487, 49343, 49830, 4, trader5));
            } catch (IOException ex) {
                
            }
        }};

        List<ConMan> conmen = new ArrayList<ConMan>() {{ 
            try {
                add(new BasicConMan("Yalçın", 0, conMan1));
                add(new BasicConMan("Sülün", 0, conMan2));
                add(new BasicConMan("Tosun", 0, conMan3));
                add(new BasicConMan("Parsadan", 0, conMan4));
            } catch (IOException ex) {
                
            }
        }};
        
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < traders.size(); i++) {
            orders.add(traders.get(i).createOrder());
        }
          
        common = new Common(screen.width, screen.height, 200, plot, ticker, traders, conmen, orders);
        
        plot = new PricePlot(common, screen);
        
        common = new Common(screen.width, screen.height, 200, plot, ticker, traders, conmen, orders);
        
        display = new Display(common);
        
        setFrame();
        
        while(true)
            common.stepAllEntities(window);

    }
    
    private static void setFrame() {
        window = new JFrame("CENG 443_HW1 - HitCoin Business by Hakan Aslan");
        window.getContentPane().add(display);
        window.setSize(common.windowWidth , common.windowHeight);
        Image icon = Toolkit.getDefaultToolkit().getImage("..\\Docs\\Hitcoin.gif");  
        icon = icon.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        window.setIconImage(icon); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
