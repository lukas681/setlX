package org.randoom.setlx.SetlXMusic.SetlXRealTimePlayer;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.SynthesizerManager;
import org.jfugue.realtime.RealtimeInterpolator;
import org.jfugue.realtime.RealtimeMidiParserListener;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;
import org.randoom.setlx.SetlXMusic.factories.AtomFactory;
import org.randoom.setlx.SetlXMusic.factories.NoteFactory;
import org.randoom.setlx.SetlXMusic.factories.iAtomFactory;
import org.randoom.setlx.SetlXMusic.factories.iNoteFactory;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by Lukas on 28.12.2017.
 */
public class SetlXRealTimePlayer implements iSetlXRealTimePlayer {

    RealtimePlayer rtplayer;
    int noteDuration = 1;
    iNoteFactory noteFac;
    iAtomFactory atomFac;

    public SetlXRealTimePlayer() {
        try {
            rtplayer = new RealtimePlayer();
        }catch(MidiUnavailableException midiException){
            System.out.println("The Midi device is not availbable"); //TODO Add Exception for this
        }
        noteFac = new NoteFactory();
        atomFac = new AtomFactory();
    }

    @Override
    public void stopNotes() {
        rtplayer.close();
        try {
            //TODO Not a nice solutions, but there is no option to stop RealTime playbacks
            rtplayer = new RealtimePlayer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace(); //TODO Throw Execption for this
        }
    }

    @Override
    public void play(PatternProducer pattern) {
        rtplayer.play(pattern);
    }

    @Override
    public void play(Atom musicalUnit) {
        rtplayer.play(musicalUnit);
    }

    @Override
    public void play(byte voice, byte layer, byte instrument, Note note) {
        rtplayer.play(atomFac.createAtom(voice, layer, instrument, note));
    }

    @Override
    public void play(byte voice, byte layer, byte instrument, int value, double duration) {
        play(voice, layer, instrument, noteFac.createNote(value, duration));
    }

    @Override
    public void changeInstrument(int instrument) {
        rtplayer.changeInstrument(instrument);
    }

    @Override
    public void setNoteDuration(int duration) {
        this.noteDuration = duration;
    }

    public static void main(String[] args) {
        SetlXRealTimePlayer real = new SetlXRealTimePlayer();
        real.play(new Pattern("C D E F G A B C"));
        real.stopNotes();
    }
}
