package repository;

import Model.Mantenimiento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class MantenimientoRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public MantenimientoRepository() {
        emf = Persistence.createEntityManagerFactory("sistema-mantenimiento");
        em = emf.createEntityManager();
    }

    public void agregarMantenimiento(Mantenimiento mantenimiento) {
        em.getTransaction().begin();
        em.persist(mantenimiento);
        em.getTransaction().commit();
    }

    public List<Mantenimiento> listarMantenimientos() {
        TypedQuery<Mantenimiento> query = em.createQuery("SELECT m FROM Mantenimiento m", Mantenimiento.class);
        return query.getResultList();
    }

    public Optional<Mantenimiento> buscarPorId(int id) {
        Mantenimiento mantenimiento = em.find(Mantenimiento.class, id);
        return Optional.ofNullable(mantenimiento);
    }

    public void actualizarMantenimiento(Mantenimiento mantenimiento) {
        em.getTransaction().begin();
        em.merge(mantenimiento);
        em.getTransaction().commit();
    }

    public void eliminarMantenimiento(int id) {
        em.getTransaction().begin();
        Mantenimiento mantenimiento = em.find(Mantenimiento.class, id);
        if (mantenimiento != null) {
            em.remove(mantenimiento);
        }
        em.getTransaction().commit();
    }

    public List<Mantenimiento> buscarPorEquipoId(int equipoId) {
        TypedQuery<Mantenimiento> query = em.createQuery(
                "SELECT m FROM Mantenimiento m WHERE m.equipo.id = :equipoId", Mantenimiento.class);
        query.setParameter("equipoId", equipoId);
        return query.getResultList();
    }

    public List<Mantenimiento> buscarPorEstado(String estado) {
        TypedQuery<Mantenimiento> query = em.createQuery(
                "SELECT m FROM Mantenimiento m WHERE m.estado = :estado", Mantenimiento.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}