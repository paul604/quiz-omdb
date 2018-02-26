package fr.iut.nantes.quizomdb.controler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.iut.nantes.quizomdb.entite.Quizz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        String image , question , answers, film;

        question = this.questionAlea() ;

//        film = this.filmAlea();
//        String filmjson = gson.toJson(film);

        image = "http://vignette2.wikia.nocookie.net/pingufan/images/2/2e/Pingu_the_movie_xxlg.png/revision/latest?cb=20170221164619";
        answers = "Pingu";

        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("question", question);
        valeurs.put("image", image);


        this.addQuizz(login, gson.toJson(valeurs) , answers);
        return gson.toJson(valeurs);
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


    /**
     *
     * @return a random question
     */
    private String questionAlea(){
        return "What is the year of released of this movie ?";
    }

    /**
     *
     * @return a random title of film
     */
    private String filmAlea(){
        String [] titles = new String[]{
                "Pingu",
        };
        int nb = (int)(Math.random() * (titles.length));
        String film= null;
        try {
            film = getHTML("http://www.omdbapi.com/?t="+titles[nb]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return film;
    }

    /**
     * do a HTTP GET
     * @param urlToRead
     * @return
     * @throws Exception
     */
    private String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }




}
