package app;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class PanelTaskFactory extends AbstractTaskFactory {
	
	
	public PanelTaskFactory(){
		
	}
	
	
    public TaskIterator createTaskIterator(){
    
		return new TaskIterator(new PanelTask());
		
	}

	
}






