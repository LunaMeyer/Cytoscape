package app;


import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.work.Tunable;
import org.cytoscape.work.swing.TunableUIHelper;


public class PanelTask extends AbstractTask {
    
    Panel panel;
    
    public PanelTask(){
    
        panel = new Panel();
        
    }
    
    
    public void run(TaskMonitor monitor){
        
        panel.setVisible();
        
       
        
    }
    
    
}



