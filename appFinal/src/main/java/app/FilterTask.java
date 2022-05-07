package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyColumn;
import java.util.Collection;


public class FilterTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    CyTable tab;
    Collection<CyRow> rows;
    Collection<CyRow> rowsTest;
    Collection<CyRow> allRows;
    Collection<CyColumn> cols;
    CyNetwork net;
    
    
    public FilterTask (final Manager manager) {
    
        this.manager = manager;
        
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Filter");
        
        appMan = manager.getAppMan();
        net = appMan.getCurrentNetwork();
        tab = net.getDefaultNodeTable();
        
        allRows = tab.getAllRows();
        for (CyRow row : allRows) {
            if (row.isSet("Marked as")) {
                row.set("selected",true);
            }
        }
        
        insertTasksAfterCurrentTask​(new ClusterTask(manager));
        
        insertTasksAfterCurrentTask​(manager.getSelectNetworkTask().createTaskIterator(net));
        
        
    }
    
    
}





