package personal_finances_management_system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.Button;
import javax.swing.JScrollPane;
import java.awt.Label;
import java.awt.Choice;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserHomeDesign {

	private JFrame frame;
	private JTable table;
	private JTextField title_field;
	private JTextField amount_field;
	private JTextField txtTransactionid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserHomeDesign window = new UserHomeDesign();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserHomeDesign() {
		initialize();
		Connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/finance_manager", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void table_load()
	   {
	     try
	     {
	    pst = con.prepareStatement("select * from transactions");
	    rs = pst.executeQuery();
//	    table.setModel(DbUtils.resultSetToTableModel(rs));
	     }
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(26, 66, 116));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(73, 146, 186));
		panel.setBounds(0, 0, 450, 55);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGE TRANSACTIONS ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(84, 17, 273, 16);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(369, 13, 75, 29);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(183, 68, 248, 155);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 73, 33, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		title_field = new JTextField();
		title_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		title_field.setForeground(new Color(192, 192, 192));
		title_field.setText("Dinner");
		title_field.setBounds(55, 67, 106, 26);
		frame.getContentPane().add(title_field);
		title_field.setColumns(10);
		
		
		String [] items = { "Food", "Health Care", "Rent", "Entertainment" };
		JComboBox category_field = new JComboBox(items);
		category_field.setBounds(54, 101, 112, 23);
		frame.getContentPane().add(category_field);
		
		JLabel category_label = new JLabel("Category");
		category_label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		category_label.setForeground(Color.WHITE);
		category_label.setBounds(6, 104, 49, 16);
		frame.getContentPane().add(category_label);
		
		JLabel lblNewLabel_1_2 = new JLabel("Amount");
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(6, 132, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		amount_field = new JTextField();
		amount_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		amount_field.setForeground(new Color(192, 192, 192));
		amount_field.setText("35");
		amount_field.setColumns(10);
		amount_field.setBounds(55, 126, 106, 26);
		frame.getContentPane().add(amount_field);
		
		JFormattedTextField date_field = new JFormattedTextField();
		date_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		date_field.setForeground(new Color(192, 192, 192));
		date_field.setText("dd/MM/yyyy");
		date_field.setToolTipText("dd/MM/yyyy");
		date_field.setBounds(54, 160, 106, 26);
		frame.getContentPane().add(date_field);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Date");
		lblNewLabel_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setBounds(10, 164, 33, 16);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		Button clear_button = new Button("Clear");
		clear_button.setBounds(79, 194, 82, 29);
		frame.getContentPane().add(clear_button);
		

		
		  
		Button save_button = new Button("Save");
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title,amount,category,date;
				title = title_field.getText();
				category = category_field.getSelectedItem().toString();;
				amount = amount_field.getText();
				date = date_field.getText();
				try {
				pst = con.prepareStatement("insert into transaction(transaction_title,category_name,amount,date)values(?,?,?,?)");
				pst.setString(1, title);
				pst.setString(2, category);
				pst.setString(3, amount);
				pst.setString(4, date);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Transaction Added!","Success" ,JOptionPane.INFORMATION_MESSAGE );
//				table_load();
				          
				title_field.setText("");
				category_field.setSelectedIndex(0);;
				amount_field.setText("");
				date_field.requestFocus();
				   }
				catch (SQLException e1)
				 {
					JOptionPane.showMessageDialog(null, "Transaction Failed!","Fail",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				 }
			}
		});
		save_button.setBounds(10, 194, 74, 29);
		frame.getContentPane().add(save_button);
		
		Button update_button = new Button("Update");
		update_button.setBounds(295, 238, 74, 29);
		frame.getContentPane().add(update_button);
		
		Button delete_button_1 = new Button("Delete");
		delete_button_1.setBounds(365, 238, 66, 29);
		frame.getContentPane().add(delete_button_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Search");
		lblNewLabel_1_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1_2_1_1.setBounds(10, 245, 33, 16);
		frame.getContentPane().add(lblNewLabel_1_2_1_1);
		
		txtTransactionid = new JTextField();
		txtTransactionid.setText("Enter transaction_id");
		txtTransactionid.setForeground(Color.LIGHT_GRAY);
		txtTransactionid.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtTransactionid.setColumns(10);
		txtTransactionid.setBounds(55, 241, 129, 26);
		frame.getContentPane().add(txtTransactionid);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField txtDate = new JFormattedTextField(df);
	}
}
