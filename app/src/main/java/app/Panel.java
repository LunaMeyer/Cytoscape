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
import javax.swing.JFileChooser;


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
        
        //create frame
        frame = new JFrame("Browse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(450, 200);
        
        //add panel to frame
        frame.add(myPanel);
        
        
        browseNetwork.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    network.setText(browse.getSelectedFile().getAbsolutePath());
                }
            }
        });
        
        browseData.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) { 
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    data.setText(browse.getSelectedFile().getAbsolutePath());
                }
            }
        });
        
        //define buttonOk action
        buttonOk.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BONJOUR");
                manager.setNetworkPath(network.getText());
                manager.setDataPath(data.getText());
                frame.dispose();
                //TestPanel test = new TestPanel(manager);
                //test.setVisible();
                TestFile test = new TestFile(manager);
            }
        });
        
        buttonCancel.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    
    
    //method to be called to show the panel
    public void setVisible(){
        frame.setVisible(true);
    }
    
    
}





