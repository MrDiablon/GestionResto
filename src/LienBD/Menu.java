package LienBD;

/***********************************************************************
 * Module:  Menu.java
 * Author:  user
 * Purpose: Defines the Class Menu
 ***********************************************************************/

import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.ResultSet;

import LienBD.Plat;

public class Menu implements Comparable<Menu> {

	private int numMenu;
	private String nom;
	private LinkedList<Plat> plats;
	
	private static myPDO instance = myPDO.getInstance();

	public Menu(int numMenu, String nom) {
		this.numMenu = numMenu;
		this.nom = nom;

		this.create();
	}

	public Menu(int id) {
		String sql = "SELECT * FROM MENU WHERE NUMMENU = ?";
		Object[] data = { id };
		Menu.instance.prepare(sql);
		ResultSet res = (ResultSet) Menu.instance.execute(data, false);
		sql = "SELECT * FROM COMPOSER WHERE NUMMENU = ?";
		Menu.instance.prepare(sql);
		ResultSet res2 = (ResultSet) Menu.instance.execute(data, false);
		try {
			if (res.next()) {
				this.numMenu = id;
				this.nom = res.getString("NOM");
			}
			while (res2.next()) {
				this.plats.add(new Plat(res2.getInt(0)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getNumMenu() {
		return numMenu;
	}

	public void setNumMenu(int numMenu) {
		this.numMenu = numMenu;
	}
	
	public LinkedList<Plat> getPlats(){
		return this.plats;
	}
	
	public void setPlats(LinkedList<Plat> plats){
		if(plats != null){
			this.plats = plats;
		}
	}
	
	public void addPlat(Plat plat){
		if(plat != null){
			this.plats.add(plat);
			String sql = "INSERT INTO COMPOSER(`NUMPLAT`,`NUMMENU`) VALUE (?,?)";
			Object[] data = {plat.getNumPlat(),this.numMenu};
			Menu.instance.prepare(sql);
			Menu.instance.execute(data,true);
		}
	}
	
	public void deletePlat(Plat plat){
		int index = this.plats.indexOf(plat);
		if(index > 0){
			this.plats.remove(index);
			String sql = "DELETE FROM COMPOSER WHERE NUMMENU = ? AND NUMPLAT = ?";
			Object data[] = {this.numMenu,plat.getNumPlat()};
			Menu.instance.prepare(sql);
			Menu.instance.execute(data, true);
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void create() {
		String sql = "INSERT INTO MENU(`NUMMENU`,`NOM`) VALUE (?,?)";
		Object[] data = { this.numMenu, this.nom };
		Menu.instance.prepare(sql);
		Menu.instance.execute(data, true);
	}

	// -modif() : modifie une ligne de la BD avec les attribut comme valeur.
	public void modif() {
		String sql = "UPDATE MENU SET NOM = ? WHERE NUMMENU = ? ";
		Object[] data = { this.nom, this.numMenu };
		Menu.instance.prepare(sql);
		Menu.instance.execute(data, true);
	}

	// -delete() : supprimer une ligne de la BD .
	public void delete(int id) {
		String sql = "DELETE FROM MENU WHERE NUMMENU = ?";
		Object[] data = { id };
		Menu.instance.prepare(sql);
		Menu.instance.execute(data, true);
	}

	public static String getCompositionById(int id) {
		String retour = "";
		String sql = "SELECT COMPOSITION FROM MENU WHERE NUMMENU = ?";
		Object[] data = { id };
		Menu.instance.prepare(sql);
		ResultSet res = (ResultSet) Menu.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getString("COMPOSITION");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static String getnomById(int id) {
		String retour = "";
		String sql = "SELECT NOM FROM MENU WHERE NUMMENU = ?";
		Object[] data = { id };
		Menu.instance.prepare(sql);
		ResultSet res = (ResultSet) Menu.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getString("NOM");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static Menu[] getAll() {
		// on ecrit notre code sql on demande tous les id de la table
		String sql = "SELECT NUMMENU FROM MENU";
		// on prepare notre requete
		Menu.instance.prepare(sql);
		// on l'execute sans parametre car inutile
		ResultSet res = (ResultSet) Menu.instance.execute();
		// on prepare notre tableau de retour;
		Menu[] retour = null;
		try {
			res.last();
			// on instance notre retour grace a res.getRow() qui donne le nombre
			// de ligne retourner
			retour = new Menu[res.getRow()];
			// on remet le curseur au debut
			res.beforeFirst();
			// pour chaque ligne on cree une nouvelle instance grace a l'id
			for (int i = 0; res.next(); i++) {
				retour[i] = new Menu(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Menu[] getByNB(int deb, int nb) {
		String sql = "SELECT NUMMENU FROM MENU LIMIT ? , ?";
		Menu.instance.prepare(sql);
		Object[] data = { deb, nb };
		ResultSet res = (ResultSet) Menu.instance.execute(data, false);
		Menu[] retour = null;
		try {
			retour = new Menu[res.getRow()];
			res.beforeFirst();
			for (int i = 0; res.next(); i++) {
				retour[i] = new Menu(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	@Override
	public int compareTo(Menu o) {
		return this.nom.compareTo(o.nom);
	}
}
