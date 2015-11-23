package LienBD;

/***********************************************************************
 * Module:  Plat.java
 * Author:  user
 * Purpose: Defines the Class Plat
 ***********************************************************************/

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Plat {
	
	private int numPlat;
	private String nomPlat;
	private String recette;
	private int prixU;

	private static myPDO instance = myPDO.getInstance();

	/**
	 * @pdRoleInfo migr=no name=Ingredient assc=association12
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 *             type=Composition
	 */
	private java.util.Collection<Ingredient> ingredient;

	public Plat(int id) throws SQLException {
		String sql = "SELECT * FROM PLAT WHERE `NUMPLAT`=?";
		String sql2 = "SELECT NUMINGREDIENT FROM CONSTITUER WHERE `NUMPLAT`=?";
		Plat.instance.prepare(sql);
		Object[] identifiant = { id };
		ResultSet res = instance.execute(identifiant, false);
		Plat.instance.prepare(sql2);
		ResultSet res2 = instance.execute(identifiant, false);
		if(res.next()){
			this.recette = (String) res.getObject("NUMPLAT");
			this.prixU = (int) res.getObject("RECETTE");
			this.numPlat = (int) res.getObject("PRIXU");
			while(res2.next()){
				this.ingredient.add(new Ingredient(res2.getInt("NUMINGREDIENT")));
			}
		}
			
		
	}

	public Plat(int numPlat, int prixU,String plat, String recette,
			java.util.Collection<Ingredient> ingredient) {
		this.ingredient = ingredient;
		this.nomPlat=plat;
		this.numPlat = numPlat;
		this.prixU = prixU;
		this.recette = recette;
		this.create();
	}

	public int getNumPlat() {
		return numPlat;
	}

	public String getNomPlat() {
		return nomPlat;
	}

	public void setNomPlat(String nomPlat) {
		this.nomPlat = nomPlat;
	}

	public void setNumPlat(int numPlat) {
		this.numPlat = numPlat;
	}

	public String getRecette() {
		return recette;
	}

	public void setRecette(String recette) {
		this.recette = recette;
	}
	

	public int getPrixU() {
		return prixU;
	}

	public void setPrixU(int prixU) {
		this.prixU = prixU;
	}

	public void setIngredient(java.util.Collection<Ingredient> ingredient) {
		this.ingredient = ingredient;
	}

	public void create() {
		String sql = "INSERT INTO PLAT(`NUMPLAT`,`NOMPLAT`,`RECETTE`,`PRIXU`) VALUES (?,?,?,?)";
		String sql2 = "INSERT INTO CONSTITUER(`NUMPLAT`,NUMINGREDIENT) VALUES (?,?)";
		Plat.instance.prepare(sql);
		Object[] data = { this.numPlat, this.ingredient, this.prixU,
				this.recette };
		Plat.instance.execute(data, true);
		Plat.instance.prepare(sql2);
		data = new Object[2];
			for(Ingredient ing : this.ingredient){
				data[0] = this.numPlat;
				data[1] = ing;
				Plat.instance.execute(data, true);
			}
		
	}

	public void delete(int id) {
		String sql = "DELETE PLAT WHERE NUMPLAT = ? ";
		Plat.instance.prepare(sql);
		Object[] data = { id };
		Plat.instance.execute(data, true);
	}

	public void modif() {
		String sql = "UPDATE `PLAT` SET `NUMPLAT` = ?,`RECETTE` = ?,`PRIXU` = ?";
		Plat.instance.prepare(sql);
		Object[] data = { this.numPlat, this.recette, this.prixU
				};
		Plat.instance.execute(data, true);
	}

	public static int getNumPlatByID(int id) {
		String sql = "SELECT * FROM PLAT WHERE `NUMPLAT`=?";
		Plat.instance.prepare(sql);
		Object[] identifiant = { id };
		ResultSet res = instance.execute(identifiant, false);
		Object[] data = new Object[1];
		try {
			data[0] = res.getObject("`NUMPLAT`");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int) data[0];
	}

	public static int getPrixUByID(int id) {
		String sql = "SELECT * FROM PLAT WHERE `NUMPLAT`=?";
		Plat.instance.prepare(sql);
		Object[] identifiant = { id };
		ResultSet res = instance.execute(identifiant, false);
		Object[] data = new Object[1];
		try {
			data[0] = res.getObject("`PRIXU`");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int) data[0];
	}

	public static String getRecetteByID(int id) {
		String sql = "SELECT * FROM PLAT WHERE `NUMPLAT`=?";
		Plat.instance.prepare(sql);
		Object[] identifiant = { id };
		ResultSet res = instance.execute(identifiant, false);
		Object[] data = new Object[1];
		try {
			data[0] = res.getObject("`RECETTE`");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) data[0];
	}
	
	public static String getNomPlatByID(int id){
		String sql = "SELECT * FROM PLAT WHERE `NUMPLAT`=?";
		Plat.instance.prepare(sql);
		Object[] identifiant = { id };
		ResultSet res = instance.execute(identifiant, false);
		Object[] data = new Object[1];
		try {
			data[0] = res.getObject("`NOMPLAT`");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) data[0];
	}

	public static Plat[] getAll() {
		// on ecrit notre code sql on demande tous les id de la table
		String sql = "SELECT NUMPLAT FROM PLAT";
		// on prepare notre requete
		Plat.instance.prepare(sql);
		// on l'execute sans parametre car inutile
		ResultSet res = Plat.instance.execute();
		// on prepare notre tableau de retour;
		Plat[] retour = null;
		try {
			// on instance notre retour grace a res.getRow() qui donne le nombre
			// de ligne retourner
			retour = new Plat[res.getRow()];
			// on remet le curseur au debut
			res.beforeFirst();
			// pour chaque ligne on cree une nouvelle instance grace a l'id
			for (int i = 0; res.next(); i++) {
				retour[i] = new Plat(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	/** @pdGenerated default getter */
	public java.util.Collection<Ingredient> getIngredient() {
		if (ingredient == null)
			ingredient = new java.util.HashSet<Ingredient>();
		return ingredient;
	}

}