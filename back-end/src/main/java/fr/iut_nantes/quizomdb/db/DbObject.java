package fr.iut_nantes.quizomdb.db;

import fr.iut_nantes.quizomdb.application.QuizOmdbApplication;
import fr.iut_nantes.quizomdb.entite.Gamer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @see fr.iut_nantes.quizomdb.db.Idb
 * @since 1.0
 */
public class DbObject implements Idb {

    private Map<String, Gamer> listGamers;

    public DbObject() {
        QuizOmdbApplication.log.debug("create DB Object");
        listGamers = new HashMap<>();
    }

    @Override
    public boolean setAnswers(String pseudo, int val) {
        listGamers.get(pseudo).setAnswers(val);
        return true;
    }

    @Override
    public boolean setGoodAnswers(String pseudo, int val) {
        listGamers.get(pseudo).setGoodAnswers(val);
        return true;
    }

    @Override
    public boolean addGamer(String pseudo, String pwd) throws Exception {
        Gamer gamer = new Gamer(pseudo, pwd);
        listGamers.put(pseudo, gamer);
        return true;
    }

    @Override
    public int getAnswers(String pseudo) {
        return listGamers.get(pseudo).getAnswers();
    }

    @Override
    public int getGoodAnswers(String pseudo) {
        return listGamers.get(pseudo).getGoodAnswers();
    }

    @Override
    public Gamer getGamer(String pseudo) {
        return listGamers.get(pseudo);
    }

    @Override
    public List<Gamer> getAllGamer() {
        return new ArrayList<>(listGamers.values());
    }

    @Override
    public boolean connect(String login, String pwd) {
        return true;
    }
}
