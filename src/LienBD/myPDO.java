package LienBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.DriverManager;

import com.mysql.jdbc.UpdatableResultSet;

public class myPDO {
	
	private static String url ;
	private static String login;
	private static String passwd;
	private static myPDO instance;
	private static Connection cn;
	private static PreparedStatement st;
	private static ResultSet rs;

	private myPDO(String login,String passwd ,String url){
		try {
			myPDO.login = login;
			myPDO.passwd = passwd;
			myPDO.url = url;
			// Etape 1 : chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			// Etape 2 : r�cup�ration de la connexion
			myPDO.cn = DriverManager.getConnection(url, login, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * methode permettant de prepare une methode
	 * 
	 * @param sql le code sql a prepare
	 */
	public void prepare(String sql){
		try {
			myPDO.st = cn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * permet d'execute des methode sql qui sont des demande simple sans insertion d'attribu
	 * 
	 * @return le resulta de la methode
	 */
	public ResultSet execute(){
		try{
			myPDO.rs = myPDO.st.executeQuery();
			//myPDO.rs = st.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPDO.rs;
	}
	
	/**
	 * methode permettant d'execute des requete sql et de recupere le resulta 
	 * 
	 * @param data tableau de donn� a introduire dans la methode sql prepar�
	 * @param update dis si le methode sql as pour but insert/update/delete 
	 * @return le resulta de la requete
	 */
	public ResultSet execute(Object[] data,boolean update){
		try {
			Object temp = new Object();
			for (int i = 0 ; i < data.length ; i++){
				temp = data[i];
				if(temp != null){
					if (data[i].getClass().getName() == "java.lang.Integer"){
						myPDO.st.setObject(i+1, temp, Types.INTEGER);
					} else {
						myPDO.st.setObject(i+1,temp );
					}
				}else{
					myPDO.st.setObject(i+1, temp);
				}				
			}
			if(update){
				myPDO.st.executeUpdate();
			}else{
				myPDO.st.executeQuery();
			}
			myPDO.rs = st.getResultSet();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPDO.rs;
	}
	
	public static myPDO getInstance(){
		if(myPDO.instance == null)
			myPDO.instance = new myPDO(myPDO.login, myPDO.passwd, myPDO.url );
		
		return myPDO.instance;
	}
	
	public static void configure(String login,String passwd ,String url){
		myPDO.login = login;
		myPDO.passwd = passwd;
		myPDO.url = url;
	}
	
	public static void close(){
		try {
			// Etape 5 :lib�rer ressources de la m�moire
			cn.close();
			st.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
