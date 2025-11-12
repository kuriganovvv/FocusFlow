package schedule;

public class ScheduleItem {
    private final String subject;
    private final String time; 

    public ScheduleItem(String subject, String time){
        this.subject=subject;
        this.time=time;
    }

    public String getSubject(){return subject;}
    public String getTime(){return time;}
}
