package LienBD;

import java.sql.SQLException;
import java.util.*;

import javax.swing.JList;

import com.mysql.jdbc.ResultSet;

public class Table implements Comparable<Table> {

	private int num;
	private int numSalle;
	private int capacite;
	private Etat etat;
	private Salles salles;
	private ArrayList<Menu> menu;
	private ArrayList<Reservation> reservation;

	private static myPDO instance = myPDO.getInstance();

	/**
	 * Constructeur de la class table qui instancie tous ce attribut grace au
	 * doonees stocker dans la base de donnée
	 * 
	 * @param id
	 *            le numerot des la table a instancier
	 */
	public Table(int id) {
		// questionnement pour les information sur la table
		String sql = "SELECT * FROM TABLES WHERE NUMTABLE = ?";
		Object[] data = { id };
		Table.instance.prepare(sql);
		ResultSet res = (ResultSet) Table.instance.execute(data, false);
		// questionnement pour les menu servi
		sql = "SELECT * FROM COMMANDER WHERE NUMTABLE = ?";
		Table.instance.prepare(sql);
		ResultSet res2 = (ResultSet) Table.instance.execute(data, false);
		// questionnement pour recuperer les reservation
		sql = "SELECT * FROM RESERVER WHERE NUMTABLE = ?";
		Table.instance.prepare(sql);
		ResultSet res3 = (ResultSet) Table.instance.execute(data, false);
		// instanciation des arrayList
		this.menu = new ArrayList<Menu>();
		this.reservation = new ArrayList<Reservation>();
		// recuperation des information
		try {
			if (res.next()) {
				this.num = id;
				this.numSalle = res.getInt("NUMSALLE");
				this.capacite = res.getInt("CAPACITE");
				this.etat = Etat.valueOf(res.getString("ETATS"));
			}
			this.salles = new Salles(this.numSalle);
			while (res2.next()) {
				this.menu.add(new Menu(res2.getInt(1)));
			}

			while (res3.next()) {
				this.reservation.add(new Reservation(res3.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructeur qui instancie les attribut grae au argumet et cree une ligne
	 * dans la base de donnée
	 * 
	 * @param capacite
	 *            le nombre de persoone pouvant ce metre sur la table
	 * @param etat
	 *            l'etat de la table
	 * @param menus
	 *            La liste des menus que sert la table
	 * @param reservations
	 *            la liste des table reserver
	 * 
	 * @throws SQLException
	 */

	public Table(int numSalle, int capacite, Etat etat, ArrayList<Menu> menus,
			ArrayList<Reservation> reservations) throws SQLException {
		
		this.numSalle = numSalle;
		this.capacite = capacite;
		this.etat = etat;
		this.menu = menus;
		this.reservation = reservations;
		this.salles = new Salles(numSalle);

		this.create();

	}

	/*
	 * GETTER
	 */

	/**
	 * @return the numTable
	 */
	public int getNumTable() {
		return num;
	}

	/**
	 * @return the numSalle
	 */
	public int getNumSalle() {
		return numSalle;
	}

	/**
	 * 
	 * @return the capacite
	 */
	public int getCapacite() {
		return capacite;
	}

	/**
	 * @return the etat
	 */
	public Etat getEtat() {
		return etat;
	}

	/**
	 * @return the salles
	 */
	public Salles getSalles() {
		return salles;
	}

	/**
	 * @return the menu
	 */
	public ArrayList<Menu> getMenu() {
		return menu;
	}

	/**
	 * @return the reservation
	 */
	public ArrayList<Reservation> getReservation() {
		return reservation;
	}

	/*
	 * SETTER
	 */

	/**
	 * @param numTable
	 *            the numTable to set
	 */
	public void setNumTable(int numTable) {
		this.num = numTable;
	}

	/**
	 * @param numSalle
	 *            the numSalle to set
	 */
	public void setNumSalle(int numSalle) {
		this.numSalle = numSalle;
	}

	/**
	 * @param capacite
	 *            the capacite to set
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	/**
	 * @param etat
	 *            the etat to set
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	/**
	 * @param salles
	 *            the salles to set
	 */
	public void setSalles(Salles salles) {
		this.salles = salles;
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(ArrayList<Menu> menu) {
		this.menu = menu;
	}

	/**
	 * @param reservation
	 *            the reservation to set
	 */
	public void setReservation(ArrayList<Reservation> reservation) {
		this.reservation = reservation;
	}

	public static Table[] getAll(int numSalle) throws SQLException {
		Table[] retour = null;
		String sql = "SELECT NUMTABLE FROM TABLES WHERE NUMSALLE = ?";
		instance.prepare(sql);
		Object[] data = { numSalle };
		java.sql.ResultSet res = instance.execute(data, false);
		res.last();
		retour = new Table[res.getRow()];
		res.beforeFirst();
		for (int i = 0; res.next(); i++) {
			retour[i] = new Table(res.getInt(1));
		}
		return retour;

	}

	/*
	 * Autre methodes
	 */

	/**
	 * Permet l'ajout d'une ligne dans la table tables de la base de donnée en
	 * fonction de attribut de la class
	 */
	private void create() {
		String sql = "INSERT INTO TABLES (`NUMSALLE`,`CAPACITE`,`ETATS`) VALUE (?,?,?)";
		Object[] data = { this.numSalle, this.capacite, this.etat.toString() };
		instance.prepare(sql);
		instance.execute(data, true);
		sql = "SELECT MAX(NUMTABLE) FROM TABLES";
		instance.prepare(sql);
		ResultSet res = (ResultSet) instance.execute();
		try {
			if (res.next())
				this.num = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de donner des nouvelle valeur a la ligne correspondent a notre
	 * instance dans la base dedonnée en fonctione de nos attribut
	 */
	public void modif() {
		String sql = "UPDATE TABLES SET NUMSALLE = ?, CAPACITE = ?,ETATS = ?, WHERE NUMTABLE = ? ";
		Object[] data = { this.numSalle, this.capacite, this.etat.toString() };
		Table.instance.prepare(sql);
		Table.instance.execute(data, true);
	}

	/**
	 * permet d'efface la ligne indiquer en parametre de la table TABLES
	 * 
	 * @param id
	 *            le NUMTABLE a effacer
	 */
	public static void delete(int id) {
		String sql = "DELETE FROM TABLES WHERE NUMTABLE = ?";
		Object[] data = { id };
		instance.prepare(sql);
		instance.execute(data, true);
	}

	/**
	 * permet de supprimer la ligne correspondant a notre instance
	 */
	public void delete() {
		String sql = "DELETE FROM TABLES WHERE NUMTABLE = ?";
		Object[] data = { this.num };
		instance.prepare(sql);
		instance.execute(data, true);
	}

	/**
	 * Methode permettant de trier les table par leur capaciter et si elle sont
	 * egale par leur etat
	 */
	@Override
	public int compareTo(Table o) {
		int retour = 0;

		if (this.capacite > o.capacite) {
			retour = 1;
		} else if (this.capacite < o.capacite) {
			retour = -1;
		}

		if (retour == 0) {
			if (this.etat.toString() == "libre"
					&& o.etat.toString() == "reserve") {
				retour = 1;
			} else if (this.etat.toString() == "reserve"
					&& o.etat.toString() == "libre") {
				retour = -1;
			}
		}

		return retour;
	}
	
	public void addCommand(int numplat){
		String sql = "INSERT INTO `COMMANDER`(NUMTABLE,NUMPLAT,QUANTITE) VALUES(?,?,?)";
		instance.prepare(sql);
		Object[] data = {this.num,numplat,1};
		instance.execute(data, true);
	}
	
	public void deleteCommand(){
		String sql = "DELETE FROM COMMANDER WHERE NUMTABLE=?";
		instance.prepare(sql);
		Object[] data = {this.num};
		instance.execute(data, true);
	}
	
	/*public JList<Plat> getPlats(){
		String sql="SELECT * FROM COMMANDER WHERE NUMTABLE = ?";
		instance.prepare(sql);
		Object[] data = {this.num};
		ResultSet res=(ResultSet) instance.execute(data, false);
		JList<Plat> ret=new JList<Plat>();
		int length=ret.getHeight();
		Plat[] plat = new Plat[length];
			try {
				for(int i=0;res.next();i++)
					plat[i]=(Plat)res.getObject(i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}
			
	}*/
	
	public int getQt(int numplat){
		String sql="SELECT QUANTITE FROM COMMANDER WHERE NUMTABLE=? AND NUMPLAT=?";
		instance.prepare(sql);
		Object[] data = {this.num,numplat};
		ResultSet res=(ResultSet) instance.execute(data, false);
		int ret=0;
		try {
			if(res.next()){
				ret=res.getInt(1);
			}else{
				return 0;
			}
		} catch (SQLException e) {
		}
		return ret;
	}
	
	public void setCommand(int numplat){
		int qt= this.getQt(numplat);
		System.out.println(qt);
		String sql="UPDATE COMMANDER SET `QUANTITE` = " + (qt+1) + " WHERE NUMPLAT = " + numplat + " AND NUMTABLE = " + this.num;
				 //"UPDATE INGREDIENT SET `ETATSI` = ?,`PRIXU` = ?,`STOCK` = ? , `NOM` = ? WHERE NUMINGREDIENT = ?"
		instance.prepare(sql);
		Object[] data = {};
		instance.execute(data,true);
	}

}