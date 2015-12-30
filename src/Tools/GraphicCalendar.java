package Tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Interface.PassFrame;

/**
 * Permet d'afficher graphiquement un calendrier
 * 
 * @author Antoine
 *
 */
public class GraphicCalendar extends JPanel {

	protected int nbDay, currentYear, currentMonth;
	protected JPanel datePanel, dayPanel, weekPanel, choosePanel,centerPanel;
	protected JButton[] buttonsDay;
	protected String[] day = { "lun.", "mar.", "mer.", "jeu.", "ven.", "sam.", "dim." }, month = { "Janvier", "Fevrier",
			"Mars", "Avril", "Mai", "Juin", "Juillet", "Octobre", "Septembre", "Octobre", "Novembre", "Decembre" };
	protected JLabel[] jLDay = new JLabel[8];
	protected JTextField chooseYear;
	protected JComboBox<String> chooseMonth;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame test = new JFrame("test Calendar");
				test.add(new GraphicCalendar(2015, 12));
				test.pack();
				test.setVisible(true);
			}
		});
	}

	public GraphicCalendar(int year, int month) {
		this.currentYear = year;
		this.currentMonth = month;
		this.setLayout(new BorderLayout());
		GridLayout layout = new GridLayout(0, 7);
		this.choosePanel = new JPanel(new GridLayout(1, 0));
		this.chooseMonth = new JComboBox<>(this.month);
		this.chooseMonth.addActionListener(e->{
			this.currentMonth = this.chooseMonth.getSelectedIndex();
			generateDayPanel();
			this.validate();
		});
		this.chooseYear = new JTextField("" + this.currentYear);
		this.choosePanel.add(this.chooseMonth);
		this.choosePanel.add(this.chooseYear);
		this.dayPanel = new JPanel(layout);
		for (int i = 0; i < day.length; i++) {
			jLDay[i] = new JLabel(day[i]);
			this.dayPanel.add(jLDay[i]);
		}
		
		this.nbDay = this.getNbDayByMonth(year, month);
		this.generateDayPanel();
		this.add(this.choosePanel,BorderLayout.NORTH);
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
		int week = this.getWeek();
		GridLayout layout = new GridLayout(0, 7);
		if(datePanel != null){
			this.remove(this.datePanel);
			this.centerPanel.remove(this.datePanel);
		}
		this.datePanel = new JPanel(layout);
		if(this.weekPanel != null){
			this.remove(this.weekPanel);
		}
		if(this.centerPanel != null){
			this.remove(this.centerPanel);
		}
		this.centerPanel = new JPanel(new BorderLayout());
		this.weekPanel = new JPanel(new GridLayout(0, 1));
		this.buttonsDay = new JButton[this.nbDay];
		for (int i = 0; i < this.nbDay; i++) {
			if (i % 7 == 0) {
				this.weekPanel.add(new JLabel("" + week++));
			}
			JButton tmp = this.buttonsDay[i];
			tmp = new JButton("" + (i + 1));
			this.datePanel.add(tmp);
		}
		this.centerPanel.add(this.dayPanel,BorderLayout.NORTH);
		this.centerPanel.add(this.datePanel,BorderLayout.CENTER);
		this.add(centerPanel);
		//this.add(this.datePanel, BorderLayout.CENTER);
		this.add(this.weekPanel, BorderLayout.WEST);
		
	}

}
