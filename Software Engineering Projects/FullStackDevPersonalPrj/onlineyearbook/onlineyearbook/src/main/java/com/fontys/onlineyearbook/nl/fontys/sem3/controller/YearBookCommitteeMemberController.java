package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.YearBookCommitteeMemberDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IYearBookCommitteeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ybcm")
@CrossOrigin("*")
public class YearBookCommitteeMemberController {
    @Autowired
    private IYearBookCommitteeMemberService service;

    @GetMapping
    public ResponseEntity<List<YearBookCommitteeMember>> getAllYBCMs(){

        List<YearBookCommitteeMember> allYBCMs = null;
        allYBCMs = service.getAllYearBookCMs();
        if(allYBCMs != null){
            return ResponseEntity.ok().body(allYBCMs);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/country/
    public ResponseEntity<YearBookCommitteeMember> createYBCM(@RequestBody YearBookCommitteeMemberDTO dto) {
        YearBookCommitteeMember ybcm = new YearBookCommitteeMember(dto.getUsername(), dto.getProfileName(), dto.getPwd(), dto.getRole());
        try {
            if(service.addyearBookCM(ybcm)){
                String url = "admin" + "/" + ybcm.getUsername();
                URI uri = URI.create(url);
                return new ResponseEntity(uri, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String entity = "null fields";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }
}
