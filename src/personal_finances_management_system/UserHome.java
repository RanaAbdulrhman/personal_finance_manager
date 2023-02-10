package personal_finances_management_system;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class UserHome extends JFrame{


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserHome frame = new UserHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserHome() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public UserHome(String userSes) {
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
		getContentPane().setBackground(new Color(26, 66, 116));
		getContentPane().setLayout(null);
		
		JButton transactions_button = new JButton("Manage Trasactions");
		transactions_button.setBackground(new Color(0, 145, 146));
		transactions_button.setBounds(136, 144, 177, 41);
		getContentPane().add(transactions_button);
		
		transactions_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Transactions window = new Transactions(userSes);

            }
        });
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(73, 146, 186));
		panel.setBounds(0, 0, 450, 73);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello "+ userSes);
		lblNewLabel.setForeground(new Color(254, 255, 255));
		lblNewLabel.setBounds(26, 25, 136, 24);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 20));
		
		JButton logout_button = new JButton("Logout");
		logout_button.setBounds(372, 26, 72, 29);
		panel.add(logout_button);
		logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(logout_button, "Are you sure?");
                // JOptionPane.setRootFrame(null);
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    UserLogin obj = new UserLogin();
                    obj.setTitle("Student-Login");
                    obj.setVisible(true);
                }
                dispose();
                UserLogin obj = new UserLogin();

                obj.setTitle("Student-Login");
                obj.setVisible(true);

            }
        });

		
		JButton catergories_button = new JButton("Manage Catergories");
		catergories_button.setBackground(new Color(0, 145, 146));
		catergories_button.setBounds(136, 191, 177, 41);
		getContentPane().add(catergories_button);
		
		JButton dashboard_button = new JButton("Dashboard");
		dashboard_button.setBackground(new Color(0, 145, 146));
		dashboard_button.setBounds(136, 96, 177, 41);
		getContentPane().add(dashboard_button);
	}
}