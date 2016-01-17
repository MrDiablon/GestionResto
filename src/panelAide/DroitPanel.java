package panelAide;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class DroitPanel extends JPanel {
	private JTextPane text;
	
	public DroitPanel(){
		String txt = "Les droit permette de controller l'acces à l'application tel que :\r\n"
				+ "\tServeur : acces au salles,table et tous ce qui est neccessaire pour faire le service \r\n"
				+ "\tCuisinier : acces au stock (lecture,ecriture)\r\n"
				+ "\tAdministratif : acces à toute l'application";
		this.text = new  JTextPane();
		this.text.setText(txt);
		this.add(text);
	}
}
