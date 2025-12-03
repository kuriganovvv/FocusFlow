package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Task{
    private final String title;
    private final LocalDate date;
    private final String id;
    private final String descr;
    private final String subject;
    private boolean killedTask;
    private final int priority;

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GRAY = "\u001B[90m";

    public Task(String title, LocalDate date, String id, String descr, String subject, boolean killedTask, int priority){
        this.title = title;
        this.date = date;
        this.id = id !=null? id:UUID.randomUUID().toString();
        this.descr = descr != null ? descr : "Без описания";
        this.subject = subject != null ? subject : "Без предмета";
        this.killedTask = killedTask;
        this.priority = priority;
    }
    
    public String getTitle(){return title;}
    public LocalDate getDate(){return date;}
    public String getId(){ return id; }
    public String getSubject(){return subject;}
    public boolean isKilledTask(){return killedTask;}
    public void setKilledTask(boolean killedTask){this.killedTask = killedTask;}
    public int getPriority(){return priority;}

    public long getDaysUntilDeadline(){
        return ChronoUnit.DAYS.between(LocalDate.now(), date);
    }
    
    /*ЦветГалочка + Название + Пробелы + ЦветПредмет + ЦветДедлайн +
    *ЦветОписание */
    @Override
    public String toString() {
        // Пустая строка
        StringBuilder sb = new StringBuilder();
        // + ЦветГалочка + Название задачи 
        String checkmarkColor = switch(priority) {
            case 1 -> GREEN;   
            case 2 -> YELLOW;  
            case 3 -> RED;    
            default -> RESET;
        };
        sb.append(checkmarkColor)
          .append(killedTask ? "[✓]" : "[ ]")
          .append(RESET)
          .append(" ")
          .append(title);
        
        // + Пробелы
        int currentLength = sb.length();
        int targetLength = 35;
        if(currentLength < targetLength)sb.append(" ".repeat(targetLength - currentLength));

        // + ЦветПредмет
        if(!subject.equals("Без предмета"))
           sb.append(GRAY).append("[").append(subject).append("]").append(RESET).append("  ");

        // + ЦветДедлайн
        long days = getDaysUntilDeadline();
        String daysColor = days <= 1 ? RED : (days <= 3 ? YELLOW : GREEN);
        String daysText = days < 0 ? "просрочено" : 
                          days == 0 ? "сегодня" : 
                          days == 1 ? "1 д" : 
                          days + " д";   
        sb.append(daysColor).append("[").append(daysText).append("]").append(RESET);
        
        /*ЦветОписание*/
        if(!descr.equals("Без описания"))
            sb.append("\n        ").append(GRAY).append(descr).append(RESET);
        
        return sb.toString();
    }
}
