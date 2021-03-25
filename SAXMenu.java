import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.*;

/**
 * SAX event handler to:
 * <p>
 * 1) Show basic event handling while reading an XML file
 * 2) output in a csv file
 *
 * @author Dion Kadriu
 * @version 11/03/20
 */
public class SAXMenu {

    /**
     * Main program to call SAX parser.
     *
     * @param args command-line arguments
     * First argument is the name of the xml file to process
     */


    public static void main(String[] args) {
    //if validation is true proceed to parsing
        if (validateDocument(args[0],args[1])){
            parse(args[0]);
        }
        else{
            System.err.println("File could not be parsed");
        }

    }

    /**
     * Callback when parser finds character data.
     *
     * @param filename XML file to read
     */
    private static void parse(String filename) {
        try {
            System.out.println("-------------------");
            System.out.println("parsing " + filename);
            System.out.println();
            // get an instance of SAXParserFactory and get an XMLReader from it
            SAXParserFactory factory = SAXParserFactory.newInstance();

            XMLReader reader = factory.newSAXParser().getXMLReader();
            // turn off XML validation
            reader.setFeature("http://xml.org/sax/features/validation", false);
            // register the relevant handler with the parser, choosing one of:
            MenuHandler handler = new MenuHandler();
            //CountHandler handler = new CountHandler();
            reader.setContentHandler(handler);
            reader.setErrorHandler(handler);
            // parse the given file


            InputSource inputSource = new InputSource(filename);
            reader.parse(inputSource);

            System.out.println("-------------------");
            System.out.println();
        } catch (SAXException saxException) {
            System.err.println("There is a mistake in the parsing- " + saxException);
        } catch (IOException op) {
            System.err.println("The file to parse is not found -" + op.getMessage());
        } catch (ParserConfigurationException ex) {
            System.err.println("The file cannot be configured- " + ex.getMessage());
        }
    }

    private static Boolean validateDocument(String xmlFilename,String xsdFilename) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //create the schema that uses the xsd as if the file is correct
            Schema schema = schemaFactory.newSchema(new StreamSource(xsdFilename));
            Validator validator = schema.newValidator();
            //take the file to validate with the xsd
             FileInputStream fileInputStream = new FileInputStream(xmlFilename);
            InputSource inputSource = new InputSource(fileInputStream);
            //pass it as the Sax source
            SAXSource source = new SAXSource(inputSource);
            //validate
            validator.validate(source);
            return true;// return true to parse the xml because there are no errors

        } catch (SAXException saxException) {
            System.err.println(saxException.getMessage());
            return false;  // return false if there is an exception
        }catch ( IOException ioException){
            System.err.println(ioException.getMessage());
            return false;
        }


    }
}