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
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Transactions {

	private JFrame frame;
	private JTable table;
	private JTextField title_field;
	private JComboBox category_field;
	private JTextField amount_field;
	private JFormattedTextField date_field;
	int user_balance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Transactions window = new Transactions("Rana");
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
	public Transactions() {

	}
	
	public Transactions(String userSes) {
		initialize(userSes);
		Connect();
		table_load(userSes);
		changeBalance(1000,1,"Rana");
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
	public boolean changeBalance(int amount,int type, String userSes) {
		// Get the balance
        try {
			pst = con.prepareStatement("Select user_balance from user where user_name=?");
            pst.setString(1, userSes);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString("user_balance"));
            	user_balance = Integer.parseInt(rs.getString("user_balance"));		
            }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        // get the updated balance
        if(type == 0) {
        	user_balance= user_balance + amount;
        }else if(type == 1){
        	if(user_balance-amount >= 0) {   
            	user_balance= user_balance-amount;
    	        // update the balance
        	}else {
            	return false; 
            }
       }
        
        // update
        try {
            pst = con.prepareStatement("update user set user_balance= ? where user_name= ?");
            pst.setInt(1,user_balance);
            pst.setString(2,userSes);
            pst.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        return true; 
        	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void table_load(String userSes)
	   {
	     try
	     {    	  	 
		    pst = con.prepareStatement("select transaction_id,transaction_title,category_name,amount,date from transaction where user_name=?");
		    pst.setString(1, userSes);
		    rs = pst.executeQuery();
		    ResultSetMetaData rsmd = rs.getMetaData();
		    DefaultTableModel model = (DefaultTableModel) table.getModel(); 
		    		    
		    String[] colNames = {"ID","Title", "Category", "Amount", "Date"};
		    model.setColumnIdentifiers(colNames);
		    
		    int cols = rsmd.getColumnCount();
		    String id,title,category,amount,date;
		    model.setRowCount(0);
		    while(rs.next()) {
		    	id= rs.getString(1); 
		    	title= rs.getString(2); 
		    	category= rs.getString(3); 
		    	amount = rs.getString(4);
		    	date = rs.getString(5);
		    	String[] row = {id,title, category, amount, date};
		    	model.addRow(row);
		    }
	     }
	     catch (SQLException e)
	     {
	    	 e.printStackTrace();
	     }
	}
	private void initialize(String userSes) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(30, 22, 99));
		frame.setBounds(100, 100, 650, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 22, 143));
		panel.setBounds(0, 0, 664, 57);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Transactions");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(200, 17, 273, 16);
		panel.add(lblNewLabel);
		
		JButton logout_button = new JButton("Logout");
		logout_button.setBounds(550, 13, 75, 29);
		panel.add(logout_button);
		
		JButton backButton = new JButton("<");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                UserHome ah = new UserHome(userSes);
                ah.setTitle("Welcome");
                ah.setVisible(true);
			}
		});
		backButton.setBounds(19, 13, 75, 29);
		panel.add(backButton);
		logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(logout_button, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                	frame.dispose();
                    UserLogin obj = new UserLogin();
                    obj.setTitle("User Login");
                    obj.setVisible(true);
                }
            }
        });
		

		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(6, 108, 33, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		title_field = new JTextField();
		title_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		title_field.setBounds(86, 105, 106, 26);
		frame.getContentPane().add(title_field);
		title_field.setColumns(10);
		ArrayList<String> items= new ArrayList<String>();
	    try{
	        con = DriverManager.getConnection("jdbc:mysql://localhost/finance_manager", "root","");
			pst = con.prepareStatement("select category_name from Catergory where user_name=?");
			pst.setString(1, userSes);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				items.add(rs.getString(1));			    
			}   
			

			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		category_field = new JComboBox(items.toArray());
		category_field.setBounds(86, 155, 112, 23);
		frame.getContentPane().add(category_field);
		
		JLabel category_label = new JLabel("Category");
		category_label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		category_label.setForeground(Color.WHITE);
		category_label.setBounds(6, 157, 79, 16);
		frame.getContentPane().add(category_label);
		
		JLabel lblNewLabel_1_2 = new JLabel("Amount");
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(10, 200, 49, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		amount_field = new JTextField();
		amount_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		amount_field.setColumns(10);
		amount_field.setBounds(86, 197, 106, 26);
		frame.getContentPane().add(amount_field);
		
		JFormattedTextField date_field = new JFormattedTextField();
		date_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		date_field.setBounds(86, 245, 106, 26);
    	date_field.setText("dd/MM/yyyy");
		frame.getContentPane().add(date_field);

		date_field.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (date_field.getText().equals("dd/MM/yyyy")) {
		        	date_field.setText("");
		        	date_field.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (date_field.getText().isEmpty()) {
		        	date_field.setForeground(Color.GRAY);
		        	date_field.setText("dd/MM/yyyy");
		        }
		    }
		});
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Date");
		lblNewLabel_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setBounds(10, 248, 33, 16);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		

		
		  
		Button save_button = new Button("Save");
		save_button.setFont(new Font("Dialog", Font.PLAIN, 10));
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title,amount,category,date;
				title = title_field.getText();
				category = category_field.getSelectedItem().toString();
				amount = amount_field.getText();
				date = date_field.getText();
				try {
				pst = con.prepareStatement("insert into transaction(user_name,transaction_title,category_name,amount,date)values(?,?,?,?,?)");
				pst.setString(1, userSes);
				pst.setString(2, title);
				pst.setString(3, category);
				pst.setString(4, amount);
				pst.setString(5, date);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Transaction Added!","Success" ,JOptionPane.INFORMATION_MESSAGE );
				table_load(userSes);
				          
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
				
				// Get the category type
				int category_type=2;
				try {
					pst = con.prepareStatement("select type from user join Catergory on user.user_name = Catergory.user_name where category_name=?");
					pst.setString(1, category);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						System.out.println(rs.getString("type"));
	                	category_type = Integer.parseInt(rs.getString("type"));		
	                }
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				int int_amount = Integer.parseInt(amount);
				if(!changeBalance(int_amount, category_type, userSes)) {
					JOptionPane.showMessageDialog(null, "There's not enough credit!","Fail",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		save_button.setBounds(10, 306, 68, 29);
		frame.getContentPane().add(save_button);
		
		Button update_button = new Button("Update");


		update_button.setFont(new Font("Dialog", Font.PLAIN, 10));
		update_button.setBounds(71, 306, 68, 29);
		frame.getContentPane().add(update_button);
		
		Button delete_button = new Button("Delete");

		delete_button.setFont(new Font("Dialog", Font.PLAIN, 10));
		delete_button.setBounds(142, 306, 61, 29);
		frame.getContentPane().add(delete_button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 69, 429, 297);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int index = table.getSelectedRow();
//				 System.out.print("row clicked"+index);
				 DefaultTableModel model = (DefaultTableModel) table.getModel();
				 int selectedIndex = table.getSelectedRow();
				 System.out.print(model.getValueAt(selectedIndex, 4).toString());
				 title_field.setText(model.getValueAt(selectedIndex, 1).toString());
				 category_field.setSelectedItem(model.getValueAt(selectedIndex, 2));
				 amount_field.setText(model.getValueAt(selectedIndex, 3).toString());
				 date_field.setText(model.getValueAt(selectedIndex, 4).toString());
			}
		});
		
		scrollPane.setViewportView(table);
		
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            int selectedIndex = table.getSelectedRow();
	            try {  
	                
		            int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
					String title = title_field.getText();
					String category = category_field.getSelectedItem().toString();;
					String amount = amount_field.getText();
					String date = date_field.getText();
		            pst = con.prepareStatement("update transaction set transaction_title= ?,category_name= ?,amount= ?,date= ? where transaction_id= ?");
		            pst.setString(1,title);
		            pst.setString(2,category);
		            pst.setString(3,amount);
		            pst.setString(4,date);
		            pst.setInt(5,id);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Transaction Updated");
		            title_field.setText("");
		            category_field.setSelectedIndex(0);
		            amount_field.setText("");
		            date_field.setText("");
		            table_load(userSes);
	          
	            } catch (SQLException ex) {
	            	JOptionPane.showMessageDialog(null, "Ubdate Failed!","Fail",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
	            }
			 }
		});
		
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		          DefaultTableModel model = (DefaultTableModel) table.getModel();
		          int selectedIndex = table.getSelectedRow();
		            try {  
		                
			            int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
			            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to Delete the transaction?","Warning",JOptionPane.YES_NO_OPTION);
			            if(dialogResult == JOptionPane.YES_OPTION){
		 
			            pst = con.prepareStatement("delete from transaction where transaction_id = ?");
			            pst.setInt(1,id);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Transaction Deleted");
			            title_field.setText("");
			            category_field.setSelectedIndex(0);
			            amount_field.setText("");
			            date_field.setText("");
			            table_load(userSes);
		          
		           }
		 
		        } catch (SQLException ex) {
		        	JOptionPane.showMessageDialog(null, "Ubdate Failed!","Fail",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
		        }
				
			}
		});
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField txtDate = new JFormattedTextField(df);
		frame.setVisible(true);
		
	}
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt){
		System.out.print("row clicked");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int selectedIndex = table.getSelectedRow();
		title_field.setText(model.getValueAt(selectedIndex, 2).toString());
		category_field.setSelectedItem(model.getValueAt(selectedIndex, 3));
		amount_field.setText(model.getValueAt(selectedIndex, 4).toString());
		date_field.setText(model.getValueAt(selectedIndex, 5).toString());
	}
	
}
