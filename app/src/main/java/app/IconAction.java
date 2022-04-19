package app;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;


public class IconAction extends AbstractCyAction {

    private static final long serialVersionUID = 1L;
    private CySwingApplication desktopApp;
    
    public IconAction(CySwingApplication desktopApp){
        // Add a menu item
        super("Icon Action");
        setPreferredMenu("Apps.banane");

        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/banane.jpg"));
        ImageIcon smallIcon = new ImageIcon(getClass().getResource("/Images/banane.jpg"));

        // Add image icons on tool-bar and menu item
        putValue(LARGE_ICON_KEY, icon);
        putValue(SMALL_ICON, smallIcon);
        
        this.desktopApp = desktopApp;
        
    }


    public void actionPerformed(ActionEvent e) {
        
        Panel panel = new Panel();
        panel.setVisible();
        
        
        
        
    }
    
    
    public boolean isInToolBar() {
        return true;
    }


    public boolean isInMenuBar() {
        return false;
    }
    
}

