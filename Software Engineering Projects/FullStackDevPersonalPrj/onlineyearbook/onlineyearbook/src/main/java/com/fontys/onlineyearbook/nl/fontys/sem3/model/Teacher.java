package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teacher")
public class Teacher extends Profile {
    @ManyToOne
    private GraduatingClass graduatingClass;


    public Teacher(String username, String profileName, String pwd, String role, GraduatingClass graduatingClass) {
        super(username, profileName, pwd, role);
        this.graduatingClass = graduatingClass;
    }

    public Teacher(){

    }

    public GraduatingClass getGraduatingClass() {
        return graduatingClass;
    }

    public void setGraduatingClass(GraduatingClass graduatingClass) {
        this.graduatingClass = graduatingClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(graduatingClass, teacher.graduatingClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), graduatingClass);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "graduatingClass=" + graduatingClass +
                '}';
    }
}
