package LienBD;

/***********************************************************************
 * Module:  Menu.java
 * Author:  user
 * Purpose: Defines the Class Menu
 ***********************************************************************/

import java.sql.SQLException;
import java.util.*;
import com.mysql.jdbc.ResultSet;

public class Menu implements Comparable<Menu> {

	private int numMenu;
	private String composition;
	private String nom;

	private static myPDO instance = myPDO.getInstance();

	public Menu(int numMenu, String composition, String nom) {
		this.numMenu = numMenu;
		this.composition = composition;
		this.nom = nom;

		this.create();

		String[] comp = this.composition.split("|");
		String sql = "INSERT INTO COMPOSER(NUMMENU,NUMPLAT) VALUE (?,?) ";
		Menu.instance.prepare(sql);
		Object[] data = new Object[2];
		for (String c : comp) {
			data[1] = this.numMenu;
			data[2] = c;
			Menu.instance.execute(data, true);
		}
	}

	public Menu(int id) {
		String sql = "SELECT * FROM MENU WHERE NUMMENU = ?";
		Object[] data = { id };
		Menu.instance.prepare(sql);
		ResultSet res = (ResultSet) Menu.instance.execute(data, false);
		try {
			if (res.next()) {
				this.numMenu = id;
				this.composition = res.getString("COMPOSITION");
				this.nom = res.getString("NOM");
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

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @pdRoleInfo migr=no name=Plat assc=composer coll=java.util.Collection
	 *             impl=java.util.ArrayList mult=0..* type=Composition
	 */
	private java.util.Collection<Plat> plat;

	/** @pdGenerated default getter */
	public java.util.Collection<Plat> getPlat() {
		if (plat == null)
			plat = new java.util.ArrayList<Plat>();
		return plat;
	}

	/** @pdGenerated default iterator getter */
	public java.util.Iterator getIteratorPlat() {
		if (plat == null)
			plat = new java.util.ArrayList<Plat>();
		return plat.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newPlat
	 */
	public void setPlat(java.util.Collection<Plat> newPlat) {
		removeAllPlat();
		for (java.util.Iterator iter = newPlat.iterator(); iter.hasNext();)
			addPlat((Plat) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newPlat
	 */
	public void addPlat(Plat newPlat) {
		if (newPlat == null)
			return;
		if (this.plat == null)
			this.plat = new java.util.ArrayList<Plat>();
		if (!this.plat.contains(newPlat))
			this.plat.add(newPlat);
	}

	/**
	 * @pdGenerated default remove
	 * @param oldPlat
	 */
	public void removePlat(Plat oldPlat) {
		if (oldPlat == null)
			return;
		if (this.plat != null)
			if (this.plat.contains(oldPlat))
				this.plat.remove(oldPlat);
	}

	/** @pdGenerated default removeAll */
	public void removeAllPlat() {
		if (plat != null)
			plat.clear();
	}

	public void create() {
		String sql = "INSERT INTO MENU(`NUMMENU`,`COMPOSITION`,`NOM`) VALUE (?,?,?)";
		Object[] data = { this.numMenu, this.composition, this.nom };
		Menu.instance.prepare(sql);
		Menu.instance.execute(data, true);
	}

	// -modif() : modifie une ligne de la BD avec les attribut comme valeur.
	public void modif() {
		String sql = "UPDATE MENU SET COMPOSITION = ?, NOM = ? WHERE NUMMENU = ? ";
		Object[] data = { this.composition, this.nom, this.numMenu };
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
