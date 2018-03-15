package fr.iut.nantes.quizzmovie.entite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @version 1.0
 * @since 1.0
 */
public class TokenException extends Exception {

    /**
     * Constructor
     */
    public TokenException() {
        super("Invalid Token");
    }

    /**
     * @return the error message in a json
     */
    public String getJsonMsg() {
        return "{ \"error\" : \" Invalid Token\" }";
    }

    /**
     * @return the responseEntity in case of tokenException
     */
    public ResponseEntity getResponseEntity() {
        return new ResponseEntity<>(getJsonMsg(), HttpStatus.UNAUTHORIZED);
    }

}
