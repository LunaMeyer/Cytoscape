package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyColumn;

import java.util.Collection;
import java.util.List;


public class FilterTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    CyTable tab;
    Collection<CyRow> rows;
    Collection<CyRow> rowsTest;
    Collection<CyRow> allRows;
    Collection<CyColumn> cols;
    CyNetwork net;
    String prefix;
    String columnRef;
    List<String> conditions;
    
    
    public FilterTask (final Manager manager) {
    
        this.manager = manager;
        prefix = manager.getPrefix();
        
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Filter");
        
        appMan = manager.getAppMan();
        net = appMan.getCurrentNetwork();
        tab = net.getDefaultNodeTable();
        conditions = manager.parseAllConditions();
        columnRef = prefix+conditions.get(0)+"_";
        
        
        allRows = tab.getAllRows();
        for (CyRow row : allRows) {
            if (row.isSet(columnRef)) {
                row.set("selected",true);
            }
        }
        
        insertTasksAfterCurrentTask​(new ClusterTask(manager));
        
        insertTasksAfterCurrentTask​(manager.getSelectNetworkTask().createTaskIterator(net));
        
        
    }
    
    
}





