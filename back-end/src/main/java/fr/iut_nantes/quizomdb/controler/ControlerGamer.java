package fr.iut_nantes.quizomdb.controler;

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

    /**
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
        Gamer gamer = new Gamer(login, password); // can throw exception
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
