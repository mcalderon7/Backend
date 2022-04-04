package com.controlvacunacion.backend.controller;

import com.controlvacunacion.backend.model.Empleado;
import com.controlvacunacion.backend.model.Vacuna;
import com.controlvacunacion.backend.repository.EmpleadoRepository;
import com.controlvacunacion.backend.repository.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    VacunaRepository vacunaRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @GetMapping("/empleado")
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        try {
            List<Empleado> empleados = new ArrayList<Empleado>(empleadoRepository.findAll());
            if (empleados.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("id") long id) {
        Optional<Empleado> empleadoData = empleadoRepository.findById(id);
        return empleadoData.map(empleado -> new ResponseEntity<>(empleado, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/empleado")
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        try {
            if (empleado.getRef_vacuna() != null) {
                Optional<Vacuna> vacunaData = vacunaRepository.findById(empleado.getRef_vacuna().getId_vacuna());
                if (vacunaData.isPresent()) {
                    Vacuna vac = vacunaData.get();
                    Date fecha_segunda_dosis = null;
                    if (vac.getDosis() > 1) {
                        fecha_segunda_dosis = calcularFechaSegundaDosis(empleado.getFecha_primera_dosis(), vac.getDias_segunda_dosis());
                    }
                    Empleado emp = empleadoRepository
                            .save(new Empleado(empleado.getNombre(), empleado.getPuesto(), empleado.getRef_vacuna(), empleado.getFecha_primera_dosis(), fecha_segunda_dosis));
                    return new ResponseEntity<>(emp, HttpStatus.CREATED);
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                Empleado emp = empleadoRepository
                        .save(new Empleado(empleado.getNombre(), empleado.getPuesto()));
                return new ResponseEntity<>(emp, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable("id") long id, @RequestBody Empleado empleado) {
        Optional<Empleado> empleadoData = empleadoRepository.findById(id);
        if (empleadoData.isPresent()) {
            Empleado emp = empleadoData.get();
            emp.setNombre(empleado.getNombre());
            emp.setPuesto(empleado.getPuesto());
            emp.setRef_vacuna(empleado.getRef_vacuna());
            emp.setFecha_primera_dosis(empleado.getFecha_primera_dosis());
            emp.setEstado_primera_dosis(empleado.isEstado_primera_dosis());

            Date fecha_segunda_dosis = empleado.getFecha_segunda_dosis();
            if (fecha_segunda_dosis == null) {
                Optional<Vacuna> vacunaData = vacunaRepository.findById(empleado.getRef_vacuna().getId_vacuna());
                if (vacunaData.isPresent()) {
                    Vacuna vac = vacunaData.get();
                    if (vac.getDosis() > 1) {
                        fecha_segunda_dosis = calcularFechaSegundaDosis(empleado.getFecha_primera_dosis(), vac.getDias_segunda_dosis());
                    }
                }
            }
            emp.setFecha_segunda_dosis(fecha_segunda_dosis);
            emp.setEstado_segunda_dosis(empleado.isEstado_segunda_dosis());
            return new ResponseEntity<>(empleadoRepository.save(emp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<HttpStatus> deleteEmpleado(@PathVariable("id") long id) {
        try {
            empleadoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Date calcularFechaSegundaDosis(Date fecha_primera_dosis, Integer dias_segunda_dosis) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha_primera_dosis);
        cal.add(Calendar.DAY_OF_MONTH, dias_segunda_dosis);
        return new java.sql.Date(cal.getTimeInMillis());
    }

}