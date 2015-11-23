package Test;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.jdom2.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import LienBD.Personnel;
import LienBD.Plat;
import LienBD.myPDO;
import Tools.JDom;

public class PlatTest {
	
	public static Plat ref;
	public static int id;
	public static myPDO instance;
	public static int nbBase;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//myPDO.configure("root", "", "jdbc:mysql://localhost/projets3");
		myPDO.configure("infs3_prj03", "k4t28jb2", "jdbc:mysql://mysql/infs3_prj03");
		instance = myPDO.getInstance();
		id = 1;
		PlatTest.ref = new Plat(id);
		String sql = "SELECT COUNT(NUMPLAT) FROM PLAT";
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
		String sql = "SELECT COUNT(NUMPLAT) FROM PLAT";
		instance.prepare(sql);
		ResultSet res = instance.execute();
		int nbactual = 0;
		if(res.next()){
			nbactual = res.getInt(1);
		}
		while(nbactual > nbBase){
			sql = "DELETE FROM PLAT WHERE NUMPLAT = (SELECT MAX(NUMPLAT) FROM PLAT";
			instance.prepare(sql);
			instance.execute();
			nbactual--;
		}
	}
	
	@Test
	public void testGetNumRestoById() {
		try {
			System.out.println("ok");
			assertEquals(ref.getNumPlat(), (int) Plat.getNumPlatByID(id));
		} catch (Exception e) {
			fail("exception déclaré");
		}
	}
	
	@Test (expected = Exception.class)
	public void testGetNumRestoByIdNegatif() throws Exception {
		assertEquals(ref.getNumPlat(), (int) Plat.getNumPlatByID(-1));
	}
	
	@Test
	public void testGetRecetteByID() {
		try {
			assertEquals(ref.getRecette(), (String) Plat.getRecetteByID(id));
		} catch (Exception e) {
			fail("exception d�clar�");
		}
	}
	
	@Test
	public void testgetPrixUByID() {
		try {
			assertEquals(ref.getRecette(), (int) Plat.getPrixUByID(id));
		} catch (Exception e) {
			fail("exception d�clar�");
		}
	}
	
	@Test
	public void testGetAll() {
		try {
			Plat[] all = Plat.getAll();
			for(Plat a : all){
				assertEquals(a.getNumPlat(),Plat.getNumPlatByID(a.getNumPlat()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
