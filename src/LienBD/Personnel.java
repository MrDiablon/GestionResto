package LienBD;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdom2.*;

import Tools.JDom;

public class Personnel implements Comparable<Personnel> {

	private int NUMPERSO;
	private int NUMRESTO;
	private int NUMSALLE;
	private String NOM;
	private String PRENOM;
	private String POSTE;
	private String ADRESSE;
	private String NUMTEL;
	private String ADRESSEMAIL;
	private Document HORAIRETRAV;
	private Document HORAIREPREV;
	private float SALAIRE_H;
	private int DROITS;
	private static myPDO instance = myPDO.getInstance();

	public Personnel(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("index null ounegatif");
		}
		String sql = "select NUMPERSO,NUMRESTO,NUMSALLE,NOM,PRENOM,POSTE,ADRESSE,NUMTEL,ADRESSEMAIL,HORAIRETRAV,HORAIREPREV,SALAIRE_H,DROITS FROM PERSONNEL WHERE `NUMPERSO` = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				this.NUMPERSO = res.getInt("NUMPERSO");
				this.NUMRESTO = res.getInt("NUMRESTO");
				this.NUMSALLE = res.getInt("NUMSALLE");
				this.NOM = res.getString("NOM");
				this.PRENOM = res.getString("PRENOM");
				this.POSTE = res.getString("POSTE");
				this.ADRESSE = res.getString("ADRESSE");
				this.NUMTEL = res.getString("NUMTEL");
				this.ADRESSEMAIL = res.getString("ADRESSEMAIL");
				this.HORAIRETRAV = (res.getString("HORAIRETRAV") != null)
						? JDom.convertStringToDocument(res.getString("HORAIRETRAV")) : null;
				this.HORAIREPREV = (res.getString("HORAIREPREV") != null)
						? JDom.convertStringToDocument(res.getString("HORAIRETRAV")) : null;
				this.SALAIRE_H = res.getFloat("SALAIRE_H");
				this.DROITS = res.getInt("DROITS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Personnel(int nUMRESTO, int NUMSALLE, String nOM, String pRENOM, String pOSTE, String aDRESSE, String nUMTEL,
			String aDRESSEMAIL, Document hORAIRETRAV, Document hORAIREPREV, float sALAIRE_H, int dROITS, String mdp) {

		this.NUMRESTO = nUMRESTO;
		this.NUMSALLE = NUMSALLE;
		this.NOM = nOM.toUpperCase();
		this.PRENOM = pRENOM;
		this.POSTE = pOSTE;
		this.ADRESSE = aDRESSE;
		this.NUMTEL = nUMTEL;
		this.ADRESSEMAIL = aDRESSEMAIL;
		this.HORAIRETRAV = hORAIRETRAV;
		this.HORAIREPREV = hORAIREPREV;
		this.SALAIRE_H = sALAIRE_H;
		this.DROITS = dROITS;

		this.create(mdp);
	}

	/**
	 * @return the nUMPERSO
	 */
	public int getNUMPERSO() {
		return NUMPERSO;
	}

	/**
	 * @param nUMPERSO
	 *            the nUMPERSO to set
	 */
	public void setNUMPERSO(int nUMPERSO) {
		NUMPERSO = nUMPERSO;
	}

	/**
	 * @return the nUMRESTO
	 */
	public int getNUMRESTO() {
		return NUMRESTO;
	}

	/**
	 * @param nUMRESTO
	 *            the nUMRESTO to set
	 */
	public void setNUMRESTO(int nUMRESTO) {
		NUMRESTO = nUMRESTO;
	}

	/**
	 * @return the NUMSALLE
	 */
	public int getNUMSALLE() {
		return NUMSALLE;
	}

	/**
	 * @param NUMSALLE
	 *            the NUMSALLE to set
	 */
	public void setNUMSALLE(int NUMSALLE) {
		this.NUMSALLE = NUMSALLE;
	}

	public String getNOMSALLE() {
		Salles salle = new Salles(this.NUMSALLE);
		return salle.getNomSalle();
	}

	/**
	 * @return the nOM
	 */
	public String getNOM() {
		return NOM;
	}

	/**
	 * @param nOM
	 *            the nOM to set
	 */
	public void setNOM(String nOM) {
		NOM = nOM;
	}

	/**
	 * @return the pRENOM
	 */
	public String getPRENOM() {
		return PRENOM;
	}

	/**
	 * @param pRENOM
	 *            the pRENOM to set
	 */
	public void setPRENOM(String pRENOM) {
		PRENOM = pRENOM;
	}

	/**
	 * @return the pOSTE
	 */
	public String getPOSTE() {
		return POSTE;
	}

	/**
	 * @param pOSTE
	 *            the pOSTE to set
	 */
	public void setPOSTE(String pOSTE) {
		POSTE = pOSTE;
	}

	/**
	 * @return the aDRESSE
	 */
	public String getADRESSE() {
		return ADRESSE;
	}

	/**
	 * @param aDRESSE
	 *            the aDRESSE to set
	 */
	public void setADRESSE(String aDRESSE) {
		ADRESSE = aDRESSE;
	}

