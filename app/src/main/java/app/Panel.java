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


class Panel {
    
    JPanel myPanel;
    JLabel lb1;
    String export;
    JLabel lbIcon;
    JLabel lb3;
    JFrame frame;
    ImageIcon icon;
    JButton buttonOk;
    JTextField txt;
    int x;
    
    public Panel() {
        
        //create panel and components
        myPanel = new JPanel();
        lb1 = new JLabel("C'est toi la banane");
        icon = new ImageIcon(getClass().getResource("/Images/clown.jpg"));
        lbIcon = new JLabel(icon, JLabel.CENTER);
        lb3 = new JLabel("Tu es eu");
        buttonOk= new JButton ("Ok");
        txt = new JTextField(20);
        x=0;
        
        //add components to panel
        myPanel.add(lb1);
        myPanel.add(lbIcon);
        myPanel.add(lb3);
        myPanel.add(buttonOk);
        myPanel.add(txt);
        
        //create frame
        frame = new JFrame("CLOWN");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(icon.getIconWidth()+20, icon.getIconHeight()+200);
        //frame.setSize(500, 400);
        
        //add panel to frame
        frame.add(myPanel);
        
        //define buttonOk action
        buttonOk.addActionListener(new ActionListener() {
            //@Override //ca fait quoi?
            public void actionPerformed(ActionEvent e) {
                
                frame.dispose();
                export = txt.getText();
                TestPanel testpanel = new TestPanel(export);
                testpanel.setVisible();
                
            }
        });
        
    }
    
    //method to be called to show the panel
    public void setVisible(){
        frame.setVisible(true);
    }
    
    
    
    
    
    
}





