package org.randoom.setlx.SetlXMusic.Patterns;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.NullArgumentsException;

import static org.junit.Assert.*;

public class SetlXPatternStorageTest {
    iSetlXPatternStorage storage = new SetlXPatternStorage();

    @Before
    public void setUp() throws Exception {

    }

    //Checks if a pattern can be saved
    @Test
    public void addPattern1() throws Exception {
        storage.addPattern("Test", new Pattern());
        assertNotNull("Pattern stored", storage.getPattern("Test"));
    }
    //Checks if a null pattern can be saved
    @Test
    public void addPattern2() throws Exception {
        boolean exceptionOccured = false;
        try {
            storage.addPattern("Test", null);
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
    storage.addPattern("Test",new Pattern());
    assertTrue(storage.checkExisting("Test"));
    }

    /**
     * Checks for a non existing pattern
     * @throws Exception
     */
    @Test
    public void checkExisting1() throws Exception {
        storage.addPattern("Test", new Pattern());
        assertFalse(storage.checkExisting("tesT"));
    }

    @Test
    public void getPattern() throws Exception{
        storage.addPattern("Test", new Pattern());
        assertNotNull(storage.getPattern("Test"));
    }
    @Test
    public void deletePattern() throws Exception {
        storage.addPattern("Test", new Pattern());
        storage.deletePattern("Test");
    }

    @Test
    public void getAllPatterns() throws Exception {
        storage.addPattern("Test", new Pattern());
        storage.addPattern("Test2", new Pattern());
        storage.addPattern("Test3", new Pattern());
        storage.addPattern("Test4", new Pattern());
        assertTrue(storage.getAllPatterns().size()==4);
    }

}