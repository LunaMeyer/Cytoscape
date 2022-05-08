package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MergeTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    CyNetwork net;
    Collection<CyColumn> tcols;
    Collection<CyColumn> ncols;
    Collection<CyRow> alltRows;
    Collection<CyRow> allnRows;
    CyTable tab;
    CyTable ntab;
    String tname;
    String nname;
    List<CyNetwork> list;
    CyTableManager tabMan;
    Set<CyTable> tabSet;
    Map<String,Object> rowValues;
    Set<String> keySet;
    
    
    public MergeTask (final Manager manager) {
        this.manager = manager;
        
    }
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Merge");
        
        appMan = manager.getAppMan();
        tabMan = manager.getTabMan();
        net = appMan.getCurrentNetwork();
        ntab = net.getDefaultNodeTable();
        tabSet = tabMan.getGlobalTables();
        
        for (CyTable table : tabSet) {
            tab = table;
        }
        
        tcols = tab.getColumns();
        for (CyColumn col : tcols) {
            if (col.isImmutable()==false) {
        
                nname = col.getName();
                nname = nname.replaceAll("\\W","_");
                nname = nname.replaceAll("(_)\\1{1,}", "$1");
                
                col.setName(nname);
            }
        }
        
        for (CyColumn column : tcols) {
            tname = column.getName();
            if (tname.equals("SUID")==false) {
                Class<?> type = column.getType();
                ntab.createColumn(tname, type, false);
            }
        }
        
        alltRows = tab.getAllRows();
        allnRows = ntab.getAllRows();
        for (CyRow nrow : allnRows) {
            if (nrow.isSet("stringdb_canonical_name")) {
                for (CyRow row : alltRows) {
                    if (nrow.get("stringdb_canonical_name", String.class).equals(row.get("Accession", String.class))) {
                        rowValues = row.getAllValues();
                        keySet = rowValues.keySet();
                        for (String cellName : keySet) {
                            nrow.set(cellName, rowValues.get(cellName));
                        }
                    }
                }
            }
        }
        
        
        insertTasksAfterCurrentTask​(new FilterTask(manager));
        
        
    }
    
}




