package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.Tunable;
import org.cytoscape.model.*;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTableManager;
import org.cytoscape.task.edit.*;
import java.util.*;



public class MergeTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    MergeTablesTaskFactory merge;
    List<String> col = new ArrayList<>();
    Collection<CyColumn> tcols;
    Collection<CyColumn> ncols;
    List<CyNetwork> list;
    CyTable ntab;
    CyNetwork net;
    CyTable tab;
    CyTableManager tabMan;
    Set<CyTable> tabSet;
    Collection<CyRow> alltRows;
    Collection<CyRow> allnRows;
    Map<String,Object> rowValues;
    Set<String> keySet;
    String tname;
    String nname;
    
    
    public MergeTask (final Manager manager) {
        this.manager = manager;
        
    }
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Merge");
        
        appMan = manager.getAppMan();
        tabMan = manager.getTabMan();
        net = appMan.getCurrentNetwork();
        tabSet = tabMan.getGlobalTables();
        
        for (CyTable table : tabSet) {
            tab = table;
        }
        
        
        tcols = tab.getColumns();
        ntab = net.getDefaultNodeTable();
        ncols = ntab.getColumns();

        list = new ArrayList<>();
        list.add(net);
        
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

        //ca marche pas :(
        for (CyColumn col : ncols) {
            if (col.isImmutable()==false) {

                nname = col.getName();
                nname = nname.replaceAll("[^A-Za-z0-9]","_");
                nname = nname.replaceAll("(_)\\1{1,}", "$1");
                col.setName(nname);
            }
        }
        
        insertTasksAfterCurrentTask​(new FilterTask(manager));
        
        
    }
    
}




