package tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService{
    private List<Task> tasks;

    public void changeStatus(){
        
    }

    public TaskService(){
        tasks=new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    
    public void removeTask(int index){
        if (index>=0 &&index<tasks.size()){
            tasks.remove(index);
        }
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public List<Task> getTasksByDate(LocalDate date){
        List<Task> result=new ArrayList<>();
        for (Task t : tasks){
            if(t.getDate().equals(date)) result.add(t);
        }
        return result;
    }
}
