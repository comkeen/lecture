/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author comkeen
 */
public class HeaderPanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    
    public HeaderPanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("EventCode"));
        this.add(createRecord("EventDesc"));
        this.add(createRecord("Location"));
        this.add(createRecord("effective"));
    }
    
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        
        JTextField textField = new JTextField("");
        box.add(textField);
        
        nameToTextField.put(name, textField);
        return box;
    }
      
    public void loadAeat(AEATType aeat) {
        nameToTextField.get("EventCode").setText(aeat.getAEA().get(0).getHeader().getEventCode().getValue());
        nameToTextField.get("EventDesc").setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(0).getValue());
        nameToTextField.get("Location").setText(aeat.getAEA().get(0).getHeader().getLocation().get(0).getValue());
        nameToTextField.get("effective").setText(aeat.getAEA().get(0).getHeader().getEffective().toString());
    }

    String getEventCode() {
        return nameToTextField.get("EventCode").getText();
    }

    String getEffective() {
        return nameToTextField.get("effective").getText();
    }
}
