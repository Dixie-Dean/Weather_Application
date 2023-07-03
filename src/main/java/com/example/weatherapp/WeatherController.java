package com.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherController {
    private static final String LINK = "https://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API = "&appid=b703ddc78950879d37132312332b1470";
    private static final String UNITS = "&units=metric";

    @FXML
    private TextField city;
    @FXML
    private Text temperature;
    @FXML
    private Text humidity;
    @FXML
    private Text feelsLike;
    @FXML
    private Text pressure;
    @FXML
    private Text errorMessage;

    @FXML
    private void setCity(ActionEvent event) throws ParseException {
        String userCity = city.getText().trim();
        setValues(userCity);
    }

    private void setValues(String city) throws ParseException {
        String data = getWeatherData(LINK + city + API + UNITS);
        JSONObject mainObj = (JSONObject) getJSON(data).get("main");

        temperature.setText("Temperature: " + getTemperature(mainObj));
        humidity.setText("Humidity: " + getHumidity(mainObj));
        feelsLike.setText("Feels Like: " + getFeelsLike(mainObj));
        pressure.setText("Pressure: " + getPressure(mainObj));
        errorMessage.setText(" ");
    }

    private static double getTemperature(JSONObject mainObj) {
        return (double) mainObj.get("temp");
    }

    private static long getHumidity(JSONObject mainObj) {
        return (long) mainObj.get("humidity");
    }

    private static double getFeelsLike(JSONObject mainObj) {
        return (double) mainObj.get("feels_like");
    }

    private static long getPressure(JSONObject mainObj) {
        return (long) mainObj.get("pressure");
    }

    private static JSONObject getJSON(String data) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(data);
    }

    private String getWeatherData(String userUrl) {
        StringBuilder data = new StringBuilder();

        try {
            URL url = new URL(userUrl);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String content;

            while ((content = reader.readLine()) != null) {
                data.append(content);
            }

        } catch (Exception e) {
            temperature.setText("Temperature: ");
            humidity.setText("Humidity: ");
            feelsLike.setText("Feels Like: ");
            pressure.setText("Pressure: ");
            errorMessage.setText("Error: city not found");
        }
        return data.toString();
    }
}