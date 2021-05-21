package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.User;
import Model.UserDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	
	// my variable
	private User user;
	private UserDao userDao;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginView frame = new LoginView();
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
	public LoginView() {
		// initial my variable
		user = new User();
		userDao = new UserDao();
		
		// system
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setBounds(80, 89, 59, 14);
		contentPane.add(labelUsername);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(80, 146, 59, 17);
		contentPane.add(labelPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(190, 83, 86, 20);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JButton btnSubmit = new JButton("Login");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = createUser();
				if(textUsername.getText().equals("")) showMessage("Tên đăng nhập không được bỏ trống!");
				else if(textPassword.getText().equals("")) showMessage("Mật khẩu không được bỏ trống!");
				else {
					if(userDao.checkUser(user)) {
						ThietBiView tb = new ThietBiView();
						closeLoginView();
						tb.setVisible(true);
					}
					else {
						showMessage("Tên đăng nhập hoặc mật khẩu không đúng!");
					}
				}
			}
		});
		btnSubmit.setBounds(119, 194, 89, 23);
		contentPane.add(btnSubmit);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(190, 144, 86, 20);
		contentPane.add(textPassword);
	}
	
	public User createUser() {
		if(textUsername != null && textPassword != null)
			return new User(textUsername.getText(), textPassword.getText());
		return null;
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public void closeLoginView() {
		this.setVisible(false);
	}
	
	public void openLoginView() {
		this.setVisible(true);
	}
	
	
}
