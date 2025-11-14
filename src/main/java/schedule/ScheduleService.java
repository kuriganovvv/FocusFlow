package schedule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScheduleService{
    private final Map<String, List<ScheduleItem>>schedule=new LinkedHashMap<>();

    public ScheduleService(){
        String[]days={"Пн","Вт","Ср","Чт","Пт","Сб"};
        for(String day:days){
            schedule.put(day,new ArrayList<>());
        }
    }

    public void add(String day,String time,String subject) {
        List<ScheduleItem>daySchedule=schedule.get(day);
        if(daySchedule!=null){
            daySchedule.add(new ScheduleItem(subject,time));
        }
    }

    public void remove(String day,int index){
        List<ScheduleItem>daySchedule=schedule.get(day);
        if(daySchedule!=null&&index<daySchedule.size()){
            daySchedule.remove(index);
        }
    }

    public Map<String,List<ScheduleItem>>getSchedule(){
        return schedule;
    }
}
