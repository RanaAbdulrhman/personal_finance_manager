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

public class Categories {

	private JFrame frame;
	private JTable table;
	private JTextField catergory_name_field;
	private JTextField type_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Categories window = new Categories("Rana");
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
	public Categories() {

	}
	
	public Categories(String userSes) {
		initialize(userSes);
		Connect();
		table_load(userSes);
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
	public void table_load(String userSes)
	   {
	     try
	     {    	  	 
		    pst = con.prepareStatement("select category_name,type from Catergory where user_name=?");
		    pst.setString(1, userSes);
		    rs = pst.executeQuery();
		    ResultSetMetaData rsmd = rs.getMetaData();
		    DefaultTableModel model = (DefaultTableModel) table.getModel(); 
		    
		    
		    String[] colNames = {"category_name","type"};
		    model.setColumnIdentifiers(colNames);
		    
		    int cols = rsmd.getColumnCount();
		    String category_name,type,type_name="";
		    model.setRowCount(0);
		    while(rs.next()) {
		    	category_name= rs.getString(1); 
		    	type= rs.getString(2); 
		    	System.out.print(type);
		    	if(type.contains("0"))
		    		type_name="Income";
		    	else if(type.contains("1")){
		    		type_name="Expense";
		    	}
		    	String[] row = {category_name,type_name};
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
		frame.getContentPane().setBackground(new Color(26, 66, 116));
		frame.setBounds(100, 100, 550, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(73, 146, 186));
		panel.setBounds(0, 0, 550, 55);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGE CATEGORIES ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(95, 17, 273, 16);
		panel.add(lblNewLabel);
		
		JButton logout_button = new JButton("Logout");
		logout_button.setBounds(383, 13, 75, 29);
		panel.add(logout_button);
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
		
		JLabel lblNewLabel_1 = new JLabel("Category name");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 77, 92, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		catergory_name_field = new JTextField();
		catergory_name_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		catergory_name_field.setBounds(105, 73, 106, 26);
		frame.getContentPane().add(catergory_name_field);
		catergory_name_field.setColumns(10);
		
		
		String [] items = { "Food", "Health Care", "Rent", "Entertainment" };
		
		JLabel category_label = new JLabel("Type");
		category_label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		category_label.setForeground(Color.WHITE);
		category_label.setBounds(14, 105, 31, 16);
		frame.getContentPane().add(category_label);
		

		
		  
		Button save_button = new Button("Add");
		save_button.setFont(new Font("Dialog", Font.PLAIN, 10));
		save_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,type_name;		
				int type=2;
				name = catergory_name_field.getText();
				type_name = type_field.getText();
				try {
				pst = con.prepareStatement("insert into Catergory(category_name,user_name, type)values(?,?,?)");
				pst.setString(1, name);
				pst.setString(2, userSes);
				if (type_name.toLowerCase().equals("income")) {
					type=0;
				}else if (type_name.toLowerCase().equals("expense")){
					type=1;
				}
				pst.setInt(3, type);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Catergory Added!","Success" ,JOptionPane.INFORMATION_MESSAGE );
				table_load(userSes);				         
				catergory_name_field.setText("");
				type_field.setText("");
				}
				catch (SQLException e1)
				 {
					JOptionPane.showMessageDialog(null, "Catergory Failed!","Fail",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				 }
			}
		});
		save_button.setBounds(10, 186, 75, 29);
		frame.getContentPane().add(save_button);
		
		Button delete_button = new Button("Delete");

		delete_button.setFont(new Font("Dialog", Font.PLAIN, 10));
		delete_button.setBounds(91, 186, 75, 29);
		frame.getContentPane().add(delete_button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 67, 233, 155);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int index = table.getSelectedRow();
				 DefaultTableModel model = (DefaultTableModel) table.getModel();
				 int selectedIndex = table.getSelectedRow();
				 catergory_name_field.setText(model.getValueAt(selectedIndex, 0).toString());
				 type_field.setText(model.getValueAt(selectedIndex, 1).toString());
			}
		});
		
		scrollPane.setViewportView(table);
		
		JLabel category_label_1 = new JLabel("(Income/Expnse)");
		category_label_1.setForeground(Color.WHITE);
		category_label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		category_label_1.setBounds(42, 106, 68, 16);
		frame.getContentPane().add(category_label_1);
		
		type_field = new JTextField();
		type_field.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		type_field.setColumns(10);
		type_field.setBounds(105, 101, 106, 26);
		frame.getContentPane().add(type_field);
		
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		          DefaultTableModel model = (DefaultTableModel) table.getModel();
		          int selectedIndex = table.getSelectedRow();
		            try {  
		                
			            String category_name = model.getValueAt(selectedIndex, 0).toString();
			            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to delete the category?","Warning",JOptionPane.YES_NO_OPTION);
			            if(dialogResult == JOptionPane.YES_OPTION){
		 
			            pst = con.prepareStatement("delete from Catergory where category_name = ?");
			            pst.setString(1,category_name);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Catergory Deleted");
			            catergory_name_field.setText("");
			            type_field.setText("");
			            table_load(userSes);		          
		           }
		 
		        } catch (SQLException ex) {
		        	JOptionPane.showMessageDialog(null, "Update Failed!","Fail",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
		        }
				
			}
		});
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField txtDate = new JFormattedTextField(df);
		frame.setVisible(true);
		
	}
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt){
//		System.out.print("row clicked");
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		int selectedIndex = table.getSelectedRow();
//		catergory_name_field.setText(model.getValueAt(selectedIndex, 2).toString());
//		category_field.setSelectedItem(model.getValueAt(selectedIndex, 3));
//		amount_field.setText(model.getValueAt(selectedIndex, 4).toString());
//		date_field.setText(model.getValueAt(selectedIndex, 5).toString());
	}
	
}
