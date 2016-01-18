package InterfaceDialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.jdom2.Document;

import LienBD.Personnel;
import Tools.JDom;

public class SetterDialogDate extends JDialog {
	private JLabel matin, aprem, debM, finM, debA, finA;
	private JSpinner hDebM, hFinM, mDebM, mFinM, hDebA, hFinA, mDebA, mFinA;
	private JButton valider, annuler;
	private Calendar dateDay;
	private Document doc;

	public SetterDialogDate(Frame owner, boolean modal, Calendar date, Document timeCard) {
		super(owner, modal);
		this.doc = timeCard;
		this.dateDay = date;

		SpinnerNumberModel min1 = new SpinnerNumberModel(1, 1, 60, 1);
		SpinnerNumberModel min2 = new SpinnerNumberModel(1, 1, 60, 1);
		SpinnerNumberModel min3 = new SpinnerNumberModel(1, 1, 60, 1);
		SpinnerNumberModel min4 = new SpinnerNumberModel(1, 1, 60, 1);
		SpinnerNumberModel hour1 = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel hour2 = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel hour3 = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel hour4 = new SpinnerNumberModel(1, 1, 12, 1);
		this.setLayout(new BorderLayout());
		// configuration de la saisie horaire
		JPanel horaire = new JPanel(new GridLayout(2, 1));
		// panel pour la partie matin
		JPanel matinP = new JPanel(new BorderLayout());
		this.matin = new JLabel("Matin : ");
		matinP.add(this.matin, BorderLayout.WEST);
		// panel pour la saisie des horaire du matin
		JPanel horaireM = new JPanel(new GridLayout(2, 1));
		// horaire de commancement
		JPanel debP = new JPanel(new GridLayout(1, 3));
		this.debM = new JLabel("Horaire de commencement : ");
		this.hDebM = new JSpinner(hour1);
		this.mDebM = new JSpinner(min1);
		debP.add(this.debM);
		debP.add(this.hDebM);
		debP.add(this.mDebM);
		horaireM.add(debP);
		// horaire de fin
		JPanel finP = new JPanel(new GridLayout(1, 3));
		this.finM = new JLabel("Horaire de fin : ");
		this.hFinM = new JSpinner(hour2);
		this.mFinM = new JSpinner(min2);
		finP.add(this.finM);
		finP.add(this.hFinM);
		finP.add(this.mFinM);
		horaireM.add(finP);
		matinP.add(horaireM, BorderLayout.CENTER);

		// Panel pour la partie apres-midi
		JPanel apremP = new JPanel(new BorderLayout());
		this.aprem = new JLabel("Apres-midi : ");
		apremP.add(this.aprem, BorderLayout.WEST);
		// panel pour la saisie des horaire de l'apres-midi
		JPanel horaireA = new JPanel(new GridLayout(2, 1));
		// horaire de commancement
		JPanel debPA = new JPanel(new GridLayout(1, 3));
		this.debA = new JLabel("Horaire de commencement : ");
		this.hDebA = new JSpinner(hour3);
		this.mDebA = new JSpinner(min3);
		debPA.add(this.debA);
		debPA.add(this.hDebA);
		debPA.add(this.mDebA);
		horaireA.add(debPA);
		// horaire de fin
		JPanel finPA = new JPanel(new GridLayout(1, 3));
		this.finA = new JLabel("Horaire de fin : ");
		this.hFinA = new JSpinner(hour4);
		this.mFinA = new JSpinner(min4);
		finPA.add(this.finA);
		finPA.add(this.hFinA);
		finPA.add(this.mFinA);
		horaireA.add(finPA);
		apremP.add(horaireA, BorderLayout.CENTER);

		horaire.add(matinP);
		horaire.add(apremP);

		this.add(horaire, BorderLayout.CENTER);

		// parametrage des boutons
		JPanel panelButon = new JPanel();
		this.valider = new JButton("Valider");
		this.valider.addActionListener(e -> {
			if (this.doc == null) {
				JDom.createTimeCard(dateDay, (int) this.hDebM.getValue(), (int) this.mDebM.getValue(),
						(int) this.hFinM.getValue(), (int) this.mFinM.getValue(), (int) this.hDebA.getValue(),
						(int) this.mDebA.getValue(), (int) this.hFinA.getValue(), (int) this.mFinA.getValue());
			} else {
				JDom.createTimeCard(this.doc, dateDay, (int) this.hDebM.getValue(), (int) this.mDebM.getValue(),
						(int) this.hFinM.getValue(), (int) this.mFinM.getValue(), (int) this.hDebA.getValue(),
						(int) this.mDebA.getValue(), (int) this.hFinA.getValue(), (int) this.mFinA.getValue());
			}
		});
		this.annuler = new JButton("Annuler");
		this.annuler.addActionListener(e -> {
			this.dispose();
		});
		panelButon.add(this.valider);
		panelButon.add(this.annuler);
		this.add(panelButon, BorderLayout.SOUTH);

		this.pack();
	}

	public static void showContactDialog(Frame parent, String title, Calendar dateD, Document doc) {
		SetterDialogDate date = new SetterDialogDate(parent, true, dateD, doc);
		date.setTitle(title);
		date.setVisible(true);
	}
}
