package kr.hr.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import kr.hr.model.SubmitVO;

public class CellRendererOut extends DefaultTableCellRenderer {
	ArrayList<SubmitVO> list;
	int cnt = 0;

	public CellRendererOut(ArrayList<SubmitVO> list) {

		this.list = list;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean

	isSelected, boolean hasFocus, int row, int column) {

		// int start= �ް������� or int end =�ް������� �̷��� �޾ƿ��� �;��
		// int name = ��û�� ��� �� �ε���, ��û�� ����� ����� �̰�,�ϰ�,���,����.
		//

		Color color = null;
		Component cell = null;

		for (int j = 0; j < list.size(); j++) {

			switch (list.get(j).getRank()) {
			case "�̰�":
				color = new Color(255, 255, 0);
				break;
			case "�ϰ�":
				color = new Color(255, 165, 0);
				break;
			case "���":
				color = new Color(107, 142, 35);
				break;
			case "����":
				color = new Color(65, 105, 225);
			}
			cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if ((column > list.get(j).getSub_date()) && (column < list.get(j).getEnd_date() + 1) && (row == j)
					&& (column > 2)) {
				System.out.println("out�� ���������?");
				cell.setBackground(color);
				break;
				// }
			} else {
				cell.setBackground(new Color(255, 255, 255));
			}
		}
		return cell;
	}

}