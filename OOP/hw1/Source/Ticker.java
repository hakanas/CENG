import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

//***************************************************************************************************************************************************

public class Ticker extends Entity
{
    int counter;
    ArrayList <Integer> arrows;

  //=================================================================================================================================================

  private final String [] bist100 = { "AEFES" , "AFYON" , "AKBNK" , "AKENR" , "AKSA"  , "AKSEN" , "ALARK" , "ALCTL" , "ALGYO" , "ALKIM" ,
                                      "ANACM" , "ANELE" , "ARCLK" , "ASELS" , "AYGAZ" , "BAGFS" , "BANVT" , "BIMAS" , "BIZIM" , "BJKAS" ,
                                      "BRISA" , "CCOLA" , "CEMTS" , "CLEBI" , "CRFSA" , "DEVA"  , "DOAS"  , "DOHOL" , "ECILC" , "EGEEN" ,
                                      "EKGYO" , "ENKAI" , "ERBOS" , "EREGL" , "FENER" , "FROTO" , "GARAN" , "GLYHO" , "GOLTS" , "GOODY" ,
                                      "GOZDE" , "GSDHO" , "GSRAY" , "GUBRF" , "HALKB" , "HLGYO" , "ICBCT" , "IHLAS" , "IPEKE" , "ISCTR" ,
                                      "ISGYO" , "IZMDC" , "KARSN" , "KARTN" , "KCHOL" , "KIPA"  , "KLGYO" , "KONYA" , "KORDS" , "KOZAA" ,
                                      "KOZAL" , "KRDMD" , "MAVI"  , "METRO" , "MGROS" , "NETAS" , "NTTUR" , "ODAS"  , "OTKAR" , "PETKM" ,
                                      "PGSUS" , "PRKME" , "SAHOL" , "SASA"  , "SISE"  , "SKBNK" , "SODA"  , "TATGD" , "TAVHL" , "TCELL" ,
                                      "THYAO" , "TKFEN" , "TKNSA" , "TMSN"  , "TOASO" , "TRCAS" , "TRKCM" , "TSKB"  , "TSPOR" , "TTKOM" ,
                                      "TTRAK" , "TUPRS" , "ULKER" , "VAKBN" , "VESTL" , "VKGYO" , "YATAS" , "YAZIC" , "YKBNK" , "ZOREN" } ;

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  // Coordinates for polygons (up arrow, right arrow, down arrow)

  private final int [] xUp    = {  7 , 14 , 10 , 10 ,  4 ,  4 ,  0 } ;
  private final int [] yUp    = {  0 ,  7 ,  7 , 14 , 14 ,  7 ,  7 } ;

  private final int [] xRight = {  0 ,  7 ,  7 , 14 ,  7 ,  7 ,  0 } ;
  private final int [] yRight = {  4 ,  4 ,  0 ,  7 , 14 , 10 , 10 } ;

  private final int [] xDown  = {  4 , 10 , 10 , 14 ,  7 ,  0 ,  4 } ;
  private final int [] yDown  = {  0 ,  0 ,  7 ,  7 , 14 ,  7 ,  7 } ;

  //=================================================================================================================================================

    public Ticker() {
        this.counter = 0;
        this.arrows = new ArrayList < > ();
        for (int i = 0; i < bist100.length; i++) {
            arrows.add(new Random().nextInt(3));
        }
    }
    
    void randomGenerateArrow() {
        for (int i = 0; i < bist100.length; i++) {
            arrows.add(new Random().nextInt(3));
        }
    }
    
    @Override
    void draw(Graphics2D g2d) {
        Shape shape;
        AffineTransform oldXForm = g2d.getTransform();
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 965 , 1200 , 30 );
        g2d.fillRect(0, 965 , 1200 , 30 );
        
        g2d.setFont(new Font("default", Font.BOLD, 16));
        
        for(int i = 0; i < bist100.length; i++) {
            g2d.setPaint(Color.WHITE);
            int textWidth = g2d.getFontMetrics().stringWidth(bist100[i]);
            g2d.drawString(bist100[i] + "    ", counter + (i * 90), 987);
            
            g2d.translate(counter + (i * 90) + textWidth + 3 , 973);
            
            switch (arrows.get(i)) {
                case 0:
                    g2d.setColor(Color.green);
                    shape =  new Polygon(xUp, yUp, xUp.length);
                    break;
                case 1:
                    g2d.setColor(Color.ORANGE);
                    shape =  new Polygon(xRight, yRight, xRight.length);
                    break;
                default:
                    g2d.setColor(Color.RED);
                    shape =  new Polygon(xDown, yDown, xDown.length);
                    break;
            }
            g2d.fill(shape);
            
            g2d.setTransform(oldXForm);
        }       
}

    @Override
    void step() {
        int tmp = bist100.length * 90;
        if(counter < - tmp) {
            counter = 1200;
            randomGenerateArrow();
        }
        counter--;
    }
}

//***************************************************************************************************************************************************
