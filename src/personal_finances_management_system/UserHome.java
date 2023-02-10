package personal_finances_management_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class UserHome extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    JPanel gui;
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
     * Create the frame.
     *public UserHome() {

    	}
     * arg String userSes
     */
    public UserHome() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        setLayout(new BorderLayout());
        JLabel header = new JLabel("Choose an operation", SwingConstants.CENTER);
        header.setFont(new Font("Tahoma", Font.PLAIN, 40));
        add(header, BorderLayout.PAGE_START);
        gui = new JPanel();
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
//        gui.setSize(new Dimension(100, 100));
        JButton dashboard = new JButton("View Dashboard");
        dashboard.setBounds(200, 50, 200, 50);
        JButton transactions = new JButton("Manage Transactions");
        transactions.setBounds(500, 50, 500, 50);
        gui.add(dashboard);
        gui.add(transactions);
        add(gui,BorderLayout.CENTER);
        


        JButton btnNewButton = new JButton("Logout");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    UserLogin obj = new UserLogin();
                    obj.setTitle("Student Login");
                    obj.setVisible(true);
                }
                dispose();
                UserLogin obj = new UserLogin();

                obj.setTitle("Student Login");
                obj.setVisible(true);

            }
        });
        btnNewButton.setBounds(70, 30, 70, 30);
        add(btnNewButton, BorderLayout.PAGE_END);
//        JButton button = new JButton("Change password\r\n");
//        button.setBackground(UIManager.getColor("Button.disabledForeground"));
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                ChangePassword bo = new ChangePassword(userSes);
//                bo.setTitle("Change Password");
//                bo.setVisible(true);
//
//            } 
//        });
//        button.setFont(new Font("Tahoma", Font.PLAIN, 10));
//        button.setBounds(70, 30, 70, 30);
//        contentPane.add(button);
    }
}