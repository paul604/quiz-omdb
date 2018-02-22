package fr.iut_nantes.quizomdb.controler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestControlerOMDB {

    ControlerOMDB control;

    @Before
    public void initialize() {
        control = new ControlerOMDB();
    }


    @Test
    public void disconnectAndAddQuizz(){
        assertNull(control.getQuestion("login"));
        control.addQuizz("login","question","answers");
        assertNotNull(control.getQuestion("login"));
        control.disconnect("login");
        assertNull(control.getQuestion("login"));
    }





}
