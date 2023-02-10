package personal_finances_management_system;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton LoginButton;
    private JPanel contentPane;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private JPanel panel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


	
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(26, 66, 116));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(254, 255, 255));
        lblNewLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 20));
        lblNewLabel.setBounds(158, 24, 134, 37);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 10));
        textField.setBounds(178, 102, 185, 37);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 10));
        passwordField.setBounds(178, 161, 185, 30);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(new Color(254, 255, 255));
        lblUsername.setFont(new Font("SF Pro Display", Font.PLAIN, 15));
        lblUsername.setBounds(78, 102, 98, 16);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(new Color(254, 255, 255));
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("SF Pro Display", Font.PLAIN, 15));
        lblPassword.setBounds(78, 164, 98, 16);
        contentPane.add(lblPassword);
        

        LoginButton = new JButton("Login");
        LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        LoginButton.setBounds(178, 215, 106, 30);
        
        LoginButton.addActionListener(new ActionListener() {
	        Connection con;
	        PreparedStatement pst;
	        ResultSet rs;
        	       	 
        	
	       public void actionPerformed(ActionEvent e) {
	                String userName = textField.getText();
	                String password = passwordField.getText();
	                
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
	               		            
	                try {
		                pst = con.prepareStatement("Select user_name from user where user_name=? and user_password=?");
		                pst.setString(1, userName);
		                pst.setString(2, password);
		                ResultSet rs = pst.executeQuery();
		                if (rs.next()) {
	                        dispose();
	                        UserHome ah = new UserHome(userName);
	                        ah.setTitle("Welcome");
	                        ah.setVisible(true);
	                        JOptionPane.showMessageDialog(null, "You have successfully logged in", "Login Success", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	                catch (SQLException e1)
	                {
	                	e1.printStackTrace();
	                }

	        

	                }
	     });
        contentPane.add(LoginButton);
        
        panel = new JPanel();
        panel.setBounds(0, 0, 450, 70);
        panel.setBackground(new Color(73, 146, 186));
        contentPane.add(panel);
    }
}
