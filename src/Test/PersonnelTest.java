package Test;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.jdom2.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import LienBD.Personnel;
import LienBD.myPDO;
import Tools.JDom;

public class PersonnelTest {

	public static Personnel ref;
	public static int id;
	public static myPDO instance;
	public static int nbBase;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//myPDO.configure("root", "", "jdbc:mysql://localhost/projets3");
		myPDO.configure("infs3_prj03", "k4t28jb2", "jdbc:mysql://mysql/infs3_prj03");
		instance = myPDO.getInstance();
		id = 1;
		PersonnelTest.ref = new Personnel(id);
		String sql = "SELECT COUNT(NUMPERSO) FROM PERSONNEL";
		instance.prepare(sql);
		ResultSet res = instance.execute();
		if(res.next()){
			nbBase = res.getInt(1);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myPDO.close();
	}
	
	@After
	public void tearDown() throws Exception {
		String sql = "SELECT COUNT(NUMPERSO) FROM PERSONNEL";
		instance.prepare(sql);
		ResultSet res = instance.execute();
		int nbactual = 0;
		if(res.next()){
			nbactual = res.getInt(1);
		}
		while(nbactual > nbBase){
			sql = "DELETE FROM PERSONNEL WHERE NUMPERSO = (SELECT MAX(NUMPERSO) FROM PERSONNEL";
			instance.prepare(sql);
			instance.execute();
			nbactual--;
		}
	}

	@Test
	public void testGetNumRestoById() {
		try {
			System.out.println("ok");
			assertEquals(ref.getNUMRESTO(), (int) Personnel.getNumRestoById(id));
		} catch (Exception e) {
			fail("exception déclaré");
		}
	}

	@Test (expected = Exception.class)
	public void testGetNumRestoByIdNegatif() throws Exception {
		assertEquals(ref.getNUMRESTO(), (int) Personnel.getNumRestoById(-1));
	}

	@Test
	public void testGetNumSaleById() {
		try {
			assertEquals(ref.getNUMSALLE(), (int) Personnel.getNUMSALLEById(id));
		} catch (Exception e) {
			fail("exception d�clar�");
		}
	}

	@Test(expected = Exception.class)
	public void testGetNumSaleByIdNegatif() throws Exception {
		Personnel.getNUMSALLEById(-1);
	}

	@Test
	public void testGetNomById() {
		try {
			assertEquals(ref.getNOM(), Personnel.getNomById(id));
		} catch (Exception e) {
			fail("Exception levée");
		}
	}

	@Test(expected = Exception.class)
	public void testGetNomByIdNegatif() throws Exception {
		Personnel.getNomById(-1);
	}

	@Test
	public void testGetPrenomById() {
		try {
			assertEquals(ref.getPRENOM(), Personnel.getPrenomById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetPrenomByIdNegatif() throws Exception {
		Personnel.getPrenomById(-1);
	}

	@Test
	public void testGetPosteById() {
		try {
			assertEquals(ref.getPOSTE(), Personnel.getPosteById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetPosteByIdNegatif() throws Exception {
		Personnel.getPosteById(-1);
	}

	@Test
	public void testGetAdresseById() {
		try {
			assertEquals(ref.getADRESSE(), Personnel.getAdresseById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetAdresseByIdNegatif() throws Exception {
		Personnel.getAdresseById(-1);
	}

	@Test
	public void testGetHoraireTravById() {
		try {
			assertEquals(ref.getHORAIRETRAV(),
					JDom.convertStringToDocument((String) Personnel
							.getHoraireTravById(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetHoraireTravByIdNegatif() throws Exception {
		Personnel.getHoraireTravById(-1);
	}

	@Test
	public void testGetHorairePrevById() {
		try {
			assertEquals(ref.getHORAIREPREV(),
					JDom.convertStringToDocument((String) Personnel
							.getHorairePrevById(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetHorairePrevByIdNegatif() throws Exception {
		Personnel.getHorairePrevById(-1);
	}

	@Test
	public void testGetSalaireById() {
		try {
			assertEquals(ref.getSALAIRE_H(), Personnel.getSalaireById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetSalaireByIdNegatif() throws Exception {
		Personnel.getSalaireById(-1);
	}

	@Test
	public void testGetDroitsById() {
		try {
			assertEquals(ref.getDROITS(), Personnel.getDroitsById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetDroitsByIdNegatif() throws Exception {
		Personnel.getDroitsById(-1);
	}

	@Test
	public void testVerifCoRight() {
		assertEquals(ref.getNUMPERSO(), Personnel.verifCo("TEST", "test"));
	}

	@Test
	public void testVerifCoWrong() {
		assertEquals(-1, Personnel.verifCo("bisouxbisoux", "ds"));
	}

	@Test
	public void testCreate() {
		int nbAvant = Personnel.count();
		Document xml = JDom
				.convertStringToDocument("<<?xml version=\"1.0\" encoding=\"UTF-8\"?><Personnel><Arrive heure=\"9\" minute=\"00\" /><Depart heure=\"9\" minute=\"00\"/></Personnel>");
		new Personnel(1, 1, "Jean", "Pascal", "Serveur",
				"test", "00", "t@t.t", xml, xml, 15, 1, "test");
		assertEquals(nbAvant + 1, Personnel.count());
		
	}

	@Test
	public void testModif() {
		ref.setADRESSE("16 rue de la modif");
		ref.modif("test");
		try {
			assertEquals(ref.getADRESSE(), Personnel.getAdresseById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		Document xml = JDom
				.convertStringToDocument("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Personnel><Arrive heure=\"9\" minute=\"00\" /><Depart heure=\"9\" minute=\"00\"/></Personnel>");
		new Personnel(1, 1, "Jean", "Pascal", "Serveur",
				"test", "00", "t@t.t", xml, xml, 15, 1, "test");
		int nbAvant = Personnel.count();
		try {
			Personnel.delete(Personnel.count());
			assertEquals(nbAvant - 1, Personnel.count());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAll() {
		try {
			Personnel[] all = Personnel.getAll();
			for(Personnel a : all){
				assertEquals(a.getNOM(),Personnel.getNomById(a.getNUMPERSO()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetByNB1() {
		try {
			Personnel[] pers = Personnel.getByNB(1, 1);
			assertEquals(1, pers.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test public void testGetByNBSup1(){
		try {
			Personnel[] pers = Personnel.getByNB(1, 2);
			assertEquals(2, pers.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
