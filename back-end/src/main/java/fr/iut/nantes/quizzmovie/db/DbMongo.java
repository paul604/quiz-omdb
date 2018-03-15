package fr.iut.nantes.quizzmovie.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import fr.iut.nantes.quizzmovie.entite.Gamer;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static fr.iut.nantes.quizzmovie.application.QuizzMovieApplication.config;
import static fr.iut.nantes.quizzmovie.application.QuizzMovieApplication.log;
import static fr.iut.nantes.quizzmovie.entite.Constants.*;

/**
 * @version 1.0
 * @see Idb
 * @since 1.0
 */
public class DbMongo implements Idb {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> gamerCollection;

    /**
     * init mongoDb
     *
     * @since 1.0
     */
    public DbMongo() throws ExceptionDB {
        try {
            log.info("create mongodb ...");
            mongoClient = new MongoClient(
                    config.getMongoUrl() + ":" + config.getMongoPort(),
                    MongoClientOptions.builder().retryWrites(false).build());
            log.info("mongodbClient OK");
            database = mongoClient.getDatabase(config.getMongoDbName());
            log.info("database OK");
            gamerCollection = database.getCollection(config.getMongoCollectionGamer());
            log.info("gamerCollection OK");
            log.info("create mongodb OK");
        } catch (Exception e) {
            log.error("MongoDb error", e);
            throw new ExceptionDB("MongoDb error: " + e.getLocalizedMessage());
        }
    }

    public static void upMongo() throws ExceptionDB {
        try {
            log.info("up mongodb");
            mongoClient = new MongoClient(
                    config.getMongoUrl() + ":" + config.getMongoPort(),
                    MongoClientOptions.builder().retryWrites(false).build());
            log.info("mongodbClient UP");
            database = mongoClient.getDatabase(config.getMongoDbName());
            log.info("database UP");
            gamerCollection = database.getCollection(config.getMongoCollectionGamer());
            log.info("gamerCollection UP");
            log.info("create mongodb UP");
        } catch (Exception e) {
            log.error("MongoDb UP error", e);
            throw new ExceptionDB("MongoDb error: " + e.getLocalizedMessage());
        }

    }

    /**
     * transform gamer in object for MongoDb
     *
     * @param gamer Gamer to transform
     * @param pwd   password of gamer
     * @return equivalent to gamer
     * @since 1.0
     */
    private static Document toDocument(Gamer gamer, String pwd) {
        return new Document(DB_GAMER_LOGIN, gamer.getLogin())
                .append(DB_GAMER_PWD, pwd)
                .append(DB_GAMER_GOOD_ANSWERS, gamer.getGoodAnswers())
                .append(DB_GAMER_ANSWERS, gamer.getAnswers());
    }

    @Override
    public boolean setAnswers(String pseudo, int val) throws ExceptionDB {
        Document documents = gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first();
        if (documents == null) {
            throw new ExceptionDB("setAnswers: value not found for update");
        }

        gamerCollection.updateOne(
                eq(DB_GAMER_LOGIN, pseudo),
                new Document("$set", new Document(DB_GAMER_ANSWERS, val))
        );

        return true;
    }

    @Override
    public boolean setGoodAnswers(String pseudo, int val) throws ExceptionDB {
        Document documents = gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first();
        if (documents == null) {
            throw new ExceptionDB("setAnswers: value not found for update");
        }

        UpdateResult result = gamerCollection.updateOne(
                eq(DB_GAMER_LOGIN, pseudo),
                new Document("$set", new Document(DB_GAMER_GOOD_ANSWERS, val))
        );

        return result.wasAcknowledged();
    }

    @Override
    public boolean addGamer(Gamer gamer, String pwd) {
        try {
            gamerCollection.insertOne(toDocument(gamer, pwd));
        } catch (Exception e) {
            log.error("addGamer error", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean supGamer(String pseudo) {
        try {
            DeleteResult deleteResult = gamerCollection.deleteOne(eq(DB_GAMER_LOGIN, pseudo));
            return deleteResult.getDeletedCount() == 1;
        } catch (Exception e) {
            log.warn("supGamer error");
            return false;
        }
    }


    @Override
    public int getAnswers(String pseudo) throws ExceptionDB {
        Document document = gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first();
        if (document == null) {
            throw new ExceptionDB("getAnswers: value not found for get");
        }
        Object o = document.get(DB_GAMER_ANSWERS);
        int out;
        if (o instanceof Double) {
            out = (int) (double) o;
        } else {
            out = (int) o;
        }
        return out;
    }

    @Override
    public int getGoodAnswers(String pseudo) throws ExceptionDB {
        Document document = gamerCollection.find(eq(DB_GAMER_LOGIN, pseudo)).first();
        if (document == null) {
            throw new ExceptionDB("getGoodAnswers: value not found for get");
        }
        Object o = document.get(DB_GAMER_GOOD_ANSWERS);
        int out;
        if (o instanceof Double) {
            out = (int) (double) o;
        } else {
            out = (int) o;
        }
        return out;
    }

    @Override
    public boolean connect(String login, String pwd) {
        String pwdDb = (String) gamerCollection.find(eq(DB_GAMER_LOGIN, login)).first().get(DB_GAMER_PWD);
        return pwdDb.equals(pwd);
    }

}
