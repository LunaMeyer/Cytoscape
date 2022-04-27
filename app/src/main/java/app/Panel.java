package app;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.event.*;
import javax.swing.*;


class Panel {
    
    
    JPanel myPanel;
    JFrame frame;
    JLabel txt1;
    JLabel txt2;
    JTextField network;
    JTextField data;
    JButton browseNetwork;
    JButton browseData;
    JButton buttonOk;
    JButton buttonCancel;
    Manager manager;
    
    
    public Panel(final Manager manager) {
        
        //create panel and components
        this.manager = manager;
        myPanel = new JPanel();
        txt1 = new JLabel("Choose your network file (format):");
        txt2 = new JLabel("Choose your data file:");
        browseNetwork = new JButton("Browse");
        browseData = new JButton("Browse");
        network = new JTextField(30);
        data = new JTextField(30);
        buttonOk= new JButton ("Ok");
        buttonCancel = new JButton("Cancel");
        
        //add components to panel
        myPanel.add(txt1);
        myPanel.add(network);
        myPanel.add(browseNetwork);
        myPanel.add(txt2);
        myPanel.add(data);
        myPanel.add(browseData);
        myPanel.add(buttonOk);
        myPanel.add(buttonCancel);
        
        //Create Layout
        GroupLayout groupLayout = new GroupLayout(myPanel); 
        groupLayout.setAutoCreateGaps(true);  
        groupLayout.setAutoCreateContainerGaps(true);  
        myPanel.setLayout(groupLayout); 
        
        //Set components in the layout
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()  
                .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(txt1).addComponent(txt2))  
                .addGroup(groupLayout.createParallelGroup(CENTER).addComponent(network).addComponent(data).addComponent(buttonCancel))
                .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(browseNetwork).addComponent(browseData).addComponent(buttonOk)));  
  
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()  
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(txt1).addComponent(network).addComponent(browseNetwork))  
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(txt2).addComponent(data).addComponent(browseData))
                .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(buttonCancel).addComponent(buttonOk)));  
        
        
        //create frame
        frame = createFrame("Browse");
        
        //add panel to frame
        frame.add(myPanel);
        
        //Create Action listeners for the buttons
        browseNetwork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    network.setText(browse.getSelectedFile().getPath());
                }
            }
        });
        
        browseData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    data.setText(browse.getSelectedFile().getPath());
                }
            }
        });
        
        //define buttonOk action
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("BONJOUR");
                manager.setNetworkPath(network.getText());
                manager.setDataPath(data.getText());
                frame.dispose();
                TestPanel test = new TestPanel(manager);
                test.setVisible();
                //TestFile test = new TestFile(manager);
            }
        });
        
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    
    
    //method to be called to show the panel
    public void setVisible(){
        frame.setVisible(true);
        frame.pack();
    }
    public JFrame createFrame(String title) {
    	frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
        
    }
    
    
    
}





