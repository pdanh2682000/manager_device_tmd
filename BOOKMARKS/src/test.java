import java.util.regex.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private int down = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				down = 1;
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(down == 1) {
					String arr[] = {"Phan", "Duy", "Anh", "Test", "Show", "Bookmarks"
							, "abc", "xyz", "qwer", "p123"};
//					System.out.println("abc");
					Vector header = new Vector();
					Vector data = new Vector();
					header.add("Show Bookmarks");
					// empty
					table.setModel(new DefaultTableModel(
							new Object[][] {
								{null},
							},
							new String[] {
								"Show Bookmarks"
							}
						));
					// find data
					String text = textField.getText();
					
//					System.out.println(text);
					for(String x:arr) {
						Pattern pattern = Pattern.compile(text.toLowerCase());    
						Matcher matcher = pattern.matcher(x.toLowerCase());    
						if(matcher.find()) {
							Vector row = new Vector();
							row.add(x);
							data.add(row);
						}
					}
					table.setModel(new DefaultTableModel(data,header));
				}
				down = 0;
			}
		});
		textField.setBounds(22, 26, 196, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(240, 25, 132, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 76, 350, 135);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP)
					textField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
				if(e.getKeyCode() == KeyEvent.VK_DOWN);
					textField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println(table.getSelectedRow());
//				System.out.println(table.getModel().getValueAt(table.getSelectedRow(), 0));
				textField.setText((String) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"Select"
			}
		));
		
		scrollPane.setViewportView(table);
	}
}
