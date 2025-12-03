package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import model.Schedule;

public class ScheduleService{
    private final Map<String, List<Schedule>>schedule=new LinkedHashMap<>();
    private final ScheduleRepository repository;

    public final String[] DAYS ={"Пн","Вт","Ср","Чт","Пт","Сб"};

    public ScheduleService(){
        for(String day:DAYS){
            schedule.put(day,new ArrayList<>());
        }
        repository=new ScheduleRepository(); // сервис->репозиторий->ентити
        loadFromDatabase();
    }
    private void loadFromDatabase(){
        try{
            /*Загрузка */
            List<ScheduleEntity> entities =repository.findAll();
            /*Очищение памяти */
            for(String day:DAYS){
                schedule.get(day).clear();
            }
            /*Заполнение */
            for(ScheduleEntity entity:entities){
                String day= entity.getDay();
                String time= entity.getTime();
                String subject= entity.getSubject();
                /* Запись в память */
                schedule.get(day).add(new Schedule(subject, time));
                Collections.sort(schedule.get(day));
            }
            System.out.println("Загрузка завершена!");
        }catch(Exception e){
            System.out.println("Ошибка загрузки БД"+e.getMessage());
        }
    }

    public void add(String day,String time,String subject) {
        List<Schedule>daySchedule=schedule.get(day);
        if(daySchedule!=null){
            daySchedule.add(new Schedule(subject,time));
            Collections.sort(daySchedule);
        }
    }

    public void remove(String day,int index){
        List<Schedule>daySchedule=schedule.get(day);
        if(daySchedule!=null&&index<daySchedule.size()){
            daySchedule.remove(index);
        }
    }

    public Map<String,List<Schedule>>getSchedule(){
        return schedule;
    }
}
