package model;

public class Pomodoro{
    private String taskName;
    private int minutes;
    private boolean completed;

    public Pomodoro(String taskName, int minutes, boolean completed){
        this.taskName= taskName;
        this.minutes=minutes;
        this.completed=completed;
    }

    @Override
    public String toString() {
        return taskName + " | " + minutes + " мин | " + (completed ? "Завершено" : "Прервано");
    }
}
