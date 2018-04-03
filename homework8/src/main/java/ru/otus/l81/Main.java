package ru.otus.l81;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        LabRat labRat = new LabRat();
        Gson gson = new Gson();
        String jsonStringFromGSON = gson.toJson(labRat);
        String myParse = JsonConverter.convertToJson(labRat);

        LabRat labRat1 = gson.fromJson(myParse, LabRat.class);
        LabRat labRat2 = gson.fromJson(jsonStringFromGSON, LabRat.class);

        if (labRat1.equals(labRat2)) {
            System.out.println("json object writer worked successfully");
        }
    }
}
