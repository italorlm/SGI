package ws;

import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CepWS {
	
	public static Element buscarCep(String cep) {
		Element element = null;
		try {
			URL url = new URL("http://viacep.com.br/ws/" + cep + "/xml/");
			Document document = getDocumento(url);
			element = document.getRootElement();			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {			
			e.printStackTrace();
		}		
		
		return element;
	}
	
	private static Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url); 
        return document;
    }
}
