package Interface.list;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import Interface.PassFrame;
import InterfaceDialog.SetterDialogTable;
import LienBD.Salles;
import LienBD.Table;

public class ListPanelTable extends JPanel {

	private PassFrame parent;
	private JButton nouveau, supprimer, modifier;
	private JList<Table> TableList;
	private MyListModel<Table> modelList = new MyListModel<Table>();
	private int numSalle;

	public ListPanelTable(PassFrame parent, int numSalle) {
		this.parent = parent;
		this.setLayout(new BorderLayout());
		this.numSalle = numSalle;

		this.nouveau = new JButton("Nouveau", new ImageIcon(getClass()
				.getResource("/img/new.png")));
		this.nouveau.addActionListener(e -> this.createTable());
		this.modifier = new JButton("Modifier", new ImageIcon(getClass()
				.getResource("/img/edit.png")));
		this.modifier.addActionListener(e -> this.editSelectedTable());
		this.supprimer = new JButton("Supprimer", new ImageIcon(getClass()
				.getResource("/img/delete.png")));
		this.supprimer.addActionListener(e -> this.deleteSelectedItem());
		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.nouveau);
		barreOutils.add(this.modifier);
		barreOutils.add(this.supprimer);
		this.add(barreOutils, BorderLayout.NORTH);

		try {
			Table[] tables = Table.getAll(numSalle);
			this.TableList = new JList<Table>(tables);
			this.TableList.setModel(modelList);
			for (Table table : tables) {
				this.modelList.add(table);
			}
		} catch (SQLException e) {
			JOptionPane
					.showMessageDialog(parent,
							"Problème lors de la communication avec la base de données");
		}

		this.TableList.setCellRenderer(new RendererTable());

		this.TableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ListPanelTable.this.editSelectedTable();
				}
			}
		});
		JScrollPane scrollList = new JScrollPane(this.TableList);

		this.add(scrollList, BorderLayout.CENTER);
	}

	public void createTable() {
		Table newTable = SetterDialogTable.showContactDialog(this.parent,
				"Nouvelle table", null, this.numSalle);
		if (newTable != null) {
			this.modelList.add(newTable);
		}
	}

	public void editSelectedTable() {
		if (!this.TableList.isSelectionEmpty()) {
			Table modifTable = SetterDialogTable.showContactDialog(this.parent,
					"Modification", this.TableList.getSelectedValue(),
					this.numSalle);
			this.modelList.remove(modifTable);
			if (modifTable.getNumSalle() == this.numSalle) {
				this.modelList.add(modifTable);
				this.parent.update(new ListPanelTable(parent, modifTable
						.getNumSalle()));
			}
		}
	}

	public void deleteSelectedItem() {
		if (!this.TableList.isSelectionEmpty()) {
			Table select = this.TableList.getSelectedValue();
			this.modelList.remove(select);
			select.delete();
		}
	}
}
