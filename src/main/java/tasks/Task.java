package tasks;

import java.time.LocalDate;

public class Task{
    private final String title;
    private LocalDate date;
    private final String id;
    private final String descr;
    private boolean killedTask;
    private final String priority;

    public Task(String title, LocalDate date, String id, String descr, boolean killedTask, String priority){
        this.title = title;
        this.date = date;
        this.id = id;
        this.descr = descr;
        this.killedTask = killedTask;
        this.priority = priority;
    }
    
    public String getTitle(){return title;}
    public LocalDate getDate(){return date;}
    
    @Override
    public String toString() {
        return title + " [" + date + "]" + id + descr + killedTask + priority;
    }

}
