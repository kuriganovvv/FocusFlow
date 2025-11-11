package tasks;
import java.time.LocalDate;

public class TaskItem {
    private String title;

    private LocalDate date;

    public TaskItem(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {return title;}
    public LocalDate getDate() {return date;}
    /* название 
     * дата (дедлайн)
     * дней до дедлайна
     * описание 
     * 
     * методы для работы с классом
    */
}
