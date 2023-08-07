package br.com.supernova.tech.gingasports.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum Modality {

       FUTEBOL_CAMPO("Campo"),
       FUTEBOL_FUTSAL("Futsal"),
       FUTEBOL_SOCIETY("Society"),
       FUTEBOL_FUTVOLEI("Futvolêi"),
       FUTEBOL_FUTMESA("Futmesa"),
       FUTEBOL_AREIA("Areia"),
       FUTEBOL_X1("X1"),
       BASQUETE_5X5("5x5"),
       BASQUETE_3X3("3x3"),
       BASQUETE_X1("X1"),
       VOLEI_QUADRA("Quadra"),
       VOLEI_PRAIA("Praia"),
       VOLEI_SENTADO("Sentado"),
       VOLEI_FUTVOLEI("Futvolêi"),
       TENNIS_QUADRA_1("Quadra-Simples"),
       TENNIS_QUADRA_2("Quadra-Duplas"),
       TENNIS_SINTETICO_1("Sintético-Simples"),
       TENNIS_SINTETICO_2("Sintético-Duplas"),
       TENNIS_SAIBRO_1("Saibro-Simples"),
        TENNIS_SAIBRO_2("Saibro-Duplas"),

       TENNIS_SQUASH_1("Squash-Simples"),
        TENNIS_SQUASH_2("Squash-Duplas"),
       TENNIS_MESA_1("Mesa-Simples"),
        TENNIS_MESA_2("Mesa-Duplas"),

        TENNIS_BEACHTENNIS_1("BeachTennis-Simples"),
       TENNIS_BEACHTENNIS_2("BeachTennis-Duplas");

       private String value;

}
