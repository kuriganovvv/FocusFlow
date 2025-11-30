package model;

public class Schedule{
    private final String subject;
    private final String time;

    public Schedule(String subject, String time){
        this.subject = subject;
        this.time = time;
    }

    public String getSubject(){return subject;}
    public String getTime(){return time;}
}
