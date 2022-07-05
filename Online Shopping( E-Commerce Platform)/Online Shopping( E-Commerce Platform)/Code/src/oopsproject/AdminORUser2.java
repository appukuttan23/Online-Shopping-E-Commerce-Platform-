package oopsproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class AdminORUser2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminORUser2 frame = new AdminORUser2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminORUser2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select User Type");
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 25));
		lblNewLabel.setBounds(267, 183, 200, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin2 adminlogin=new AdminLogin2();
				adminlogin.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(102, 274, 136, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Customer");
		btnNewButton_1.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log2 login=new Log2();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(500, 274, 136, 51);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Group 14.com");
		lblNewLabel_1.setForeground(new Color(51, 51, 51));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 43));
		lblNewLabel_1.setBounds(215, 38, 316, 98);
		contentPane.add(lblNewLabel_1);
	}
}
