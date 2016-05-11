
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.*;

public class Components extends HBox
{
    private ObservableList<University> allDataU;
    private ObservableList<University> pageDataU  = FXCollections.observableArrayList();

    Label textPage = new Label("Номер страницы:");
    Label currentAllPages = new Label ("0/0");
    Label allRecords = new Label ("0");
    Label numberRecords = new Label ("Число записей в таблице:");

    Button firstPage = new Button("Первая страница");
    Button lastPage = new Button("Последняя страница");

    Button nextPage = new Button("Следующая страница");
    Button previousPage = new Button("Предыдущая страница");

    Button setRecords = new Button("Установить число записей на странице");
    TextField textNumberRecords = new TextField ("10");
    HBox setBoxRecords = new HBox();

    HBox lastFirst = new HBox();
    HBox nlPage = new HBox();
    HBox page = new HBox();
    HBox recordsBox = new HBox();
    HBox pageComponents = new HBox();
    HBox recordsComponents = new HBox();

    VBox allComponents = new VBox();

    MyTable myTable;

    int allCountRecords;
    int countRecordsPerPage = 10;
    int allCountPage;
    int currentPage;

    Components() {

        nlPage.setSpacing(5);
        nlPage.getChildren().addAll(nextPage, previousPage);

        lastFirst.setSpacing(5);
        lastFirst.getChildren().addAll(firstPage, lastPage);

        page.setAlignment(Pos.CENTER);
        page.setSpacing(5);
        page.getChildren().addAll(textPage, currentAllPages);
        currentAllPages.setFont(new Font(14));

        pageComponents.setSpacing(20);
        pageComponents.getChildren().addAll( page , nlPage, lastFirst);

        recordsBox.setAlignment(Pos.CENTER);
        recordsBox.setSpacing(5);
        recordsBox.getChildren().addAll(numberRecords, allRecords);

        setBoxRecords.setAlignment(Pos.CENTER);
        setBoxRecords.setSpacing(5);
        textNumberRecords.setMaxSize(40,20);
        setBoxRecords.getChildren().addAll(textNumberRecords, setRecords);

        recordsComponents.setSpacing(70);
        recordsComponents.getChildren().addAll(recordsBox, setBoxRecords);

        allComponents.getChildren().addAll(pageComponents,recordsComponents);
        this.getChildren().addAll(allComponents);
        allComponents.setSpacing(10);
        this.setMargin(allComponents, new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);



        nextPage.setOnAction(event -> showPage(currentPage + 1));

        firstPage.setOnAction(event -> showPage(1));

        lastPage.setOnAction(event -> showPage(allCountPage));

        previousPage.setOnAction(event -> showPage(currentPage - 1));

        setRecords.setOnAction(event -> {
            try {
                countRecordsPerPage = Integer.parseInt(textNumberRecords.getText());
                allCountPage = allCountRecords / countRecordsPerPage;
                if (((double)allCountRecords / countRecordsPerPage) > allCountPage) allCountPage++;
                showPage(1);
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Введите число записей на странице!");
            }
        });
    }

    public void setTable(MyTable table)
    {
        myTable = table;
        allDataU = (ObservableList<University>) table.getDataU();
        allCountRecords = allDataU.size();
        allCountPage = allCountRecords / countRecordsPerPage;
        if (((double)allCountRecords / countRecordsPerPage) > allCountPage) allCountPage++;
        allRecords.setText( "" + allCountRecords);
        showPage(1);
    }

    public void updatePages()
    {
        allCountRecords = allDataU.size();
        allRecords.setText( "" + allCountRecords);
        allCountPage = allCountRecords / countRecordsPerPage;
        if (((double)allCountRecords / countRecordsPerPage) > allCountPage) allCountPage++;
        if (currentPage > allCountPage)
            showPage(1);
        else
            showPage(currentPage);

    }

    public void showPage(int pageNumber)
    {
        currentPage = pageNumber;
        int startI = (pageNumber - 1) * countRecordsPerPage;
        int endI = pageNumber * countRecordsPerPage;
        pageDataU.clear();
        for (int i = startI; i < endI; i++) {
            if (i < allCountRecords)
              pageDataU.add(allDataU.get(i));
        }
             myTable.setItems(pageDataU);
        currentAllPages.setText(pageNumber+"/"+allCountPage);
    }
}