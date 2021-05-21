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
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelMaTT = new JLabel("Mã TT");
		labelMaTT.setBounds(219, 11, 46, 14);
		contentPane.add(labelMaTT);
		
		JLabel labelTen = new JLabel("Tên TB");
		labelTen.setBounds(10, 11, 46, 14);
		contentPane.add(labelTen);
		
		JLabel labelNhap = new JLabel("Ngày nhập");
		labelNhap.setBounds(219, 53, 57, 14);
		contentPane.add(labelNhap);
		
		JLabel labelMaP = new JLabel("Mã Phòng");
		labelMaP.setBounds(10, 53, 64, 14);
		contentPane.add(labelMaP);
		
		JLabel labelBT = new JLabel("Hạn BT");
		labelBT.setBounds(219, 97, 57, 14);
		contentPane.add(labelBT);
		
		textTB = new JTextField();
		textTB.setBounds(84, 8, 104, 20);
		contentPane.add(textTB);
		textTB.setColumns(10);
		
		textBT = new JTextField();
		textBT.setBounds(286, 94, 86, 20);
		contentPane.add(textBT);
		textBT.setColumns(10);
		
		JButton btnAdd = new JButton("Thêm mới");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set MaTB
				String test[] = {"ML001", "ML002", "ML003"};
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
				System.out.println(result);	
			}
		});
		btnAdd.setBounds(10, 190, 89, 23);
		contentPane.add(btnAdd);
		
		JComboBox comboMaP = new JComboBox();
		comboMaP.setModel(new DefaultComboBoxModel(new String[] {"P01 (Phòng 1)", "P02 (Phòng 2)", "P03 (Phòng 3)"}));
		comboMaP.setBounds(84, 49, 104, 22);
		contentPane.add(comboMaP);
		
		JComboBox comboMaTT = new JComboBox();
		comboMaTT.setModel(new DefaultComboBoxModel(new String[] {"TT00 (Tốt)", "TT01 (Lỗi)", "TT02 (Hư)"}));
		comboMaTT.setBounds(285, 7, 87, 22);
		contentPane.add(comboMaTT);
		
		textNhap = new JTextField();
		textNhap.setBounds(286, 50, 86, 20);
		contentPane.add(textNhap);
		textNhap.setColumns(10);

	}
}
