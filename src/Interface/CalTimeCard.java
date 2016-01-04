package Interface;

import javax.swing.JButton;

import Tools.GraphicCalendar;

public class CalTimeCard extends GraphicCalendar {

	public CalTimeCard(int year, int month) {
		super(year, month);
		for (JButton jButton : buttonsDay) {
			jButton.addActionListener(e ->{
				
			});
		}
	}

}
