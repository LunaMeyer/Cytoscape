package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.Tunable;
import org.cytoscape.model.*;
import org.cytoscape.model.subnetwork.*;
import org.cytoscape.task.edit.*;
import java.util.*;



public class Task2 extends AbstractTask {
    
    CyApplicationManager appMan;
    MergeTablesTaskFactory merge;
    Manager manager;
    ImportDataTableTaskFactory merge2;
    List<String> col = new ArrayList<>();
    
    
    public Task2 (final Manager manager) {
        this.manager = manager;
    }
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("My task");
        appMan = manager.getAppMan();
        CyNetwork net = appMan.getCurrentNetwork();
        CyRootNetwork root = manager.getRoot(net);
        CyTable tab = appMan.getCurrentTable();
        Collection<CyColumn> cols = tab.getColumns();
        for (CyColumn column : cols) {
            String colName = column.getName();
            col.add(colName);
        }
        CyTable ntab = net.getDefaultNodeTable();
        CyColumn link = ntab.getColumn("stringdb_canonical_name");
        //CyColumn key = tab.getPrimaryKey();
        //int nb = net.getNodeCount();
        //TestPanel test = new TestPanel(nb);
        //ntab.deleteColumn("target_family");
        List<CyNetwork> list = new ArrayList<>();
        list.add(net);
        Collection<Long> rowtotest = ntab.getMatchingKeys​("id", "120621",Long.class);
        /*
        Collection<Long> testpk = new ArrayList<Long>();
        for (Long row : rowtotest) {
            testpk.add(row.get("name",Long.class));
        }
        */
        Collection<Long> rowtodel = ntab.getMatchingKeys​("stringdb_canonical_name",null,Long.class);
        int k = root.getNodeCount();
        
        Collection<CyRow> allrow = ntab.getAllRows();
        /*
        for (CyRow row : allrow) {
            String str = row.get("stringdb_canonical_name", String.class);
            String str2 = "null";
            //TestPanel test = new TestPanel("'"+str+"'");
            
            if (str.equals("null")) {
                boolean w = rowtodel.add(row);
            }
        }
        */
        /*
        for (CyRow row : allrow) {            
            if (row.getRaw("stringdb_canonical_name")==null) {
                String bridge = row.get("id",String.class);
                Collection<Long> tmprow = ntab.getMatchingKeys​("id",bridge,Long.class);
                for (Long lg : tmprow) {
                    rowtodel.add(lg);
                }
            }
        }
        
        int y = rowtodel.size();
        TestPanel test1 = new TestPanel("test1: "+Integer.toString(y));
        boolean x = ntab.deleteRows(rowtodel);
        if (x==true){
            TestPanel test2 = new TestPanel("WIN");
        }
        */
        merge = manager.getMergeTask();
        manager.execute(merge.createTaskIterator​(tab,ntab,col,"Accession",false,true,false,list,root,link,CyNode.class));
        
        
        
        
    }
    
}
