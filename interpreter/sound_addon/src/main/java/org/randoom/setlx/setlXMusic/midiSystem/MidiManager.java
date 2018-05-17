package org.randoom.setlx.setlXMusic.midiSystem;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

public class MidiManager implements iMidiManager {

    @Override
    public Pattern load(String filename) throws IOException, InvalidMidiDataException {
        Pattern loadedPattern;
        File file = new File(filename);
        loadedPattern = MidiFileManager.loadPatternFromMidi(file);
        return loadedPattern;
    }

    @Override
    public void save(String filename, Pattern pattern) throws IOException {
        if (filename.compareTo("") == 0) {

            filename = "song_" + System.nanoTime();
        }
        if (!hasFileEnding(filename, ".mid")) {
            filename += ".mid";
        }
        File file = new File(filename);
        MidiFileManager.savePatternToMidi(pattern, file);
    }

    public boolean hasFileEnding(String filename, String ending) {
        return filename.matches(".*[.]" + ending);
    }
}
