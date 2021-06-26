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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.SystemColor;

public class ShowView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnShowAll;
	private JLabel labelMaTB;
	private JTextField textShow;
	private JButton btnShowSelected;
	private JTextField textTen;

	private boolean key;
	private boolean key1;
	private ArrayList<String> arr;
	private JTable table_1;
	private JLabel labelTen;
	private JTextField textTenTB;
	private JLabel labelBT;
	private JTextField textBT;
	private JLabel labelMaTT;
	private JTextField textNhap;
	private JTextField textCountry;
	private JTextField textCompany;
	private JTextField textMoney;
	private JLabel labelSX;
	private JTextField textSX;
	private JLabel labelSD;
	private JTextField textSD;
	private JLabel labelModel;
	private JTextField textModel;
	private JSeparator separator_1;
	private JLabel labelMa;
	private JTextField textMa;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShowView frame = new ShowView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ShowView() {

		arr = new ArrayList<String>();
		// load database
		Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT tentb FROM public.thietbi");
			while (rs.next()) {
				arr.add(rs.getString(1));
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

		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Show");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 311, 1164, 215);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				key1 = true;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (key1) {
					if (e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN) {
						textMa.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
						textTenTB.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
						textNhap.setText(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
						textCountry.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 9));
						textCompany.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 10));
						textSX.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 6));
						textSD.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 7));
						textModel.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 8));
						textMoney.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 11));

						// han BT;

						Date ngayNhap = Date.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
						Date ngayBT = Date.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 5).toString());
						long time = ngayBT.getMonth() - ngayNhap.getMonth();
						textBT.setText(String.valueOf(time));

						// reset
						key1 = false;
					}
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textMa.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
				textTenTB.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
				textNhap.setText(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
				textCountry.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 9));
				textCompany.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 10));
				textSX.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 6));
				textSD.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 7));
				textModel.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 8));
				textMoney.setText((String) table.getModel().getValueAt(table.getSelectedRow(), 11));

				// han BT;

				Date ngayNhap = Date.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
				Date ngayBT = Date.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 5).toString());
				long time = ngayBT.getMonth() - ngayNhap.getMonth();
				textBT.setText(String.valueOf(time));

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "matb", "tentb", "maphong", "matt",
				"ngaynhap", "hanbt", "namsx", "namsd", "model", "country", "company", "giatien" }));
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
				Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT matb, tentb, maphong, matinhtrang, ngaynhaptb, hanbaotri,"
							+ " namsx, namsd, model, country, company, giatl"
							+ "  FROM public.thietbi NATURAL JOIN public.thanhly");
					while (rs.next()) {
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

				table.setModel(new DefaultTableModel(data, header));
			}
		});
		btnShowAll.setBounds(412, 537, 89, 23);
		contentPane.add(btnShowAll);

		labelMaTB = new JLabel("Mã TB");
		labelMaTB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaTB.setBounds(259, 25, 64, 20);
		contentPane.add(labelMaTB);

		textShow = new JTextField();
		textShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maTB = textShow.getText();
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
				Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT matb, tentb, maphong, matinhtrang, ngaynhaptb, hanbaotri,"
							+ " namsx, namsd, model, country, company, giatl"
							+ "  FROM public.thietbi NATURAL JOIN public.thanhly WHERE matb = " + "\'" + maTB + "\'");
					while (rs.next()) {
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

				table.setModel(new DefaultTableModel(data, header));
			}
		});
		textShow.setColumns(10);
		textShow.setBounds(363, 24, 145, 25);
		contentPane.add(textShow);

		btnShowSelected = new JButton("Xem");
		btnShowSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maTB = textShow.getText();
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
				Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT matb, tentb, maphong, matinhtrang, ngaynhaptb, hanbaotri,"
							+ " namsx, namsd, model, country, company, giatl"
							+ "  FROM public.thietbi NATURAL JOIN public.thanhly WHERE matb = " + "\'" + maTB + "\'");
					while (rs.next()) {
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

				table.setModel(new DefaultTableModel(data, header));
			}
		});
		btnShowSelected.setBounds(513, 25, 89, 23);
		contentPane.add(btnShowSelected);

		JLabel lblTnTb = new JLabel("Tên TB");
		lblTnTb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnTb.setBounds(672, 29, 64, 20);
		contentPane.add(lblTnTb);

		textTen = new JTextField();
		textTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				key = true;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (key == true) {
					Vector header = new Vector();
					Vector data = new Vector();
					header.add("Tên TB");
					table_1.setModel(new DefaultTableModel(new Object[][] { { null }, }, new String[] { "Tên TB" }));

					String text = textTen.getText();
					for (String x : arr) {
						Pattern pattern = Pattern.compile(text.toLowerCase());
						Matcher matcher = pattern.matcher(x.toLowerCase());
						if (matcher.find()) {
							Vector row = new Vector();
							row.add(x);
							data.add(row);
						}
					}
					table_1.setModel(new DefaultTableModel(data, header));
				}
			}
		});
		textTen.setColumns(10);
		textTen.setBounds(733, 26, 145, 25);
		contentPane.add(textTen);

		JButton btnShowSelected_1 = new JButton("Xem");
		btnShowSelected_1.addActionListener(new ActionListener() {
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
				String text = textTen.getText();
				// load database
				Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT matb, tentb, maphong, matinhtrang, ngaynhaptb, hanbaotri,"
							+ " namsx, namsd, model, country, company, giatl"
							+ "  FROM public.thietbi NATURAL JOIN public.thanhly WHERE tentb = " + "\'" + text + "\'");
					while (rs.next()) {
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

				table.setModel(new DefaultTableModel(data, header));
			}
		});
		btnShowSelected_1.setBounds(898, 25, 89, 23);
		contentPane.add(btnShowSelected_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(732, 60, 255, 224);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
					textTen.setText((String) table_1.getModel().getValueAt(table_1.getSelectedRow(),
							table_1.getSelectedColumn()));
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					textTen.setText((String) table_1.getModel().getValueAt(table_1.getSelectedRow(),
							table_1.getSelectedColumn()));
			}
		});
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textTen.setText(
						(String) table_1.getModel().getValueAt(table_1.getSelectedRow(), table_1.getSelectedColumn()));
			}
		});
		table_1.setModel(new DefaultTableModel(new Object[][] { { null }, }, new String[] { "Tên TB" }));
		scrollPane_1.setViewportView(table_1);

		labelTen = new JLabel("Tên TB");
		labelTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTen.setBounds(10, 97, 64, 20);
		contentPane.add(labelTen);

		textTenTB = new JTextField();
		textTenTB.setColumns(10);
		textTenTB.setBounds(69, 92, 145, 25);
		contentPane.add(textTenTB);

		labelBT = new JLabel("Hạn BT (month)");
		labelBT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelBT.setBounds(10, 146, 96, 20);
		contentPane.add(labelBT);

		textBT = new JTextField();
		textBT.setColumns(10);
		textBT.setBounds(125, 145, 57, 25);
		contentPane.add(textBT);

		labelMaTT = new JLabel("Mã TT");
		labelMaTT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaTT.setBounds(10, 193, 64, 20);
		contentPane.add(labelMaTT);

		JComboBox comboMaTT = new JComboBox();
		comboMaTT.setModel(new DefaultComboBoxModel(new String[] { "TT00 (Tốt)", "TT01 (Lỗi)", "TT02 (Hư)" }));
		comboMaTT.setBounds(87, 193, 145, 22);
		contentPane.add(comboMaTT);

		JLabel labelNhap = new JLabel("Ngày nhập(yyyy-mm-dd)");
		labelNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNhap.setBounds(231, 97, 145, 20);
		contentPane.add(labelNhap);

		textNhap = new JTextField();
		textNhap.setColumns(10);
		textNhap.setBounds(386, 94, 130, 24);
		contentPane.add(textNhap);

		JLabel labelCountry = new JLabel("Country");
		labelCountry.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelCountry.setBounds(545, 101, 57, 14);
		contentPane.add(labelCountry);

		textCountry = new JTextField();
		textCountry.setColumns(10);
		textCountry.setBounds(618, 98, 93, 26);
		contentPane.add(textCountry);

		JLabel labelCompany = new JLabel("Company");
		labelCompany.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelCompany.setBounds(205, 150, 57, 14);
		contentPane.add(labelCompany);

		textCompany = new JTextField();
		textCompany.setColumns(10);
		textCompany.setBounds(272, 147, 86, 26);
		contentPane.add(textCompany);

		JLabel labelMoney = new JLabel("Thành tiền");
		labelMoney.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMoney.setBounds(386, 150, 86, 14);
		contentPane.add(labelMoney);

		textMoney = new JTextField();
		textMoney.setColumns(10);
		textMoney.setBounds(491, 147, 124, 26);
		contentPane.add(textMoney);

		JLabel labelMaP = new JLabel("Mã Phòng");
		labelMaP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaP.setBounds(259, 197, 71, 22);
		contentPane.add(labelMaP);

		JComboBox comboMaP = new JComboBox();
		comboMaP.setModel(new DefaultComboBoxModel(new String[] { "A1 (Phòng A1)", "A2 (Phòng A2)", "A3 (Phòng A3)",
				"A4 (Phòng A4)", "A5 (Phòng A5)", "A6 (Phòng A6)", "A7 (Phòng A7)", "A8 (Phòng A8)", "A9 (Phòng A9)",
				"A10 (Phòng A10)", "A11 (Phòng A11)", "A12 (Phòng A12)", "A13 (Phòng A13)", "A14 (Phòng A14)",
				"A15 (Phòng A15)", "A18 (Phòng A18)", "A19 (Phòng A19)", "B1 (Phòng B1)", "B2 (Phòng B2)",
				"B3 (Phòng B3)", "B4 (Phòng B4)", "B5 (Phòng B5)", "B6 (Phòng B6)", "B7 (Phòng B7)", "B8 (Phòng B8)",
				"B9 (Phòng B9)", "B10 (Phòng B10)", "B11 (Phòng B11)", "B12 (Phòng B12)", "B13 (Phòng B13)",
				"B14 (Phòng B14)", "B15 (Phòng B15)", "B16 (Phòng B16)", "B17 (Phòng B17)", "B18 (Phòng B18)",
				"B19 (Phòng B19)", "B20 (Phòng B20)", "B21 (Phòng B21)", "PM (Phòng mổ)", "XN (Phòng xét nghiệm)",
				"SA (Phòng siêu âm)", "XQ (Phòng X-Quang)", "DLX (Phòng DLX)", "TNT (Phòng TNT)",
				"NT (Phòng nội trú)" }));
		comboMaP.setBounds(340, 198, 132, 23);
		contentPane.add(comboMaP);

		JButton btnUpdate = new JButton("Cập Nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
				// data

				String maTB = textMa.getText();
				String tenTB = textTenTB.getText();
				String maPhong = getMaPhong(comboMaP.getSelectedIndex());
				String maTT = getMaTT(comboMaTT.getSelectedIndex());
				String model = textModel.getText();
				String company = textCompany.getText();
				String country = textCountry.getText();
				int namSX = 2021;
				int namSD = 2021;
				if (!textSX.getText().equals(""))
					namSX = Integer.valueOf(textSX.getText());
				if (!textSD.getText().equals(""))
					namSD = Integer.valueOf(textSD.getText());
				double giaTien = 0;
				if (!textMoney.getText().equals(""))
					giaTien = Double.parseDouble(textMoney.getText());

				// date default
				String strDate = "2021-01-01";
				if (!textNhap.getText().equals(""))
					strDate = textNhap.getText();
				Date dateNhap = Date.valueOf(strDate);
				Date dateBT;
				long numBT = 0;
				if (!textBT.getText().equals("")) {
					numBT = dateNhap.getTime() + (Long.valueOf(textBT.getText()) * 30 * 24 * 60 * 60 * 1000);
					dateBT = new Date(numBT);
				} else
					dateBT = Date.valueOf(strDate);
				if (!maTB.equals("") && !tenTB.equals("") && !maPhong.equals("") && !maTT.equals("")) {

					String query2 = "UPDATE public.thanhly SET giatl=\'" + giaTien + "\'" + " WHERE matb=\'" + maTB
							+ "\'";
					String query1 = "UPDATE public.thietbi SET tentb=\'" + tenTB + "\'" + ",maphong=\'" + maPhong + "\'"
							+ ",matinhtrang=\'" + maTT + "\'" + ",ngaynhaptb=\'" + dateNhap + "\'" + ",hanbaotri=\'"
							+ dateBT + "\'" + ",namsx=\'" + namSX + "\'" + ",namsd=\'" + namSD + "\'" + ",model=\'"
							+ model + "\'" + ",country=\'" + country + "\'" + ",company=\'" + company + "\'"
							+ " WHERE matb=\'" + maTB + "\'" + " ;" + query2;

					System.out.println(query1);

					PreparedStatement st = null;
					try {
						st = conn.prepareStatement(query1);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						st.executeUpdate();
						showMessage("Cập nhật thành công!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						showMessage("Cập nhật thành công!");
					}
				}

			}
		});
		btnUpdate.setBounds(528, 209, 89, 48);
		contentPane.add(btnUpdate);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 60, 712, 5);
		contentPane.add(separator);

		labelSX = new JLabel("Năm SX");
		labelSX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelSX.setBounds(10, 245, 96, 20);
		contentPane.add(labelSX);

		textSX = new JTextField();
		textSX.setColumns(10);
		textSX.setBounds(69, 246, 57, 25);
		contentPane.add(textSX);

		labelSD = new JLabel("Năm SD");
		labelSD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelSD.setBounds(148, 249, 96, 20);
		contentPane.add(labelSD);

		textSD = new JTextField();
		textSD.setColumns(10);
		textSD.setBounds(205, 244, 57, 25);
		contentPane.add(textSD);

		labelModel = new JLabel("Model");
		labelModel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelModel.setBounds(280, 249, 96, 20);
		contentPane.add(labelModel);

		textModel = new JTextField();
		textModel.setColumns(10);
		textModel.setBounds(340, 246, 57, 25);
		contentPane.add(textModel);

		separator_1 = new JSeparator();
		separator_1.setBounds(10, 282, 712, 14);
		contentPane.add(separator_1);

		labelMa = new JLabel("Mã");
		labelMa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMa.setBounds(412, 249, 41, 20);
		contentPane.add(labelMa);

		textMa = new JTextField();
		textMa.setColumns(10);
		textMa.setBounds(440, 246, 57, 25);
		contentPane.add(textMa);

		JButton btnFix = new JButton("Sửa chữa");
		btnFix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maScTb = textMa.getText();
