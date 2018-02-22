package fr.iut_nantes.quizomdb.entite;

/**
 * @version 1.0
 * @since 1.0
 */
public class Gamer {
    private String login;
    private int goodAnswers;
    private int answers;


    /**
     * Class constructor
     * @param login
     * @param password
     * @throws Exception if can't find the user in the database
     */
    public Gamer(String login, String password) throws Exception {
        this.login = login;
        this.goodAnswers = 0;
        this.answers = 0;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }


    /**
     * Set the login
     * @param login, the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * @return the number of good answers
     */
    public int getGoodAnswers() {
        return goodAnswers;
    }


    /**
     * Set the number of answers
     * @param goodAnswers
     */
    public void setGoodAnswers(int goodAnswers) {
        this.goodAnswers = goodAnswers;
    }


    /**
     * @return the number of answers
     */
    public int getAnswers() {
        return answers;
    }


    /**
     * Set the number of answers
     * @param answers, the new number of answers
     */
    public void setAnswers(int answers) {
        this.answers = answers;
    }

    /**
     * Override of the method equals
     * @param o
     * @return true of false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gamer)) return false;

        Gamer gamer = (Gamer) o;

        return login.equals(gamer.login);
    }
}
