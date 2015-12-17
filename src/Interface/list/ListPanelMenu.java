package Interface.list;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import Interface.PassFrame;
import InterfaceDialog.SetterDialogMenu;
import InterfaceDialog.SetterDialogTable;
import LienBD.Ingredient;
import LienBD.Menu;
import LienBD.Table;

public class ListPanelMenu extends JPanel {
	
	private PassFrame parent;
	private JButton nouveau, supprimer, modifier;
	private JList<Menu> menuList;
	private MyListModel<Menu> modelList = new MyListModel<Menu>();

	public ListPanelMenu(PassFrame parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());

		this.nouveau = new JButton("Nouveau", new ImageIcon(getClass()
				.getResource("/img/new.png")));
		this.nouveau.addActionListener(e -> this.createMenu());
		this.modifier = new JButton("Modifier", new ImageIcon(getClass()
				.getResource("/img/edit.png")));
		this.modifier.addActionListener(e -> this.editSelectedMenu());
		this.supprimer = new JButton("Supprimer", new ImageIcon(getClass()
				.getResource("/img/delete.png")));
		this.supprimer.addActionListener(e -> this.deleteSelectedMenu());
		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.nouveau);
		barreOutils.add(this.modifier);
		barreOutils.add(this.supprimer);
		this.add(barreOutils, BorderLayout.NORTH);
		
		Menu[] menus = Menu.getAll();
		this.menuList = new JList<Menu>(menus);
		this.menuList.setModel(this.modelList);
		for (Menu menu : menus) {
			this.modelList.add(menu);
		}
		this.menuList.setCellRenderer(new MenuRenderer());
		
		JScrollPane scroll = new JScrollPane(this.menuList);
		
		this.add(scroll, BorderLayout.CENTER);
}
	
	public void createMenu() {
		SetterDialogMenu.showContactDialog(parent, "nouveau menu", null);
	}
	
	public void editSelectedMenu() {
		
	}

	public void deleteSelectedMenu() {
	
	}
	
}
