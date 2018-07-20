//***************************************************************************************************************************************************

import javax.swing.JPanel            ;
import java.awt.Color                ;
import java.awt.Graphics             ;
import java.awt.Dimension            ;
import java.awt.Graphics2D           ;
import java.awt.geom.AffineTransform ;

//***************************************************************************************************************************************************

public class RobotsDisplay extends JPanel
{
  //=================================================================================================================================================

  private static final long serialVersionUID = -4223244452889676912L ;

  //=================================================================================================================================================

  public RobotsDisplay ()
  {
    setBackground( Color.WHITE ) ;
  }

  //=================================================================================================================================================

  @Override public Dimension getPreferredSize ()
  {
    return new Dimension( Runner.factory.maxRobots * 200 , 200 ) ;
  }

  //=================================================================================================================================================

  @Override public void paintComponent ( Graphics g )
  {
    super.paintComponent( g ) ;

    Graphics2D g2d = (Graphics2D) g ;

    AffineTransform tOriginal = g2d.getTransform() ;

    g2d.setColor( Color.LIGHT_GRAY ) ;

    for ( int i = 200 ; i < Runner.factory.maxRobots * 200 ; i += 200 )  { g2d.drawLine( i , 0 , i , 200 ) ; }

    synchronized ( Runner.factory.robots )
    {
      for ( Robot r : Runner.factory.robots )  { r.draw( g2d ) ;  g2d.translate( 200 , 0 ) ; }
    }

    g2d.setTransform( tOriginal ) ;
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
