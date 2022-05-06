package app;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;
import java.util.*;

import org.cytoscape.view.presentation.property.*;
import org.cytoscape.view.model.*;



public class IconAction extends AbstractCyAction {

    private static final long serialVersionUID = 1L;
    private CySwingApplication desktopApp;

    CyNetworkView view;
    final Manager manager;
    CyApplicationManager appMan;
    List<String> conditions = new ArrayList<>();
    List<String> endConditions = new ArrayList<>();
    List<String[]> pairs = new ArrayList<>();
    String prefix = "log2_Abundance_Ratio_";
    Map<String,Integer> map = new HashMap<String,Integer>(4);
    int lgth = prefix.length();
    int fullLgth;
    String current;
    String tmp;
    String name;
    String[] pair;
    String ctrl;
    String veh;


    
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
    
        //endConditions = manager.parseParam();
        appMan = manager.getAppMan();
        view = appMan.getCurrentNetworkView();
        Double widthD = view.getVisualProperty(BasicVisualLexicon.NETWORK_WIDTH);
        Double heightD = view.getVisualProperty(BasicVisualLexicon.NETWORK_HEIGHT);


        //manager.command(null, "cluster featurevector createNewNetwork=true edgeAttribute=FeatureDistance edgeCutoff='0.05' ignoreMissing=true metric='Euclidean distance' nodeAttributeList='log2_Abundance_Ratio_"+endConditions.get(0)+"_,log2_Abundance_Ratio_"+endConditions.get(1)+"_,log2_Abundance_Ratio_"+endConditions.get(2)+"_,log2_Abundance_Ratio_"+endConditions.get(3)+"_' selectedOnly=false zeroMissing=false");
        //manager.command(null, /*"network load file file='STRING network - Homo_sapiens_nat.cyjs'",*/ "table import file file='Elysia_Bioscience_Data_Res.xlsx' caseSensitiveNetworkCollectionKeys=false dataTypeTargetForNetworkCollection='Node Table Columns' firstRowAsColumnNames=true, keyColumnForMapping=stringdb_canonical_name targetNetworkCollection='STRING network - Homo sapiens_nat.cyjs' whereImportTable='Network Collection' keyColumnIndex=20 startLoadRow=1");
        manager.command(null, "annotation add text text='le plus bo des titres' fontFamily='serif' fontSize=75 view=CURRENT x="+Double.toString(-widthD)+" y="+Double.toString(heightD*2));
    }
    
    public boolean isInToolBar() {
        return true;
    }

    public boolean isInMenuBar() {
        return false;
    }
    
}

