package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "graduatingyear")
public class GraduatingYear {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int graduatingYear;

    public GraduatingYear(int graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public GraduatingYear() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(int graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraduatingYear)) return false;
        GraduatingYear that = (GraduatingYear) o;
        return graduatingYear == that.graduatingYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(graduatingYear);
    }

    @Override
    public String toString() {
        return "GraduatingYear{" +
                "graduatingYear=" + graduatingYear +
                '}';
    }
}
