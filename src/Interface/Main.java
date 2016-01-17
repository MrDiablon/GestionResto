package Interface;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import InterfaceDialog.SetterDialogResto;
import LienBD.Restaurant;
import LienBD.myPDO;
import resteau.config.ResteauConfig;

public class Main {

	public static void main(String[] args) {
		myPDO.configure("infs3_prj03", "k4t28jb2", "jdbc:mysql://mysql/infs3_prj03");
		//myPDO.configure("root", "", "jdbc:mysql://localhost/projets3");
		if(!ResteauConfig.isSet()){
			JOptionPane.showConfirmDialog(null, "Lancement de la procédure de création d'un nouveau restaurant");
			Restaurant resto = SetterDialogResto.showContactDialog(null, "Création d'un restaurant", null);
			ResteauConfig.createConfig(resto.getNumResto());
		}
		SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            Connexion f = new Connexion();
	            f.setVisible(true);
	         }
	      });
	}
}
