package org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.MusicPlayer;

import static org.junit.Assert.*;

public class SetlXMusicManagerTest {

    MusicManager mgr;
    MusicPlayer pl;
    @Before
    public void setUp() throws Exception {
        mgr = new MusicManager();
        pl = new MusicPlayer(mgr);
        mgr.add("Test", new Pattern("C D E F G"));
    }

    @Test
    public void add() throws Exception {
        mgr.add("Test2", new Pattern("C D E F"));
        mgr.add("Test3", new Rhythm("....."));
        mgr.add("Test4", new ChordProgression("I IV V I"));
        mgr.getPattern("Test2");

    }

    /**
    * Tests, weather a new pattern will be created on duplicatePattern call
     *
      */
    @Test
    public void duplicatePatternIsCreated() throws Exception {
        mgr.getPattern("Test").setVoice(1).setInstrument(1).setTempo(120).setLayer(1);
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
        assertFalse(mgr.getPattern("Test")==mgr.getPattern("Test2")); //Make sure, that both objects are differentd
        assertFalse(mgr.getPattern("Test").getPattern()==mgr.getPattern("Test2").getPattern()); //Make sure, that both objects are different
    }
    @Test
    public void duplicatePatternDoesRemoveExplicitSettings() throws Exception{
        mgr.getPattern("Test").setInstrument(1);
        mgr.add("A",new Pattern("C D E F").setInstrument(2));
        mgr.duplicatePattern("A","B");
        assertTrue(mgr.getPattern("B").toString().compareTo("C D E F") == 0);
    }

    @Test
    public void eachChordAs() throws Exception { //TODO Write Test cases
        mgr.add("Test2", new ChordProgression("I IV V I"));
        mgr.eachChordAs("Test2","$0q $1w $2q");
        System.out.println(mgr.getChordProgression("Test2").getPattern());
    }
    @Test
    public void allChordsAsModified() throws Exception {
        mgr.add("Test2", new ChordProgression("I IV V"));
        mgr.allChordsAs("Test2","$0q $1w $2q");
        assertTrue( mgr.getChordProgression("Test2").toString().compareTo("C4MAJq F4MAJw G4MAJq")==0);
    }

    @Test
    public void getChordProgressions() throws Exception {
        ChordProgression prog = new ChordProgression("I IV V I");
        mgr.add("Test2", prog);
        assertEquals(mgr.getChordProgression("Test2"),prog);
    }
}
