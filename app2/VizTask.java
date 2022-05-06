package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;
import java.util.*;

public class VizTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    Collection<CyColumn> cycols;
    String prefix = "log2_Abundance_Ratio_";
    int lgth = prefix.length();
    int fullLgth;
    List<String> conditions = new ArrayList<>();
    List<String> endConditions = new ArrayList<>();
    Map<String,Integer> map = new HashMap<String,Integer>(4);
    List<String[]> pairs = new ArrayList<>();
    String current;
    String tmp;
    String name;
    String[] pair;
    String ctrl = "CTRL";
    String veh;

    
    public VizTask(final Manager manager) {
        this.manager = manager;
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("VizTaskLaunch");
        manager.parseParam();
        VizPanel panel = new VizPanel(manager);
        
    }
        
    
}





