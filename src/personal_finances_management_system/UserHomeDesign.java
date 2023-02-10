package personal_finances_management_system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.Button;

public class UserHomeDesign {

	private JFrame frame;
	private JTable table;
	private JTextField title_field;
	private JTextField amount_field;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		
		table = new JTable();
		table.setBounds(183, 68, 248, 155);
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(28, 67, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		title_field = new JTextField();
		title_field.setForeground(new Color(192, 192, 192));
		title_field.setText("Dinner");
		title_field.setBounds(28, 83, 122, 26);
		frame.getContentPane().add(title_field);
		title_field.setColumns(10);
		
		JComboBox category_field = new JComboBox();
		category_field.setBounds(28, 127, 122, 23);
		frame.getContentPane().add(category_field);
		
		JLabel category_label = new JLabel("Category");
		category_label.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		category_label.setForeground(Color.WHITE);
		category_label.setBounds(28, 110, 61, 16);
		frame.getContentPane().add(category_label);
		
		JLabel lblNewLabel_1_2 = new JLabel("Amount");
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(28, 151, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		amount_field = new JTextField();
		amount_field.setForeground(new Color(192, 192, 192));
		amount_field.setText("35");
		amount_field.setColumns(10);
		amount_field.setBounds(28, 162, 122, 26);
		frame.getContentPane().add(amount_field);
		
		JFormattedTextField frmtdtxtfldDdmmyyyy = new JFormattedTextField();
		frmtdtxtfldDdmmyyyy.setForeground(new Color(192, 192, 192));
		frmtdtxtfldDdmmyyyy.setText("dd/MM/yyyy");
		frmtdtxtfldDdmmyyyy.setToolTipText("dd/MM/yyyy");
		frmtdtxtfldDdmmyyyy.setBounds(28, 200, 122, 26);
		frame.getContentPane().add(frmtdtxtfldDdmmyyyy);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Date");
		lblNewLabel_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setBounds(28, 186, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		Button clear_button = new Button("Clear");
		clear_button.setBounds(91, 232, 66, 29);
		frame.getContentPane().add(clear_button);
		
		Button save_button = new Button("Save");
		save_button.setBounds(28, 232, 66, 29);
		frame.getContentPane().add(save_button);
		
		Button update_button = new Button("Update");
		update_button.setBounds(295, 232, 74, 29);
		frame.getContentPane().add(update_button);
		
		Button delete_button_1 = new Button("Delete");
		delete_button_1.setBounds(365, 229, 66, 29);
		frame.getContentPane().add(delete_button_1);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField txtDate = new JFormattedTextField(df);
	}
}
