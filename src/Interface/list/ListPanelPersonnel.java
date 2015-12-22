package Interface.list;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import Interface.PersonnelRenderer;
import InterfaceDialog.SetterDialogPerso;
import LienBD.Personnel;

public class ListPanelPersonnel extends JPanel {

	private JFrame parent;
	private JButton nouveau, modifier, supprimer;
	private JList<Personnel> listPerso;
	MyListModel<Personnel> modelListPerso;

	public ListPanelPersonnel(JFrame parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());

		// barre d'outils
		this.nouveau = new JButton(new ImageIcon(getClass().getResource(
				"/img/new.png")));
		this.modifier = new JButton(new ImageIcon(getClass().getResource(
				"/img/edit.png")));
		this.supprimer = new JButton(new ImageIcon(getClass().getResource(
				"/img/delete.png")));
		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.nouveau);
		barreOutils.add(this.modifier);
		barreOutils.add(this.supprimer);

		this.add(barreOutils, BorderLayout.NORTH);

		// clic sur les boutons de la barre
		this.nouveau.addActionListener(e -> ajouter());
		this.modifier.addActionListener(e -> modifier());
		this.supprimer.addActionListener(e -> supprimer());

		// Configuration de la liste du personnel
		this.listPerso = new JList<>();
		this.modelListPerso = new MyListModel<Personnel>();
		this.listPerso.setModel(this.modelListPerso);
		PersonnelRenderer rendererListPerso = new PersonnelRenderer();
		this.listPerso.setCellRenderer(rendererListPerso);
		Personnel[] personnes;
		try {
			personnes = Personnel.getAll();
			for (Personnel p : personnes) {
				if (!p.getNOM().equals("ROOT")) {
					modelListPerso.add(p);
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// clic sur la liste
		this.listPerso.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					modifier();
				}
			}
		});

		JScrollPane personnelScroll = new JScrollPane(listPerso);
		this.add(personnelScroll);
	}

	private void modifier() {
		if (!this.listPerso.isSelectionEmpty()) {
			Personnel select = this.listPerso.getSelectedValue();
			SetterDialogPerso.showContactDialog(this.parent,
					"modifier personnel", select);
		}
	}

	private void ajouter() {
		Personnel perso = SetterDialogPerso.showContactDialog(this.parent,
				"nouveau personnel", null);
		this.modelListPerso.add(perso);
	}

	private void supprimer() {
		if (!this.listPerso.isSelectionEmpty()) {
			Personnel select = this.listPerso.getSelectedValue();
			this.modelListPerso.remove(this.listPerso.getSelectedValue());
			try {
				select.delete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
