package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Image;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingClassService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingYearService;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin("*")
public class ImageController {
    @Autowired
    private IImageService service;

    @Autowired
    private IGraduatingYearService gradYearService;

    @Autowired
    private IGraduatingClassService gradClassService;

//    @PostMapping()
//    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile multipartFile)throws IOException {
//        System.out.println("original image byte size - " + multipartFile.getBytes().length);
//
//                Image img = new Image(multipartFile.getOriginalFilename(), multipartFile.getContentType(),
//                        service.compressBytes(multipartFile.getBytes()), LocalDate.now());
//
//                service.uploadImage(img);
//                return ResponseEntity.ok("200");
//    }

    @PostMapping("/upload/{gradYearId}/{gradClassId}/{category}")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile multipartFile,
                                        @PathVariable(value = "gradYearId") long gradYearId,
                                        @PathVariable(value = "gradClassId") long gradClassId,
                                        @PathVariable(value = "category") String category)throws IOException {
        System.out.println("original image byte size - " + multipartFile.getBytes().length);

        GraduatingYear gradYear = gradYearService.getGraduatingYearById(gradYearId);
        GraduatingClass gradClass = gradClassService.getGraduatingClassById(gradClassId);

        if(gradYear != null){
            if(gradClass != null){
                Image img = new Image(multipartFile.getOriginalFilename(), multipartFile.getContentType(),
                        service.compressBytes(multipartFile.getBytes()), LocalDate.now(), gradYear, gradClass, category);

                service.uploadImage(img);
                return ResponseEntity.ok("200");
            }
            else{
                String entity = "Graduation class could not be found";
                return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
            }
        }
        String entity = "Graduation year could not be found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity<String> makeImageAvailable(@RequestParam(value = "imageid") long imageId){
        Image image = service.getImageByID(imageId);
        if(image != null){
            service.makeImageAvailable(true, imageId);
            return ResponseEntity.ok("200");
        }
        String entity = "Image could not be found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }

//    @GetMapping("getAll")
//    public List<Image> getImage(){
//        List<Image>temp = new ArrayList<>();
//        for (Image item:service.getAllIMages()) {
//            Image img = new Image(item.getName(), item.getType(), service.decompressBytes(item.getContent()));
//            temp.add(img);
//        }
//        return temp;
//    }

    @GetMapping("/getAll/{gradYearId}")
    public List<Image> getImageByGradYear(@PathVariable(value = "gradYearId") long gradYearId){
        //i need a grad id to get he grad year
        GraduatingYear gradYear = gradYearService.getGraduatingYearById(gradYearId);

        //i need the gradyear to be added in the getallimageByGradYear
        List<Image>temp = new ArrayList<>();
        for (Image item:service.getAllImageByGradYear(gradYear)) {
            Image img = new Image(item.getName(), item.getType(), service.decompressBytes(item.getContent()));
            temp.add(img);
        }
        return temp;
    }

    @GetMapping("/getAll/{gradYearId}/{category}")
    public List<Image> getImageByGradYear(@PathVariable(value = "gradYearId") long gradYearId, @PathVariable(value = "category") String category){
        //i need a grad id to get he grad year
        GraduatingYear gradYear = gradYearService.getGraduatingYearById(gradYearId);

        //i need the gradyear to be added in the getallimageByGradYear
        if(gradYear != null){
            List<Image>temp = new ArrayList<>();
            for (Image item:service.getAllImageByGradYearAndCategory(gradYear, category)) {
                Image img = new Image(item.getName(), item.getType(), service.decompressBytes(item.getContent()));
                temp.add(img);
            }
            return temp;
        }
        return null;
    }

    @GetMapping("/getAll/gradClass/{gradClassId}/{gradYearId}")
    public List<Image> getImageByGradClass(@PathVariable(value = "gradClassId") long gradClassId,
                                           @PathVariable(value = "gradYearId") long gradYearId){
        GraduatingClass gradClass = gradClassService.getGraduatingClassById(gradClassId);
        GraduatingYear gradYear = gradYearService.getGraduatingYearById(gradYearId);

        List<Image>temp = new ArrayList<>();
        for (Image item:service.getAllImagesByGradClass(gradClass, gradYear)) {
            Image img = new Image(item.getName(), item.getType(), service.decompressBytes(item.getContent()));
            temp.add(img);
        }
        return temp;
    }
}
