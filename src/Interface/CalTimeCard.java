package Interface;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
				Calendar cal = new GregorianCalendar(this.currentYear, this.currentMonth, buttonsDay.indexOf(jButton)+1);
				SetterDialogDate.showContactDialog(null, "Horaire pour le : " + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.DAY_OF_MONTH) , cal,timecard);
			});
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Date d = new Date(2016, 01, 13);
		System.out.println(d.getYear());
		System.out.println(d.getMonth());
		System.out.println(d.getDate());
	}

}
