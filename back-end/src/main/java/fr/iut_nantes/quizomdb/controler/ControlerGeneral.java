package fr.iut_nantes.quizomdb.controler;

public class ControlerGeneral {
	private ControlerOMDB omdb;
	private ControlerGamer gamer;
	
	
	public String getQuestion(String token) {
		return this.omdb.getQuestion(token);
	}
	
	public String generateQuestion(String token) {
		return this.omdb.generateQuestion(token);
	}
	
	public boolean sendResponse(String token, String response) {
		// TODO
		return false;
	}

	public String login(String login, String password) {
		return this.gamer.login(login, password);
	}

	public void disconnect(String token) {
		this.gamer.disconnect(token);
	}

	public int getGoodAnswers(String token) {
		return this.gamer.getGoodAnswers(token);
	}

	public int getAnswers(String token) {
		return this.gamer.getAnswers(token);
	}
}
