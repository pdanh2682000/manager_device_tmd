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
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ThietBiView extends JFrame {

	private JPanel contentPane;
	private JTextField textTB;
	private JTextField textBT;
	private JTextField textNhap;

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
		labelNhap.setBounds(312, 66, 87, 20);
		contentPane.add(labelNhap);
		
		JLabel labelMaP = new JLabel("Mã Phòng");
		labelMaP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelMaP.setBounds(312, 139, 71, 22);
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
		textBT.setBounds(405, 66, 132, 24);
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
				
			}
		});
		btnAdd.setBounds(32, 288, 113, 30);
		contentPane.add(btnAdd);
		
		JComboBox comboMaP = new JComboBox();
		comboMaP.setModel(new DefaultComboBoxModel(new String[] {"P01 (Phòng 1)", "P02 (Phòng 2)", "P03 (Phòng 3)"}));
		comboMaP.setBounds(405, 139, 132, 23);
		contentPane.add(comboMaP);
		
		JComboBox comboMaTT = new JComboBox();
		comboMaTT.setModel(new DefaultComboBoxModel(new String[] {"TT00 (Tốt)", "TT01 (Lỗi)", "TT02 (Hư)"}));
		comboMaTT.setBounds(104, 207, 145, 22);
		contentPane.add(comboMaTT);
		
		textNhap = new JTextField();
		textNhap.setBounds(104, 136, 145, 25);
		contentPane.add(textNhap);
		textNhap.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ THIẾT BỊ TMĐ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(229, 11, 226, 22);
		contentPane.add(lblNewLabel);

	}
	
	public void connectDB() {
		String host="localhost";
		String port="5432";
		String dbname="QLTB";
		String user="postgres";
		String pass="123";
		String dburl = "jdbc:postgresql://"+host+":"+port+"/"+dbname+"?loggerLevel=OFF";
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dburl, user, pass);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        
        try {
            // create statement
            stmt = conn.createStatement();
            // statement
            rs = stmt.executeQuery("select * from public.phong");
            // show data
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
