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
	}Element
	
	public static boolean createParam(){
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
			fw.write(createParamXML());
		} catch (IOException e) {
			retour = false;
		}	
		
		return retour;
	}
	
	private String createParamXML(int numR){
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
		Element nomResto = new Element("BDD");
		
		return retour;
	}
	
}