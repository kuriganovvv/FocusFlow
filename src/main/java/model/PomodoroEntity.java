package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "PomodoroEntity")
@Table(name= "Pomodoro")

public class PomodoroEntity {
    @Id
    private String id; 

    @Column(name = "taskName", nullable = false, length = 100)
    private String taskName;

    @Column(name = "minutes", nullable = false, length = 100)
    private int minutes;

    @Column(name = "completed", nullable = false, length = 100)
    private boolean completed;

    public PomodoroEntity(){
    }

    public PomodoroEntity(String taskName, int minutes, boolean completed){
        this.taskName= taskName;
        this.minutes=minutes;
        this.completed=completed;
    }

    public String getId(){
        return id;
    }
    
    public int getMinutes(){
        return minutes;
    }
    
    public void setDay(int minutes){
        this.minutes =minutes;
    }
    
    public String getTaskName(){
        return taskName;
    }
    
    public void setTime(String taskName){
        this.taskName= taskName;
    }
    
    public boolean getCompleted(){
        return completed;
    }
    
    public void setSubject(Boolean completed){
        this.completed = completed;
    }
}
