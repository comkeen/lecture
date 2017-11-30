/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author comkeen
 */
public class PublisherActionListener implements ActionListener, MessageListener {

    private Publisher publisher;

    public PublisherActionListener(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Load":
                publisher.onClickedLoadButton();
                break;
            case "Save":
                publisher.onClickedSaveButton();
                break;
            default:
                break;
        }
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("Received Message:\n" + textMessage.getText());
                publisher.onMessage(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
