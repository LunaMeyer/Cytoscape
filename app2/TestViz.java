package app;

import java.awt.Color;
import java.awt.Paint;
import java.util.Iterator;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;

import org.cytoscape.view.model.*;
import org.cytoscape.view.vizmap.*;
import org.cytoscape.view.vizmap.mappings.*;
import org.cytoscape.view.presentation.property.*;
import org.cytoscape.view.presentation.property.values.NodeShape;
import org.cytoscape.view.vizmap.mappings.*;

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
    
    public TestViz(final Manager manager) {
        this.manager = manager;
    }
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Vizuboi2");
        appMan = manager.getAppMan();
        vizMan = manager.getVizMan();
        view = appMan.getCurrentNetworkView();
        vizu1 = vizMan.getVisualStyle​(appMan.getCurrentNetworkView());
        
        ContinuousMapping fillNode = (ContinuousMapping) vizu1.getVisualMappingFunction​ (BasicVisualLexicon.NODE_FILL_COLOR);
        
        ContinuousMappingPoint point = fillNode.getPoint​(0);
        
        BoundaryRangeValues<Paint> brv1 = new BoundaryRangeValues<Paint>(Color.WHITE, Color.WHITE, Color.WHITE);
        Double val1 = 0.001;
        fillNode.addPoint(val1,brv1);
        
        vizu1.apply(view);
    }
    
}






