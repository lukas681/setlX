package org.randoom.setlx.setlXMusic.musicSystem.musicManager;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.KeyAlreadyInUseException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.NullArgumentsException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotFoundExceptions.PatternNotFoundException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotSupportedException;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class MusicManagerTest {

    MusicManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new MusicManager();
        manager.add("Test", new Pattern("C D E F G A B C6"));
        manager.add("Test2", new Pattern("C D E F G A B C6"));
        manager.add("Test3", new Pattern("C D E F G A B C6"));
    }

    @Test
    public void addPatternsToPatternByName() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.add("Pattern1", new Pattern("C D E"));
        manager.add("Pattern2", new Pattern("F G B"));
        manager.addPatternsToPatternByName("Pattern2", "Pattern1");
        manager.addPatternsToPatternByName("Pattern2", "Pattern1", "Pattern1");
    }

    @Test(expected = PatternNotFoundException.class)
    public void addPatternsToPatternByNameNotFound() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.add("Pattern1", new Pattern("C D E"));
        manager.add("Pattern2", new Pattern("F G B"));

        manager.addPatternsToPatternByName(null, null);
        manager.addPatternsToPatternByName(null, "Pattern1");
        manager.addPatternsToPatternByName("Pattern3", "Pattern3");
    }

    @Test
    public void allPatternsExists() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.add("Pattern1", new Pattern("C D E"));
        manager.add("Pattern2", new Pattern("F G B"));

        assertTrue(manager.allPatternsExists("Pattern1"));
        assertTrue(manager.allPatternsExists("Pattern1", "Pattern1"));
        assertTrue(manager.allPatternsExists("Pattern1", "Pattern2"));
        assertFalse(manager.allPatternsExists("Pattern3"));
        assertFalse(manager.allPatternsExists("Pattern3", "Pattern1"));
        assertFalse(manager.allPatternsExists("Pattern3", "Pattern2"));
        assertFalse(manager.allPatternsExists("Pattern3", "Pattern3"));
        assertFalse(manager.allPatternsExists("Pattern3", "Pattern2", "Pattern3", "Pattern4", "c", "d"));

    }


    @Test
    public void addPatternsToStorageThatAreAllowed() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        Pattern TestPattern = new Pattern("C D E F");
        manager.add("Test4", TestPattern);
        manager.add("", TestPattern);
    }


    @Test(expected = NullArgumentsException.class)
    public void addNullPointerToStorage() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        manager.add("", null);
        manager.add("Test4", null);
        manager.add("", new Pattern("C D E"));
    }


    @Test
    public void addNotesToPattern() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
    }

    @Test
    public void addPatternsToPatternByName1() {
    }

    @Test
    public void addPatternsToPattern() {
    }

    @Test
    public void addNotesToPattern1() {
    }

    @Test
    public void modifyPatternProperty() {
    }

    @Test
    public void removeElement() {
    }

    @Test
    public void getPattern() {
    }

    @Test
    public void duplicatePattern() {
    }

    @Test
    public void getDetailPatternStats() {
    }

    @Test
    public void getGeneralPatternStats() {
    }

    @Test
    public void getRhythm() {
    }

    @Test
    public void getChordProgression() {
    }

    @Test
    public void getAllRhythms() {
    }

    @Test
    public void getAllChordProgressions() {
    }

    @Test
    public void getStorageWhereKeyIsUsed() {
    }

    @Test
    public void eachChordAs() {
    }

    @Test
    public void allChordsAs() {
    }

    @Test
    public void saveAsPattern() {
    }

    @Test
    public void saveAsMidi() {
    }

    @Test
    public void loadMidi() {
    }

    @Test
    public void getAllPatterns() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        // Some initial tests
        assertNotNull(manager.getAllPatterns());
        int currentSize = manager.getAllPatterns().size();
        manager.add("Test4",new Pattern("C D E F"));
        assertTrue((currentSize+1)==manager.getAllPatterns().size());

        // testing the correctness
        iMusicManager tmp_mgr = new MusicManager();
        HashMap<String, Pattern> Patterns = new HashMap();
        Patterns.put("A", new Pattern("C D E F G"));
        Patterns.put("B", new Pattern("C D E F G"));
        Patterns.put("C", new Pattern("C F G C C"));
        Patterns.put("D", new Pattern("C G B C D"));

        Patterns.forEach((x,y) -> { //Inserting Patterns
            try {
                tmp_mgr.add(x,y);
            } catch (NullArgumentsException e) {
                e.printStackTrace();
            } catch (ProducerNotSupportedException e) {
                e.printStackTrace();
            } catch (KeyAlreadyInUseException e) {
                e.printStackTrace();
            }
        });
        assertTrue(tmp_mgr.getAllPatterns().equals(Patterns));
        assertTrue(new MusicManager().getAllPatterns().isEmpty()==true); // An empty Music Storage should return null
    }

    @Test
    public void allPatternsExists1() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        assertTrue(manager.allPatternsExists("Test"));
        assertTrue(manager.allPatternsExists("Test","Test2"));
        assertTrue(manager.allPatternsExists("Test","Test3","Test2"));
        assertTrue(manager.allPatternsExists("Test","Test","Test","Test"));
        assertFalse(manager.allPatternsExists("Test4"));
        assertFalse(manager.allPatternsExists("Test4","Test5"));
        assertFalse(manager.allPatternsExists("Test4", null));
        assertFalse(manager.allPatternsExists("Test3", null));
        assertFalse(manager.allPatternsExists(null));
        assertFalse(manager.allPatternsExists(null,null));
    }
}