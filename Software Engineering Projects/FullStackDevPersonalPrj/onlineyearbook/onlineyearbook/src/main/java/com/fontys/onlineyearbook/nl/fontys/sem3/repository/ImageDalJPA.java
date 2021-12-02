package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IImageDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageDalJPA implements IImageDal {

    @Autowired
    IImageRepository repo;
    @Override
    public void addImageToDB(Image image) {
        repo.save(image);
    }

    @Override
    public List<Image> getAllImagesFromDB() {
        return repo.findAll();
    }

    @Override
    public List<Image> getAllImagesByGradYearFromDB(GraduatingYear gradYear) {
        List<Image> images = (List<Image>) repo.getAllImagesByGradYear(gradYear);
        return images;
    }

    @Override
    public List<Image> getAllImagesByGradYearAndCategoryFromDB(GraduatingYear gradYear, String category) {
        List<Image> images = (List<Image>) repo.getAllImagesByGradYearAndCategory(gradYear, category);
        return images;
    }

    @Override
    public List<Image> getAllImagesByGradClassFromDB(GraduatingClass gradClass, GraduatingYear graduatingYear) {
        List<Image> images = (List<Image>)repo.getAllImagesByGradClass(gradClass, graduatingYear);
        return images;
    }

    @Override
    public List<Image> getAllUnAvailableImagesFromDB() {
        List<Image> images = (List<Image>) repo.getAllUnAvailableImages();
        return images;
    }

    @Override
    public void imageAvailable(boolean available, long id) {
        repo.imageAvailable(available, id);
    }

    @Override
    public Image getImageByID(long id) {
        return repo.getImageById(id);
    }
}
