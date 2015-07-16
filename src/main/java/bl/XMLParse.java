// author Veronika Gößler
package bl;

import beans.Leg;
import beans.Location;
import java.awt.List;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
            System.out.println("                      Fehler im Constructor XMLParse");
        }
    }

    public Location xmlToLocation() {
        double x = 0;
        double y = 0;
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
                    Element e3 = (Element) locations.item(i);
                    String responseKoord = e3.getTextContent();
                    String[] split = responseKoord.split("\n");

                    for (int l = 0; l < split.length; l++) {
                        String string = split[l];

                        if (l == 1) {
                            x = Double.parseDouble(string.trim());
                        } else if (l == 2) {
                            y = Double.parseDouble(string.trim());
                        }
                    }
                }
            }
            loc = new Location("", x, y);
            return loc;
        }
        return null;
    }

    public String xmlElevationInformation() {
        double elevation;

        Element root = xmlDoc.getDocumentElement();

        NodeList results = root.getElementsByTagName("result");
        for (int i = 0; i < results.getLength(); i++) {
            Element e = (Element) results.item(i);
            NodeList elevationList = e.getElementsByTagName("elevation");
            Element eElevation = (Element) elevationList.item(0);
            elevation = Double.parseDouble(eElevation.getTextContent());
            System.out.println(elevation);
        }

        return null;
    }

    public String xmlToDistanceAndDuration() {
        String duration = "";
        String distance = "";

        Element root = xmlDoc.getDocumentElement();
        NodeList row = root.getElementsByTagName("row");
        for (int i = 0; i < row.getLength(); i++) {
            Element e = (Element) row.item(i);
            NodeList elementList = e.getElementsByTagName("element");
            for (int j = 0; j < elementList.getLength(); j++) {
                Element e1 = (Element) elementList.item(j);
                NodeList durationList = e1.getElementsByTagName("duration");
                for (int k = 0; k < durationList.getLength(); k++) {
                    Element eDuration = (Element) durationList.item(i);
                    NodeList durationText = eDuration.getElementsByTagName("text");
                    Element dText = (Element) durationText.item(0);
                    duration = dText.getTextContent();
                    System.out.println(duration);
                }
                NodeList distanceList = e1.getElementsByTagName("distance");
                for (int k = 0; k < distanceList.getLength(); k++) {
                    Element eDistance = (Element) distanceList.item(i);
                    NodeList distanceText = eDistance.getElementsByTagName("text");
                    Element dText = (Element) distanceText.item(0);
                    distance = dText.getTextContent();
                    System.out.println(distance);
                }
            }
        }

        return duration + "-" + distance;
    }

    public LinkedList<Leg> xmlFromDistanceAPItoLocations() {

        LinkedList<Leg> list = new LinkedList<Leg>();
        Element root = xmlDoc.getDocumentElement();
        NodeList namenList = root.getElementsByTagName("step");

        for (int i = 0; i < namenList.getLength(); i++) {

            Element elem = (Element) namenList.item(i);

            NodeList end_loc = elem.getElementsByTagName("end_location");
            NodeList duration = elem.getElementsByTagName("duration");
            NodeList distance = elem.getElementsByTagName("distance");
            NodeList html_instruction = elem.getElementsByTagName("html_instructions");
   
            for (int k = 0; k < distance.getLength(); k++) {
                //
                Element elem_end_loc = (Element) end_loc.item(k);
                Element elem_distance = (Element) distance.item(k);
                Element elem_duration = (Element) duration.item(k); 
                //
                NodeList end_loc_lat = elem_end_loc.getElementsByTagName("lat");
                NodeList end_loc_lng = elem_end_loc.getElementsByTagName("lng");
                Element endloclat = (Element) end_loc_lat.item(k);
                Element endloclng = (Element) end_loc_lng.item(k);
                //
                NodeList distance_value = elem_distance.getElementsByTagName("value");
                Element elem_distance_value = (Element) distance_value.item(k);
                NodeList duration_value = elem_duration.getElementsByTagName("value");
                Element elem_duration_value = (Element) duration_value.item(k);
                //
                Element html_instr = (Element) html_instruction.item(k);
                //
                String str_distance = elem_distance_value.getTextContent();
                float f_distance = Float.parseFloat(str_distance);
                
                String str_duration = elem_duration_value.getTextContent();
                int i_duration = Integer.parseInt(str_duration);
                //
                String str_endloclat = endloclat.getTextContent();
                float f_endloclat = Float.parseFloat(str_endloclat);
                
                String str_endloclng = endloclng.getTextContent();
                float f_endloclng = Float.parseFloat(str_endloclng);
                //             
                String str_html_instruction = html_instr.getTextContent();
                //
                GeocodingAPI a = new GeocodingAPI();
                double[] koordinaten = {f_endloclat,f_endloclng};
                //
                //Location l = a.KoordToOrt(koordinaten);
                //
                //String str_elevation = a.getElevationInformation(l);
                //double d_elevation = Double.parseDouble(str_elevation);
                //
                list.add(new Leg(f_distance, i_duration, f_endloclat, f_endloclng, str_html_instruction, 123));              
            }
        }

        return list;

    }

    public static void main(String[] args) {
        XMLParse xml = new XMLParse("<ElevationResponse>\n"
                + "<status>OK</status>\n"
                + "<result>\n"
                + "<location>\n"
                + "<lat>39.7391536</lat>\n"
                + "<lng>-104.9847034</lng>\n"
                + "</location>\n"
                + "<elevation>1608.6379395</elevation>\n"
                + "<resolution>4.7719760</resolution>\n"
                + "</result>\n"
                + "</ElevationResponse>");

        xml.xmlElevationInformation();

    }
}
