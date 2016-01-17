package Tools;

import java.io.StringReader;
import java.util.Date;

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
	@SuppressWarnings("deprecation")
	public static Document createTimeCard(Date dateDay,int beginHourM, int beginMinM, int endHourM, int endMinM,
			int beginHourA, int beginMinA, int endHourA, int endMinA) {
		
		Element racine = new Element("Personnel_TimeCard");
		Document retour = new Document(racine);
		
		//ecriture de la partie consernant l'heure d'arriver
		Element day = new Element("Date");
		Attribute year = new Attribute("year", "" + dateDay.getYear());
		Attribute month = new Attribute("month", "" + dateDay.getMonth());
		Attribute dayR = new Attribute("day", "" + dateDay.getDay());
		day.setAttribute(year);
		day.setAttribute(month);
		day.setAttribute(dayR);
		Element matin = new Element("matin");
		Element beginM = new Element("heure_arriver_matin");
		Element beginHM = new Element("hour");
		Attribute valueBHM= new Attribute("value", "" + beginHourM);
		beginHM.setAttribute(valueBHM);
		beginM.addContent(beginHM);
		Element beginMM = new Element("minute");
		Attribute valueBMM = new Attribute("value", "" + beginMinM);
		beginMM.setAttribute(valueBMM);
		beginM.addContent(beginMM);
		//ecriture de la partie heure de fin
		Element endM = new Element("heure_fin_matin");
		Element endHM = new Element("hour");
		Attribute valueEHM = new Attribute("value", "" + endHourM);
		endHM.setAttribute(valueEHM);
		endM.addContent(endHM);
		Element endMM = new Element("minute");
		Attribute valueEMM = new Attribute("value", "" + endMinM);
		endMM.setAttribute(valueEMM);
		endM.addContent(endMM);
		matin.addContent(beginM);
		matin.addContent(endM);
		day.addContent(matin);
		Element ApresMidi = new Element("apresMidi");
		Element beginA = new Element("heure_arriver_apres_midi");
		Element beginHA = new Element("hour");
		Attribute valueBHA = new Attribute("value", "" + beginHourA);
		beginHA.setAttribute(valueBHA);
		beginA.addContent(beginHA);
		Element beginMA = new Element("minute");
		Attribute valueBMA = new Attribute("value", "" + beginMinA);
		beginMA.setAttribute(valueBMA);
		beginA.addContent(beginMA);
		//ecriture de la partie heure de fin
		Element endA = new Element("heure_fin_apres_midi");
		Element endHA = new Element("hour");
		Attribute valueEHA = new Attribute("value", "" + endHourA);
		endHA.setAttribute(valueEHA);
		endA.addContent(endHA);
		Element endMA = new Element("minute");
		Attribute valueEMA = new Attribute("value", "" + endMinA);
		endMA.setAttribute(valueEMA);
		endA.addContent(endMA);
		ApresMidi.addContent(beginA);
		ApresMidi.addContent(endA);
		day.addContent(ApresMidi);
		
		racine.addContent(day);
		return retour;
	}
	
	public static Document createTimeCard(Document doc,Date dateDay,int beginHourM, int beginMinM, int endHourM, int endMinM,
			int beginHourA, int beginMinA, int endHourA, int endMinA) {
		
		
		Element racine = doc.getRootElement();
		
		//ecriture de la partie consernant l'heure d'arriver
		Element day = new Element("Date");
		Attribute year = new Attribute("year", "" + dateDay.getYear());
		Attribute month = new Attribute("month", "" + dateDay.getMonth());
		Attribute dayR = new Attribute("day", "" + dateDay.getDay());
		day.setAttribute(year);
		day.setAttribute(month);
		day.setAttribute(dayR);
		Element matin = new Element("matin");
		Element beginM = new Element("heure_arriver_matin");
		Element beginHM = new Element("hour");
		Attribute valueBHM= new Attribute("value", "" + beginHourM);
		beginHM.setAttribute(valueBHM);
		beginM.addContent(beginHM);
		Element beginMM = new Element("minute");
		Attribute valueBMM = new Attribute("value", "" + beginMinM);
		beginMM.setAttribute(valueBMM);
		beginM.addContent(beginMM);
		//ecriture de la partie heure de fin
		Element endM = new Element("heure_fin_matin");
		Element endHM = new Element("hour");
		Attribute valueEHM = new Attribute("value", "" + endHourM);
		endHM.setAttribute(valueEHM);
		endM.addContent(endHM);
		Element endMM = new Element("minute");
		Attribute valueEMM = new Attribute("value", "" + endMinM);
		endMM.setAttribute(valueEMM);
		endM.addContent(endMM);
		matin.addContent(beginM);
		matin.addContent(endM);
		day.addContent(matin);
		Element ApresMidi = new Element("apresMidi");
		Element beginA = new Element("heure_arriver_apres_midi");
		Element beginHA = new Element("hour");
		Attribute valueBHA = new Attribute("value", "" + beginHourA);
		beginHA.setAttribute(valueBHA);
		beginA.addContent(beginHA);
		Element beginMA = new Element("minute");
		Attribute valueBMA = new Attribute("value", "" + beginMinA);
		beginMA.setAttribute(valueBMA);
		beginA.addContent(beginMA);
		//ecriture de la partie heure de fin
		Element endA = new Element("heure_fin_apres_midi");
		Element endHA = new Element("hour");
		Attribute valueEHA = new Attribute("value", "" + endHourA);
		endHA.setAttribute(valueEHA);
		endA.addContent(endHA);
		Element endMA = new Element("minute");
		Attribute valueEMA = new Attribute("value", "" + endMinA);
		endMA.setAttribute(valueEMA);
		endA.addContent(endMA);
		ApresMidi.addContent(beginA);
		ApresMidi.addContent(endA);
		day.addContent(ApresMidi);
		
		racine.addContent(day);
		
		return doc;
	}

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		Date d = new Date(2016, 01, 13);
		Document doc1 = createTimeCard(d,10, 50, 10, 50, 10, 50, 10, 50);
		Document doc2 = createTimeCard(doc1,d,10, 50, 10, 50, 10, 50, 10, 50);
		System.out.println(convertDocumentToString(doc1));
		System.out.println(convertDocumentToString(doc2));
	}
}
