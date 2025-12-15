package repository;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

import model.ScheduleEntity;

public class ScheduleRepository {
    private EntityManagerFactory emf;//фабрика звезд
    
    public ScheduleRepository(){// Персистенс.хмл
        emf = Persistence.createEntityManagerFactory("focusflow_pu");
    }
    
    public void save(ScheduleEntity entity){
        EntityManager em = emf.createEntityManager();// Лариса Долина 
        try { // продает квартиру 
            em.getTransaction().begin(); // сделка началась
            em.persist(entity); // Лариса получила деньги
            em.getTransaction().commit(); // сделка закончилась
        } finally{
            em.close();// Долина вернет квартиру себе при любом исходе 
        }
    }
    
    public List<ScheduleEntity> findAll(){
        EntityManager em = emf.createEntityManager();
        try {
            /*JPQL запрос  */
            return em.createQuery(
                "SELECT s FROM ScheduleEntity s", // выбрать такие s, что ЧедулЭнтити имеют тип 
                ScheduleEntity.class // вернуть обьект
            ).getResultList(); // вернуть List<обьект>
        } finally{
            em.close();// исход не важен, закрыть мы должны
        }
    }
    public List<ScheduleEntity> findByDay(String day) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ScheduleEntity> query = em.createQuery(
                "SELECT s FROM ScheduleEntity s WHERE s.day = :day ORDER BY s.time", 
                ScheduleEntity.class
            );
            query.setParameter("day", day);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            ScheduleEntity entity = em.find(ScheduleEntity.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    /* Звезды кончились, но цирк открыт */
    public void close() {
        if (emf != null) emf.close();// закроем его
    }
}
