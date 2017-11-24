package org.randoom.setlx.MusicPlayer.SingleTonePlayer;

import jm.music.data.Note;

/**
 * Created by Lukas on 24.11.2017.
 */
public interface RealTimeTonePlayerIntf {

    /**
     * Adds a new note into the queue.
     * If it is
     * @param n
     */
    public void addNote(Note n);

    public void pause();

    public void unPause();

    public void begin();

    public void stop();
}
