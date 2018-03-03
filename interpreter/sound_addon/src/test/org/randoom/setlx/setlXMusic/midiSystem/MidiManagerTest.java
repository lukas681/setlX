package org.randoom.setlx.setlXMusic.midiSystem;

import org.jfugue.pattern.Pattern;
import org.junit.Before;
import org.junit.Test;
import org.randoom.setlx.setlXMusic.musicPlayer.MusicPlayer;
import org.randoom.setlx.setlXMusic.musicSystem.musicManager.MusicManager;

import javax.sound.midi.InvalidMidiDataException;
import java.io.IOException;

import static org.junit.Assert.*;

public class MidiManagerTest {

    Pattern testPattern;
    MidiManager manager;
    MusicPlayer Player;
    @Before
    public void setUp() throws Exception {
      testPattern = new Pattern("C D E F G A B H");
      manager = new MidiManager();
      Player = new MusicPlayer(new MusicManager());
    }

    @Test
    public void load() throws IOException, InvalidMidiDataException {
        manager.save("test.mid", testPattern); //Assuming correctness of this method.
        Pattern loadedPattern = manager.load("test.mid");
        assertNotNull(loadedPattern);
        // loadedPattern = manager.load("Fugue12.mid");
        // manager.save("test2", loadedPattern);
    }

    @Test
    public void save() throws IOException {
        manager.save("", testPattern);
    }

    @Test
    public void hasFileEnding() {
        assertFalse(manager.hasFileEnding("/this/is/a/absolute/path/mid","mid"));
        assertTrue(manager.hasFileEnding("./this/is/a/relative/path/mid.mid","mid"));
        assertFalse(manager.hasFileEnding("./this/is/a/relative/path/mid","mid"));
        assertFalse(manager.hasFileEnding("/this/is/a/absolute/path/mid","mid"));
        assertTrue(manager.hasFileEnding("test.mid","mid"));
        assertFalse(manager.hasFileEnding("test","mid"));
        assertFalse(manager.hasFileEnding("","mid"));
    }

    @Test
    public void demo() {
        Pattern song = new Pattern("C D E F G");
        Pattern p1 = new Pattern("C D E F G");
        Pattern p2 = new Pattern("C D E F G");

        song.add(p1, p1);

    }
}