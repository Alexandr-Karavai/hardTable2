package sample;

import javafx.beans.property.StringProperty;

public class Lecturer {

    private String fio;

    private  String title;
    private  String academicDegree;

    public Lecturer(String fio, String title, String academicDegree) {
        this.fio = fio;
        this.title = title;
        this.academicDegree = academicDegree;

    }

    public String getFio() {
        return fio;
    }
    public String getTitle() {
        return title;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

}
