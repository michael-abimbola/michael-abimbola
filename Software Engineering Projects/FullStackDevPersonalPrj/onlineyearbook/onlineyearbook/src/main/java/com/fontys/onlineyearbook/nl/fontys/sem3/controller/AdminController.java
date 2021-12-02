package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.AdminDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Image;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IAdminService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private IAdminService service;

    @Autowired
    private IImageService imageService;


    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins(){

        List<Admin> allAdmins = null;
        allAdmins = service.getAllAdmin();
        if(allAdmins != null){
            return ResponseEntity.ok().body(allAdmins);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/country/
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminDTO dto) {
        try {
            Admin admin = new Admin(dto.getUsername(), dto.getProfileName(), dto.getPwd(), dto.getRole());
            if(service.addAdmin(admin)){
                String url = "admin" + "/" + admin.getUsername();
                URI uri = URI.create(url);
                return new ResponseEntity(uri, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String entity = "null fields";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }


    @GetMapping("/image/getAll/unavailable")
    public List<Image> getAllUnavailableImages(){
        List<Image>temp = new ArrayList<>();
        for (Image item:imageService.getAllUnAvailableImages()) {
            Image img = new Image(item.getName(), item.getType(), imageService.decompressBytes(item.getContent()));
                    img.setId(item.getId());
            temp.add(img);
        }
        return temp;
    }
}
