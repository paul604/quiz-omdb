package fr.iut.nantes.quizomdb.controler;

import fr.iut.nantes.quizomdb.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestControlerGamer {
    ControlerGamer control;

	 @Before
	 public void initialize() {
         Utils.setupConfig();
		 control = new ControlerGamer();
	    }
	
	
	@Test
	public void testAddGamerAndDisconnect(){
		assertNull(control.getGamer("login"));
		control.addGamer("login", 0,0);
        assertNotNull(control.getGamer("login"));
        control.disconnect("login");
        assertNull(control.getGamer("login"));
	}

    @Test
    public void testGetLogins(){
        assertTrue(!control.getLogins().contains("login"));
        control.addGamer("login", 0,0);
        assertTrue(control.getLogins().contains("login"));

    }

    @Test (expected = Exception.class)
    public void testLoginInvalid() throws Exception {
        control.login("falseLogin", "falsePassword");
    }

    @Test
    public void testAddGamerReturn() throws Exception {
        String value1 = control.addGamer("login", 0, 0);
        String value2 = control.addGamer("login", 0, 0);
        assertTrue(value1.equals(value2));
    }

}
