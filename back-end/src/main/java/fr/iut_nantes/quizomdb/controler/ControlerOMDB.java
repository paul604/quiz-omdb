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
     * Change the currents question/answers of the user
     * @param token of the user
     * @return the new question
     */
    public String generateQuestion(String token) {
        // TODO
        return null;
    }

    /**
     * @param token of the user
     * @return the actual question of the user
     */
    public String getQuestion(String token) {
        return this.actualsQuizz.get(token).getQuestion();
    }

    /**
     * @param token of the user
     * @return the actual answers
     */
    public String getAnswers(String token) {
        return this.actualsQuizz.get(token).getAnswers();
    }


}
