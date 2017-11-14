/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import aeat.AEAType;
import aeat.AEAtypeType;
import aeat.AudienceType;
import aeat.HeaderType;
import aeat.TypeType;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;
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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author comkeen
 */
public class Frame {

    private JFrame frame;
    private AeaPanel aeaPanel;
    private HeaderPanel headerPanel;
    private AeatextPanel aeatextPanel;
    private JPanel buttonPanel;

    private MyButtonActionListener buttonActionListener;

    public static final String TITLE = "AEAT Publisher";
    public static final Dimension LABEL_DIMENSION = new Dimension(80, 40);
    public static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);

    public Frame(MyButtonActionListener buttonActionListener) {
        this.buttonActionListener = buttonActionListener;

        this.frame = new JFrame(TITLE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        aeaPanel = new AeaPanel(buttonActionListener);
        frame.getContentPane().add(aeaPanel);

        headerPanel = new HeaderPanel(buttonActionListener);
        frame.getContentPane().add(headerPanel);

        aeatextPanel = new AeatextPanel(buttonActionListener);
        frame.getContentPane().add(aeatextPanel);

        buttonPanel = initButtonPanel();
        frame.getContentPane().add(buttonPanel);

        frame.setSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

    public void loadAeat(AEATType aeat) {
        aeaPanel.loadAeat(aeat);
        headerPanel.loadAeat(aeat);
        aeatextPanel.loadAeat(aeat);
    }

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

    private XMLGregorianCalendar stringToXMLGregorianCalendar(String s) {
        XMLGregorianCalendar result = null;

        
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = simpleDateFormat.parse(s);
            GregorianCalendar gregorianCalendar
                    = (GregorianCalendar) GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            return result;
        } catch (ParseException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
