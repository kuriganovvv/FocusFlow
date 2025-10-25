import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

class Subject{
    public ArrayList<String> names;
    public Subject(){
        this.names = new ArrayList<String>();
   }
}

public class study_management{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        Subject[] subjects = new Subject[6];
        int i;
        for(i = 0; i < 7; i++){
            subjects[i] = new Subject();
        }
        int num = 1;
        String operation = "\0";
        String day = "\0";
        String[] week = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
        String subject = "\0";
        int numbersubject = 0;
        int k = 0;
        String output;
        File studyfile;
        while(num > 0){
            num = in.nextInt();
            in.nextLine();
            switch(num){
                case 0:
                    System.out.println("Выход\n");
                    break;
                case 1:
                    System.out.println("Выберите действие - \"Добавление\", \"Удаление\" или \"Редактирование\":\n");
                    operation = in.nextLine();
                    System.out.println("Выберите учебный день недели:\n");
                    day = in.nextLine();
                    for(i = 0; i < 6; i++){
                        if(day.equals(week[i]))
                            break;
                    }
                    if(i == 6)
                        break;
                    System.out.println("Введите название предмета:\n");
                    subject = in.nextLine();
                    if(operation.equals("Добавление")){
                        subjects[i].names.add(subject);
                    }
                    else if(operation.equals("Удаление")){
                        System.out.println("Введите позицию предмета в списке:\n");
                        numbersubject = in.nextInt();
                        if (subjects[i].names.size() < numbersubject)
                            break;
                        subjects[i].names.remove(numbersubject);
                    }
                    else if(operation.equals("Редактирование")){
                        System.out.println("Введите позицию предмета в списке:\n");
                        numbersubject = in.nextInt();
                        if (subjects[i].names.size() < numbersubject)
                            break;
                        subjects[i].names.set(numbersubject, subject);
                    }
                    break;
                case 2: 
                    for(i = 0; i < 6; i++){
                        System.out.println(week[i] + ':' + '\n');
                        for(k = 0; k < subjects[i].names.size(); ++k){
                            System.out.println(subjects[i].names.get(k) + '\n');
                        }
                    }
                    break;
                case 3:
                    System.out.println("Выберите действие - \"Запись\" или \"Чтение\":\n");
                    operation = in.nextLine();
                    if(operation.equals("Чтение")){
                        try(FileReader reader = new FileReader("study.txt")){
                            int input;
                            while((input = reader.read())!= -1){
                                System.out.println((char)input);
                            }
                        }
                        catch(IOException ex){
                            try{
                                studyfile = new File("study.txt");
                                studyfile.createNewFile();
                            }
                            catch(IOException ex2){
                                System.out.println(ex2.getMessage());
                            }
                        }
                    }
                    else if(operation.equals("Запись")){
                        try(FileWriter writer = new FileWriter("study.txt", true)){
                            output = in.nextLine();
                            writer.write(output);
                            writer.append('\n');
                        }
                        catch(IOException ex){
                            try{
                                studyfile = new File("study.txt");
                                studyfile.createNewFile();
                                FileWriter writer = new FileWriter("study.txt", true);
                                output = in.nextLine();
                                writer.write(output);
                                writer.append('\n');
                            }
                            catch(IOException ex2){
                                System.out.println(ex2.getMessage());
                            }
                        }                    
                    }
                    break;
                case 4:
                default: System.out.println("Значение превышено\n");
            }
        }
        in.close();
    }
}
