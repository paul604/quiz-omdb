package fr.iut_nantes.quizomdb.controler;

import fr.iut_nantes.quizomdb.entite.Quizz;

import java.util.HashMap;

/**
 * @version 1.0
 * @since 1.0
 */
public class ControlerOMDB {
    private HashMap<String, Quizz> actualsQuizz;

    /**
     * @param token of the user
     * @return the actual question of the user
     */
    public String getQuestion(String token) {
        return getQuizz(token).getQuestion();
    }

    /**
     * Change the currents question/answers of the user
     * @param token of the user
     * @return the new question
     */
    public String generateQuestion(String token) {
        return getQuizz(token).generateQuestion();
    }

    /**
     *
     * @param token of the user
     * @param response of the user
     * @return the actual answers
     */
    public String getAnswers(String token, String response) {
        return getQuizz(token).getAnswers();
    }

    /**
     *
     * @param token of the user
     * @return the actual quizz of the user
     */
    private Quizz getQuizz(String token) {
        return this.actualsQuizz.get(token);
    }


}
