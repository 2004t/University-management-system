package com.example.javagui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginGUI extends Application {
    private Employee loggedInEmployee;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");


        GridPane loginGridPane = createLoginGridPane();

        Scene loginScene = new Scene(loginGridPane, 300, 200);


        primaryStage.setScene(loginScene);
        primaryStage.show();


        Button loginButton = (Button) loginGridPane.lookup("#loginButton");
        loginButton.setOnAction(e -> {
            String email = ((TextField) loginGridPane.lookup("#emailField")).getText();
            String password = ((PasswordField) loginGridPane.lookup("#passwordField")).getText();


            boolean authenticated = authenticate(email, password);

            if (authenticated) {
                loggedInEmployee = new Employee(email, "", email, password);
                System.out.println("Logged in as: " + loggedInEmployee.getName());

                UniversityGUI universityGUI = new UniversityGUI();
                try {
                    universityGUI.display(primaryStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                System.out.println("Invalid email or password. Please try again.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Credentials");
            }
        });
    }

    private GridPane createLoginGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setId("emailField");
        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);


        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setId("passwordField");
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);


        Button loginButton = new Button("Login");
        loginButton.setId("loginButton");
        gridPane.add(loginButton, 1, 2);

        return gridPane;
    }

    private boolean authenticate(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("Login.txt"))) {
            String storedEmail = br.readLine();
            String storedPassword = br.readLine();



            if (email.equals(storedEmail) && password.equals(storedPassword)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
