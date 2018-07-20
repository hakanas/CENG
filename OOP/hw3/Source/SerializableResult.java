//***************************************************************************************************************************************************

// TODO : imports
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class SerializableResult implements Serializable
{
  //=================================================================================================================================================

  public int                    updateReturnValue ;
  public List< String         > columnNames       ;
  public List< List< Object > > rows              ;

  //=================================================================================================================================================

  public SerializableResult ( int updateReturnValue )
  {
    this.updateReturnValue = updateReturnValue ;
    this.columnNames       = null              ;
    this.rows              = null              ;
  }

  //=================================================================================================================================================

  public SerializableResult ( ResultSet resultSet ) throws Exception
  {
    this.updateReturnValue = -1                ;
    this.columnNames       = new ArrayList<>() ;
    this.rows              = new ArrayList<>() ;

    ResultSetMetaData metadata = resultSet.getMetaData();
    
    for(int i = 0; i < metadata.getColumnCount(); i++) {
        
        columnNames.add(metadata.getColumnName(i+1));
    }
    
    for (int i = 0; resultSet.next(); i++) {
        
        rows.add(new ArrayList<>());
        
        for(int j = 0; j < metadata.getColumnCount(); j++) {
            
            rows.get(i).add(resultSet.getString(j+1));
        }
    }
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
