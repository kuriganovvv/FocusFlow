
import java.util.Scanner;

import service.ScheduleService;
import service.TaskService;
import service.PomodoroService;
import controller.ScheduleMenu;
import controller.TaskMenu;
import controller.PomodoroMenu;

public class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        ScheduleService scheduleService = null;// вынесем чтобы finnaly мог очистить иначе внутри try он не увидит
        
        try{
            scheduleService=new ScheduleService();
            ScheduleMenu scheduleMenu=new ScheduleMenu(scheduleService,scanner);

            TaskService taskService = new TaskService();
            TaskMenu taskMenu =new TaskMenu(taskService, scanner);

            PomodoroService pomodoroService = new PomodoroService();
            PomodoroMenu pomodoroMenu = new PomodoroMenu(pomodoroService, taskService, scanner);
            
            while(true){
                System.out.println("\n=== Главное меню ===");
                System.out.println("1. Расписание");
                System.out.println("2. Задачи");
                System.out.println("3. Pomodoro");
                System.out.println("0. Выход");
                System.out.print("Выбор: ");
                String choice=scanner.nextLine().trim();

                switch(choice){
                    case "1" -> scheduleMenu.menu();
                    case "2" -> taskMenu.menu();
                    case "3" -> pomodoroMenu.menu();
                    case "0" -> {
                        System.out.println("Выход...");
                        return;
                    }
                    
                    default -> System.out.println("Неверный выбор, попробуйте снова.");
                }
            }
        }finally{
            if (scanner != null){
                scanner.close();
            }
            if (scheduleService != null){
                scheduleService.cleanup();
            }
            System.out.println("Приложение завершено.");
        }
    }
}
