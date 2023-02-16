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


public class Dashboard {
	
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
					Dashboard window = new Dashboard("Rana");
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
	public Dashboard() {

	}
	
	public Dashboard(String userSes) {
		initialize(userSes);
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

	private void initialize(String userSes) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(26, 66, 116));
		frame.setBounds(100, 100, 470, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(73, 146, 186));
		panel.setBounds(0, 0, 550, 55);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(95, 17, 273, 16);
		panel.add(lblNewLabel);
		
		JButton logout_button = new JButton("Logout");
		logout_button.setBounds(383, 13, 75, 29);
		panel.add(logout_button);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(18, 13, 75, 29);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                UserHome ah = new UserHome(userSes);
                ah.setTitle("Welcome");
                ah.setVisible(true);
			}
		});
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
		
		
		
		
		
		
		
		frame.setVisible(true);
		
	}
}
