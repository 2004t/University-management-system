package com.example.javagui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors


public class University implements java.io.Serializable{
    String name;
    ArrayList<Campus> campuses;


    University(String name, ArrayList<Campus> campuses) {
        this.name = name;
        this.campuses = campuses;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(ArrayList<Campus> campuses) {
        this.campuses = campuses;
    }


    public void addCampus(Campus campus) {
        campuses.add(campus);
    }

    public void removeCampus(Campus campus) {
        campuses.remove(campus);
    }

    public ArrayList<Campus> getCampus() {
        return campuses;
    }

    public void saveData(){
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", campuses=" + campuses +
                '}';
    }
}
