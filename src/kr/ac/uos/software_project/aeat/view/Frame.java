/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import kr.ac.uos.aeat.AEATType;
import kr.ac.uos.aeat.AEAType;
import kr.ac.uos.aeat.AEAtypeType;
import kr.ac.uos.aeat.AudienceType;
import kr.ac.uos.aeat.HeaderType;
import kr.ac.uos.aeat.TypeType;
import kr.ac.uos.software_project.aeat.Publisher;
import kr.ac.uos.software_project.aeat.PublisherActionListener;

/**
 *
 * @author comkeen
 */
public class Frame {

    private AeaPanel aeaPanel;
    private HeaderPanel headerPanel;
    private AeatextPanel aeatextPanel;
    private JPanel buttonPanel;

    private PublisherActionListener buttonActionListener;

    public static final String TITLE = "AEAT Publisher";
    public static final Dimension LABEL_DIMENSION = new Dimension(80, 40);
    public static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);
    private MessagePanel messagePanel;

    public Frame(PublisherActionListener buttonActionListener) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame frame = new JFrame(TITLE);
        this.buttonActionListener = buttonActionListener;
        JTabbedPane mainTabbedPanel = new JTabbedPane() ;
               
        mainTabbedPanel.add("AEAT 편집", initAeatEditTabPanel());
        mainTabbedPanel.add("메시지", initMessageTabPanel());
        frame.getContentPane().add(mainTabbedPanel);
        
        frame.setPreferredSize(new Dimension(400, 600));
        frame.setSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
    
    private JPanel initAeatEditTabPanel() {
        JPanel aeatEditTabPanel = new JPanel();
        aeatEditTabPanel.setLayout(new BoxLayout(aeatEditTabPanel, BoxLayout.Y_AXIS));
        
        this.aeaPanel = new AeaPanel(buttonActionListener);
        aeatEditTabPanel.add(aeaPanel);        
        this.headerPanel = new HeaderPanel(buttonActionListener);
        aeatEditTabPanel.add(headerPanel);
        this.aeatextPanel = new AeatextPanel(buttonActionListener);
        aeatEditTabPanel.add(aeatextPanel);
        this.buttonPanel = initButtonPanel();
        aeatEditTabPanel.add(buttonPanel);
        
        return aeatEditTabPanel;
    }

    private JPanel initButtonPanel() {
        JPanel panel = new JPanel();
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(buttonActionListener);
        panel.add(loadButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(buttonActionListener);
        panel.add(saveButton);

        return panel;
    }
    
    private JPanel initMessageTabPanel() {
        this.messagePanel = new MessagePanel(buttonActionListener);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        return messagePanel;
    }

    //메소드명:loadAeat()
    //입력:하위 패널들의 텍스트필드에 표출할 데이터인 aeat 객체
    //출력:없음
    //부수효과:하위 패널의 loadAeat() 메소드 호출
    public void loadAeat(AEATType aeat) {
        aeaPanel.loadAeat(aeat);
        headerPanel.loadAeat(aeat);
        aeatextPanel.loadAeat(aeat);
        
        messagePanel.loadAeat(Publisher.aeatToXml(aeat));
    }

    //메소드명:getAeat()
    //입력:없음
    //출력:하위 패널들의 텍스트필드로부터 AEAT 요소 값들을 읽어와서 AEAT 객체 생성
    //부수효과:없음
    public AEATType getAeat() {
        AEATType aeat = new AEATType();
        AEAType aea = new AEAType();
        aea.setAeaId(aeaPanel.getAeaId());
        aea.setIssuer(aeaPanel.getIssuer());
        aea.setAudience(AudienceType.fromValue(aeaPanel.getAudience()));
        aea.setAeaType(AEAtypeType.fromValue(aeaPanel.getAeaType()));

        HeaderType header = new HeaderType();
        TypeType typeType = new TypeType();
        typeType.setValue(headerPanel.getEventCode());
        header.setEventCode(typeType);
        header.setEffective(stringToXMLGregorianCalendar(headerPanel.getEffective()));
        aea.setHeader(header);

        aeat.getAEA().add(aea);
        return aeat;
    }

    //메소드명:stringToXMLGregorianCalendar()
    //입력:XMLGregorianCalendar 타입으로 변환할 문자열(String)
    //출력:XMLGregorianCalendar 객체
    //부수효과:없음
    private XMLGregorianCalendar stringToXMLGregorianCalendar(String input) {
        XMLGregorianCalendar result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = simpleDateFormat.parse(input);
            GregorianCalendar gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (ParseException | DatatypeConfigurationException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void clearMessagePanel() {
        messagePanel.clearTextArea();
    }

    public void onMessage(String text) {
        messagePanel.onMessage(text);
    }

    public String getMessage() {
        return messagePanel.getMessage();
    }

    public String getSendDestination() {
        return messagePanel.getSenderDestination();
    }

    public String getSendMessage() {
        return messagePanel.getSendMessage();
    }

}
