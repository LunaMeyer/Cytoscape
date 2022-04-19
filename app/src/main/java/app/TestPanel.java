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
    JLabel lb1;
    JButton buttonOk;
    
    
    public TestPanel(String label) {
        
        myPanel = new JPanel();
        buttonOk= new JButton ("Ok");
        lb1= new JLabel("Votre reponse est: "+label);
        frame = new JFrame ("Answer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        myPanel.add(lb1);
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








