package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.TeacherDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.exceptions.NullFieldsException;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.FarewellMessage;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Teacher;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IFareWellMsgService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingClassService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin("*")
public class TeacherController {
    @Autowired
    private ITeacherService service;

    @Autowired
    private IFareWellMsgService fareWellMsgService;

    @Autowired
    private IGraduatingClassService gradClassService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){

        List<Teacher> allTeachers = null;
        allTeachers = service.getAllTeachers();
        if(allTeachers != null){
            return ResponseEntity.ok().body(allTeachers);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/country/
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDTO dto) {
        Teacher teacher = new Teacher(dto.getUsername(), dto.getProfileName(), dto.getPwd(), dto.getRole(), dto.getGraduatingClass());
        try {
            if(service.addTeacher(teacher)){
                String url = "teacher" + "/" + teacher.getUsername();
                URI uri = URI.create(url);
                return new ResponseEntity(uri, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String entity = "null fields";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @PostMapping("/farewellmsg/{username}/{gradClassName}")
    public ResponseEntity<FarewellMessage> addFareWellMessage(@PathVariable(value = "username")String username, @PathVariable(value = "gradClassName") String gradClassName, @RequestBody FarewellMessage farewellMessage)throws NullFieldsException{
        Teacher teacher = service.getFirstTeacherByUsrID(username);
        GraduatingClass graduatingClass = gradClassService.getFirstGraduatingClassByClassName(gradClassName);
        ///we check if the teacher exists or not
        if(teacher != null){
            if(graduatingClass != null){
                farewellMessage.setTeacher(teacher);
                farewellMessage.setGraduatingClass(graduatingClass);
                fareWellMsgService.addFarewellMsg(farewellMessage);
                String entity = "farewell message of username " + teacher.getUsername() + " has been added";
                return new ResponseEntity(entity, HttpStatus.CREATED);
            }
            else{
                String entity = "Graduation class could not be found";
                return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
            }
        }
        String entity = "Teacher could not be found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/farewellmsg/update/{id}")
    public ResponseEntity<FarewellMessage> updateFareWellMessage(@RequestBody String updatedMsg, @PathVariable(value = "id")long id){
        FarewellMessage farewellMessage = fareWellMsgService.getFarewellMsgById(id);

        if(farewellMessage != null){
            fareWellMsgService.updateFarewellMsg(updatedMsg, id);
            String entity = "Farewell message updated";
            return new ResponseEntity(entity, HttpStatus.CREATED);
        }
        String entity = "Farewell message of the given id could not be found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/farewellmsg/delete/{id}")
    public ResponseEntity<FarewellMessage> deleteFareWellMessage(@PathVariable(value = "id") long id){
        FarewellMessage farewellMessage = fareWellMsgService.getFarewellMsgById(id);
        if(farewellMessage != null){
            fareWellMsgService.deleteFarewellMsg(id);
            return ResponseEntity.ok().build();
        }
        String entity = "Farewell message of the given id could not be found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }
}
