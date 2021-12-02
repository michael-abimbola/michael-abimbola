package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.GraduatingYearDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/graduatingyear")
@CrossOrigin("*")
public class GraduatingYearController {
    @Autowired
    private IGraduatingYearService service;

    @GetMapping
    public ResponseEntity<List<GraduatingYear>> getAllGraduatingYears(){

        List<GraduatingYear> allGradYears = null;
        allGradYears = service.getAllGraduatingYears();
        if(allGradYears != null){
            return ResponseEntity.ok().body(allGradYears);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/country/
    public ResponseEntity<GraduatingYear> createGraduatingYear(@RequestBody GraduatingYearDTO dto) {
        GraduatingYear graduatingYear = new GraduatingYear(dto.getGraduatingYear());
        if(service.addGraduatingYear(graduatingYear).equals("exists")){
            String entity = "Graduating year " + graduatingYear.getGraduatingYear() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else if(service.addGraduatingYear(graduatingYear).equals("zero input")){
            String entity = "Graduating year input was zero";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        String url = "graduationYear" +"/"+graduatingYear.getGraduatingYear();
        URI uri = URI.create(url);
        return new ResponseEntity(uri, HttpStatus.CREATED);
    }
}
