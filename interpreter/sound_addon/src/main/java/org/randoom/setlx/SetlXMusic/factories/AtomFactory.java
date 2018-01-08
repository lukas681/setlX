package org.randoom.setlx.SetlXMusic.factories;

import org.jfugue.pattern.Atom;
import org.jfugue.theory.Note;

public class AtomFactory implements iAtomFactory {

    @Override
    public Atom createAtom(Note note) {
        return createAtom(defaultVoice, note);
    }

    @Override
    public Atom createAtom(byte voice, Note note) {
        return createAtom(defaultLayer, voice, note);
    }

    @Override
    public Atom createAtom(byte voice, byte layer, Note note) {
        return createAtom(voice, layer, defaultInstrument, note);
    }

    @Override
    public Atom createAtom(byte voice, byte layer, byte instrument, Note note) {
        return new Atom(voice, layer, instrument, note);
    }

}
