package fr.iut.nantes.quizomdb.controler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class TestControlerGeneral {
    ControlerGeneral control;
    String token ;

    @Before
    public void initialize() {
        control = new ControlerGeneral();
        //TODO mock to abstract from database
        token = "";

    }

    @Test
    public void getLoginFromToken(){
        assertNull(control.getLoginFromToken(""));
    }




}
