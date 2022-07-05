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



public class Log2 extends JFrame {

	private JPanel contentPane;
	private  JTextField useremail;
	private JTextField userpassword;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log2 frame = new Log2();
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
	public Log2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("User Login");
		Title.setFont(new Font("Gadugi", Font.BOLD, 37));
		Title.setBounds(300, 23, 190, 44);
		contentPane.add(Title);
		
		JLabel lblNewLabel = new JLabel("Email Id");
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 23));
		lblNewLabel.setBounds(171, 110, 97, 44);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Gadugi", Font.BOLD, 23));
		lblNewLabel_1.setBounds(157, 192, 111, 28);
		contentPane.add(lblNewLabel_1);
		
		useremail = new JTextField();
		useremail.setFont(new Font("Tahoma", Font.BOLD, 20));
		useremail.setBounds(278, 107, 218, 44);
		contentPane.add(useremail);
		useremail.setColumns(10);
		
		
		
		
		userpassword = new JTextField();
		userpassword.setFont(new Font("Tahoma", Font.BOLD, 17));
		userpassword.setBounds(278, 191, 257, 44);
		contentPane.add(userpassword);
		userpassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Connection connect=null;
				
				try {
					Class.forName("org.postgresql.Driver");
					connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
				            "postgres", "aravind");
					String userid=useremail.getText();
					String userpass=userpassword.getText();
					Statement stm=connect.createStatement();
					String query="select * from userrecord where useremail='"+userid+"' and userpassword='"+userpass+"'";
					ResultSet rs=stm.executeQuery(query);
					if(rs.next()) {
						//System.out.print("Successfully Logged IN \n");
						JOptionPane.showMessageDialog(btnLogin,"Login Succesfull");
					    
						productpage propage=new productpage();
						propage.setVisible(true);
						dispose();
					}
					else {
						//System.out.print("Login Not Successfull \n");
						JOptionPane.showMessageDialog(btnLogin, "Login Not Succesful");
					}
					
					
				}catch(Exception E1) {
					E1.printStackTrace();
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnLogin.setBounds(192, 304, 116, 44);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Reg2 register=new Reg2();
				 register.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnRegister.setBounds(446, 303, 137, 44);
		contentPane.add(btnRegister);
	}
	

	
	
	
	
	
	
	
}

