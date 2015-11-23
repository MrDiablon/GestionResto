package LienBD;

import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.ResultSet;

public class Table {

	private int num;
	private int numSalle;
	private int capacite;
	private Etat etat;
	private Salles salles;
	private java.util.Collection<Menu> menu;
	private java.util.Collection<Reservation> reservation;

	private static myPDO instance = myPDO.getInstance();

	public Table(int id) {
		String sql = "SELECT * FROM TABLE WHERE NUMTABLE = ?";
		Object[] data = { id };
		Table.instance.prepare(sql);
		ResultSet res = (ResultSet) Table.instance.execute(data, false);
		sql = "SELECT * FROM COMMANDER WHERE NUMTABLE = ?";
		Table.instance.prepare(sql);
		ResultSet res2 = (ResultSet) Table.instance.execute(data, false);
		sql = "SELECT * FROM RESERVER WHERE NUMTABLE = ?";
		Table.instance.prepare(sql);
		ResultSet res3 = (ResultSet) Table.instance.execute(data, false);
		try {
			if (res.next()) {
				this.num = id;
				this.numSalle = res.getInt("NUMSALLE");
				this.capacite = res.getInt("CAPACITE");
				this.etat = (Etat) res.getObject("ETAT");
			}

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

	public Table(int numSalle, int capacite, int posX, int posY, Etat etat,
			Collection<Menu> menus, Collection<Reservation> reservations) throws SQLException {
		this.capacite = capacite;
		this.etat = etat;
		this.menu = menus;
		this.reservation = reservations;

		create();

	}

	/**
	 * @return the numTable
	 */
	public int getNumTable() {
		return num;
	}

	/**
	 * @param numTable
	 *            the numTable to set
	 */
	public void setNumTable(int numTable) {
		this.num = numTable;
	}

	/**
	 * @return the numSalle
	 */
	public int getNumSalle() {
		return numSalle;
	}

	/**
	 * @param numSalle
	 *            the numSalle to set
	 */
	public void setNumSalle(int numSalle) {
		this.numSalle = numSalle;
	}

	/**
	 * @return the capacite
	 */
	public int getCapacite() {
		return capacite;
	}

	/**
	 * @param capacite
	 *            the capacite to set
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
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
	}

	/**
	 * @return the salles
	 */
	public Salles getSalles() {
		return salles;
	}

	/**
	 * @param salles
	 *            the salles to set
	 */
	public void setSalles(Salles salles) {
		this.salles = salles;
	}

	/**
	 * @return the menu
	 */
	public java.util.Collection<Menu> getMenu() {
		return menu;
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(java.util.Collection<Menu> menu) {
		this.menu = menu;
	}

	/**
	 * @return the reservation
	 */
	public java.util.Collection<Reservation> getReservation() {
		return reservation;
	}

	/**
	 * @param reservation
	 *            the reservation to set
	 */
	public void setReservation(java.util.Collection<Reservation> reservation) {
		this.reservation = reservation;
	}

	private void create()  {
		String sql = "INSERT INTO TABLES (`NUMSALLE`,`CAPACITE`,`ETATS`, `POSX`, `POSY`) VALUE (?,?,?,?,?)";
		Object[] data = { this.numSalle, this.capacite, this.etat.toString()};
		instance.prepare(sql);
		instance.execute(data, true);
		sql = "SELECT MAX(NUMTABLE) FROM PERSONNEL";
		instance.prepare(sql);
		ResultSet res = (ResultSet) instance.execute();
		try {
			if(res.next())
				this.num = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void modif() {
		String sql = "UPDATE TABLES SET NUMSALLE = ?, CAPACITE = ?,ETATS = ?, WHERE NUMTABLE = ? ";
		Object[] data = { this.numSalle, this.capacite, this.etat.toString()};
		Table.instance.prepare(sql);
		Table.instance.execute(data, true);
	}
	
	public static void delete(int id){
		String sql = "DELETE FROM TABLES WHERE NUMTABLE = ?";
		Object[] data = { id };
		instance.prepare(sql);
		instance.execute(data, true);
	}
	
	public void delete(){
		String sql = "DELETE FROM TABLES WHERE NUMTABLE = ?";
		Object[] data = { this.num };
		instance.prepare(sql);
		instance.execute(data, true);
	}

}