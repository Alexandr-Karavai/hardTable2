package sample;

import javafx.beans.property.StringProperty;


public class University  {

    private String faculty;
    private  String nameOfDepartment;
    private Lecturer lecturer;

    public University (String faculty, String nameOfDepartment, Lecturer lecturer) {
        this.faculty = faculty;
        this.nameOfDepartment = nameOfDepartment;
        this.lecturer = lecturer;

    }

    public String getFaculty() {
        return faculty;
    }
    public String getNameOfDepartment() {
        return nameOfDepartment;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setNameOfDepartment(String nameOfDepartment) {
        this.nameOfDepartment = nameOfDepartment;
    }

    public Lecturer getLecturer() {return lecturer;}

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getFio() {
        return lecturer.getFio();
    }
    public String getTitle() {
        return lecturer.getTitle();
    }

    public void setFio(String fio) {
        lecturer.setFio(fio);
    }

    public void setTitle(String title) {
        lecturer.setTitle(title);

    }

    public String getAcademicDegree() {
        return lecturer.getAcademicDegree();
    }

    public void setAcademicDegree(String academicDegree) {
        lecturer.setAcademicDegree(academicDegree);

    }


}
