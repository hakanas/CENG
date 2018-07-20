//***************************************************************************************************************************************************

// TODO: imports

//***************************************************************************************************************************************************

public class Builder extends Logic
{
  //=================================================================================================================================================

  @Override public void run ( Robot robot )
  {
    // TODO

    /** wait until the supplier robots notify */
    while (true) {
        synchronized (robot) {
            System.out.printf( "Robot %02d : Builder cannot build anything, waiting!%n" , Runner.get(robot, "serialNo") ) ;
            try {
                robot.wait();
                break; // time to build some robots
            }
            catch(InterruptedException ex) {
              // Do nothing
            }
        }
    }
      
    System.out.printf( "Robot %02d : Builder woke up, going back to work.%n" , Runner.get(robot, "serialNo"));

                    
    /** send signal to factory to stop production because storage space is full */
    if (Runner.factory.storage.maxCapacity <= Runner.factory.storage.robots.size()) {
        
        Runner.factory.initiateStop();
    }

    /** find all possibilities of fullfilling a production step and attach them to each other */
    synchronized ( Runner.factory.productionLine.parts ) {

      try {
        for (int i = 0; i < Runner.factory.productionLine.parts.size(); i++) {

            Class cls = Runner.factory.productionLine.parts.get(i).getClass(); // class type of part
            String pName = cls.getName();
                        
            if (pName.equals("Base")) {
                
                Object arm = Runner.get(Runner.factory.productionLine.parts.get(i), "arm");
                Object payload = Runner.get(Runner.factory.productionLine.parts.get(i), "payload");
                Object logic = Runner.get(Runner.factory.productionLine.parts.get(i), "logic");
                
                /** if the base has no arm then find an arm to attach to it */
                if (arm == null) {
                    for (int j = 0; j < Runner.factory.productionLine.parts.size(); j++) {
                        if (Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Arm")) {
                            Runner.set(Runner.factory.productionLine.parts.get(i), "arm", Runner.factory.productionLine.parts.get(j));
                            Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(j));
                            System.out.printf( "Robot %02d : Builder attached some parts or relocated a completed robot.%n" , Runner.get(robot, "serialNo") ) ;

                            break;
                        }
                    }
                }

                /** if the base has no payload then find a payload and attach it to the arm */
                if (payload == null && arm != null) {
                    for (int j = 0; j < Runner.factory.productionLine.parts.size(); j++) {
                        if (Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Gripper") | 
                                  Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Welder") |
                                  Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Camera") |
                                  Runner.factory.productionLine.parts.get(j).getClass().getName().equals("MaintenanceKit")) {
                            
                            Runner.set(Runner.factory.productionLine.parts.get(i), "payload", Runner.factory.productionLine.parts.get(j));
                            Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(j));
                            System.out.printf( "Robot %02d : Builder attached some parts or relocated a completed robot.%n" , Runner.get(robot, "serialNo") ) ;

                            break;
                        }
                    }        
                }

                /** if the base has no logic chip then find a compatible logic chip to the payload and attach it to the robot */
                if ((logic == null) && (arm != null) && (payload != null)) {
                    for (int j = 0; j < Runner.factory.productionLine.parts.size(); j++) { 
                        if (payload.getClass().getName().equals("Gripper") && Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Supplier")) {
                            Runner.set(Runner.factory.productionLine.parts.get(i), "logic", Runner.factory.productionLine.parts.get(j));
                            Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(j));
                            System.out.printf( "Robot %02d : Builder attached some parts or relocated a completed robot.%n" , Runner.get(robot, "serialNo") ) ;
                            break;
                        }
                        else if (payload.getClass().getName().equals("Welder") && Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Builder")) {
                            Runner.set(Runner.factory.productionLine.parts.get(i), "logic", Runner.factory.productionLine.parts.get(j));
                            Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(j));
                            System.out.printf( "Robot %02d : Builder attached some parts or relocated a completed robot.%n" , Runner.get(robot, "serialNo") ) ;
                            break;
                        }
                        else if (payload.getClass().getName().equals("Camera") && Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Inspector")) {
                            Runner.set(Runner.factory.productionLine.parts.get(i), "logic", Runner.factory.productionLine.parts.get(j));
                            Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(j));
                            System.out.printf( "Robot %02d : Builder attached some parts or relocated a completed robot.%n" , Runner.get(robot, "serialNo") ) ;
                            break;
                        }
                        else if (payload.getClass().getName().equals("MaintenanceKit") && Runner.factory.productionLine.parts.get(j).getClass().getName().equals("Fixer")) {
                            Runner.set(Runner.factory.productionLine.parts.get(i), "logic", Runner.factory.productionLine.parts.get(j));
                            Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(j));
                            System.out.printf( "Robot %02d : Builder attached some parts or relocated a completed robot.%n" , Runner.get(robot, "serialNo") ) ;
                            break;
                        }
                    }
                }

                /** if the robot is completed ... */
                if ((logic != null) && (arm != null) && (payload != null)) {

                    /** if working robots has enough number then add the completed robot to the storage and remove it from productionLine  */
                    if (Runner.factory.robots.size() >= Runner.factory.maxRobots) {
                        synchronized ( Runner.factory.storage.robots ) {
                            Runner.factory.storage.robots.add((Robot) Runner.factory.productionLine.parts.get(i));
                            Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(i));
                        }
                    }

                    /** the completed robot is added to the working robots or storage. Finally remove it from productionLine  */
                    else {
                        switch (Runner.random.nextInt(2)) {
                            case 0: synchronized ( Runner.factory.robots ) {
                                        Runner.factory.robots.add((Robot) Runner.factory.productionLine.parts.get(i)); 
                                        new Thread( (Robot) Runner.factory.productionLine.parts.get(i) ).start() ;
                                        Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(i));

                                        break;
                                    }
                            default: synchronized ( Runner.factory.storage.robots ) {
                                        Runner.factory.storage.robots.add((Robot) Runner.factory.productionLine.parts.get(i));
                                        Runner.factory.productionLine.parts.remove(Runner.factory.productionLine.parts.get(i));
                                        break;
                                    }
                        }
                    }
                    Runner.storageDisplay.repaint();
                }
            }
        } 
      } 
      catch (IndexOutOfBoundsException e) {
        // Do nothing
      }
    }
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
