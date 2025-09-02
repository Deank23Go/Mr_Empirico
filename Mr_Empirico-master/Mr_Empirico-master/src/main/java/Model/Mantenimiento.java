package Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mantenimientos")
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipo; // "PREVENTIVO" o "CORRECTIVO"
    private String descripcion;
    private String estado; // "PENDIENTE", "REALIZADO", "CANCELADO"

    @Temporal(TemporalType.DATE)
    private Date fechaProgramada;

    @Temporal(TemporalType.DATE)
    private Date fechaRealizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @com.fasterxml.jackson.annotation.JsonBackReference
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    public Mantenimiento() {}

    public Mantenimiento(String tipo, String descripcion, String estado, Date fechaProgramada, Date fechaRealizacion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaProgramada = fechaProgramada;
        this.fechaRealizacion = fechaRealizacion;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Date getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(Date fechaProgramada) { this.fechaProgramada = fechaProgramada; }

    public Date getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(Date fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }

    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }
}