package fr.iut_nantes.quizomdb.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import fr.iut_nantes.quizomdb.entite.Gamer;

import java.net.UnknownHostException;
import java.util.List;

import static fr.iut_nantes.quizomdb.application.QuizOmdbApplication.log;

/**
 * @version 1.0
 * @see fr.iut_nantes.quizomdb.db.Idb
 * @since 1.0
 */
public class DbMongo implements Idb {

    public DbMongo() {
        try {
            log.debug("create mongodb");
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (UnknownHostException e) {
            log.error("Error create db mongo", e);
        }
    }

    @Override
    public boolean setAnswers(String pseudo, int val) {
        return false;
    }

    @Override
    public boolean setGoodAnswers(String pseudo, int val) {
        return false;
    }

    @Override
    public boolean addGamer(String pseudo, String pwd) {
        return false;
    }

    @Override
    public boolean addGamer(Gamer gamer) {
        return false;
    }

    @Override
    public int getAnswers(String pseudo) {
        return 0;
    }

    @Override
    public int getGoodAnswers(String pseudo) {
        return 0;
    }

    @Override
    public Gamer getGamer(String pseudo) {
        return null;
    }

    @Override
    public List<Gamer> getAllGamer() {
        return null;
    }

    @Override
    public boolean connect(String login, String pwd) {
        return false;
    }
}
