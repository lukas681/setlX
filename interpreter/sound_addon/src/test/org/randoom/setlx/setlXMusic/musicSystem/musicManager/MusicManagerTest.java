package org.randoom.setlx.setlXMusic.musicSystem.musicManager;

import com.sun.org.apache.xml.internal.utils.res.XResources_ja_JP_HA;
import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.CanNotConvertException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.KeyAlreadyInUseException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.NullArgumentsException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotFoundExceptions.ChordProgressionNotFoundException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotFoundExceptions.PatternNotFoundException;
import org.randoom.setlx.setlXMusic.musicSystem.exceptions.ProducerNotSupportedException;
import org.randoom.setlx.setlXMusic.musicSystem.storages.StorageTypes;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class MusicManagerTest {

    MusicManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new MusicManager();

        // Declaring some Test Pattern for later use
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
    public void addPatternsToPatternByName1() {

        // TODO difficult to test
    }

    @Test
    public void addPatternsToPattern() {

    // TODO difficult to test
    }


    @Test
    public void modifyPatternProperty() {
        // TOO difficult to Test
    }

    @Test
    public void removeElementPattern() throws PatternNotFoundException {
        int patternSize = manager.getAllPatterns().size();
        manager.removeElement("Test");
        assertTrue(patternSize== manager.getAllPatterns().size()+1); // There must be one element less...

    }
    @Test
    public void removeElementRhythm() throws PatternNotFoundException, KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        manager.add("Rhythm1", new Rhythm("I IV V I"));
        int rhythmSize = manager.getAllRhythms().size();
        manager.removeElement("Rhythm1");
        assertTrue(rhythmSize== manager.getAllRhythms().size()+1); // There must be one element less...

    }
    @Test
    public void removeElementChordProgression() throws PatternNotFoundException, KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        manager.add("Chord1", new ChordProgression("I IV V I"));
        int chordSize = manager.getAllChordProgressions().size();
        manager.removeElement("Chord1");
        assertTrue(chordSize == manager.getAllChordProgressions().size()+1); // There must be one element less...
    }


    @Test
    public void getPattern() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        Pattern patt = new Pattern("C D E F G A B C6");
        manager.add("patt", patt);
        assertEquals(patt, manager.getPattern("patt"));
    }

    @Test(expected = PatternNotFoundException.class)
    public void getNonExistingPattern() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        assertNotNull(manager.getPattern("loremipsum"));
    }

    @Test (expected = PatternNotFoundException.class)
    public void getPatternFromNullValue() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        assertNotNull(manager.getPattern("loremipsum"));
    }

    @Test
    public void duplicatePattern() throws KeyAlreadyInUseException, PatternNotFoundException, NullArgumentsException {
        manager.duplicatePattern("Test","Test-Duplicate");
        assertTrue(manager.allPatternsExists("Test-Duplicate"));
        // TODO this line
        //      assertEquals(manager.getPattern("Test-Duplicate"), manager.getPattern("Test"));
    }

    @Test
    public void getDetailPatternStats() throws PatternNotFoundException {
        assertNotNull(manager.getDetailPatternStats("Test"));
    }

    @Test (expected = PatternNotFoundException.class)
    public void getDetailPatternStatsWithNullValue() throws PatternNotFoundException {
        manager.getDetailPatternStats(null);
    }

    @Test (expected = PatternNotFoundException.class)
    public void getDetailPatternStatsWithInvalidString() throws PatternNotFoundException {
        manager.getDetailPatternStats("loremipsumdolorsitamet");
    }

    @Test
    public void getGeneralPatternStats() throws PatternNotFoundException, KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        Pattern patt = new Pattern("C D E F | G A B C6 Rw"); // A simple C Maj scale. 8 Notes, 0 Rests
        manager.add("scale", patt);
        int[] stats = manager.getGeneralPatternStats("scale");
        assertNotNull(stats);
        assertEquals(stats[0], 8); // 8 Notes
        assertEquals(stats[1], 1); // 1 Rest
        assertEquals(stats[2], 1); // 1 measure
    }

    @Test(expected = PatternNotFoundException.class)
    public void getGeneralPatternStatsFromNonExistingPattern() throws PatternNotFoundException, KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        manager.getGeneralPatternStats(null);
    }

    @Test
    public void getRhythm() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        Rhythm rhythm1 = new Rhythm("I IV V I");
        manager.add("rhythm1", rhythm1);
        assertEquals(manager.getRhythm("rhythm1"), rhythm1);
    }
    @Test(expected = PatternNotFoundException.class)
    public void getNotRegisteredRhythmProgression() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.getRhythm("loremipsumdolorsitametsed");
    }

    @Test(expected = PatternNotFoundException.class)
    public void getRhythmProgressionFromNullArgument() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.getRhythm(null);
    }

    @Test
    public void getChordProgression() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        ChordProgression prog1 = new ChordProgression("I IV V I");
        manager.add("Chord1", prog1);
        assertEquals(manager.getChordProgression("Chord1"), prog1);
    }
    @Test(expected = PatternNotFoundException.class)
    public void getNotRegisteredChordProgression() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.getChordProgression("loremipsumdolorsitametsed");
    }

    @Test(expected = PatternNotFoundException.class)
    public void getChordProgressionFromNullArgument() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.getChordProgression(null);
    }



    @Test
    public void getAllRhythms() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        manager.add("Rhythm1", new Rhythm(".x.x.x.x.x"));
        manager.add("Rhythm2", new Rhythm("x.x.x.x.x."));
        manager.add("Rhythm3", new Rhythm(".x.x.x.x.x"));
        manager.add("Rhythm14", new Rhythm("x.x.x.x.x"));
        assertTrue(manager.getAllRhythms().size()==4);
        assertTrue(new MusicManager().getAllRhythms().isEmpty());
    }

    @Test
    public void getAllChordProgressions() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException {
        manager.add("Chord1", new ChordProgression("I IV V I"));
        manager.add("Chord2", new ChordProgression("I IV V I"));
        manager.add("Chord3", new ChordProgression("I IV V I"));
        manager.add("Chord4", new ChordProgression("I IV V I"));
        assertTrue(manager.getAllChordProgressions().size()==4);
        assertTrue(new MusicManager().getAllChordProgressions().isEmpty());
    }
    @Test
    public void getStorageWhereKeyIsUsed() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.add("Chord1", new ChordProgression("I IV V I"));
        manager.add("Rhythm1", new Rhythm("xxxxxx.x"));

        assertEquals(manager.getStorageWhereKeyIsUsed("Chord1"), StorageTypes.CHORD_PROGRESSION_STORAGE);
        assertEquals(manager.getStorageWhereKeyIsUsed("Test"), StorageTypes.PATTERN_STORAGE);
        assertEquals(manager.getStorageWhereKeyIsUsed("Rhythm1"), StorageTypes.RHYTHM_STORAGE);
        assertEquals(manager.getStorageWhereKeyIsUsed("Test Test"), StorageTypes.UNSUPPORTED_TYPE);
        assertEquals(manager.getStorageWhereKeyIsUsed(""), StorageTypes.UNSUPPORTED_TYPE);
        assertEquals(manager.getStorageWhereKeyIsUsed("12321"), StorageTypes.UNSUPPORTED_TYPE);
        assertEquals(manager.getStorageWhereKeyIsUsed("Pattern Chord"), StorageTypes.UNSUPPORTED_TYPE);
    }


    @Test
    public void saveAsPattern() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException, CanNotConvertException {
        manager.add("Chord1", new ChordProgression(("I IV V I")));
        manager.add("Rhythm1", new Rhythm((".x.x.x.x.x.x")));
        manager.saveAsPattern("Chord1");
        manager.saveAsPattern("Rhythm1");
        assertTrue(manager.allPatternsExists("Chord1_c"));
        assertTrue(manager.allPatternsExists("Rhythm1_c"));
    }

    @Test(expected = CanNotConvertException.class)
    public void saveAsPatternInvalidValue() throws CanNotConvertException, PatternNotFoundException, NullArgumentsException {
        manager.saveAsPattern("1");
        manager.saveAsPattern(null);
        manager.saveAsPattern("");
    }

    @Test
    public void saveAsMidi() {
        // TODO How to test this
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

        Patterns.forEach((x,y) -> { //Inserting patterns
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