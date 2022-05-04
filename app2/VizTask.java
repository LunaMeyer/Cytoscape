package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;
import java.util.*;

public class VizTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    Collection<CyColumn> columns;
    String prefix = "log2_Abundance_Ratio_";
    int lgth = prefix.length();
    List<String> col = new ArrayList<>();
    
    public VizTask(final Manager manager) {
        this.manager = manager;
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("VizTaskLaunch");
        appMan = manager.getAppMan();
        columns = appMan.getCurrentNetwork().getDefaultNodeTable().getColumns();
        for (CyColumn column : columns) {
            String name = column.getName();
            if (name.startsWith​(prefix)) {
                String tmp = name.substring​(lgth);
                col.add(tmp);
            }  
        }
        manager.setParams(col);
        VizPanel panel = new VizPanel(manager);
        
    }
        
    
}





