package org.randoom.setlx.SetlXMusic.MusicSystem.MusicManager;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.KeyAlreadyInUseException;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.NullArgumentsException;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.ProducerNotFoundExceptions.PatternNotFoundException;
import org.randoom.setlx.SetlXMusic.MusicSystem.Exceptions.ProducerNotSupportedException;

import static org.junit.Assert.*;

public class MusicManagerTest {

    MusicManager manager;
    @Before
    public void setUp() throws Exception {
        manager = new MusicManager();
    }

    @Test
    public void addPatternsToPatternByName() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.add("Pattern1", new Pattern("C D E"));
        manager.add("Pattern2", new Pattern("F G B"));

        manager.addPatternsToPatternByName("Pattern2", "Pattern1");

    }
    @Test
    public void allPatternsExists() throws KeyAlreadyInUseException, ProducerNotSupportedException, NullArgumentsException, PatternNotFoundException {
        manager.add("Pattern1", new Pattern("C D E"));
        manager.add("Pattern2", new Pattern("F G B"));

        assertTrue(manager.allPatternsExists("Pattern1"));
        assertTrue(manager.allPatternsExists("Pattern1", "Pattern2"));
        assertFalse(manager.allPatternsExists("Pattern3"));
        assertFalse(manager.allPatternsExists("Pattern3", "Pattern1"));
        assertFalse(manager.allPatternsExists("Pattern3", "Pattern2"));
        assertFalse(manager.allPatternsExists("Pattern3", "Pattern2","Pattern3","Pattern4","c","d"));

    }
}