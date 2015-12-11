package Interface.list;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import Interface.PassFrame;
import InterfaceDialog.SetterDialogRoom;
import LienBD.Salles;

public class ListPanelRoom extends JPanel {

	private PassFrame parent;
	private JButton newRoom, deleteRoom, setRoom;
	private JList<Salles> roomList;
	private MyListModel<Salles> modelList;

	public ListPanelRoom(PassFrame parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());

		//parmatrage des bouton
		this.newRoom = new JButton(new ImageIcon(getClass().getResource(
				"/img/new.png")));
		this.setRoom = new JButton(new ImageIcon(getClass().getResource(
				"/img/edit.png")));
		this.deleteRoom = new JButton(new ImageIcon(getClass().getResource(
				"/img/delete.png")));
		this.newRoom.addActionListener(e -> createRoom());
		this.setRoom.addActionListener(e -> editSelectedRoom());
		this.deleteRoom.addActionListener(e -> deleteRoom());

		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.newRoom);
		barreOutils.add(this.setRoom);
		barreOutils.add(this.deleteRoom);

		this.add(barreOutils, BorderLayout.NORTH);

		this.roomList = new JList<Salles>();
		this.modelList = new MyListModel<Salles>();
		this.roomList.setModel(this.modelList);
		this.roomList.setCellRenderer(new RendererRoom());
		Salles[] salles = Salles.getAll();
		for (Salles s : salles) {
			this.modelList.add(s);
		}
		this.roomList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Salles select = ListPanelRoom.this.roomList.getSelectedValue();
					parent.addNewTab(select.getNomSalle(), new ListPanelTable(parent, select.getNumSalle()) );
				}
			}
		});
		JScrollPane SalleScroll = new JScrollPane(this.roomList);
		this.add(SalleScroll, BorderLayout.CENTER);
	}

	private void createRoom() {
		Salles newSalle = SetterDialogRoom.showContactDialog(this.parent,
				"nouvelle salle", null);
		if (newSalle != null) {
			this.modelList.add(newSalle);
		}

	}

	private void editSelectedRoom() {
		if (!this.roomList.isSelectionEmpty()) {
			Salles select = this.roomList.getSelectedValue();
			Salles newSalle = SetterDialogRoom.showContactDialog(this.parent,
					"nouvelle salle", select);
			if (newSalle != null) {
				this.modelList.remove(select);
				this.modelList.add(newSalle);
			}

		}
	}

	private void deleteRoom() {
		if (!this.roomList.isSelectionEmpty()) {
			Salles select = this.roomList.getSelectedValue();
			this.modelList.remove(select);
			select.delete();
		}
	}
}
