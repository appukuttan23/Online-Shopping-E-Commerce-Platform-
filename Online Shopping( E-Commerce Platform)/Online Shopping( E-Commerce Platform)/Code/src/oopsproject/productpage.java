package oopsproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class productpage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					productpage frame = new productpage();
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
	public productpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 540);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 0, 0));
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product List");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 25));
		
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setOpaque(true);
		
		lblNewLabel.setBounds(0, 10, 1200, 50);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 81, 575, 257);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Gadugi", Font.BOLD, 14));
		table.setForeground(new Color(51, 51, 51));
		
		
		
		//color
		table.setBackground(UIManager.getColor("ComboBox.buttonBackground"));
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				
				
				String quantity = tblModel.getValueAt(table.getSelectedRow(), 3).toString();
				
				
	  
				  textField.setText(quantity);
				  
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Id", "Product Name", "Product Price", "Quantity"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("GoToCart");
		btnNewButton.setForeground(new Color(51, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cartpage tocart = new Cartpage();
				tocart.setVisible(true);
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Gadugi", Font.BOLD, 20));
		
		btnNewButton.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		
		btnNewButton.setBounds(360, 378, 153, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("AddToCart");
		btnNewButton_1.setForeground(new Color(51, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				
				try {
				Class.forName("org.postgresql.Driver");
				Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
			            "postgres", "aravind");
				
				int i = table.getSelectedRow();
				
					String Productid = tblModel.getValueAt(i, 0).toString();
					String productname = tblModel.getValueAt(i, 1).toString();
					String price = tblModel.getValueAt(i, 2).toString();
					String quantity = tblModel.getValueAt(i, 3).toString();
					
					String query = "insert into cart(product_id,product_name,product_price,quantity)values (?,?,?,?)";
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, Productid);
					ps.setString(2, productname);
					ps.setString(3, price);
					ps.setString(4, quantity);
					
					ps.executeUpdate();
					JOptionPane.showMessageDialog(btnNewButton_1, "Product Added to Cart");
				}
				
				
				
				catch(Exception e) {
					
					
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Gadugi", Font.BOLD, 20));
		
		btnNewButton_1.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		
		btnNewButton_1.setBounds(615, 378, 164, 54);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity:");
		lblNewLabel_1.setForeground(new Color(51, 0, 0));
		lblNewLabel_1.setFont(new Font("Gadugi", Font.BOLD, 20));
		lblNewLabel_1.setBounds(40, 115, 103, 49);
		contentPane.add(lblNewLabel_1);
		
		
		
		textField = new JTextField();
		textField.setFont(new Font("Gadugi", Font.BOLD, 20));
		
		//color
		textField.setBackground(new Color(255, 255, 255));
		
		textField.setBounds(153, 115, 123, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		
			
				try {
					Class.forName("org.postgresql.Driver");
					Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
				            "postgres", "aravind");
					
					Statement st = con.createStatement();
					
					String sql = "select * from productlist";
					ResultSet rs = st.executeQuery(sql);
					
					while(rs.next()) {
						
						String productid = rs.getString("product_id");
						String productname = rs.getString("product_name");
						String price = rs.getString("product_price");
						
						String tbdata[] = {productid,productname,price};
						DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
						
						tblModel.addRow(tbdata);
					
					}
					
					
					con.close();
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				JButton btnNewButton_2 = new JButton("Update");
				btnNewButton_2.setForeground(new Color(51, 51, 51));
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
						
						if (table.getSelectedRowCount() == 1) {
							
							String quantity = textField.getText();
							
							tblModel.setValueAt(quantity, table.getSelectedRow(), 3);
						}
						
							
						}
					
				});
				btnNewButton_2.setFont(new Font("Gadugi", Font.BOLD, 20));
				btnNewButton_2.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
				btnNewButton_2.setBounds(83, 202, 123, 44);
				contentPane.add(btnNewButton_2);
			}
	}

