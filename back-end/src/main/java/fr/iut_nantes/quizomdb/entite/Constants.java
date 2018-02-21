package fr.iut_nantes.quizomdb.entite;

import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

public class Constants {
	
	public static Key key = MacProvider.generateKey();	
	
}
