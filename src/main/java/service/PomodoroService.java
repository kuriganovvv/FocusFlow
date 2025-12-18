package service;

import java.util.ArrayList;
import java.util.List;

import model.Pomodoro;
import model.PomodoroEntity;
import repository.PomodoroRepository;

public class PomodoroService{
    private List<Pomodoro> sessions=new ArrayList<>();
    private final PomodoroRepository repository;
    public PomodoroService(){
        this.repository = new PomodoroRepository();
        sessions= new ArrayList<>();
    }

    private void loadFromDatabase(){
        try{
            sessions.clear();

            List<PomodoroEntity> entities = repository.findAll();
            
            for(PomodoroEntity entity: entities){
                String taskName = entity.getTaskName();
                int minutes = entity.getMinutes();
                boolean completed = entity.getCompleted();
                
            }
        }
        catch(Exception e){
            System.out.println("Ошибка загрузки БД:"+e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveSession(String taskName,int minutes,boolean completed){
        sessions.add(new Pomodoro(taskName,minutes,completed));
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
