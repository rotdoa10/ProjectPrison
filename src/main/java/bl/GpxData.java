package bl;

import beans.Location;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author patzineubi
 *
 */
public class GpxData {

    private GeocodingAPI geo;
    private XmlPullParser parser;
    private InputStream gpxInput;

    public GpxData(XmlPullParser pars, InputStream gpxIn) 
    {  
        this.parser = pars;
        this.gpxInput = gpxIn;
    }

    /*Parses the waypoint (wpt tags) data into native objects from a GPX stream.*/
    public LinkedList<Location> loadGpxData()
            throws XmlPullParserException, IOException {

        LinkedList<Location> locList = new LinkedList<>();
        parser.setInput(gpxInput, null);
        parser.nextTag();

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            double[] koordinaten = new double[2];
            koordinaten[0] = Double.valueOf(parser.getAttributeValue(null, "lat"));
            koordinaten[1] = Double.valueOf(parser.getAttributeValue(null, "lon"));
            if (parser.getName().equals("wpt")) {
                locList.add(geo.KoordToOrt(koordinaten));
            }
        }
        return locList;
    }

    public static void main(String[] args) {

    }

}
