package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name= "tasks")
public class TaskEntity{
    
    @Id
    private String id;
    /*    имя колонны | может ли быть нулем|длина максимальная  */
    
    @Column(name= "title", nullable = false, length = 100)
    private String title;
    
    @Column(name = "deadline", nullable = false)
    private LocalDate date;  
    
    @Column(name = "description", length = 500)
    private String descr;
    
    @Column(name = "subject", length = 50)
    private String subject;
    
    @Column(name = "is_completed")
    private boolean killedTask;  
    
    @Column(nullable = false)
    private int priority;  
    
    /*Конструкторы*/
    /*для Hibernate: используя рефлексию он сам найдет поля и методы для заполнения
    поэтому и конструктор для него пустой */ 
    public TaskEntity(){
    }
    /*конструктор для человеского управления */
    public TaskEntity(String id, String title, LocalDate date, String descr,String subject, boolean killedTask, int priority){
        this.id =id;
        this.title = title;
        this.date =date;
        this.descr = descr;
        this.subject= subject;
        this.killedTask = killedTask;
        this.priority = priority;
    }
    
    public String getId(){
        return id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public String getDescr(){
        return descr;
    }
    
    public String getSubject(){
        return subject;
    }
    
    public boolean isKilledTask(){
        return killedTask;
    }
    
    public int getPriority(){
        return priority;
    }
    
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }
    
    public void setDescr(String descr){
        this.descr = descr;
    }
    
    public void setSubject(String subject){
        this.subject = subject;
    }
    
    public void setKilledTask(boolean killedTask){
        this.killedTask = killedTask;
    }
    
    public void setPriority(int priority){
        this.priority = priority;
    }
    
}
