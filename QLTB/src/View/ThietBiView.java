package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThietBiView extends JFrame {

	private JPanel contentPane;
	private JTextField textTB;
	private JTextField textDate;
	private JTextField textBT;
	private JTextField textModel;
	private JTextField textCompany;
	private JTextField textCountry;
	private JTextField textMoney;

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
		labelMaP.setBounds(312, 206, 71, 22);
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
				double giaTien = 0;
				if(!textMoney.getText().equals(""))
					giaTien = Double.parseDouble(textMoney.getText());
				String strDate = "2021-01-01";
				if(!textDate.getText().equals(""))
					strDate = textDate.getText();
				Date dateNhap = Date.valueOf(strDate);
				long numBT = 0;
				if(!textBT.getText().equals(""))
					numBT = dateNhap.getTime() + (Long.valueOf(textBT.getText()) * 30 * 24 * 60 * 60 * 1000);
				Date dateBT = new Date(numBT);
				try {
					insertDB_TB(maTB, tenTB, maPhong, maTT, 2020 , 2021 , model, country, company, dateNhap, giaTien, dateBT);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(32, 288, 113, 30);
		contentPane.add(btnAdd);

		comboMaP.setModel(new DefaultComboBoxModel(new String[] { "A1 (Phòng A1)", "A2 (Phòng A2)", "A3 (Phòng A3)" }));
		comboMaP.setBounds(393, 204, 132, 23);
		contentPane.add(comboMaP);

		comboMaTT.setModel(new DefaultComboBoxModel(new String[] { "TT00 (Tốt)", "TT01 (Lỗi)", "TT02 (Hư)" }));
		comboMaTT.setBounds(104, 207, 145, 22);
		contentPane.add(comboMaTT);

		textBT = new JTextField();
		textBT.setBounds(127, 139, 57, 25);
		contentPane.add(textBT);
		textBT.setColumns(10);

		JLabel lblNewLabel = new JLabel("QUẢN LÝ THIẾT BỊ TMĐ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(229, 11, 226, 22);
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

		JButton btnFile = new JButton("Load File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertFromExcel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		btnShow.setBounds(312, 288, 113, 30);
		contentPane.add(btnShow);
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
		Connection conn = getConnection("localhost", "5432", "QLTB", "postgres", "123");
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
		Connection conn = getConnection("localhost", "5432", "QLTB", "postgres", "123");
		if(maPhong.equals("")) maPhong = "A1"; // default
		if(maTT.equals("")) maTT = "TT1";
		if(model.equals("")) model = "no branch";
		if(country.equals("")) country = "no country";
		if(company.equals("")) company = "no company";
		if(date == null) date = new Date(0);
		if(hanBT == null) hanBT = new Date(0);
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
		Connection conn = getConnection("localhost", "5432", "QLTB", "postgres", "123");
		if(giaTien <= 0)	 giaTien = (double)1;
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
			}
			catch(Exception e) {
				conn.close();
				e.printStackTrace();
			}
		}
	}

	// excel
	public void insertFromExcel() throws IOException, SQLException {
		double stt =  0;
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
		FileInputStream inputStream = new FileInputStream(
				new File("D:\\duyanh\\manager_device_tmd\\QLTB\\qltb2.xlsx"));

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
			System.out.println(getMaTB(tenTB) + " - " + tenTB + " - " + "A1" + " - " + "TT1" +
					namSX + " - " + namSD + " - " + model);
			 //reset
			stt =  0;
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
		default:
			return "A1";
		}
	}
	
	public String getMaTB(String tenTB) throws SQLException {
		String result = new String("");
		char keys[] = { 'Ă', 'Â', 'Á', 'À', 'Ạ', 'Ả', 'Ã', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ', 'Ấ', 'Ầ', 'Ẩ', 'Ẫ', 'Ậ',
				'Ô', 'Ơ', 'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ố', 'Ồ', 'Ổ', 'Ỗ', 'Ộ', 'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ', 'Ĩ', 'Ỉ',
				'Í', 'Ị', 'Ì', 'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ', 'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ', 'Ú', 'Ù', 'Ụ', 'Ủ', 'Ũ', 
				'Đ'};
		char values[] = { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
				'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'I', 'I',
				'I', 'I', 'I', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'U', 'U', 'U', 'U', 'U'
				, 'D'};
		if (!tenTB.equals("")) {
			StringTokenizer str = new StringTokenizer(tenTB);
			while (str.hasMoreTokens()) {
				char temp = str.nextToken().toUpperCase().charAt(0);
				for (int i = 0; i < keys.length; i++) {
					if (temp == keys[i])
						temp = values[i];
					if(temp <= 47 || (temp >= 58 && temp <= 64)) temp = 'J'; // cac ki tu dac biet
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
}