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


class TestPanel {
    
    JFrame frame;
    JPanel myPanel;
    JLabel lbIcon;
    JLabel lbData;
    JLabel lbNetwork;
    JButton buttonOk;
    ImageIcon icon;
    Manager manager;
    
    
    public TestPanel(final Manager manager) {
        
        this.manager = manager;
        myPanel = new JPanel();
        buttonOk= new JButton ("Ok");
        frame = new JFrame ("Answer");
        lbData = new JLabel(manager.getDataPath());
        lbNetwork = new JLabel(manager.getNetworkPath());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        myPanel.add(lbData);
        myPanel.add(lbNetwork);
        myPanel.add(buttonOk);
        frame.add(myPanel);
        
        
        buttonOk.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    
    public void setVisible(){
        frame.setVisible(true);
    }
    
}








