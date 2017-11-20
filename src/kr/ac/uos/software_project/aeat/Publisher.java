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

    public static final String AEAT_SAMPLE = "xml/AEAT-Example-20170920.xml"; // 샘플 aeat xml 경로
    public static final String AEAT_XML_SCHEMA = "xmlSchema/AEAT-1.0-20170920.xsd"; // aeat schema 경로
    public static final String AEAT_OUTPUT = "xml/output.xml"; // 저장되는 파일 경로

    public Publisher() {
        MyButtonActionListener buttonActionListener = new MyButtonActionListener(this); // 버튼액션리스너 생성
        this.frame = new Frame(buttonActionListener); // 메인 프레임 생성
    }

    private void aeatMarshalling(AEATType aeat, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //AEATType 객체를 "path" 경로에 파일로 저장            
            marshaller.marshal(aeat, new File(path));
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
            //XML 파일로부터 AEATTYPE 객체 반환
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(file)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }

    public void onClickedLoadButton() {
        String path = AEAT_SAMPLE;
        frame.loadAeat(this.aeatUnmarshalling(path));
        System.out.println("Load xml from: " + path);
    }

    public void onClickedSaveButton() {
        String path = AEAT_OUTPUT;
        this.aeatMarshalling(frame.getAeat(), path);
        System.out.println("Save xml to: " + path);
    }

}
