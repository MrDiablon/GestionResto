package Interface.list;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;

import InterfaceDialog.SetterDialogRoom;
import LienBD.Salles;
import LienBD.Menu;

public class ListPanelMenu extends JPanel {
	

	private JFrame parent;
	private JButton nouveau, supprimer, modifier;
	private JList<Menu> Menus;
	private MyListModel<Menu> modelList;

	public ListPanelMenu(JFrame parent) {
		this.parent = parent;
		
		this.setLayout(new BorderLayout());

		this.nouveau = new JButton(new ImageIcon(getClass().getResource("")));
		this.modifier = new JButton(new ImageIcon(getClass().getResource("")));
		this.supprimer = new JButton(new ImageIcon(getClass().getResource("")));
		this.nouveau.addActionListener(e -> createMenu());
		this.modifier.addActionListener(e -> editSelectedMenu());
		this.supprimer.addActionListener(e -> delSelectedMenu());

		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.nouveau);
		barreOutils.add(this.modifier);
		barreOutils.add(this.supprimer);

		this.add(barreOutils, BorderLayout.NORTH);

		this.Menus = new JList<Menu>();
		this.modelList = new MyListModel<Menu>();
		this.Menus.setModel(this.modelList);
		this.Menus.setCellRenderer(new MenuRenderer());
		Menu[] menus = Menu.getAll();
		for (Menu m : menus) {
			this.modelList.add(m);
		}
		this.Menus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editSelectedMenu();
				}
			}
		});
		JScrollPane SalleScroll = new JScrollPane(this.Menus);
		this.add(SalleScroll, BorderLayout.CENTER);
	}

	
	private void createMenu() {
		/*Menu newMenu = SetterDialogRoom.showContactDialog(this.parent,
				"nouvelle salle", null);
		if (newMenu != null) {
			this.modelList.add(newMenu);
		}
		 */
	}
	
	
	private void editSelectedMenu(){
		/*if(!this.Menus.isSelectionEmpty()){
			Menu select = this.Menus.getSelectedValue();
			Menu newMenu = SetterDialogRoom.showContactDialog(this.parent,
					"nouvelle salle", select);
			if(newMenu != null){
				this.modelList.remove(select);
				this.modelList.add(newMenu);
			}		
		}*/
	}
	
	private void delSelectedMenu(){
		/*if(!this.Menus.isSelectionEmpty()){
			Menu select = this.Menus.getSelectedValue();
			this.modelList.remove(select);
		}
	*/
	}
	
}
	
