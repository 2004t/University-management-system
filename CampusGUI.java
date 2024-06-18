package com.example.javagui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CampusGUI{
    private Director director;
    private ArrayList<Department> departments = new ArrayList<>();

    private Campus campus;


    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }


    public void display() {
        Stage primaryStage = new Stage();

        primaryStage.setTitle("Campus GUI");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        gridPane.getColumnConstraints().addAll(col1, col2);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);


        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();
        gridPane.add(addressLabel, 0, 1);
        gridPane.add(addressField, 1, 1);


        Label directorNameLabel = new Label("Director Name:");
        TextField directorNameField = new TextField();
        gridPane.add(directorNameLabel, 0, 2);
        gridPane.add(directorNameField, 1, 2);

        Label directorGradeLabel = new Label("Director Grade:");
        TextField directorGradeField = new TextField();
        gridPane.add(directorGradeLabel, 0, 3);
        gridPane.add(directorGradeField, 1, 3);


        Button addDepartmentButton = new Button("Add Department");
        addDepartmentButton.setOnAction(e -> {

            DepartmentGUI addDepartmentGUI = new DepartmentGUI();
            addDepartmentGUI.display();
            Department department = new Department(addDepartmentGUI.getHod(), addDepartmentGUI.getLabs());

            if (department != null) {
                departments.add(department);
            }
        });

        gridPane.add(addDepartmentButton, 0, 4, 2, 1);


        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {

            String campusName = nameField.getText();
            String campusAddress = addressField.getText();
            String directorName = directorNameField.getText();
            String directorGrade = directorGradeField.getText();

            campus = new Campus(campusName, campusAddress, new Director(directorName, directorGrade), departments);


            System.out.println("Campus: " + campus);



        });

        gridPane.add(submitButton, 1, 4);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(400, 200);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

