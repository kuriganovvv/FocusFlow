package tasks;

import java.time.LocalDate;

public class Task{
    private String title;
    private LocalDate date;

    public Task(String title,LocalDate date){
        this.title = title;
        this.date = date;
    }
    
    public String getTitle(){return title;}
    public LocalDate getDate(){return date;}
    
    @Override
    public String toString() {
        return title + " [" + date + "]";
    }

}
