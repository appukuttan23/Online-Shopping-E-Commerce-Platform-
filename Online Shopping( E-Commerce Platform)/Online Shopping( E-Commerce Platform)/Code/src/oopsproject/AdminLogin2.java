package oopsproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class AdminLogin2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin2 frame = new AdminLogin2();
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
	public AdminLogin2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 36));
		lblNewLabel.setBounds(287, 10, 236, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Admin ID");
		lblNewLabel_1.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblNewLabel_1.setBounds(165, 101, 134, 45);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblNewLabel_2.setBounds(165, 211, 145, 45);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField.setBounds(364, 101, 225, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_1.setBounds(364, 219, 250, 45);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Connection connect=null;
					
					try {
						Class.forName("org.postgresql.Driver");
						connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
					            "postgres", "aravind");
						String id=textField.getText();
						String pass=textField_1.getText();
						Statement stm=connect.createStatement();
						String query="select * from adminrecord where admin_id='"+id+"' and admin_password='"+pass+"'";
						ResultSet rs=stm.executeQuery(query);
						if(rs.next()) {
							//System.out.print("Successfully Logged IN \n");
							JOptionPane.showMessageDialog(btnNewButton, "Login Succesful");
							Adminpage page=new Adminpage();
							page.setVisible(true);
							dispose();
							
							
						}
						else {
							//System.out.print("Login Not Successfull \n");
							JOptionPane.showMessageDialog(btnNewButton, "Login Not Succesful");
						}
						
						
					}catch(Exception E1) {
						E1.printStackTrace();
					}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnNewButton.setBounds(185, 352, 123, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminORUser2 AdminUser=new AdminORUser2();
				AdminUser.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnNewButton_1.setBounds(441, 352, 112, 44);
		contentPane.add(btnNewButton_1);
	}
	
}
