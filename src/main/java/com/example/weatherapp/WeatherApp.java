package com.example.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WeatherApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApp.class.getResource("weather.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}