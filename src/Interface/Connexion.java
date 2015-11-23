package Interface;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import LienBD.Personnel;
import LienBD.Restaurant;

@SuppressWarnings("serial")
public class Connexion extends JFrame {

	private JLabel nomLabel, pwdLabel;
	private JTextField nomTextField;
	private JPasswordField pwdTextField;
	private JButton valider;
	private File config;
	
	public Connexion() {
		this.setTitle("Connexion");
		// this.setSize(300,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2, 2, 2));

		// Ajout du label et du champ de saisie pour l'identifiant
		this.nomLabel = new JLabel();
		this.nomLabel.setText("Identifiant");
		this.nomTextField = new JTextField(3);
		panel1.add(this.nomLabel);
		panel1.add(this.nomTextField);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2, 2, 5));

		// Ajout du label et du champ de saisie pour le mot de passe
		this.pwdLabel = new JLabel();
		this.pwdLabel.setText("Mot de passe");
		this.pwdTextField = new JPasswordField(3);
		panel2.add(this.pwdLabel);
		panel2.add(this.pwdTextField);

		JPanel panel3 = new JPanel();
		
		ActionListener ouverture = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openPassFrame();
			}
		};

		this.valider = new JButton();
		this.valider.setText("Valider");

		// Ajout d'un ActionListener au bouton valider afin d'ouvrir la
		// PassFrame en cliquant sur valider
		this.valider.addActionListener(ouverture);
		this.pwdTextField.addActionListener(ouverture);

		panel3.add(valider);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);

		this.pack();
	}

	@SuppressWarnings("deprecation")
	private int verifConnexion() {
		int res = 0;
		if (this.nomTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Entrez votre identifiant");
			res = 0;
		} else if (this.pwdTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Entrez votre mot de passe");
			res = 0;
		} else {
			String idSearched = this.nomTextField.getText().toUpperCase();
			String pwdSearched = this.pwdTextField.getText();
			res = Personnel.verifCo(idSearched, pwdSearched);
			if (res < 0) {
				JOptionPane.showMessageDialog(this, "connexion echoué");
			}
		}
		return res;
	}

	private void openPassFrame() {
		int id = verifConnexion();
		try {
			if (id > 0) {
				PassFrame passframe = new PassFrame((int) Personnel.getDroitsById(id));
				Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
	            passframe.setBounds(bounds);
				passframe.setVisible(true);
				Connexion.this.dispose();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
/*
	private void firstConnection() throws IOException{
		String path = "/home/Projets/infs3_prj03/config.txt";
		File file = new File(path);
		try{
			if(!file.exists())
			BufferedReader buf = new BufferedReader(new FileReader(file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/home/Projets/infs3_prj03/config.txt")));
			int fin = 0;
          // on lit le fichier caractère par caractère
          while ((fin = buf.read()) != -1) {
        	  
          }
        	  
          }
	}
*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Connexion connexion = new Connexion();
				connexion.setVisible(true);
			}
		});
	}
}
