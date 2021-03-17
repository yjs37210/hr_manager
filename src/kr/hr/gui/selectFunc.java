package kr.hr.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class selectFunc {

	private JFrame frame;

	public selectFunc(String manager_id) {
		initialize(manager_id);
		frame.setVisible(true);
	}

	private void initialize(String manager_id) {
		frame = new JFrame();
		frame.setTitle("\uC758\uACBD \uC601\uC678\uD65C\uB3D9 \uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		frame.setBounds(300, 150, 960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1960, 1080);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		Image image = new ImageIcon("image/selectfunc.png").getImage();

		JLabel lbl_background = new JLabel("");
		lbl_background.setIcon(new ImageIcon(image.getScaledInstance(960, 540, Image.SCALE_SMOOTH)));
		lbl_background.setBounds(0, 0, 960, 540);
		panel.add(lbl_background);
		
		JLabel lbl_1 = new JLabel("");
		lbl_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Join.main(null);
				frame.dispose();
			}
		});
		lbl_1.setBounds(160, 162, 223, 213);
		panel.add(lbl_1);
		
		JLabel lbl_2 = new JLabel("");
		lbl_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new onGoing(manager_id);
			}
		});
		lbl_2.setBounds(600, 162, 223, 213);
		panel.add(lbl_2);
	}

}
