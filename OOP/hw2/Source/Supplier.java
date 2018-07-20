//***************************************************************************************************************************************************

// TODO: imports
import java.util.ArrayList;
import java.util.List;

//***************************************************************************************************************************************************

public class Supplier extends Logic
{
  //=================================================================================================================================================

  @Override public void run ( Robot robot )
  {
    // TODO

    List<Part> parts = new ArrayList<>();
    
    /** create a robot part randomly and add it to the ProductionLine.parts*/
    if (Runner.factory.productionLine.maxCapacity > Runner.factory.productionLine.parts.size()) {
        System.out.printf( "Robot %02d : Supplying a random part on production line.%n", Runner.get(robot, "serialNo"));
        
        switch ( Runner.random.nextInt( 16 ) )
        {
          case  0 : case 1 : case 2 : case 3 : parts.add( Factory.createBase(                  ) ) ;  break ;
          case  4 : case 5 : case 6 : case 7 : parts.add( Factory.createPart( "Arm"            ) ) ;  break ;
          case  8 :                            parts.add( Factory.createPart( "Gripper"        ) ) ;  break ;
          case  9 :                            parts.add( Factory.createPart( "Welder"         ) ) ;  break ;
          case 10 :                            parts.add( Factory.createPart( "Camera"         ) ) ;  break ;
          case 11 :                            parts.add( Factory.createPart( "MaintenanceKit" ) ) ;  break ;
          case 12 :                            parts.add( Factory.createPart( "Supplier"       ) ) ;  break ;
          case 13 :                            parts.add( Factory.createPart( "Builder"        ) ) ;  break ;
          case 14 :                            parts.add( Factory.createPart( "Inspector"      ) ) ;  break ;
          case 15 :                            parts.add( Factory.createPart( "Fixer"          ) ) ;  break ;
        }

        System.out.printf( "Robot %02d : Waking up waiting builders.%n", Runner.get(robot, "serialNo"));

        synchronized ( Runner.factory.productionLine.parts ) {
            Runner.factory.productionLine.parts.add(parts.get(0));
        }

    }
    /** if productionLine is full then remove one of them */
    else {
        System.out.printf( "Robot %02d : Production line is full, removing a random part from production line.%n" , Runner.get(robot, "serialNo") );
        
        try {
            synchronized ( Runner.factory.productionLine.parts ) {
                Runner.factory.productionLine.parts.remove(Runner.random.nextInt( 10 ));
            }
        }
        catch (IndexOutOfBoundsException ex) {
          // Do nothing
        }
    }

    /** notify the Builder robots */
    synchronized ( Runner.factory.robots ) {    
        for (int i = 0; i  < Runner.factory.robots.size(); i++) {
            Object obj = Runner.get(Runner.factory.robots.get(i), "logic");

            try {
                Class cls = obj.getClass();

                if (cls.getName().equals("Builder") && (Boolean) Runner.get(Runner.factory.robots.get(i), "isWaiting") == false) {

                    synchronized ( Runner.factory.robots.get(i) ) {
                        Runner.factory.robots.get(i).notify();
                    }

                }
            }
            catch (NullPointerException e) {
                // Do nothing
            }
        }
    }
     
     Runner.productionLineDisplay.repaint();

  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
