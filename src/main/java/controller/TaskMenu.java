package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import model.Task;
import service.TaskService;

public class TaskMenu{
    private final TaskService taskService;
    private final Scanner scanner;

    public TaskMenu(TaskService taskService,Scanner scanner){
        this.taskService =taskService;
        this.scanner= scanner;
    }

    public void menu(){
        while(true){
            System.out.println("\n=== Меню задач ===");
            System.out.println("1. Просмотреть задачи");
            System.out.println("2. Добавить задачу");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Изменить статус задачи");
            System.out.println("0. Назад");
            System.out.print("Выбор: ");
            String choice= scanner.nextLine().trim();

            switch(choice){
                case "1" -> viewTasks();
                case "2" -> addTask();
                case "3" ->removeTask();
                case "4" ->changeStatus();
                case "0"->{
                    return;
                }
                default ->System.out.println("Неверный выбор");
            }
        }
    }

    private void changeStatus(){
        //реализовать функцию
    }

    private void viewTasks(){
        List<Task> tasks= taskService.getTasks();
        if(tasks.isEmpty()){
            System.out.println("Задач нет.");
            return;
        }
        System.out.println("\n=== Список задач ===");
        for(int i=0;i< tasks.size();i++){
            System.out.println((i + 1)+". "+tasks.get(i));
        }
    }

    private void addTask(){
        try{
            boolean res = false;
            String title = "";
            long id = Calendar.getInstance().getTimeInMillis(); 
            int priority = 0;
            boolean killedTasks = false;
            LocalDate date = null;
            

            while(res != true){
                System.out.println("Введите название задачи: ");
                title= scanner.nextLine().trim();
                if(title.isEmpty()){
                    System.out.println("Название не может быть пустым!");
                }else{
                    res = true;
                }
            }

            System.out.println("Введите описание задачи: ");
            String descr = scanner.nextLine().trim();
            if(descr.isEmpty()){
                descr = "Без описания";
            }
            res = false;
            while(res != true){
                System.out.println("Введите приоритет задачи(низкий - 1, средний - 2, высокий - 3): ");
                priority = scanner.nextInt();
                if ((priority!=1 && priority!=2 && priority!=3)){
                    System.out.println("Приоритет должен быть по шаблону!(низкий - 1, средний - 2, высокий - 3)");
                }else{
                    res = true;
                }
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            res = false;
            
            
            while(res != true){
                try {
                    LocalDate t;
                    System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
                    String input = reader.readLine().trim();
                    if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("❌ Неверный формат! Должно быть: ГГГГ-ММ-ДД");
                        System.out.println("   Пример: 2025-12-25");
                        continue;
                    }
                    t = LocalDate.parse(input);
                    if(t.isBefore(LocalDate.now())) {
                        System.out.println("Введите будующую дату!");
                    }else{
                        res = true;
                        date = t;
                    }
                }catch (java.time.format.DateTimeParseException e){
                    System.out.println("❌ Неверный формат даты! Используйте ГГГГ-ММ-ДД");
                }catch (java.time.DateTimeException e){
                    System.out.println("❌ Несуществующая дата! Проверьте день и месяц");
                }
            } 
                
            scanner.nextLine();
            taskService.addTask(new Task(title, date, id, descr, killedTasks, priority));
            System.out.println("Задача добавлена.");
        }catch(IOException e){
            System.out.println("Ошибка ввода");
        }
    }

    private void removeTask(){
        List<Task> tasks= taskService.getTasks();
        if(tasks.isEmpty()){
            System.out.println("Список пуст.");
            return;
        }

        viewTasks();
        System.out.print("Введите номер задачи для удаления: ");
        
        try{
            int index= Integer.parseInt(scanner.nextLine().trim())-1;
            if(index>= 0 && index<tasks.size()){
                taskService.removeTask(index);
                System.out.println("Задача удалена.");
            }else{
                System.out.println("Неверный номер!");
            }
        }catch (NumberFormatException e){
            System.out.println("Введите число!");
        }
    }
}