	/**
	 * @return the nUMTEL
	 */
	public String getNUMTEL() {
		return NUMTEL;
	}

	/**
	 * @param nUMTEL
	 *            the nUMTEL to set
	 */
	public void setNUMTEL(String nUMTEL) {
		NUMTEL = nUMTEL;
	}

	/**
	 * @return the aDRESSEMAIL
	 */
	public String getADRESSEMAIL() {
		return ADRESSEMAIL;
	}

	/**
	 * @param aDRESSEMAIL
	 *            the aDRESSEMAIL to set
	 */
	public void setADRESSEMAIL(String aDRESSEMAIL) {
		ADRESSEMAIL = aDRESSEMAIL;
	}

	/**
	 * @return the hORAIRETRAV
	 */
	public Document getHORAIRETRAV() {
		return (Document) HORAIRETRAV;
	}

	/**
	 * @param hORAIRETRAV
	 *            the hORAIRETRAV to set
	 */
	public void setHORAIRETRAV(Document hORAIRETRAV) {
		HORAIRETRAV = hORAIRETRAV;
	}

	/**
	 * @return the hORAIREPREV
	 */
	public Document getHORAIREPREV() {
		return (Document) HORAIREPREV;
	}

	/**
	 * @param hORAIREPREV
	 *            the hORAIREPREV to set
	 */
	public void setHORAIREPREV(Document hORAIREPREV) {
		HORAIREPREV = hORAIREPREV;
	}

	/**
	 * @return the sALAIRE_H
	 */
	public float getSALAIRE_H() {
		return SALAIRE_H;
	}

	/**
	 * @param sALAIRE_H
	 *            the sALAIRE_H to set
	 */
	public void setSALAIRE_H(int sALAIRE_H) {
		SALAIRE_H = sALAIRE_H;
	}

	/**
	 * @return the dROITS
	 */
	public int getDROITS() {
		return DROITS;
	}

	/**
	 * @param dROITS
	 *            the dROITS to set
	 */
	public void setDROITS(int dROITS) {
		DROITS = dROITS;
	}

