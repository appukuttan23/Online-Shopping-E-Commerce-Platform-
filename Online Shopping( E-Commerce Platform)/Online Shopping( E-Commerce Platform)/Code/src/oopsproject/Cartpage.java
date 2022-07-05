package oopsproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class Cartpage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cartpage frame = new Cartpage();
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
	public Cartpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cart");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0,0,0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Gadugi", Font.BOLD, 20));
		lblNewLabel.setBounds(0,10,1200,50);
		
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Go To Payment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payment pay=new payment();
				pay.setVisible(true);
				
				
				
				
			}
		});
		btnNewButton.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton.setFont(new Font("Gadugi", Font.BOLD, 20));
		btnNewButton.setBounds(534, 395, 179, 42);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 87, 560, 294);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Productid", "Productname", "Price", "Quantity"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton_1.setFont(new Font("Gadugi", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				
				try {
					Class.forName("org.postgresql.Driver");
					Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
				            "postgres", "aravind");
					int SelectedRowIndex = table.getSelectedRow();
					
					

					String value=(table.getModel().getValueAt(SelectedRowIndex, 0).toString());
					
					
					
					String sql="DELETE FROM cart where product_id = '"+value+"'";
					PreparedStatement pst;
					pst=con.prepareStatement(sql);
//					pst.setString(1,productid.getText());
					pst.executeUpdate();
					model.removeRow(SelectedRowIndex);
					JOptionPane.showMessageDialog(btnNewButton,"Row Deleted");
					
				
					
				    //DefaultTableModel model1=(DefaultTableModel)table.getModel();
//					model1.setRowCount(0);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(614, 199, 109, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Calculate Total");
		btnNewButton_2.setForeground(new Color(51, 51, 51));
		btnNewButton_2.setFont(new Font("Gadugi", Font.BOLD, 20));
		btnNewButton_2.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numrow=table.getRowCount();
				
				double tot=0;
				
				for(int i=0;i<numrow;i++) {
					double price=Double.valueOf(table.getValueAt(i,2).toString());
					double quantity=Double.valueOf(table.getValueAt(i,3).toString());
					
					tot=tot+(price*quantity);
				}
				txtTotal.setText(Double.toString(tot));
			}
		});
		btnNewButton_2.setBounds(31, 388, 187, 49);
		contentPane.add(btnNewButton_2);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Gadugi", Font.BOLD, 20));
		txtTotal.setBounds(240, 392, 140, 42);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		 
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ecommerce",
		            "postgres", "aravind");
			
			Statement st = con.createStatement();
			
			String sql = "select * from cart";
			ResultSet rs = st.executeQuery(sql);
			
			
			
			
			while(rs.next()) {
				
				String productid = rs.getString("product_id");
				String productname = rs.getString("product_name");
				String price = rs.getString("product_price");
				String quantity = rs.getString("quantity");
				
				String tbdata[] = {productid,productname,price,quantity};
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				
				tblModel.addRow(tbdata);
			
			}
			
			
			con.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
