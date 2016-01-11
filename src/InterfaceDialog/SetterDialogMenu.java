package InterfaceDialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import LienBD.Menu;
import LienBD.Plat;

public class SetterDialogMenu extends JDialog {

	private JLabel nom, nbPlat, nomPlat, prix, symbole, erreur = new JLabel();
	private JTextField saisieNom, saisieprix;
	private LinkedList<Plat> mealsList;
	private Menu menu;
	private JButton valider, annuler;

	public SetterDialogMenu(Frame owner, boolean modal, Menu menu) {
		super(owner, modal);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.menu = menu;
		if(menu != null){
			this.mealsList = menu.getPlats();
		}else{
			this.mealsList = new LinkedList<Plat>();
		}
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
		Plat[] meals = Plat.getAll();
		JPanel mealsP = new JPanel();
		mealsP.setLayout(new GridLayout(0,1));
		for (Plat plat : meals) {
			JCheckBox tmp = new JCheckBox(plat.getNomPlat()); 
			tmp.addActionListener(e->{
					add(plat);
			});
			if(menu != null){
				LinkedList<Plat> plats = menu.getPlats();
				if(plats.indexOf(plat) < 0){
					tmp.setSelected(true);
				}
			}
			mealsP.add(tmp);
		}
		JScrollPane scrollMeal = new JScrollPane(mealsP);
		this.add(scrollMeal);
		
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			try {
				if(this.updateMenu()){
					this.dispose();
				}
			} catch (Exception e1) {
				//this.erreur.setText("Une erreur est survenue");
				e1.printStackTrace();
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
	
	public void add(Plat meal){
		if(this.mealsList.contains(meal)){
			this.mealsList.remove(meal);
		}else{
			this.mealsList.add(meal);
		}
	}
	
	public boolean updateMenu() throws SQLException{
		boolean retour = false;
		String nom = this.saisieNom.getText();
		if(this.menu == null){
			Menu newMenu = new Menu(nom);
			for (Plat plat : mealsList) {
				newMenu.addPlat(plat);
			}
			retour = true;
		}else{
			this.menu.setNom(nom);
			this.menu.setPlats(this.mealsList);
			
			retour = true;
		}
		return retour;
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
