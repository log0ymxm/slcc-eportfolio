// Fig. 28.25: ResultSetTableModel.java
// A TableModel that supplies ResultSet data to a JTable.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;

// ResultSet rows and columns are counted from 1 and JTable 
// rows and columns are counted from 0. When processing 
// ResultSet rows or columns for use in a JTable, it is 
// necessary to add 1 to the row or column number to manipulate
// the appropriate ResultSet column (i.e., JTable column 0 is 
// ResultSet column 1 and JTable row 0 is ResultSet row 1).
public class ResultSetTableModel extends AbstractTableModel
{

	// keep track of database connection status
	private boolean connectedToDatabase = false;
	private final Connection connection;
	private ResultSetMetaData metaData;
	private int numberOfRows;
	private ResultSet resultSet;

	private final Statement statement;

	// constructor initializes resultSet and obtains its meta data object;
	// determines number of rows
	public ResultSetTableModel(String url, String username, String password,
			String query) throws SQLException, ClassNotFoundException
	{
		// connect to database
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(url, username, password);

		// create Statement to query database
		this.statement = this.connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		// update database connection status
		this.connectedToDatabase = true;

		// set query and execute it
		this.setQuery(query);
	} // end constructor ResultSetTableModel

	// close Statement and Connection
	public void disconnectFromDatabase()
	{
		if (this.connectedToDatabase)
		{
			// close Statement and Connection
			try
			{
				this.resultSet.close();
				this.statement.close();
				this.connection.close();
			} // end try
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			} // end catch
			finally
			// update database connection status
			{
				this.connectedToDatabase = false;
			} // end finally
		} // end if
	} // end method disconnectFromDatabase

	// get class that represents column type
	@Override
	public Class getColumnClass(int column) throws IllegalStateException
	{
		// ensure database connection is available
		if (!this.connectedToDatabase)
		{
			throw new IllegalStateException("Not Connected to Database");
		}

		// determine Java class of column
		try
		{
			String className = this.metaData.getColumnClassName(column + 1);

			// return Class object that represents className
			return Class.forName(className);
		} // end try
		catch (Exception exception)
		{
			exception.printStackTrace();
		} // end catch

		return Object.class; // if problems occur above, assume type Object
	} // end method getColumnClass

	// get number of columns in ResultSet
	@Override
	public int getColumnCount() throws IllegalStateException
	{
		// ensure database connection is available
		if (!this.connectedToDatabase)
		{
			throw new IllegalStateException("Not Connected to Database");
		}

		// determine number of columns
		try
		{
			return this.metaData.getColumnCount();
		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		} // end catch

		return 0; // if problems occur above, return 0 for number of columns
	} // end method getColumnCount

	// get name of a particular column in ResultSet
	@Override
	public String getColumnName(int column) throws IllegalStateException
	{
		// ensure database connection is available
		if (!this.connectedToDatabase)
		{
			throw new IllegalStateException("Not Connected to Database");
		}

		// determine column name
		try
		{
			return this.metaData.getColumnName(column + 1);
		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		} // end catch

		return ""; // if problems, return empty string for column name
	} // end method getColumnName

	// return number of rows in ResultSet
	@Override
	public int getRowCount() throws IllegalStateException
	{
		// ensure database connection is available
		if (!this.connectedToDatabase)
		{
			throw new IllegalStateException("Not Connected to Database");
		}

		return this.numberOfRows;
	} // end method getRowCount

	// obtain value in particular row and column
	@Override
	public Object getValueAt(int row, int column) throws IllegalStateException
	{
		// ensure database connection is available
		if (!this.connectedToDatabase)
		{
			throw new IllegalStateException("Not Connected to Database");
		}

		// obtain a value at specified ResultSet row and column
		try
		{
			this.resultSet.absolute(row + 1);
			return this.resultSet.getObject(column + 1);
		} // end try
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		} // end catch

		return ""; // if problems, return empty string object
	} // end method getValueAt

	// set new database query string
	public void setQuery(String query) throws SQLException,
			IllegalStateException
	{
		// ensure database connection is available
		if (!this.connectedToDatabase)
		{
			throw new IllegalStateException("Not Connected to Database");
		}

		// specify query and execute it
		this.resultSet = this.statement.executeQuery(query);

		// obtain meta data for ResultSet
		this.metaData = this.resultSet.getMetaData();

		// determine number of rows in ResultSet
		this.resultSet.last();                   // move to last row
		this.numberOfRows = this.resultSet.getRow();  // get row number

		// notify JTable that model has changed
		this.fireTableStructureChanged();
	} // end method setQuery
}  // end class ResultSetTableModel

/**************************************************************************
 * (C) Copyright 1992-2012 by Deitel & Associates, Inc. and *
 * Pearson Education, Inc. All Rights Reserved. *
 * *
 * DISCLAIMER: The authors and publisher of this book have used their *
 * best efforts in preparing the book. These efforts include the *
 * development, research, and testing of the theories and programs *
 * to determine their effectiveness. The authors and publisher make *
 * no warranty of any kind, expressed or implied, with regard to these *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or *
 * consequential damages in connection with, or arising out of, the *
 * furnishing, performance, or use of these programs. *
 *************************************************************************/