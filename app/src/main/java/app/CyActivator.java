package app;

import java.util.Properties;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;



public class CyActivator extends AbstractCyActivator {

	public CyActivator() {
		super();
	}


	public void start(BundleContext bc) {
	    {
		    CySwingApplication cytoscapeDesktopService = getService(bc,CySwingApplication.class);
		    IconAction iconAction = new IconAction(cytoscapeDesktopService);
		    registerService(bc,iconAction,CyAction.class, new Properties());
		}
		
		
		{
		    //create taskfactory
		    PanelTaskFactory panelTaskFactory = new PanelTaskFactory ();
		    //add menu item
		    Properties props = new Properties();
		    props.setProperty("preferredMenu","Apps.BANANA");
		    props.setProperty("title","Prends 2 bananes");
		    //register taskfactory
		    registerService(bc,panelTaskFactory,TaskFactory.class, props);
		}

	}
}








