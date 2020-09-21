

/*
 *  ============================================================================================
 *  Book Searching program
 *  ============================================================================================
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class bs extends JFrame {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String USER = "sshi145";
	static final String PASS = "ssf0531";
	static final String URL = "jdbc:mysql://studdb-mysql.fos.auckland.ac.nz:3306/stu_sshi145_COMPSCI_280_C_S2_2017";

	// declare and create GUI components
	private JTextField titleTextField;
	private JButton searchButton;
	private JTable bookTable;
	private BookTableModel bookTableModel=new BookTableModel(JDBC_DRIVER,URL,USER,PASS);
	// complete this

	public bs() {
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		JLabel label = new JLabel("Enter title:");
		titleTextField = new JTextField(20);
		searchButton = new JButton("Search");

		titleTextField.addActionListener(new ActionListener() {
			// This method is invoked when the user hits ENTER in the field
			public void actionPerformed(ActionEvent e) {
				bookTableModel.populateTable(titleTextField.getText());
				bookTableModel.fireTableDataChanged();
			}
		});
		searchButton.addActionListener(new ActionListener() {
			// This method is invoked when the user hits Search button
			public void actionPerformed(ActionEvent e) {
				// complete this				
				bookTableModel.populateTable(titleTextField.getText());				
				bookTableModel.fireTableDataChanged();
			}
		});
		topPanel.add(label);
		topPanel.add(titleTextField);
		topPanel.add(searchButton);

		pane.add(topPanel, BorderLayout.PAGE_START);

		bookTable = new JTable(bookTableModel);
		
		JScrollPane scrollPane = new JScrollPane(bookTable);
		scrollPane.setViewportView(bookTable);
		pane.add(scrollPane, BorderLayout.CENTER);
		JTextArea bookInfo = new JTextArea(10,40);
		bookInfo.setEditable(false);
		pane.add(bookInfo, BorderLayout.PAGE_END);

		bookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				try {
				int selectedRow = bookTable.getSelectedRow();
				bookInfo.setText(bookTableModel.getCurrentBook(selectedRow).toString());
				}catch(Exception e) {
					titleTextField.setText("");
				}
				
			}
		});
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);		
		// Create and set up the window.
		bs frame = new bs();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Display the window.
		frame.setPreferredSize(new Dimension(800, 550));
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}