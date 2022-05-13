package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableManager;
import org.cytoscape.model.CyColumn;

import java.util.Set;
import java.util.Collection;
import javax.swing.JOptionPane;


public class ImportTable extends AbstractTask {
    
    final Manager manager;
    String dataPath;
    String networkPath;
    CyTable tab;
    CyTableManager tabMan;
    Set<CyTable> tabSet;
    Collection<CyColumn> tcols;
    String[] conditionList;
    String tname;
    
    
    public ImportTable (final Manager manager) {
    
        this.manager = manager;
        networkPath = manager.getNetworkPath();
        conditionList = manager.getConditionList();
        tabMan = manager.getTabMan();
        tabSet = tabMan.getGlobalTables();
        for (CyTable table : tabSet) {
            tab = table;
        }
        tcols = tab.getColumns();
        
    }
    
    
    public void run(TaskMonitor monitor){
        
        boolean check = true;
        for (String condition : conditionList) {
            boolean check2 = false;
            for (CyColumn column : tcols) {
                tname = column.getName();
                if (tname.equals("SUID")==false) {
                    if (tname.contains(condition)) {
                        check2 = true;
                        break;
                    }
                }
            }
            if (check2 == false) {
                check = false;
             	JOptionPane.showMessageDialog(null, "The condition "+condition+" couldn't be found in the data header.");
             	break;
            }
        }
        
        if (check == true) {
            manager.command(null, "network load file file='"+networkPath+"'");
            manager.setNetworkPath("");
        }
        
    }
    
    
}





