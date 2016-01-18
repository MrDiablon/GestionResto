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

import InterfaceDialog.SetterDialogPerso;
import InterfaceDialog.SetterDialogPlat;
import LienBD.Personnel;
import LienBD.Plat;

public class ListPanelPlat extends JPanel {

	private JFrame parent;
	private JButton nouveau, modifier, supprimer;
	private JList<Plat> listPlat;
	MyListModel<Plat> modelListPlat;

	public ListPanelPlat(JFrame parent) {
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
		this.listPlat = new JList<>();
		this.modelListPlat = new MyListModel<Plat>();
		this.listPlat.setModel(this.modelListPlat);
		RendererPlat rendererListPlat = new RendererPlat();
		this.listPlat.setCellRenderer(rendererListPlat);
		
		Plat[] plats;
		plats = Plat.getAll();

		for (Plat p : plats) {
			modelListPlat.add(p);
		}

		// clic sur la liste
		this.listPlat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					modifier();
				}
			}
		});

		JScrollPane platScroll = new JScrollPane(listPlat);
		this.add(platScroll);
	}

	private void modifier() {
		if (!this.listPlat.isSelectionEmpty()) {
			Plat select = this.listPlat.getSelectedValue();
			SetterDialogPlat.showContactDialog(this.parent, "modifier plat",
					select);
		}
	}

	private void ajouter() {
		Plat plat = SetterDialogPlat.showContactDialog(this.parent,
				"nouveau plat", null);
		this.modelListPlat.add(plat);
	}

	private void supprimer() {
		if (!this.listPlat.isSelectionEmpty()) {
			Plat select = this.listPlat.getSelectedValue();
			this.modelListPlat.remove(this.listPlat.getSelectedValue());
			try {
				select.delete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
