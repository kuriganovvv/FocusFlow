package controller;

import java.time.LocalDate;
import java.util.UUID;
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

    private void viewTasks(){
        List<Task> tasks= taskService.getTasks();
        if(tasks.isEmpty()){
            System.out.println("Задач нет.");
            return;
        }
        System.out.println("\n"+"=".repeat(19)+"Список задач"+"=".repeat(19));
        for(int i=0;i< tasks.size();i++){
            System.out.println((i + 1)+". "+tasks.get(i));
        }
        System.out.println("=".repeat(50));
    }
    private void addTask(){
        try{
            String title = "";
            String id = UUID.randomUUID().toString(); 
            int priority = 0;
            boolean killedTasks = false;
            LocalDate date = null;
            

            while(true) {
                System.out.println("Введите название задачи: ");
                title = scanner.nextLine().trim();
                if(!title.isEmpty()) break;
                System.out.println("Название не может быть пустым!");
            }

            System.out.println("Введите описание задачи: ");
            String descr = scanner.nextLine().trim();
            if(descr.isEmpty())descr = "Без описания";
            
            System.out.println("Введите предмет: ");
            String subject = scanner.nextLine().trim();
            if(subject.isEmpty())subject="Без предмета";

            while(priority<1 || priority>3){
                System.out.println("Введите приоритет задачи(низкий - 1, средний - 2, высокий - 3): ");
                try {
                    priority = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Введите число!");
                }
            }

            while(date==null){
                try{
                    System.out.printf("Введите дату (от %s): ",LocalDate.now());
                    String input = scanner.nextLine().trim();
                    if (!input.matches("\\d{4}-\\d{2}-\\d{2}"))continue;
                    
                    LocalDate enteredDate = LocalDate.parse(input);
                    if(enteredDate.isBefore(LocalDate.now()))continue;
                    date=enteredDate;
                    
                }catch (java.time.format.DateTimeParseException e){
                    System.out.println("Неверный формат даты! Используйте ГГГГ-ММ-ДД");
                }catch (java.time.DateTimeException e){
                    System.out.println("Несуществующая дата! Проверьте день и месяц");
                }
            } 
                
            taskService.addTask(title, date, id, descr,subject, killedTasks, priority);
            System.out.println("Задача добавлена.");
        }catch(Exception e){
            System.out.println("Ошибка ввода"+e.getMessage());
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
    private void changeStatus(){
        List<Task> tasks = taskService.getTasks();
        if(tasks.isEmpty()){
            System.out.println("Задач нет.");
            return;
        }
        
        viewTasks();
        System.out.println("Введите номер задачи для изменения статуса: ");
        
        try{
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if(index >= 0 && index < tasks.size()){
                Task task = tasks.get(index);
                
                task.setKilledTask(!task.isKilledTask());
                System.out.println("Статус изменён!");
            } else{
                System.out.println("Неверный номер!");
            }
        } catch(NumberFormatException e){
            System.out.println("Введите число!");
        }
    }
}
