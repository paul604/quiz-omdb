package fr.iut_nantes.quizomdb.db;

import fr.iut_nantes.quizomdb.entite.Gamer;

import java.util.List;

/**
 *
 */
public interface Idb {

    /**
     * @param pseudo name of gamer
     * @param val number of answers
     * @return true if ok
     */
    boolean setAnswers(String pseudo, int val);

    /**
     * @param pseudo name of gamer
     * @param val number of good answers
     * @return true if ok
     */
    boolean setGoodAnswers(String pseudo, int val);

    /**
     * @param pseudo name of gamer
     * @param pwd password
     */
    boolean addGamer(String pseudo, String pwd);

    /**
     * @param gamer gamer
     */
    boolean addGamer(Gamer gamer);


    /**
     * @param pseudo name of gamer
     * @return number of answers
     */
    int getAnswers(String pseudo);

    /**
     * @param pseudo name of gamer
     * @return number of good answers
     */
    int getGoodAnswers(String pseudo);

    /**
     * @param pseudo name of gamer
     * @return Gamer if exist, null other
     */
    Gamer getGamer(String pseudo);

    /**
     * @return all gamer
     */
    List<Gamer> getAllGamer();

    boolean connect(String login, String pwd);

}

