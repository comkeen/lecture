/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat;

import kr.ac.uos.software_project.aeat.view.Frame;
import aeat.AEATType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //AEATType 객체를 "path"경로에 xml파일로 저장            
            marshaller.marshal(aeat, new File(path));
            System.out.println("Save xml to: " + path);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private AEATType aeatUnmarshalling(String path) {
        AEATType aeat = null;
        try {
            File file = new File(path);

            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(file)).getValue();
            System.out.println("Load xml from: " + path);
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
        AEATType aeat = frame.getAeat();
        aeatMarshalling(aeat, "xml/output.xml");

    }

}
