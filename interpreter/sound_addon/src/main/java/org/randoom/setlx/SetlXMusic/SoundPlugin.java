package org.randoom.setlx.SetlXMusic;

import org.randoom.setlx.SetlXMusic.MusicSystem.MusicManager.MusicManager;
import org.randoom.setlx.SetlXMusic.MusicSystem.MusicManager.iMusicManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.*;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions.SetlXMidiNotAvailableException;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.*;
import org.randoom.setlx.SetlXMusic.factories.AtomFactory.*;
import org.randoom.setlx.SetlXMusic.factories.ChordProgressionFactory.*;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory.*;
import org.randoom.setlx.SetlXMusic.factories.PatternFactory.*;
import org.randoom.setlx.SetlXMusic.factories.RhythmFactory.*;

public class SoundPlugin implements iSoundPlugin {

    private static SoundPlugin setlxSoundPlugin;

    private iMusicPlayer musicPlayer;
    private iMusicManager musicManager;
    private iRealTimePlayer realTimePlayer;

    // Factories TODO Sum up factories
    private iAtomFactory atomFactory;
    private iNoteFactory noteFactory;
    private iChordProgressionFactory chordProgressionFactory;
    private iPatternFactory patternFactory;
    private iRhythmFactory rhythmFactory;

    private SoundPlugin() throws SetlXMidiNotAvailableException { //TODO SetlXMidiNotAvailable lassen?
        initializeComponents();
    }

    /**
     * Initializes all components: Instantiates the Factories, Players and Storages
     */
    private void initializeComponents() throws SetlXMidiNotAvailableException {

        // Initializes factories
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
    public iMusicPlayer getSetlxMusicPlayer() {
        return musicPlayer;
    }

    @Override
    public iRealTimePlayer getSetlXRealTimePlayer() {
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
            } catch (SetlXMidiNotAvailableException e) {
                e.printStackTrace();
            }
        }
        return setlxSoundPlugin;
    }
}
