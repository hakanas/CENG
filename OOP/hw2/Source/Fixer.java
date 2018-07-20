//***************************************************************************************************************************************************

// TODO: imports

//***************************************************************************************************************************************************

public class Fixer extends Logic
{
  //=================================================================================================================================================

  @Override public void run ( Robot robot )
  {
    // TODO

    /** if there is no broken robots then wait till notified */
    while (Runner.factory.brokenRobots.size() == 0) {
        synchronized (robot) {
            System.out.printf( "Robot %02d : Nothing to fix, waiting!%n" , Runner.get(robot, "serialNo") ) ;
            try {
                robot.wait();
                break;
            }
            catch(InterruptedException ex) {
                
            }
        }
    }

    System.out.printf( "Robot %02d : Fixer woke up, going back to work.%n" , Runner.get(robot, "serialNo") ) ;

    /** find the broken robots */
    try {
        synchronized (Runner.factory.brokenRobots) {
            for (int i = 0; i < Runner.factory.brokenRobots.size(); i++) {

                /** fix the arm */
                if (Runner.get(Runner.factory.brokenRobots.get(i), "arm") == null) {
                    Runner.set(Runner.factory.brokenRobots.get(i), "arm", Factory.createPart( "Arm"));
                }

                /** fix the payload */
                else if (Runner.get(Runner.factory.brokenRobots.get(i), "payload") == null) {

                    Object obj = Runner.get(Runner.factory.brokenRobots.get(i), "logic");
                    Class cls = obj.getClass();
                    String logic = cls.getName();

                    if (logic.equals("Supplier")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "payload", Factory.createPart( "Gripper"));
                    }
                    else if (logic.equals("Builder")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "payload", Factory.createPart( "Welder"));
                    }
                    else if (logic.equals("Inspector")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "payload", Factory.createPart( "Camera"));
                    }
                    else if (logic.equals("Fixer")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "payload", Factory.createPart( "MaintenanceKit"));
                    }
                }

                /** fix the logic chip */
                else if (Runner.get(Runner.factory.brokenRobots.get(i), "logic") == null) {

                    Object obj = Runner.get(Runner.factory.brokenRobots.get(i), "payload");
                    Class cls = obj.getClass();
                    String logic = cls.getName();

                    if (logic.equals("Gripper")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "logic", Factory.createPart( "Supplier"));
                    }
                    else if (logic.equals("Welder")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "logic", Factory.createPart( "Builder"));
                    }
                    else if (logic.equals("Camera")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "logic", Factory.createPart( "Inspector"));
                    }
                    else if (logic.equals("MaintenanceKit")) {
                        Runner.set(Runner.factory.brokenRobots.get(i), "logic", Factory.createPart( "Fixer"));
                    }
                }
                
                /** finally remove the fixed robots from the brokenRobots list */
                try {
                    synchronized (Runner.factory.brokenRobots.get(i)) {
                        Runner.factory.brokenRobots.get(i).notify();
                    }
                    Runner.factory.brokenRobots.remove(Runner.factory.brokenRobots.get(i));
                } 
                catch (IndexOutOfBoundsException e) { /** DoNothing */ }
            }
        }
    }
    catch (IndexOutOfBoundsException ex) { }
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
