package Interface.list;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import LienBD.Reservation;

public class ReservationRenderer implements ListCellRenderer<Reservation> {

	private JLabel label;

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Reservation> list, Reservation value, int index,
			boolean isSelected, boolean cellHasFocus) {

		int numRes = value.getNumReservation();
		Date dateRes = value.getDateReservation();
		String nomClient = value.getNomClient();
		int nbPers = value.getNbPers();

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		if (dateRes.equals(cal.getTime())) {
			this.label.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		}

		this.label.setText(numRes + " : " + nomClient + " (" + nbPers
				+ "personnes" + ") ");

		if (isSelected) {
			this.label.setBackground(list.getSelectionBackground());
			this.label.setForeground(list.getSelectionForeground());
			this.label.setOpaque(true);
		} else {
			this.label.setBackground(list.getBackground());
			this.label.setForeground(list.getForeground());
		}
		// regarde si l'element est focus et si oui on affiche le focus
		if (cellHasFocus) {
			this.label
					.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		} else {
			// sinon on le retire
			this.label.setBorder(null);
		}
		return null;
	}

}
