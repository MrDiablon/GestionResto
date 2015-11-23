package LienBD;

/***********************************************************************
 * Module:  Ingredient.java
 * Author:  user
 * Purpose: Defines the Class Ingredient
 ***********************************************************************/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Ingredient implements Comparable<Ingredient> {

	private int numIngredient;
	private EtatI etatI;
	private int prixU;
	private int stock;
	private String nom;

	private static myPDO instance = myPDO.getInstance();

	public Ingredient(int prixU, int stock, EtatI etatI, String nom) throws SQLException {
		this.etatI = etatI;
		this.prixU = prixU;
		this.stock = stock;
		this.nom = nom;
		this.create();
	}

	public Ingredient(int id) {
		String sql = "SELECT * FROM INGREDIENT WHERE NUMINGREDIENT = ?";
		Ingredient.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Ingredient.instance.execute(data, false);

		this.numIngredient = id;
		try {
			if (res.next()) {
				this.etatI = EtatI.valueOf(res.getString("ETATSI"));
				this.prixU = res.getInt("PRIXU");
				this.stock = res.getInt("STOCK");
				this.nom = res.getString("NOM");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the numIngredient
	 */
	public int getNumIngredient() {
		return numIngredient;
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * @param numIngredient
	 *            the numIngredient to set
	 */
	public void setNumIngredient(int numIngredient) {
		this.numIngredient = numIngredient;
	}

	/**
	 * @return the prixU
	 */
	public int getPrixU() {
		return prixU;
	}

	/**
	 * @param prixU
	 *            the prixU to set
	 */
	public void setPrixU(int prixU) {
		this.prixU = prixU;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the etatI
	 */
	public EtatI getEtatI() {
		return etatI;
	}

	/**
	 * @param etatI
	 *            the etatI to set
	 */
	public void setEtatI(EtatI etatI) {
		this.etatI = etatI;
	}

	public void create() throws SQLException {
		String sql = "INSERT INTO INGREDIENT(`NUMINGREDIENT`,`ETATI`,`PRIXU`,`STOCK`,`NOM`) VALUES (?,?,?,?,?)";
		Ingredient.instance.prepare(sql);
		Object[] data = { this.numIngredient, this.etatI, this.prixU,
				this.stock, this.nom };
		Ingredient.instance.execute(data, true);
		sql = "SELECT MAX(NUMINGREDIENT) FROM INGREDIENT";
		ResultSet res = Ingredient.instance.execute();
		if (res.next()) {
			this.numIngredient = res.getInt(1);
		}

	}

	public void delete(int id) {
		String sql = "DELETE FROM INGREDIENT WHERE NUMINGREDIENT = ? ";
		Ingredient.instance.prepare(sql);
		Object[] data = { id };
		Ingredient.instance.execute(data, true);
	}

	public void modif() {
		String sql = "UPDATE `INGREDIENT` SET `NUMINGREDIENT` = ?,`ETATI` = ?,`PRIXU` = ?,`STOCK` = ? , `NOM = ?`";
		Object[] data = { this.numIngredient, this.etatI, this.prixU,
				this.stock,this.nom };
		Ingredient.instance.execute(data, true);
	}

	public static Ingredient[] getAll() {
		// on ecrit notre code sql on demande tous les id de la table
		String sql = "SELECT NUMINGREDIENT FROM INGREDIENT";
		// on prepare notre requete
		Ingredient.instance.prepare(sql);
		// on l'execute sans parametre car inutile
		ResultSet res = Ingredient.instance.execute();
		// on prepare notre tableau de retour;
		Ingredient[] retour = null;
		try {
			// on place le curseur a la fin de la liste;
			res.last();
			// on instance notre retour grace a res.getRow() qui donne le nombre
			// de ligne retourner
			retour = new Ingredient[res.getRow()];
			// on remet le curseur au debut
			res.beforeFirst();
			// pour chaque ligne on cree une nouvelle instance grace a l'id
			for (int i = 0; res.next(); i++) {
				retour[i] = new Ingredient(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public static Ingredient[] getByNB(int deb, int nb) {
		String sql = "SELECT NUMINGREDIENT FROM INGREDIENT LIMIT ? , ?";
		Ingredient.instance.prepare(sql);
		Object[] data = { deb, nb };
		ResultSet res = Ingredient.instance.execute(data, false);
		Ingredient[] retour = null;
		try {
			retour = new Ingredient[res.getRow()];
			res.beforeFirst();
			for (int i = 0; res.next(); i++) {
				retour[i] = new Ingredient(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	@Override
	public int compareTo(Ingredient o) {
		int retour = 0;
		if (retour == 0) {
			if (this.stock > o.stock) {
				retour = 1;
			} else if (this.stock < o.stock) {
				retour = -1;
			}
		}

		if (this.prixU > o.prixU) {
			retour = 1;
		} else if (this.prixU < o.prixU) {
			retour = -1;
		}

		return retour;
	}

}