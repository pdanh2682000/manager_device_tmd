package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DateFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
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
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import java.awt.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.ThietBi;
import javax.swing.JProgressBar;

public class ThietBiView extends JFrame {

//	private ThietBi thietbi;

	private JPanel contentPane;
	private JTextField textTB;
	private JTextField textDate;
	private JTextField textBT;
	private JTextField textModel;
	private JTextField textCompany;
	private JTextField textCountry;
	private JTextField textMoney;
	private JTextField textNamSX;
	private JTextField textNamSD;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ThietBiView frame = new ThietBiView();
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
	public ThietBiView() {
		JComboBox comboMaP = new JComboBox();
		JComboBox comboMaTT = new JComboBox();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("QLTB");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelMaTT = new JLabel("Mã TT");
		labelMaTT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaTT.setBounds(21, 207, 64, 20);
		contentPane.add(labelMaTT);

		JLabel labelTen = new JLabel("Tên TB");
		labelTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTen.setBounds(21, 70, 64, 20);
		contentPane.add(labelTen);

		JLabel labelNhap = new JLabel("Ngày nhập(yyyy-mm-dd)");
		labelNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNhap.setBounds(287, 70, 145, 20);
		contentPane.add(labelNhap);

		JLabel labelMaP = new JLabel("Mã Phòng");
		labelMaP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaP.setBounds(243, 206, 71, 22);
		contentPane.add(labelMaP);

		JLabel labelBT = new JLabel("Hạn BT (month)");
		labelBT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelBT.setBounds(21, 140, 96, 20);
		contentPane.add(labelBT);

		textTB = new JTextField();
		textTB.setBounds(104, 65, 145, 25);
		contentPane.add(textTB);
		textTB.setColumns(10);

		textDate = new JTextField();
		textDate.setBounds(442, 69, 130, 24);
		contentPane.add(textDate);
		textDate.setColumns(10);

		JButton btnAdd = new JButton("Thêm mới");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 153, 204));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get first character for every word (Upper Case)
				String tenTB = textTB.getText();
				String maTB = "";
				try {
					maTB = getMaTB(tenTB);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
//				System.out.println(maTB);

				String maPhong = getMaPhong(comboMaP.getSelectedIndex());
				String maTT = getMaTT(comboMaTT.getSelectedIndex());
				String ngayNhap = textBT.getText();
				String hanBT = textDate.getText();
				String model = textModel.getText();
				String company = textCompany.getText();
				String country = textCountry.getText();
				int namSX = 2021;
				int namSD = 2021;
				if (!textNamSX.getText().equals(""))
					namSX = Integer.valueOf(textNamSX.getText());
				if (!textNamSD.getText().equals(""))
					namSD = Integer.valueOf(textNamSX.getText());
				double giaTien = 0;
				if (!textMoney.getText().equals(""))
					giaTien = Double.parseDouble(textMoney.getText());

				// date default
				String strDate = "2021-01-01";
				if (!textDate.getText().equals(""))
					strDate = textDate.getText();
				Date dateNhap = Date.valueOf(strDate);
				Date dateBT;
				long numBT = 0;
				if (!textBT.getText().equals("")) {
					numBT = dateNhap.getTime() + (Long.valueOf(textBT.getText()) * 30 * 24 * 60 * 60 * 1000);
					dateBT = new Date(numBT);
				} else
					dateBT = Date.valueOf(strDate);

				// set default data
				if (model.equals(""))
					model = "no model";
				if (country.equals(""))
					model = "no country";
				if (company.equals(""))
					model = "no company";

				// check 4 field data absolute
				if (tenTB.equals(""))
					showMessage("Tên thiết bị không được để trống!");
				else if (maPhong.equals(""))
					showMessage("Mã phòng không được để trống!");
				else if (maTT.equals(""))
					showMessage("Mã tình trạng không được để trống!");
				else {
					try {
						insertDB_TB(maTB, tenTB, maPhong, maTT, namSX, namSD, model, country, company, dateNhap,
								giaTien, dateBT);
						showMessage("Thêm mới thành công!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						showMessage("Thêm mới thất bại!");
					}
				}
			}
		});
		btnAdd.setBounds(32, 288, 113, 30);
		contentPane.add(btnAdd);

		comboMaP.setModel(new DefaultComboBoxModel(new String[] {"A1 (Phòng A1)", "A2 (Phòng A2)", "A3 (Phòng A3)", "A4 (Phòng A4)", "A5 (Phòng A5)", "A6 (Phòng A6)", "A7 (Phòng A7)", "A8 (Phòng A8)", "A9 (Phòng A9)", "A10 (Phòng A10)", "A11 (Phòng A11)", "A12 (Phòng A12)", "A13 (Phòng A13)", "A14 (Phòng A14)", "A15 (Phòng A15)", "A18 (Phòng A18)", "A19 (Phòng A19)", "B1 (Phòng B1)", "B2 (Phòng B2)", "B3 (Phòng B3)", "B4 (Phòng B4)", "B5 (Phòng B5)", "B6 (Phòng B6)", "B7 (Phòng B7)", "B8 (Phòng B8)", "B9 (Phòng B9)", "B10 (Phòng B10)", "B11 (Phòng B11)", "B12 (Phòng B12)", "B13 (Phòng B13)", "B14 (Phòng B14)", "B15 (Phòng B15)", "B16 (Phòng B16)", "B17 (Phòng B17)", "B18 (Phòng B18)", "B19 (Phòng B19)", "B20 (Phòng B20)", "B21 (Phòng B21)", "PM (Phòng mổ)", "XN (Phòng xét nghiệm)", "SA (Phòng siêu âm)", "XQ (Phòng X-Quang)", "DLX (Phòng DLX)", "TNT (Phòng TNT)", "NT (Phòng nội trú)"}));
		comboMaP.setBounds(312, 207, 132, 23);
		contentPane.add(comboMaP);

		comboMaTT.setModel(new DefaultComboBoxModel(new String[] { "TT00 (Tốt)", "TT01 (Lỗi)", "TT02 (Hư)" }));
		comboMaTT.setBounds(78, 207, 145, 22);
		contentPane.add(comboMaTT);

		textBT = new JTextField();
		textBT.setBounds(127, 139, 57, 25);
		contentPane.add(textBT);
		textBT.setColumns(10);

		JLabel lblNewLabel = new JLabel("QUẢN LÝ THIẾT BỊ TMĐ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(307, 11, 226, 22);
		contentPane.add(lblNewLabel);

		JLabel labelModel = new JLabel("Model");
		labelModel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelModel.setBounds(209, 143, 46, 14);
		contentPane.add(labelModel);

		textModel = new JTextField();
		textModel.setBounds(265, 139, 86, 25);
		contentPane.add(textModel);
		textModel.setColumns(10);

		JLabel labelCompany = new JLabel("Company");
		labelCompany.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelCompany.setBounds(374, 141, 57, 14);
		contentPane.add(labelCompany);

		textCompany = new JTextField();
		textCompany.setBounds(460, 138, 86, 26);
		contentPane.add(textCompany);
		textCompany.setColumns(10);

		JLabel labelCountry = new JLabel("Country");
		labelCountry.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelCountry.setBounds(607, 73, 57, 14);
		contentPane.add(labelCountry);

		textCountry = new JTextField();
		textCountry.setBounds(674, 68, 93, 26);
		contentPane.add(textCountry);
		textCountry.setColumns(10);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(255, 69, 0));
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setBounds(80, 428, 169, 20);
		contentPane.add(progressBar);
		
		JButton btnFile = new JButton("Load File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(20);
				String pathExcel = new String("");
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Chọn tệp tin: ");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				// filter
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xls");
				jfc.addChoosableFileFilter(filter);
				jfc.setFileFilter(filter);

				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					pathExcel = jfc.getSelectedFile().getPath();
					System.out.println(pathExcel);
				}
				// insert
				if (!pathExcel.equals("")) {

					try {
						Cursor cursor = new Cursor(Cursor.WAIT_CURSOR); // HAND CURSOR
					    setCursor(cursor);
						insertFromExcel(pathExcel);
						cursor = new Cursor(Cursor.DEFAULT_CURSOR); // HAND CURSOR
					    setCursor(cursor);
						progressBar.setValue(100);
						showMessage("Load File thành công!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						showMessage("Load File thất bại!");
					}
				}
				progressBar.setValue(0);
			}
		});
		btnFile.setForeground(Color.WHITE);
		btnFile.setBackground(new Color(0, 153, 204));
		btnFile.setBounds(175, 288, 113, 30);
		contentPane.add(btnFile);

		JLabel labelMoney = new JLabel("Thành tiền");
		labelMoney.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMoney.setBounds(568, 144, 86, 14);
		contentPane.add(labelMoney);

		textMoney = new JTextField();
		textMoney.setColumns(10);
		textMoney.setBounds(643, 134, 124, 26);
		contentPane.add(textMoney);

		JButton btnShow = new JButton("Xem");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowView show = new ShowView();
				show.setVisible(true);
				show.setDefaultCloseOperation(show.HIDE_ON_CLOSE);
			}
		});
		btnShow.setForeground(Color.WHITE);
		btnShow.setBackground(new Color(0, 153, 204));
		btnShow.setBounds(470, 288, 113, 30);
		contentPane.add(btnShow);

		JLabel labelNamSX = new JLabel("Năm SX");
		labelNamSX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNamSX.setBounds(460, 210, 57, 14);
		contentPane.add(labelNamSX);

		textNamSX = new JTextField();
		textNamSX.setColumns(10);
		textNamSX.setBounds(527, 205, 86, 26);
		contentPane.add(textNamSX);

		JLabel labelNamSD = new JLabel("Năm SD");
		labelNamSD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNamSD.setBounds(623, 211, 57, 14);
		contentPane.add(labelNamSD);

		textNamSD = new JTextField();
		textNamSD.setColumns(10);
		textNamSD.setBounds(681, 207, 86, 26);
		contentPane.add(textNamSD);

		JButton btnExport = new JButton("Xuất Excel");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(20);
				// choose file
				String pathExcel = new String();
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Chọn thư mục: ");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isDirectory()) {
						pathExcel = jfc.getSelectedFile().getAbsolutePath();
						System.out.println("Bạn đã chọn: " + pathExcel);
					}
				}
				if (!pathExcel.equals("")) {
					// load database
					Cursor cursor = new Cursor(Cursor.WAIT_CURSOR); // HAND CURSOR
				    setCursor(cursor);
					ArrayList<ThietBi> list = new ArrayList<ThietBi>();
					Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
					try {
						// crate statement
						Statement stmt = conn.createStatement();
						// get data from table 'student'
						ResultSet rs = stmt.executeQuery("SELECT * from public.thietbi NATURAL JOIN public.thanhly");
						// show data
						while (rs.next()) {
//		                System.out.println(rs.getInt(1) + "  " + rs.getString(2) 
//		                        + "  " + rs.getString(3));
							ThietBi tb = new ThietBi();
							tb.setMaTB(rs.getString(1));
							tb.setTenTB(rs.getString(2));
							tb.setMaPhong(rs.getString(3));
							tb.setMaTinhTrang(rs.getString(4));
							tb.setNgayNhapTB(rs.getDate(5));
							tb.setHanBT(rs.getDate(6));
							tb.setNamSX((int) rs.getDouble(7));
							tb.setNamSD((int) rs.getDouble(8));
							tb.setModel(rs.getString(9));
							tb.setCountry(rs.getString(10));
							tb.setCompany(rs.getString(11));

							DecimalFormat df = new DecimalFormat("##");
							String strGiaTien = df.format(rs.getDouble(13));

							tb.setGiaTien(Double.parseDouble(strGiaTien));
							list.add(tb);
						}
						// close connection
						conn.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					// export
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet sheet = workbook.createSheet("ThietBi sheet");

					int rownum = 0;
					Cell cell;
					Row row;
					//
					XSSFCellStyle style = createStyleForTitle(workbook);

					row = sheet.createRow(rownum);

					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue("MaTB");
					cell.setCellStyle(style);

					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue("TenTB");
					cell.setCellStyle(style);

					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue("MaPhong");
					cell.setCellStyle(style);

					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue("MaTT");
					cell.setCellStyle(style);

					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue("NgayNhap");
					cell.setCellStyle(style);

					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue("HanBT");
					cell.setCellStyle(style);

					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue("NamSX");
					cell.setCellStyle(style);

					cell = row.createCell(7, CellType.STRING);
					cell.setCellValue("NamSD");
					cell.setCellStyle(style);

					cell = row.createCell(8, CellType.STRING);
					cell.setCellValue("Model");
					cell.setCellStyle(style);

					cell = row.createCell(9, CellType.STRING);
					cell.setCellValue("Country");
					cell.setCellStyle(style);

					cell = row.createCell(10, CellType.STRING);
					cell.setCellValue("Company");
					cell.setCellStyle(style);

					cell = row.createCell(11, CellType.STRING);
					cell.setCellValue("GiaTien");
					cell.setCellStyle(style);

					// Data
					for (ThietBi tb : list) {

						rownum++;
						row = sheet.createRow(rownum);

						// EmpNo (A)
						cell = row.createCell(0, CellType.STRING);
						cell.setCellValue(tb.getMaTB());
						// EmpName (B)
						cell = row.createCell(1, CellType.STRING);
						cell.setCellValue(tb.getTenTB());
						// Salary (C)
						cell = row.createCell(2, CellType.STRING);
						cell.setCellValue(tb.getMaPhong());
						// Grade (D)
						cell = row.createCell(3, CellType.STRING);
						cell.setCellValue(tb.getMaTinhTrang());
						// Bonus (E)
						cell = row.createCell(4, CellType.STRING);
						cell.setCellValue(tb.getNgayNhapTB().toString());

						cell = row.createCell(5, CellType.STRING);
						cell.setCellValue(tb.getHanBT().toString());

						cell = row.createCell(6, CellType.STRING);
						cell.setCellValue(String.valueOf(tb.getNamSX()));

						cell = row.createCell(7, CellType.STRING);
						cell.setCellValue(String.valueOf(tb.getNamSD()));

						cell = row.createCell(8, CellType.STRING);
						cell.setCellValue(tb.getModel());

						cell = row.createCell(9, CellType.STRING);
						cell.setCellValue(tb.getCountry());

						cell = row.createCell(10, CellType.STRING);
						cell.setCellValue(tb.getCompany());

						cell = row.createCell(11, CellType.STRING);
						cell.setCellValue(String.valueOf(tb.getGiaTien()));

					}

					// Auto resize column witdth
					int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
					autosizeColumn(sheet, numberOfColumn);

//				File file = new File("D:/duyanh/readexcel.xls");
//				System.out.println(pathExcel+"\\thietbi.xls");
					File file = new File(pathExcel + "\\thietbi.xlsx");
					file.getParentFile().mkdirs();

					FileOutputStream outFile = null;
					try {
						outFile = new FileOutputStream(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						workbook.write(outFile);
						progressBar.setValue(100);
						cursor = new Cursor(Cursor.DEFAULT_CURSOR); // HAND CURSOR
					    setCursor(cursor);
						showMessage("Xuất File thành công!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						showMessage("Xuất File thất bại!");
					}
					System.out.println("Created file: " + file.getAbsolutePath());
				}
				progressBar.setValue(0);
			}
		});
		btnExport.setForeground(Color.WHITE);
		btnExport.setBackground(new Color(0, 153, 204));
		btnExport.setBounds(323, 288, 113, 30);
		contentPane.add(btnExport);
		
		
		JLabel lblProcessing = new JLabel("Processing");
		lblProcessing.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProcessing.setBounds(10, 428, 64, 20);
		contentPane.add(lblProcessing);
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

	public ArrayList<String> getAllMaTB(String header) throws SQLException {
		ArrayList<String> arr = new ArrayList<String>();
		Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
		if (conn != null) {
			Statement st = null;
			ResultSet rs = null;
			try {
				st = conn.createStatement();
				String sqlQuery = "SELECT matb FROM public.thietbi WHERE matb ~ " + "\'^" + header + "\'";
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					arr.add(rs.getString(1));
				}
				conn.close();
			} catch (Exception e) {
				conn.close();
				e.printStackTrace();
			}
		}
		return arr;
	}

	public void insertDB_TB(String maTB, String tenTB, String maPhong, String maTT, double namSX, double namSD,
			String model, String country, String company, Date date, double giaTien, Date hanBT) throws SQLException {
		Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
		if (maPhong.equals(""))
			maPhong = "A1"; // default
		if (maTT.equals(""))
			maTT = "TT1";
		if (model.equals(""))
			model = "no model";
		if (country.equals(""))
			country = "no country";
		if (company.equals(""))
			company = "no company";
		if (date == null)
			date = Date.valueOf("2021-01-01");
		if (hanBT == null)
			hanBT = Date.valueOf("2021-01-01");
		if (conn != null) {
			PreparedStatement ps = null;
			try {
				// insert table thietbi
				String sqlQuery = "INSERT INTO public.thietbi(matb, tentb, maphong, matinhtrang, namsx, namsd,"
						+ "model,country, company, ngaynhaptb, hanbaotri)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, maTB);
				ps.setString(2, tenTB);
				ps.setString(3, maPhong);
				ps.setString(4, maTT);
				ps.setDouble(5, namSX);
				ps.setDouble(6, namSD);
				ps.setString(7, model);
				ps.setString(8, country);
				ps.setString(9, company);
				ps.setDate(10, date);
				ps.setDate(11, hanBT);
				ps.execute();
				conn.close();
				// insert table thanhly
				insertDB_TL(maTB, date, giaTien);
			} catch (Exception e) {
				conn.close();
				e.printStackTrace();
			}
		}
	}

	public void insertDB_TL(String maTB, Date date, Double giaTien) throws SQLException {
		Connection conn = getConnection("192.168.1.10", "5432", "QLTB", "postgres", "123");
		if (giaTien <= 0)
			giaTien = (double) 1;
		if (conn != null) {
			PreparedStatement ps = null;
			try {
				String sqlQuery2 = "INSERT INTO public.thanhly(matb, ngaytl, giatl) VALUES(?, ?, ?)";
				ps = conn.prepareStatement(sqlQuery2);
				ps.setString(1, maTB);
				ps.setDate(2, date);
				ps.setDouble(3, giaTien);
				ps.execute();
				conn.close();
			} catch (Exception e) {
				conn.close();
				e.printStackTrace();
			}
		}
	}

	// excel
	public void insertFromExcel(String pathExcel) throws IOException, SQLException {
		double stt = 0;
		String tenTB = new String("no info");
		String model = new String("no info");
		String company = new String("no info");
		String country = new String("no info");
		double namSX = 0;
		double namSD = 0;
		String tinhTrang = new String("no info");
		double giaThanh = 0;
		String ghiChu = new String("no info");

		// Đọc một file XSL.
		FileInputStream inputStream = new FileInputStream(new File(pathExcel));

		// Đối tượng workbook cho file XSL.
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		// Lấy ra sheet đầu tiên từ workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// Lấy Iterator cho tất cả các cell của dòng hiện tại.
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getColumnIndex() == 0) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						stt = cell.getNumericCellValue();
						break;
					case STRING:
						stt = Double.parseDouble(cell.getStringCellValue());
						break;
					}
				}
				if (cell.getColumnIndex() == 1) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						tenTB = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						tenTB = cell.getStringCellValue();
						break;
					}
				}
				if (cell.getColumnIndex() == 2) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						model = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						model = cell.getStringCellValue();
						break;
					}
				}
				if (cell.getColumnIndex() == 3) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						company = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						company = cell.getStringCellValue();
						break;
					}
				}
				if (cell.getColumnIndex() == 4) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						country = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						country = cell.getStringCellValue();
						break;
					}
				}
				if (cell.getColumnIndex() == 5) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						namSX = cell.getNumericCellValue();
						break;
					case STRING:
						namSX = Double.parseDouble(cell.getStringCellValue());
						break;
					}
				}
				if (cell.getColumnIndex() == 6) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						namSD = cell.getNumericCellValue();
						break;
					case STRING:
						namSD = Double.parseDouble(cell.getStringCellValue());
						break;
					}
				}
				if (cell.getColumnIndex() == 7) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						tinhTrang = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						tinhTrang = cell.getStringCellValue();
						break;
					}
				}
				if (cell.getColumnIndex() == 9) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						giaThanh = cell.getNumericCellValue();
						break;
					case STRING:
						giaThanh = 0;
						break;
					}
				}
				if (cell.getColumnIndex() == 10) {
					// Đổi thành getCellType() nếu sử dụng POI 4.x
					CellType cellType = cell.getCellTypeEnum();
					switch (cellType) {
					case NUMERIC:
						ghiChu = String.valueOf(cell.getNumericCellValue());
						break;
					case STRING:
						ghiChu = cell.getStringCellValue();
						break;
					}
				}
			}
			insertDB_TB(getMaTB(tenTB), tenTB, "", "", namSX, namSD, model, country, company, null, giaThanh, null);
			System.out.println(getMaTB(tenTB) + " - " + tenTB + " - " + "A1" + " - " + "TT1" + namSX + " - " + namSD
					+ " - " + model);
			// reset
			stt = 0;
			tenTB = new String("no info");
			model = new String("no info");
			company = new String("no info");
			country = new String("no info");
			namSX = 0;
			namSD = 0;
			tinhTrang = new String("no info");
			giaThanh = 0;
			ghiChu = new String("no info");
		}
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

	public String getMaTB(String tenTB) throws SQLException {
		String result = new String("");
		char keys[] = { 'Ă', 'Â', 'Á', 'À', 'Ạ', 'Ả', 'Ã', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ', 'Ấ', 'Ầ', 'Ẩ', 'Ẫ', 'Ậ', 'Ô', 'Ơ',
				'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ố', 'Ồ', 'Ổ', 'Ỗ', 'Ộ', 'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ', 'Ĩ', 'Ỉ', 'Í', 'Ị', 'Ì', 'É',
				'È', 'Ẻ', 'Ẽ', 'Ẹ', 'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ', 'Ú', 'Ù', 'Ụ', 'Ủ', 'Ũ', 'Đ' };
		char values[] = { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'O', 'O',
				'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'I', 'I', 'I', 'I', 'I', 'E',
				'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'U', 'U', 'U', 'U', 'U', 'D' };
		if (!tenTB.equals("")) {
			StringTokenizer str = new StringTokenizer(tenTB);
			while (str.hasMoreTokens()) {
				char temp = str.nextToken().toUpperCase().charAt(0);
				for (int i = 0; i < keys.length; i++) {
					if (temp == keys[i])
						temp = values[i];
					if (temp <= 47 || (temp >= 58 && temp <= 64))
						temp = 'J'; // cac ki tu dac biet
				}
				result += temp;
			}
		}
		result += "Z"; // ma rieng
		// database
		String maTB = new String("");
		int indexMaTB = 0;
		ArrayList<String> arr = getAllMaTB(result);
		if (!arr.isEmpty()) {
			String tail = arr.get(arr.size() - 1).substring(result.length());
			if (tail.equals(""))
				indexMaTB = 1;
			else {
				indexMaTB = Integer.valueOf(tail) + 1;
			}
//			System.out.println(tail);
			maTB = result.concat(String.valueOf(indexMaTB));
		} else
			maTB = result + "1";
		return maTB;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	private static void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}
}