package InterfaceDialog;

import java.awt.Frame;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import LienBD.Etat;
import LienBD.Salles;
import LienBD.Table;

public class SetterDialogTable extends JDialog {

	private JLabel Salle, etat, capacite, erreur;
	private JComboBox<String> etatList;
	private JComboBox<Salles> SalleSpinner;
	private JSpinner capaciterSpinner;
	private JButton valider, annuler;
	private Table table;

	/**
	 * Instancie tous les attribut et prepare la page a l'affichage
	 * 
	 * @param owner
	 *            La fenetre qui affiche la SetterDialogTable
	 * @param modal
	 *            dis si cette fenetre est modal ou non
	 * @param table
	 *            la table a modifier peut etre null dans la cas d'une créations
	 */
	public SetterDialogTable(Frame owner, boolean modal, Table table, int numSalle) {
		super(owner, modal);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.table = table;

		this.erreur = new JLabel();
		this.add(erreur);

		// configuration de la partie salle
		this.Salle = new JLabel("Salle");
		this.SalleSpinner = new JComboBox<Salles>(Salles.getAll());
		if (table != null) {
			this.SalleSpinner.setSelectedItem(table.getSalles());
		}else{
			this.SalleSpinner.setSelectedItem(new Salles(numSalle));
		}
		JPanel sallePannel = new JPanel();
		sallePannel.add(this.Salle);
		sallePannel.add(this.SalleSpinner);
		this.add(sallePannel);

		// configuration de la partie etat
		this.etat = new JLabel("Etat");
		String[] etat = { Etat.libre.toString(), Etat.reserve.toString(),
				Etat.horsservice.toString() };
		this.etatList = new JComboBox<String>(etat);
		if (table != null) {
			this.etatList.setSelectedItem(table.getEtat());
		}
		JPanel etatPanel = new JPanel();
		etatPanel.add(this.etat);
		etatPanel.add(this.etatList);
		this.add(etatPanel);

		// configuration de la partie capaciter
		this.capacite = new JLabel("capacité");
		this.capaciterSpinner = new JSpinner();
		if (table != null) {
			this.capaciterSpinner.setValue(table.getCapacite());
		}
		JPanel capacitePanel = new JPanel();
		capacitePanel.add(this.capacite);
		capacitePanel.add(this.capaciterSpinner);
		this.add(capacitePanel);

		// configuration des bouton
		this.valider = new JButton("Valider");
		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(e -> this.dispose());
		this.valider.addActionListener(e -> {
			if (this.updateTable()) {
				this.dispose();
			}
		});
		JPanel pannelButton = new JPanel();
		pannelButton.add(this.valider);
		pannelButton.add(this.annuler);
		this.add(pannelButton);

		this.pack();
	}

	public boolean updateTable() {
		boolean retour = true;
		Salles salle = (Salles) this.SalleSpinner.getSelectedItem();
		Etat etat =  Etat.valueOf((String) this.etatList.getSelectedItem());
		int capaciter = (int) this.capaciterSpinner.getValue();

		if (this.table == null) {
			try {
				new Table(salle.getNumSalle(),capaciter, etat, null, null);
			} catch (SQLException e) {
				retour = false;
				this.erreur
						.setText("Une erreur avec la base de donnée est survenue");
			}
		} else {
			this.table.setCapacite(capaciter);
			this.table.setEtat(etat);
			this.table.setSalles(salle);
			this.table.modif();
		}

		return retour;
	}

	public static Table showContactDialog(Frame parent, String title,
			Table table, int numSalle) {
		SetterDialogTable tableDialog = new SetterDialogTable(parent,true,table,numSalle);
		tableDialog.setTitle(title);
		tableDialog.setVisible(true);
		Table retour = tableDialog.table;
		
		return retour;
	}
}
