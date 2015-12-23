package Tools;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class JDom {
	
	public static Document convertStringToDocument(String xml){
		SAXBuilder builder;
		Document retour = null;
		try {
			builder = new SAXBuilder();
			retour = builder.build(new StringReader(xml));
		}catch (Exception e ){
			e.printStackTrace();
		}
		return retour;
	}
	
	public static String convertDocumentToString(Document doc){
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		String retour = sortie.outputString(doc);
		return retour;
	}
	
	public static Document createTimeCard(int hour ,int min){
		Document retour = null;
		
		return retour;
	}
}
