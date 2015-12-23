package InterfaceDialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;

import LienBD.Personnel;
import LienBD.Restaurant;
import LienBD.Salles;

public class SetterDialogPerso extends JDialog {
	private JLabel nomPerso, prenomPerso, adressePerso, numTelPerso, mailPerso, postePerso, sallePerso,
			horairePrevPerso, salairePerso, mdpPerso, restoPerso, droitPerso;
	private JTextField nom, prenom, adresse, numTel, mail, poste, salaire;
	private JSpinner droit, minutePrev, heurePrev;
	private JComboBox<Restaurant> resto;
	private JComboBox<Salles> salle;
	private JPasswordField mdp;
	private Personnel perso;
	private GridLayout grid = new GridLayout(1, 2);

	public SetterDialogPerso(Frame owner, boolean modal, Personnel perso) {
		super(owner, modal);
		// configuration des attributs
		this.nomPerso = new JLabel("Nom : ");
		if (perso != null) {
			this.nom = new JTextField(perso.getNOM());
		} else {
			this.nom = new JTextField();
		}
		JPanel boxNom = new JPanel();
		boxNom.setLayout(this.grid);
		boxNom.add(this.nomPerso);
		boxNom.add(this.nom);

		this.prenomPerso = new JLabel("Prénom : ");
		if (perso != null) {
			this.prenom = new JTextField(perso.getPRENOM());
		} else {
			this.prenom = new JTextField();
		}
		JPanel boxPrenom = new JPanel();
		boxPrenom.setLayout(this.grid);
		boxPrenom.add(this.prenomPerso);
		boxPrenom.add(this.prenom);

		this.adressePerso = new JLabel("Adresse : ");
		if (perso != null) {
			this.adresse = new JTextField(perso.getADRESSE());
		} else {
			this.adresse = new JTextField();
		}
		JPanel boxAdresse = new JPanel();
		boxAdresse.setLayout(this.grid);
		boxAdresse.add(this.adressePerso);
		boxAdresse.add(this.adresse);

		this.numTelPerso = new JLabel("Téléphone : ");
		if (perso != null) {
			this.numTel = new JTextField(perso.getNUMTEL());
		} else {
			this.numTel = new JTextField();
		}
		JPanel boxTel = new JPanel();
		boxTel.setLayout(this.grid);
		boxTel.add(this.numTelPerso);
		boxTel.add(this.numTel);

		this.mailPerso = new JLabel("Adresse Mail : ");
		if (perso != null) {
			this.mail = new JTextField(perso.getADRESSEMAIL());
		} else {
			this.mail = new JTextField();
		}
		JPanel boxMail = new JPanel();
		boxMail.setLayout(this.grid);
		boxMail.add(this.mailPerso);
		boxMail.add(this.mail);

		this.postePerso = new JLabel("Poste Occupé : ");
		if (perso != null) {
			this.poste = new JTextField(perso.getPOSTE());
		} else {
			this.poste = new JTextField();
		}
		JPanel boxPoste = new JPanel();
		boxPoste.setLayout(this.grid);
		boxPoste.add(this.postePerso);
		boxPoste.add(this.poste);

		this.restoPerso = new JLabel("Restaurtant");
		this.resto = new JComboBox<Restaurant>(Restaurant.getAll());

		JPanel boxResto = new JPanel();
		boxResto.setLayout(this.grid);
		boxResto.add(this.restoPerso);
		boxResto.add(this.resto);

		this.sallePerso = new JLabel("Salle de Travail : ");
		this.salle = new JComboBox<Salles>(Salles.getAll());

		JPanel boxSalle = new JPanel();
		boxSalle.setLayout(this.grid);
		boxSalle.add(this.sallePerso);
		boxSalle.add(this.salle);

		this.horairePrevPerso = new JLabel("Horaires Prévus : Heure|Minute");
		this.minutePrev = new JSpinner();
		NumberEditor numberEditorM = new NumberEditor(minutePrev);
		this.minutePrev.setEditor(numberEditorM);
		numberEditorM.getModel().setMaximum(60);
		numberEditorM.getModel().setMinimum(0);
		this.heurePrev = new JSpinner();
		NumberEditor numberEditorH = new NumberEditor(heurePrev);
		this.heurePrev.setEditor(numberEditorH);
		numberEditorH.getModel().setMaximum(24);
		numberEditorH.getModel().setMinimum(0);
		JPanel boxHorPrev = new JPanel();
		boxHorPrev.setLayout(new GridLayout(1, 3));
		boxHorPrev.add(this.horairePrevPerso);
		boxHorPrev.add(this.heurePrev);
		boxHorPrev.add(this.minutePrev);

		this.salairePerso = new JLabel("Salaire : ");
		if (perso != null) {
			this.salaire = new JTextField("" + perso.getSALAIRE_H());
		} else {
			this.salaire = new JTextField();

		}
		JPanel boxSalaire = new JPanel();
		boxSalaire.setLayout(this.grid);
		boxSalaire.add(this.salairePerso);
		boxSalaire.add(this.salaire);

		this.mdpPerso = new JLabel("Mot de Passe : ");
		if (this.perso != null) {
			this.mdp = new JPasswordField();
		} else {
			this.mdp = new JPasswordField();
		}
		JPanel boxMdp = new JPanel();
		boxMdp.setLayout(this.grid);
		boxMdp.add(this.mdpPerso);
		boxMdp.add(this.mdp);

		this.droitPerso = new JLabel("Droit : ");
		this.droit = new JSpinner();
		JSpinner.NumberEditor spinnerEditor2 = new JSpinner.NumberEditor(droit);
		droit.setEditor(spinnerEditor2);
		spinnerEditor2.getModel().setMinimum(0);
		spinnerEditor2.getModel().setMaximum(1);
		JPanel boxDroit = new JPanel();
		boxDroit.setLayout(this.grid);
		boxDroit.add(droitPerso);
		boxDroit.add(droit);

		JButton valider = new JButton("Valider");
		valider.addActionListener(e -> {
			if (this.perso == null) {
				create();
			} else {
				update();
			}
			this.dispose();
		});
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(e -> this.dispose());
		JPanel bouton = new JPanel();
		bouton.setLayout(grid);
		bouton.add(valider);
		bouton.add(annuler);

		this.setLayout(new GridLayout(14, 1));
		this.add(boxNom);
		this.add(boxPrenom);
		this.add(boxAdresse);
		this.add(boxTel);
		this.add(boxMail);
		this.add(boxPoste);
		this.add(boxResto);
		this.add(boxSalle);
		this.add(boxHorPrev);
		this.add(boxSalaire);
		this.add(boxMdp);
		this.add(boxDroit);
		this.add(bouton);
		this.pack();
		this.minutePrev.setSize(this.minutePrev.getWidth() / 2, this.minutePrev.getHeight());
		this.heurePrev.setSize(this.heurePrev.getWidth() / 2, this.heurePrev.getHeight());
		this.pack();
	}

