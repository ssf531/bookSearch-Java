

/*
 *  ============================================================================================
 *  BookTableModel.java : A table model
 *  ============================================================================================
 */
import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class BookTableModel extends AbstractTableModel {
	private ArrayList<Book> bookList;
	private int rows, cols = 3;
	private String[] columnName;
	private Connection conn = null;

	public BookTableModel(String driver, String url, String username, String password) {
		columnName = new String[] { "ID", "Title", "Author" };
		System.out.println("Connecting...");
		bookList = new ArrayList<Book>();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			populateTable("");
			if (conn != null)
				System.out.println("...MySQL Server connected.");
			else
				System.out.println("Failed to make connection!");
		} catch (ClassNotFoundException exp) {
			System.out.println(driver + " Not found");
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
	}

	/**
	 * Returns the value held at a particular point in the table using the
	 * ArrayList<Book> row = the element in the ArrayList col = the particular
	 * property of the book
	 */
	public Object getValueAt(int row, int col) {
		if (col == 0) 
			return bookList.get(row).getID(); 
		else if (col == 1)
			return bookList.get(row).getTitle();
		else
			return bookList.get(row).getAuthor();
	}

	/**
	 * populate the table by executing a sql statement
	 */
	public void populateTable(String searchTitle) {
		Statement statement = null;
		String sql = null;
		ResultSet rs = null;
		bookList = new ArrayList<Book>();
		try {
			statement = conn.createStatement();
			sql = "select * from BOOKS where Title like '%" + searchTitle + "%'";
			rs = statement.executeQuery(sql);
			
			while (rs.next())
				bookList.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
			rows=bookList.size();
			fireTableChanged(null); // Tell the listeners a new table has arrived.
		} catch (SQLException exp) {
			System.out.println("Error in : " + sql);
			exp.printStackTrace();
		}
	}

	/**
	 * return a book's details by executing a sql statement
	 */
	public Book getCurrentBook(int row) {
		Statement statement = null;
		String sql = null;
		ResultSet rs = null;
		Book bk = bookList.get(row);
		int bookID = bk.getID();
		try {
			statement = conn.createStatement();
			sql = "select * from BOOKS where BOOK_ID = " + bookID;
			rs = statement.executeQuery(sql);
			while (rs.next())
			bookList.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));		

		} catch (SQLException exp) {
			System.out.println("Error in : " + sql);
			exp.printStackTrace();
		}
		return bookList.get(bookList.size()-1);
	
	}

	/**
	 * Returns number of rows in table
	 */
	public int getRowCount() {
		return rows;
	}

	/**
	 * Returns number of columns in table
	 */
	public int getColumnCount() {
		return cols;
	}

	/**
	 * Returns the title of a particular column
	 */
	public String getColumnName(int col) {
		return columnName[col];
	}

	/**
	 * This indicates to the table object whether the specified cell is editable. by
	 * default the key column is not editable. This could be changed to allow user
	 * to specify this..
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}