package InterfaceDialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import LienBD.Etat;
import LienBD.Restaurant;
import LienBD.Salles;

public class SetterDialogRoom extends JDialog {
	private JLabel nomSalle, numResto, etat, nbTables, errorLabel;
	private JComboBox<String> etatBox;
	JComboBox<Restaurant> restoBox;
	private JSpinner nbTableSpinner;
	private JTextField nomSalleText;
	private JButton valider, annuler;
	private Salles salle;

	public SetterDialogRoom(Frame owner, boolean modal, Salles salle) {
		super(owner, modal);
		// configuration des attributs
		this.errorLabel = new JLabel();
		this.salle = salle;
		this.nomSalle = new JLabel("Nom de la Salle");
		this.numResto = new JLabel("Restaurent");
		this.etat = new JLabel("Etat de la salle");
		this.nbTables = new JLabel("Nombre de tables");

		String[] etat = { Etat.libre.toString(), Etat.reserve.toString(),
				Etat.horsservice.toString() };
		this.etatBox = new JComboBox<String>(etat);
		this.restoBox = new JComboBox<Restaurant>();
		Restaurant[] restos = Restaurant.getAll();
		int index = 0;
		for (Restaurant r : restos) {
			this.restoBox.addItem(r);
			if (this.salle != null) {
				if (this.salle.getNumResto() == r.getNumResto()) {
					this.restoBox.setSelectedIndex(index);
				}
			}
			index++;
		}

		this.nbTableSpinner = new JSpinner();
		this.nomSalleText = new JTextField(5);

		// configuration des boutons
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			try {
				if (updateSalle()) {
					this.dispose();
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "acces a la base de donnée impossible");
			}
		});

		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(e -> this.dispose());

		if (this.salle != null) {
			this.nbTableSpinner.setValue(salle.getNombreTables());
			this.nomSalleText.setText(this.salle.getNomSalle());
		}

		// création de panel pour aligner les composant
		JPanel nomPanel = new JPanel();
		nomPanel.add(this.nomSalle);
		nomPanel.add(this.nomSalleText);

		JPanel restoPanel = new JPanel();
		restoPanel.add(this.numResto);
		restoPanel.add(this.restoBox);

		JPanel etatPanel = new JPanel();
		etatPanel.add(this.etat);
		etatPanel.add(this.etatBox);

		JPanel tablePanel = new JPanel();
		tablePanel.add(this.nbTables);
		tablePanel.add(this.nbTableSpinner);

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.add(this.valider);
		ButtonPanel.add(this.annuler);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.add(this.errorLabel);
		this.add(nomPanel);
		this.add(restoPanel);
		this.add(etatPanel);
		this.add(tablePanel);
		this.add(ButtonPanel);

		this.pack();

	}

	private boolean updateSalle() throws SQLException {
		boolean retour = false;
		if (this.nomSalleText.getText().equals("")) {
			this.errorLabel.setText("Remplir le champ \"Nom de la Salle\"");
		} else if (this.nbTableSpinner.getValue().equals(0)) {
			this.errorLabel.setText("Remplir le champ \"Nombre de tables\"");
		}
		String nom = this.nomSalleText.getText();
		int nbTable = (int) this.nbTableSpinner.getValue();
		Etat etatSalle = Etat.valueOf((String) this.etatBox.getSelectedItem());
		Restaurant selectResto = (Restaurant) this.restoBox.getSelectedItem(); 
		if (this.salle == null) {
			this.salle = new Salles(selectResto.getNumResto(), nom, nbTable, etatSalle, null);
			retour = true;
		} else {
			this.salle.setnomSalle(nom);
			this.salle.setnombreTables(nbTable);
			this.salle.setetat(etatSalle);
			this.salle.modif();
			retour = true;
		}
		// cree la salles grace aux donnee saisie
		return retour;
	}

	public static Salles showContactDialog(Frame parent, String title,
			Salles salles) {
		SetterDialogRoom salleContact = new SetterDialogRoom(parent, true,
				salles);
		salleContact.setVisible(true);
		salleContact.setTitle(title);
		Salles retour = salleContact.salle;

		return retour;
	}
}