	public static Personnel showContactDialog(Frame parent, String title, Personnel personne) {
		SetterDialogPerso perso = new SetterDialogPerso(parent, true, personne);
		perso.setVisible(true);
		perso.setTitle(title);
		Personnel retour = perso.perso;
		return retour;
	}

	public void update() {
		char[] mdp = this.mdp.getPassword();
		String s = new String(mdp);
		this.perso.modif(s);
	}

	public void create() {
		int minute = (int) this.minutePrev.getValue();
		int heure = (int) this.heurePrev.getValue();
		Restaurant Resto = (Restaurant) this.resto.getSelectedItem();
		int numResto = Resto.getNumResto();
		Salles Salle = (Salles) this.salle.getSelectedItem();
		int numSalle = Salle.getNumSalle();
		String nomPerso = this.nom.getText();
		String prenomPerso = this.prenom.getText();
		String postePerso = this.poste.getText();
		String adressPerso = this.adresse.getText();
		String numTelPerso = this.numTel.getText();
		String mailPerso = this.mail.getText();
		float salairePerso = Float.parseFloat(this.salaire.getText());
		int droitPerso = (int) this.droit.getValue();
		String mdpPerso = this.mdp.getSelectedText();
		this.perso = new Personnel(numResto, numSalle, nomPerso, prenomPerso, postePerso, adressPerso, numTelPerso,
				mailPerso, null, null, salairePerso, droitPerso, mdpPerso);
	}
}
