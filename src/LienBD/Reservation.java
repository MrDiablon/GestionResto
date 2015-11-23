package LienBD;

/***********************************************************************
 * Module:  Reservation.java
 * Author:  Prj 03
 * Purpose: Defines the Class Reservation
 ***********************************************************************/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Reservation implements Comparable<Reservation> {

	private int numReservation;
	private int numTable;
	private Date dateReserv = Calendar.getInstance().getTime();
	private String nomClient;
	private int nbPers;
	private static myPDO instance = myPDO.getInstance();

	public Reservation(int numR, Date date, String nom, int nb, int numTable) {
		this.numReservation = numR;
		this.dateReserv = date;
		this.nomClient = nom;
		this.nbPers = nb;
		this.numTable = numTable;
		this.create();
	}

	public Reservation(int id) throws SQLException {
		String sql = "SELECT * FROM INGREDIENT WHERE NUMINGREDIENT = ?";
		Reservation.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Reservation.instance.execute(data, false);
		this.numReservation = id;
		if (res.next()) {
			this.dateReserv = (Date) res.getObject("DATERESERV");
			this.nomClient = res.getString("NOMCLIENT");
			this.nbPers = res.getInt("NBPERS");
			this.numTable = res.getInt("NUMTABLE");
		}

	}

	public int getNumTable() {
		return numTable;
	}

	public void setNumTable(int numTable) {
		this.numTable = numTable;
	}

	public int getNumReservation() {
		return this.numReservation;
	}

	public void setNumReservation(int num) {
		this.numReservation = num;
	}

	public Date getDateReservation() {
		return this.dateReserv;
	}

	public void setDateReservation(Date date) {
		this.dateReserv = date;
	}

	public String getNomClient() {
		return this.nomClient;
	}

	public void setNomClient(String nom) {
		this.nomClient = nom;
	}

	public int getNbPers() {
		return this.nbPers;
	}

	public void setNbPers(int nb) {
		this.nbPers = nb;
	}

	public Date getDateReservByID(int id) throws SQLException {
		String sql = "SELECT DATERESERV FROM RESERVATION WHERE NUMRESERVATION = ?";
		Reservation.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Reservation.instance.execute(data, false);
		this.numReservation = id;
		if (res.next()) {
			this.dateReserv = (Date) res.getObject("DATERESERV");
		}
		return this.dateReserv;
	}

	public Date getNumTableByID(int id) throws SQLException {
		String sql = "SELECT NUMTABLE FROM RESERVATION WHERE NUMRESERVATION = ?";
		Reservation.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Reservation.instance.execute(data, false);
		this.numReservation = id;
		if (res.next()) {
			this.dateReserv = (Date) res.getObject("DATERESERV");
		}

		return this.dateReserv;
	}

	@Override
	public String toString() {
		return "Reservation [numReservation=" + numReservation
				+ ", dateReserv=" + dateReserv + ", nomClient=" + nomClient
				+ ", nbPers=" + nbPers + "]";
	}

	public void create() {
		String sql = "INSERT INTO RESERVATION(`NUMRESERVATION`,`DATERESERV`,`NOMCLIENT`,`NBPERS`)"
				+ "VALUES (?,?,?,?)";
		Reservation.instance.prepare(sql);
		Object[] data = { this.numReservation, this.dateReserv, this.nomClient,
				this.nbPers };
		Reservation.instance.execute(data, true);
	}

	public void delete(int id) {
		String sql = "DELETE FROM RESERVATION WHERE NUMRESERVATION = ? ";
		Reservation.instance.prepare(sql);
		Object[] data = { id };
		Reservation.instance.execute(data, true);
	}

	public void modif() {
		String sql = "UPDATE `RESERVATION` SET `NUMRESERVATION` = ?,`DATERESERV` = ?,`NOMCLIENT` = ?,`NBPERS` = ?";
		Object[] data = { this.numReservation, this.dateReserv, this.nomClient,
				this.nbPers };
		Reservation.instance.execute(data, true);
	}

	public static Reservation[] getAll() {
		// on ecrit notre code sql on demande tous les id de la table
		String sql = "SELECT NUMRESERV FROM RESERVATION";
		// on prepare notre requete
		Reservation.instance.prepare(sql);
		// on l'execute sans parametre car inutile
		ResultSet res = Reservation.instance.execute();
		// on prepare notre tableau de retour;
		Reservation[] retour = null;
		try {
			// on instance notre retour grace a res.getRow() qui donne le nombre
			// de ligne retourner
			retour = new Reservation[res.getRow()];
			// on remet le curseur au debut
			res.beforeFirst();
			// pour chaque ligne on cree une nouvelle instance grace a l'id
			for (int i = 0; res.next(); i++) {
				retour[i] = new Reservation(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public static Reservation[] getByNB(int deb, int nb) {
		String sql = "SELECT NUMRESERV FROM RESERVATION LIMIT ? , ?";
		Reservation.instance.prepare(sql);
		Object[] data = { deb, nb };
		ResultSet res = Reservation.instance.execute(data, false);
		Reservation[] retour = null;
		try {
			retour = new Reservation[res.getRow()];
			res.beforeFirst();
			for (int i = 0; res.next(); i++) {
				retour[i] = new Reservation(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	@Override
	public int compareTo(Reservation o) {
		int res = 0;
		if (this.numReservation < o.getNumReservation())
			res = -1;
		else if (this.numReservation > o.getNumReservation()) {
			res = 1;
		}
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj == this)
			res = true;
		else if (obj instanceof Reservation) {
			Reservation reservation = (Reservation) obj;
			res = (((Reservation) this).equals(reservation))
					&& (this.numReservation == reservation.numReservation);
		}
		return res;
	}

}