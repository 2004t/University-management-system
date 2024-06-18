package com.example.javagui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AddLabGUI {

    static ArrayList<Lab> labs = new ArrayList<>();
    static ArrayList<LabStaff> labStaff = new ArrayList<>();
    static boolean hasProjector;
    static ArrayList<Computer> computers = new ArrayList<>();


    public static void addLab(LabStaff incharge, ArrayList<LabStaff> labStaff, boolean hasProjector, ArrayList<Computer> computers) {
        Lab lab = new Lab(incharge, hasProjector, computers, labStaff);
        labs.add(lab);
        System.out.println("Lab added: " + lab.toString());
        serializeLabs(labs);
    }

    public static void addComputer(Computer computer) {
        computers.add(computer);
        System.out.println("Computer added: " + computer.getSystemDetails());
        serializeComputers(computers);
    }

    public static void addLabStaff(LabStaff LabStaff) {
        labStaff.add(LabStaff);
        System.out.println("Lab Staff added: " + labStaff.toString());

    }

    public static ArrayList<Lab> getLabs() {
        return labs;
    }

    public static void display() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Lab");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(100);
        gridPane.getColumnConstraints().add(col1);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(300);
        gridPane.getColumnConstraints().add(col2);



        Label inchargeNameLabel = new Label("Name:");
        TextField inchargeNameField = new TextField();
        gridPane.add(inchargeNameLabel, 0, 0);
        gridPane.add(inchargeNameField, 1, 0);
        Label inchargeGradeLabel = new Label("Grade:");
        TextField inchargeGradeField = new TextField();
        gridPane.add(inchargeGradeLabel, 0, 1);
        gridPane.add(inchargeGradeField, 1, 1);




        Label projectorLabel = new Label("Has Projector:");
        CheckBox projectorCheckBox = new CheckBox();
        gridPane.add(projectorLabel, 0, 2);
        gridPane.add(projectorCheckBox, 1, 2);


        Label labStaffLabel = new Label("Lab Staff:");
        TextArea labStaffTextArea = new TextArea();
        labStaffTextArea.setEditable(false);
        labStaffTextArea.setPrefRowCount(5);
        gridPane.add(labStaffLabel, 0, 4);
        gridPane.add(labStaffTextArea, 1, 4);



        Button addLabStaffButton = new Button("Add Lab Staff");
        addLabStaffButton.setOnAction(e -> {

            Dialog<LabStaff> dialog = new Dialog<>();
            dialog.setTitle("Add Lab Staff");
            dialog.setHeaderText("Enter lab staff details");


            ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);


            GridPane dialogGrid = new GridPane();
            dialogGrid.setHgap(10);
            dialogGrid.setVgap(10);
            dialogGrid.setPadding(new Insets(10));

            dialogGrid.getColumnConstraints().add(col1);
            dialogGrid.getColumnConstraints().add(col2);

            TextField nameField = new TextField();
            TextField gradeField = new TextField();

            dialogGrid.addRow(0, new Label("Name:"), nameField);
            dialogGrid.addRow(1, new Label("Grade:"), gradeField);

            dialog.getDialogPane().setContent(dialogGrid);


            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    String name = nameField.getText();
                    String grade = gradeField.getText();

                    LabStaff labStaff = new LabStaff(name, grade);
                    addLabStaff(labStaff);
                    labStaffTextArea.appendText(labStaff.getName() + " - " + labStaff.getGrade() + "\n");
                    return labStaff;
                }
                return null;
            });

            dialog.showAndWait();
        });

        gridPane.add(addLabStaffButton, 0, 4);



        Label computerLabel = new Label("Computers:");
        TextArea computerTextArea = new TextArea();
        computerTextArea.setEditable(false);
        computerTextArea.setPrefRowCount(5);
        gridPane.add(computerLabel, 0, 3);
        gridPane.add(computerTextArea, 1, 3);




        Button addComputerButton = new Button("Add Computer");
        addComputerButton.setOnAction(e -> {

            Dialog<Computer> dialog = new Dialog<>();
            dialog.setTitle("Add Computer");
            dialog.setHeaderText("Enter computer details");


            ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);


            GridPane dialogGrid = new GridPane();
            dialogGrid.setHgap(10);
            dialogGrid.setVgap(10);
            dialogGrid.setPadding(new Insets(10));

            dialogGrid.getColumnConstraints().add(col1);
            dialogGrid.getColumnConstraints().add(col2);

            TextField systemNameField = new TextField();
            TextField systemSpeedField = new TextField();
            TextField ramSizeField = new TextField();
            TextField hardDiskSizeField = new TextField();
            TextField icdMakeModelField = new TextField();

            dialogGrid.addRow(0, new Label("System Name:"), systemNameField);
            dialogGrid.addRow(1, new Label("System Speed:"), systemSpeedField);
            dialogGrid.addRow(2, new Label("RAM Size:"), ramSizeField);
            dialogGrid.addRow(3, new Label("Hard Disk Size:"), hardDiskSizeField);
            dialogGrid.addRow(4, new Label("ICD Make/Model:"), icdMakeModelField);

            dialog.getDialogPane().setContent(dialogGrid);


            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    String systemName = systemNameField.getText();
                    String systemSpeed = systemSpeedField.getText();
                    String ramSize = ramSizeField.getText();
                    String hardDiskSize = hardDiskSizeField.getText();
                    String icdMakeModel = icdMakeModelField.getText();

                    Computer computer = new Computer(systemName, systemSpeed, ramSize, hardDiskSize, icdMakeModel);
                    addComputer(computer);
                    computerTextArea.appendText(computer.getSystemName() + "\n");
                    return computer;
                }
                return null;
            });

            dialog.showAndWait();
        });


        Button addLabButton = new Button("Add Lab");
        addLabButton.setOnAction(e -> {
            String inchargeName = inchargeNameField.getText();
            String inchargeGrade = inchargeGradeField.getText();
            LabStaff incharge = new LabStaff(inchargeName, inchargeGrade);

            boolean hasProjector = projectorCheckBox.isSelected();
            addLab(incharge, labStaff , hasProjector, computers);
            computers.clear();
            computerTextArea.clear();
        });

        gridPane.add(addComputerButton, 0, 3);
        gridPane.add(addLabButton, 1, 5);

        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void serializeLabs(ArrayList<Lab> labs) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Labs.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(labs);
            out.close();
            fileOut.close();
            System.out.println("Labs serialized and saved to Labs.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeComputers(ArrayList<Computer> computers) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Computers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(computers);
            out.close();
            fileOut.close();
            System.out.println("Computers serialized and saved to Computers.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
