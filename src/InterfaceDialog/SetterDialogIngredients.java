package InterfaceDialog;

import java.awt.Color;
import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import LienBD.Etat;
import LienBD.EtatI;
import LienBD.Ingredient;

public class SetterDialogIngredients extends JDialog {
	private JLabel nomIngredient, etat, prix, errorLabel, stock;
	private JComboBox<String> etatBox, ingredientBox;
	private JPanel prixPanel;
	private JTextField nomIngredientText;
	private JSpinner stockSpinner;
	private JTextField prixS;
	private JButton valider, annuler;
	private Ingredient ingredient;

	public SetterDialogIngredients(Frame owner, boolean modal, Ingredient ing) {
		super(owner, modal);
		// configuration des attributs
		this.errorLabel = new JLabel();
		this.errorLabel.setBackground(Color.red);
		this.ingredient = ing;
		this.nomIngredient = new JLabel("Nom de l'ingredient");
		this.etat = new JLabel("Etat de l'ingredient");
		this.prix = new JLabel("Prix de l'ingredient");
		this.stock = new JLabel("Quantité");
		SpinnerNumberModel model =  new SpinnerNumberModel(1.0, 0.0, 100000.0, 0.1);
		this.prixS = new JTextField(10);
		this.stockSpinner = new JSpinner();
		this.nomIngredientText = new JTextField(3);

		String[] etat = { EtatI.bon.toString(), EtatI.mauvais.toString(),
				EtatI.danger.toString() };
		this.etatBox = new JComboBox<String>(etat);
		if (this.ingredient != null) {
			this.nomIngredientText.setText(this.ingredient.getNom());
			this.prixS.setText("" + this.ingredient.getPrixU());
			this.stockSpinner.setValue(this.ingredient.getStock());
			this.etatBox.setSelectedItem(this.ingredient.getEtatI());
		}

		// configuration des boutons
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			try {
				if (updateIngredient()) {
					this.dispose();
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"acces a la base de données impossible");
			}
		});

		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(e -> this.show(false));

		// création de panel pour aligner les composant
		JPanel nomPanel = new JPanel();
		nomPanel.add(this.nomIngredient);
		nomPanel.add(this.nomIngredientText);

		this.prixPanel = new JPanel();
		this.prixPanel.add(this.prix);
		this.prixPanel.add(prixS);

		JPanel stockPanel = new JPanel();
		this.prixPanel.add(this.stock);
		this.prixPanel.add(stockSpinner);

		JPanel etatPanel = new JPanel();
		etatPanel.add(this.etat);
		etatPanel.add(this.etatBox);

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.add(this.valider);
		ButtonPanel.add(this.annuler);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.add(this.errorLabel);
		this.add(nomPanel);
		this.add(stockPanel);
		this.add(etatPanel);
		this.add(prixPanel);
		this.add(ButtonPanel);
		this.pack();
	}

	private boolean updateIngredient() throws SQLException {
		boolean retour = true;
		String nom = this.nomIngredientText.getText();
		String prix = this.prixS.getText();
		float prixF;
		try{
			prixF = Float.parseFloat(prix);
		}catch(NumberFormatException e){
			prixF = (float) 0.0;
			retour = false;
			this.errorLabel.setText("Le format du prix est incorect.");
		}
		EtatI etat = EtatI.valueOf(this.etatBox.getSelectedItem().toString());
		int stock = (int) this.stockSpinner.getValue();

		if (nom.equals("")) {
			this.errorLabel.setText("Remplir le champ \"Nom de l'ingredient\"");
			this.pack();
			retour = false;
		} else if (prixF == (float) 0.0) {
			this.errorLabel
					.setText("Remplir le champ \"Prix de l'ingredient\"");
			retour = false;
			this.pack();
		}
		if (retour) {

			if (this.ingredient == null) {
				this.ingredient = new Ingredient(prixF, stock, etat, nom);
				this.ingredient.modif();
			} else {
				this.ingredient.setNom(nom);
				this.ingredient.setPrixU(prixF);
				this.ingredient.setEtatI(etat);
				this.ingredient.modif();
			}
		}
		// cree l'ingredient grace aux donnee saisie
		// Test
		return retour;
	}

	public static Ingredient showContactDialog(Frame parent, String title,
			Ingredient ing) {
		SetterDialogIngredients ingredientContact = new SetterDialogIngredients(
				parent, true, ing);
		ingredientContact.setVisible(true);
		ingredientContact.setTitle(title);
		Ingredient retour = ingredientContact.ingredient;
		return retour;
	}
}