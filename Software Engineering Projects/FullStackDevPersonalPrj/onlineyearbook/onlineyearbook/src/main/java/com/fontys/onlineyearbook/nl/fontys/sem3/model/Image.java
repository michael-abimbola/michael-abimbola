package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "image")
@EqualsAndHashCode(callSuper = false)
@ToString
public class Image {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", length = 1000000000)
    private byte[] content;

    @Column(name = "uploadDate")
    private LocalDate uploadDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GraduatingYear graduatingYear;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GraduatingClass graduatingClass;

    @Column(name = "category")
    private String category;

    @Column(name = "available")
    private boolean available;


    //this constructor is for images that need a graduatingyear, gradautingclass and category
    public Image(String name, String type, byte[] content, LocalDate uploadDate,
                 GraduatingYear graduatingYear, GraduatingClass graduatingClass, String category) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.uploadDate = uploadDate;
        this.graduatingYear = graduatingYear;
        this.graduatingClass = graduatingClass;
        this.category = category;
        this.available = false;
    }

    public Image(String name, String type, byte[] content, LocalDate uploadDate,
                 GraduatingYear graduatingYear, String category) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.uploadDate = uploadDate;
        this.graduatingYear = graduatingYear;
        this.category = category;
        this.available = false;
    }

    public Image(String name, String type, byte[] content, LocalDate uploadDate) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.uploadDate = uploadDate;
        this.available = false;
    }

    public Image(String name, String type, byte[] content) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.available = false;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setUploadDate(LocalDate date){
        this.uploadDate = date;
    }

    public LocalDate getUploadDate() { return uploadDate; }

    public GraduatingYear getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(GraduatingYear graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public GraduatingClass getGraduatingClass() {
        return graduatingClass;
    }

    public void setGraduatingClass(GraduatingClass graduatingClass) {
        this.graduatingClass = graduatingClass;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
