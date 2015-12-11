package Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class Parameters {

	public static void main(String[] args) {
	}
	
	
	public static boolean isSet(){
		boolean retour = false;
		String osName =  System.getProperty("os.name");
		if(osName.equals("Linux")){
			//dans une config de base
			//File param = new File("/home/GestionResteau/config.xml");
			//pour le projet
			File param = new File("/home/Projets/infs3_prj03/Code/GestionResteau/config.xml");
			if(param.exists()){
				retour = true;
			}
		}else if(osName.equals("Windows")){
			File param = new File("c:\\programfile\\GestionResteau\\config.xml");
			if(param.exists()){
				retour = true;
			}
		}
		
		return retour;
	}
	
	public static boolean createParam(int numR,String id,String pwd){
		boolean retour = false;
		String osName =  System.getProperty("os.name");
		File newFile = null;
		if(osName.equals("Linux")){
			newFile = new File("/home/Projets/infs3_prj03/Code/GestionResteau/config.xml");
			retour = newFile.mkdirs();
		}else if(osName.equals("Windows")){
			newFile = new File("c:\\programfile\\GestionResteau\\config.xml");
			retour = newFile.mkdirs();
		}
		
		try {
			FileWriter fw = new FileWriter(newFile);
			//fw.write(createParamXML());
		} catch (IOException e) {
			retour = false;
		}	
		
		return retour;
	}
	
	private String createParamXML(int numR,String id,String pwd){
		String retour = null;
		//creation du document
		Element racine = new Element("parametre");
		Document xml = new Document(racine);
		//configuration du restaurant
		Element resto = new Element("Restaurant");
		racine.addContent(resto);
		//config du numero du restaurant
		Element numResto = new Element("numResto");
		Attribute num = new Attribute("num","" + numR);
		numResto.setAttribute(num);
		//config des parametre de la vase de donnée
		Element bdd = new Element("BDD");
		//parametrage de l'emlement user
		Element user = new Element("user");
		Attribute userA = new Attribute("name", id);
		user.setAttribute(userA);
		bdd.addContent(user);
		//parametrage de l'element mdp coder
		Element mdp = new Element("password");
		Attribute mdpA = new Attribute("pass", encode(pwd));
		
		return retour;
	}
	
	private String encode(String mdp){
		char[] passChar = mdp.toCharArray();
		char[] newPassChar = new char[passChar.length];
		for(int i = 0 ; i<newPassChar.length; i ++){
			try{
				if(i%2 == 0){
					newPassChar[i] = passChar[i/2];
				}else{
					newPassChar[i] = (char) (passChar[(i+1)/2] + passChar[(i-1)/2]);
				}
			}catch(IndexOutOfBoundsException e){
				if(i == newPassChar.length){
					newPassChar[i] = (char) (passChar[(i-1)/2] + passChar[0]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char c : newPassChar) {
			sb.append(c);
		}
		return sb.toString();
	}
	/*
	private String decode(String code){
		char[] codeC = code.toCharArray();
		//char 
		
	}*/
	
}