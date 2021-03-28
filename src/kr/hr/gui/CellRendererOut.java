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

		// int start= 휴가시작일 or int end =휴가종료일 이렇게 받아오고 싶어요
		// int name = 신청한 사람 행 인덱스, 신청한 사람의 계급은 이경,일경,상경,수경.
		//

		Color color = null;
		Component cell = null;

		for (int j = 0; j < list.size(); j++) {

			switch (list.get(j).getRank()) {
			case "이경":
				color = new Color(255, 255, 0);
				break;
			case "일경":
				color = new Color(255, 165, 0);
				break;
			case "상경":
				color = new Color(107, 142, 35);
				break;
			case "수경":
				color = new Color(65, 105, 225);
			}
			cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			if ((column > list.get(j).getSub_date()) && (column < list.get(j).getEnd_date() + 1) && (row == j)
					&& (column > 2)) {
				System.out.println("out은 몇번들어오니?");
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