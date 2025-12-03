package controller;

import java.util.Scanner;

import service.ScheduleService;

public class ScheduleMenu{
    private ScheduleService service;
    private Scanner scanner;
    private final String[] DAYS={"Пн","Вт","Ср","Чт","Пт","Сб"};
    private final String[] TIMES={"08:00","09:50","11:40","13:45","15:35","17:25"};

    public ScheduleMenu(ScheduleService service,Scanner scanner){
        this.service=service;
        this.scanner=scanner;
    }

    public void menu(){
        while(true){
            System.out.println("\n=== РАСПИСАНИЕ ===");
            System.out.println("1. Добавить пару");
            System.out.println("2. Удалить пару");
            System.out.println("3. Показать расписание");
            System.out.println("0. Выход");
            System.out.print("Выберите: ");

            String choice=scanner.nextLine();
            
            switch(choice){
                case "1" -> addPair();
                case "2" -> removePair();
                case "3" -> showSchedule();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private void addPair(){
        
        try{
            System.out.println("Выберите день:");
            for(int i=0;i<DAYS.length;i++){
                System.out.println((i+1)+". "+DAYS[i]);
            }
    
            int dayIndex = Integer.parseInt(scanner.nextLine())-1;
            while(dayIndex >= DAYS.length || dayIndex < 0){
                System.out.println("Ошибка ввода, повторите: ");
                dayIndex = Integer.parseInt(scanner.nextLine())-1;
            }

            System.out.println("Выберите время:");
            for(int i=0;i<TIMES.length;i++){
                System.out.println((i+1)+". "+TIMES[i]);
            }
            
            int timeIndex=Integer.parseInt(scanner.nextLine())-1;
            while(timeIndex >= TIMES.length || timeIndex < 0){
                System.out.println("Ошибка ввода, повторите: ");
                timeIndex = Integer.parseInt(scanner.nextLine())-1;
            }

            System.out.println("Введите предмет: ");
            String subject=scanner.nextLine().trim();
            while(subject.length() > 15){
                System.out.println("Слишком длинное название!");
                subject = scanner.nextLine().trim();
            }
            
            String day=DAYS[dayIndex];
            String time=TIMES[timeIndex];
            service.add(day,time,subject);
            System.out.println("Пара добавлена!");
        }catch(Exception e){
            System.out.println("Ошибка ввода!");
        }
    }

    private void removePair(){
        try{
            System.out.println("Выберите день:");
            for(int i=0;i<DAYS.length;i++){
                System.out.println((i+1)+". "+DAYS[i]);
            }
            int dayIndex=Integer.parseInt(scanner.nextLine())-1;
            String day=DAYS[dayIndex];
            
            var daySchedule=service.getSchedule().get(day);
            if(daySchedule.isEmpty()){
                System.out.println("В этот день нет пар!");
                return;
            }
            
            System.out.println("Пары в "+day+":");
            for(int i=0;i<daySchedule.size();i++){
                var item=daySchedule.get(i);
                System.out.println((i+1)+". "+item.getTime()+" - "+item.getSubject());
            }
            
            System.out.print("Какую пару удалить? (номер): ");
            int pairIndex=Integer.parseInt(scanner.nextLine())-1;
            if (pairIndex<0||pairIndex>=daySchedule.size()){
                System.out.println("Такой пары не существует!");
                return;
            }
            service.remove(day,pairIndex);
            System.out.println("Пара удалена!");
        }catch(Exception e){
            System.out.println("Ошибка ввода!");
        }
    }

    private void showSchedule(){
        var schedule=service.getSchedule();
        
        System.out.print("\nВремя   ");
        for(String day:DAYS){
            System.out.printf("%-15s",day);
        }
        System.out.println();
        
        for(String time:TIMES){
            System.out.printf("%-8s",time);
            
            for(String day:DAYS){
                var daySchedule=schedule.get(day);
                String subject="";
                
                for(var item:daySchedule){
                    if(item.getTime().equals(time)){
                        subject=item.getSubject();
                        break;
                    }
                }
                
                System.out.printf("%-15s",subject);
            }
            System.out.println();
        }
    }
}
