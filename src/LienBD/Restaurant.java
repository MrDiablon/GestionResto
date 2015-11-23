/***********************************************************************
 * Module:  Restaurant.java
 * Author:  user
 * Purpose: Defines the Class Restaurant
 ***********************************************************************/
package LienBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Restaurant implements Comparable<Restaurant>{
	
	private int numResto;
	private float marge;
	private int nbSalles;
	private int nbEmployes;
	private String adresse;
	private String pays;
	private String numTel;
	private String ville;
	private String cp;
	private String nomResto;

	private static myPDO instance = myPDO.getInstance();

	public Restaurant(String nomResto, float marge, int nbSalles, int nbEmployes,
			String adresse, String pays, String numTel, String ville, String cp) {
		super();
		this.nomResto = nomResto;
		this.marge = marge;
		this.nbSalles = nbSalles;
		this.nbEmployes = nbEmployes;
		this.adresse = adresse;
		this.pays = pays;
		this.numTel = numTel;
		this.ville = ville;
		this.cp = cp;
		this.create();
	}

	public Restaurant(int id) throws SQLException {
		String sql = "SELECT * FROM RESTAURANT WHERE NUMRESTO = ?";
		Restaurant.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Restaurant.instance.execute(data, false);
		this.numResto = id;
		if(res.next()){
			this.nomResto = res.getString("NOMRESTO");
			this.marge = res.getFloat("MARGE");
			this.nbSalles = res.getInt("NBSALLES");
			this.nbEmployes = res.getInt("NBEMPLOYE");
			this.adresse = res.getString("ADRESSE");
			this.pays = res.getString("PAYS");
			this.numTel = res.getString("NUMTEL");
			this.ville = res.getString("VILLE");
			this.cp = res.getString("CP");
		}
	}

	public int getNumResto() {
		return numResto;
	}
	
	public String getNomResto(){
		return this.nomResto;
	}
	
	public void setNomResto(String nomResto){
		this.nomResto = nomResto;
	}
	
	public float getMarge() {
		return marge;
	}

	public void setMarge(int marge) {
		this.marge = marge;
	}

	public int getNbSalles() {
		return nbSalles;
	}

	public void setNbSalles(int nbSalles) {
		this.nbSalles = nbSalles;
	}

	public int getNbEmployes() {
		return nbEmployes;
	}

	public void setNbEmployes(int nbEmployes) {
		this.nbEmployes = nbEmployes;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public void create() {
		String sql = "INSERT INTO RESTO(`NUMRESTO`,`MARGE`,`NBSALLES`,`NBEMPLOYEE`, `ADRESSE`,`PAYS`,`NUMTEL`,`VILLE`,`CP`)"
				+ "VALUES (?,?,?,?)";
		Restaurant.instance.prepare(sql);
		Object[] data = { this.numResto, this.marge, this.nbSalles,
				this.nbEmployes, this.adresse, this.pays, this.numTel,
				this.ville, this.cp };
		Restaurant.instance.execute(data, true);
	}

	public void delete(int id) {
		String sql = "DELETE FROM RESTAURANT WHERE NUMRESTO = ? ";
		Restaurant.instance.prepare(sql);
		Object[] data = { id };
		Restaurant.instance.execute(data, true);
	}

	public void modif() {
		String sql = "UPDATE `RESTAURANT` SET `NUMRESTO` = ?,`MARGE` = ?,`NBSALLES` = ?,`NBEMPLOYEE` = ?, `ADRESSE` = ?,`PAYS` = ?,`NUMTEL` = ?,`VILLE` = ?,`CP` = ?";
		Object[] data = { this.numResto, this.marge, this.nbSalles,
				this.nbEmployes, this.adresse, this.pays, this.numTel,
				this.ville, this.cp };
		Restaurant.instance.execute(data, true);
	}

	public static Restaurant[] getAll() {
		// on ecrit notre code sql on demande tous les id de la table
		String sql = "SELECT NUMRESTO FROM RESTAURANT";
		// on prepare notre requete
		Restaurant.instance.prepare(sql);
		// on l'execute sans parametre car inutile
		ResultSet res = Restaurant.instance.execute();
		// on prepare notre tableau de retour;
		Restaurant[] retour = null;
		try {
			//on place le curseur a la fin pour savoir le nombre de resultat
			res.last();
			// on instance notre retour grace a res.getRow() qui donne le nombre
			// de ligne retourner
			retour = new Restaurant[res.getRow()];
			// on remet le curseur au debut
			res.beforeFirst();
			// pour chaque ligne on cree une nouvelle instance grace a l'id
			for (int i = 0; res.next(); i++) {
				retour[i] = new Restaurant(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public static Restaurant[] getByNB(int deb, int nb) {
		String sql = "SELECT NUMRESTO FROM RESTAURANT LIMIT ? , ?";
		Restaurant.instance.prepare(sql);
		Object[] data = { deb, nb };
		ResultSet res = Restaurant.instance.execute(data, false);
		Restaurant[] retour = null;
		try {
			retour = new Restaurant[res.getRow()];
			res.beforeFirst();
			for (int i = 0; res.next(); i++) {
				retour[i] = new Restaurant(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}
	
	@Override
	public int compareTo(Restaurant o) {
		int res = 0;
		if (this.numResto < o.getNumResto())
			res = -1;
		else if (this.numResto > o.getNumResto()){
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
			Restaurant restaurant = (Restaurant) obj;
			res = ( ((Restaurant) this).equals(restaurant) ) && (this.numResto == restaurant.getNumResto());
		}
		return res;
	}
	
	@Override
	public String toString(){
		return this.nomResto;
	}
}