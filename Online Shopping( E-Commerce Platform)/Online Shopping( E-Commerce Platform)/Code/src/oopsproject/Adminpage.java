package oopsproject;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class Adminpage extends JFrame {

	private JPanel contentPane;
	private JTextField productid;
	private JTextField productname;
	private JTextField productprice;
	private JTable table;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 * 
	 */
	
	public Connection getConnection() {
		Connection con;
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
		            "postgres", "aravind");
			return con;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public ArrayList<Product> getProductList(){
		ArrayList<Product> productlist=new ArrayList<Product>();
		Connection connection=getConnection();
		String query="Select * from productlist";
		Statement st;
		ResultSet rs;
		try {
			st=connection.createStatement();
			rs=st.executeQuery(query);
			Product product;
			while(rs.next()) {
				product=new Product(rs.getString("product_id"),rs.getString("product_name"),rs.getString("product_price"));
				productlist.add(product);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return productlist;
	}
	
	//display data in jtable
	public void show_product() {
		ArrayList<Product> list=getProductList();
		model=(DefaultTableModel)table.getModel();
		Object[] row=new Object[4];
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getid();
			row[1]=list.get(i).getname();
			row[2]=list.get(i).getprice();
			model.addRow(row);
		}
		
		
	}
	//execute sql query
	public void executeSQLQuery(String query,String message) {
		Connection con=getConnection();
		Statement st;
		try {
			st=con.createStatement();
			if(st.executeUpdate(query)==1) {
				
				//refresh table
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				model.setRowCount(0);
				show_product();
				
				JOptionPane.showMessageDialog(null,"Data "+message+" Succesfully");
				
			}
			else {
				JOptionPane.showMessageDialog(null,"Data Not "+message);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adminpage frame = new Adminpage();
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
	public Adminpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 762);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(457, 21, 221, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1.setBounds(53, 156, 138, 45);
		contentPane.add(lblNewLabel_1);
		
		productid = new JTextField();
		productid.setFont(new Font("Tahoma", Font.BOLD, 18));
		productid.setBounds(209, 158, 237, 45);
		contentPane.add(productid);
		productid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Product Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(22, 248, 169, 45);
		contentPane.add(lblNewLabel_2);
		
		productname = new JTextField();
		productname.setFont(new Font("Tahoma", Font.BOLD, 19));
		productname.setBounds(209, 254, 293, 45);
		contentPane.add(productname);
		productname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Product Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3.setBounds(34, 336, 157, 31);
		contentPane.add(lblNewLabel_3);
		
		productprice = new JTextField();
		productprice.setFont(new Font("Tahoma", Font.BOLD, 19));
		productprice.setBounds(209, 340, 202, 45);
		contentPane.add(productprice);
		productprice.setColumns(10);
		
		
		//Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//display select row in JTextfields
				int i=table.getSelectedRow();
				TableModel model=table.getModel();
				productid.setText(model.getValueAt(i, 0).toString());
				productname.setText(model.getValueAt(i, 1).toString());
				productprice.setText(model.getValueAt(i, 2).toString());
			}
		});
		scrollPane.setBounds(532, 111, 486, 573);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 204));
		model=new DefaultTableModel();
		Object[] column= {"Product ID","Product Name","Product Price"};
		Object[] row=new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		show_product();
		
		//Buttons
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="INSERT INTO productlist(product_id,product_name,product_price) VALUES('"+productid.getText()+"','"+productname.getText()+"',"+productprice.getText()+")";
				executeSQLQuery(query,"Inserted");
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAdd.setBounds(34, 429, 138, 45);
		contentPane.add(btnAdd);
		
		/*JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query="DELETE FROM productlist WHERE product_id= "+ productid;
				
				executeSQLQuery(query,"Deleted");
			}
		});
		
		btnDelete.setBounds(435, 328, 138, 45);
		contentPane.add(btnDelete);*/
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="UPDATE productlist SET product_name='"+productname.getText()+"',product_price='"+productprice.getText()+"' where product_id='"+productid.getText()+"'";
				executeSQLQuery(query,"Updated");
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(382, 429, 129, 45);
		contentPane.add(btnUpdate);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="DELETE FROM productlist where product_id=?";
					Connection con=getConnection();
					PreparedStatement pst;
					pst=con.prepareStatement(query);
					pst.setString(1,productid.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(btnNewButton,"Row Deleted");
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					model.setRowCount(0);
					show_product();
					
					
				}catch(Exception e1) {
					e1.printStackTrace();
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(209, 429, 138, 45);
		contentPane.add(btnNewButton);
	}
}
