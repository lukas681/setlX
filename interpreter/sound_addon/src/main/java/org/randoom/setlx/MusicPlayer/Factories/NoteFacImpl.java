package org.randoom.setlx.MusicPlayer.Factories;

import jm.music.data.Note;

public class NoteFacImpl implements NoteFacIntf{


    private final int defaultPan = 0;
    private final double defaultRhythmValue = 0;
    private final int defaultPitch = 0;
    private final int defaultDynamic = 0;

    @Override
    public Note createNote(int pitch, double rhythmValue, int dynamic, double pan) {
        return new Note(pitch, rhythmValue, dynamic,pan);
    }
    public Note createNote(int pitch, double rhythmValue, int dynamic){
        return createNote(pitch, rhythmValue, dynamic, defaultPan);
    }
    public Note createNote(int pitch, double rythmValue){
        return createNote(pitch, rythmValue, defaultDynamic, defaultPan);
    }
    public Note createNote(int pitch){
        return createNote(pitch,defaultRhythmValue,defaultDynamic,defaultPan);
    }
    public Note createNote(){
        return createNote(defaultPitch,defaultRhythmValue,defaultDynamic,defaultPan);
    }
}
