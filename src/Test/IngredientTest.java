package Test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import LienBD.EtatI;
import LienBD.Ingredient;
import LienBD.myPDO;

public class IngredientTest {
	
	public static myPDO instance;
	public static Ingredient ref;
	public static int numRef;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myPDO.configure("root", "", "jdbc:mysql://localhost/projets3");
		//myPDO.configure("infs3_prj03", "k4t28jb2", "jdbc:mysql://mysql/infs3_prj03");
		instance = myPDO.getInstance();
		IngredientTest.numRef = 1;
		IngredientTest.ref = new Ingredient(1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		myPDO.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(ref);
	}
	
	@Test
	public void testConstruct() throws SQLException{
		Ingredient newIng = new Ingredient((float) 10.5, 5, EtatI.mauvais, "tomate");
		assertNotNull(newIng);
		Ingredient testIng = new Ingredient(newIng.getNumIngredient());
		assertNotNull(testIng.getNom());
	}
	
	@Test (expected = NullPointerException.class)
	public void testDestruct() throws SQLException{
		Ingredient newIng = new Ingredient((float) 10.5, 5, EtatI.mauvais, "tomate");
		newIng.delete();
		new Ingredient(newIng.getNumIngredient());
	}
	
	@Test 
	public void testModif() throws NullPointerException, SQLException{
		ref.setNom("patate");
		ref.modif();
		Ingredient test = new Ingredient(numRef);
		assertNotNull("etat",test.getEtatI());
		assertEquals("patate", ref.getNom());
		assertNotNull("prixU",test.getPrixU());
		assertNotNull("stock", test.getStock());
		assertEquals("etat", ref.getEtatI(), test.getEtatI());
		assertEquals("stock", ref.getStock(), test.getStock());
	}

}
