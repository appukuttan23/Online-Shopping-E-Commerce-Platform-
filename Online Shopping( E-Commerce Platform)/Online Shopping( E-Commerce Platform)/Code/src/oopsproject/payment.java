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

public class payment extends JFrame {

	private JPanel contentPane;
	private JTextField useremail;
	private JTextField user_phno;
	private JTextField user_address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					payment frame = new payment();
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
	public payment() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 656);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(364, 29, 209, 73);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Confirm User ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(90, 133, 162, 34);
		contentPane.add(lblNewLabel_1);
		
		useremail = new JTextField();
		useremail.setFont(new Font("Tahoma", Font.BOLD, 17));
		useremail.setBounds(277, 133, 330, 34);
		contentPane.add(useremail);
		useremail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_2.setBounds(111, 240, 148, 46);
		contentPane.add(lblNewLabel_2);
		
		user_phno = new JTextField();
		user_phno.setFont(new Font("Tahoma", Font.BOLD, 17));
		user_phno.setBounds(277, 240, 296, 34);
		contentPane.add(user_phno);
		user_phno.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3.setBounds(135, 360, 117, 40);
		contentPane.add(lblNewLabel_3);
		
		user_address = new JTextField();
		user_address.setFont(new Font("Tahoma", Font.BOLD, 17));
		user_address.setBounds(277, 351, 315, 63);
		contentPane.add(user_address);
		user_address.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Confirm Payment");
		btnNewButton.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnNewButton, "Order Succesfully Placed");
				Connection connect=null;
				try {
					
					Class.forName("org.postgresql.Driver");
					connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
				            "postgres", "aravind");
					
					
					Statement stm=connect.createStatement();
					String query="delete from cart";
					ResultSet rs=stm.executeQuery(query);
					dispose();
				
			}
				
			catch(Exception E1) {
				E1.printStackTrace();
			}
			}		
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(605, 473, 209, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect=null;
				try {
					
					Class.forName("org.postgresql.Driver");
					connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
				            "postgres", "aravind");
					String userid=useremail.getText();
					
					Statement stm=connect.createStatement();
					String query="select userphone,useraddress from userrecord where useremail='"+userid+"'";
					ResultSet rs=stm.executeQuery(query);
					if(rs.next()) {
						String phno=rs.getString("userphone");
						String Address=rs.getString("useraddress");
						user_phno.setText(phno);
						user_address.setText(Address);
						
					}
					else {
						JOptionPane.showMessageDialog(lblNewLabel_1,"Invalid User Id");
						
					}
					
					
				}catch(Exception E1) {
					E1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(639, 133, 134, 34);
		contentPane.add(btnNewButton_1);
	}
}
