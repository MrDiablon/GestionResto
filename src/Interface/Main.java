package Interface;

import java.awt.GraphicsEnvironment;
import javax.swing.SwingUtilities;

import LienBD.myPDO;

public class Main {

	public static void main(String[] args) {
		myPDO.configure("infs3_prj03", "k4t28jb2", "jdbc:mysql://mysql/infs3_prj03");
		//myPDO.configure("root", "", "jdbc:mysql://localhost/projets3");
		SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	            Connexion f = new Connexion();
	            f.setVisible(true);
	         }
	      });
	}

}
