package com.bsuir.ief.lab4.backend.sql;

import java.util.Objects;

public class Faculty {

    private int id;
    private String name;
    private String dean;
    private String address;
    private int specialitiesQuantity;
    private int studentsQuantity;
    private int teachersQuantity;

    public Faculty(int id, String name, String dean, int specialitiesQuantity, int studentsQuantity, int teachersQuantity, String address) {
        this.id = id;
        this.name = name;
        this.dean = dean;
        this.address = address;
        this.specialitiesQuantity = specialitiesQuantity;
        this.studentsQuantity = studentsQuantity;
        this.teachersQuantity = teachersQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSpecialitiesQuantity() {
        return specialitiesQuantity;
    }

    public void setSpecialitiesQuantity(int specialitiesQuantity) {
        this.specialitiesQuantity = specialitiesQuantity;
    }

    public int getStudentsQuantity() {
        return studentsQuantity;
    }

    public void setStudentsQuantity(int studentsQuantity) {
        this.studentsQuantity = studentsQuantity;
    }

    public int getTeachersQuantity() {
        return teachersQuantity;
    }

    public void setTeachersQuantity(int teachersQuantity) {
        this.teachersQuantity = teachersQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return specialitiesQuantity == faculty.specialitiesQuantity &&
                studentsQuantity == faculty.studentsQuantity &&
                teachersQuantity == faculty.teachersQuantity &&
                Objects.equals(name, faculty.name) &&
                Objects.equals(dean, faculty.dean) &&
                Objects.equals(address, faculty.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dean, address, specialitiesQuantity, studentsQuantity, teachersQuantity);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dean='" + dean + '\'' +
                ", address='" + address + '\'' +
                ", specialitiesQuantity=" + specialitiesQuantity +
                ", studentsQuantity=" + studentsQuantity +
                ", teachersQuantity=" + teachersQuantity +
                '}';
    }
}
