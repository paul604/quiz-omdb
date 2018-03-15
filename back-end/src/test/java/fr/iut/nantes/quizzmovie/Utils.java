package fr.iut.nantes.quizzmovie;

import fr.iut.nantes.quizzmovie.application.QuizzMovieApplication;
import fr.iut.nantes.quizzmovie.db.DbMongo;
import fr.iut.nantes.quizzmovie.db.ExceptionDB;
import fr.iut.nantes.quizzmovie.entite.Config;

import static fr.iut.nantes.quizzmovie.application.QuizzMovieApplication.config;
import static fr.iut.nantes.quizzmovie.application.QuizzMovieApplication.log;

/**
 * @version 1.0
 * @since 1.0
 */
public class Utils {
    public static void setupConfig() {
        String configPath = QuizzMovieApplication.class.getResource("/configTest.properties").getPath();
        log.info("path of config file: " + configPath);
        config = new Config(configPath);
        try {
            DbMongo.upMongo();
        } catch (ExceptionDB exceptionDB) {
            exceptionDB.printStackTrace();
        }
    }
}
