package app;


import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.work.Tunable;
import org.cytoscape.work.swing.TunableUIHelper;


public class PanelTask extends AbstractTask {
    
    //run app, run
    public void run(TaskMonitor monitor){
        
        Panel panel = new Panel();
        panel.setVisible();
        
        
    }
    
    
}



