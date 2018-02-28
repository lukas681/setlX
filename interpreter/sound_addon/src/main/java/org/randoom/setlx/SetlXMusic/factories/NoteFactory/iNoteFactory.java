package org.randoom.setlx.SetlXMusic.factories.NoteFactory;

import org.jfugue.theory.Note;

/**
 * A simple factory for creating notes
 */
public interface iNoteFactory {
    int defaultNoteValue = 50;
    int defaultDuration = 1;

    Note createNote();

    Note createNote(int value);

    /**
     * Creates a new Note
     *
     * @param value    The note Value
     * @param duration a duration in seconds
     * @return
     */
    Note createNote(int value, double duration);


}
