package org.randoom.setlx;

/**
 * Every Class that can be started with an play-command should implement this interface
 */
public interface Playable {
    //It is not running in the beginning
    boolean isRunning = false;


    /**
     * Plays the musical content, that is stored in this instance.
     * An recommended way to do this is to store Music Patterns.
     */
    void play();
}
