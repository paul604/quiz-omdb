package fr.iut.nantes.quizomdb.controler;

import fr.iut.nantes.quizomdb.entite.Quizz;

import java.util.HashMap;

/**
 * @version 1.0
 * @since 1.0
 */
public class ControlerOMDB {
    private HashMap<String, Quizz> actualsQuizz;

    public ControlerOMDB() {
        this.actualsQuizz = new HashMap<>();
    }

    /**
     * Change the currents question/answers of the user
     * @param login of the user
     * @return the new question
     */
    public String generateQuestion(String login) {
        String image , question , answers;

        question = "What is the year of released of this movie ?";
        image = "http://vignette2.wikia.nocookie.net/pingufan/images/2/2e/Pingu_the_movie_xxlg.png/revision/latest?cb=20170221164619";
        answers = "Pingu";


        String json = "{ " +
                "\"question\" : \""+ question +"\", " +
                "\"image\" : \""+ image +
                "\"}";


        this.addQuizz(login, json , answers);
        return json;
    }

    /**
     * Ajoute un quizz dans la mémoire locale
     * @param login
     * @param question
     * @param answers
     */
    public void addQuizz(String login, String question, String answers){
        this.actualsQuizz.put(login, new Quizz(question, answers));
    }

    /**
     * retire un quizz de la mémoire locale
     * @param login
     */
    public void disconnect(String login){
        this.actualsQuizz.remove(login);
    }



    /**
     * @param login of the user
     * @return the actual question of the user
     */
    public String getQuestion(String login) {
        Quizz quizz = this.actualsQuizz.get(login);
        if (quizz != null) return quizz.getQuestion();
        return  null;

    }

    /**
     * @param login of the user
     * @return the actual answers
     */
    public String getAnswers(String login) {
        Quizz quizz = this.actualsQuizz.get(login);
        if (quizz != null) return quizz.getAnswers();
        return  null;
    }


}
