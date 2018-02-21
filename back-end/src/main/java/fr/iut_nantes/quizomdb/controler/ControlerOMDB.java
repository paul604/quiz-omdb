package fr.iut_nantes.quizomdb.controler;

import fr.iut_nantes.quizomdb.entite.Quizz;

import java.util.HashMap;

public class ControlerOMDB {
    private HashMap<String, Quizz> actualsQuizz;

    public String getQuestion(String token) {
        return getQuizz(token).getQuestion();
    }

    public String generateQuestion(String token) {
        return getQuizz(token).generateQuestion();
    }

    public String getAnswers(String token, String response) {
        return getQuizz(token).getAnswers();
    }

    private Quizz getQuizz(String token) {
        return this.actualsQuizz.get(token);
    }


}
