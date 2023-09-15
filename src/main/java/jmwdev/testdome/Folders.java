package jmwdev.testdome;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Folders {
    static Logger log = LogManager.getLogger(Folders.class);

    public static Collection<String> folderNames(String xml, char startingLetter) throws ParserConfigurationException, IOException, SAXException {
        Set<String> folderNames = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setExpandEntityReferences(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        NodeList nodes = doc.getElementsByTagName("folder");
        for (int i = 0; i < nodes.getLength(); i++) {
            String foldername = nodes.item(i).getAttributes().getNamedItem("name").getNodeValue();
            if (foldername.toCharArray()[0] == startingLetter) {
                folderNames.add(foldername);
            }
        }
        return folderNames;
    }

    public static void main(String[] args) throws Exception {

        String xml =

                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +

                        "<folder name=\"c\">" +

                        "<folder name=\"program files\">" +

                        "<folder name=\"uninstall information\" />" +

                        "</folder>" +

                        "<folder name=\"users\" />" +

                        "</folder>";


        Collection<String> names = folderNames(xml, 'u');
        for (String name : names)

            log.info(name);

    }


}

class Folder {
    private String name;

    Folder(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}