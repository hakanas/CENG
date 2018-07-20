//***************************************************************************************************************************************************

// TODO : imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class RMIServer
{
  //=================================================================================================================================================

  private static String codebase = "http://localhost/"    ;  // An HTTP/FTP/NFS/FS codebase
  private static String name     = "RemoteDatabaseServer" ;  // Name to be registered in RMI Registry

  //=================================================================================================================================================

  public static void main ( String args[] ) throws Exception
  {
    if ( args.length > 0 )  { codebase = args[0] ; }
    if ( args.length > 1 )  { name     = args[1] ; }

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    try {

        RMIImplementation obj  = new RMIImplementation()                                    ;
        RMIInterface      stub = (RMIInterface) UnicastRemoteObject.exportObject( obj , 0 ) ;

        LocateRegistry.getRegistry().rebind( name , stub ) ;
        System.out.println("Server ready...");

    } catch (Exception e) {
        System.err.println("Server exception: " + e.toString());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    JFrame         frame    = new JFrame ( "Server" )                                                                                 ;
    JButton        button   = new JButton( "Exit"   )                                                                                 ;
    ActionListener listener = new ActionListener() { @Override public void actionPerformed ( ActionEvent e ) { System.exit( 0 ) ; } } ;

    button.addActionListener      ( listener             ) ;
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ) ;
    frame.setLocationByPlatform   ( true                 ) ;
    frame.add                     ( button               ) ;
    frame.setSize                 ( 450 , 150            ) ;

    SwingUtilities.invokeLater( new Runnable() { @Override public void run () { frame.setVisible( true ) ; } } ) ;
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
