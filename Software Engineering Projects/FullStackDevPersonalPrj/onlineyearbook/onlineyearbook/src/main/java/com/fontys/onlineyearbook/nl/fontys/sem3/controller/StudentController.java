package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.StudentDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.exceptions.NullFieldsException;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.*;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IBioService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IFareWellMsgService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingYearService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private IStudentService service;

    @Autowired
    private IBioService bioService;

    @Autowired
    private IGraduatingYearService graduatingYearService;

    @Autowired
    private IFareWellMsgService fareWellMsgService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student> allStudents = null;
        allStudents = service.getAllStudents();
        if(allStudents != null){
            return ResponseEntity.ok().body(allStudents);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/country/
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO dto) {
        Student student = new Student(dto.getUsername(), dto.getProfileName(), dto.getPwd(), dto.getRole(), dto.getGraduatingYear(), dto.getGraduatingClass());
        try {
            if(service.addStudent(student)){
                String url = "student" + "/" + student.getUsername();
                URI uri = URI.create(url);
                return new ResponseEntity(uri, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String entity = "null fields";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

//    @GetMapping("/search/{username}")
//    public ResponseEntity<String> getStudentID(@PathVariable(value = "username") String username){
//        Student student =  service.getStudentID(username);
//        if(student != null){
//            return ResponseEntity.ok().body(student.getRole());
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PutMapping("/studentbio/{username}")
    public ResponseEntity<Bio> addStudentBio(@PathVariable(value = "username") String username, @RequestBody Bio studentBio)throws NullFieldsException{
        //this is to get the student from the given username
        Student student = service.getStudentID(username);
        //we check if we got a person
        if(student != null){
            if(student.getStudentBio() == null){
                //if we did get a student
                //create a new bio belonging to the student
                Bio bio = new Bio(studentBio.getFullName(), studentBio.getDateOfBirth(), studentBio.getNickName(), studentBio.getSchoolStartingYear(), studentBio.getRoleModel(),
                        studentBio.getStateOfOrigin(), studentBio.getHobbies(), studentBio.getFavorableQuote(), studentBio.getMemorableDay(), studentBio.getFarewellMsg());

                //add the bio to our bio table in database
                bioService.addStudentBio(bio);

                //we get the full name of the student given in the bio
                //search for his bio after it it added
                Bio addedBio = bioService.getBioIDByFullName(bio.getFullName());

                //we then relate that bio to the student by updating the student info
                service.updateStudentBio(student.getId(), addedBio);
                String entity = "bio of student " +  student.getUsername() + " created";
                return new ResponseEntity(entity, HttpStatus.CREATED);
            }
            else{
                String entity = "student already has a bio";
                return new ResponseEntity(entity, HttpStatus.CONFLICT);
            }
        }
        String entity = "student username was null";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll/studentBio/{gradYearId}")
    public ResponseEntity<List<Bio>> getImageByGradYear(@PathVariable(value = "gradYearId") long gradYearId){
        //i need a grad id to get he grad year
        GraduatingYear gradYear = graduatingYearService.getGraduatingYearById(gradYearId);
        if(gradYear != null){
            //i need the gradyear to be added in the getallimageByGradYear
            List<Bio>temp = new ArrayList<>();
            for (Bio item:service.getAllBioByGradyear(gradYear)) {
                temp.add(item);
            }
            return ResponseEntity.ok().body(temp);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll/farewellMessages/{username}")
    public ResponseEntity<List<FarewellMessage>> getFarewellMessagesByGradClass(@PathVariable(value = "username") String usr){
        Student student = service.getStudentID(usr);

        if(student != null){
            List<FarewellMessage>temp = new ArrayList<>();
            for (FarewellMessage item:fareWellMsgService.getFarewellMessageByGradClass(student.getGraduatingClass())) {
                temp.add(item);
            }
            return ResponseEntity.ok().body(temp);
        }
        return ResponseEntity.notFound().build();
    }
}
