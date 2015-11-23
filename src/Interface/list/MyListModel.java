package Interface.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.AbstractListModel;

public class MyListModel<T extends Comparable<T>> extends AbstractListModel<T>{
	
	private ArrayList<T> list;
	
	/**
	 * Instentie l' ArrayList
	 */
	public MyListModel() {
		this.list = new ArrayList<>();
	}
	
	/**
	 * Instentie l'ArrayList avec la collection passer en parametre
	 * 
	 * @param coll Collection a placer dans l'ArrayList
	 */
	public MyListModel(Collection<T> coll){
		this.list = new ArrayList<>(coll);
	}
	
	/**
	 * Si l'element <I>element</I> n'est pas dans la liste l'ajoute
	 * 
	 * @param element l'element a ajouter
	 */
	public void add(T element){
		//on verifie que l'element n'est pas deja dans la liste
		int index = Collections.binarySearch(this.list, element);
		if(index < 0){
			index = -index-1;
			this.list.add(index, element);
			fireIntervalAdded(this, index, index);
		}
	}
	
	
	/**
	 * supprimer l'element <i>element</i>
	 * @param element l'element a retire
	 */
	public void remove(T element){
		//cherche la position de l'element
		int index = this.list.indexOf(element);
		//supprime l'element de la liste
		this.list.remove(index);
		//previen la vue qu'il y a supretion
		fireIntervalRemoved(this, index, index);
	}
	
	@Override
	public T getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}

}
