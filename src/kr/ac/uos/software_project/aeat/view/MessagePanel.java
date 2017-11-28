/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author comkeen
 */
public class MessagePanel extends JPanel{

    private final MyButtonActionListener buttonActionListener;
    private JTextArea textArea;
    private JButton sendButton;
    
    public MessagePanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        initComponents();
    }

    private void initComponents() {
        this.textArea = new JTextArea();  
        initButtonPanel();
        
        this.add(textArea);
    }

    public void loadAeat(String aeatXML) {
        textArea.setText(aeatXML);
    }

    public void clearTextArea() {
        textArea.setText("");
    }

    void onMessage(String text) {
        textArea.setText(text);
    }

    private void initButtonPanel() {
        this.sendButton = new JButton("Send");
        sendButton.addActionListener(buttonActionListener);
    }

    public String getMessage() {
        return textArea.getText();
    }
}
