package pomodoro;

import java.util.ArrayList;
import java.util.List;

public class PomodoroService{
    private List<PomodoroItem> sessions;

    public PomodoroService(){
        sessions= new ArrayList<>();
    }

    public void saveSession(String taskName,int minutes,boolean completed){
        sessions.add(new PomodoroItem(taskName,minutes,completed));
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
