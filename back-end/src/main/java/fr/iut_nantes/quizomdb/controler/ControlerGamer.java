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
     * @param login    login of gamer
     * @param password password of gamer
     * @return
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

    /**
     * @param token
     * @since 1.0
     */
    public void disconnect(String token) {
        this.gamers.remove(token);
    }


    /**
     * @param token
     * @return
     * @since 1.0
     */
    public Gamer getGamer(String token) {
        return this.gamers.get(token);
    }

    /**
     * @return
     * @since 1.0
     */
    public Set<String> getLogins() {
        return this.gamers.keySet();
    }


}
