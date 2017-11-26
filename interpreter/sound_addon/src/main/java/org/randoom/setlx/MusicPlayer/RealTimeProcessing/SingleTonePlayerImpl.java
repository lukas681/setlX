package org.randoom.setlx.MusicPlayer.RealTimeProcessing;

import jm.music.data.Note;

public class SingleTonePlayerImpl implements SingleTonePlayerIntf{

    Note playbackNote;

    public SingleTonePlayerImpl(){
        playbackNote = new Note();
    }

    @Override
    public void playTone() {

    }

    @Override
    public void play() {
      //  Player.midi();
    }
}
