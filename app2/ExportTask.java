package app;


import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;


public class ExportTask extends AbstractTask {
    
    final Manager manager;
    CyNetworkView view;
    String viewTitle;
    Double height;
    Double width;
    
    public ExportTask (final Manager manager) {
        
        this.manager = manager;
        view = manager.getAppMan().getCurrentNetworkView();
        width = view.getVisualProperty(BasicVisualLexicon.NETWORK_WIDTH);
        height = view.getVisualProperty(BasicVisualLexicon.NETWORK_HEIGHT);
        viewTitle = manager.getAppMan().getCurrentNetwork().NAME;
        
    }
    
    public void run(TaskMonitor monitor) {
        
        manager.command(null, "annotation add text text='le plus bo des titres' fontFamily='serif' fontSize=75 view=CURRENT x="+Double.toString(-width)+" y="+Double.toString(height*2), "view export options='JPEG (*.jpeg, *.jpg)' view=CURRENT outputFile="+viewTitle);
        
    }
    
}





