package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IImageDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Image;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IImageService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService implements IImageService {
    IImageDal dal;

    public ImageService(IImageDal dal){
        this.dal = dal;
    }
    @Override
    public void uploadImage(Image image) {
        dal.addImageToDB(image);
    }

    @Override
    public List<Image> getAllIMages() {
        return dal.getAllImagesFromDB();
    }


    @Override
    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompressBytes(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try{
            while(!inflater.finished()){
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch(DataFormatException e){
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @Override
    public List<Image> getAllImageByGradYear(GraduatingYear gradYear) {
        return dal.getAllImagesByGradYearFromDB(gradYear);
    }

    @Override
    public List<Image> getAllImageByGradYearAndCategory(GraduatingYear gradYear, String category) {
        return dal.getAllImagesByGradYearAndCategoryFromDB(gradYear, category);
    }

    @Override
    public List<Image> getAllImagesByGradClass(GraduatingClass graduatingClass, GraduatingYear graduatingYear) {
        return dal.getAllImagesByGradClassFromDB(graduatingClass, graduatingYear);
    }

    @Override
    public List<Image> getAllUnAvailableImages() {
        return dal.getAllUnAvailableImagesFromDB();
    }

    @Override
    public void makeImageAvailable(boolean available, long id) {
        dal.imageAvailable(available, id);
    }

    @Override
    public Image getImageByID(long id) {
        return dal.getImageByID(id);
    }
}
