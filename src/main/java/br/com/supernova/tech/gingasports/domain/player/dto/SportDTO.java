package br.com.supernova.tech.gingasports.domain.player.dto;

import br.com.supernova.tech.gingasports.domain.player.dao.Preference;

public record SportDTO(String sport, String modality, String position){

    public SportDTO(Preference preference){
        this(preference.getSport().getValue(), preference.getModality().getValue(), preference.getPosition());
    }
}
