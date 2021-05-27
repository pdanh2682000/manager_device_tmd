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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private JTextField textBT;
	private JTextField textNhap;
	private JTextField textModel;
	private JTextField textCompany;
	private JTextField textCountry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThietBiView frame = new ThietBiView();
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
	public ThietBiView() {
		JComboBox comboMaP = new JComboBox();
		JComboBox comboMaTT = new JComboBox();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
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
		
		JLabel labelNhap = new JLabel("Ngày nhập");
		labelNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNhap.setBounds(278, 70, 87, 20);
		contentPane.add(labelNhap);
		
		JLabel labelMaP = new JLabel("Mã Phòng");
		labelMaP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaP.setBounds(312, 206, 71, 22);
		contentPane.add(labelMaP);
		
		JLabel labelBT = new JLabel("Hạn BT");
		labelBT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelBT.setBounds(21, 140, 57, 20);
		contentPane.add(labelBT);
		
		textTB = new JTextField();
		textTB.setBounds(104, 65, 145, 25);
		contentPane.add(textTB);
		textTB.setColumns(10);
		
		textBT = new JTextField();
		textBT.setBounds(352, 65, 64, 24);
		contentPane.add(textBT);
		textBT.setColumns(10);
		
		JButton btnAdd = new JButton("Thêm mới");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 153, 204));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get first character for every word (Upper Case)
				String result = new String("");
				char keys[] = {'Ă', 'Â', 'Á', 'À', 'Ạ', 'Ả', 'Ã', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ','Ấ',
						'Ầ', 'Ẩ', 'Ẫ', 'Ậ', 'Ô', 'Ơ', 'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ố', 'Ồ', 'Ổ', 'Ỗ' , 'Ộ' 
						, 'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ', 'Ĩ', 'Ỉ', 'Í', 'Ị', 'Ì', 'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ',
						'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ', 'Ú', 'Ù', 'Ụ', 'Ủ', 'Ũ'};
				char values[] = {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
						 'A', 'A', 'A', 'A', 'A', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
						 'O', 'O', 'O', 'O', 'O', 'I', 'I', 'I', 'I', 'I',
						 'E', 'E','E', 'E','E', 'E','E', 'E','E', 'E','E', 'U', 'U', 'U', 'U', 'U'};
				if(!textTB.getText().equals("")) {
					StringTokenizer str = new StringTokenizer(textTB.getText());
					while(str.hasMoreTokens()) {
						char temp = str.nextToken().toUpperCase().charAt(0);
						for(int i=0; i<keys.length; i++) {
							if(temp == keys[i]) temp = values[i];
						}
						result += temp;	
					}
				}
				// database
				String maTB = new String("");
				int indexMaTB = 0;
				ArrayList<String> arr = getAllMaTB(result);
				if(!arr.isEmpty()) {
					String tail = arr.get(arr.size()-1).substring(result.length());
					if(tail.equals("")) indexMaTB = 1;
					else indexMaTB = Integer.valueOf(tail) + 1;
//					System.out.println(tail);
					maTB = result.concat(String.valueOf(indexMaTB));
				}
				else maTB = result + "1";
				
				String tenTB = textTB.getText();
				String maPhong = getMaPhong(comboMaP.getSelectedIndex());
				String maTT = getMaTT(comboMaTT.getSelectedIndex());
				String ngayNhap = textNhap.getText();
				String hanBT = textBT.getText();
				String model = textModel.getText();
				String company = textCompany.getText();
				String country = textCountry.getText();
				insertDB_TB(maTB, tenTB, maPhong, maTT, ngayNhap, hanBT, model, company, country);
			}
		});
		btnAdd.setBounds(32, 288, 113, 30);
		contentPane.add(btnAdd);
		
		comboMaP.setModel(new DefaultComboBoxModel(new String[] {"A1 (Phòng A1)", "A2 (Phòng A2)", "A3 (Phòng A3)"}));
		comboMaP.setBounds(393, 204, 132, 23);
		contentPane.add(comboMaP);
		
		comboMaTT.setModel(new DefaultComboBoxModel(new String[] {"TT00 (Tốt)", "TT01 (Lỗi)", "TT02 (Hư)"}));
		comboMaTT.setBounds(104, 207, 145, 22);
		contentPane.add(comboMaTT);
		
		textNhap = new JTextField();
		textNhap.setBounds(104, 136, 57, 25);
		contentPane.add(textNhap);
		textNhap.setColumns(10);
		
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
		labelCountry.setBounds(438, 70, 57, 14);
		contentPane.add(labelCountry);
		
		textCountry = new JTextField();
		textCountry.setBounds(498, 64, 71, 26);
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
				}
			}
		});
		btnFile.setForeground(Color.WHITE);
		btnFile.setBackground(new Color(0, 153, 204));
		btnFile.setBounds(175, 288, 113, 30);
		contentPane.add(btnFile);
	}
	
	public Connection getConnection(String host, String port, String dbName, String username,
			String password) {
		Connection conn = null;
		String dbURL = "jdbc:postgresql://"+host+":"+port+"/"+dbName+"?loggerLevel=OFF";
		try {
			conn = DriverManager.getConnection(dbURL, username, password);
			System.out.println("Connect successfully!");
		}
		catch(Exception e) {
			System.out.println("Connect failure!");
			e.printStackTrace();
		}
		return conn;
	}
	
	public ArrayList<String> getAllMaTB(String header){
		ArrayList<String> arr = new ArrayList<String>();
		Connection conn = getConnection("localhost", "5432", "QLTB", "postgres", "123");
		if(conn != null) {
			Statement st = null;
			ResultSet rs = null;
			try {
				st = conn.createStatement();
				String sqlQuery = "SELECT matb FROM public.thietbi WHERE matb ~ " + "\'^" +header + "\'";
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					arr.add(rs.getString(1));
				}
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	
	public void insertDB_TB(String maTB, String tenTB, String maPhong, String maTT, String ngayNhap,
			String hanBT, String model, String company, String country) {
		Connection conn = getConnection("localhost", "5432", "QLTB", "postgres", "123");
		if(conn != null) {
			Statement st = null;
			PreparedStatement  ps = null;
			try {
				st = conn.createStatement();
				String sqlQuery = "INSERT INTO public.thietbi VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				ps = conn.prepareStatement(sqlQuery);
				ps.setString(1, maTB);
				ps.setString(2, tenTB);
				ps.setString(3, maPhong);
				ps.setString(4, maTT);
				ps.setString(5, ngayNhap);
				ps.setString(6, hanBT);
				ps.setString(7, model);
				ps.setString(8, company);
				ps.setString(9, country);
				ps.execute();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// excel
	public void insertFromExcel() throws IOException {
		String tenTB = new String("");
		String model = new String("");
		String company = new String("");
		String country = new String("");
		double namSX = 0;
		double namSD = 0;
		String tinhTrang = new String("");
		double giaThanh = 0;
		String ghiChu = new String("");
		
		
		// Đọc một file XSL.
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\DUYANH\\Documents\\Zalo Received Files\\qltb1.xlsx"));

		// Đối tượng workbook cho file XSL.
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		// Lấy ra sheet đầu tiên từ workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Cell cell = row.getCell(1);
			tenTB = cell.getStringCellValue();
			cell = row.getCell(2);
			model = cell.getStringCellValue();
			cell = row.getCell(3);
			company = cell.getStringCellValue();
			cell = row.getCell(4);
			country = cell.getStringCellValue();
			cell = row.getCell(5);
			namSX = cell.getNumericCellValue();
			cell = row.getCell(6);
			namSD = cell.getNumericCellValue();
			cell = row.getCell(7);
			tinhTrang = cell.getStringCellValue();
			cell = row.getCell(9);
			giaThanh = cell.getNumericCellValue();
			cell = row.getCell(10);
			ghiChu = cell.getStringCellValue();
					
			System.out.println(tenTB +" " + model +" " + company +" " + country +" " + namSX +" " + namSD +" "+
			tinhTrang + " " + giaThanh + " "+ ghiChu);
			
			String result = new String("");
			char keys[] = {'Ă', 'Â', 'Á', 'À', 'Ạ', 'Ả', 'Ã', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ','Ấ',
					'Ầ', 'Ẩ', 'Ẫ', 'Ậ', 'Ô', 'Ơ', 'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ố', 'Ồ', 'Ổ', 'Ỗ' , 'Ộ' 
					, 'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ', 'Ĩ', 'Ỉ', 'Í', 'Ị', 'Ì', 'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ',
					'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ', 'Ú', 'Ù', 'Ụ', 'Ủ', 'Ũ'};
			char values[] = {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
					 'A', 'A', 'A', 'A', 'A', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
					 'O', 'O', 'O', 'O', 'O', 'I', 'I', 'I', 'I', 'I',
					 'E', 'E','E', 'E','E', 'E','E', 'E','E', 'E','E', 'U', 'U', 'U', 'U', 'U'};
			if(!tenTB.equals("")) {
				StringTokenizer str = new StringTokenizer(tenTB);
				while(str.hasMoreTokens()) {
					char temp = str.nextToken().toUpperCase().charAt(0);
					for(int i=0; i<keys.length; i++) {
						if(temp == keys[i]) temp = values[i];
					}
					result += temp;	
				}
			}
			// database
			String maTB = new String("");
			int indexMaTB = 0;
			ArrayList<String> arr = getAllMaTB(result);
			if(!arr.isEmpty()) {
				String tail = arr.get(arr.size()-1).substring(result.length());
				if(tail.equals("")) indexMaTB = 1;
				else indexMaTB = Integer.valueOf(tail) + 1;
//				System.out.println(tail);
				maTB = result.concat(String.valueOf(indexMaTB));
			}
			else maTB = result + "1";
			
			String maTT = "";
			if(tinhTrang.equals("Mới")) maTT = "TT1";
			else maTT = "TT2";
//			insertDB_TB(maTB, tenTB, "A1", maTT, "1/1/2020", "3", model, company, country);

			// Lấy Iterator cho tất cả các cell của dòng hiện tại.
//			Iterator<Cell> cellIterator = row.cellIterator();
//			while (cellIterator.hasNext()) {
//				Cell cell = cellIterator.next();
//
//				// Đổi thành getCellType() nếu sử dụng POI 4.x
//				CellType cellType = cell.getCellTypeEnum();
//
//				switch (cellType) {
//				case _NONE:
//					System.out.print("");
//					System.out.print("\t");
//					break;
//				case BOOLEAN:
//					System.out.print(cell.getBooleanCellValue());
//					System.out.print("\t");
//					break;
//				case BLANK:
//					System.out.print("");
//					System.out.print("\t");
//					break;
//				case FORMULA:
//
//					// Công thức
//					System.out.print(cell.getCellFormula());
//					System.out.print("\t");
//
//					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//
//					// In ra giá trị từ công thức
//					System.out.print(evaluator.evaluate(cell).getNumberValue());
//					break;
//				case NUMERIC:
//					System.out.print("n:" + cell.getNumericCellValue());
//					System.out.print("\t");
//					break;
//				case STRING:
//					System.out.print(cell.getStringCellValue());
//					System.out.print("\t");
//					break;
//				case ERROR:
//					System.out.print("!");
//					System.out.print("\t");
//					break;
//				}
//
//			}
			System.out.println("");
		}
	}
	
	
	
	public String getMaTT(int index) {
		switch(index) {
			case 0: return "TT1";
			case 1: return "TT2";
			case 2: return "TT3";
			default: return "TT1";
		}
	}
	public String getMaPhong(int index) {
		switch(index) {
			case 0: return "A1";
			case 1: return "A2";
			case 2: return "A3";
			default: return "A1";
		}
	}
}