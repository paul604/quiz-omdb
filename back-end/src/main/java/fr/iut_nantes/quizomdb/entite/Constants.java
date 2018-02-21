package fr.iut_nantes.quizomdb.entite;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class Constants {

    public static Key key = MacProvider.generateKey();

    //<editor-fold desc="Key MongoDB">
    public static final String DB_GAMER_LOGIN = "login";
    public static final String DB_GAMER_PWD = "pwd";
    public static final String DB_GAMER_GOOD_ANSWERS = "goodAnswers";
    public static final String DB_GAMER_ANSWERS = "answers";
    //</editor-fold>
}
