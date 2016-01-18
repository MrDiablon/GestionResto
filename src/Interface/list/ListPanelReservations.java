package Interface.list;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import InterfaceDialog.SetterDialogReservation;
import InterfaceDialog.SetterDialogRoom;
import LienBD.Reservation;
import LienBD.Salles;

public class ListPanelReservations extends JPanel {
	private JFrame parent;
	private JButton nouveau, supprimer, modifer;
	private JList<Reservation> ReservationList;
	private MyListModel<Reservation> modelList;

	public ListPanelReservations(JFrame parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());

		this.nouveau = new JButton(new ImageIcon(getClass().getResource(
				"/img/new.png")));
		this.modifer = new JButton(new ImageIcon(getClass().getResource(
				"/img/edit.png")));
		this.supprimer = new JButton(new ImageIcon(getClass().getResource(
				"/img/delete.png")));

		this.nouveau.addActionListener(e -> {
			try {
				createReservation();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		this.modifer.addActionListener(e -> {
			try {
				editSelectedReservation();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		this.supprimer.addActionListener(e -> {delSelectedReservation();this.disable();});

		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.nouveau);
		barreOutils.add(this.modifer);
		barreOutils.add(this.supprimer);

		this.add(barreOutils, BorderLayout.NORTH);

		this.ReservationList = new JList<Reservation>();
		this.modelList = new MyListModel<Reservation>();
		this.ReservationList.setModel(this.modelList);
		this.ReservationList.setCellRenderer(new ReservationRenderer());
		Reservation[] reservations = Reservation.getAll();
		for (Reservation r : reservations) {
			this.modelList.add(r);
		}
		this.ReservationList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						editSelectedReservation();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		JScrollPane SalleScroll = new JScrollPane(this.ReservationList);
		this.add(SalleScroll, BorderLayout.CENTER);
	}

	private void createReservation() throws SQLException, FileNotFoundException {
		Reservation newReservation = SetterDialogReservation.showContactDialog(
				this.parent, "Nouvelle reservation", null);
		if (newReservation != null) {
			this.modelList.add(newReservation);
		}
	}

	private void editSelectedReservation() throws SQLException, FileNotFoundException {
		if (!this.ReservationList.isSelectionEmpty()) {
			Reservation select = this.ReservationList.getSelectedValue();
			Reservation newReservation = SetterDialogReservation
					.showContactDialog(this.parent, "Reservation modifiee",
							select);
			if (newReservation != null) {
				this.modelList.remove(select);
				this.modelList.add(newReservation);
			}
		}
	}

	private void delSelectedReservation() {
		if (!this.ReservationList.isSelectionEmpty()) {
			Reservation select = this.ReservationList.getSelectedValue();
			this.modelList.remove(select);
			select.delete();
		}
	}
}
