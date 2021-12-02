package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student extends Profile {
    @ManyToOne
    private GraduatingYear graduatingYear;
    @ManyToOne
    private GraduatingClass graduatingClass;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bio_id", referencedColumnName = "id")
    private Bio studentBio;

    public Student(String username, String profileName, String pwd, String role, GraduatingYear graduatingYear, GraduatingClass graduatingClass) {
        super(username, profileName, pwd, role);
        this.graduatingYear = graduatingYear;
        this.graduatingClass = graduatingClass;
    }

    public Student() {

    }

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

    public Bio getStudentBio() {
        return studentBio;
    }

    public void setStudentBio(Bio studentBio) {
        this.studentBio = studentBio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(graduatingYear, student.graduatingYear) && Objects.equals(graduatingClass, student.graduatingClass) && Objects.equals(studentBio, student.studentBio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), graduatingYear, graduatingClass, studentBio);
    }

    @Override
    public String toString() {
        return "Student{" +
                "graduatingYear=" + graduatingYear +
                ", graduatingClass=" + graduatingClass +
                ", studentBio=" + studentBio +
                '}';
    }
}
