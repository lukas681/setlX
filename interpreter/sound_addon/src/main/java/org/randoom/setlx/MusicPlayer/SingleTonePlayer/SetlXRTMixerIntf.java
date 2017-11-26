package org.randoom.setlx.MusicPlayer.SingleTonePlayer;

public interface SetlXRTMixerIntf {


    void pause();

    void unPause();

    void begin();

    void stop();

    void addNewSetlXRTLine(SetlXRTLineIntf[] lines);


    void removeSetlXRTLine();

}
