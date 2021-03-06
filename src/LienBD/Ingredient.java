package LienBD;

/***********************************************************************
 * Module:  Ingredient.java
 * Author:  user
 * Purpose: Defines the Class Ingredient
 ***********************************************************************/

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ingredient implements Comparable<Ingredient> {

	private int numIngredient;
	private EtatI etatI;
	private float prixU;
	private int stock;
	private String nom;

	private static myPDO instance = myPDO.getInstance();

	public Ingredient(float prixU, int stock, EtatI etatI, String nom) throws SQLException {
		this.etatI = etatI;
		this.prixU = prixU;
		this.stock = stock;
		this.nom = nom;
		this.create();
	}

	public Ingredient(int id) throws NullPointerException, SQLException {
		String sql = "SELECT * FROM INGREDIENT WHERE NUMINGREDIENT = ?";
		Ingredient.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Ingredient.instance.execute(data, false);

		this.numIngredient = id;
		if (res.next()) {
			this.etatI = EtatI.valueOf(res.getString("ETATSI"));
			this.prixU = res.getFloat("PRIXU");
			this.stock = res.getInt("STOCK");
			this.nom = res.getString("NOM");
		} else {
			throw new NullPointerException();
		}

	}

	/**
	 * @return the numIngredient
	 */
	public int getNumIngredient() {
		return this.numIngredient;
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
	public float getPrixU() {
		return prixU;
	}

	/**
	 * @param prix
	 *            the prixU to set
	 */
	public void setPrixU(float prix) {
		this.prixU = prix;
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

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param etatI
	 *            the etatI to set
	 */
	public void setEtatI(EtatI etatI) {
		this.etatI = etatI;
	}

	public void create() throws SQLException {
		String sql = "INSERT INTO INGREDIENT(`NUMINGREDIENT`,`ETATSI`,`PRIXU`,`STOCK`,`NOM`) VALUES (?,?,?,?,?)";
		Object[] data = { this.numIngredient, this.etatI.toString(), this.prixU, this.stock, this.nom };
		Ingredient.instance.prepare(sql);
		Ingredient.instance.execute(data, true);
		sql = "SELECT MAX(NUMINGREDIENT) FROM INGREDIENT";
		Ingredient.instance.prepare(sql);
		ResultSet res = Ingredient.instance.execute();
		if (res.next()) {
			this.numIngredient = res.getInt(1);
		}

	}

	public static void delete(int id) {
		String sql = "DELETE FROM INGREDIENT WHERE NUMINGREDIENT = ? ";
		Ingredient.instance.prepare(sql);
		Object[] data = { id };
		Ingredient.instance.execute(data, true);
	}

	public void delete() {
		String sql = "DELETE FROM INGREDIENT WHERE NUMINGREDIENT = ? ";
		Ingredient.instance.prepare(sql);
		Object[] data = { this.numIngredient };
		Ingredient.instance.execute(data, true);
	}

	public void modif() {
		String sql = "UPDATE INGREDIENT SET `ETATSI` = ?,`PRIXU` = ?,`STOCK` = ? , `NOM` = ? WHERE NUMINGREDIENT = ?";
		Ingredient.instance.prepare(sql);
		Object[] data = { this.etatI.toString(), this.prixU, this.stock, this.nom, this.numIngredient };
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