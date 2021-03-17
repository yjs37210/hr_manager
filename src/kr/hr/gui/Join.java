package kr.hr.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.hr.model.InformationDAO;
import kr.hr.model.InformationVO;

public class Join {

	private JFrame frame;
	private JTextField text_name;
	private JTextField text_mil_id;
	private JTextField text_pw;
	private JTextField text_manager_id;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join window = new Join();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Join() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 150, 960, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1960, 1080);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		text_name = new JTextField();
		text_name.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		text_name.setBounds(146, 166, 210, 35);
		panel_1.add(text_name);
		text_name.setColumns(10);
		text_name.setOpaque(false);
		text_name.setBorder(null);

		text_mil_id = new JTextField();
		text_mil_id.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		text_mil_id.setColumns(10);
		text_mil_id.setBounds(146, 233, 210, 43);
		panel_1.add(text_mil_id);
		text_mil_id.setOpaque(false);
		text_mil_id.setBorder(null);

		text_pw = new JTextField();
		text_pw.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		text_pw.setColumns(10);
		text_pw.setBounds(657, 162, 197, 43);
		panel_1.add(text_pw);
		text_pw.setOpaque(false);
		text_pw.setBorder(null);

		text_manager_id = new JTextField();
		text_manager_id.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		text_manager_id.setColumns(10);
		text_manager_id.setBounds(657, 223, 197, 43);
		panel_1.add(text_manager_id);
		text_manager_id.setOpaque(false);
		text_manager_id.setBorder(null);

		JComboBox com_job = new JComboBox();
		com_job.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		com_job.setBackground(new Color(255, 255, 255));
		com_job.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		com_job.setModel(new DefaultComboBoxModel(
				new String[] { "\uC120\uD0DD\uD558\uC138\uC694", "\uC778\uC0AC\uB300\uC6D0", "\uCC28\uB7C9\uB300\uC6D0",
						"\uC62C\uB808\uAE38\uB300\uC6D0", "\uC0C1\uD669\uB300\uC6D0", "\uBCF4\uAE09\uB300\uC6D0" }));
		com_job.setBounds(135, 298, 232, 50);
		panel_1.add(com_job);

		JComboBox com_rank = new JComboBox();
		com_rank.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		com_rank.setBackground(new Color(255, 255, 255));
		com_rank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		com_rank.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uD558\uC138\uC694", "\uC774\uACBD",
				"\uC77C\uACBD", "\uC0C1\uACBD", "\uC218\uACBD" }));
		com_rank.setBounds(135, 370, 232, 59);
		panel_1.add(com_rank);

		JComboBox com_month = new JComboBox();
		com_month.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		com_month.setBackground(new Color(255, 255, 255));
		com_month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		com_month.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uD558\uC138\uC694", "01", "02", "03",
				"04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		com_month.setBounds(604, 370, 103, 59);
		panel_1.add(com_month);

		JComboBox com_day = new JComboBox();
		com_day.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		com_day.setBackground(new Color(255, 255, 255));
		com_day.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		com_day.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uD558\uC138\uC694", "01", "02", "03",
				"04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

		com_day.setBounds(756, 370, 107, 59);
		panel_1.add(com_day);

		JComboBox com_year = new JComboBox();
		com_year.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		com_year.setBackground(new Color(255, 255, 255));
		com_year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		com_year.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uD558\uC138\uC694", "2016", "2017",
				"2018", "2019", "2020", "2021", "2022", "2023" }));
		com_year.setBounds(410, 370, 141, 59);
		panel_1.add(com_year);
		
		Image image = new ImageIcon("image/join.png").getImage();
		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(image.getScaledInstance(960, 540, Image.SCALE_SMOOTH)));
		lbl_background.setBounds(0, 0, 960, 540);
		panel_1.add(lbl_background);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = text_name.getText();
				String mil_id = text_mil_id.getText();
				int pw = Integer.parseInt(text_pw.getText());
				String manager_id = text_manager_id.getText();
				String job = (String) com_job.getSelectedItem();
				String year = (String) com_year.getSelectedItem();
				String month = (String) com_month.getSelectedItem();
				String day = (String) com_day.getSelectedItem();
				String rank = (String) com_rank.getSelectedItem();
				String s_date = year.concat(month).concat(day);

				InformationVO vo = new InformationVO(name, mil_id, pw, job, rank, s_date, manager_id);
				InformationDAO dao = new InformationDAO();
				int count = dao.insert(vo);

				if (count > 0) {
					JOptionPane.showMessageDialog(null, "½Åº´ ÀÔ·ÂÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.", "½Åº´°ü¸®", JOptionPane.INFORMATION_MESSAGE);
					new selectFunc(manager_id);
					frame.dispose();
				}
				if (count < 0) {
					JOptionPane.showMessageDialog(null, "´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.", "½Åº´°ü¸®", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblNewLabel.setBounds(420, 447, 97, 59);
		panel_1.add(lblNewLabel);

		
	}

}
