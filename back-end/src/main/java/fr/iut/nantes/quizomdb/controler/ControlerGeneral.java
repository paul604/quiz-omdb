package fr.iut.nantes.quizomdb.controler;

import fr.iut.nantes.quizomdb.entite.Constants;
import fr.iut.nantes.quizomdb.entite.Gamer;
import fr.iut.nantes.quizomdb.entite.TokenException;
import io.jsonwebtoken.Jwts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @version 1.0
 * @since 1.0
 */
public class ControlerGeneral {
    private ControlerOMDB omdb = new ControlerOMDB();
    private ControlerGamer gamer = new ControlerGamer();

    /**
     * @param token
     * @return the actual question of the user
     */
    public String getQuestion(String token) throws TokenException {
        return this.omdb.getQuestion(getLoginFromToken(token));
    }

    /**
     * Change the actual question of a user
     *
     * @param token
     * @return the new question
     */
    public String generateQuestion(String token) throws TokenException {
        return this.omdb.generateQuestion(getLoginFromToken(token));
    }

    /**
     * Check if the response in parameter is close to the answers
     *
     * @param token    of the user
     * @param response
     * @return true of false
     */
    public boolean isCorrectResponse(String token, String response) throws TokenException {
        String login = getLoginFromToken(token);
        this.gamer.getGamer(login).incrementAnswers();
        String answers = this.omdb.getAnswers(login);
        boolean isCorrect = new ControlerDatamuse().hasCloseSpelling(response, answers);
        if (isCorrect) this.gamer.getGamer(login).incrementGoodAnswers();
        return isCorrect;
    }

    /**
     * Try to log in a user
     *
     * @param login
     * @param password
     * @return the token of the user if sucessful, else 'err'
     */
    public String login(String login, String password) {
        try {
            return this.gamer.login(login, password);
        } catch (Exception e) {
            return "Err";
        }
    }

    /**
     * Delete the actual session of the user
     *
     * @param token of the user
     */
    public void disconnect(String token) throws TokenException {
        String login = getLoginFromToken(token);
        this.omdb.disconnect(login);
        this.gamer.disconnect(login);
    }

    /**
     * @param token of the user
     * @return the number of good answers of the user
     */
    public int getGoodAnswers(String token) throws TokenException {
        return this.gamer.getGamer(getLoginFromToken(token)).getGoodAnswers();
    }

    /**
     * @param token of the user
     * @return the number of answers of the user
     */
    public int getAnswers(String token) throws TokenException {
        return this.gamer.getGamer(getLoginFromToken(token)).getAnswers();
    }

    /**
     * get the login associated to the token
     *
     * @param token of the user
     * @return the login if successful, else null
     */
    public String getLoginFromToken(String token) throws TokenException {
        for (String login : this.gamer.getLogins()) {
            boolean isValid;
            try {
                isValid = Jwts.parser().setSigningKey(Constants.key).parseClaimsJws(token).getBody().getSubject().equals(login);
            }catch (Exception e){
                throw new TokenException();
            }
            if (isValid) return login;
        }
        return null;
    }

    /**
     * create a user in the database
     * @param login
     * @param password
     */
    public String createAccount(String login, String password){
        boolean ok = this.gamer.getDb().addGamer(new Gamer(login, 0,0),password);
        return "{ " +
                "\"result\" : \"" + ok
                + "\" }";
    }
}
