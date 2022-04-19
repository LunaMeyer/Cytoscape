package app;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import java.awt.BorderLayout;


class Panel {
    
    JPanel myPanel;
	JLabel lb1;
	JLabel lb2;
	JLabel lbIcon;
	JLabel lb3;
	JFrame frame;
	NewPanel panel;
	ImageIcon icon;
    
    public Panel() {
        myPanel = new JPanel();
        lb1 = new JLabel("C'est toi la banane");
        frame = new JFrame("CLOWN");
		icon = new ImageIcon(getClass().getResource("/Images/clown.jpg"));
		lbIcon = new JLabel(icon, JLabel.CENTER);
		lb3 = new JLabel("Tu es eu");
        frame.setSize(icon.getIconWidth()+20, icon.getIconHeight()+100);
        frame.setLocationRelativeTo(null);
        myPanel.add(lb1);
        myPanel.add(lbIcon);
        myPanel.add(lb3);
	    frame.add(myPanel);
        
    }
    
    public void setVisible(){
        frame.setVisible(true);
    }
    
    
    
    
    
}





