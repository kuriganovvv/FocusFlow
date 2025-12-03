package model;
/* импорт JPA (Жакарт Упорный,прикладной программный интерфейс ) */
/* Hibernate(Соня) испольнитель воли царя Жакарта*/
import jakarta.persistence.*;

// сообщаем Соне, что этот класс таблица с именем.
@Entity  
@Table(name = "schedule_items")

public class ScheduleEntity{
    /*Автоматическая генерация ID(Автоинкремент):1,2,3... */
    @Id // декоратор для айди
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // магическим способом Соня присваивает айди
    /* колонны, не нулевые и длины имен */
    @Column(name ="day_of_week", nullable= false,length =10)
    private String day;
    
    @Column(name= "time_slot", nullable = false, length= 10)
    private String time;
    
    @Column(name = "subject",nullable= false, length = 50)
    private String subject;
    
    // Конструктор по умолчанию для Сони
    public ScheduleEntity(){}
    // Конструктор с параметрами это для меня
    public ScheduleEntity(String day, String time, String subject){
        this.day = day;
        this.time= time;
        this.subject = subject;
    }
    
    // Геттеры и сеттеры для каждой колонки
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getDay(){
        return day;
    }
    
    public void setDay(String day){
        this.day =day;
    }
    
    public String getTime(){
        return time;
    }
    
    public void setTime(String time){
        this.time= time;
    }
    
    public String getSubject(){
        return subject;
    }
    
    public void setSubject(String subject){
        this.subject = subject;
    }
    
    @Override
    public String toString() {
        return time +" - "+ subject;
    }
}
