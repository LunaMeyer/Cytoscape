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
    JLabel txt3;
    JTextField network;
    JTextField data;
    JTextField conditions;
    JButton browseNetwork;
    JButton browseData;
    JButton buttonOk;
    JButton buttonCancel;
    String path;
    final String currentDir;
    
    
    public ImportPanel(final Manager manager) {
        
        this.manager = manager;
        currentDir = manager.getAppMan().getCurrentDirectory().getAbsolutePath();
        //create panel and components
        myPanel = new JPanel();
        txt1 = new JLabel("Choose your network file:");
        txt2 = new JLabel("Choose your data file:     ");
        txt3 = new JLabel("Enter conditions:         ");
        browseNetwork = new JButton("Browse");
        browseData = new JButton("Browse");
        network = new JTextField(30);
        data = new JTextField(30);
        conditions = new JTextField(36);
        buttonOk= new JButton ("Ok");
        buttonCancel = new JButton("Cancel");
        
        //add components to panel
        myPanel.add(txt1);
        myPanel.add(network);
        myPanel.add(browseNetwork);
        myPanel.add(txt2);
        myPanel.add(data);
        myPanel.add(browseData);
        myPanel.add(txt3);
        myPanel.add(conditions);
        myPanel.add(buttonOk);
        myPanel.add(buttonCancel);
        
        
        //create frame
        frame = new JFrame("Browse");
        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        //add panel to frame
        frame.add(myPanel);
        frame.setVisible(true);
        
        //Create Action listeners for the buttons
        browseNetwork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser browse = new JFileChooser(currentDir);
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    path = browse.getSelectedFile().getPath();
                    path = path.replace("\\","/");
                    network.setText(path);
                }
            }
        });
        
        browseData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                JFileChooser browse = new JFileChooser(currentDir);
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    path = browse.getSelectedFile().getPath();
                    path = path.replace("\\","/");
                    data.setText(path);
                }
            }
        });
        
        //define buttonOk action
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.setDataPath(data.getText());
                manager.setNetworkPath(network.getText());
                manager.setConditions(conditions.getText());
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
    
}





