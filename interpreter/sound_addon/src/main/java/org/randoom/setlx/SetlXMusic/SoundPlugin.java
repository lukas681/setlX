package org.randoom.setlx.SetlXMusic;

import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.MusicManager;
import org.randoom.setlx.SetlXMusic.Patterns.SetlXPatternManager.iMusicManager;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.MusicPlayer;
import org.randoom.setlx.SetlXMusic.SetlXMusicPlayer.iMusicPlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.Exceptions.SetlXMidiNotAvailableException;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.RealTimerPlayer;
import org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer.iRealTimePlayer;
import org.randoom.setlx.SetlXMusic.factories.AtomFactory.AtomFactory;
import org.randoom.setlx.SetlXMusic.factories.AtomFactory.iAtomFactory;
import org.randoom.setlx.SetlXMusic.factories.ChordProgressionFactory.ChordProgressionFactory;
import org.randoom.setlx.SetlXMusic.factories.ChordProgressionFactory.iChordProgressionFactory;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory.NoteFactory;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory.iNoteFactory;
import org.randoom.setlx.SetlXMusic.factories.PatternFactory.PatternFactory;
import org.randoom.setlx.SetlXMusic.factories.PatternFactory.iPatternFactory;
import org.randoom.setlx.SetlXMusic.factories.RhythmFactory.RhythmFactory;
import org.randoom.setlx.SetlXMusic.factories.RhythmFactory.iRhythmFactory;

public class SoundPlugin implements iSoundPlugin {

    private static SoundPlugin setlxSoundPlugin;

    private iMusicPlayer musicPlayer;
    private iMusicManager patternManager;
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

        patternManager = new MusicManager();
        musicPlayer = new MusicPlayer(patternManager);
        realTimePlayer = new RealTimerPlayer(noteFactory, atomFactory);

    }

    @Override
    public iMusicManager getSetlXPatternManager() {
        return patternManager;
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
