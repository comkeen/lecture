/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.ac.uos.aeat.AEATType;
import kr.ac.uos.aeat.AudienceType;
import kr.ac.uos.software_project.aeat.PublisherActionListener;

/**
 *
 * @author comkeen
 */
public class AeaPanel extends JPanel {

    private PublisherActionListener buttonActionListener;
    private Map<String, JComponent> nameToTextField;

    public AeaPanel(PublisherActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }

    private void initComponents() {
        this.add(createRecord("aeaId"));
        this.add(createRecord("issuer"));
        this.add(createComboBoxRecord("audience"));
        this.add(createRecord("aeaType"));
        this.add(createRecord("refAEAId"));
        this.add(createRecord("priority"));
        this.add(createRecord("wakeup"));

        //audience combobox item
        List<String> items = new ArrayList();
        for (AudienceType value : AudienceType.values()) {
            items.add(value.value());
        }
        ComboBoxModel comboBoxModel = new DefaultComboBoxModel(items.toArray());
        ((JComboBox) nameToTextField.get("audience")).setModel(comboBoxModel);
    }

    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5, 10, 5, 10));
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

    private Box createComboBoxRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);

        box.add(Box.createHorizontalGlue());

        JComboBox comboBox = new JComboBox();
        box.add(comboBox);

        nameToTextField.put(name, comboBox);
        return box;
    }

    public void loadAeat(AEATType aeat) {
        ((JTextField) nameToTextField.get("aeaId")).setText(aeat.getAEA().get(0).getAeaId());
        ((JTextField) nameToTextField.get("issuer")).setText(aeat.getAEA().get(0).getIssuer());
        ((JComboBox) nameToTextField.get("audience")).setSelectedItem((String)aeat.getAEA().get(0).getAudience().value());
        ((JTextField) nameToTextField.get("aeaType")).setText(aeat.getAEA().get(0).getAeaType().value());
        ((JTextField) nameToTextField.get("refAEAId")).setText(aeat.getAEA().get(0).getRefAEAId());
        ((JTextField) nameToTextField.get("priority")).setText(aeat.getAEA().get(0).getPriority().toString());
        ((JTextField) nameToTextField.get("wakeup")).setText(String.valueOf(aeat.getAEA().get(0).isWakeup()));
    }

    public String getAeaId() {
        return ((JTextField) nameToTextField.get("aeaId")).getText();
    }

    public String getIssuer() {
        return ((JTextField) nameToTextField.get("issuer")).getText();
    }

    public String getAudience() {
        return (String) ((JComboBox) nameToTextField.get("audience")).getSelectedItem();
    }

    public String getAeaType() {
        return ((JTextField) nameToTextField.get("aeaType")).getText();
    }
}
