package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.jfugue.pattern.PatternProducer;
import org.randoom.setlx.SetlXMusic.Patterns.Exceptions.ProducerNotFoundExceptions.PatternNotFoundException;

/**
 * Created by Lukas on 28.12.2017.
 */
public interface iMusicPlayer { //TODO Maybe a own song system
    /**
     * Plays the music elements with that name.
     * IMPORTANT: If you want to play more than one pattern at one, be aware, that the TEMPO of the first argument
     * will be used, if there are more different temppos given.
     * It does not make any sense, to allow different tempos at once.
     *
     * @param patternNames
     * @throws PatternNotFoundException
     */
    void play(String... patternNames) throws PatternNotFoundException;

    void play(PatternProducer pattern);
}
