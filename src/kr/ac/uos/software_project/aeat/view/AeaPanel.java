/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author comkeen
 */
public class AeaPanel extends JPanel {
    
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    
    public AeaPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setMinimumSize(new Dimension(400, 200));
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("aeaId"));
        this.add(createRecord("issuer"));
        this.add(createRecord("audience"));
        this.add(createRecord("aeaType"));
        this.add(createRecord("refAEAId"));
        this.add(createRecord("priority"));
        this.add(createRecord("wakeup"));
    }
    
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        
        box.add(Box.createHorizontalGlue());
        
        JTextField textField = new JTextField("");
        box.add(textField);
        
        nameToTextField.put(name, textField);
        return box;
    }
    
    public void loadAeat(AEATType aeat) {
        nameToTextField.get("aeaId").setText(aeat.getAEA().get(0).getAeaId());
        nameToTextField.get("issuer").setText(aeat.getAEA().get(0).getIssuer());
        nameToTextField.get("audience").setText(aeat.getAEA().get(0).getAudience().value());
        nameToTextField.get("aeaType").setText(aeat.getAEA().get(0).getAeaType().value());
        nameToTextField.get("refAEAId").setText(aeat.getAEA().get(0).getRefAEAId());
        nameToTextField.get("priority").setText(aeat.getAEA().get(0).getPriority().toString());  
        nameToTextField.get("wakeup").setText(String.valueOf(aeat.getAEA().get(0).isWakeup()));        
    }

    public String getAeaId() {
        return nameToTextField.get("aeaId").getText();
    }

    public String getIssuer() {
        return nameToTextField.get("issuer").getText();
    }

    public String getAudience() {
        return nameToTextField.get("audience").getText();
    }

    public String getAeaType() {
        return nameToTextField.get("aeaType").getText();
    }
}
