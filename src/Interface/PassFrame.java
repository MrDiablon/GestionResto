package Interface;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Interface.list.ListPanelIngredient;
import Interface.list.ListPanelMenu;
import Interface.list.MenuRenderer;
import Interface.list.MyListModel;
import Interface.list.RendererIngredient;
import LienBD.Ingredient;
import LienBD.Menu;
import LienBD.Personnel;
import Interface.list.ListPanelRoom;

public class PassFrame extends JFrame {

	private JTabbedPane pane = new JTabbedPane();

	public PassFrame(int droit) throws Exception {
		// on verifie que les droit sont bon
		if (droit == 1 || droit == 2) {
			this.setTitle("test de Page");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);

			// configuration du menu
			JMenuBar barre = new JMenuBar();
			JMenu fichier = new JMenu("Fichier");
			JMenu windows = new JMenu("Windows");
			JMenu newWindows = new JMenu("Nouvelle Fenetre");
			// JMenuItem
			JMenuItem fermer = new JMenuItem("Fermer");
			JMenuItem listeSalle = new JMenuItem("Liste des salles");
			JMenuItem listeIngredientM = new JMenuItem("Liste des ingredients");
			JMenuItem listeMenuM = new JMenuItem("Liste des menus");
			// configuration des actionListener du menu
			fermer.addActionListener(e -> this.dispose());
			listeSalle.addActionListener(e -> this.addNewTab(
					"Liste des salles", new ListPanelRoom(this)));
			listeIngredientM.addActionListener(e -> this.addNewTab(
					"Liste des ingredients", new ListPanelIngredient(this), false));
			listeMenuM.addActionListener(e -> this.addNewTab("Liste des menus",
					new ListPanelMenu(this), false));
			// ajout des item et sous menu
			fichier.add(fermer);
			windows.add(newWindows);
			newWindows.add(listeSalle);
			newWindows.add(listeMenuM);
			barre.add(fichier);
			barre.add(windows);

			this.add(barre);

			// configuration de la liste des ingredient
			JList<Ingredient> listeIngredient = new JList<Ingredient>();
			MyListModel<Ingredient> modelListIngredient = new MyListModel<>();
			listeIngredient.setModel(modelListIngredient);
			RendererIngredient rendererIngredient = new RendererIngredient();
			listeIngredient.setCellRenderer(rendererIngredient);
			Ingredient[] ings = Ingredient.getAll();
			for (Ingredient I : ings) {
				modelListIngredient.add(I);
			}
			JScrollPane ingredientScroll = new JScrollPane(listeIngredient);

			// Configuration de la liste du personnel
			JList<Personnel> listPerso = new JList<>();
			MyListModel<Personnel> modelListPerso = new MyListModel<Personnel>();
			listPerso.setModel(modelListPerso);
			PersonnelRenderer rendererListPerso = new PersonnelRenderer();
			listPerso.setCellRenderer(rendererListPerso);
			Personnel[] personnes = Personnel.getAll();
			for (Personnel p : personnes) {
				if (!p.getNOM().equals("ROOT")) {
					modelListPerso.add(p);
				}
			}
			JScrollPane personnelScroll = new JScrollPane(listPerso);

			//this.addNewTab("Liste des ingredients", new ListPanelIngredient(
			//		this));
			this.addNewTab("Liste des salles", new ListPanelRoom(this), false);
			if (droit == 2) {
				this.addNewTab("Liste des menus", new ListPanelMenu(this),false);
				pane.addTab("Liste des employés", personnelScroll);
			}
			JButton bouton = new JButton("Afficher");
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
		if(focus){
			pane.setSelectedComponent(panel);
		}
	}
	
	public void addNewTab(String title, JPanel panel){
		this.addNewTab(title, panel,true);
	}

	public void delTab(JPanel panel) {
		pane.remove(panel);
	}

	public void update(JPanel panel) {

	}
}
