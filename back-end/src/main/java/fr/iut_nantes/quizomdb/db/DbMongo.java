package fr.iut_nantes.quizomdb.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import fr.iut_nantes.quizomdb.entite.Gamer;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static fr.iut_nantes.quizomdb.application.QuizOmdbApplication.log;
import static fr.iut_nantes.quizomdb.entite.Constants.*;

/**
 * @version 1.0
 * @see fr.iut_nantes.quizomdb.db.Idb
 * @since 1.0
 */
public class DbMongo implements Idb {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> gamerCollection;

    /**
     * init mongoDb
     */
    public DbMongo() {
        log.debug("create mongodb");
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDatabase("DbQuizOmdb");
        gamerCollection = database.getCollection("Gamer");
    }

    /**
     * transform gamer in object for MongoDb
     *
     * @param gamer Gamer to transform
     * @param pwd   password of gamer
     * @return equivalent to gamer
     */
    private static Document toDocument(Gamer gamer, String pwd) {
        return new Document(DB_GAMER_LOGIN, gamer.getLogin())
                .append(DB_GAMER_PWD, pwd)
                .append(DB_GAMER_GOOD_ANSWERS, gamer.getGoodAnswers())
                .append(DB_GAMER_ANSWERS, gamer.getAnswers());
    }

    @Override
    public boolean setAnswers(String pseudo, int val) {
        Document documents = gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first();
        if (documents == null) {
            log.warn("setAnswers: value not found for update");
            return false;
        }

        gamerCollection.updateOne(
                eq(DB_GAMER_LOGIN, pseudo),
                new Document("$set", new Document(DB_GAMER_ANSWERS, val))
        );

        return true;
    }

    @Override
    public boolean setGoodAnswers(String pseudo, int val) {
        Document documents = gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first();
        if (documents == null) {
            log.warn("setGoodAnswers: value not found for update");
            return false;
        }

        UpdateResult result = gamerCollection.updateOne(
                eq(DB_GAMER_LOGIN, pseudo),
                new Document("$set", new Document(DB_GAMER_GOOD_ANSWERS, val))
        );

        return result.wasAcknowledged();
    }

    @Override
    public boolean addGamer(String pseudo, String pwd) {
//        try {
//            Document document = toDocument(new Gamer(pseudo, pwd), pwd);
//            gamerCollection.insertOne(document);
//        } catch (Exception e) {
//            log.error("error add gamer in DB", e);
//        }
        return true;
    }

    @Override
    public int getAnswers(String pseudo) {
        return (int) gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first().get(DB_GAMER_ANSWERS);
    }

    @Override
    public int getGoodAnswers(String pseudo) {

        return (int) gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first().get(DB_GAMER_GOOD_ANSWERS);
    }

    @Override
    public boolean connect(String login, String pwd) {
        String pwdDb = (String) gamerCollection.find(eq(DB_GAMER_LOGIN, login)).first().get(DB_GAMER_PWD);
        return pwdDb.equals(pwd);
    }

}
