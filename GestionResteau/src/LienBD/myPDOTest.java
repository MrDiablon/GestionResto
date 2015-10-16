package LienBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.IsoEra;

import org.junit.Before;
import org.junit.Test;

public class myPDOTest {
	
	@Before
	public void init(){
				
	}
	/*
	 * test d'un insert
	 */
	/@Test
	public void test() {
		myPDO test = myPDO.getInstance("root", "", "jdbc:mysql://localhost/projets3");
		String sql = "INSERT INTO `restaurant` (`NUMRESTO`,`MARGE`,`NBSALLES`,`NBEMPLOYEE`,`ADRESSE`,`PAYS`,`NUMTEL`,`VILLE`,`CP`) VALUES (?,?,?,?,?,?,?,?,?)";
		test.prepare(sql);
		Object[] data = new Object[9];
		data[0] = null;
		data[1] = 10;
		data[2] = 2;
		data[3] = 2;
		data[4] = "test";
		data[5] = "France";
		data[6] = "0656056560";
		data[7] = "reims";
		data[8] = "51100";
		test.execute(data,true);
	}
	
	@Test
	public void testUpdate() {
		myPDO test = myPDO.getInstance("root", "", "jdbc:mysql://localhost/projets3");
		String sql = "UPDATE `restaurant` SET `MARGE` = ?,`NBSALLES` = ?,`NBEMPLOYEE` = ?,`ADRESSE` = ?,`PAYS` = ?,`NUMTEL` = ?,`VILLE` = ?,`CP` = ? WHERE `NUMRESTO` = ?";
		test.prepare(sql);
		Object[] data = new Object[9];
		data[0] = 15;
		data[1] = 1;
		data[2] = 1;
		data[3] = "25 rue dubois";
		data[4] = "France";
		data[5] = "123456";
		data[6] = "reims";
		data[7] = "51100";
		data[8] = 1;
		test.execute(data,true);
	}
	
	@Test
	public void testDelete(){
		myPDO test = myPDO.getInstance("root", "", "jdbc:mysql://localhost/projets3");
		String sql = "DELETE FROM `restaurant` where NUMRESTO = ?";
		test.prepare(sql);
		Object[] data = {5};
		test.execute(data,true);
	}
	
	@Test
	public void testSelect(){
		myPDO test = myPDO.getInstance("root", "", "jdbc:mysql://localhost/projets3");
		String sql = "SELECT * FROM `restaurant`";
		test.prepare(sql);
		ResultSet res = test.execute();
		try {
			while(res.next()){				
				System.out.println(res.getObject("NUMRESTO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void testSelectAtt(){
		myPDO test = myPDO.getInstance("root", "", "jdbc:mysql://localhost/projets3");
		String sql = "SELECT * FROM `restaurant` where  `NUMRESTO` = ?";
		test.prepare(sql);
		Object[] data= {13};
		ResultSet res = test.execute(data,false);
		try {
			while(res.next()){				
				System.out.println(res.getObject("MARGE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
