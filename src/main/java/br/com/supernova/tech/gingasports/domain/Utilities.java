package br.com.supernova.tech.gingasports.domain;

import java.time.LocalDate;

public class Utilities {

    public static LocalDate convertStringToDate(String date){
        String[] dateArray = date.split("\\-");
        return LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
    }
}
