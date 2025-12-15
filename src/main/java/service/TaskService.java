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
            tasks.clear();

            List<TaskEntity> entities = repository.findAll();
            
            for(TaskEntity entity: entities){
                String title = entity.getTitle();
                LocalDate date = entity.getDate();
                String id = entity.getId();
                String descr = entity.getDescr();
                String subject = entity.getSubject();
                boolean isKilledTask = entity.isKilledTask();
                int priority = entity.getPriority();
                
                Task task = new Task(title,date,id,descr,subject,isKilledTask,priority);
                tasks.add(task);
            }
            tasks.sort(Comparator.comparingLong(Task::getDaysUntilDeadline));
            System.out.println("ЗАГРУЖЕНО "+tasks.size()+"задач из БД");
        }
        catch(Exception e){
            System.out.println("Ошибка загрузки БД:"+e.getMessage());
            e.printStackTrace();
        }
    }
    public void addTask(String title, LocalDate date, String id, String descr,String subject, boolean killedTasks, int priority){
        Task task = new Task(title, date, id, descr, subject, killedTasks, priority);

        TaskEntity entity = new TaskEntity(
            task.getId(),
            task.getTitle(),
            task.getDate(),
            task.getDescr(),
            task.getSubject(),
            task.isKilledTask(),
            task.getPriority()
        );

        repository.save(entity);
        tasks.add(task);
        tasks.sort(Comparator.comparingLong(Task::getDaysUntilDeadline));
    }
    public void removeTask(int index){
    if(index >= 0 && index < tasks.size()){
        Task task = tasks.get(index); 
        try{
            repository.deleteById(task.getId());

            tasks.remove(index);
            
            System.out.println("Задача удалена из БД и памяти");
        } catch(Exception e){
            System.err.println("Ошибка удаления: " + e.getMessage());
        }
    } else {
        System.out.println("Неверный индекс задачи: " + index);
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
    public void close() {
        if (repository != null) {
            repository.close();
            System.out.println("Соединение с задачами БД закрыто");
        }
    }
}
