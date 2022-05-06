package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;


public class VizTask extends AbstractTask {
    
    final Manager manager;

    
    public VizTask(final Manager manager) {
        this.manager = manager;
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("VizTaskLaunch");
        manager.parseParam();
        VizPanel panel = new VizPanel(manager);
        
    }
        
    
}





