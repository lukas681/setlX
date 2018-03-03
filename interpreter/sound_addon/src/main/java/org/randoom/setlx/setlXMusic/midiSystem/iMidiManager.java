package org.randoom.setlx.setlXMusic.midiSystem;

import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.IOException;

public interface iMidiManager {

    /**
     * Loads a pattern from a midi file.
     *
     * @param filename
     * @return
     */
    Pattern load(String filename) throws IOException, InvalidMidiDataException;

    /**
     * Saves a {@link Pattern} to a midi file.
     *
     * @param filename
     */
    void save(String filename, Pattern pattern) throws IOException;


}
