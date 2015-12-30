package Tools;

import java.io.StringReader;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class JDom {

	public static Document convertStringToDocument(String xml) {
		SAXBuilder builder;
		Document retour = null;
		try {
			builder = new SAXBuilder();
			retour = builder.build(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static String convertDocumentToString(Document doc) {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		String retour = sortie.outputString(doc);
		return retour;
	}

	/**
	 * Genere une fiche horaire en document XML
	 *  
	 * @param beginHour heure de départ
	 * @param beginMin minute de départ
	 * @param endHour heure de fin
	 * @param endMin minute de fin
	 * @return le document XML generer
	 */
	public static Document createTimeCard(int beginHour, int beginMin, int endHour, int endMin) {
		Element racine = new Element("Personnel_TimeCard");
		Document retour = new Document(racine);
		//ecriture de la partie consernant l'heure d'arriver
		Element begin = new Element("heure_arriver");
		Element beginH = new Element("hour");
		Attribute valueBH = new Attribute("value", "" + beginHour);
		beginH.setAttribute(valueBH);
		begin.addContent(beginH);
		Element beginM = new Element("minute");
		Attribute valueBM = new Attribute("value", "" + beginMin);
		beginM.setAttribute(valueBM);
		begin.addContent(beginM);
		//ecriture de la partie heure de fin
		Element end = new Element("heure_fin");
		Element endH = new Element("hour");
		Attribute valueEH = new Attribute("value", "" + endHour);
		endH.setAttribute(valueEH);
		end.addContent(endH);
		Element endM = new Element("minute");
		Attribute valueEM = new Attribute("value", "" + endMin);
		endM.setAttribute(valueEM);
		end.addContent(endM);
		racine.addContent(begin);
		racine.addContent(end);
		return retour;
	}

	public static void main(String[] args) {
		System.out.println(convertDocumentToString(createTimeCard(10, 50, 10, 50)));
	}
}
