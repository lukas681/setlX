package org.randoom.setlx.SetlXMusic.SetlXMusicPlayer;

import org.randoom.setlx.Exceptions.PatternNotFoundException;
import org.staccato.StaccatoParser;

/**
 * Created by Lukas on 28.12.2017.
 */
public interface iSetlXMusicPlayer { //TODO Maybe a own song system
    void play(String... patternNames) throws PatternNotFoundException;

    //StaccatoParser getStaccatoParser();
}
