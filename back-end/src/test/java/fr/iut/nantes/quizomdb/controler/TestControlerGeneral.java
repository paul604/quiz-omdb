package fr.iut.nantes.quizomdb.controler;

import fr.iut.nantes.quizomdb.entite.Constants;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestControlerGeneral {
    ControlerGeneral control;
    String token ;

    @Before
    public void initialize() {
        control = new ControlerGeneral();
        ControlerGamer gamer = new ControlerGamer();
        token = gamer.addGamer("login", 0,0);

    }

    @Test
    public void getLoginFromToken(){
        assertNull(control.getLoginFromToken(""));
    }

    @Test
    public void isValideToken(){
        assertTrue(Jwts.parser().setSigningKey(Constants.key).parseClaimsJws(token).getBody().getSubject().equals("login"));
        assertTrue(!Jwts.parser().setSigningKey(Constants.key).parseClaimsJws(token).getBody().getSubject().equals("log"));
    }





}
