package fr.iut_nantes.quizomdb.controler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestControlerGamer {
	ControlerGamer control;
	    
	 @Before 
	 public void initialize() {   
		 control = new ControlerGamer();
	    }
	
	
	@Test
	public void testLogin(){
		String token = control.login("cc", "yeah");
		assertNotNull(control.getAnswers(token));
	}
	
	@Test
	public void testGetGoodAnswers(){
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testGetAnswers(){
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testDisconnect(){
		fail("Not yet implemented"); // TODO
		
	}

}
