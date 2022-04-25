package com.diagnoseme.DiagnoseMe.util;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Predictor {
    private URL url ;
    private HttpURLConnection connection;

    public Predictor(){
        try {
            url = new URL("http://localhost:5000/predict");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String predict(String modelName, int[] data)  {
        String jsonObject = convertToJson(modelName,data);
        sendDataThroughRequest(jsonObject);
        String response = readResponseFromRequest();
        if(response == null)
            return "Something wrong";
        else
            return response;
    }

    private String convertToJson(String modelName,int[] data){
        StringBuilder jsonObject = new StringBuilder("{");
        jsonObject.append("\"modelName\":");
        jsonObject.append("\"").append(modelName).append("\",");
        jsonObject.append("\"data\":[");
        for(int i=0;i<data.length;i++){
            jsonObject.append(data[i]);
            if(i== data.length-1)
                jsonObject.append("]");
            else
                jsonObject.append(",");
        }
        jsonObject.append("}");
        return jsonObject.toString();
    }

    private void sendDataThroughRequest(String jsonObject){
        try(BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(connection.getOutputStream())
        )){
            bufferedWriter.write(jsonObject);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readResponseFromRequest(){
        String response = null;
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)
        )){
            response =  bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

