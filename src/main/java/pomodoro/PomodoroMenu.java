package pomodoro;

import java.util.List;
import java.util.Scanner;
import tasks.Task;
import tasks.TaskService;

public class PomodoroMenu {
    private final PomodoroService pomodoroService;
    private final TaskService taskService;
    private Scanner scanner;

    public PomodoroMenu(PomodoroService pomodoroService,TaskService taskService,Scanner scanner){
        this.pomodoroService= pomodoroService;
        this.taskService= taskService;
        this.scanner= scanner;
    }

    public void menu(){
        while (true){
            System.out.println("\n=== Меню Pomodoro ===");
            System.out.println("1. Начать сессию по задаче");
            System.out.println("2. Просмотреть историю");
            System.out.println("0. Назад");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine().trim();

            switch(choice){
                case"1"-> startSession();
                case"2"-> pomodoroService.viewHistory();
                case"0"->{
                    return;
                }
                default-> System.out.println("Неверный выбор.");
            }
        }
    }

    private void startSession(){
        List<Task> tasks= taskService.getTasks();
        if(tasks.isEmpty()){
            System.out.println("Нет задач для выполнения!");
            return;
        }

        System.out.println("Выберите задачу для выполнения:");
        for (int i=0;i<tasks.size();i++){
            System.out.println((i+1)+". "+tasks.get(i).getTitle());
        }

        System.out.print("Номер задачи: ");
        try{
            int index= Integer.parseInt(scanner.nextLine().trim())-1;
            if (index< 0|| index>= tasks.size()){
                System.out.println("Неверный номер!");
                return;
            }

            Task task= tasks.get(index);
            startPomodoro(task);
            scanner= new Scanner(System.in);
            
        }catch(NumberFormatException e){
            System.out.println("Введите число!");
        }
    }

    private void startPomodoro(Task task){
        int totalMinutes= 25;
        int totalSeconds= totalMinutes * 60;
        int elapsed= 0;
        boolean paused= false;
        boolean stopped= false;

        System.out.println("⏳ Начинаем задачу: "+task.getTitle());
        System.out.println("Нажмите 'p' — пауза, 'r' — продолжить, 's' — завершить.");

        while (elapsed< totalSeconds && !stopped){
            if (!paused){
                try{
                    Thread.sleep(1000);
                    elapsed++;
                    int remaining= totalSeconds-elapsed;
                    int minutes= remaining/60;
                    int seconds= remaining%60;
                    System.out.printf("\rОсталось: %02d:%02d ",minutes,seconds);
                }catch (InterruptedException e){
                    break;
                }
            }

            try{
                if(System.in.available()> 0){
                    char command= (char) System.in.read();
                    if(command== 'p'){
                        paused = true;
                        System.out.println("\nПауза. Нажмите 'r' чтобы продолжить.");
                    }else if (command== 'r'){
                        paused= false;
                        System.out.println("Продолжаем!");
                    }else if (command== 's'){
                        System.out.println("\nСессия завершена.");
                        stopped = true;
                    }
                }
            }catch(Exception e){

            }
        }

        if(!stopped){
            System.out.println("\nСессия завершена!");
        }
        
        pomodoroService.saveSession(task.getTitle(),elapsed/60,!stopped);
    }
}
