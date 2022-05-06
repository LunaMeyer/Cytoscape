package app;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.util.*;


class VizPanel {
    
    final Manager manager;
    JPanel myPanel;
    JFrame frame;
    JButton buttonOk;
    JButton buttonCancel;
    JButton buttonExport;
    JTextField clientNameField;
    JComboBox<String> dropDownList;
    VizTask2 viz;
    ExportTask export;
    
    List<String> nameList;
    String clientName;
    
    
    
    public VizPanel(final Manager manager) {
        
        //create panel and components
        this.manager = manager;
        nameList = manager.getParams();
        clientNameField = new JTextField("Client Name as Title    ");
        myPanel = new JPanel();
        buttonOk= new JButton ("Apply");
        buttonExport = new JButton ("Export");
        buttonCancel = new JButton("Cancel");
        dropDownList = new JComboBox<String>();
        for (String el : nameList) {
            dropDownList.addItem(el);
        }
        
        //add components to panel
        myPanel.add(dropDownList);
        myPanel.add(clientNameField);
        myPanel.add(buttonOk);
        myPanel.add(buttonCancel);
        myPanel.add(buttonExport);
        
        //create frame
        frame = new JFrame("StyleChooser");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setSize(450, 100);
        
        //add panel to frame
        frame.add(myPanel);
        frame.setVisible(true);
        
        
        
        buttonOk.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                manager.setRef(dropDownList.getSelectedItem().toString());
                clientName = clientNameField.getText();
                manager.setClientName(clientName);
                viz = new VizTask2(manager);
                manager.executeTask(viz);
                
            }
        });
        
        buttonCancel.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        buttonExport.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                
                export = new ExportTask(manager);
                manager.executeTask(export);
                
            }
        });
    }
    
    
    
}





