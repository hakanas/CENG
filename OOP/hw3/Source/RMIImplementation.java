//***************************************************************************************************************************************************

// TODO : imports
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class RMIImplementation implements RMIInterface
{
  //=================================================================================================================================================

  private final String     driver     ;
  private final String     url        ;
  private final String     database   ;
  private final String     username   ;
  private final String     password   ;
  private       Connection connection ;
  private       Statement  statement  ;

  //=================================================================================================================================================

  public RMIImplementation () throws Exception
  {
    this.driver     = "com.mysql.jdbc.Driver"        ;
    this.url        = "jdbc:mysql://localhost:3306/" ;
    this.database   = "Ceng443"                      ;
    this.username   = "root"                         ;
    this.password   = ""                             ;
    this.connection = null                           ;
    this.statement  = null                           ;

    connect() ;
  }

  //=================================================================================================================================================

  public RMIImplementation ( String driver , String url , String database , String username , String password ) throws Exception
  {
    this.driver     = driver                         ;
    this.url        = url                            ;
    this.database   = database                       ;
    this.username   = username                       ;
    this.password   = password                       ;
    this.connection = null                           ;
    this.statement  = null                           ;

    connect() ;
  }

  //=================================================================================================================================================

  private void connect () throws Exception
  {
    Class.forName(driver);

    System.out.println("Connecting to the database...");

    connection = DriverManager.getConnection( url + database , username, password);

    System.out.println("Connected the database successfully...");
  }

  //=================================================================================================================================================

  @Override protected void finalize () throws Throwable
  {
    super.finalize();
  }

  //=================================================================================================================================================

  @Override public SerializableResult processSQL ( String sql , boolean isQuery ) throws RemoteException
  {
    SerializableResult result;
      
      if(isQuery) {
          try {
            System.out.println("Creating statement...");
            PreparedStatement ps = connection.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery(); 
            
            result = new SerializableResult(rs);

            return result;
            
          } catch (SQLException ex) {
              System.err.println("RMIImplementation exception: " + ex.toString());
          } catch (Exception ex) {
              System.err.println("RMIImplementation exception: " + ex.toString());
          }
      }
      
      else {
          try {
              System.out.println("Creating statement...");
              PreparedStatement ps = connection.prepareStatement(sql);
              int n = ps.executeUpdate();

              result = new SerializableResult(n);
              
              return result;
              
          } catch (SQLException ex) {
              System.err.println("RMIImplementation exception: " + ex.toString());
          }
      }

      return null;
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
