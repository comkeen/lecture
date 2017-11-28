/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author comkeen
 */
public class MyButtonActionListener implements ActionListener {

    private Publisher publisher;

    public MyButtonActionListener(Publisher publisher) {
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
            case "Send":
                publisher.onClickedSendButton();
                break;
            default:
                break;
        }
    }
}
