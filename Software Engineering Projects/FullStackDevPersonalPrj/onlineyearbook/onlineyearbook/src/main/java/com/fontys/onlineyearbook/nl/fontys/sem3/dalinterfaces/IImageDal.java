package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Image;

import java.util.List;

public interface IImageDal {
    void addImageToDB(Image image);
    List<Image>getAllImagesFromDB();
    List<Image>getAllImagesByGradYearFromDB(GraduatingYear gradYear);
    List<Image>getAllImagesByGradYearAndCategoryFromDB(GraduatingYear gradYear, String category);
    List<Image>getAllImagesByGradClassFromDB(GraduatingClass gradClass, GraduatingYear graduatingYear);
    List<Image>getAllUnAvailableImagesFromDB();
    void imageAvailable(boolean available, long id);
    Image getImageByID(long id);
}
