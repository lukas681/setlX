package org.randoom.setlx.setlXMusic.factories.atomFactory;


import org.jfugue.pattern.Atom;
import org.jfugue.theory.Note;

public interface iAtomFactory {
    byte defaultVoice = 1;
    byte defaultLayer = 1;
    byte defaultInstrument = 1;

    /**
     * Create a new {@link Atom}, that can be used for Real Time processing.
     * An Atom containes the Note, that is to be played AND information about the
     * voice, layer and instrument, that normally would be set global for a
     * {@link org.jfugue.realtime.RealtimePlayer}
     *
     * @param note
     * @return
     */
    Atom createAtom(Note note);

    Atom createAtom(byte voice, Note note);

    Atom createAtom(byte voice, byte layer, Note note);

    Atom createAtom(byte voice, byte layer, byte instrument, Note note);


}
