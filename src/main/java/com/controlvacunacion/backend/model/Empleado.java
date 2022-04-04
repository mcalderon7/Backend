package com.controlvacunacion.backend.model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_empleado;

    @NotNull
    @Column(name = "nombre", length = 50)
    private String nombre;

    @NotNull
    @Column(name = "puesto", length = 50)
    private String puesto;

    @OneToOne
    @JoinColumn(name = "ref_vacuna", referencedColumnName = "id_vacuna")
    private Vacuna ref_vacuna;

    @Column(name = "fecha_primera_dosis")
    private Date fecha_primera_dosis;

    @Column(name = "estado_primera_dosis")
    private boolean estado_primera_dosis;

    @Column(name = "fecha_segunda_dosis")
    private Date fecha_segunda_dosis;

    @Column(name = "estado_segunda_dosis")
    private boolean estado_segunda_dosis;

    public Empleado() {}

    public Empleado(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.estado_primera_dosis = false;
        this.estado_segunda_dosis = false;
    }

    public Empleado(String nombre, String puesto, Vacuna ref_vacuna, Date fecha_primera_dosis, Date fecha_segunda_dosis) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.ref_vacuna = ref_vacuna;
        this.fecha_primera_dosis = fecha_primera_dosis;
        this.fecha_segunda_dosis = fecha_segunda_dosis;
        this.estado_primera_dosis = true;
        this.estado_segunda_dosis = false;
    }

    public long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Vacuna getRef_vacuna() {
        return ref_vacuna;
    }

    public void setRef_vacuna(Vacuna ref_vacuna) {
        this.ref_vacuna = ref_vacuna;
    }

    public Date getFecha_primera_dosis() {
        return fecha_primera_dosis;
    }

    public void setFecha_primera_dosis(Date fecha_primera_dosis) {
        this.fecha_primera_dosis = fecha_primera_dosis;
    }

    public boolean isEstado_primera_dosis() {
        return estado_primera_dosis;
    }

    public void setEstado_primera_dosis(boolean estado_primera_dosis) {
        this.estado_primera_dosis = estado_primera_dosis;
    }

    public Date getFecha_segunda_dosis() {
        return fecha_segunda_dosis;
    }

    public void setFecha_segunda_dosis(Date fecha_segunda_dosis) {
        this.fecha_segunda_dosis = fecha_segunda_dosis;
    }

    public boolean isEstado_segunda_dosis() {
        return estado_segunda_dosis;
    }

    public void setEstado_segunda_dosis(boolean estado_segunda_dosis) {
        this.estado_segunda_dosis = estado_segunda_dosis;
    }
}
