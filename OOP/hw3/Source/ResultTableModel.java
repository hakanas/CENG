//***************************************************************************************************************************************************

// TODO : imports
import javax.swing.table.AbstractTableModel;


//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class ResultTableModel extends AbstractTableModel
{
  //=================================================================================================================================================

  private final SerializableResult serializableResult ;

  //=================================================================================================================================================

  public ResultTableModel ( SerializableResult serializableResult )
  {
    this.serializableResult = serializableResult ;
  }

  //=================================================================================================================================================

  @Override public int getRowCount ()
  {
    if (serializableResult.rows == null)
      return 1;
    else
      return serializableResult.rows.size();
  }

  //=================================================================================================================================================

  @Override public int getColumnCount ()
  {
    if (serializableResult.columnNames == null)
      return 1;
    else
      return serializableResult.columnNames.size();
  }

  //=================================================================================================================================================

  @Override public String getColumnName ( int colIndex )
  {
    if (serializableResult.columnNames == null)
      return "Update Return Value";
    else
      return serializableResult.columnNames.get(colIndex);
  }

  //=================================================================================================================================================

  @Override public Class getColumnClass ( int colIndex )
  {
    if ( ( serializableResult.rows != null ) && ( serializableResult.rows.size() > 0 ) )  { return getValueAt( 0 , colIndex ).getClass() ; }
    if ( colIndex == 0                                                                 )  { return Integer.class                         ; }

    return Object.class ;
  }

  //=================================================================================================================================================

  @Override public boolean isCellEditable ( int rowIndex , int colIndex )
  {
    return false ;
  }

  //=================================================================================================================================================

  @Override public Object getValueAt ( int rowIndex , int colIndex )
  {
    if (serializableResult.rows == null)
      return serializableResult.updateReturnValue;
    else
      return serializableResult.rows.get(rowIndex).get(colIndex);
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************
