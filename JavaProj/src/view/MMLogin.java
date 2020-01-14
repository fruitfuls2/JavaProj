package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controller.MemberManagementService;
import model.Member;
import java.awt.Color;

public class MMLogin {
	private MemberManagementService service = new MemberManagementService();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MMLogin window = new MMLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MMLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 240));
		panel.setBounds(473, 535, 350, 200);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(50, 90, 57, 15);
		lblNewLabel.setFont(new Font("���� ���� Semilight", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PW");
		lblNewLabel_1.setBounds(50, 115, 57, 15);
		lblNewLabel_1.setFont(new Font("���� ���� Semilight", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JTextField id = new JTextField();
		id.setBounds(119, 87, 175, 21);
		id.setFont(new Font("���� ���� Semilight", Font.PLAIN, 12));
		panel.add(id);
		id.setColumns(10);
		
		JPasswordField pw = new JPasswordField();
		pw.setBounds(119, 112, 175, 21);
		pw.setFont(new Font("���� ���� Semilight", Font.PLAIN, 12));
		panel.add(pw);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(50, 30, 244, 29);
		lblNewLabel_2.setFont(UIManager.getFont("CheckBoxMenuItem.font"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MMJoin join = new MMJoin();
			}
		});
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		String imgPath = this.getClass().getResource(".").getPath() + "..//..//img//btn004.png";
		System.out.println(imgPath);
		lblNewLabel_4.setIcon(new ImageIcon(imgPath));
		lblNewLabel_4.setBounds(184, 140, 117, 49);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String infoId = id.getText();
				String infoPw = pw.getText();
				Member m = new Member(infoId, infoPw);
				Member loginUser = service.memberLogin(m);
				if(loginUser==null) {
					JOptionPane.showMessageDialog(frame,
						    "�α��� ����");
				}else {
					JOptionPane.showMessageDialog(frame,
						    "�α��� ����");
					MMMain main = new MMMain(loginUser);
					frame.dispose();
				}
				
			}
		});
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		imgPath = this.getClass().getResource(".").getPath() + "..//..//img//btn003.png";
		System.out.println(imgPath);
		lblNewLabel_6.setIcon(new ImageIcon(imgPath));
		lblNewLabel_6.setBounds(77, 140, 95, 49);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_3 = new JLabel("");
		imgPath = this.getClass().getResource(".").getPath() + "..//..//img//0004.jpg";
		System.out.println(imgPath);
		lblNewLabel_3.setIcon(new ImageIcon(imgPath));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 0, 1280, 871);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
