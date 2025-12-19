package model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "PomodoroEntity")
@Table(name= "pomodoro_sessions")

public class PomodoroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name", nullable = false, length = 100)
    private String taskName;

    @Column(name = "minutes", nullable = false)
    private int minutes;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @Column(name = "session_date", nullable = false)
    private LocalDateTime sessionDate;

    public PomodoroEntity(){
        this.sessionDate = LocalDateTime.now();
    }

    public PomodoroEntity(String taskName, int minutes, boolean completed){
        this.taskName= taskName;
        this.minutes=minutes;
        this.completed=completed;
        this.sessionDate = LocalDateTime.now();
    }

    public Long getId(){ return id; }
    public void setId(Long id) { this.id = id;}
    
    public int getMinutes(){ return minutes; }
    public void setMinutes(int minutes) { this.minutes = minutes;}
    
    public String getTaskName(){ return taskName; }
    public void setTaskName(String taskName){ this.taskName = taskName;}
    
    public boolean isCompleted(){ return completed; }
    public void setCompleted(boolean completed){ this.completed = completed;}
    
}
