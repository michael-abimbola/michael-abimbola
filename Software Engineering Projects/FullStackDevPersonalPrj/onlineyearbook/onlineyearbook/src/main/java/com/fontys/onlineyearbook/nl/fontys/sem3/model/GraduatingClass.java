package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "graduatingclass")
public class GraduatingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String className;

    public GraduatingClass(String className) {
        this.className = className;
    }

    public GraduatingClass() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraduatingClass)) return false;
        GraduatingClass that = (GraduatingClass) o;
        return id == that.id && Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className);
    }

    @Override
    public String toString() {
        return "GraduatingClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }
}
