package fr.iut_nantes.quizomdb.controler;

import fr.iut_nantes.quizomdb.entite.Constants;
import io.jsonwebtoken.Jwts;

/**
 * @version 1.0
 * @since 1.0
 */
public class ControlerGeneral {
    private ControlerOMDB omdb;
    private ControlerGamer gamer;


    /**
     * @param token
     * @return the actual question of the user
     */
    public String getQuestion(String token) {
        return this.omdb.getQuestion(token);
    }

    /**
     * Change the actual question of a user
     * @param token
     * @return the new question
     */
    public String generateQuestion(String token) {
        return this.omdb.generateQuestion(token);
    }

    /**
     * Check if the response in parameter is close to the answers
     * @param token of the user
     * @param response
     * @return true of false
     */
    public boolean isCorrectResponse(String token, String response) {
        // TODO
        return false;
    }

    /**
     * Try to log in a user
     * @param login
     * @param password
     * @return the token of the user if sucessful, else 'err'
     */
    public String login(String login, String password) {
        try {
            return this.gamer.login(login, password);
        } catch (Exception e) {
            return "err";
        }
    }

    /**
     * Delete the actual session of the user
     * @param token of the user
     */
    public void disconnect(String token) {
        this.gamer.disconnect(token);
    }

    /**
     *
     * @param token of the user
     * @return the number of good answers of the user
     */
    public int getGoodAnswers(String token) {
        return this.gamer.getGamer(token).getGoodAnswers();
    }

    /**
     *
     * @param token
     * @return the number of answers of the user
     */
    public int getAnswers(String token) {
        return this.gamer.getGamer(token).getAnswers();
    }

    /**
     * Check if the token is correct
     * @param token of the user
     * @return true if successful, else false
     */
    public boolean isCorrectToken(String token) {
        for (String login : this.gamer.getLogins()) {
            assert Jwts.parser().setSigningKey(Constants.key).parseClaimsJws(token).getBody().getSubject().equals(login);
            return true;
        }
        return false;
    }

}
