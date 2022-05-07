package app;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class StartFacto extends AbstractTaskFactory {

    final Manager manager;
    
    public StartFacto (final Manager manager) {
        this.manager = manager;
    }
    
    //Method detected and used by the voodoo to run the task. Necessary
    public TaskIterator createTaskIterator(){
    
        return new TaskIterator(new ImportTask(manager), new MergeTask(manager));
        
    }
    
}






