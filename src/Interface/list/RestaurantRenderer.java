package Interface.list;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import LienBD.Restaurant;

public class RestaurantRenderer implements ListCellRenderer<Restaurant> {

	private JLabel label;
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Restaurant> list, Restaurant value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		String nomResto = value.getNomResto();
		value.getMarge();
		value.getNbSalles();
		value.getNbEmployes();
		String adresse = value.getAdresse();
		String pays = value.getPays();
		String numTel = value.getNumTel();
		value.getVille();
		value.getCp();

		this.label.setText(nomResto + " : " + adresse + " (" + pays 
				+ ") " + numTel);

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
