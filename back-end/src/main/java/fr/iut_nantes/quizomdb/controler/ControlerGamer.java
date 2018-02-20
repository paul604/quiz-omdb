package fr.iut_nantes.quizomdb.controler;

import java.util.HashMap;

import fr.iut_nantes.quizomdb.entite.Gamer;

public class ControlerGamer {
	private HashMap<String, Gamer> gamers;
	
	
	public ControlerGamer() {
		this.gamers = new HashMap<>();
	}

	public String login(String login, String password){
		Gamer gamer = new Gamer(login, password);
		String token="err";
		if (gamer==null){
			System.out.println("Connexion échoué.");
			//TODO correction du system de token
		}else{
			token = login; //TODO correction du system de token
			this.gamers.put(token, gamer);
		}		
		return token;
	}
	
	public void disconnect(String token){
		this.gamers.remove(token);
	}
	
	public int getGoodAnswers(String token){
		return this.gamers.get(token).getGoodAnswers();
	}
	
	public int getAnswers(String token){
		return this.gamers.get(token).getAnswers();
	}
	
	
}
