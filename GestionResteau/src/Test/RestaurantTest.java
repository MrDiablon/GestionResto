package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import LienBD.myPDO;

public class RestaurantTest {
	
	@Before
	public void init(){
		myPDO.configure("infs3_prj03", "azerty01", "jdbc:mysql://mysql/infs3_prj03");
	}

	@Test
	public void test() {
		
	}

}
