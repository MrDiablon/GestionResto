package LienBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeLien {

	private int numTable;
	private float prix;
	private java.util.Collection<Plat> plats;
	private int numCommande;
	
	private static myPDO instance = myPDO.getInstance();
	
	public CommandeLien(int id) throws SQLException {
		String sql = "SELECT * FROM COMMANDE WHERE `NUMCOMMANDE`=?";
		CommandeLien.instance.prepare(sql);
		String sql2="SELECT `NUMTABLE` FROM TABLE WHERE `NUMCOMMANDE` =?";
		Object[] identifiant = { id };
		ResultSet res = instance.execute(identifiant, false);
		CommandeLien.instance.prepare(sql2);
		ResultSet res2 = instance.execute(identifiant, false);
		if(res2.next())
		    this.numTable = new Table(res.getInt("NUMTABLE")).getNumTable();
		if (res.next()) {
			//this.recette = (String) res.getObject("RECETTE");
			this.prix = res.getFloat("PRIX");
			this.numCommande = id;
		}

	}
	
	public CommandeLien(int numCommande, float prix, int table, java.util.Collection<Plat> plat) {
		this.plats = plat;
		this.numCommande = numCommande;
		this.prix = prix;
		this.numTable = table;
		this.create();
	}

	public int getTable() {
		return numTable;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public java.util.Collection<Plat> getPlats() {
		return plats;
	}

	public void setPlats(java.util.Collection<Plat> plats) {
		this.plats = plats;
	}

	public int getNumCommande() {
		return numCommande;
	}
	public void create() {
		String sql = "INSERT INTO COMMANDE(`NUMCOMMANDE`,`PLATS`,`PRIX`,`NUMTABLE`) VALUES (?,?,?,?)";
		CommandeLien.instance.prepare(sql);
		Object[] data = { this.numCommande, this.plats, this.prix, this.numTable };
		CommandeLien.instance.execute(data, true);//
		sql = "SELECT MAX(NUMCOMMANDE) FROM COMMANDE";
		CommandeLien.instance.prepare(sql);
		ResultSet res = (ResultSet) CommandeLien.instance.execute();
		if (new Table(this.numTable) != null) {
			sql = "INSERT INTO TABLE(NUMCOMMANDE) VALUE (?)";
			CommandeLien.instance.prepare(sql);
			data[0] = this.numCommande;
				data[1] = this.numTable;
				CommandeLien.instance.execute(data, true);
		}
		try {
			if (res.next())
				this.numCommande = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void modif() {
		String sql = "UPDATE COMMANDE SET NUMCOMMANDE = ?, NUMTABLE = ?, PLATS = ?, PRIX = ? WHERE NUMCOMMANDE = ? ";
		Object[] data = { this.numCommande, this.numTable,this.plats,this.prix,this.numCommande };
		CommandeLien.instance.prepare(sql);
		CommandeLien.instance.execute(data, true);
	}
	
	public static void delete(int id) {
		String sql = "DELETE FROM COMMANDE WHERE NUMCOMMANDE = ?";
		Object[] data = { id };
		CommandeLien.instance.prepare(sql);
		CommandeLien.instance.execute(data, true);
	}
	
	public void delete() {
		String sql = "DELETE FROM COMMANDE WHERE NUMCOMMANDE = ?";
		Object[] data = { this.numCommande };
		CommandeLien.instance.prepare(sql);
		CommandeLien.instance.execute(data, true);
	}
	
	public void addPlat(Plat newp) {
		if (newp != null) {
			if (this.plats == null) {
				this.plats = new ArrayList<Plat>();
			}
			if (!this.plats.contains(newp)) {
				this.plats.add(newp);
				String sql = "INSERT INTO COMMANDE(NUMCOMMANDE,NUMPLAT) VALUE (?,?)";
				Object[] data = { this.numCommande, newp.getNumPlat() };
				CommandeLien.instance.prepare(sql);
				CommandeLien.instance.execute(data, true);
			}
		}
	}
	
	public void deleteAll(){
		String sql = " DELETE * FROM LISTPLAT WHERE NUMLIST = ?";
		CommandeLien.instance.prepare(sql);
		Object[] data = { this.numCommande};
		CommandeLien.instance.execute(data, false);
	}
	
	public static int last(){
		String sql = " SELECT MAX(NUMCOMMANDE) FROM COMMANDE ";
		CommandeLien.instance.prepare(sql);
		int num=0;
		Object[] data = {};
		ResultSet res = instance.execute(data, false);
		if(res!= null){
		try {
			num= res.getInt("NUMCOMMANDE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return num;
	}

}
