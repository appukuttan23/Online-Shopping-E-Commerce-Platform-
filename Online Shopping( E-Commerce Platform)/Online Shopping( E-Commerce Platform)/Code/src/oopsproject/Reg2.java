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
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class Reg2 extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField userphnum;
	private JTextField useraddress;
	private JTextField useremail;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reg2 frame = new Reg2();
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
	public Reg2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 948, 721);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Registeration");
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 36));
		lblNewLabel.setBounds(349, 24, 313, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblNewLabel_1.setBounds(250, 105, 104, 33);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.BOLD, 19));
		username.setBounds(396, 107, 302, 41);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ph Number:");
		lblNewLabel_2.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblNewLabel_2.setBounds(186, 181, 158, 41);
		contentPane.add(lblNewLabel_2);
		
		userphnum = new JTextField();
		userphnum.setFont(new Font("Tahoma", Font.BOLD, 18));
		userphnum.setBounds(396, 181, 346, 47);
		contentPane.add(userphnum);
		userphnum.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address: ");
		lblNewLabel_3.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblNewLabel_3.setBounds(225, 254, 129, 38);
		contentPane.add(lblNewLabel_3);
		
		useraddress = new JTextField();
		useraddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		useraddress.setBounds(396, 252, 346, 79);
		contentPane.add(useraddress);
		useraddress.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email Id:");
		lblNewLabel_4.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblNewLabel_4.setBounds(225, 363, 129, 41);
		contentPane.add(lblNewLabel_4);
		
		useremail = new JTextField();
		useremail.setFont(new Font("Tahoma", Font.BOLD, 18));
		useremail.setBounds(396, 365, 320, 48);
		contentPane.add(useremail);
		useremail.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Set Password:");
		lblNewLabel_5.setFont(new Font("Gadugi", Font.BOLD, 28));
		lblNewLabel_5.setBounds(170, 460, 184, 41);
		contentPane.add(lblNewLabel_5);
		
		passwordField = new JTextField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordField.setBounds(396, 458, 346, 54);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JButton userregisterBTN = new JButton("Register");
		userregisterBTN.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		userregisterBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect=null;
				PreparedStatement pst=null;
				String uemail=useremail.getText();
				String upass=passwordField.getText();
				String uname=username.getText();
				String uphno=userphnum.getText();
				String uaddress=useraddress.getText();
				try {
					connect=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
				            "postgres", "aravind");
					String query="INSERT INTO userrecord(useremail,userpassword,username,useraddress,userphone) VALUES(?,?,?,?,?)";
					pst=connect.prepareStatement(query);
					pst.setString(1, uemail);
					pst.setString(2, upass);
					pst.setString(3, uname);
					pst.setString(4, uaddress);
					pst.setString(5, uphno);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(userregisterBTN, "Registered Succesfully");
					Log2 login=new Log2();
					login.setVisible(true);
					dispose();
					
					
				}catch(Exception E1) {
					E1.printStackTrace();
				}
			}
		});
		userregisterBTN.setFont(new Font("Tahoma", Font.BOLD, 23));
		userregisterBTN.setBounds(396, 561, 165, 54);
		contentPane.add(userregisterBTN);
	}

}
