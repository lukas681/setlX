package org.randoom.setlx.MusicPlayer.Factories;

import jm.music.data.Note;

public interface InstrumentsFacIntf {

    Note createNote(int pitch, double rhythmValue, int dynamic, double pan);
}
