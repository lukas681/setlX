package org.randoom.setlx.setlXMusic.factories.noteFactory;

import org.jfugue.theory.Note;

/**
 * Created by Lukas on 28.12.2017.
 */
public class NoteFactory implements iNoteFactory {

    @Override
    public Note createNote() {
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
}
