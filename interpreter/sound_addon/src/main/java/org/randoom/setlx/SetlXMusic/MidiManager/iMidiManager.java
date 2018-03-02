package org.randoom.setlx.SetlXMusic.MidiManager;

import org.jfugue.pattern.Pattern;

public interface iMidiManager {

    /**
     * Loads a pattern from a midi file.
     * @param filename
     * @return
     */
    Pattern load(String filename);

    /**
     * Saves a {@link Pattern} to a midi file.
     * @param filename
     * @return Success of operation: true if sucessfull
     */
    boolean save(String filename, Pattern pattern);


}
