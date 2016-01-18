package Tools;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Permet d'afficher graphiquement un calendrier
 * 
 * @author Antoine
 *
 */
public class GraphicCalendar extends JPanel {

	protected int nbDay, currentYear, currentMonth;
	protected JPanel datePanel, dayPanel, weekPanel, choosePanel, centerPanel;
	protected LinkedList<JButton> buttonsDay;
	protected String[] day = { "lun.", "mar.", "mer.", "jeu.", "ven.", "sam.", "dim." }, month = { "Janvier", "Fevrier",
			"Mars", "Avril", "Mai", "Juin", "Juillet", "Octobre", "Septembre", "Octobre", "Novembre", "Decembre" };
	protected JLabel[] jLDay = new JLabel[8];
	protected JTextField chooseYear;
	protected JComboBox<String> chooseMonth;
	protected Calendar cal;
	protected int posFirstDay = 0;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame test = new JFrame("test Calendar");
				test.add(new GraphicCalendar(2016, Calendar.MAY));
				test.pack();
				test.setVisible(true);
			}
		});

	/*
		 Calendar cal = new GregorianCalendar(2016, 0, 1); 
		 System.out.println(cal.getTime().toString());
		 System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		 System.out.println(Calendar.SATURDAY);*/
		
	}

	public GraphicCalendar(int year, int month) {
		this.currentYear = year;
		this.currentMonth = month;
		this.setLayout(new BorderLayout());
		GridLayout layout = new GridLayout(0, 7);
		this.choosePanel = new JPanel(new GridLayout(1, 0));
		this.chooseMonth = new JComboBox<>(this.month);
		this.chooseMonth.addActionListener(e -> {
			currentMonth = chooseMonth.getSelectedIndex();
			generateDayPanel();
			GraphicCalendar.this.validate();
		});
		this.chooseYear = new JTextField("" + this.currentYear);
		chooseYear.addActionListener(e -> {
			currentYear = Integer.parseInt(chooseYear.getText());
			generateDayPanel();
			GraphicCalendar.this.validate();
		});
		this.choosePanel.add(this.chooseMonth);
		this.choosePanel.add(this.chooseYear);
		this.dayPanel = new JPanel(layout);
		for (int i = 0; i < day.length; i++) {
			jLDay[i] = new JLabel(day[i]);
			this.dayPanel.add(jLDay[i]);
		}
		this.generateDayPanel();
		this.add(this.choosePanel, BorderLayout.NORTH);
	}

	private int getNbDayByMonth(int year, int month) {
		int retour = 0;
		if (month <= 12 && month > 0) {
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				retour = 31;
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				retour = 30;
			} else if (month == 2 && this.isBissextile(year)) {
				retour = 29;
			} else {
				retour = 28;
			}
		}
		return retour;
	}

	private int getWeek() {
		int retour = 0;
		for (int i = this.currentMonth; i >= 0; i--) {
			retour += this.getNbDayByMonth(this.currentYear, i) / 7;
		}
		return retour + 1;
	}

	private boolean isBissextile(int year) {
		return ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0);
	}

	private void generateDayPanel() {
		this.nbDay = this.getNbDayByMonth(this.currentYear, this.currentMonth+1);
		this.cal = new GregorianCalendar(this.currentYear, currentMonth, 1);
		if (this.cal.get(Calendar.DAY_OF_WEEK) == 1 ) {
			this.posFirstDay = 6;
		} else {
			this.posFirstDay = this.cal.get(Calendar.DAY_OF_WEEK)-1;
		}

		int week = this.getWeek();
		GridLayout layout = new GridLayout(0, 7);
		if (datePanel != null) {
			this.remove(this.datePanel);
			this.centerPanel.remove(this.datePanel);
		}
		this.datePanel = new JPanel(layout);
		if (this.weekPanel != null) {
			this.remove(this.weekPanel);
		}
		if (this.centerPanel != null) {
			this.remove(this.centerPanel);
		}
		this.centerPanel = new JPanel(new BorderLayout());
		this.weekPanel = new JPanel(new GridLayout(0, 1));
		this.buttonsDay = new LinkedList<JButton>();
		if (this.posFirstDay > 0) {
			int beforeD = (this.currentMonth == 0) ? this.getNbDayByMonth(currentYear-1, 11)
					: this.getNbDayByMonth(currentYear, currentMonth);
			for (int i = this.posFirstDay-1; i >= 0; i--) {
				JButton tmp = new JButton("" + (beforeD - i ));
				tmp.setEnabled(false);
				this.datePanel.add(tmp);
			}
		}
		for (int i = 0; i < this.nbDay; i++) {
			if (i % 7 == 0) {
				this.weekPanel.add(new JLabel("" + week++));
			}
			JButton tmp = new JButton("" + (i + 1));
			this.buttonsDay.add(tmp);
			this.datePanel.add(tmp);
		}
		this.centerPanel.add(this.dayPanel, BorderLayout.NORTH);
		this.centerPanel.add(this.datePanel, BorderLayout.CENTER);
		this.add(centerPanel);
		// this.add(this.datePanel, BorderLayout.CENTER);
		this.add(this.weekPanel, BorderLayout.WEST);

	}

}
