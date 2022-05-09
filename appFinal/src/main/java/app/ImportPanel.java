package app;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.util.List;


class ImportPanel {
    
    final Manager manager;
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
 	String path;
    
    
    public ImportPanel(final Manager manager) {
        
        this.manager = manager;
        
        //create panel and components
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
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        //add panel to frame
        frame.add(myPanel);
        frame.setVisible(true);
        frame.pack();
        
        //Create Action listeners for the buttons
        browseNetwork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    path = browse.getSelectedFile().getPath();
                    path = path.replace("\\","/");
                    manager.setNetworkPath(path);
                    network.setText(path);
                }
                
            }
        });
        
        browseData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    path = browse.getSelectedFile().getPath();
                    path = path.replace("\\","/");
                    manager.setDataPath(path);
                    data.setText(path);
                }
                
            }
        });
        
        //define buttonOk action
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.executeTask(new ImportTask(manager));
                frame.dispose();
                
            }
        });
        
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
    }
    
    
    private JFrame createFrame(String title) {
    	
        return frame;
    }
    
    
    
    
}





