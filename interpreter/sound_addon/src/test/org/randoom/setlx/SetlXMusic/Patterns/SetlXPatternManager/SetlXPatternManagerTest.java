package org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetlXPatternManagerTest {
    SetlXPatternManager mgr;
    @Before
    public void setUp() throws Exception {
        mgr = new SetlXPatternManager();
        mgr.addPattern("Test", new Pattern("C D E F G"));
    }


    /**
    * Tests, weather a new pattern will be created on duplicatePattern call
     *
      */
    @Test
    public void duplicatePatternIsCreated() throws Exception {
        mgr.duplicatePattern("Test", "Test2");
        assertTrue(mgr.getAllPatterns().size()==2); //A new duplicate Element was added
        //not tested: the set tempo/voice and instrumentation, because we can not fetch those properties
    }

    /**
     * Tests, weather the duplicate pattern has the same properties as the original one.
     */
    @Test
    public void duplicatePatternIsTheSame() throws Exception{
        mgr.duplicatePattern("Test","Test2");
        assertTrue(mgr.getPattern("Test").getPattern().toString().compareTo(mgr.getPattern("Test2").getPattern().toString())==0); //The patterns must be the same

    }

    /**
     * Duplicating objects have the risk, that just the references are copied. Then we would not have two
     * independent objects.
     */
    @Test
    public void duplicatePatternDoesNotReferenceToSameObject() throws Exception{
        mgr.duplicatePattern("Test","Test2");
        assertFalse(mgr.getPattern("Test")==mgr.getPattern("Test2")); //Make sure, that both objects are different
    }

}
