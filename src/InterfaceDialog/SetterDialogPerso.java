package InterfaceDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.jdom2.Document;

import Interface.CalTimeCard;
import LienBD.Personnel;
import LienBD.Restaurant;
import LienBD.Salles;
import Tools.JDom;
import resteau.config.ResteauConfig;

@SuppressWarnings("serial")
/**
 * Fenetre pop-up permetant de definir les information sur un objet de type
 * LienBD.Personnel
 * 
 * @author Benjamin
 *
 */
public class SetterDialogPerso extends JDialog {
	private JLabel nomPerso, prenomPerso, adressePerso, numTelPerso, mailPerso, postePerso, sallePerso,
			horairePrevPersoB, horairePrevPersoE, salairePerso, mdpPerso, restoPerso, droitPerso , erreur;
	private JTextField nom, prenom, adresse, numTel, mail, poste, salaire;
	private JSpinner droit, minutePrevB, heurePrevB, minutePrevE, heurePrevE;
	private JComboBox<Restaurant> resto;
	private JComboBox<Salles> salle;
	private JPasswordField mdp;
	private Personnel perso;
	private GridLayout grid = new GridLayout(1, 2);

	public SetterDialogPerso(Frame owner, boolean modal, Personnel perso) {
		super(owner, modal);
		this.perso = perso;
		
		this.erreur = new JLabel("");
		this.add(erreur);
		this.erreur.setBackground(Color.red);
		// configuration des attributs
		this.nomPerso = new JLabel("Nom : ");
		if (perso != null) {
			this.nom = new JTextField(perso.getNOM());
		} else {
			this.nom = new JTextField();
		}
		JPanel all = new JPanel();
		JPanel infoPerso = new JPanel(new GridLayout(4, 1));
		JScrollPane scroll = new JScrollPane(all);
		JPanel boxNom = new JPanel();
		boxNom.setLayout(this.grid);
		boxNom.add(this.nomPerso);
		boxNom.add(this.nom);
		infoPerso.add(boxNom);

		this.prenomPerso = new JLabel("Pr�nom : ");
		if (perso != null) {
			this.prenom = new JTextField(perso.getPRENOM());
		} else {
			this.prenom = new JTextField();
		}
		JPanel boxPrenom = new JPanel();
		boxPrenom.setLayout(this.grid);
		boxPrenom.add(this.prenomPerso);
		boxPrenom.add(this.prenom);
		infoPerso.add(boxNom);

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
		infoPerso.add(boxAdresse);

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
		infoPerso.add(boxTel);

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
		infoPerso.add(boxMail);

		JPanel workPanel = new JPanel(new GridLayout(4, 1));

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
		workPanel.add(boxPoste);

		this.restoPerso = new JLabel("Restaurtant");
		this.resto = new JComboBox<Restaurant>(Restaurant.getAll());

		JPanel boxResto = new JPanel();
		boxResto.setLayout(this.grid);
		boxResto.add(this.restoPerso);
		boxResto.add(this.resto);
		workPanel.add(boxResto);

		this.sallePerso = new JLabel("Salle de Travail : ");
		try {
			this.salle = new JComboBox<Salles>(Salles.getAll());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel boxSalle = new JPanel();
		boxSalle.setLayout(this.grid);
		boxSalle.add(this.sallePerso);
		boxSalle.add(this.salle);
		workPanel.add(boxSalle);

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
		workPanel.add(boxSalaire);

		this.mdpPerso = new JLabel("Mot de Passe : ");
		this.mdp = new JPasswordField();

		JPanel logPerso = new JPanel(new GridLayout(2, 1));

		JPanel boxMdp = new JPanel();
		boxMdp.setLayout(this.grid);
		boxMdp.add(this.mdpPerso);
		boxMdp.add(this.mdp);
		logPerso.add(boxMdp);

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
		logPerso.add(boxDroit);

		JButton valider = new JButton("Valider");
		valider.addActionListener(e -> {
				if(verif()){
					this.dispose();
				}
		});
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(e -> this.dispose());
		JPanel bouton = new JPanel();
		bouton.setLayout(grid);
		bouton.add(valider);
		bouton.add(annuler);

		JSplitPane splitp = new JSplitPane();

		this.setLayout(new BorderLayout());
		all.setLayout(new GridLayout(3, 1));
		all.add(infoPerso);
		all.add(workPanel);
		all.add(logPerso);
		splitp.setLeftComponent(all);

		// recuperation des elements pour la fiche horaire heure|Minute arriver
		Calendar cal = Calendar.getInstance();
		JPanel panelCal = new JPanel(new BorderLayout());
		CalTimeCard calG = null;
		if (this.perso != null) {
			calG = new CalTimeCard(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), this.perso.getHORAIREPREV());
		} else {
			try {
				this.perso =new Personnel(ResteauConfig.getResteauID(),0, "tmp", null, null, null, null, null, null, null, 0, 1,null);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			calG = new CalTimeCard(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), this.perso.getHORAIREPREV());
		}
		panelCal.add(calG, BorderLayout.CENTER);
		JLabel calLabel = new JLabel("Gestion horaire : ");
		panelCal.add(calLabel, BorderLayout.NORTH);
		splitp.setRightComponent(panelCal);

		this.add(bouton, BorderLayout.SOUTH);
		this.add(splitp, BorderLayout.CENTER);
		this.pack();
	}

	public static Personnel showContactDialog(Frame parent, String title, Personnel personne) {
		SetterDialogPerso perso = new SetterDialogPerso(parent, true, personne);
		perso.setVisible(true);
		perso.setTitle(title);
		Personnel retour = perso.perso;
		return retour;
	}

	public boolean verif() {
		boolean retour= true;
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
		float salairePerso = 0;
		try{
			salairePerso = Float.parseFloat(this.salaire.getText());
		}catch(NumberFormatException e){
			this.erreur.setText("Le format du salaire ne convient pas.");
			this.pack();
			retour = false;
		}		
		int droitPerso = (int) this.droit.getValue();
		String mdpPerso = this.mdp.getText(); 
		if(nomPerso.equals("")){
			this.erreur.setText("Le format du nom ne convient pas.");
			this.pack();
			retour = false;
		}
		if(this.perso != null){
			try {
				this.perso.delete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.perso = new Personnel(numResto, numSalle, nomPerso, prenomPerso, postePerso, adressPerso, numTelPerso,
				mailPerso, null, null, salairePerso, droitPerso, mdpPerso);
		
		return retour;
	}
}
