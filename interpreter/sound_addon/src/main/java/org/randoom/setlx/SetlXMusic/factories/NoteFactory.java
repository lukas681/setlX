package org.randoom.setlx.SetlXMusic.factories;

import org.jfugue.theory.Note;

/**
 * Created by Lukas on 28.12.2017.
 */
public class NoteFactory implements iNoteFactory {

    private static NoteFactory instance;

    /**
     * Declaring a private Constructor, because we just need one NoteFactory
     * in the whole application
     */
    private NoteFactory(){

    }

    @Override
    public Note createNote(){
        return createNote(defaultNoteValue);
    }

    @Override
    public Note createNote(int value) {
        return createNote(value, defaultDuration);
    }

    @Override
    public Note createNote(int value, double duration) {
        return new Note(value, duration);
    }

    public static iNoteFactory getInstance() {
        if(NoteFactory.instance==null){
            instance = new NoteFactory();
        }
        return instance;
    }
}
