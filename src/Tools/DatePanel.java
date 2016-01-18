package Tools;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DatePanel extends JPanel {

	// attributs
	private JLabel label;
	private JSpinner daySpinner, yearSpinner;
	private JComboBox<String> monthCombo;

	/**
	 * constructeur
	 * 
	 * @param labelText
	 *            texte du label
	 */
	public DatePanel(String labelText) {
		this.setLayout(new GridLayout(1, 4));
		JLabel label = new JLabel(labelText);
		this.add(label);
		daySpinner = new JSpinner();
		this.add(daySpinner);
		daySpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// System.out.println(daySpinner.getValue());
				checkDaySpinner();

			}
		});

		String[] monthList = new String[] { "Janvier", "Fevrier", "Mars",
				"Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre",
				"Octobre", "Novembre", "Decembre" };
		monthCombo = new JComboBox(monthList);
		this.add(monthCombo);
		monthCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkDaySpinner();
			}
		});
		yearSpinner = new JSpinner();
		yearSpinner.setValue(2016);
		this.add(yearSpinner);
		yearSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// System.out.println(daySpinner.getValue());
				checkDaySpinner();

			}
		});
		this.setVisible(true);
	}

	/**
	 * retourne le jour configuré dans le spinner correspondant
	 * 
	 * @return jour
	 */
	public int getDay() {

		return (int) daySpinner.getValue();
	}

	/**
	 * retourne le mois sélectionné dans la combobox sous forme d'un entier
	 * 
	 * @return mois (0 pour Janvier, 1 pour Février, etc...)
	 */
	public int getMonth() {
		return monthCombo.getSelectedIndex() + 1;

	}

	/**
	 * retourne l'année configurée dans le spinner correspondant
	 * 
	 * @return année
	 */
	public int getYear() {
		return (int) yearSpinner.getValue();
	}

	/**
	 * permet de savoir si une année est bissextile
	 * 
	 * @param year
	 * @return vrai si l'année passée en paramètre est bissextile, faux sinon
	 */
	private static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * retourne le nombre de jours du mois (0=janvier, 1=février, etc...) passé
	 * en paramètre, en prenant en compte l'année (bissextile ou non)
	 * 
	 * @param month
	 * @param year
	 * @return nombre de jours du mois
	 */
	private static int getDaysInMonth(int month, int year) {
		if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7
				|| month == 9 || month == 11) {
			return 31;
		} else if (month == 3 || month == 5 || month == 8 || month == 10) {
			return 30;
		} else {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
	}

	/**
	 * vérifie que la valeur du spinner correspondant au jour est valide par
	 * rapport aux mois et année définis par monthCombo et yearSpinner. Si ce
	 * n'est pas le cas, la valeur du daySpinner sera corrigée
	 */
	private void checkDaySpinner() {
		int month = monthCombo.getSelectedIndex();
		int year = (int) yearSpinner.getValue();
		int day = (int) daySpinner.getValue();
		if (day > (getDaysInMonth(month, year))) {
			daySpinner.setValue(getDaysInMonth(month, year));
		} else if (day < 1) {

			daySpinner.setValue(1);
		}
	}

	public void setDay(int day) {
		daySpinner.setValue(day);
	}

	public void setMonth(int month) {
		monthCombo.setSelectedIndex(month - 1);
	}

	public void setYear(int year) {
		yearSpinner.setValue(year);
	}

}