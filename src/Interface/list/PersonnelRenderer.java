package Interface.list;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import LienBD.Etat;
import LienBD.Personnel;
import LienBD.Salles;
import LienBD.Table;

public class PersonnelRenderer implements ListCellRenderer<Personnel> {
	private JLabel label;

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Personnel> list, Personnel perso, int index,
			boolean isSelected, boolean cellHasFocus) {
		this.label = new JLabel();
		this.label.setText(perso.getPRENOM() + " " + perso.getNOM());

		// si l'element est selectionner on change l'affichage pour bien le
		// montré
		if (isSelected) {
			this.label.setBackground(list.getSelectionBackground());
			this.label.setForeground(list.getSelectionForeground());
			this.label.setOpaque(true);
		} else {
			// sinon on met un affichage normal
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