package Test;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import LienBD.Menu;
import LienBD.myPDO;

public class MenuTest {

	public static Menu ref;
	public static int id;
	public static myPDO instance;
	public static int nbBase;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//myPDO.configure("root", "", "jdbc:mysql://localhost/projets3");
		myPDO.configure("infs3_prj03", "k4t28jb2", "jdbc:mysql://mysql/infs3_prj03");
		instance = myPDO.getInstance();
		id = 1;
		MenuTest.ref = new Menu(id);
		String sql = "SELECT COUNT(NUMMENU) FROM MENU";
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
		String sql = "SELECT COUNT(NUMMENU) FROM MENU";
		instance.prepare(sql);
		ResultSet res = instance.execute();
		int nbactual = 0;
		if(res.next()){
			nbactual = res.getInt(1);
		}
		while(nbactual > nbBase){
			sql = "DELETE FROM MENU WHERE NUMMENU = (SELECT MAX(NUMMENU) FROM MENU";
			instance.prepare(sql);
			instance.execute();
			nbactual--;
		}
	}

	@Test
	public void testGetNomById() {
		try {
			assertEquals(ref.getNom(), Menu.getnomById(id));
		} catch (Exception e) {
			fail("Exception levée");
		}
	}

	@Test(expected = Exception.class)
	public void testGetNomByIdNegatif() throws Exception {
		Menu.getnomById(-1);
	}

	@Test
	public void testGetPrenomById() {
		try {
			assertEquals(ref.getComposition(), Menu.getCompositionById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testGetPrenomByIdNegatif() throws Exception {
		Menu.getCompositionById(-1);
	}

	@Test
	public void testGetAll() {
		try {
			Menu[] all = Menu.getAll();
			for(Menu a : all){
				assertEquals(a.getNom(),Menu.getnomById(a.getNumMenu()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetByNB1() {
		try {
			Menu[] menu = Menu.getByNB(1, 1);
			assertEquals(1, menu.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test public void testGetByNBSup1(){
		try {
			Menu[] menu = Menu.getByNB(1, 2);
			assertEquals(2, menu.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

