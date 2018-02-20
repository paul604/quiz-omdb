package fr.iut_nantes.quizomdb.entite;

public class Gamer {
	private String login;
	private int goodAnswers;
	private int answers;
	
	
	public Gamer(String login, String password) {
		login(login, password);
	}


	private void login(String login, String password){	
		//TODO connexion à la base données et complétion des attributs
		this.login=login;
		this.goodAnswers=0;
		this.answers=0;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public int getGoodAnswers() {
		return goodAnswers;
	}


	public void setGoodAnswers(int goodAnswers) {
		this.goodAnswers = goodAnswers;
	}


	public int getAnswers() {
		return answers;
	}


	public void setAnswers(int answers) {
		this.answers = answers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Gamer)) return false;

		Gamer gamer = (Gamer) o;

		return login.equals(gamer.login);
	}
}
