package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.GraduatingClassDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/graduatingclass")
@CrossOrigin("*")
public class GraduatingClassController {
    @Autowired
    private IGraduatingClassService service;

    @GetMapping()
    public ResponseEntity<List<GraduatingClass>> getAllGraduatingClasses(){

        List<GraduatingClass> allGradClasses = null;
        allGradClasses = service.getAllGraduatingClasses();
        if(allGradClasses != null){
            return ResponseEntity.ok().body(allGradClasses);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/graduatingclass/
    public ResponseEntity<GraduatingClass> createGraduatingClass(@RequestBody GraduatingClassDTO dto) {
        GraduatingClass gradClass = new GraduatingClass(dto.getClassName());
        if(service.addGraduatignClass(gradClass).equals("exists")){
            String entity = "Graduating class " + gradClass.getClassName() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else if(service.addGraduatignClass(gradClass).equals("null")){
            String entity = "Graduating class input was zero";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        String url = "graduationclass" +"/"+gradClass.getClassName();
        URI uri = URI.create(url);
        return new ResponseEntity(uri, HttpStatus.CREATED);
    }
}
