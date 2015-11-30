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

import InterfaceDialog.SetterDialogRoom;
import LienBD.Ingredient;
import LienBD.Salles;

public class ListPanelIngredient extends JPanel{
	
	private JFrame parent;
	private JButton ajouter, supprimer, modifier;
	private JList<Ingredient> IngredientList;
	private MyListModel<Ingredient> modelList;
	
	public ListPanelIngredient(JFrame parent){
		this.parent=parent;
		this.setLayout(new BorderLayout());
		this.ajouter=new JButton(new ImageIcon(getClass().getResource(
				"/img/ajout.png")));
		this.supprimer=new JButton(new ImageIcon(getClass().getResource(
				"/img/sup.png")));
		this.modifier=new JButton(new ImageIcon(getClass().getResource(
				"/img/mod.png")));
		
		this.ajouter.addActionListener(e -> ajouter());
		this.modifier.addActionListener(e -> editSelectedIngredient());
		this.supprimer.addActionListener(e -> sup());
		
		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.ajouter);
		barreOutils.add(this.modifier);
		barreOutils.add(this.supprimer);
		
		this.add(barreOutils, BorderLayout.NORTH);
		
		this.IngredientList = new JList<Ingredient>();
		this.modelList = new MyListModel<Ingredient>();
		this.IngredientList.setModel(this.modelList);
		this.IngredientList.setCellRenderer(new RendererIngredient());
		Ingredient[] ingredient = Ingredient.getAll();
		for (Ingredient i : ingredient) {
			this.modelList.add(i);
		}
		this.IngredientList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editSelectedIngredient();
				}
			}
		});
		JScrollPane IngredientScroll = new JScrollPane(this.IngredientList);
		this.add(IngredientScroll, BorderLayout.CENTER);
	}
	
	

	private void editSelectedIngredient() {
		

	}
	
	private void sup() {
		// TODO Auto-generated method stub
	}

	private void ajouter() {
		// TODO Auto-generated method stub
	}
}
