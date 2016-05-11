package sample;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

import java.util.List;

public class MyTable extends TableView {

    private List<University> usersData = FXCollections.observableArrayList();




    public MyTable()
    {

        this.setEditable(false);

        TableColumn facultyColumn = new TableColumn("Факультет");
        facultyColumn.setPrefWidth(100.0);
        TableColumn nameOfDepartmentColumn = new TableColumn("Название кафедры");
        nameOfDepartmentColumn.setPrefWidth(170.0);
        TableColumn fioColumn = new TableColumn("ФИО преподавателя");
        fioColumn.setPrefWidth(190.0);
        TableColumn titleColumn = new TableColumn("Ученое звание");
        titleColumn.setPrefWidth(220.0);
        TableColumn academicDegreeColumn = new TableColumn("Ученая степень");
        academicDegreeColumn.setPrefWidth(220.0);


        facultyColumn.setCellValueFactory(new PropertyValueFactory<University, String>("faculty"));
        facultyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        facultyColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<University, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<University, String> studentsStringCellEditEvent) {
                studentsStringCellEditEvent.getTableView().getItems().get(studentsStringCellEditEvent.getTablePosition().getRow()).setFaculty(studentsStringCellEditEvent.getNewValue());
            }
        });
        nameOfDepartmentColumn.setCellValueFactory(new PropertyValueFactory<University, String>("nameOfDepartment"));
        nameOfDepartmentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameOfDepartmentColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<University, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<University, String> studentsStringCellEditEvent) {
                studentsStringCellEditEvent.getTableView().getItems().get(studentsStringCellEditEvent.getTablePosition().getRow()).setNameOfDepartment(studentsStringCellEditEvent.getNewValue());
            }
        });

        fioColumn.setCellValueFactory(new PropertyValueFactory<University, String>("fio"));
        fioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fioColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<University, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<University, String> studentsStringCellEditEvent) {
                studentsStringCellEditEvent.getTableView().getItems().get(studentsStringCellEditEvent.getTablePosition().getRow()).setFio(studentsStringCellEditEvent.getNewValue());
            }
        });
        titleColumn.setCellValueFactory(new PropertyValueFactory<University, String>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<University, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<University, String> studentsStringCellEditEvent) {
                studentsStringCellEditEvent.getTableView().getItems().get(studentsStringCellEditEvent.getTablePosition().getRow()).setTitle(studentsStringCellEditEvent.getNewValue());
            }
        });
        academicDegreeColumn.setCellValueFactory(new PropertyValueFactory<University, String>("academicDegree"));
        academicDegreeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        academicDegreeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<University, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<University, String> studentsStringCellEditEvent) {
                studentsStringCellEditEvent.getTableView().getItems().get(studentsStringCellEditEvent.getTablePosition().getRow()).setAcademicDegree(studentsStringCellEditEvent.getNewValue());
            }
        });

        this.getColumns().addAll(facultyColumn, nameOfDepartmentColumn, fioColumn, titleColumn, academicDegreeColumn);

        this.setItems((ObservableList) usersData);

    }


    public List<University> getDataU()
    {
        return this.usersData;
    }
    public void setDataU(List <University> inU)
    {
        this.usersData = inU;
    }

}

