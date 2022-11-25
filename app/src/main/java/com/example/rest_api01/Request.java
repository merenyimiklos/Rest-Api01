package com.example.rest_api01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    public static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    public static String getData() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200){
            throw new RuntimeException("Hiba történt : hibakód : " + responseCode);
        }
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        StringBuilder builder = new StringBuilder();
        String output;
        while ((output = bufferedReader.readLine()) != null){
            builder.append(output);
            builder.append(System.lineSeparator());
        }
        connection.disconnect();
        return builder.toString().trim();
    }

    public static String postData(String kuldendoAdat) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(kuldendoAdat.getBytes());
        outputStream.flush();

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_CREATED){
            throw new RuntimeException("Hiba történt : hibakód : " + responseCode);
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        StringBuilder builder = new StringBuilder();
        String output;
        while ((output = bufferedReader.readLine()) != null){
            builder.append(output);
            builder.append(System.lineSeparator());
        }
        connection.disconnect();
        return builder.toString().trim();
    }
}
