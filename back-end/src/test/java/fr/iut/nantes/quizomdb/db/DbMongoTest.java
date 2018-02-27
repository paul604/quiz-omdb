package fr.iut.nantes.quizomdb.db;

import fr.iut.nantes.quizomdb.Utils;
import fr.iut.nantes.quizomdb.application.QuizOmdbApplication;
import fr.iut.nantes.quizomdb.entite.Config;
import fr.iut.nantes.quizomdb.entite.Gamer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static fr.iut.nantes.quizomdb.application.QuizOmdbApplication.*;
import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @see DbMongo
 * @since 1.0
 */
public class DbMongoTest {

    private Idb db;

    @Before
    public void setUp() throws Exception {
        Utils.setupConfig();

        db = new DbMongo();

        db.addGamer(new Gamer("pseudo", 0, 0), "pwd");
    }

    @After
    public void tearDown() throws Exception {
        db.supGamer("pseudo");
    }

    //<editor-fold desc="setAnswers">
    @Test(expected = ExceptionDB.class)
    public void setAnswersForUnknownPseudo() throws Exception {
        db.setAnswers("UnknownPseudo", 10);
    }

    @Test
    public void setAnswersForNoUnknownPseudo() throws Exception {
        String pseudo = "pseudo";
        db.setAnswers(pseudo, 10);
        int answers = db.getAnswers(pseudo);
        assertEquals("setAnswers 10", 10, answers);
    }
    //</editor-fold>

    //<editor-fold desc="setGoodAnswers">
    @Test(expected = ExceptionDB.class)
    public void setGoodAnswersForUnknownPseudo() throws Exception {
        db.setGoodAnswers("UnknownPseudo", 10);
    }

    @Test
    public void setGoodAnswersForNoUnknownPseudo() throws Exception {
        String pseudo = "pseudo";
        db.setGoodAnswers(pseudo, 10);
        int goodAnswers = db.getGoodAnswers(pseudo);
        assertEquals("setAnswers 10", 10, goodAnswers);
    }
    //</editor-fold>


}