	public static Object getNumRestoById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT `NUMRESTO` FROM PERSONNEL WHERE `NUMPERSO` = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("NUMRESTO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getNUMSALLEById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT NUMSALLE FROM PERSONNEL WHERE `NUMPERSO` = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("NUMSALLE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getNomById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT NOM FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("NOM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getPrenomById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT PRENOM FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("PRENOM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getPosteById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT POSTE FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("POSTE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getAdresseById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT ADRESSE FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("ADRESSE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getHoraireTravById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT HORAIRETRAV FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = JDom.convertStringToDocument(res.getString("HORAIRETRAV"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getHorairePrevById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT HORAIREPREV FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = JDom.convertStringToDocument(res.getString("HORAIREPREV"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getSalaireById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT SALAIRE FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("SALAIRE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static Object getDroitsById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		Object retour = null;
		String sql = "SELECT DROITS FROM PERSONNEL WHERE NUMPERSO = ?";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				retour = res.getObject("DROITS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static int verifCo(String nom, String mdp) {
		int retour = -1;
		String sql = "SELECT NUMPERSO FROM PERSONNEL WHERE NOM = ? AND MDP = sha1(?)";
		Personnel.instance.prepare(sql);
		Object[] data = { nom.toUpperCase(), mdp };
		ResultSet res = Personnel.instance.execute(data, false);
		try {
			if (res.next()) {
				Object tmp = res.getInt("NUMPERSO");
				retour = (int) tmp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public void create(String mdp) {

		String sql = "INSERT INTO `PERSONNEL` (`NUMPERSO`,`NUMRESTO`,`NUMSALLE`,`NOM`,`PRENOM`,`POSTE`,`ADRESSE`,`NUMTEL`,`ADRESSEMAIL`,`HORAIRETRAV`,`HORAIREPREV`,`SALAIRE_H`,`DROITS`,`MDP`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,sha1(?))";
		Personnel.instance.prepare(sql);
		Object[] data = this.getData(mdp);		
		Personnel.instance.execute(data, true);
		sql = "SELECT MAX(NUMPERSO) FROM PERSONNEL";
		Personnel.instance.prepare(sql);
		ResultSet res = Personnel.instance.execute();
		try {
			if (res.next())
				this.NUMPERSO = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private Object[] getData(String mdp){
		Object[] data = null;
		if(mdp != null){
			if (this.HORAIREPREV != null && this.HORAIRETRAV != null) {
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,JDom.convertDocumentToString(this.HORAIRETRAV),JDom.convertDocumentToString(this.HORAIREPREV),this.SALAIRE_H,this.DROITS,mdp};
			}else if(this.HORAIREPREV != null && this.HORAIRETRAV == null){
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,null,JDom.convertDocumentToString(this.HORAIREPREV),this.SALAIRE_H,this.DROITS,mdp};
			}else if(this.HORAIREPREV == null && this.HORAIRETRAV != null){
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,JDom.convertDocumentToString(this.HORAIRETRAV),null,this.SALAIRE_H,this.DROITS,mdp};
			}else{
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,null,null,this.SALAIRE_H,this.DROITS,mdp};
			}
		}else{
			if (this.HORAIREPREV != null && this.HORAIRETRAV != null) {
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,JDom.convertDocumentToString(this.HORAIRETRAV),JDom.convertDocumentToString(this.HORAIREPREV),this.SALAIRE_H,this.DROITS};
			}else if(this.HORAIREPREV != null && this.HORAIRETRAV == null){
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,null,JDom.convertDocumentToString(this.HORAIREPREV),this.SALAIRE_H,this.DROITS};
			}else if(this.HORAIREPREV == null && this.HORAIRETRAV != null){
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,JDom.convertDocumentToString(this.HORAIRETRAV),null,this.SALAIRE_H,this.DROITS};
			}else{
				data = new Object[]{null,this.NUMRESTO,this.NUMSALLE,this.NOM,this.PRENOM,this.POSTE,this.ADRESSE,this.NUMTEL,this.ADRESSEMAIL,null,null,this.SALAIRE_H,this.DROITS};
			}
		}
		return data;
	}

	public void modif(String mdp) {
		String sql = "";
		Object[] data = null;
		if (mdp != null) {
			sql = "UPDATE `PERSONNEL` SET `NUMRESTO` = ?,`NUMSALLE` = ?,`NOM` = ?,`PRENOM` = ?,`POSTE` = ?,`ADRESSE` = ?,`NUMTEL` = ?,`ADRESSEMAIL` = ?,`HORAIRETRAV` = ?,`HORAIREPREV` = ?,`SALAIRE_H` = ?,`DROITS` = ?,`MDP` = ? WHERE `NUMPERSO` = ?";
			data = this.getData(mdp);
		} else {
			sql = "UPDATE `PERSONNEL` SET `NUMRESTO` = ?,`NUMSALLE` = ?,`NOM` = ?,`PRENOM` = ?,`POSTE` = ?,`ADRESSE` = ?,`NUMTEL` = ?,`ADRESSEMAIL` = ?,`HORAIRETRAV` = ?,`HORAIREPREV` = ?,`SALAIRE_H` = ?,`DROITS` = ? WHERE `NUMPERSO` = ?";
			data = this.getData(null);
		}
		Personnel.instance.prepare(sql);
		Personnel.instance.execute(data, true);
	}

	public static void delete(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("id null ou negatif");
		}
		String sql = "DELETE FROM PERSONNEL WHERE NUMPERSO = ? ";
		Personnel.instance.prepare(sql);
		Object[] data = { id };
		Personnel.instance.execute(data, true);
	}

	public void delete() throws Exception {
		if (this.NUMPERSO <= 0) {
			throw new Exception("id null ou negatif");
		}
		String sql = "DELETE FROM PERSONNEL WHERE NUMPERSO = ? ";
		Personnel.instance.prepare(sql);
		Object[] data = { NUMPERSO };
		Personnel.instance.execute(data, true);
	}

	public static Personnel[] getAll() throws Exception {
		// on ecrit notre code sql on demande tous les id de la table
		String sql = "SELECT NUMPERSO FROM PERSONNEL";
		// on prepare notre requete
		Personnel.instance.prepare(sql);
		// on l'execute sans parametre car inutile
		ResultSet res = Personnel.instance.execute();
		// on prepare notre tableau de retour;
		Personnel[] retour = null;
		try {
			res.last();
			// on instance notre retour grace a res.getRow() qui donne le nombre
			// de ligne retourner
			res.last();
			retour = new Personnel[res.getRow()];
			// on remet le curseur au debut
			res.beforeFirst();
			// pour chaque ligne on cree une nouvelle instance grace a l'id
			for (int i = 0; res.next(); i++) {
				retour[i] = new Personnel(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public static Personnel[] getByNB(int deb, int nb) throws Exception {
		String sql = "SELECT NUMPERSO FROM PERSONNEL LIMIT ? , ?";
		Personnel.instance.prepare(sql);
		Object[] data = { deb, nb };
		ResultSet res = Personnel.instance.execute(data, false);
		Personnel[] retour = null;
		try {
			res.last();
			retour = new Personnel[res.getRow()];
			res.beforeFirst();
			for (int i = 1; res.next(); i++) {
				retour[i] = new Personnel(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	public static int count() {
		int retour = 0;
		String sql = "SELECT COUNT(NUMPERSO) FROM PERSONNEL";
		Personnel.instance.prepare(sql);
		ResultSet res = Personnel.instance.execute();
		try {
			if (res.next()) {
				retour = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retour;
	}

	@Override
	public int compareTo(Personnel p) {
		int retour = 0;
		System.out.println(p.NOM);
		System.out.println(this.NOM);
		retour = this.NOM.compareTo(p.NOM);
		if (retour == 0) {
			retour = this.PRENOM.compareTo(p.NOM);
		}
		return retour;
	}
}
