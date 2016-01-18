package Interface.list;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import LienBD.Etat;
import LienBD.Reservation;
import LienBD.Salles;

public class ReservationRenderer implements ListCellRenderer<Reservation> {

	private JLabel label;

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Reservation> list, Reservation value, int index,
			boolean isSelected, boolean cellHasFocus) {

		int numRes = value.getNumReservation();
		int numTable = value.getNumTable();
		Date dateRes = value.getDateReservation();
		String nomClient = value.getNomClient();
		String prenomClient = value.getPrenomClient();
		int nbPers = value.getNbPers();

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		if (dateRes.equals(cal.getTime())) {
			this.label.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		}
		this.label = new JLabel();
		this.label.setText("Réservation n° " + numRes + " :  " + nomClient.toUpperCase() + " " + prenomClient + "        " + nbPers
				+ " personnes" + "        " + "Table n° : " + numTable + "      le " + dateRes /*+ "   a " + heure + ":" + minutes*/);

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
		return this.label;
	}

}
