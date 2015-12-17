package Interface.list;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import LienBD.Menu;

public class MenuRenderer implements ListCellRenderer<Menu> {

	private JLabel label;

	@Override
	public Component getListCellRendererComponent(JList<? extends Menu> list,
			Menu value, int index, boolean isSelected, boolean cellHasFocus) {
		this.label = new JLabel();
		String text = value.getNom();

		this.label.setText(text);
		
		if(isSelected){
			this.label.setBackground(list.getSelectionBackground());
			this.label.setForeground(list.getSelectionForeground());
			this.label.setOpaque(true);
			String composition = value.getComposition();
			composition = composition.replace("|", " : ");
			text += "\n\r - " + composition ; 
			this.label.setText(text);
		}else{
			this.label.setBackground(list.getBackground());
			this.label.setForeground(list.getForeground());
			if(!this.label.getText().equals(text)){
				this.label.setText(text);
			}
		}
		
		if(cellHasFocus){
			this.label
			.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}else{
			this.label.setBorder(null);
		}

		return this.label;
	}

}
