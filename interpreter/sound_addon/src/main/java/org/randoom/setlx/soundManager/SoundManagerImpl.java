package org.randoom.setlx.soundManager;

import org.randoom.setlx.exceptions.SetlException;

import javax.sound.midi.*;
import javax.swing.*;

public class SoundManagerImpl {
    Synthesizer synthesizer;
    public static SoundManagerImpl instance;
    private MidiChannel[] midiChannels;
    private Instrument[] instruments;
    private int instrumentIndex = 0;

    /**
     * private Constructor as we use the Soundmanager as a Singleton
     */
    private SoundManagerImpl(){
        openDefaultMidiSynthesizer();
        initializeMidiChannels();
    }

    /**
     * Creates the Singleton instance and returns it
     * @return
     */
    public static SoundManagerImpl getInstance() {
        if(instance==null){
            instance = new SoundManagerImpl();
        }
        return instance;
    }

    /**
     * This method opens the default Midi Synthesizer. A Synthesizer is used in order to interpret midi signals and
     * start an actual playback.
     */
    public void openDefaultMidiSynthesizer(){
        try {
            synthesizer= MidiSystem.getSynthesizer();
            if(synthesizer == null) {
                //TODO setl exception
            }else{
                synthesizer.open();
            }
        } catch (MidiUnavailableException e) {
            e.printStackTrace(); //TODO Improve this line
        }
    }

    /**
     * Will inizialize the Channels and creates a simple default bank.
     */
    public void initializeMidiChannels(){
        this.midiChannels = synthesizer.getChannels();
        Soundbank bank = synthesizer.getDefaultSoundbank();
        synthesizer.loadAllInstruments(bank);
        this.instruments = synthesizer.getAvailableInstruments();
        synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
        synthesizer.getChannels()[0].programChange(instrumentIndex);
    }


    /**
     * Can be used in order to play a specific MIDI-note
     * @param noteNumber The number of the note ranging form 0-127
     * @param velocity
     * @param duration The duration of the playback
     */
    public void playTone(int noteNumber, int velocity, int duration){
            midiChannels[0].noteOn(noteNumber, velocity);
            try {//TODO THREAD.SLEEP SCHÖN? TRY/CATCH WEG
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            midiChannels[0].noteOff(noteNumber);
    }
    public void playTone(int noteNumber, int velocity, int duration, int instrument) {
            midiChannels[0].programChange(0, instrument);
            playTone(noteNumber, velocity, duration);
    }

    /**
     * A simple function in order to show all available Instruments and its belonging ID
     * @return
     */
    public String listInstruments(){
        Instrument[] orchestra = synthesizer.getAvailableInstruments();
        final StringBuilder sb = new StringBuilder();
        sb.append("The orchestra has "+ orchestra.length  +"instruments.\n");
        for (Instrument instrument : orchestra) {
            sb.append(instrument.toString()+"\n");
        }
        synthesizer.close();
        return sb.toString();
    }
}
