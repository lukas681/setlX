package org.randoom.setlx.SetlXMusic.SetlXMIDI;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

public class MidiManager implements iMidiManager {

    @Override
    public Pattern load(String filename) {
        Pattern loadedPattern = null;
        try {
            File file = new File(filename);
            loadedPattern = MidiFileManager.loadPatternFromMidi(file);
        } catch (IOException io) {
            io.printStackTrace(); // Todo != print Stacktrace
        } catch (InvalidMidiDataException mde) {
            mde.printStackTrace();
        }
        return loadedPattern;
    }

    @Override
    public boolean save(String filename, Pattern pattern) {
        if (filename.compareTo("") == 0) {

            filename = "Soundpattern_" + System.nanoTime();
        }
        if (!hasFileEnding(filename)) {
            filename += ".mid";
        }
        try {
            File file = new File(filename);
            MidiFileManager.savePatternToMidi(pattern, file);
            return true;
        } catch (IOException io) {
            io.printStackTrace();
        }
        return false;
    }

    public boolean hasFileEnding(String filename) {
        return filename.matches(".*[.].*");
    }
}
