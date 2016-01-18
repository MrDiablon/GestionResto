package InterfaceDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JList;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import LienBD.CommandeLien;
import LienBD.Menu;
import LienBD.Plat;
import LienBD.PrixNourriture;
import LienBD.Table;

public class SetterDialogCommande extends JDialog {

	private JLabel Table, erreur;
	private JList<PrixNourriture> menuAll, menuAdd;
	private JButton valider, annuler, payer;
	private Table table;
	private int numCommande;
	private CommandeLien commandeLien;

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
	public SetterDialogCommande(Frame owner, Boolean modal, Table table) {
		super(owner, modal);
		// this.commandeLien=new
		// CommandeLien(CommandeLien.last(),this.prixt,this.table.getNumTable(),null);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.table = table;

		this.erreur = new JLabel();
		this.add(erreur);

		// configuration de la partie salle
		JButton add = new JButton("add");
		JButton remove = new JButton("remove");
		PrixNourriture[] list;
		if (Menu.getAll() != null && Plat.getAll() != null) {
			int c = 0;
			c = Menu.getAll().length + Plat.getAll().length;
			list = new PrixNourriture[c];
			int j = 0;
			for (Menu m : Menu.getAll()) {
				list[j] = m;
				j++;
			}
			for (Plat p : Plat.getAll()) {
				list[j] = p;
				j++;
			}
		} else
			list = new PrixNourriture[0];
		this.menuAll = new JList<PrixNourriture>(list);
		this.menuAll.setPreferredSize(new Dimension(200, 200));
		add.addActionListener(e -> this.add(this.menuAll.getSelectedValue()));
		this.menuAdd = new JList<PrixNourriture>();
		remove.addActionListener(e -> this.remove(this.menuAdd.getSelectedIndex()));
		;
		/*
		 * if (table != null) { this.menuAll.setSelectedValue(table,true);
		 * System.out.println(table.getNumTable()); }
		 */
		JPanel Pannel = new JPanel();
		Pannel.add(new JScrollPane(this.menuAll));
		Pannel.add(add);
		Pannel.add(remove);
		Pannel.add(new JScrollPane(this.menuAdd));

		this.add(Pannel);

		// configuration des bouton
		this.valider = new JButton("Valider");
		this.annuler = new JButton("Annuler");
		this.payer = new JButton("Payer");
		this.annuler.addActionListener(e -> this.dispose());
		this.valider.addActionListener(e -> {
			if (this.updateTable()) {
				this.dispose();
			}
		});
		this.payer.addActionListener(e -> this.payer());
		JPanel pannelButton = new JPanel();
		pannelButton.add(this.valider);
		pannelButton.add(this.annuler);
		pannelButton.add(this.payer);

		this.add(pannelButton);

		this.pack();

	}

	private void payer() {
		this.menuAdd.setListData(new Menu[0]);
		this.updateTable();
		this.table.deleteCommand();
	}

	public boolean updateTable() {
		boolean retour = false;
		if (this.menuAdd.getHeight() >= 1) {
			retour = true;
			ListModel<PrixNourriture> mod = menuAdd.getModel();
			if (this.commandeLien != null)
				this.commandeLien.delete();
			ArrayList<PrixNourriture> ajout = new ArrayList<PrixNourriture>();
			ArrayList<Plat> plats = new ArrayList<Plat>();
			for (int i = 0; i < mod.getSize(); i++) {
				ajout.add(mod.getElementAt(i));
			}
			for (int i = 0; i < ajout.size(); i++) {
				PrixNourriture p = ajout.get(i);
				if(p!=null){
				if (p.isPlat())
					plats.add((Plat) p);
				else {
					Menu m = (Menu) p;
					LinkedList<Plat> pl = m.getPlats();
					for (Plat pla : pl) {
						plats.add(pla);
					}
				}
			}
			}
			for (Plat p : plats) {
				int num = p.getNumPlat();
				if (this.table.getQt(num) > 0) {
					this.table.setCommand(num);
				} else {
					this.table.addCommand(num);
				}
			}
		}
		return retour;
	}

	public void remove(int i) {
		ListModel<PrixNourriture> t = this.menuAdd.getModel();
		if (t.getSize() > 0) {
			PrixNourriture[] l = new PrixNourriture[t.getSize() - 1];

			int k = 0;
			for (int j = 0; j < t.getSize()-1; j++) {
				if (j != i) {
					l[k] = t.getElementAt(j);
					k++;
				}
			}
			this.menuAdd.setListData(l);
		}
	}

	public void add(PrixNourriture menu) {
		ListModel<PrixNourriture> t = this.menuAdd.getModel();
		PrixNourriture[] l = new PrixNourriture[t.getSize() + 1];
		for (int i = 0; i < t.getSize(); i++) {
			l[i] = t.getElementAt(i);
		}
		l[t.getSize()] = menu;
		this.menuAdd.setListData(l);
	}

	public static Table showContactDialog(Frame parent, String title, Table table) {
		SetterDialogCommande CommandeDialog = new SetterDialogCommande(parent, true, table);
		CommandeDialog.setTitle(title);
		CommandeDialog.setVisible(true);
		Table retour = CommandeDialog.table;
		return retour;
	}

}