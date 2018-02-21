package fr.iut_nantes.quizomdb.controler;

import fr.iut_nantes.quizomdb.entite.DataJwt;
import io.jsonwebtoken.Jwts;

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
		try {
			return this.gamer.login(login, password);
		} catch (Exception e) {
			return "Login or password invalid.";
		}
	}

	public void disconnect(String token) {
		this.gamer.disconnect(token);
	}

	public int getGoodAnswers(String token) {
		return this.gamer.getGamer(token).getGoodAnswers();
	}

	public int getAnswers(String token) {
		return this.gamer.getGamer(token).getAnswers();
	}
	
	public boolean validateToken(String token){
		for (String login : this.gamer.getLogins()) {
			assert Jwts.parser().setSigningKey(DataJwt.key).parseClaimsJws(token).getBody().getSubject().equals(login);
			return true;
		}
		return false;		
	}
	
}
