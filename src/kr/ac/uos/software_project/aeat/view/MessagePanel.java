/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import kr.ac.uos.software_project.aeat.PublisherActionListener;

/**
 *
 * @author comkeen
 */
public class MessagePanel extends JPanel{

    private final PublisherActionListener buttonActionListener;
    private JTextArea textArea;
    
    public MessagePanel(PublisherActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        initComponents();
    }

    private void initComponents() {
        this.textArea = new JTextArea();        
        this.add(textArea);
    }

    public void loadAeat(String aeatXML) {
        textArea.setText(aeatXML);
    }

    public void clearTextArea() {
        textArea.setText("");
    }
}
