package app;

import java.awt.Color;
import java.awt.Paint;
import java.util.Iterator;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.TaskIterator;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;

import org.cytoscape.view.model.*;
import org.cytoscape.view.vizmap.*;
import org.cytoscape.view.vizmap.mappings.*;
import org.cytoscape.view.presentation.property.*;
import org.cytoscape.view.presentation.property.values.NodeShape;
import org.cytoscape.view.vizmap.mappings.*;
import java.util.HashSet;
import java.util.Set;

public class TestViz extends AbstractTask {
    
    final Manager manager;
    CyNetwork net;
    CyApplicationManager appMan;
    CyNetworkView view;
    VisualStyle vizu1;
    CyTable tab;
    VisualMappingManager vizMan;
    VisualMappingFunctionFactory vizMapFacto;
    VisualMappingFunctionFactory vizMapFacto2;
    Set<VisualStyle> vsSet;
    
    public TestViz(final Manager manager) {
        this.manager = manager;
    }
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Vizuboi2");
        appMan = manager.getAppMan();
        vizMan = manager.getVizMan();
        view = appMan.getCurrentNetworkView();
        vizu1 = vizMan.getVisualStyle​(appMan.getCurrentNetworkView());
        
        vsSet = manager.loadVizFile();
        
        if (view == null || vsSet.size() == 0)
			return;
			
		final Set<CyNetworkView> views = new HashSet<CyNetworkView>();
		views.add(view);
		TaskIterator ti = manager.applyVizTask(views);

		insertTasksAfterCurrentTask(ti);
        
        
    }
    
}






