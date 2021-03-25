import org.xml.sax.*;                // import SAX classes
import org.xml.sax.helpers.*;        // import SAX helper classes

/**
 * SAX event handler to output in basic format.
 *
 * @author Dion Kadriu
 * @version 11/03/20
 */
public class MenuHandler extends DefaultHandler {
    private String tagName = "";

    /**
     * Callback when parser finds character data.
     *
     * @param ch     character data
     * @param start  character start index
     * @param length character count
     * @throws SAXException
     */
    public void characters(char ch[], int start, int length) throws SAXException {

        String characters = new String(ch, start, length).trim();

        if (tagName.equals("name")) {
           this.tagName="";
            System.out.printf("%-30s", characters);
        }
        if (tagName.equals("price")) {
            this.tagName="";
            System.out.printf("%-30s", characters);
        }
        if (tagName.equals("description")) {
            this.tagName="";
            System.out.printf("%-30s\n", characters);
    }

    }
        /**
         Callback when parser finds the end of a document.

         @throws SAXException
         */
        public void endDocument () throws SAXException {

        }

        /**
         Callback when parser finds the end of an element.

         @param namespaceURI        namespace URI
         @param localName        local namespace identifier
         @param qName        qualified name for namespace
         @throws SAXException
         */
        public void endElement (String namespaceURI, String localName, String qName)
   throws SAXException {
//    System.err.println("endElement callback for '" + qName + "' '" + localName +"'");
        }

        /**
         Callback when parser starts to read a document.

         @throws SAXException
         */
        public void startDocument () throws SAXException {
        System.err.printf("%-30s%-30s%-30s\n", "Name", "Price", "Description");
        }

        /**
         Callback when parser starts to read an element.

         @param namespaceURI        namespace URI
         @param localName        local namespace identifier
         @param qName        qualified name for namespace
         @param attributes        elements attributes
         @throws SAXException
         */
        public void startElement (String namespaceURI, String localName,
                String qName, Attributes attributes) throws SAXException {
            this.tagName = qName;

//    System.err.println("startElement callback for '"  + qName + "'");
//    for (int i = 0; i < attributes.getLength(); i++)
//      System.err.println(
//	"  attribute '" + attributes.getQName(i) +
//	"' is '" + attributes.getValue(i) + "'");
        }

    }
