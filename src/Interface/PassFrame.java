package Interface;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Interface.list.ListPanelIngredient;
import Interface.list.ListPanelMenu;
import Interface.list.ListPanelPersonnel;
import Interface.list.ListPanelRoom;
import panelAide.DroitPanel;

public class PassFrame extends JFrame {

	private JTabbedPane pane = new JTabbedPane();

	public PassFrame(int droit) throws Exception {
		// on verifie que les droit sont bon
		if (droit > 0 && droit <= 3) {
			this.setTitle("Test de Page");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);

			// configuration du menu
			JMenuBar barre = new JMenuBar();
			JMenu fichier = new JMenu("Fichier");
			JMenu windows = new JMenu("Windows");
			JMenu newWindows = new JMenu("Nouvelle Fenetre");
			JMenu aide = new JMenu("Aide");
			// JMenuItem
			JMenuItem fermer = new JMenuItem("Fermer");
			JMenuItem listeSalle = new JMenuItem("Liste des salles");

			JMenuItem listeMenuM = new JMenuItem("Liste des menus");
			JMenuItem aideDroit = new JMenuItem("droit");
			// configuration des actionListener du menu
			fermer.addActionListener(e -> this.dispose());
			listeSalle.addActionListener(e -> this.addNewTab("Liste des salles", new ListPanelRoom(this), false));
			listeMenuM.addActionListener(e -> this.addNewTab("Liste des menus", new ListPanelMenu(this), false));
			aideDroit.addActionListener(e -> this.addNewTab("Aide droit", new DroitPanel(), true));
			// ajout des item et sous menu
			fichier.add(fermer);
			windows.add(newWindows);
			newWindows.add(listeSalle);
			newWindows.add(listeMenuM);
			if (droit >= 2) {
				JMenuItem listeIngredientM = new JMenuItem("Liste des ingredients");
				newWindows.add(listeIngredientM);
				listeIngredientM.addActionListener(
						e -> this.addNewTab("Liste des ingredients", new ListPanelIngredient(this), false));
				if (droit >= 3) {
					JMenuItem listePersoM = new JMenuItem("Liste des employés");
					listePersoM.addActionListener(
							e -> this.addNewTab("Liste des employés", new ListPanelPersonnel(this), false));
					newWindows.add(listePersoM);
				}
			}
			aide.add(aideDroit);
			barre.add(fichier);
			barre.add(windows);
			barre.add(aide);

			this.add(barre);

			this.addNewTab("Liste des salles", new ListPanelRoom(this));
			if (droit >= 2) {
				this.addNewTab("Liste des ingredients", new ListPanelIngredient(this),false);
				this.addNewTab("Liste des menus", new ListPanelMenu(this), false);
				if(droit >= 3 ){
					this.addNewTab("Liste des employes", new ListPanelPersonnel(this), false);
				}
			}
			this.setLayout(new GridLayout(1, 5));
			this.setJMenuBar(barre);

			this.add(pane);
			this.pack();
		}
	}

	public void addNewTab(String title, JPanel panel, boolean focus) {
		pane.add(title, panel);

		// configuation du label
		JLabel closeLabel = new JLabel(title);
		closeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		// configuration du bouton
		JButton closeButton = new JButton("X");
		closeButton.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		closeButton.addActionListener(e -> delTab(panel));
		// configuration du panel contenant le label et le bouton
		JPanel titlePanel = new JPanel();
		titlePanel.add(closeLabel);
		titlePanel.add(closeButton);

		pane.setTabComponentAt(pane.indexOfComponent(panel), titlePanel);
		pane.validate();
		if (focus) {
			pane.setSelectedComponent(panel);
		}
	}

	public void addNewTab(String title, JPanel panel) {
		this.addNewTab(title, panel, true);
	}

	public void delTab(JPanel panel) {
		pane.remove(panel);
	}

	public void update(JPanel panel) {

	}
}
