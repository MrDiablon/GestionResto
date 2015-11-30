package Interface;

import java.awt.Button;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Interface.list.MenuRenderer;
import Interface.list.MyListModel;
import Interface.list.RendererIngredient;
import Interface.list.SalleRenderer;
import LienBD.Ingredient;
import LienBD.Menu;
import LienBD.Personnel;
import LienBD.Salles;
import Interface.list.ListPanelSalles;

public class PassFrame extends JFrame {

	public PassFrame(int droit) throws Exception {
		// on verifie que les droit sont bon
		if (droit == 1 || droit == 2) {
			this.setTitle("test de Page");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);

			// configuration du menu
			JMenuBar barre = new JMenuBar();
			JMenuItem item1 = new JMenuItem("test");
			JMenuItem item2 = new JMenuItem("plop");
			JMenuItem item3 = new JMenuItem("fermer");
			JMenuItem item4 = new JMenuItem("save");
			JMenu fichier = new JMenu("Fichier");
			JMenu autre = new JMenu("Autre");
			JTabbedPane pane = new JTabbedPane();
			fichier.add(item1);
			fichier.add(item3);
			fichier.add(item4);
			autre.add(item2);
			barre.add(fichier);
			barre.add(autre);

			// Configuration du tabbedPane
			JTextField text1 = new JTextField(50);
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

			// configuration de la liste des menus
			JList<LienBD.Menu> listMenu = new JList<LienBD.Menu>();
			MyListModel<LienBD.Menu> modelListMenu = new MyListModel<LienBD.Menu>();
			listMenu.setModel(modelListMenu);
			MenuRenderer listMenuRenderer = new MenuRenderer();
			listMenu.setCellRenderer(listMenuRenderer);
			Menu[] menus = Menu.getAll();
			for (Menu m : menus) {
				modelListMenu.add(m);
			}
			JScrollPane MenuSroll = new JScrollPane(listMenu);

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

			pane.addTab("Liste des ingrédients", ingredientScroll);
			pane.addTab("Liste des salles", new ListPanelSalles(this));
			if (droit == 2) {
				pane.addTab("Liste des menus", MenuSroll);
				pane.addTab("Liste des employés", personnelScroll);
			}
			JButton bouton = new JButton("Afficher");
			this.setLayout(new GridLayout(1, 5));
			this.setJMenuBar(barre);

			// this.add(bouton);
			this.add(pane);
			this.pack();
		}

	}
}
