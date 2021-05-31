package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class ShowView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnShowAll;
	private JLabel labelMaTB;
	private JTextField textField;
	private JButton btnShowSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowView frame = new ShowView();
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
	public ShowView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 1164, 204);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"matb", "tentb", "maphong", "matt", "ngaynhap", "hanbt", "namsx", "namsd", "model", "country", "company", "giatien"
			}
		));
		scrollPane.setViewportView(table);
		
		btnShowAll = new JButton("Xem tất");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector data = new Vector();
				Vector header = new Vector();
				header.add("matb");
				header.add("tentb");
				header.add("maphong");
				header.add("matt");
				header.add("ngaynhap");
				header.add("hanbt");	
				header.add("namsx");
				header.add("namsd");
				header.add("model");
				header.add("country");
				header.add("company");
				header.add("giatien");
				
				// load database
				Connection conn = getConnection("localhost", "5432", "QLTB", "postgres", "123");
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT matb, tentb, maphong, matinhtrang, ngaynhaptb, hanbaotri,"
							+ " namsx, namsd, model, country, company, giatl"
							+ "  FROM public.thietbi NATURAL JOIN public.thanhly");
					while(rs.next()) {
						DecimalFormat df1 = new DecimalFormat("##");
						Vector row = new Vector();
						row.add(rs.getString(1));
						row.add(rs.getString(2));
						row.add(rs.getString(3));
						row.add(rs.getString(4));
						row.add(rs.getDate(5));
						row.add(rs.getDate(6));
						row.add(df1.format(rs.getDouble(7)));
						row.add(df1.format(rs.getDouble(8)));
						row.add(rs.getString(9));
						row.add(rs.getString(10));
						row.add(rs.getString(11));
						DecimalFormat df2 = new DecimalFormat("##.##");
						row.add(df2.format(rs.getDouble(12)));
						data.add(row);
					}
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					try {
						conn.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					e1.printStackTrace();
				}

				table.setModel(new DefaultTableModel(data,header));
			}
		});
		btnShowAll.setBounds(534, 316, 89, 23);
		contentPane.add(btnShowAll);
		
		labelMaTB = new JLabel("Mã TB");
		labelMaTB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaTB.setBounds(259, 25, 64, 20);
		contentPane.add(labelMaTB);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(363, 24, 145, 25);
		contentPane.add(textField);
		
		btnShowSelected = new JButton("Xem");
		btnShowSelected.setBounds(513, 25, 89, 23);
		contentPane.add(btnShowSelected);
	}
	
	public Connection getConnection(String host, String port, String dbName, String username, String password) {
		Connection conn = null;
		String dbURL = "jdbc:postgresql://" + host + ":" + port + "/" + dbName + "?loggerLevel=OFF";
		try {
			conn = DriverManager.getConnection(dbURL, username, password);
			System.out.println("Connect Database Successfully!");
		} catch (Exception e) {
			System.out.println("Connect Database Failure!");
			e.printStackTrace();
		}
		return conn;
	}
}
