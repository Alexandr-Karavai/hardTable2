package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage thisStage;
    private MenuLine menuLine;
    private Components components;



    @Override
    public void start(Stage primaryStage) throws Exception{

        thisStage = primaryStage;
        primaryStage.getIcons().add(
                new Image("file:src/sample/address_book_32.png"));
        primaryStage.setTitle("Лабораторная работа №2");
        final BorderPane root = new BorderPane();
        menuLine = new MenuLine(root, Main.this);
        Scene scene = new Scene(root, 900, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
        components = new Components();
        components.setVisible(false);
        root.setBottom(components);
        root.setTop(menuLine);

    }

    public void creatingLoadingTable() {
        components.setTable(menuLine.myTable);
        components.setVisible(true);
    }

    public void updatePage() {
        components.updatePages();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
