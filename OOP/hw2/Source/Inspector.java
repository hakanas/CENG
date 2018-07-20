//***************************************************************************************************************************************************

// TODO: imports

//***************************************************************************************************************************************************

public class Inspector extends Logic
{
  //=================================================================================================================================================

  @Override public void run ( Robot robot )
  {
    // TODO

    /** checks if a robot is broke. if so, puts that robot to the brokenRobots */
    synchronized ( Runner.factory.brokenRobots ) {
        synchronized ( Runner.factory.robots ) {
            for (Robot r : Runner.factory.robots) {
                if ((Boolean) Runner.get(r, "isWaiting") == true) {
                    System.out.printf( "Robot %02d : Detected a broken robot (%02d), adding it to broken robots list.%n" , Runner.get(robot, "serialNo"), Runner.get(r, "serialNo") ) ;
                    System.out.printf( "Robot %02d : Notifying waiting fixers.%n", Runner.get(r, "serialNo"));
                    try{
                        Runner.factory.brokenRobots.add(r);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {}
                }
            }
        }
    }

    /** notify the Fixer robots to fix the broken robots */
    synchronized ( Runner.factory.robots ) { 
        if (Runner.factory.brokenRobots.size() > 0) {
              
            for (Robot r2 : Runner.factory.robots) {
                Object obj = Runner.get(r2, "logic");
                try {
                    Class cls = obj.getClass();
                    if (cls.getName().equals("Fixer") && (Boolean) Runner.get(r2, "isWaiting") == false) {
                        synchronized ( r2 ) {
                            r2.notify();
                        }
                    }
                }
                catch (NullPointerException e) { /* Do nothing */ }
            }
        }
    }
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
