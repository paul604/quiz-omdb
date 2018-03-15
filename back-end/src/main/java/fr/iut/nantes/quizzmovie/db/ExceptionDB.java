package fr.iut.nantes.quizzmovie.db;

import fr.iut.nantes.quizzmovie.application.QuizzMovieApplication;

/**
 * @version 1.0
 * @since 1.0
 */
public class ExceptionDB extends Exception {

    /**
     * @param message desc of error
     * @since 1.0
     */
    public ExceptionDB(String message) {
        super(message);
        QuizzMovieApplication.log.error("ExceptionDB: " + message);
    }
}
