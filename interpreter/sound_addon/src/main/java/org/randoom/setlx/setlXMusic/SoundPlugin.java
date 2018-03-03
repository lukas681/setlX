package org.randoom.setlx.SetlXMusic;

import org.randoom.setlx.SetlXMusic.MusicPlayer.MusicPlayer;
import org.randoom.setlx.SetlXMusic.MusicPlayer.iMusicPlayer;
import org.randoom.setlx.SetlXMusic.MusicSystem.MusicManager.MusicManager;
import org.randoom.setlx.SetlXMusic.MusicSystem.MusicManager.iMusicManager;
import org.randoom.setlx.SetlXMusic.RealTimeSystem.Exceptions.MidiNotAvailableException;
import org.randoom.setlx.SetlXMusic.RealTimeSystem.*;
import org.randoom.setlx.SetlXMusic.Factories.AtomFactory.*;
import org.randoom.setlx.SetlXMusic.Factories.ChordProgressionFactory.*;
import org.randoom.setlx.SetlXMusic.Factories.NoteFactory.*;
import org.randoom.setlx.SetlXMusic.Factories.PatternFactory.*;
import org.randoom.setlx.SetlXMusic.Factories.RhythmFactory.*;

public class SoundPlugin implements iSoundPlugin {

    private static SoundPlugin setlxSoundPlugin;

    private iMusicPlayer musicPlayer;
    private iMusicManager musicManager;
    private iRealTimePlayer realTimePlayer;

    // Factories TODO Sum up Factories
    private iAtomFactory atomFactory;
    private iNoteFactory noteFactory;
    private iChordProgressionFactory chordProgressionFactory;
    private iPatternFactory patternFactory;
    private iRhythmFactory rhythmFactory;

    private SoundPlugin() throws MidiNotAvailableException { //TODO SetlXMidiNotAvailable lassen?
        initializeComponents();
    }

    /**
     * Initializes all components: Instantiates the Factories, Players and Storages
     */
    private void initializeComponents() throws MidiNotAvailableException {

        // Initializes Factories
        atomFactory = new AtomFactory();
        noteFactory = new NoteFactory();
        chordProgressionFactory = new ChordProgressionFactory();
        patternFactory = new PatternFactory();
        rhythmFactory = new RhythmFactory();

        musicManager = new MusicManager();
        musicPlayer = new MusicPlayer(musicManager);
        realTimePlayer = new RealTimerPlayer(noteFactory, atomFactory);

    }

    @Override
    public iMusicManager getMusicManager() {
        return musicManager;
    }

    @Override
    public iMusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    @Override
    public iRealTimePlayer getRealTimePlayer() {
        return realTimePlayer;
    }

    @Override
    public iAtomFactory getAtomFactory() {
        return atomFactory;
    }

    @Override
    public iPatternFactory getPatternFactoy() {
        return patternFactory;
    }

    @Override
    public iChordProgressionFactory getChordProgressionFactory() {
        return chordProgressionFactory;
    }

    @Override
    public iNoteFactory getNoteFactory() {
        return noteFactory;
    }

    @Override
    public iRhythmFactory getRhythmFactory() {
        return rhythmFactory;
    }

    public static SoundPlugin getInstance() {
        if (setlxSoundPlugin == null) { //Singleton
            try {
                setlxSoundPlugin = new SoundPlugin();
            } catch (MidiNotAvailableException e) {
                e.printStackTrace();
            }
        }
        return setlxSoundPlugin;
    }
}
