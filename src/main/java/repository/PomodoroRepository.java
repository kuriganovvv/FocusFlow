package repository;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

import model.ScheduleEntity;
import model.TaskEntity;
import model.PomodoroEntity;

public class PomodoroRepository {
    private EntityManagerFactory emf;//фабрика звезд
    
    public PomodoroRepository(){// Персистенс.хмл
        emf = Persistence.createEntityManagerFactory("focusflow_pu");
    }
    public void save(PomodoroEntity entity){
        EntityManager em = emf.createEntityManager();// Лариса Долина 
        try { // продает квартиру 
            em.getTransaction().begin(); // сделка началась
            em.persist(entity); // Лариса получила деньги
            em.getTransaction().commit(); // сделка закончилась
        } finally{
            em.close();// Долина вернет квартиру себе при любом исходе 
        }
    }

    public List<PomodoroEntity> findAll(){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT s FROM PomodoroEntity s", 
                PomodoroEntity.class
            ).getResultList();
        } finally{
            em.close();
        }
    }

    public void deleteById(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            PomodoroEntity entity = em.find(PomodoroEntity.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null) emf.close();// закроем его
    }
}
