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


import InterfaceDialog.SetterDialogIngredients;

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
				"/img/new.png")));
		this.supprimer=new JButton(new ImageIcon(getClass().getResource(
		 		"/img/delete.png")));
		this.modifier=new JButton(new ImageIcon(getClass().getResource(
				"/img/edit.png")));
		
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
	
	
	private void ajouter() {
		Ingredient newingredient = SetterDialogIngredients.showContactDialog(this.parent,
				"nouvelle ingredient", null);
		if (newingredient != null) {
			this.modelList.add(newingredient);
		}
	}
	
	private void sup() {
		if(!this.IngredientList.isSelectionEmpty()){
			Ingredient select = this.IngredientList.getSelectedValue();
			this.modelList.remove(select);
			select.delete(select.getNumIngredient());
		}
	}

	private void editSelectedIngredient() {
		if(!this.IngredientList.isSelectionEmpty()){
			Ingredient select = this.IngredientList.getSelectedValue();
			Ingredient newIngredient = SetterDialogIngredients.showContactDialog(this.parent,
					"nouvelle ingredient", select);
			if(newIngredient != null){
				this.modelList.remove(select);
				this.modelList.add(newIngredient);
			}		
		}
	}
}

