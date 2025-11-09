package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("1. Расписание");
            System.out.println("2. Задачи");
            System.out.println("3. Pomodoro");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": 
                    System.out.println("В разработке...");
                    return;
                case "2": 
                    System.out.println("В разработке...");
                    return;
                case "3": 
                    System.out.println("В разработке...");
                    return;
                case "0": {
                    System.out.println("Выход...");
                    return;
                }
                default: 
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}
