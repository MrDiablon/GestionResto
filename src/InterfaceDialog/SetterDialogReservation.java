package InterfaceDialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Tools.DatePanel;
import Interface.list.MyListModel;
import LienBD.Personnel;
import LienBD.Reservation;
import LienBD.Salles;
import LienBD.Table;
import Tools.hashText;

public class SetterDialogReservation extends JDialog{
	private JLabel nomLabel, prenomLabel, nbPers, numTable, numSalle;
	private JTextField nom, prenom, heure, minutes;
	private JSpinner nbPersSpinner;
	private Tools.DatePanel datePanel;
	private JButton valider, annuler;
	private Reservation reservation;
	private MyListModel<Reservation> modelList;
	private JComboBox<Table> listeTables;
	private JComboBox<Salles> listeSalles;
	private JPanel numTablePanel, nbPersPanel, tablePanel;
	
	public SetterDialogReservation(Frame owner, boolean modal, Reservation reservation) throws SQLException, FileNotFoundException{
		super(owner, modal);
		this.reservation = reservation;
		this.setTitle("Renseignez la réservation");
		
		//panel pour le nom
		JPanel panelNomClient = new JPanel();
		this.nomLabel = new JLabel("Nom du client : ");
		this.nom = new JTextField(10);
		panelNomClient.add(this.nomLabel);
		if(reservation!=null){
			this.nom.setText(reservation.getNomClient());
			
		}
		panelNomClient.add(this.nom);
		panelNomClient.setLayout(new GridLayout(1,1, 1, 0));
		
		//panel pour le prenom
		JPanel panelPrenomClient = new JPanel();
		this.prenomLabel = new JLabel("Prenom du client : ");
		this.prenom = new JTextField(10);
		if(reservation!=null){
			this.prenom.setText(reservation.getPrenomClient());
			
		}
		panelPrenomClient.add(this.prenomLabel);
		panelPrenomClient.add(this.prenom);
		panelPrenomClient.setLayout(new GridLayout(1, 1, 1,0));
		
		
		//panel pour la date
		this.datePanel = new DatePanel("Date : ");
		if(reservation!=null){
			Date date = reservation.getDateReservation();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			int m  = date.getMonth();
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			this.datePanel.setDay(day);
			this.datePanel.setMonth(m+1);			
			this.datePanel.setYear(year);
		}
		
		//panel pour l'heure
		JPanel panelHeure = new JPanel();
		panelHeure.add(new JLabel("Heure : "));
		this.heure = new JTextField();
		
		panelHeure.add(this.heure);
		
		panelHeure.add(new JLabel(":"));
		this.minutes = new JTextField();
		panelHeure.add(this.minutes);
		panelHeure.setSize(4, 4);
		panelHeure.setLayout(new GridLayout(1,1, 0,0));
		
		
		
		//Panel pour les salles
		JPanel numSallePanel = new JPanel();
		this.numSalle = new JLabel("Salle : ");
		this.listeSalles = new JComboBox<Salles>();
		Salles[] salles = Salles.getAll();
		for(Salles s : salles){
			this.listeSalles.addItem(s);
		}
		this.listeSalles.addItemListener(e ->{			
			Salles select = (Salles) listeSalles.getSelectedItem();
			try {
				tablesSelonSalle(select.getNumSalle());
			} catch (SQLException e1) {
				
			}
			SetterDialogReservation.this.validate();
		});
		Salles select = (Salles) this.listeSalles.getSelectedItem();
		this.tablePanel = new JPanel();
		this.tablesSelonSalle(select.getNumSalle());
		numSallePanel.add(numSalle);
		numSallePanel.add(listeSalles);	
		
		
		
		
		
		
		//panel pour les bouton
		JPanel panelBouton = new JPanel();
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			try {
				if(this.updateReservation()){
					this.dispose();
				};
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(e -> {
			this.dispose();
		});
		panelBouton.add(this.valider);
		panelBouton.add(this.annuler);
		panelBouton.setLayout(new GridLayout(1, 0, 2, 2));
		
		this.add(panelNomClient);
		this.add(panelPrenomClient);
		this.add(datePanel);
		this.add(panelHeure);
		//this.add(nbPersPanel);
		this.add(numSallePanel);
		//this.add(numTablePanel);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(this.tablePanel);
		this.add(panelBouton);
		this.pack();
		//configuration de la position;
		Rectangle bounds = owner.getBounds();
		Point location = bounds.getLocation();
		this.setLocation(location);
	}

	private boolean updateReservation() throws SQLException {
		boolean b = true;
		String nom = this.nom.getText();
		String prenom = this.prenom.getText();
		java.sql.Date date = new java.sql.Date(this.datePanel.getYear(), this.datePanel.getMonth(), this.datePanel.getDay() );
		String heure = "" + this.heure.getText() + this.minutes.getText();
		int nb = (int) this.nbPersSpinner.getValue();
		
		Table numTable = (Table) this.listeTables.getSelectedItem();
		int num = numTable.getNumTable();
		
		if(nom.equals("")){
			JOptionPane.showMessageDialog(this.getContentPane(), "Veuillez renseigner le champ \"nom\"");
			b=false;
		}else if(prenom.equals("")){
			JOptionPane.showMessageDialog(this.getContentPane(), "Veuillez renseigner le champ \"prenom\"");
			b=false;
		}else if(nb == 0){
			JOptionPane.showMessageDialog(this.getContentPane(), "Veuillez renseigner le nombre de personnes");
			b=false;
		}if(b){
			Reservation res = new Reservation(date, heure, nom, prenom, nb, num);
			JOptionPane.showMessageDialog(this.getContentPane(), " La réservation pour le client " + res.getNomClient() + res.getPrenomClient() + " a ete ajoutee a la base de donnée au numero " + res.getNumReservation());
			this.modelList = new MyListModel<Reservation>();
			this.modelList.add(res);
			this.dispose();
		}
		return b;
	}
	
	public static Reservation showContactDialog(Frame parent, String title,
			Reservation reservation) throws SQLException, FileNotFoundException {
		SetterDialogReservation resDialog = new SetterDialogReservation(parent, true,
				reservation);
		resDialog.setVisible(true);
		resDialog.setTitle(title);
		Reservation retour = resDialog.reservation;
		return retour;
	}
	
	public void tablesSelonSalle(int numSalle) throws SQLException{
		//Panel pour les tables
		if(this.numTablePanel != null){
			this.tablePanel.remove(this.numTablePanel);
		}
		if(this.nbPersPanel != null){
			this.tablePanel.remove(this.nbPersPanel);
		}
		numTablePanel = new JPanel();
		this.listeTables = new JComboBox<Table>();
		this.numTable = new JLabel("Numéro de la table : ");
		Table[] tables = Table.getAll(numSalle);
		for(Table t : tables){
			this.listeTables.addItem(t);
		}
		numTablePanel.add(numTable);
		numTablePanel.add(listeTables);
		
		//panel pour le nb de personnes
		Table capaciteMax = (Table) listeTables.getSelectedItem();
		int capacite = capaciteMax.getCapacite();
		nbPersPanel = new JPanel();
		this.nbPers = new JLabel("Nombre de personnes");
		//Model de spinner pour set la valeur maximale a la capacite de la table
		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, capacite, 1);  
		this.nbPersSpinner = new JSpinner();
		nbPersPanel.add(nbPers);
		nbPersPanel.add(nbPersSpinner);
		
		this.tablePanel.add(numTablePanel);
		this.tablePanel.add(nbPersPanel);
	}
}
