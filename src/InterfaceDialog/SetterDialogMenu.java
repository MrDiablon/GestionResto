package InterfaceDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import LienBD.Menu;
import LienBD.Plat;

@SuppressWarnings("serial")
public class SetterDialogMenu extends JDialog {

	private JLabel nom, prix, symbole, erreur = new JLabel();
	private JTextField saisieNom, saisieprix;
	private LinkedList<Plat> mealsList;
	private Menu menu;
	private JButton valider, annuler;

	public SetterDialogMenu(Frame owner, boolean modal, Menu menu) {
		super(owner, modal);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.menu = menu;
		if (menu != null) {
			this.mealsList = menu.getPlats();
		} else {
			this.mealsList = new LinkedList<Plat>();
		}
		this.erreur = new JLabel("");
		this.erreur.setForeground(Color.red);
		this.add(erreur);
		// configuration de la partie "nom"
		this.nom = new JLabel("Nom : ");
		this.saisieNom = new JTextField();
		if (menu != null) {
			this.saisieNom.setText(menu.getNom());
		}

		JPanel nomPanel = new JPanel(new BorderLayout());
		nomPanel.add(this.nom,BorderLayout.WEST);
		nomPanel.add(this.saisieNom,BorderLayout.CENTER);
		this.add(nomPanel);

		// configuration de la partie "plat"
		Plat[] meals = Plat.getAll();
		JPanel mealsP = new JPanel();
		mealsP.setLayout(new GridLayout(0, 1));
		if (meals != null) {
			for (Plat plat : meals) {
				JCheckBox tmp = new JCheckBox(plat.getNomPlat());
				tmp.addActionListener(e -> {
					add(plat);
				});
				if (menu != null) {
					LinkedList<Plat> plats = menu.getPlats();
					if (plats.indexOf(plat) > 0) {
						tmp.setSelected(true);
					}
				}
				mealsP.add(tmp);
			}
		} else {
			mealsP.add(new JLabel("Aucun plat connu."));
			JButton addPlat = new JButton("Ajouter un plat");
			addPlat.addActionListener(e -> {
				/*
				 * ajout d'un appel a la cr�aton d'un plat grace a une fonction
				 * qui previen du succes de l'operation.
				 */
			});
		}
		JScrollPane scrollMeal = new JScrollPane(mealsP);
		this.add(scrollMeal);

		// configuration de la partie qui permet la saisie du prix
		JPanel pricePanel = new JPanel(new BorderLayout());
		this.prix = new JLabel("Prix : ");
		pricePanel.add(this.prix,BorderLayout.WEST);
		this.saisieprix = new JTextField(10);
		pricePanel.add(this.saisieprix,BorderLayout.CENTER);
		this.symbole = new JLabel("€");
		pricePanel.add(this.symbole,BorderLayout.EAST);
		this.add(pricePanel);

		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			try {
				if (this.updateMenu()) {
					this.dispose();
				}
			} catch (Exception e1) {
				// this.erreur.setText("Une erreur est survenue");
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

	public void add(Plat meal) {
		if (this.mealsList.contains(meal)) {
			this.mealsList.remove(meal);
		} else {
			this.mealsList.add(meal);
		}
	}

	public boolean updateMenu() throws SQLException {
		boolean retour = false;
		boolean crea = true;
		if (this.saisieNom.getText().equals("")) {
			this.erreur.setText("Veuillez saisir le nom.");
			crea = false;
			this.pack();
		} else if (this.saisieprix.getText().equals("")) {
			this.erreur.setText("Veuiller saisir le prix.");
			crea = false;
			this.pack();
		}
		if (crea) {

			String nom = this.saisieNom.getText();
			String SPrix = this.saisieprix.getText();
			SPrix.replace(',', '.');
			float prix = 0;
			try {
				prix = Float.parseFloat(this.saisieprix.getText());
			} catch (NumberFormatException e) {
				this.erreur.setText("Le format du prix n'est pas valide");
				this.validate();
			}
			if (this.menu == null) {
				Menu newMenu = new Menu(nom, prix);
				newMenu.setPlats(this.mealsList);
				retour = true;
			} else {
				this.menu.setNom(nom);
				this.menu.setPrix(prix);
				this.menu.setPlats(this.mealsList);
				retour = true;
			}
		}
		return retour;
	}

	public static Menu showContactDialog(Frame parent, String title, Menu menu) {
		SetterDialogMenu menuDialog = new SetterDialogMenu(parent, true, menu);
		menuDialog.setTitle(title);
		menuDialog.setVisible(true);
		Menu retour = menuDialog.menu;

		return retour;
	}
}
