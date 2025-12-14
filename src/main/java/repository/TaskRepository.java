package repository;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

import model.ScheduleEntity;
import model.TaskEntity;

public class TaskRepository {
    private EntityManagerFactory emf;
    
    public TaskRepository(){
        emf = Persistence.createEntityManagerFactory("task_pu");
    }
    
    public void save(TaskEntity entity){
        EntityManager em = emf.createEntityManager(); 
        try { 
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally{
            em.close();
        }
    }
    
    public List<TaskEntity> findAll(){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT s FROM TaskEntity s", 
                TaskEntity.class
            ).getResultList();
        } finally{
            em.close();
        }
    }

    public void deleteById(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            TaskEntity entity = em.find(TaskEntity.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null) emf.close();
    }
}
