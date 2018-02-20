package fr.iut_nantes.quizomdb.db;

import fr.iut_nantes.quizomdb.entite.Gamer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Paul on 20/02/18.
 */
public class DbObjectTest {

    Idb db;

    @Before
    public void setUp() throws Exception {
        db = new DbObject();
    }

    @Test
    public void listGamerIsEmpty() throws Exception {
        assertTrue("list Gamer Is not Empty", db.getAllGamer().isEmpty());
    }

    @Test
    public void addGamerDirectly() throws Exception {
        Gamer gamer = new Gamer("test", "pwdTest");
        db.addGamer(gamer);
        assertFalse("List gamers is empty", db.getAllGamer().isEmpty());
        assertEquals("Object gamer is not equals", gamer, db.getGamer(gamer.getLogin()));
    }

    @Test
    public void addGamerIndirectly() throws Exception {
        String name = "test";
        Gamer gamer = new Gamer(name, "pwdTest");
        db.addGamer(name, "pwdTest");
        assertFalse("List gamers is empty", db.getAllGamer().isEmpty());
        assertEquals("Object gamer is not equals", gamer,  db.getGamer(name));
    }
}