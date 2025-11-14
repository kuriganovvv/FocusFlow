package app;

import java.util.Scanner;

import schedule.ScheduleMenu;
import schedule.ScheduleService;

public class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        ScheduleService scheduleService=new ScheduleService();

        while(true){
            System.out.println("\n=== Главное меню ===");
            System.out.println("1. Расписание");
            System.out.println("2. Задачи");
            System.out.println("3. Pomodoro");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String choice=scanner.nextLine().trim();

            switch(choice){
                case "1": 
                    ScheduleMenu scheduleMenu=new ScheduleMenu(scheduleService,scanner);
                    scheduleMenu.showMenu();
                    break;
                    
                case "2": 
                    System.out.println("Модуль задач в разработке...");
                    break;
                    
                case "3": 
                    System.out.println("Pomodoro таймер в разработке...");
                    break;
                    
                case "0":{
                    System.out.println("Выход...");
                    scanner.close();
                    return;
                }
                
                default: 
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}
