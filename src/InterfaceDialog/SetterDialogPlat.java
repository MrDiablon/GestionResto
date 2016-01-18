package InterfaceDialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import LienBD.Etat;
import LienBD.Ingredient;
import LienBD.Plat;
import LienBD.Restaurant;
import LienBD.Salles;

public class SetterDialogPlat extends JDialog {

	private JLabel nomPlat, recette, prixU, errorLabel;
	private JTextField nomPlatTextField, prixTextField;
	private JPanel ingredients;
	private LinkedList<Ingredient> ingAjout;

	private JButton valider, annuler;
	private Plat plat;

	public SetterDialogPlat(Frame owner, boolean modal, Plat plat) {
		super(owner, modal);
		// configuration des attributs
		this.plat = plat;
		this.nomPlat = new JLabel("Nom du plat : ");
		this.recette = new JLabel("Recette : ");
		this.prixU = new JLabel("Prix unitaire : ");
		this.errorLabel = new JLabel("");
		if (this.plat != null) {
			this.ingAjout = this.plat.getIngredient();
		} else {
			this.ingAjout = new LinkedList<Ingredient>();
		}

		this.nomPlatTextField = new JTextField(20);
		Ingredient[] ingredients = Ingredient.getAll();
		this.ingredients = new JPanel(new GridLayout(ingredients.length, 1));
		for (Ingredient i : ingredients) {
			JCheckBox tmp = new JCheckBox(i.getNom());
			if (this.plat != null) {
				if (this.ingAjout.contains(i)) {
					tmp.setSelected(true);
				}
			}
			tmp.addActionListener(e -> ajout(i));
			this.ingredients.add(tmp);
		}
		JScrollPane scroll = new JScrollPane(this.ingredients);

		// Creation d'un Textfiel pour le prix
		this.prixTextField = new JTextField(10);

		// configuration des boutons
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			try {
				if (updatePlat()) {
					this.dispose();
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"acces a la base de donnee impossible");
			}
		});

		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(e -> this.dispose());

		if (this.plat != null) {
			this.prixTextField.setText("" + plat.getPrixU());
			this.nomPlatTextField.setText(this.plat.getNomPlat());
		}

		// création de panel pour aligner les composant

		JPanel nomPanel = new JPanel();
		nomPanel.add(this.nomPlat);
		nomPanel.add(this.nomPlatTextField);

		JPanel recettePanel = new JPanel();
		recettePanel.add(this.recette);
		recettePanel.add(scroll);

		JPanel prixPanel = new JPanel();
		prixPanel.add(this.prixU);
		prixPanel.add(this.prixTextField);

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.add(this.valider);
		ButtonPanel.add(this.annuler);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.add(errorLabel);
		this.add(nomPanel);
		this.add(nomPanel);
		this.add(recettePanel);
		this.add(prixPanel);
		this.add(ButtonPanel);
		this.pack();

	}

	private void ajout(Ingredient ing) {
		if (this.ingAjout.contains(ing)) {
			this.ingAjout.remove(ing);
		} else {
			this.ingAjout.add(ing);
		}
	}

	private boolean updatePlat() throws SQLException {
		boolean retour = true;
		if (this.nomPlatTextField.getText().equals("")) {
			this.errorLabel.setText("Remplir le champ \"Nom du plat\"");
			this.pack();
			retour = false;
		} else if (this.prixTextField.getText().equals(0)) {
			this.errorLabel.setText("Remplir le champ \"Prix du plat\"");
			this.pack();
			retour = false;
		}

		String nom = this.nomPlatTextField.getText();
		String str = prixTextField.getText();
		float prix = 0;
		try {
			prix = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			this.errorLabel.setText("Le format du prix n'est pas crorrect.");
			this.pack();
			retour = false;
		}
		if (retour) {
			if (this.plat == null) {
				this.plat = new Plat(nom, this.ingAjout, prix);
				retour = true;
			} else {
				this.plat.setNomPlat(nomPlatTextField.getText());
				this.plat.setPrixU(prix);

				retour = true;
			}
		}
		return retour;
	}

	public static Plat showContactDialog(Frame parent, String title, Plat plat) {
		SetterDialogPlat platModif = new SetterDialogPlat(parent, true, plat);
		platModif.setVisible(true);
		platModif.setTitle(title);
		Plat retour = platModif.plat;

		return retour;
	}

}
