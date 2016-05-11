package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;


public class SearchDelete {

    private BorderPane pane;
    private ChoiceBox<String> chooseParameter;
    ChoiceBox<String> titleBox;
    private List<University> db = FXCollections.observableArrayList();

    private TextField fio;
    private TextField nameOfDepartment;
    private TextField title;
    private TextField faculty;

    private ObservableList<University> result;
    private MyTable resultTable;
    private MenuLine parentClass;
    private Stage thisStage;
    private VBox resBox;
    private Components components;
    private  BorderPane PgTbPane;



    public void start(final Stage primaryStage, final List<University> allData, final MenuLine parentClass)
    {
        final Label nullResult = new Label("Ничего не найдено!");
        this.db = allData;

        PgTbPane = new BorderPane();
        components = new Components();
        thisStage = primaryStage;
        this.parentClass = parentClass;
        pane = new BorderPane();
        Scene scene = new Scene(pane, 900, 450);
        thisStage.setScene(scene);
        thisStage.setTitle("Найти/Удалить");
        thisStage.show();

        Button find = new Button ("Найти");
        Button delete = new Button ("Удалить");
        HBox panel = new HBox();
        panel.setSpacing(30);

        VBox resultBox = new VBox();
        resBox = new VBox();

        ObservableList<String> options = FXCollections.observableArrayList
                (
                        "Поиск по факультету и названию кафедры",
                        "Поиск по фамилии преподавателя и наименованию кафедры",
                        "Поиск по ученому званию и наименованию кафедры"
                );
        chooseParameter = new ChoiceBox<String>(options);


        panel.getChildren().addAll(chooseParameter, find, delete);
        pane.setTop(panel);
        resultBox.setSpacing(7);


        HBox.setMargin(chooseParameter, new Insets(10, 0, 5, 10));
        HBox.setMargin(find, new Insets(10,0,0,0));
        HBox.setMargin(delete, new Insets(10, 0, 0, 0));

        PgTbPane.setTop(nullResult);
        PgTbPane.setCenter(resultTable);

        PgTbPane.setBottom(components);
        nullResult.setVisible(false);
        components.setVisible(true);///

        pane.setBottom(PgTbPane);
        nullResult.setFont(new Font("Verdana", 18));

        pane.setMargin(nullResult, new Insets(5, 0, 5, 10));



        chooseParameter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.equals("Поиск по факультету и названию кафедры"))
                {
                    firstFinding();
                }

                if (newValue.equals("Поиск по фамилии преподавателя и наименованию кафедры"))
                {
                    secondFinding();
                }

