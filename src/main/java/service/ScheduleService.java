package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import model.Schedule;
import model.ScheduleEntity; 
import repository.ScheduleRepository;

public class ScheduleService{
    private final Map<String, List<Schedule>>schedule=new LinkedHashMap<>();
    private final ScheduleRepository repository;

    public final String[] DAYS ={"Пн","Вт","Ср","Чт","Пт","Сб"};

    public ScheduleService(){
        System.out.println("ScheduleService: Начинаю инициализацию...");
        for(String day:DAYS){
            schedule.put(day,new ArrayList<>());
        }
        System.out.println("ScheduleService: Создаю репозиторий...");
        repository=new ScheduleRepository(); // сервис->репозиторий->ентити
        System.out.println("ScheduleService: Загружаю из БД...");
        loadFromDatabase();
        System.out.println("ScheduleService: Инициализация завершена!");
    }
    private void loadFromDatabase(){
        System.out.println("loadFromDatabase: Начинаю загрузку...");
        try{
            /*Загрузка */
            List<ScheduleEntity> entities =repository.findAll();
            System.out.println("loadFromDatabase: Найдено " + entities.size() + " записей");
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
            System.out.println("Расписание загружено из БД");
        }catch(Exception e){
            System.out.println("Ошибка загрузки БД"+e.getMessage());
            e.printStackTrace();
        }
    }

    public void add(String day, String time, String subject) {
        List<Schedule> daySchedule = schedule.get(day);
        if (daySchedule != null) {
            try {
                System.out.println("ADD: Сохраняю в БД...");
                
                //Сохраняем в БД
                ScheduleEntity entity = new ScheduleEntity(day, time, subject);
                repository.save(entity);
                
                System.out.println("ADD: Добавляю в память...");
                
                //Добавляем в память 
                daySchedule.add(new Schedule(subject, time));
                Collections.sort(daySchedule);
                
                System.out.println("ADD: Пара сохранена");
            } catch (Exception e) {
                System.err.println("ADD: Ошибка: " + e.getMessage());
            }
        }
    }

    public void remove(String day, int index){
        List<Schedule> daySchedule =schedule.get(day);
        if (daySchedule != null && index < daySchedule.size()){
            try {
                // Получаем все записи этого дня 
                List<ScheduleEntity> entities= repository.findByDay(day);
                
                if (index >= 0 && index < entities.size()){
                    // Удаляем из БД по ID
                    ScheduleEntity entityToDelete = entities.get(index);
                    repository.deleteById(entityToDelete.getId());
                    
                    // Удаляем из памяти
                    daySchedule.remove(index);
                    
                    System.out.println("Пара удалена из БД и памяти");
                }
            } catch (Exception e) {
                System.err.println("Ошибка удаления: " + e.getMessage());
            }
        }
    }

    public Map<String,List<Schedule>>getSchedule(){
        return schedule;
    }
    public void cleanup() {
        if (repository != null) {
            repository.close();
            System.out.println("Соединение с БД закрыто");
        }
    }
}
