package Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.chrono.IsoEra;

import org.junit.Before;
import org.junit.Test;

import LienBD.myPDO;

public class myPDOTest {
	
	@Before
	public void init(){
		myPDO.configure("root", "", "jdbc:mysql://localhost/projets3");
	}
	/*
	 * test d'un insert
	 */
	@Test
	public void test() {
		myPDO pdo = myPDO.getInstance();
		String sql = "INSERT INTO `RESTAURANT` (`NUMRESTO`,`MARGE`,`NBSALLES`,`NBEMPLOYEE`,`ADRESSE`,`PAYS`,`NUMTEL`,`VILLE`,`CP`) VALUES (?,?,?,?,?,?,?,?,?)";
		pdo.prepare(sql);
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
		pdo.execute(data,true);
	}
	/*
	@Test
	public void testUpdate() {
		myPDO pdo = myPDO.getInstance();
		String sql = "UPDATE `RESTAURANT` SET `MARGE` = ?,`NBSALLES` = ?,`NBEMPLOYEE` = ?,`ADRESSE` = ?,`PAYS` = ?,`NUMTEL` = ?,`VILLE` = ?,`CP` = ? WHERE `NUMRESTO` = ?";
		pdo.prepare(sql);
		Object[] data = new Object[9 ];
		data[0] = 15;
		data[1] = 1;
		data[2] = 1;
		data[3] = "25 rue dubois";
		data[4] = "France";
		data[5] = "123456";
		data[6] = "reims";
		data[7] = "51100";
		data[8] = 1;
		pdo.execute(data,true);
	}
	
	@Test
	public void testDelete(){
		myPDO pdo = myPDO.getInstance();
		String sql = "DELETE FROM `RESTAURANT` where NUMRESTO = ?";
		pdo.prepare(sql);
		Object[] data = {5};
		pdo.execute(data,true);
	}
	
	@Test
	public void testSelect(){
		myPDO pdo = myPDO.getInstance();
		String sql = "SELECT * FROM `restaurant`";
		pdo.prepare(sql);
		ResultSet res = pdo.execute();
		try {
			while(res.next()){				
				System.out.println(res.getObject("NUMRESTO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	@Test
	public void testSelectAtt(){
		myPDO pdo = myPDO.getInstance();
		String sql = "SELECT * FROM `PERSONNEL` where  `NUMPERSO` = ?";
		pdo.prepare(sql);
		Object[] data= {1};
		ResultSet res = pdo.execute(data,false);
		try {
			while(res.next()){				
				System.out.println(res.getObject("NUMRESTO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
