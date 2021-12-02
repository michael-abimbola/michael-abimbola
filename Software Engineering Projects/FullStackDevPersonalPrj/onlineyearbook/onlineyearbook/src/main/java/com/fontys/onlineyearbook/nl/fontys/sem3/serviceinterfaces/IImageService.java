package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Image;

import java.util.List;

public interface IImageService {
    void uploadImage(Image image);
    List<Image> getAllIMages();
    byte[] compressBytes(byte[] data);
    byte[] decompressBytes(byte[] data);
    List<Image> getAllImageByGradYear(GraduatingYear gradYear);
    List<Image> getAllImageByGradYearAndCategory(GraduatingYear gradYear, String category);
    List<Image> getAllImagesByGradClass(GraduatingClass graduatingClass, GraduatingYear graduatingYear);
    List<Image> getAllUnAvailableImages();
    void makeImageAvailable(boolean available, long id);
    Image getImageByID(long id);
}
