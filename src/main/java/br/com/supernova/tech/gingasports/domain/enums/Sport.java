package br.com.supernova.tech.gingasports.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sport {

    Soccer("Futebol"),
    Volley("Volêi"),
    Basket("Basquete"),
    Tennis("Tênis");

    private String value;
}
