package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Task;
import repository.TaskRepository;
import model.TaskEntity;

public class TaskService{
    private final List<Task> tasks=new ArrayList<>();
    private final TaskRepository repository;
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GRAY = "\u001B[90m";
    int i = 0;

    public TaskService(){
        repository = new TaskRepository();
        loadFromDatabase();
    }
    private void loadFromDatabase(){
        try{
            List<TaskEntity> entities = repository.findAll();
            for(i = 1; i <= tasks.size(); i++){
                tasks.get(i).clear();
            }
            for(TaskEntity entity: entities){
                String title = entity.getTitle();
                LocalDate date = entity.getDate();
                String id = entity.getId();
                String descr = entity.getDescr();
                String subject = entity.getSubject();
                boolean iskilledTask = entity.isKilledTask();
                int priority = entity.getPriority();
                i++;
                String checkmarkColor = switch(priority) {
                    case 1 -> GREEN;   
                    case 2 -> YELLOW;  
                    case 3 -> RED;    
                    default -> RESET;
                };
                tasks.get(i).add(new Task(title, date, id, descr, subject, iskilledTask, priority));
                }
        }
        catch(Exception e){
            System.out.println("Ошибка загрузки БД"+e.getMessage());
            e.printStackTrace();
        }
    }
    public void addTask(String title, LocalDate date, String id, String descr, String subject, boolean killedTasks, int priority){
        TaskEntity entity = new TaskEntity(title, date, id, descr, subject, killedTasks, priority);
        repository.save(entity);
        tasks.add(new Task(title, date, id, descr, subject, killedTasks, priority));
        tasks.sort(Comparator.comparingLong(Task::getDaysUntilDeadline));
    }
    public void removeTask(int index){
        Task task = tasks.get(i);
        try{
            TaskEntity entity = repository.deleteById(task.getId());
            if (index>=0 &&index<tasks.size()) tasks.remove(index);
        }
        catch(Exception e) {
            System.err.println("Ошибка удаления: " + e.getMessage());
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
