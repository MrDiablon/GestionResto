package InterfaceDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import LienBD.Restaurant;

public class SetterDialogResto extends JDialog {
	JLabel nom, adresse, pays, numtel, ville, cp, erreur;
	JTextField nomS, margeS, adresseS, paysS, numtelS, villeS, cpS;
	JButton valider, annuler;
	Restaurant resto;

	public static void main(String[] args) {
		showContactDialog(null, "test", null);
	}

	public SetterDialogResto(Frame owner, boolean modal, Restaurant resto) {
		super(owner, modal);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.resto = resto;

		// configurationd de erreur
		this.erreur = new JLabel("");
		this.erreur.setForeground(Color.red);
		this.add(this.erreur);

		// configuration de la partie nom
		JPanel nomP = new JPanel(new BorderLayout());
		this.nom = new JLabel("Nom du Restaurant* : ");
		this.nomS = new JTextField(10);
		nomP.add(this.nom, BorderLayout.WEST);
		nomP.add(this.nomS, BorderLayout.CENTER);
		this.add(nomP);

		// configuration de la partie adresse
		JPanel adrP = new JPanel(new BorderLayout());
		this.adresse = new JLabel("Adresse* : ");
		this.adresseS = new JTextField(10);
		adrP.add(this.adresse, BorderLayout.WEST);
		adrP.add(this.adresseS, BorderLayout.CENTER);
		this.add(adrP);

		// configuration de la partie pays
		JPanel paysP = new JPanel(new BorderLayout());
		this.pays = new JLabel("Pays* : ");
		this.paysS = new JTextField(10);
		paysP.add(this.pays, BorderLayout.WEST);
		paysP.add(this.paysS, BorderLayout.CENTER);
		this.add(paysP);

		// configuration de la partie ville
		JPanel villeP = new JPanel(new BorderLayout());
		this.ville = new JLabel("Ville* : ");
		this.villeS = new JTextField(10);
		villeP.add(this.ville, BorderLayout.WEST);
		villeP.add(this.villeS, BorderLayout.CENTER);
		this.add(villeP);

		// configuration de la partie CP
		JPanel cpP = new JPanel(new BorderLayout());
		this.cp = new JLabel("Code Postal* : ");
		this.cpS = new JTextField(10);
		cpP.add(this.cp, BorderLayout.WEST);
		cpP.add(this.cpS, BorderLayout.CENTER);
		this.add(cpP);

		// configuration de la partie numTel
		JPanel numP = new JPanel(new BorderLayout());
		this.numtel = new JLabel("Numéro de téléphone : ");
		this.numtelS = new JTextField(10);
		numP.add(this.numtel, BorderLayout.WEST);
		numP.add(this.numtelS);
		this.add(numP);

		// configuration de la partie bouton
		JPanel buttonP = new JPanel();
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			if (setRestaurant()) {
				SetterDialogResto.this.dispose();
			}
		});
		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(e -> this.dispose());
		buttonP.add(this.valider);
		buttonP.add(this.annuler);
		this.add(buttonP);
		
		//si le resto n'est pas null on préremplie les champ
		if(this.resto != null){
			this.nomS.setText(this.resto.getNomResto());
			this.adresseS.setText(this.resto.getAdresse());
			this.paysS.setText(this.resto.getPays());
			this.numtelS.setText(this.resto.getNumTel());
			this.villeS.setText(this.resto.getVille());
			this.cpS.setText(this.resto.getCp());
		}

		this.pack();
	}

	public boolean erreur(String nom) {
		this.erreur.setText("Le champ " + nom + " doit être saisie");
		return false;
	}

	public boolean verifErreur(JTextField champ, String nom) {
		boolean retour = true;
		if (champ.getText().equals("")) {
			retour = erreur(nom);
			this.pack();
		}
		return retour;
	}

	public boolean setRestaurant() {
		boolean retour = true;
		if (verifErreur(this.nomS, "nom") && verifErreur(adresseS, "adresse") &&
				verifErreur(cpS, "code postal")	&& verifErreur(paysS, "pays") &&
				verifErreur(villeS, "ville")) {
			
			String nom = this.nomS.getText();
			String adr = this.adresseS.getText();
			String pays = this.paysS.getText();
			String num = this.numtelS.getText();
			String ville = this.villeS.getText();
			String cp = this.cpS.getText();
			
			if(this.resto == null){
				Restaurant newResto = new Restaurant(nom, 0, 0, 0, adr, pays, num, ville, cp);
			}else{
				this.resto.setAdresse(adr);
				this.resto.setCp(cp);
				this.resto.setNomResto(nom);
				this.resto.setNumTel(num);
				this.resto.setPays(pays);
				this.resto.setVille(ville);
			}
		}else{
			retour = false;
		}
		return retour;
	}

	public static Restaurant showContactDialog(Frame parent, String title, Restaurant resto) {
		SetterDialogResto set = new SetterDialogResto(parent, true, resto);
		set.setTitle(title);
		set.setVisible(true);
		return resto;
	}
}
