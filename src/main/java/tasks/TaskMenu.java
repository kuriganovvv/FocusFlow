package tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("Введите название задачи: ");
            String title= scanner.nextLine().trim();
            if(title.isEmpty()){
                System.out.println("Название не может быть пустым!");
                return;
            }
            System.out.println("Введите описание задачи: ");
            String descr = scanner.nextLine().trim();
            if(title.isEmpty()){
                descr = "Без описания";
            }
            boolean killedTasks = false;

            System.out.print("Введите приоритет задачи(низкий, средний, высокий): ");
            String priority = scanner.nextLine();
            System.out.println(priority);
            if (!priority.equals("низкий") || !priority.equals("средний") || !priority.equals("высокий") || priority.isEmpty()){ //доработать проверку
                System.out.println("Приоритет должен быть по шаблону!(низкий, средний, высокий)");
                return;
            }


            //System.out.print("Введите дату (ГГГГ-ММ-ДД): ");
            //LocalDate date = LocalDate.parse(scanner.nextLine().trim())
            
            LocalDate date= LocalDate.now();
            
            String id = "s"; //реализовать id

            taskService.addTask(new Task(title, date, id, descr, killedTasks, priority));
            System.out.println("Задача добавлена.");
        }catch (Exception e){
            System.out.println("Ошибка ввода! Проверьте формат даты.");
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
