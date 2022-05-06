package app;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;


class TestPanel {
    
    JFrame frame;
    JPanel myPanel;
    JButton buttonOk;
    JLabel label;
    int nb;
    String nbstr;
    
    
    public TestPanel(String str) {
        
        myPanel = new JPanel();
        buttonOk= new JButton ("OK");
        frame = new JFrame ("TEST");
        //nbstr = Integer.toString(nb);
        label = new JLabel("\""+str+"\"");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        
        myPanel.add(label);
        myPanel.add(buttonOk);
        frame.add(myPanel);
        frame.setVisible(true);
        
        buttonOk.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    
}








