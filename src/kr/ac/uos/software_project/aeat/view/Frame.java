/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
     
}
