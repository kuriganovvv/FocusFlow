package service;

import java.util.ArrayList;
import java.util.List;

import model.Pomodoro;
import model.PomodoroEntity;
import repository.PomodoroRepository;

public class PomodoroService{
    private List<Pomodoro> sessions=new ArrayList<>();
    private final PomodoroRepository repository;
    public PomodoroService() {
        this.sessions = new ArrayList<>();
        this.repository = new PomodoroRepository();
        loadFromDatabase();
    }

    private void loadFromDatabase(){
        try{
            sessions.clear();

            List<PomodoroEntity> entities = repository.findAll();
            
            for(PomodoroEntity entity: entities){
                String taskName = entity.getTaskName();
                int minutes = entity.getMinutes();
                boolean completed= entity.isCompleted();
                Pomodoro pomodoro= new Pomodoro(taskName, minutes, completed);
                sessions.add(pomodoro);
            }
            
            System.out.println("Загружено " +sessions.size()+ " Pomodoro сессий из БД");
            
        } catch (Exception e) {
            System.out.println("Ошибка загрузки БД:"+e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveSession(String taskName, int minutes, boolean completed){
        // сохранение в бд(сначала надо бины создать, тоесть обьекты для маппинга в бд)
         PomodoroEntity entity = new PomodoroEntity(taskName, minutes, completed);
         repository.save(entity);
         // в озу сохранение
         sessions.add(new Pomodoro(taskName, minutes, completed));
       
         System.out.println("Pomodoro сессия сохранена: " + taskName);
     }

    public void viewHistory(){
        System.out.println("=== История Pomodoro-сессий ===");
        if(sessions.isEmpty()){
            System.out.println("История пуста.");
            return;
        }

        for(int i=0;i<sessions.size();i++){
            System.out.println((i+1)+". "+sessions.get(i));
        }
    }
}
