package br.com.supernova.tech.gingasports.infra.exception;

public class QueryException extends RuntimeException {

    public QueryException(String message){
        super(message);
    }
}
