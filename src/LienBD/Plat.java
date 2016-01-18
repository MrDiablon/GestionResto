package LienBD;

/***********************************************************************
 * Module:  Plat.java
 * Author:  user
 * Purpose: Defines the Class Plat
 ***********************************************************************/

import java.util.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import Tools.JDom;

public class Plat extends PrixNourriture implements Comparable<Plat>{
	
	private int numPlat;
	private String nomPlat;
	private float prixU;
	private LinkedList<Ingredient> ingredients = new LinkedList<Ingredient>();

	private static myPDO instance = myPDO.getInstance();

	/**
	 * @pdRoleInfo migr=no name=Ingredient assc=association12
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 *             type=Composition
	 */
	

	public Plat(int id) throws SQLException {
		String sql = "SELECT * FROM PLAT WHERE `NUMPLAT`=?";
		String sql2 = "SELECT NUMINGREDIENT FROM CONSTITUER WHERE `NUMPLAT`=?";
		Plat.instance.prepare(sql);
		Object[] identifiant = { id };
		ResultSet res = instance.execute(identifiant, false);
		Plat.instance.prepare(sql2);
		ResultSet res2 = instance.execute(identifiant, false);
		if(res.next()){
			this.prixU = (float) res.getObject("PRIXU");
			this.numPlat = id;
			this.nomPlat = res.getString("NOMPLAT");
			while(res2.next()){
				this.ingredients.add(new Ingredient(res2.getInt("NUMINGREDIENT")));
			}
		}
			
		
	}

	public Plat(String plat,
			LinkedList<Ingredient> ingredient, float prixU) throws SQLException {
		this.ingredients = ingredient;
		this.nomPlat=plat;
		this.prixU = prixU;
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

	public Collection<Ingredient> getRecette() {
		return ingredients;
	}
	

	public float getPrixU() {
		return prixU;
	}

	public void setPrixU(float prixU) {
		this.prixU = prixU;
	}

	public void create() throws SQLException {
		String sql = "INSERT INTO PLAT(`NUMPLAT`,`NOMPLAT`,`PRIXU`) VALUES (?,?,?)";
		String sql2 = "SELECT MAX(NUMPLAT) FROM PLAT";
		String sql3 = "INSERT INTO CONSTITUER(`NUMPLAT`,`NUMINGREDIENT`) VALUES (?,?)";
		Plat.instance.prepare(sql);
		Object[] data = { this.numPlat, this.nomPlat, this.prixU,
				};
		Plat.instance.execute(data, true);
		Plat.instance.prepare(sql2);
		ResultSet res = instance.execute();
		if(res.next()){
			this.numPlat = res.getInt(1);
		}
		Plat.instance.prepare(sql3);
		data = new Object[2];
		data[0] = this.numPlat;
			for(Ingredient ing : this.ingredients){
				data[1] = ing.getNumIngredient();
				Plat.instance.execute(data, true);
			}
		
	}

	public void delete(int id) {
		String sql = "DELETE FROM `PLAT` WHERE NUMPLAT = ? ";
		Plat.instance.prepare(sql);
		Object[] data = { id };
		Plat.instance.execute(data, true);
	}
	
	public void delete() {
		String sql = "DELETE FROM `PLAT` WHERE NUMPLAT = ? ";
		Plat.instance.prepare(sql);
		Object[] data = { this.numPlat };
		Plat.instance.execute(data, true);
	}

	public void modif() {
		String sql = "UPDATE `PLAT` SET `NUMPLAT` = ?,`INGREDIENTS` = ?,`PRIXU` = ?";
		Plat.instance.prepare(sql);
		Object[] data = { this.numPlat, this.ingredients, this.prixU
				};
		Plat.instance.execute(data, true);
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
			data[0] = res.getObject("`INGREDIENTS`");
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
			res.last();
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
	public LinkedList<Ingredient> getIngredient() {
		if (ingredients == null)
			ingredients = new LinkedList<Ingredient>();
		return ingredients;
	}
	
	@Override
	public String toString(){
		return this.nomPlat;
	}

	@Override
	public int compareTo(Plat o) {
		int retour = 0;
		if(o.numPlat < this.numPlat){
			retour = -1; 
		}
		else 
			retour = 1;
		
		return retour;
		
	}

	@Override
	public boolean equals(Object obj) {
		boolean retour = false;
		if(obj instanceof Plat){
			Plat ing = (Plat) obj;
			if(this.numPlat == ing.numPlat){
				retour = true;
			}
		}
		return retour;
	}
}