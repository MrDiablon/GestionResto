package Interface;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.security.NoSuchAlgorithmException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LienBD.Personnel;
import Tools.hashText;

public class NewPersonnelDialog extends JDialog{
		
		private JLabel nomLabel, prenomLabel, typeLabel, pwdLabel, adresseLabel;
		private JTextField nom, prenom, adresse, cp;
		private JPasswordField pwd;
		private JButton valide, annulle;
		private JComboBox<String> type;
		
		public NewPersonnelDialog(Frame owner, boolean modal){
			super(owner, modal);
			this.setTitle("Création");
			
			//panel pour le nom
			JPanel panelNom = new JPanel();
			this.nomLabel = new JLabel("Nom");
			this.nom = new JTextField(3);
			panelNom.add(this.nomLabel);
			panelNom.add(this.nom);
			panelNom.setLayout(new GridLayout(1, 0));
			
			//panel pour le prenom
			JPanel panelPwd = new JPanel();
			this.pwdLabel = new JLabel("mots de passe");
			this.pwd = new JPasswordField(3);
			panelPwd.add(this.pwdLabel);
			panelPwd.add(this.pwd);
			panelPwd.setLayout(new GridLayout(1, 0));
			
			//panel pour le type
			JPanel panelType = new JPanel();
			this.typeLabel = new JLabel("Type");
			String[] type = {"Choisissez un type","Employer","Gerant"};
			this.type = new JComboBox<String>(type);
			panelType.add(this.typeLabel);
			panelType.add(this.type);
			panelType.setLayout(new GridLayout(1, 0));
			
			
			//panel pour les bouton
			JPanel panelBouton = new JPanel();
			this.valide = new JButton("Validé");
			this.valide.addActionListener(e -> {
				this.createPersonnel();
			});
			this.annulle = new JButton("Annulé");
			this.annulle.addActionListener(e -> {
				this.dispose();
			});
			panelBouton.add(this.valide);
			panelBouton.add(this.annulle);
			panelBouton.setLayout(new GridLayout(1, 0));
			
			this.add(panelNom);
			this.add(panelPwd);
			this.add(panelType);
			this.add(panelBouton);
			this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
			this.pack();
			//configuration de la position;
			Rectangle bounds = owner.getBounds();
			Point location = bounds.getLocation();
			this.setLocation(location);
		}

		private void createPersonnel() {
			String nom = this.nom.getText();
			char[] pwdC = this.pwd.getPassword();
			String pwd = new String(pwdC);
			try {
				pwd = hashText.StringToSHA1(pwd);
			} catch (NoSuchAlgorithmException e) {
				JOptionPane.showMessageDialog(this.getContentPane(), "Un probléme est survenue");
			}
			int type = this.type.getSelectedIndex();
			if(nom.equals("")){
				JOptionPane.showMessageDialog(this.getContentPane(), "Veuillez renseigner le champ \"nom\"");
			}else if(pwd.equals("")){
				JOptionPane.showMessageDialog(this.getContentPane(), "Veuillez renseigner le champ \"mots de passe\"");
			}else if(type == 0){
				JOptionPane.showMessageDialog(this.getContentPane(), "Veuillez renseigner le champ \"type\"");
			}else{
				Personnel pers = new Personnel(0, 0, nom, null, null, null, null, null, null, null, 0, type, pwd);
				JOptionPane.showMessageDialog(this.getContentPane(), pers.getNOM() + " a été ajouter à la base de donnée au numero " + pers.getNUMPERSO());
				this.dispose();
			}
		}
}
