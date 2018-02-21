package fr.iut_nantes.quizomdb.controler;

import fr.iut_nantes.quizomdb.entite.Constants;
import fr.iut_nantes.quizomdb.entite.Gamer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Set;

public class ControlerGamer {
    private HashMap<String, Gamer> gamers;


    public ControlerGamer() {
        this.gamers = new HashMap<>();
    }

    public String login(String login, String password) throws Exception {
        Gamer gamer = new Gamer(login, password); // can throw exception
        this.gamers.put(login, gamer);
        return Jwts.builder()
                .setSubject(login)
                .signWith(SignatureAlgorithm.HS512, Constants.key)
                .compact();
    }

    public void disconnect(String token) {
        this.gamers.remove(token);
    }


    public Gamer getGamer(String token) {
        return this.gamers.get(token);
    }

    public Set<String> getLogins() {
        return this.gamers.keySet();
    }


}
