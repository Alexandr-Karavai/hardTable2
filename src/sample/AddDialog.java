package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;


public class AddDialog {

    public void start(final Stage primaryStage, final List<University> allDataU, final MenuLine parentClass) {

        Pane pane = new Pane();
        //* BorderPane pane = new BorderPane();
        primaryStage.setTitle("Добавить запись");
        Scene scene;

        scene = new Scene(pane, 640, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
        Button add = new Button("Добавить");
        add.setMinSize(50, 20);
        add.setLayoutX(150);
        add.setLayoutY(400);
        Label facultyLabel = new Label("Факультет");
        facultyLabel.setLayoutX(100);
        facultyLabel.setLayoutY(20);
        Label nameOfDepartmentLabel = new Label("Кафедра");
        nameOfDepartmentLabel.setLayoutX(100);
        nameOfDepartmentLabel.setLayoutY(100);
        Label fioLabel = new Label("ФИО");
        fioLabel.setLayoutX(100);
        fioLabel.setLayoutY(180);
        Label titleLabel = new Label("Ученое звание");
        titleLabel.setLayoutX(100);
        titleLabel.setLayoutY(260);
        Label academicLabel = new Label("Ученая степень");
        academicLabel.setLayoutX(100);
        academicLabel.setLayoutY(340);


        final TextField facultyText = new TextField();
        facultyText.setLayoutX(300);
        facultyText.setLayoutY(20);
        final TextField nameOfDepartmentText = new TextField();
        nameOfDepartmentText.setLayoutX(300);
        nameOfDepartmentText.setLayoutY(100);
        final TextField fioText = new TextField();
        fioText.setLayoutX(300);
        fioText.setLayoutY(180);

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
        ChoiceBox<String> titleBox = new ChoiceBox<String>(titleList);
        titleBox.setLayoutX(300);
        titleBox.setLayoutY(260);
        titleBox.setPrefSize(185,30);

        ObservableList<String> academicList = FXCollections.observableArrayList
                (
                        "кандидат наук",
                        "доктор наук"
                );
        ChoiceBox<String> academicBox = new ChoiceBox<String>(academicList);

        academicBox.setLayoutX(300);
        academicBox.setLayoutY(340);
        academicBox.setPrefSize(185,30);


        pane.getChildren().addAll(fioLabel, fioText, facultyText,
                facultyLabel, nameOfDepartmentLabel, nameOfDepartmentText, academicLabel, academicBox,
                titleLabel, titleBox, add);

        add.setOnAction(event -> {
            Lecturer lecturer = new Lecturer(fioText.getText(),
                    titleBox.getSelectionModel().getSelectedItem(), academicBox.getSelectionModel().getSelectedItem());
            allDataU.add(new University(facultyText.getText(), nameOfDepartmentText.getText(),lecturer));
            parentClass.parentClass.updatePage();
            primaryStage.close();
        });

    }


}
