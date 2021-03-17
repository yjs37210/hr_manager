package kr.hr.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.hr.model.AllowDAO;
import kr.hr.model.AllowVO;
import kr.hr.model.SubmitDAO;

public class Reject {

	private JFrame frame;
	private JTextField textField;

	public Reject(String manager_id, int sub_number) {
		initialize(manager_id, sub_number);
		frame.setVisible(true);
	}

	private void initialize(String manager_id, int sub_number) {
		frame = new JFrame();
		frame.setTitle("\uC758\uACBD \uC601\uC678\uD65C\uB3D9 \uC2DC\uC2A4\uD15C");
		frame.getContentPane().setBackground(new Color(51, 102, 204));
		frame.setBounds(100, 100, 450, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 102, 204));
		panel.setBounds(12, 10, 410, 292);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uBD88\uD5C8\uAC00 \uC0AC\uC720");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(56, 24, 297, 37);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(12, 85, 386, 118);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(51, 102, 153));
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String yn = "N";
				String note = textField.getText();
				AllowVO vo = new AllowVO(manager_id, sub_number, yn, note);
				AllowDAO dao = new AllowDAO();
				int cnt = dao.insertAllow(vo);
				if(cnt < 0) {
					JOptionPane.showMessageDialog(null, "ÈÞ°¡ Àç°¡ ¿À·ù", "¿À·ù", JOptionPane.INFORMATION_MESSAGE);
				}
				SubmitDAO sdao = new SubmitDAO();
				cnt = sdao.delete_allow(vo.getSub_number());
				if(cnt < 0) {
					JOptionPane.showMessageDialog(null, "ÈÞ°¡ Àç°¡ ¿À·ù", "¿À·ù", JOptionPane.INFORMATION_MESSAGE);
				}
				cnt = sdao.delete_submit(vo.getSub_number());
				if(cnt < 0) {
					JOptionPane.showMessageDialog(null, "ÈÞ°¡ Àç°¡ ¿À·ù", "¿À·ù", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Àü´Þ µÇ¾ú½À´Ï´Ù.", "ÈÞ°¡ °ÅºÎ", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				}
			}
		});
		btnNewButton.setBounds(156, 234, 97, 37);
		panel.add(btnNewButton);
	}

}
