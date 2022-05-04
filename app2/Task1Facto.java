package app;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class Task1Facto extends AbstractTaskFactory {

    final Manager manager;
    
    public Task1Facto (final Manager manager) {
        this.manager = manager;
    }
    
    //Method detected and used by the voodoo to run the task. Necessary
    public TaskIterator createTaskIterator(){
    
        return new TaskIterator(new Task1(manager),new Task2(manager));
        
    }
    
}






