package fr.iut_nantes.quizomdb.controler;

import java.util.HashMap;

import fr.iut_nantes.quizomdb.entite.Gamer;

public class ControlerGamer {
	private HashMap<String, Gamer> gamers;
	
	public String login(String login, String password){
		String token = null;
		//TODO
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