                if (newValue.equals("Поиск по ученому званию и наименованию кафедры"))
                {
                    thirdFinding();
                }

            }
        });

        find.setOnAction(event -> {

            if (chooseParameter.getValue().equals("Поиск по фамилии преподавателя и наименованию кафедры"))
            {
                String fio = SearchDelete.this.fio.getText();
                String nameOfDepartment = SearchDelete.this.nameOfDepartment.getText();
                SearchDelete.this.result = (ObservableList<University>) findingOne(fio, nameOfDepartment);
                if(findingOne(fio, nameOfDepartment).size() != 0)
                {
                    nullResult.setVisible(false);
                    adding(findingOne(fio, nameOfDepartment));
                }
                else
                {
                    nullResult.setVisible(true);
                    resultTable.setVisible(false);
                }
            }


            if (chooseParameter.getValue().equals("Поиск по ученому званию и наименованию кафедры"))
            {
                String title = SearchDelete.this.titleBox.getSelectionModel().getSelectedItem();
                String nameOfDepartment = SearchDelete.this.nameOfDepartment.getText();
                SearchDelete.this.result = (ObservableList<University>) findingTwo(title, nameOfDepartment);
                if(findingTwo(title, nameOfDepartment).size() != 0)
                {
                    nullResult.setVisible(false);
                    adding(findingTwo(title, nameOfDepartment));
                }
                else
                {
                    nullResult.setVisible(true);
                    resultTable.setVisible(false);
                }
            }

            if (chooseParameter.getValue().equals("Поиск по факультету и названию кафедры"))
            {
                String faculty = SearchDelete.this.faculty.getText();
                String nameOfDepartment = SearchDelete.this.nameOfDepartment.getText();
                SearchDelete.this.result = (ObservableList<University>) findingThree(faculty, nameOfDepartment);
                if(findingThree(faculty, nameOfDepartment).size() != 0)
                {
                    nullResult.setVisible(false);
                    adding(findingThree(faculty, nameOfDepartment));
                }
                else
                {
                    nullResult.setVisible(true);
                    resultTable.setVisible(false);
                }
            }


        });

        delete.setOnAction(event -> {
            if (SearchDelete.this.result.size() > 0)
            {
                JOptionPane.showMessageDialog
                        (null, "Удаление прошло успешно \n"
                                + SearchDelete.this.result.size()
                                + " запись удалена.");
                for(int i = 0; i < result.size(); i++)
                {
                    allData.remove(result.get(i));
                    pane.getChildren().remove(resultTable);
                }
                parentClass.parentClass.updatePage();
            }
            else
                JOptionPane.showMessageDialog
                        (null, "Deleting wasn't successful: \n"
                                + " no records was chosen.");

        });
    }

    public void firstFinding()
    {

        Label facultyLabel = new Label("Факультет");
        faculty = new TextField();
        Label nameOfDepartmentLabel = new Label("Название кафедры");
        nameOfDepartment = new TextField();

        VBox first = new VBox();
        VBox second = new VBox();
        faculty.setMinSize(300, 20);
        nameOfDepartment.setMinSize(300, 20);

        first.getChildren().addAll(facultyLabel, faculty);
        second.getChildren().addAll(nameOfDepartmentLabel, nameOfDepartment);
        HBox allInfo = new HBox();
        allInfo.setSpacing(30);
        allInfo.setMargin(first, new Insets(20, 50, 0, 100));
        allInfo.setMargin(second, new Insets(20, 0, 0, 0));
        allInfo.getChildren().addAll(first, second);
        this.pane.setCenter(allInfo);

    }

    public void secondFinding()
    {
        Label fioLabel = new Label("ФИО");
        fio = new TextField();
        Label nameOfDepartmentLabel = new Label("Название кафедры");
        nameOfDepartment = new TextField();

        VBox first = new VBox();
        VBox second = new VBox();
        fio.setMinSize(300, 20);
        nameOfDepartment.setMinSize(300, 20);

        first.getChildren().addAll(fioLabel, fio);
        second.getChildren().addAll(nameOfDepartmentLabel, nameOfDepartment);

        HBox allInfo = new HBox();
        allInfo.setSpacing(30);
        allInfo.setMargin(first, new Insets(20, 50, 0, 100));
        allInfo.setMargin(second, new Insets(20, 0, 0, 0));
        allInfo.getChildren().addAll(first, second);
        this.pane.setCenter(allInfo);
    }

    public void thirdFinding()
    {
        Label titleLabel = new Label("Ученое звание");
        ObservableList<String> titleList = FXCollections.observableArrayList
                (
                        "мл. науч. сотр.",
                        "науч. сотр.",
                        "ст. науч. сотр.",
                        "вед. науч. сотр.",
                        "гл. науч. сотр.",
                        "доц.",
                        "проф.",
                        "чл.-кор.",
                        "акад."
                );
        titleBox = new ChoiceBox<String>(titleList);

        Label nameOfDepartmentLabel = new Label("Название кафедры");
        nameOfDepartment = new TextField();


        VBox first = new VBox();
        VBox second = new VBox();
        VBox third = new VBox();
        titleBox.setMinSize(300, 20);
        nameOfDepartment.setMinSize(300, 20);

        first.getChildren().addAll(titleLabel, titleBox);
        second.getChildren().addAll(nameOfDepartmentLabel, nameOfDepartment);
        HBox allInfo = new HBox();
        allInfo.setSpacing(30);
        HBox.setMargin(first, new Insets(20, 50, 0, 100));
        allInfo.setMargin(second, new Insets(20, 0, 0, 0));
        allInfo.getChildren().addAll(first, second, third);
        this.pane.setCenter(allInfo);
    }


    public  List <University> findingOne(String fio, String nameOfDepartment)
    {
        List <University> allStudents = this.db;
        List <University> result = FXCollections.observableArrayList();
        for(int i = 0; i < allStudents.size(); i++)
        {
           University student = allStudents.get(i);
            if((student.getFio().equals(fio)) && (student.getNameOfDepartment().equals(nameOfDepartment)))
            {
                result.add(student);
            }
        }
        return result;
    }


    public  List <University> findingTwo(String title, String nameOfDepartment)
    {
        List <University> allStudents = this.db;
        List <University> result = FXCollections.observableArrayList();
        for(int i = 0; i < allStudents.size(); i++)
        {
            University student = allStudents.get(i);
            if((student.getTitle().equals(title)) && (student.getNameOfDepartment().equals(nameOfDepartment)))
            {
                result.add(student);
            }
        }
        return result;
    }

    public  List <University> findingThree(String faculty, String nameOfDepartment)
    {
        List <University> allStudents = this.db;
        List <University> result = FXCollections.observableArrayList();
        for(int i = 0; i < allStudents.size(); i++)
        {
            University student = allStudents.get(i);
            if((student.getFaculty().equals(faculty)) && (student.getNameOfDepartment().equals(nameOfDepartment)))
            {
                result.add(student);
            }
        }
        return result;
    }


    public void adding (List <University> result)
    {
        resultTable = new MyTable ();
        resultTable.setDataU(result);
        VBox table = new VBox();
        table.getChildren().add(resultTable);
        PgTbPane.setCenter(table);
        resultTable.setMaxHeight( 200.0 );
        components.setTable(resultTable);
        components.updatePages();
        components.setVisible(true);
    }
}
