package Interface.list;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import InterfaceDialog.SetterDialogRoom;
import LienBD.Salles;

public class ListPanelSalles extends JPanel {

	private JFrame parent;
	private JButton nouveau, supprimer, modifier;
	private JList<Salles> SalleList;
	private MyListModel<Salles> modelList;

	public ListPanelSalles(JFrame parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());

		this.nouveau = new JButton(new ImageIcon(getClass().getResource(
				"/img/new.png")));
		this.modifier = new JButton(new ImageIcon(getClass().getResource(
				"/img/edit.png")));
		this.supprimer = new JButton(new ImageIcon(getClass().getResource(
				"/img/delete.png")));
		this.nouveau.addActionListener(e -> {
			createRoom();
		});

		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.nouveau);
		barreOutils.add(this.modifier);
		barreOutils.add(this.supprimer);

		this.add(barreOutils, BorderLayout.NORTH);

		this.SalleList = new JList<Salles>();
		this.modelList = new MyListModel<Salles>();
		this.SalleList.setModel(this.modelList);
		this.SalleList.setCellRenderer(new SalleRenderer());
		Salles[] salles = Salles.getAll();
		for (Salles s : salles) {
			this.modelList.add(s);
		}
		this.SalleList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editSelectedRoom();
				}
			}
		});
		JScrollPane SalleScroll = new JScrollPane(this.SalleList);
		this.add(SalleScroll, BorderLayout.CENTER);
	}

	private void createRoom() {
		Salles newSalle = SetterDialogRoom.showContactDialog(this.parent,
				"nouvelle salle", null);
		if (newSalle != null) {
			this.modelList.add(newSalle);
		}

	}
	
	private void editSelectedRoom(){
		if(!this.SalleList.isSelectionEmpty()){
			Salles select = this.SalleList.getSelectedValue();
			Salles newSalle = SetterDialogRoom.showContactDialog(this.parent,
					"nouvelle salle", select);
			if(newSalle != null){
				this.modelList.remove(select);
				this.modelList.add(newSalle);
			}
			
			
		}
	}
}
