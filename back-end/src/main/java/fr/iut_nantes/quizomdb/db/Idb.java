package fr.iut_nantes.quizomdb.db;

import fr.iut_nantes.quizomdb.entite.Gamer;

import java.util.List;

/**
 * @version 1.0
 * @since 1.0
 */
public interface Idb {

    /**
     * @param pseudo name of gamer
     * @param val    number of answers
     * @return true if ok
     * @since 1.0
     */
    boolean setAnswers(String pseudo, int val);

    /**
     * @param pseudo name of gamer
     * @param val    number of good answers
     * @return true if ok
     * @since 1.0
     */
    boolean setGoodAnswers(String pseudo, int val);

    /**
     * @param pseudo name of gamer
     * @param pwd    password
     * @return true if ok
     * @since 1.0
     */
    boolean addGamer(String pseudo, String pwd);

    /**
     * @param gamer gamer
     * @return true if ok
     * @since 1.0
     */
    boolean addGamer(Gamer gamer);


    /**
     * @param pseudo name of gamer
     * @return number of answers
     * @since 1.0
     */
    int getAnswers(String pseudo);

    /**
     * @param pseudo name of gamer
     * @return number of good answers
     * @since 1.0
     */
    int getGoodAnswers(String pseudo);

    /**
     * @param pseudo name of gamer
     * @return Gamer if exist, null other
     * @since 1.0
     */
    Gamer getGamer(String pseudo);

    /**
     * @return all gamer
     * @since 1.0
     */
    List<Gamer> getAllGamer();

    /**
     * @param login name of gamer
     * @param pwd   password of gamer
     * @return true if login and pwd is good
     * @since 1.0
     */
    boolean connect(String login, String pwd);

}

