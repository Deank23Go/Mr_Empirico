package service;

import Model.Mantenimiento;
import repository.MantenimientoRepository;
import java.util.List;

public class MantenimientoService {
    private MantenimientoRepository mantenimientoRepository;

    public MantenimientoService() {
        this.mantenimientoRepository = new MantenimientoRepository();
    }

    public void agregarMantenimiento(Mantenimiento m) {
        mantenimientoRepository.agregarMantenimiento(m);
    }

    public List<Mantenimiento> listarMantenimientos() {
        return mantenimientoRepository.listarMantenimientos();
    }

    public Mantenimiento buscarPorId(int id) {
        return mantenimientoRepository.buscarPorId(id).orElse(null);
    }

    public void actualizarMantenimiento(Mantenimiento mantenimiento) {
        mantenimientoRepository.actualizarMantenimiento(mantenimiento);
    }

    public void eliminarMantenimiento(int id) {
        mantenimientoRepository.eliminarMantenimiento(id);
    }

    public List<Mantenimiento> buscarPorEquipoId(int equipoId) {
        return mantenimientoRepository.buscarPorEquipoId(equipoId);
    }

    public List<Mantenimiento> buscarPorEstado(String estado) {
        return mantenimientoRepository.buscarPorEstado(estado);
    }

    public void close() {
        mantenimientoRepository.close();
    }
}