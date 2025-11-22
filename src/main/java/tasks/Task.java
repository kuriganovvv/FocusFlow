package tasks;

import java.time.LocalDate;

public class Task{
    private final String title;
    private LocalDate date;
    private final long id;
    private final String descr;
    private boolean killedTask;
    private int priority;

    public Task(String title, LocalDate date, long id, String descr, boolean killedTask, int priority){
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
