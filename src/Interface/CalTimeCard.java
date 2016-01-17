package Interface;

import java.util.Date;

import javax.swing.JButton;

import org.jdom2.Document;
import InterfaceDialog.SetterDialogDate;
import Tools.GraphicCalendar;

public class CalTimeCard extends GraphicCalendar {

	@SuppressWarnings("deprecation")
	public CalTimeCard(int year, int month,Document timecard) {
		super(year, month);
		for (JButton jButton : buttonsDay) {
			jButton.addActionListener(e ->{
				Date d = new Date(this.currentYear, this.currentMonth, buttonsDay.indexOf(jButton)+1);
				System.out.println(d.getYear() + "/" + this.currentYear );
				SetterDialogDate.showContactDialog(null, "Horaire pour le : " + d.getDay() + "/" + (d.getMonth()+1) + "/" + d.getDate() , d,timecard);
			});
		}
	}

}
