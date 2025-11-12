package tasks;
import java.time.LocalDate;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate deadline;
    private LocalDate date;

    public TaskItem(String title, LocalDate date, LocalDate deadline, String description) {
        this.title = title;
        this.date = date;
        this.deadline = deadline;
        this.description = description;
    } 

    public String getTitle() {return title;}//геттеры
    public LocalDate getDate() {return date;}
    public LocalDate getDeadline() {return deadline;} 
    public String getDescription() {return description;}
    
    public void setDeadline(LocalDate deadline) {this.deadline = deadline;}//сеттеры
    public void setDate(LocalDate date) {this.date = date;} 
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
}
