package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

public interface SetlXRTMixerIntf {


    void pause();

    void unPause();

    void begin();

    void stop();

    void addNewSetlXRTLines(SetlXRTLineIntf[] lines);
}
