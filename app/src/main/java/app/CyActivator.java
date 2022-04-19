package app;

import java.util.Properties;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.service.util.AbstractCyActivator;

//This is the only needed import for the cyactivator
import org.osgi.framework.BundleContext;



public class CyActivator extends AbstractCyActivator {

    public CyActivator() {
        super();
    }

    //I dont know what is a bundle context and it does not matter. It is imported, put in the argument of the start method, and used to register services.
    public void start(BundleContext bc) {
        
        //Each registered service is in a {} blocks for clarity
        
        //This is the bananicon eventListener service
        {   
            //Get the object needed for cytoscape to read that specific service
            CySwingApplication cytoscapeDesktopService = getService(bc,CySwingApplication.class);
            //Create the service
            IconAction iconAction = new IconAction(cytoscapeDesktopService);
            //Register the service
            registerService(bc,iconAction,CyAction.class, new Properties());
        }
        
        
        //This is the menu item taskfactory
        {
            //create taskfactory
            PanelTaskFactory panelTaskFactory = new PanelTaskFactory ();
            //add menu item
            Properties props = new Properties();
            props.setProperty("preferredMenu","Apps.BANANAPP");
            props.setProperty("title","Prends 2 bananes");
            //register taskfactory
            registerService(bc,panelTaskFactory,TaskFactory.class, props);
        }
    }
}








