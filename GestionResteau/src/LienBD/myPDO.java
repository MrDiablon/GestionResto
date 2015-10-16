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

	public static void main(String[] args) {
		myPDO test = myPDO.getInstance("root", "", "jdbc:mysql://localhost/formation");
		String sql = "select * from javadb";
		test.prepare(sql);
		/*
		Object[] data = new Object [1];
		data[0] = 1;*/
		test.execute();
		try {
			while (rs.next()){
				System.out.println(rs.getString("personne"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private myPDO(String login,String passwd ,String url){
		try {
			myPDO.login = login;
			myPDO.passwd = passwd;
			myPDO.url = url;
			// Etape 1 : chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			// Etape 2 : récupération de la connexion
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
			System.out.println(st);
			myPDO.rs = myPDO.st.executeQuery();
			System.out.println(st);
			//myPDO.rs = st.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myPDO.rs;
	}
	
	/**
	 * methode permettant d'execute des requete sql et de recupere le resulta 
	 * 
	 * @param data tableau de donné a introduire dans la methode sql preparé
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
			System.out.println(myPDO.st);
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
	
	public static myPDO getInstance(String login,String passwd ,String url){
		myPDO pdo = null;
		try {
			if(myPDO.instance == null){
				throw new java.lang.NullPointerException("instance null");
			}else{
				pdo = myPDO.instance;
			}
		} catch (NullPointerException e){
			pdo = new myPDO(login, passwd, url );
		}
		
		return pdo;
	}
}
