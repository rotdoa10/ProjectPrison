// author Veronika Gößler
package bl;

import beans.Location;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParse {

    private static Document xmlDoc;
    private String xmlString;

    public Document loadXMLFromString() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(this.xmlString));
        return builder.parse(is);
    }

    public XMLParse(String requestUrl) {
        this.xmlString = requestUrl;
        try {
            xmlDoc = this.loadXMLFromString();
        } catch (Exception ex) {
            Logger.getLogger(XMLParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Location xmlToLocation() {
        double x=0;
        double y=0;
        Location loc = null;
        
        Element root = xmlDoc.getDocumentElement();
        NodeList results = root.getElementsByTagName("result");
        for (int i = 0; i < results.getLength(); i++) {
            Element e = (Element) results.item(i);
            
            NodeList geometry = e.getElementsByTagName("geometry");
            for (int j = 0; j < geometry.getLength(); j++) {
                Element e2 = (Element) geometry.item(i);
                NodeList locations = e.getElementsByTagName("location");
                for (int k = 0; k < locations.getLength(); k++) {
                    Element e3= (Element) locations.item(i);
                    x= Double.parseDouble(e3.getAttribute("lat"));
                    y= Double.parseDouble(e3.getAttribute("lng"));
                    
                }
                
            }
            
        }
        return loc;
    }

}
