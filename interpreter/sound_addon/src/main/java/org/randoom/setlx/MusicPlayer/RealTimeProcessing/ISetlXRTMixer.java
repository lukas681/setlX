package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

public interface ISetlXRTMixer {


    void pause();

    void unPause();

    void begin();

    void stop();

    void addNewSetlXRTLines(SetlXRTLine[] lines);

    void addNewSetlXRTLines(SetlXRTLine lines);
}