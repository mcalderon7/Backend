package com.controlvacunacion.backend.controller;

import com.controlvacunacion.backend.model.Vacuna;
import com.controlvacunacion.backend.repository.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class VacunaController {

    @Autowired
    VacunaRepository vacunaRepository;

    @GetMapping("/vacuna")
    public ResponseEntity<List<Vacuna>> getAllVacunas() {
        try {
            List<Vacuna> vacunas = new ArrayList<Vacuna>(vacunaRepository.findAll());
            if (vacunas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(vacunas, HttpStatus.OK);
        } catch (Exception e) {
            System.out.print(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}