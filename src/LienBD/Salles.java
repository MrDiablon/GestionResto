/***********************************************************************
 * Module:  Salles.java
 * Author:  user
 * Purpose: Defines the Class Salles
 ***********************************************************************/
package LienBD;

import java.util.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import resteau.config.ResteauConfig;

import com.mysql.jdbc.ResultSet;

public class Salles implements Comparable<Salles> {

	private int numSalle;
	private int numResto;
	private String nomSalle;
	private int nombreTables;
	private static myPDO instance = myPDO.getInstance();
	private Etat etat;
	private int nbTableDispo;
	private Restaurant restaurant;
	private java.util.Collection<Menu> menus;

	public Salles(int id) {
		String sql = "SELECT * FROM SALLE WHERE NUMSALLE = ?";
		Object[] data = { id };
		Salles.instance.prepare(sql);
		ResultSet res = (ResultSet) Salles.instance.execute(data, false);
		sql = "SELECT COUNT(NUMTABLE) FROM TABLES WHERE NUMSALLE = ?";
		instance.prepare(sql);
		ResultSet res2 = (ResultSet) instance.execute(data, false);
		sql = "SELECT * FROM MENU WHERE NUMMENU = ?";
		Salles.instance.prepare(sql);
		ResultSet res3 = (ResultSet) Salles.instance.execute(data, false);
		this.menus = new ArrayList<Menu>();
		try {
			if (res.next()) {
				this.numSalle = id;
				this.numResto = res.getInt("NUMRESTO");
				this.nomSalle = res.getString("NOMSALLE");
				this.nombreTables = res.getInt("NOMBRETABLES");
				this.etat = Etat.valueOf(res.getString("ETATS").toLowerCase());
			}
			this.restaurant = new Restaurant(this.numResto);
			if (res2.next()) {
				this.nbTableDispo = res2.getInt(1);
			}
			while (res3.next()) {
				this.menus.add(new Menu(res3.getInt("NUMMENU")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Salles(int numResto, String nomSalle, int nombreTables, Etat etat,
			Collection<Menu> menu) throws SQLException {
		this.numResto = numResto;
		this.nomSalle = nomSalle;
		this.nombreTables = nombreTables;
		this.etat = etat;
		this.restaurant = new Restaurant(numResto);
		this.menus = menu;
		this.create();
	}

	public static int getNumRestaurantById(int id) {
		int retour = 1;
		String sql = "SELECT NUMRESTO FROM SALLE WHERE id = ?";
		Object[] data = { id };
		Salles.instance.prepare(sql);
		ResultSet res = (ResultSet) Salles.instance.execute(data, false);
		try {
			retour = res.getInt("NUMRESTO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static String getNomSalleById(int id) {
		String retour = "";
		String sql = "SELECT NOMSALLE FROM SALLE WHERE id = ?";
		Object[] data = { id };
		Salles.instance.prepare(sql);
		ResultSet res = (ResultSet) Salles.instance.execute(data, false);
		try {
			retour = res.getString("NOMSALLE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static int getNombreTablesById(int id) {
		int retour = 1;
		String sql = "SELECT NOMBRETABLES FROM SALLE WHERE id = ?";
		Object[] data = { id };
		Salles.instance.prepare(sql);
		ResultSet res = (ResultSet) Salles.instance.execute(data, false);
		try {
			retour = res.getInt("NOMBRETABLES");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public int getNumSalle() {
		return this.numSalle;
	}

	public static Etat getEtatById(int id) {
		Etat retour = null;
		String sql = "SELECT ETAT FROM SALLE WHERE id = ?";
		Object[] data = { id };
		Salles.instance.prepare(sql);
		ResultSet res = (ResultSet) Salles.instance.execute(data, false);
		try {
			retour = Etat.valueOf(res.getString("ETAT"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static Salles[] getByNB(int deb, int nb) {
		String sql = "SELECT NUMSALLE FROM SALLE LIMIT ? , ?";
		Salles.instance.prepare(sql);
		Object[] data = { deb, nb };
		ResultSet res = (ResultSet) Salles.instance.execute(data, false);
		Salles[] retour = null;
		try {
			retour = new Salles[res.getRow()];
			res.beforeFirst();
			for (int i = 0; res.next(); i++) {
				retour[i] = new Salles(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Salles[] getAll() throws FileNotFoundException {
		String sql = "SELECT NUMSALLE FROM SALLE WHERE NUMRESTO = ? ORDER BY NUMRESTO";
		Object[] data = {ResteauConfig.getResteauID()};
		Salles.instance.prepare(sql);
		ResultSet res = (ResultSet) Salles.instance.execute(data, false);
		Salles[] retour = null;
		try {
			res.last();
			retour = new Salles[res.getRow()];
			res.beforeFirst();
			for (int i = 0; res.next(); i++) {
				retour[i] = new Salles(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public void create() {
		String sql = "INSERT INTO SALLE(`NOMSALLE`,`NOMBRETABLES`,`ETATS`,`NUMRESTO`) VALUE (?,?,?,?)";
		Object[] data = { this.nomSalle, this.nombreTables,
				this.etat.toString(), this.restaurant.getNumResto() };
		Salles.instance.prepare(sql);
		Salles.instance.execute(data, true);
		sql = "SELECT MAX(NUMPERSO) FROM PERSONNEL";
		Salles.instance.prepare(sql);
		ResultSet res = (ResultSet) Salles.instance.execute();
		// insertion des menu dans la table servir
		if (this.menus != null) {
			sql = "INSERT INTO SERVIR(NUMSALLE,NUMMENU) VALUE (?,?)";
			Salles.instance.prepare(sql);
			data[0] = this.numSalle;
			for (Menu m : this.menus) {
				data[1] = m.getNumMenu();
				Salles.instance.execute(data, true);
			}
		}
		try {
			if (res.next())
				this.numSalle = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void modif() {
		String sql = "UPDATE SALLE SET NOMSALLE = ?, NOMBRETABLES = ?, ETATS = ?, NUMRESTO = ? WHERE NUMSALLE = ? ";
		Object[] data = { this.nomSalle, this.nombreTables,
				this.etat.toString(), this.restaurant.getNumResto(),
				this.numSalle };
		Salles.instance.prepare(sql);
		Salles.instance.execute(data, true);
	}

	public static void delete(int id) {
		String sql = "DELETE FROM SALLE WHERE NUMSALLE = ?";
		Object[] data = { id };
		Salles.instance.prepare(sql);
		Salles.instance.execute(data, true);
	}

	public void delete() {
		String sql = "DELETE FROM SALLE WHERE NUMSALLE = ?";
		Object[] data = { this.numSalle };
		Salles.instance.prepare(sql);
		Salles.instance.execute(data, true);
	}

	public java.util.Collection<Menu> getMenu() {
		if (menus == null)
			menus = new ArrayList<Menu>();
		return menus;
	}

	public java.util.Iterator getIteratorMenu() {
		if (menus == null)
			menus = new ArrayList<Menu>();
		return menus.iterator();
	}

	public void setMenu(java.util.Collection<Menu> newMenu) {
		removeAllMenu();
		for (java.util.Iterator iter = newMenu.iterator(); iter.hasNext();)
			addMenu((Menu) iter.next());
	}

	public void addMenu(Menu newMenu) {
		if (newMenu != null) {
			if (this.menus == null) {
				this.menus = new ArrayList<Menu>();
			}
			if (!this.menus.contains(newMenu)) {
				this.menus.add(newMenu);
				String sql = "INSERT INTO SERVIR(NUMSALLE,NUMMENU) VALUE (?,?)";
				Object[] data = { this.numSalle, newMenu.getNumMenu() };
				Salles.instance.prepare(sql);
				Salles.instance.execute(data, true);
			}
		}
	}

	public void removeMenu(Menu oldMenu) {
		if (oldMenu == null)
			return;
		if (this.menus != null)
			if (this.menus.contains(oldMenu))
				this.menus.remove(oldMenu);
	}

	public void removeAllMenu() {
		if (menus != null)
			menus.clear();
	}

	/**
	 * @return the numSalle
	 */
	public int getNumSalle1() {
		return numSalle;
	}

	/**
	 * @return the numResto
	 */
	public int getNumResto() {
		return numResto;
	}

	/**
	 * @param numResto
	 *            the numResto to set
	 */
	public void setnumResto(int numResto) {
		this.numResto = numResto;
		this.modif();
	}

	/**
	 * @return the nomSalle
	 */
	public String getNomSalle() {
		return nomSalle;
	}

	/**
	 * @param nomSalle
	 *            the nomSalle to set
	 */
	public void setnomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
		this.modif();
	}

	/**
	 * @return the nombreTables
	 */
	public int getNombreTables() {
		return nombreTables;
	}

	public int getTableDispo() {
		return this.nbTableDispo;
	}

	public void setTableDispo(int nb) {
		this.nbTableDispo = nb;
		this.modif();
	}

	/**
	 * @param numSalle
	 *            the numSalle to set
	 */
	public void setnombreTables(int nombreTables) {
		this.nombreTables = nombreTables;
		this.modif();
	}

	/**
	 * @return the etat
	 */
	public Etat getEtat() {
		return etat;
	}

	/**
	 * @param etat
	 *            the etat to set
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
		this.modif();
	}

	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant
	 *            the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.modif();
	}

	@Override
	public int compareTo(Salles o) {
		int retour = 0;
		if (this.nombreTables > o.nombreTables) {
			retour = 1;
		} else if (this.nombreTables < o.nombreTables) {
			retour = -1;
		}

		if (retour == 0) {
			if (this.etat.equals(Etat.libre)
					&& (o.etat.equals(Etat.horsservice) || o.etat
							.equals(Etat.reserve))) {
				retour = 1;
			} else if ((this.etat.equals(Etat.horsservice) || this.etat
					.equals(Etat.reserve)) && o.etat.equals(Etat.libre)) {
				retour = -1;
			}
		}
		return retour;
	}

	@Override
	public String toString() {
		String nom = this.getNomSalle();
		return nom;

	}

}