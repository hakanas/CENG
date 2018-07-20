//***************************************************************************************************************************************************
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;


public class PricePlot extends Entity
{
    public Common               common;
    public Dimension            frame;
    public LinkedList<Integer>  graphPoints;
    
    public PricePlot(Common common, Dimension frame) {
        this.common = common;
        this.frame  = frame;
        graphPoints = new LinkedList<>();
        
        graphPoints.add(randomPrice());
        
        for(int i = 0; i < 998; i++) {
            int previousPrice = (int) (graphPoints.getLast());
            graphPoints.add((Integer) (randomPrice(previousPrice)));
        }

    }

  //=================================================================================================================================================

    private int randomPrice ()
    {
      int value = common.generateRandomInt( 20 , common.plotHeight - 20 ) ;

      return value ;
    }

    //=================================================================================================================================================

    private int randomPrice ( int previousPrice )
    {
      int value = previousPrice + common.generateRandomInt( 0 , 30 ) - 15 ;

      if ( value <= 0                 )  { value = 1                     ; }
      if ( value >= common.plotHeight )  { value = common.plotHeight - 1 ; }

      return value ;
    }

  //=================================================================================================================================================

    @Override
    void draw(Graphics2D g2d) {        
        Color gridColor = new Color(200, 200, 200, 100);
        Color dotColor = new Color(0, 51, 204, 100);
        
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(201, 0 , getWindowWidth() - 202 , 200 );
        
        // vertical lines of the grid
        g2d.setColor(gridColor);
        for(int i = 201; i < getWindowWidth(); i += 25) { 
            g2d.drawLine(i, 0, i, 200);
        }
        
        // horizontal lines of the grid
        for(int i = 0; i < 200; i += 20) { 
            g2d.drawLine(201, i, getWindowWidth(), i); 
        }
        
        g2d.setColor(dotColor);
        g2d.setStroke(new BasicStroke(0.9f));
        for(int i = 1; i < graphPoints.size(); i++) {
            g2d.setColor(dotColor);
            g2d.drawLine(i + 201, getPlotHeight() - (int) graphPoints.get(i - 1), i + 201, getPlotHeight() - graphPoints.get(i));  
        }
        
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("default", Font.BOLD, 18));
        g2d.drawString("Hitcoin Price", 215, 20);
  
    }

    @Override
    void step() {
        int previousPrice = (int) (graphPoints.getLast());
        graphPoints.removeFirst();
        graphPoints.add(randomPrice(previousPrice));
        
        for(int i = 0; i < common.orders.size(); i++) {
            common.orders.get(i).trader.netWorth = common.orders.get(i).trader.coins * graphPoints.getLast();
            common.orders.get(i).trader.netWorth += common.orders.get(i).trader.capital;
            
            if(common.orders.get(i).position.y <= 200) {
                if (common.orders.get(i).color == Color.PINK)
                    common.orders.get(i).trader.coins -= common.orders.get(i).size;
                else
                    common.orders.get(i).trader.coins += common.orders.get(i).size;

                common.orders.get(i).trader.capital = common.orders.get(i).trader.netWorth - (common.orders.get(i).trader.coins * graphPoints.getLast());
                common.orders.remove(common.orders.get(i));
                
            }
        }
    }
    
    public int getWindowWidth() {
        return common.windowWidth;
    }
    
    public int getPlotHeight() {
        return common.plotHeight;
    }

}

//***************************************************************************************************************************************************
