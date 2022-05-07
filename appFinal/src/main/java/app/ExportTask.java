package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.presentation.annotations.AnnotationManager;
import org.cytoscape.view.presentation.annotations.Annotation;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.View;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import java.io.File;
import java.util.List;


public class ExportTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    AnnotationManager annMan;
    CyNetworkView view;
    CyNetwork network;
    String condition;
    File viewFile;
    String clientName;
    String networkName;
    File dir;
    List<CyNode> nodeList;
    List<Annotation> annList;
    CyNode nodes;
    Double minX;
    Double minY;
    Double maxX;
    Double maxY;
    Double maxW;
    Double maxH;
    
    
    public ExportTask (final Manager manager) {
        
        this.manager = manager;
        appMan = manager.getAppMan();
        annMan = manager.getAnnMan();
        view = appMan.getCurrentNetworkView();
        annList = annMan.getAnnotations​(view);
        network = appMan.getCurrentNetwork();
        networkName = network.getRow(network).get(CyNetwork.NAME, String.class);
        clientName = manager.getClientName();
        dir = appMan.getCurrentDirectory();
        nodeList = network.getNodeList();
        condition = manager.getRef();
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
	    maxX = Double.MIN_VALUE;
		maxY = Double.MIN_VALUE;
		maxH = Double.MIN_VALUE;
         
    }
    
    
    public void run(TaskMonitor monitor) {
    
        monitor.setTitle("Exportation Task");
        
        condition = condition.replaceAll("/","-");
        String jpegName = dir.getAbsolutePath()+"/"+networkName+" - "+condition+".jpeg";
        viewFile = new File(jpegName);
        
        //Change name if name exists
        int cpt = 0;
        while (viewFile.exists()) {
            cpt += 1;
            jpegName = dir.getAbsolutePath()+"/"+networkName+" - "+condition+" ("+Integer.toString(cpt)+").jpeg";
            viewFile = new File(jpegName);
        }
        
        //This bit of code (and the corresponding variables called earlier) was taken from the git repository of the cytoscape Legend creator app.
        for (CyNode node : nodeList) {
			View<CyNode> nodeview = view.getNodeView(node);
			Double x = nodeview.getVisualProperty(BasicVisualLexicon.NODE_X_LOCATION);
			Double y = nodeview.getVisualProperty(BasicVisualLexicon.NODE_Y_LOCATION);
			Double w = nodeview.getVisualProperty(BasicVisualLexicon.NODE_WIDTH);
			Double h = nodeview.getVisualProperty(BasicVisualLexicon.NODE_HEIGHT);
	        
	        maxH = Math.max(maxH, h);
			minX = Math.min(minX, x-w/2);
			minY = Math.min(minY, y-h/2);
			maxX = Math.max(maxX, x+w/2);
			maxY = Math.max(maxY, y+h/2);
		}

        //delete annotation if exists
        if (annList.size()>0) {
            annMan.removeAnnotation​(annList.get(0));
        }
        
        
        //export
        insertTasksAfterCurrentTask​(manager.commandTask(null, "view export options='JPEG (*.jpeg, *.jpg)' view=CURRENT outputFile='"+jpegName+"'"));
        
        //annotate and reset the view to fit the title
        insertTasksAfterCurrentTask​(manager.commandTask(null, "annotation add text text='"+clientName+"' fontFamily='serif' fontStyle=Italic fontSize=60 view=CURRENT x="+Double.toString(minX)+" y="+Double.toString(maxY+maxH), "view fit content view=CURRENT"));
        
    }
    
}





