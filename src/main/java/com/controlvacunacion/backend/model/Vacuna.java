package com.controlvacunacion.backend.model;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "vacuna")
public class Vacuna {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_vacuna;

    @NotNull
    @Column(name = "nombre", length = 50)
    private String nombre;

    @NotNull
    @Column(name = "dosis")
    private int dosis;

    @Column(name = "dias_segunda_dosis")
    private Integer dias_segunda_dosis;

    public Vacuna() {}

    public Vacuna(String nombre, int dosis) {
        this.nombre = nombre;
        this.dosis = dosis;
    }

    public Vacuna(String nombre, int dosis, int dias_segunda_dosis) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.dias_segunda_dosis = dias_segunda_dosis;
    }

    public long getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(long id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public Integer getDias_segunda_dosis() {
        return dias_segunda_dosis;
    }

    public void setDias_segunda_dosis(Integer dias_segunda_dosis) {
        this.dias_segunda_dosis = dias_segunda_dosis;
    }

    @Override
    public String toString() {
        return "Vacuna{" +
                "id_vacuna=" + id_vacuna +
                ", nombre='" + nombre + '\'' +
                ", dosis=" + dosis +
                ", dias_segunda_dosis=" + dias_segunda_dosis +
                '}';
    }
}
