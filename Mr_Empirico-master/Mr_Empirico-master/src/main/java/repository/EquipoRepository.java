package repository;

import Model.Equipo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class EquipoRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public EquipoRepository() {
        emf = Persistence.createEntityManagerFactory("sistema-mantenimiento");
        em = emf.createEntityManager();
    }

    public void agregarEquipo(Equipo equipo) {
        em.getTransaction().begin();
        em.persist(equipo);
        em.getTransaction().commit();
    }

    public List<Equipo> listarEquipos() {
        TypedQuery<Equipo> query = em.createQuery("SELECT e FROM Equipo e", Equipo.class);
        return query.getResultList();
    }

    public Optional<Equipo> buscarPorId(int id) {
        Equipo equipo = em.find(Equipo.class, id);
        return Optional.ofNullable(equipo);
    }

    public void actualizarEquipo(Equipo equipoActualizado) {
        em.getTransaction().begin();
        em.merge(equipoActualizado);
        em.getTransaction().commit();
    }

    public void eliminarEquipo(int id) {
        em.getTransaction().begin();
        Equipo equipo = em.find(Equipo.class, id);
        if (equipo != null) {
            em.remove(equipo);
        }
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }
}