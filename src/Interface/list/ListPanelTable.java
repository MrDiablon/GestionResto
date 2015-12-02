package Interface.list;

import javax.swing.JButton;
import javax.swing.JList;

import Interface.PassFrame;
import LienBD.Salles;
import LienBD.Table;

public class ListPanelTable {
	
	private PassFrame parent;
	private JButton nouveau, supprimer, modifier;
	private JList<Table> TableList;
	private MyListModel<Table> modelList;
	
	public ListPanelTable(PassFrame parent) {
		// TODO Auto-generated constructor stub
	}
}
