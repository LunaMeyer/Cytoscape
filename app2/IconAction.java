package app;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;
import java.util.*;


public class IconAction extends AbstractCyAction {

    private static final long serialVersionUID = 1L;
    private CySwingApplication desktopApp;
    private String networkPath;
    private String dataPath;
    final Manager manager;
    CyApplicationManager appMan;
    Collection<CyColumn> columns;
    String prefix = "log2_Abundance_Ratio_";
    int lgth = prefix.length();
    List<String> col = new ArrayList<>();
    
    public IconAction(CySwingApplication desktopApp, final Manager manager){
        // Add a menu item
        super("Icon Action");
        setPreferredMenu("Apps.banane");

        this.manager = manager;
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/banane.jpg"));
        ImageIcon smallIcon = new ImageIcon(getClass().getResource("/Images/banane.jpg"));

        // Add image icons on tool-bar and menu item
        putValue(LARGE_ICON_KEY, icon);
        putValue(SMALL_ICON, smallIcon);
        
        this.desktopApp = desktopApp;
        
    }


    public void actionPerformed(ActionEvent e) {
        
        

    }
    
    public boolean isInToolBar() {
        return true;
    }

    public boolean isInMenuBar() {
        return false;
    }
    
}