//				 System.out.println(maScTb);
				if (!maScTb.equals("")) {
					SuaChuaView sc = new SuaChuaView(maScTb);
					sc.setVisible(true);
					sc.setLocationRelativeTo(null);
					sc.setResizable(false);
					sc.setDefaultCloseOperation(sc.HIDE_ON_CLOSE);
					sc.setTitle("Sửa chữa");
				}
			}
		});
		btnFix.setBounds(594, 537, 117, 23);
		contentPane.add(btnFix);
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

	public String getMaTT(int index) {
		switch (index) {
		case 0:
			return "TT1";
		case 1:
			return "TT2";
		case 2:
			return "TT3";
		default:
			return "TT1";
		}
	}

	public String getMaPhong(int index) {
		switch (index) {
		case 0:
			return "A1";
		case 1:
			return "A2";
		case 2:
			return "A3";
		case 3:
			return "A4";
		case 4:
			return "A5";
		case 5:
			return "A6";
		case 6:
			return "A7";
		case 7:
			return "A8";
		case 8:
			return "A9";
		case 9:
			return "A10";
		case 10:
			return "A11";
		case 11:
			return "A12";
		case 12:
			return "A13";
		case 13:
			return "A14";
		case 14:
			return "A15";
		case 15:
			return "A18";
		case 16:
			return "A19";
		case 17:
			return "B1";
		case 18:
			return "B2";
		case 19:
			return "B3";
		case 20:
			return "B4";
		case 21:
			return "B5";
		case 22:
			return "B6";
		case 23:
			return "B7";
		case 24:
			return "B8";
		case 25:
			return "B9";
		case 26:
			return "B10";
		case 27:
			return "B11";
		case 28:
			return "B12";
		case 29:
			return "B13";
		case 30:
			return "B14";
		case 31:
			return "B15";
		case 32:
			return "B16";
		case 33:
			return "B17";
		case 34:
			return "B18";
		case 35:
			return "B19";
		case 36:
			return "B20";
		case 37:
			return "B21";
		case 38:
			return "PM";
		case 39:
			return "XN";
		case 40:
			return "SA";
		case 41:
			return "XQ";
		case 42:
			return "DLX";
		case 43:
			return "TNT";
		case 44:
			return "NT";
		default:
			return "A1";
		}
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
