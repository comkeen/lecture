/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import kr.ac.uos.software_project.aeat.Publisher;
import kr.ac.uos.software_project.aeat.PublisherActionListener;

/**
 *
 * @author comkeen
 */
public class MessagePanel extends JPanel{

    private final PublisherActionListener buttonActionListener;
    private JTextArea textArea;
    private JButton sendButton;
    private JPanel buttonPanel;
    private JTextField mqAddressTextField;
    private JTextField consumerDestinationTextField;
    private JPanel senderPanel;
    private JPanel receiverPanel;
    private JTextField senderDestinationTextField;
    private JTextArea senderTextArea;
    private JTextArea receiverTextArea;
    private JTextField receiverDestinationTextField;
    private JButton receiverButton;
    private JButton setMqAddressButton;
    
    public MessagePanel(PublisherActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        initComponents();
    }

    private void initComponents() {
        
        this.senderPanel = new JPanel();        
        senderPanel.setLayout(new BoxLayout(senderPanel, BoxLayout.Y_AXIS));
        senderPanel.setBorder(BorderFactory.createTitledBorder("Sender"));
        this.senderTextArea = new JTextArea();
        
        Box senderButtonBox = Box.createHorizontalBox();
        senderButtonBox.setBorder(BorderFactory.createTitledBorder("setSenderDestination"));
        this.senderDestinationTextField = new JTextField(Publisher.DEFAULT_DESTINATION);
        this.sendButton = new JButton("Send");
        sendButton.addActionListener(buttonActionListener);
        senderButtonBox.add(senderDestinationTextField);
        senderButtonBox.add(sendButton);
        
        senderPanel.add(senderTextArea);
        senderPanel.add(senderButtonBox);
        
        this.receiverPanel = new JPanel();
        receiverPanel.setLayout(new BoxLayout(receiverPanel, BoxLayout.Y_AXIS));
        receiverPanel.setBorder(BorderFactory.createTitledBorder("Receiver"));
        this.receiverTextArea = new JTextArea();
        
        Box receiverButtonBox = Box.createHorizontalBox();
        receiverButtonBox.setBorder(BorderFactory.createTitledBorder("setReceiverDestination"));
        this.receiverDestinationTextField = new JTextField(Publisher.DEFAULT_DESTINATION);        
        this.receiverButton = new JButton("setReceiveDestination");
        receiverButton.addActionListener(buttonActionListener);
        receiverButtonBox.add(receiverDestinationTextField);
        receiverButtonBox.add(receiverButton);
        
        receiverPanel.add(receiverTextArea);
        receiverPanel.add(receiverButtonBox);
        
        Box mqBox = Box.createHorizontalBox();
        mqBox.setBorder(BorderFactory.createTitledBorder("setMqAddress"));
        this.mqAddressTextField = new JTextField(Publisher.MQ_ADDRESS);
        this.setMqAddressButton = new JButton("setMqAddress");   
        mqBox.add(mqAddressTextField);
        mqBox.add(Box.createHorizontalGlue());
        mqBox.add(setMqAddressButton);
        
        this.add(senderPanel);
        this.add(receiverPanel);
        this.add(mqBox);
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

    public String getMessage() {
        return textArea.getText();
    }

    String getSenderDestination() {
        return senderDestinationTextField.getText();
    }

    String getSendMessage() {
        return senderTextArea.getText();
    }
}
