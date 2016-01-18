package LienBD;

/***********************************************************************
 * Module:  Reservation.java
 * Author:  Prj 03
 * Purpose: Defines the Class Reservation
 ***********************************************************************/

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Reservation implements Comparable<Reservation> {

	private int numReservation;
	private int numTable;
	private java.sql.Date dateReserv;
	private String hReserv;
	private String nomClient;
	private String prenomClient;

	public java.sql.Date getDateReservation() {
		return dateReserv;
	}

	public void setDateReservation(java.sql.Date dateReserv) {
		this.dateReserv = dateReserv;
	}

	public String gethReserv() {
		return hReserv;
	}

	public void sethReserv(String hReserv) {
		this.hReserv = hReserv;
	}

	public String getPrenomClient() {
		return this.prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	private int nbPers;
	private static myPDO instance = myPDO.getInstance();

	public Reservation(java.sql.Date date, String heure, String nom,
			String prenom, int nb, int numTable) throws SQLException {
		this.dateReserv = date;
		this.hReserv = heure;
		this.nomClient = nom;
		this.prenomClient = prenom;
		this.nbPers = nb;
		this.numTable = numTable;
		this.create();
	
	}

	public Reservation(int id) throws SQLException {
		String sql = "SELECT * FROM RESERVATION WHERE NUMRESERVATION = ?";
		Reservation.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Reservation.instance.execute(data, false);
		this.numReservation = id;
		if (res.next()) {
			this.dateReserv = (java.sql.Date) res.getDate("DATERESERV");
			this.numReservation = (int) res.getInt("NUMRESERVATION");
			this.hReserv = res.getString("HRESERV");
			this.nomClient = res.getString("NOMCLIENT");
			this.prenomClient = res.getString("PRENOMCLIENT");
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
			this.dateReserv = (java.sql.Date) res.getDate("DATERESERV");
			this.hReserv = res.getString("HRESERV");
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
			this.dateReserv = (java.sql.Date) res.getDate("DATERESERV");
			this.hReserv = res.getString("HRESERV");
		}

		return this.dateReserv;
	}

	@Override
	public String toString() {
		return "Reservation [numReservation=" + this.numReservation
				+ ", numTable=" + this.numTable + ", dateReserv="
				+ this.dateReserv + "heure=" + this.hReserv + ", nomClient="
				+ this.nomClient + "prenomClient=" + this.prenomClient
				+ ", nbPers=" + this.nbPers + "]";
	}

	public void create() throws SQLException {
		String sql = "INSERT INTO `RESERVATION` (`DATERESERV`,`HRESERV`,`NOMCLIENT`,`PRENOMCLIENT`,`NBPERS`,`NUMTABLE`)"
				+ " VALUES (?,?,?,?,?,?)";
		Reservation.instance.prepare(sql);
		Object[] data = {(Date)this.dateReserv,
				this.hReserv, this.nomClient, this.prenomClient, this.nbPers,this.numTable 
						};
		Reservation.instance.execute(data, true);
		sql = "SELECT MAX(NUMRESERVATION) FROM RESERVATION ";
		instance.prepare(sql);
		ResultSet res = instance.execute();
		if(res.next()){
			this.numReservation = res.getInt(1);
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM RESERVATION WHERE NUMRESERVATION = ? ";
		Object[] data = { id };
		Reservation.instance.prepare(sql);
		Reservation.instance.execute(data, true);
	}
	
	public void delete(){
		String sql = "DELETE FROM RESERVATION WHERE NUMRESERVATION = ? ";
		Object[] data = { this.numReservation };
		Reservation.instance.prepare(sql);
		Reservation.instance.execute(data, true);
	}

	public void modif() {
		String sql = "UPDATE `RESERVATION` SET `NUMRESERVATION` = ?,`DATERESERV` = ?,`HRESERV` = ?,`NOMCLIENT` = ?,`PRENOMCLIENT` = ?,`NBPERS` = ?,`NUMTABLE` = ?";
		Object[] data = { this.numReservation, (Date) this.dateReserv, this.hReserv,
				this.nomClient, this.prenomClient, this.nbPers, this.numTable };
		Reservation.instance.prepare(sql);
		Reservation.instance.execute(data, true);
	}
	
	public static Reservation[] getAll() {
		// on ecrit notre code sql on demande tous les id de la table
		String sql = "SELECT NUMRESERVATION FROM RESERVATION";
		// on prepare notre requete
		Reservation.instance.prepare(sql);
		// on l'execute sans parametre car inutile
		ResultSet res = Reservation.instance.execute();
		// on prepare notre tableau de retour;
		Reservation[] retour = null;
		try {
			// on instance notre retour grace a res.getRow() qui donne le nombre
			// de ligne retourner
			res.last();
			if (res.getRow() > 0) {
				retour = new Reservation[res.getRow()];
				// on remet le curseur au debut
				res.beforeFirst();
				// pour chaque ligne on cree une nouvelle instance grace a l'id
				for (int i = 0; res.next(); i++) {
					retour[i] = new Reservation(res.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public static Reservation[] getByNB(int deb, int nb) {
		String sql = "SELECT NUMRESERVATION FROM RESERVATION LIMIT ? , ?";
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
			res = ((this.nomClient.equals(((Reservation) obj).nomClient))
					&& (this.prenomClient.equals(((Reservation) obj).prenomClient)));
		}
		return res;
	}

	
}