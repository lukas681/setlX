package org.randoom.setlx.SetlXMusic.Patterns;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.MusicSystem.Storages.MusicStorage;
import org.randoom.setlx.SetlXMusic.MusicSystem.Storages.iMusicStorage;

import static org.junit.Assert.*;

public class MusicStorageTest {
    iMusicStorage<Pattern> storage = new MusicStorage();

    @Before
    public void setUp() throws Exception {

    }

    //Checks if a pattern can be saved
    @Test
    public void addPattern1() throws Exception {
        storage.addElement("Test", new Pattern());
        assertNotNull("Pattern stored", storage.getElement("Test"));
    }
    //Checks if a null pattern can be saved
    @Test
    public void addPattern2() throws Exception {
        boolean exceptionOccured = false;
        try {
            storage.addElement("Test", null);
        } catch (NullArgumentsException ne) {
            exceptionOccured = true;
        }
        assertTrue("Null Argument on adding a pattern throws an Setl Exception", exceptionOccured);
    }

    /**
     * Checks for an existing Pattern
     * @throws Exception
     */
    @Test
    public void checkExisting() throws Exception {
    storage.addElement("Test",new Pattern());
    assertTrue(storage.checkExisting("Test"));
    }

    /**
     * Checks for a non existing pattern
     * @throws Exception
     */
    @Test
    public void checkExisting1() throws Exception {
        storage.addElement("Test", new Pattern());
        assertFalse(storage.checkExisting("tesT"));
    }

    @Test
    public void getPattern() throws Exception{
        storage.addElement("Test", new Pattern());
        assertNotNull(storage.getElement("Test"));
    }
    @Test
    public void deletePattern() throws Exception {
        storage.addElement("Test", new Pattern());
        storage.deleteElement("Test");
        assertTrue(storage.getAllElements().size()==0);
    }

    @Test
    public void getAllPatterns() throws Exception {
        storage.addElement("Test", new Pattern());
        storage.addElement("Test2", new Pattern());
        storage.addElement("Test3", new Pattern());
        storage.addElement("Test4", new Pattern());
        assertTrue(storage.getAllElements().size()==4);
    }

}