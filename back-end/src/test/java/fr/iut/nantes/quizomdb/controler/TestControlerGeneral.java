package fr.iut.nantes.quizomdb.controler;

import fr.iut.nantes.quizomdb.Utils;
import fr.iut.nantes.quizomdb.entite.Constants;
import fr.iut.nantes.quizomdb.entite.Gamer;
import io.jsonwebtoken.Jwts;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestControlerGeneral {
    ControlerGeneral control;
    String token ;
    ControlerGamer gamer;

    @Before
    public void initialize() {
        Utils.setupConfig();
        control = new ControlerGeneral();
        gamer = new ControlerGamer();
        gamer.getDb().addGamer(new Gamer("test",0,0), "test");
        token = control.login("test","test");

    }

    @After
    public void tearDown() throws Exception {
        gamer.getDb().supGamer("test");
    }

    @Test
    public void getLoginFromToken(){
        assertNull(control.getLoginFromToken(""));
    }

    @Test
    public void getLoginFromToken2(){
        assertEquals("test",control.getLoginFromToken(token));
    }

    @Test
    public void isValideToken(){
        assertTrue(Jwts.parser().setSigningKey(Constants.key).parseClaimsJws(token).getBody().getSubject().equals("test"));
        assertTrue(!Jwts.parser().setSigningKey(Constants.key).parseClaimsJws(token).getBody().getSubject().equals("log"));
    }

    @Test
    public void loginFailed(){
        assertEquals("Err", control.login("",""));
    }













}
