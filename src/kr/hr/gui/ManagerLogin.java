package kr.hr.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.hr.model.ManagerDAO;

public class ManagerLogin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLogin window = new ManagerLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagerLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uC758\uACBD \uC601\uC678\uD65C\uB3D9 \uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(300, 150, 960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1960, 1080);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 18));
		textField.setBounds(192, 401, 167, 31);
		panel.add(textField);
		textField.setColumns(10);
		textField.setOpaque(false);
		textField.setBorder(null);

		textField_1 = new JPasswordField();
		textField_1.setFont(new Font("Dialog", Font.BOLD, 18));
		textField_1.setBounds(547, 401, 167, 31);
		panel.add(textField_1);
		textField_1.setOpaque(false);
		textField_1.setBorder(null);

		Image image = new ImageIcon("image/managerlogin.png").getImage();
		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(image.getScaledInstance(960, 540, Image.SCALE_SMOOTH)));
		lbl_background.setBounds(0, 0, 960, 540);
		panel.add(lbl_background);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String manager_id = textField.getText();
				String manager_pw_str = textField_1.getText();
				if(manager_pw_str.equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "접근불가", JOptionPane.ERROR_MESSAGE);
				}else {
					int manager_pw = Integer.parseInt(manager_pw_str);
					ManagerDAO dao = new ManagerDAO();
					String name = dao.managerlogin(manager_id, manager_pw);
					if(name != null) {
						new selectFunc(manager_id);
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "아이디와 비밀번호가 일치하지 않습니다.", "접근불가", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			
			}
		});
		lblNewLabel_3.setBounds(751, 388, 116, 56);
		panel.add(lblNewLabel_3);
	}
}
