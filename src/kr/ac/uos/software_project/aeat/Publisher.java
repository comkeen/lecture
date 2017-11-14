/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat;

import kr.ac.uos.software_project.aeat.view.Frame;
import aeat.AEATType;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author comkeen
 */
public class Publisher {

    private Frame frame;
    
    public static final String AEAT_EXAM = "xml/AEAT-Example-20170920.xml";
    public static final String AEAT_XML_SCHEMA = "xmlSchema/AEAT-1.0-20170920.xsd";
    
    public Publisher() {
        MyButtonActionListener buttonActionListener = new MyButtonActionListener(this);
        frame = new Frame(buttonActionListener);    
    }
    
    private void aeatMarshalling(AEATType aeat, String path) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
            URL schemaURL = Publisher.class.getResource(AEAT_XML_SCHEMA);
            Schema schema = schemaFactory.newSchema(schemaURL);
            
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setEventHandler(new ValidationEventHandler() {
                @Override
                public boolean handleEvent(ValidationEvent event) {
                    System.err.println(String.format("ERROR: %s (%d, %d) Severity: %s", event.getMessage(),
                    event.getLocator().getLineNumber(),
                    event.getLocator().getColumnNumber(),
                    event.getSeverity()));
                    return false;
                }
            });
            
            
            marshaller.marshal(aeat, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private AEATType aeatUnmarshalling(String path) {
        AEATType aeat = null;
        try {
            File file = new File(path);

            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(file)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }

    public void onClickedLoadButton() {
        
        AEATType aeat = aeatUnmarshalling(AEAT_EXAM);
        frame.loadAeat(aeat);
    }

    public void onClickedSaveButton() {
        //todo
    }
    
}
