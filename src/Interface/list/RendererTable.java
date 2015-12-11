package Interface.list;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import LienBD.Table;
import LienBD.Etat;

public class RendererTable implements ListCellRenderer<Table> {
	
	private JLabel label;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Table> list,
			Table value, int index, boolean isSelected, boolean cellHasFocus) {
		
		int capaciter = value.getCapacite();
		Etat etatTable = value.getEtat();
		
		if (etatTable.equals(Etat.horsservice)) {
			this.label.setIcon(new ImageIcon(getClass().getResource(
					"/img/tableR.png")));
		} else if (etatTable.equals(Etat.libre)) {
			this.label.setIcon(new ImageIcon(getClass().getResource(
					"/img/tableV.png")));
		}
		
		this.label.setText("" + capaciter);
		
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
