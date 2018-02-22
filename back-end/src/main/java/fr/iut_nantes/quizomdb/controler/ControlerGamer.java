package fr.iut_nantes.quizomdb.controler;

import fr.iut_nantes.quizomdb.db.DbMongo;
import fr.iut_nantes.quizomdb.db.Idb;
import fr.iut_nantes.quizomdb.entite.Constants;
import fr.iut_nantes.quizomdb.entite.Gamer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Set;

/**
 * @version 1.0
 * @since 1.0
 */
public class ControlerGamer {

    private HashMap<String, Gamer> gamers;
    private Idb db;

    /**
     * @version 1.0
     * @see fr.iut_nantes.quizomdb.controler.ControlerGamer
     * @since 1.0
     */
    public ControlerGamer() {
        this.gamers = new HashMap<>();
    }

    /**
     * Try to authenticate the user
     * @param login gamer
     * @param password gamer
     * @return the token of the user
     * @throws Exception
     * @since 1.0
     */
    public String login(String login, String password) throws Exception {
        if (!db.connect(login, password)){
            throw new Exception();
        }
        int answers, goodAnswers;
        answers = db.getAnswers(login);
        goodAnswers = db.getAnswers(login);

        Gamer gamer = new Gamer(login, answers, goodAnswers);
        this.gamers.put(login, gamer);
        return Jwts.builder()
                .setSubject(login)
                .signWith(SignatureAlgorithm.HS512, Constants.key)
                .compact();
    }

    /** Delete the user from the local memory
     * @param token of the user
     * @since 1.0
     */
    public void disconnect(String token) {
        this.gamers.remove(token);
    }


    /**
     * @param token of the user
     * @return a user
     * @since 1.0
     */
    public Gamer getGamer(String token) {
        return this.gamers.get(token);
    }

    /**
     * @return the gamers in the local memory
     * @since 1.0
     */
    public Set<String> getLogins() {
        return this.gamers.keySet();
    }


}
