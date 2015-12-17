package InterfaceDialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import LienBD.Menu;
import LienBD.Plat;

public class SetterDialogMenu extends JDialog {

	private JLabel nom, nbPlat, nomPlat, prix, symbole€, erreur = new JLabel();
	private JTextField saisieNom, saisieprix;
	private JSpinner nbPlatS;
	private int nbPlatsPres;
	private JPanel panelPlats;
	private LinkedList<JComboBox<Plat>> listeBoxPlats;
	private JComboBox<Plat> listeplats;
	private Menu menu;
	private JButton valider, annuler;

	public SetterDialogMenu(Frame owner, boolean modal, Menu menu) {
		super(owner, modal);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.menu = menu;
		
		this.listeBoxPlats = new LinkedList<>();
		// configuration de la partie "nom"
		this.nom = new JLabel("Nom : ");
		this.saisieNom = new JTextField(10);
		if (menu != null) {
			this.saisieNom.setText(menu.getNom());
		}

		JPanel nomPanel = new JPanel();
		nomPanel.add(this.nom);
		nomPanel.add(this.saisieNom);
		this.add(nomPanel);
		
		// configuration de la partie "plat"
		this.nbPlat = new JLabel("Nombre de plats");
		this.nbPlatS = new JSpinner();
		this.nbPlatS.addChangeListener(e -> this.add());
		JPanel panelNbPlat = new JPanel();		
		panelNbPlat.add(this.nbPlat);
		panelNbPlat.add(this.nbPlatS);
		this.panelPlats = new JPanel(new GridLayout(0, 2));
		this.add(panelNbPlat);
		this.add(this.panelPlats);
		
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			if(this.updateMenu()){
				this.dispose();
			}
		});
		this.annuler = new JButton("Annuller");
		this.annuler.addActionListener(e -> this.dispose());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(valider);
		buttonPanel.add(annuler);
		this.add(buttonPanel);
		
		this.pack();
		
	}
	
	public boolean updateMenu(){
		boolean retour = true;
		String nom = this.saisieNom.getText();
		int nbPlat = this.listeBoxPlats.size();
		//int this.
		if(this.menu == null){
			
		}
		return retour;
	}
	
	public void add(){
		int value = (int) this.nbPlatS.getValue();
		if(value > 0){
			Plat[] plats = Plat.getAll();
			this.nomPlat = new JLabel("Plat : ");
			this.listeplats = new JComboBox<Plat>(plats);
			JPanel listeplat = new JPanel();
			listeplat.add(this.nomPlat);
			listeplat.add(this.listeplats);
			this.panelPlats.add(listeplat);
			this.listeBoxPlats.add(this.listeplats);
			
			this.pack();
		}
	}
	
	public static Menu showContactDialog(Frame parent, String title,
			Menu menu) {
		SetterDialogMenu menuDialog = new SetterDialogMenu(parent, true, menu);
		menuDialog.setTitle(title);
		menuDialog.setVisible(true);
		Menu retour = menuDialog.menu;
		
		return retour;
	}
}
