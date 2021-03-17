package kr.hr.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.hr.model.AllowDAO;
import kr.hr.model.AllowVO;
import kr.hr.model.SubmitDAO;
import kr.hr.model.SubmitVO;

public class onGoing {

	private JFrame frame;
	static JLabel lblMonth, lblYear;
	static JButton btnPrev, btnNext;
	static JTable tblCalendar;
	static JComboBox cmbYear;
	static JFrame frmMain;
	static Container pane;
	static DefaultTableModel mtblCalendar; //
	static JScrollPane stblCalendar; // The scrollpane
	static JPanel pnlCalendar;
	static int realYear, realMonth, realDay, currentYear, currentMonth;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scroll_ing;
	private JTable table_3;
	private JButton btnNewButton;
	private JButton confirm;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;

	public onGoing(String manager_id) {
		initialize(manager_id);
		frame.setVisible(true);
	}

	private void initialize(String manager_id) {
		frame = new JFrame();
		frame.setBounds(200, 75, 1146, 723);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 331, 301, 310);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		// calendar

		pane = frame.getContentPane(); // Get content pane
		pane.setLayout(null); // Apply null layout
		lblYear = new JLabel("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton("이전 달");

		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (currentMonth == 0) { // 지금이 1월달이면
					currentMonth = 11; // 작년 12월달로 값이 넘어간다.
					currentYear -= 1; // 작년
				} else { // Back one month
					currentMonth -= 1; // 지난 달꺼로
				}
				refreshCalendar(currentMonth, currentYear);

			}
		});
		btnNext = new JButton("다음 달 ");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// 다음달로 넘어갈 때

				if (currentMonth == 11) { // Foward one year
					currentMonth = 0; // 다음년도 1월로 넘어간다.
					currentYear += 1;
				} else { // Foward one month
					currentMonth += 1; // 그냥 다음 월로 넘어간다.
				}
				refreshCalendar(currentMonth, currentYear);

			}
		});
		mtblCalendar = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);
		pnlCalendar = new JPanel(null);

		panel_1.add(pnlCalendar);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(stblCalendar);

		pnlCalendar.setBounds(0, 0, 301, 340);
		lblYear.setBounds(10, 305, 80, 20);
		cmbYear.setBounds(186, 283, 80, 20);
		btnPrev.setBounds(10, 0, 80, 25);
		btnNext.setBounds(203, 0, 92, 25);
		stblCalendar.setBounds(10, 33, 285, 250);

		cmbYear.addActionListener(new cmbYear_Action());

		frame.setResizable(false);
		frame.setVisible(true);

		GregorianCalendar cal = new GregorianCalendar(); // 그레고리력 생성
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // 그레고리력에서 현재 날짜 가져온다.
		realMonth = cal.get(GregorianCalendar.MONTH); // 실제 지금 달
		realYear = cal.get(GregorianCalendar.YEAR); // 실제 지금 연수
		currentMonth = realMonth; // currentMonth에 진짜 달 집어넣는다.
		currentYear = realYear;

		// Add headers
		String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }; // All headers
		for (int i = 0; i < 7; i++) {
			mtblCalendar.addColumn(headers[i]);// 차례로 일요일부터 집어넣어준다.
		}
		tblCalendar.getParent().setBackground(tblCalendar.getBackground()); // Set background

		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		tblCalendar.setRowHeight(38);

		lblMonth = new JLabel("January");
		lblMonth.setFont(new Font("굴림", Font.BOLD, 13));
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setBounds(115, 0, 76, 25);
		pnlCalendar.add(lblMonth);

		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 1128, 326);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1132, 316);
		panel_2.add(tabbedPane);

		scrollPane = new JScrollPane();
		tabbedPane.addTab("\uC815\uAE30\uD734\uAC00", null, scrollPane, null);

		String[] column1 = { "이름", "군번", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

		SubmitDAO dao = new SubmitDAO();
		ArrayList<SubmitVO> vacList = dao.vacSelect();

		Object[][] data1 = new Object[vacList.size()][33];

		table_6 = new JTable(data1, column1);
		table_6.setRowHeight(20);
		scrollPane.setViewportView(table_6);

		for (int i = 0; i < vacList.size(); i++) {
			data1[i][0] = vacList.get(i).getName();
			data1[i][1] = vacList.get(i).getMil_id();
			for (int j = 2, k = 1; j < 33; j++, k++) {
				data1[i][j] = k;
			}
		}
		table_6.setDefaultRenderer(Object.class, new CellRendererVac(vacList)); // 이것이 들어가야 cell의 색이 바뀜

		scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("\uC815\uAE30\uC678\uBC15", null, scrollPane_1, null);

		ArrayList<SubmitVO> nightList = dao.nightSelect();
		Object[][] data2 = new Object[nightList.size()][33]; //

		table_5 = new JTable(data2, column1);
		table_5.setRowHeight(20);
		scrollPane_1.setViewportView(table_5);

		for (int i = 0; i < nightList.size(); i++) {
			data2[i][0] = nightList.get(i).getName();
			data2[i][1] = nightList.get(i).getMil_id();
			for (int j = 2, k = 1; j < 33; j++, k++) {
				data2[i][j] = k;
			}
		}

		table_5.setDefaultRenderer(Object.class, new CellRendererVac(nightList)); // 이부분!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("\uC815\uAE30\uC678\uCD9C", null, scrollPane_2, null);

		ArrayList<SubmitVO> outList = dao.outSelect(); // outList DTO생성해놓음 즉, 정기 외출자들만 한곳에 모아 가져왔습니다.
		Object[][] data3 = new Object[outList.size()][33]; //

		table_4 = new JTable(data3, column1);
		table_4.setRowHeight(20);
		scrollPane_2.setViewportView(table_4);

		CellRendererOut cellO = new CellRendererOut(outList); // 외출자칸에 색을 입혀서 넣어줄수 있는 CellRendererOut 호출

		for (int i = 0; i < outList.size(); i++) {
			data3[i][0] = outList.get(i).getName();
			data3[i][1] = outList.get(i).getMil_id();
			for (int j = 2, k = 1; j < 33; j++, k++) {
				data3[i][j] = k;
			}
		}

		table_4.setDefaultRenderer(Object.class, cellO);

		panel_3 = new JPanel();
		panel_3.setBounds(313, 331, 807, 310);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		scroll_ing = new JScrollPane();
		scroll_ing.setBounds(0, 0, 795, 286);
		panel_3.add(scroll_ing);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);
		for (

				int i = realYear - 100; i <= realYear + 100; i++) {
			cmbYear.addItem(String.valueOf(i));
		}

		refreshCalendar(realMonth, realYear);
		ArrayList<SubmitVO> list = dao.allSelect();
		String[] column = { "군번", "계급", "이름", "보직", "휴가종류", "휴가 시작일", "휴가 종료일", "사유" };

		Object[][] data = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getMil_id();
			data[i][1] = list.get(i).getRank();
			data[i][2] = list.get(i).getName();
			data[i][3] = list.get(i).getJob();
			data[i][4] = list.get(i).getVac_type();
			data[i][5] = cmbYear.getSelectedItem().toString() + "년" + lblMonth.getText() + " "
					+ list.get(i).getSub_date() + "일";
			data[i][6] = cmbYear.getSelectedItem().toString() + "년" + lblMonth.getText() + " "
					+ list.get(i).getEnd_date() + "일";
			data[i][7] = list.get(i).getReason();
		}

		DefaultTableModel model = new DefaultTableModel(data, column);
		table_3 = new JTable(model); //
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		table_3.setRowHeight(25);
		scroll_ing.setViewportView(table_3);

		btnNewButton = new JButton("\uBD88\uD5C8");
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				// 행삭제 해주는기능삽입

			}
		});
		btnNewButton.setBounds(10, 287, 97, 23);
		panel_3.add(btnNewButton);

		confirm = new JButton("\uD655\uC815");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// 확정 버튼 누르면

				ArrayList<SubmitVO> vacList2 = dao.vacSelect();
				ArrayList<SubmitVO> outList2 = dao.outSelect();
				ArrayList<SubmitVO> nightList2 = dao.nightSelect();
				Object[][] data6 = new Object[vacList2.size()][33];
				Object[][] data5 = new Object[nightList2.size()][33];
				Object[][] data4 = new Object[outList2.size()][33];
				for (int i = 0; i < vacList2.size(); i++) {
					data6[i][0] = vacList2.get(i).getName();
					data6[i][1] = vacList2.get(i).getMil_id();
					for (int j = 2, k = 1; j < 33; j++, k++) {
						data6[i][j] = k;
					}
				}
				for (int i = 0; i < outList2.size(); i++) {
					data4[i][0] = outList2.get(i).getName();
					data4[i][1] = outList2.get(i).getMil_id();
					for (int j = 2, k = 1; j < 33; j++, k++) {
						data4[i][j] = k;
					}
				}
				for (int i = 0; i < nightList2.size(); i++) {
					data5[i][0] = nightList2.get(i).getName();
					data5[i][1] = nightList2.get(i).getMil_id();
					for (int j = 2, k = 1; j < 33; j++, k++) {
						data5[i][j] = k;
					}
				}

				DefaultTableModel modelOut = new DefaultTableModel(data4, column1);
				DefaultTableModel modelNight = new DefaultTableModel(data5, column1);
				DefaultTableModel modelVac = new DefaultTableModel(data6, column1);

				table_6.setDefaultRenderer(Object.class, new CellRendererVac(vacList));
				table_4.setDefaultRenderer(Object.class, new CellRendererOut(outList));
				table_5.setDefaultRenderer(Object.class, new CellRendererNight(nightList));

				table_4.setModel(modelOut);
				table_5.setModel(modelNight);
				table_6.setModel(modelVac);

				// insertAllowAllow를 통해서 Allow (허가) 데이터 insert 해주기
				for (int i = 0; i < list.size(); i++) {
					int sub_number = list.get(i).getSub_number();
					String yn = "Y";
					AllowVO vo = new AllowVO(manager_id, sub_number, yn, null);
					AllowDAO dao = new AllowDAO();
					int count = dao.insertAllow(vo);
					if (count < 0) {
						JOptionPane.showMessageDialog(null, "허가 오류", "오류", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		confirm.setBounds(698, 287, 97, 23);
		panel_3.add(confirm);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 행삭제 해주는기능삽입

				int row = table_3.getSelectedRow();
				int sub_number = list.get(row).getSub_number();
				model.removeRow(table_3.getSelectedRow());
				new Reject(manager_id, sub_number);

			}
		});

	}

	private static void refreshCalendar(int month, int year) {
		// Variables
		String[] months = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
		int nod, som; // Number Of Days, Start Of Month

		// Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear - 10) {
			btnPrev.setEnabled(false);
		} // 0월이고 10년전치거 못 봄
		if (month == 11 && year >= realYear + 100) {
			btnNext.setEnabled(false);
		} // 100년 이상 못봄
		lblMonth.setText(months[month]);

		cmbYear.setSelectedItem(String.valueOf(year)); // 알맞는 해 고르기

		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
			}
		}
		// 그 달의 첫째날
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// 캘린더 그리기
		for (int i = 1; i <= nod; i++) {
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);
		}

		// Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), (TableCellRenderer) new tblCalendarRenderer());
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer { // 색깔 표현해주는 곳 !!!

		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			setBackground(new Color(255, 255, 255));
			if (value != null) {
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth
						&& currentYear == realYear) { // Today
					setBackground(new Color(245, 255, 250)); // 오늘은 퍼런색으로 !
				}
				if (realDay - 15 <= Integer.parseInt(value.toString())
						&& realDay - 10 >= Integer.parseInt(value.toString()) && currentMonth == realMonth
						&& currentYear == realYear) {
					setBackground(new Color(221, 221, 255));
				}
			}

			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	static class btnPrev_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0) { // Back one year
				currentMonth = 11;
				currentYear -= 1;
			} else { // Back one month
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	static class btnNext_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11) { // Foward one year
				currentMonth = 0;
				currentYear += 1;
			} else { // Foward one month
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	static class cmbYear_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (cmbYear.getSelectedItem() != null) {
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}

		}
	}

}